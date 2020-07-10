package com.example.casestudy.presenter;

import android.util.Log;

import com.example.casestudy.activity.mainActivity.MainActivityContract;
import com.example.casestudy.main.App;
import com.example.casestudy.model.response.RepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View mView;

    @Override
    public void setView(MainActivityContract.View view) {
        this.mView = view;
    }

    @Override
    public void create() {
        mView.bindViews();
    }

    @Override
    public void request(String repoUserName) {
        App.apiService.retrieveUserRepos(repoUserName).enqueue(new Callback<List<RepoResponse>>() {
            @Override
            public void onResponse(Call<List<RepoResponse>> call, Response<List<RepoResponse>> response) {
                mView.hideProgress();
                mView.response(response.body() );
            }

            @Override
            public void onFailure(Call<List<RepoResponse>> call, Throwable t) {
                mView.hideProgress();
                Log.e("Error Retrieve Repo", t.toString());
            }
        });
    }
}
