package com.example.jpm.librarydemo.tools.base;

public interface XCallBack {

    //数据返回成功
    void onResponse(String result, int code);

    //数据返回失败
    void onError(int code);
}
