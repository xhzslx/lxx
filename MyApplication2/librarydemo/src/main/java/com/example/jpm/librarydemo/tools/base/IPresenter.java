package com.example.jpm.librarydemo.tools.base;

public interface IPresenter {

    //数据返回成功
    void setSuccessData2Presenter(String data, int code);

    //数据返回失败
    void setErrorData2Presenter(int code);

}
