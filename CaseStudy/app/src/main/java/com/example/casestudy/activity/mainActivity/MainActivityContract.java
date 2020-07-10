package com.example.casestudy.activity.mainActivity;

import com.example.casestudy.model.response.RepoResponse;
import com.example.casestudy.presenter.BasePresenter;
import com.example.casestudy.view.BaseView;

import java.util.List;

public interface MainActivityContract {
    interface View extends BaseView {
        void response(List<RepoResponse> repoResponses);
        void repoWarningShow();
        void repoWarningHide();
    }

    interface Presenter extends BasePresenter<View> {
        void request(String repoUserName);
    }
}
