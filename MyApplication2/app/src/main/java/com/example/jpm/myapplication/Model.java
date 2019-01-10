package com.example.jpm.myapplication;

import com.example.jpm.librarydemo.tools.base.BaseModel;
import com.example.jpm.librarydemo.tools.base.IPlusPresenter;
import com.example.jpm.librarydemo.tools.util.CustomObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mayn on 2018/12/10.
 */

public class Model extends BaseModel {

    public IPlusPresenter iPlusPresenter;
    public MyApiService service;

    public Model(IPlusPresenter iPlusPresenter) {
        super(MyApplicationTest.getContext(), MyApiService.class);
        this.iPlusPresenter = iPlusPresenter;
        service = (MyApiService) manager.getApiService();
    }

    public void loadData(String login_name, String password) {
        service.SignIn(login_name, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserver<SignInbean>(iPlusPresenter, 10000));
    }
}
