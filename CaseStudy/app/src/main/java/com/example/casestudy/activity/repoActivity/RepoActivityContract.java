package com.example.casestudy.activity.repoActivity;

import com.example.casestudy.presenter.BasePresenter;
import com.example.casestudy.view.BaseView;

public interface RepoActivityContract {
    interface View extends BaseView {
        void showUserAvatar();
    }

    interface Presenter extends BasePresenter<View> {
    }
}
