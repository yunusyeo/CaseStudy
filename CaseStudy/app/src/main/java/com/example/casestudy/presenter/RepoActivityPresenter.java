package com.example.casestudy.presenter;

import com.example.casestudy.activity.repoActivity.RepoActivityContract;

public class RepoActivityPresenter implements RepoActivityContract.Presenter {
    private RepoActivityContract.View mView;

    @Override
    public void setView(RepoActivityContract.View view) {
        this.mView = view;
    }

    @Override
    public void create() {
        mView.bindViews();
    }
}
