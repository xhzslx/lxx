package com.example.jpm.librarydemo.tools.base;

/**
 * Created by mayn on 2018/11/7.
 */

public class BasePresenter<T> {
    private T mview;

    public BasePresenter(T view) {
        this.mview = view;
    }

    public void detach() {
        if (this.mview != null) {
            this.mview = null;
        }

    }

}
