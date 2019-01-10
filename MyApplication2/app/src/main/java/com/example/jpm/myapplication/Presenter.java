package com.example.jpm.myapplication;

import com.example.jpm.librarydemo.tools.base.BasePlusPresenter;
import com.example.jpm.librarydemo.tools.base.BasePresenter;
import com.example.jpm.librarydemo.tools.base.IPlusView;
import com.example.jpm.librarydemo.tools.base.MyApplication;
import com.example.jpm.librarydemo.tools.util.LoggUtil;
import com.example.jpm.librarydemo.tools.util.ToastUtil;

/**
 * Created by mayn on 2018/12/10.
 */

public class Presenter extends BasePlusPresenter<IPlusView,Object> {

    public Model model=new Model(this);
    public Presenter(IPlusView view) {
        super(view);
    }

    public void loadData(String login_name,String password){
        model.loadData(login_name, password);
    }

    @Override
    public void setSuccessData2Presenter(Object result, int code) {
//        ToastUtil.showShort(MyApplication.getContext(),"2");
        LoggUtil.i("url","2");
        iBaseView.setSuccessData2View(result, code);
    }
}
