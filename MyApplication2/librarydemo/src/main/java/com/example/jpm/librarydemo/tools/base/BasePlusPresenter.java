package com.example.jpm.librarydemo.tools.base;

import com.example.jpm.librarydemo.tools.util.LoggUtil;

/**
 * presenter的基础类
 * Created by lx464 on 2018/3/23.
 */
public class BasePlusPresenter<V extends IPlusView, T> implements IPlusPresenter {

    public IPlusView iBaseView = null;  //基类视图

    /**
     * @param view 具体业务的视图接口对象
     * @descriptoin 构造方法
     * @author dc
     * @date 2017/2/16 15:12
     */
    public BasePlusPresenter(V view) {
        this.iBaseView = view;
    }

    //Presenter注销接口防止内存泄漏
    public void detach() {
        if (iBaseView != null) {
            iBaseView = null;
        }
    }

    @Override
    public void showProgress() {
        iBaseView.showProgress();
    }

    @Override
    public void disimissProgress() {
        iBaseView.showProgress();
    }

    @Override
    public void setSuccessData2Presenter(Object result, int code) {
//        ToastUtil.showShort(MyApplication.getContext(),"1");
        LoggUtil.i("url","1");
        iBaseView.setSuccessData2View(result, code);
    }


    @Override
    public void loadDataError(int code) {
        iBaseView.loadDataError(code);
    }

}
