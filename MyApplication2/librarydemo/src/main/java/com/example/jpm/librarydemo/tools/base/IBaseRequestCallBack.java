package com.example.jpm.librarydemo.tools.base;

public interface IBaseRequestCallBack<T> {

    /**
     * @descriptoin 请求开始
     * @author dc
     * @date 2017/2/16 11:34
     */
    void beforeRequest();

    /**
     * @descriptoin 请求异常
     * @author dc
     * @date 2017/2/16 11:34
     */
    void requestError(int code);

    /**
     * @descriptoin 请求完成
     * @author dc
     * @date 2017/2/16 11:35
     */
    void requestComplete();

    /**
     * @param callBack 根据业务返回相应的数据
     * @descriptoin 请求成功
     * @author dc
     * @date 2017/2/16 11:35
     */
    void requestSuccess(T callBack, int code);
}
