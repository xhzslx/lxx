package com.example.jpm.librarydemo.tools.util;

import com.example.jpm.librarydemo.tools.base.IPlusPresenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by mayn on 2019/1/10.
 */

public class CustomObserver<T> implements Observer<T>{

    private IPlusPresenter iPlusPresenter;
    private Disposable d;
    private int code;
    private int i=0;
    public CustomObserver(IPlusPresenter iPlusPresenter,int code){
        this.iPlusPresenter=iPlusPresenter;
        this.code=code;
    }
    @Override
    public void onSubscribe(Disposable d) {
        this.d=d;
    }

    @Override
    public void onNext(T t) {
        iPlusPresenter.setSuccessData2Presenter(t,code);
        i++;
        if (i == 2) {
            d.dispose();
        }

    }

    @Override
    public void onError(Throwable e) {
        iPlusPresenter.loadDataError(code);
    }

    @Override
    public void onComplete() {
        iPlusPresenter.showProgress();
    }
}
