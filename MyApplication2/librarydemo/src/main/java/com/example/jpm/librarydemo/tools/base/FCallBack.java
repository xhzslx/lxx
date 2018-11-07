package com.example.jpm.librarydemo.tools.base;

public interface FCallBack {

    //文件下载成功
    void onsuccess(int code);
    //文件下载失败
    void onError(int code);
   //文件下载中
    void onLoading(long total, long current);
}
