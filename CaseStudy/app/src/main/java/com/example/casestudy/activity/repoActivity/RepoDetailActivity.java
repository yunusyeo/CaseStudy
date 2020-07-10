package com.example.casestudy.activity.repoActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casestudy.constants.Constants;
import com.example.casestudy.R;
import com.example.casestudy.main.BaseActivity;
import com.example.casestudy.model.RepoDetail;
import com.example.casestudy.presenter.RepoActivityPresenter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import es.dmoral.toasty.Toasty;

public class RepoDetailActivity extends BaseActivity implements RepoActivityContract.View {
    public static final String REPO_DETAIL = "REPO_DETAIL";
    private Toolbar toolbar;
    private TextView userName;
    private TextView repoStarsCount;
    private TextView repoOpenIssuesCount;
    private ImageView userAvatar;
    private ProgressBar progressBar;

    private RepoDetail repoDetail;
    private boolean isSaved;

    private RepoActivityPresenter repoActivityPresenter;

    public static Intent newIntent(Activity activity, RepoDetail repoDetail) {
        Intent intent = new Intent(activity, RepoDetailActivity.class);
        intent.putExtra(REPO_DETAIL, (Serializable) repoDetail);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            repoDetail = (RepoDetail) bundle.getSerializable(REPO_DETAIL);
        }

        repoActivityPresenter = new RepoActivityPresenter();
        repoActivityPresenter.setView(this);
        repoActivityPresenter.create();
    }

    private void setUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        userName = (TextView) findViewById(R.id.user_name_text);
        repoStarsCount = (TextView) findViewById(R.id.repo_stars_count);
        repoOpenIssuesCount = (TextView) findViewById(R.id.repo_open_issues_count);
        userAvatar = (ImageView) findViewById(R.id.user_avatar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            if (isSaved) {
                isSaved = false;
                removeFavoriteList(isSaved);
            } else {
                isSaved = true;
                addFavoriteList(isSaved);
            }
            invalidateOptionsMenu();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addFavoriteList(boolean isSaved) {
        if (repoDetail != null && isSaved) {
            addFavoriteListItem(String.valueOf(repoDetail.getId()), repoDetail.getId());
            Toasty.success(this, getResources().getString(R.string.repo_add_favorite_list), Toast.LENGTH_SHORT, true).show();
        }
    }

    private void removeFavoriteList(boolean isSaved) {
        if (repoDetail != null && !isSaved) {
            removeFavoriteListItem(String.valueOf(repoDetail.getId()));
            Toasty.error(this, getResources().getString(R.string.repo_remove_favorite_list), Toast.LENGTH_SHORT, true).show();

        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isSaved) {
            menu.findItem(R.id.action_favorite)
                    .setIcon(R.drawable.ic_star_on)
                    .setTitle(R.string.save);
        } else {
            menu.findItem(R.id.action_favorite)
                    .setIcon(R.drawable.ic_star_off)
                    .setTitle(R.string.unsave);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void bindViews() {
        setUI();
        showProgress();
        if (repoDetail != null) {
            setTitle(repoDetail.getRepoName());
            userName.setText(repoDetail.getUserName());
            repoStarsCount.setText(String.valueOf(repoDetail.getRepoStarsCount()));
            repoOpenIssuesCount.setText(String.valueOf(repoDetail.getRepoOpenIssuesCount()));

            //Avatar için, Picasso ile UI'a basılırken hata olmasında ya da url boş olması durumda default avatar kullanıldı.
            if (repoDetail.getUserAvatarUrl() != null) {
                Picasso.with(getApplicationContext()).load(repoDetail.getUserAvatarUrl()).into(userAvatar, new Callback() {
                    @Override
                    public void onSuccess() {
                        showUserAvatar();
                        hideProgress();
                    }

                    @Override
                    public void onError() {
                        hideProgress();
                    }
                });
            } else {
                showUserAvatar();
            }
            //Favoriler listesi kontrolü
            if (retrieveFavoriteListItem(String.valueOf(repoDetail.getId())) != Constants.ZERO) {
                isSaved = true;
            }
        }
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
    public void showUserAvatar() {
        userAvatar.setVisibility(View.VISIBLE);
    }
}
