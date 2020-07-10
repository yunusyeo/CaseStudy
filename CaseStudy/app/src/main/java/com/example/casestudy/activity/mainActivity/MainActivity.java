package com.example.casestudy.activity.mainActivity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casestudy.R;
import com.example.casestudy.main.BaseActivity;
import com.example.casestudy.model.response.RepoResponse;
import com.example.casestudy.presenter.MainActivityPresenter;
import com.example.casestudy.util.Util;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity extends BaseActivity implements MainActivityContract.View {
    private Toolbar toolbar;
    private EditText userName;
    private TextView repoWarning;
    private Button send;
    private RecyclerView repoList;
    private ProgressBar progressBar;
    private RepoListAdapter repoListAdapter;

    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainActivityPresenter();
        mainActivityPresenter.setView(this);
        mainActivityPresenter.create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Detay sayfasından tekrar geri geliğimizde, eğer repo favoriler listesine eklenmiş ise listede gösterilmesi için
        repoListAdapter.notifyDataSetChanged();
    }

    private void setUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.home_page));

        userName = (EditText) findViewById(R.id.user_name_text);
        repoWarning = (TextView) findViewById(R.id.repo_warning_text);
        send = (Button) findViewById(R.id.send_btn);
        repoList = (RecyclerView) findViewById(R.id.repo_list);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public void response(List<RepoResponse> repoResponsese) {
        if (repoResponsese != null && !repoResponsese.isEmpty()) {
            repoWarningHide();
            repoListAdapter.repoResponses = repoResponsese;
            repoListAdapter.notifyDataSetChanged();
        } else {
            if (repoListAdapter.repoResponses != null && !repoListAdapter.repoResponses.isEmpty()) {
                repoListAdapter.repoResponses.clear();
            }
            repoWarningShow();
        }
    }

    @Override
    public void bindViews() {
        setUI();
        RecyclerView.LayoutManager chippinFriendLayoutManager = new LinearLayoutManager(getApplicationContext());
        repoList.setLayoutManager(chippinFriendLayoutManager);
        repoList.setItemAnimator(new DefaultItemAnimator());
        repoList.setHasFixedSize(true);
        repoListAdapter = new RepoListAdapter(this, new ArrayList<RepoResponse>());
        repoList.setAdapter(repoListAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.checkInternetConnection(getApplicationContext())) {
                    repoWarningHide();
                    showProgress();
                    Util.hideKeyboard(MainActivity.this);
                    mainActivityPresenter.request(userName.getText().toString().trim());
                } else {
                    hideProgress();
                    Toasty.error(MainActivity.this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void repoWarningShow() {
        repoWarning.setVisibility(View.VISIBLE);
    }

    @Override
    public void repoWarningHide() {
        repoWarning.setVisibility(View.GONE);
    }
}
