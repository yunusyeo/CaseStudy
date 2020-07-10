package com.example.casestudy.presenter;

import com.example.casestudy.view.BaseView;

public interface BasePresenter<T extends BaseView> {
    void setView(T view);
    void create();
}
