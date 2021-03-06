package com.example.jpm.librarydemo.tools.base;

/**
 * Created by mayn on 2018/12/10.
 */

public interface IPlusPresenter<T> {

    /**
     * @descriptoin 请求前加载progress
     * @author dc
     * @date 2017/2/16 11:00
     */
    void showProgress();

    /**
     * @descriptoin 请求结束之后隐藏progress
     * @author dc
     * @date 2017/2/16 11:018
     */

    void disimissProgress();

    /**
     * @descriptoin 请求数据成功
     * @author dc
     * @date 2017/2/16 11:01
     */
    void setSuccessData2Presenter(T result, int code);

    /**
     * @descriptoin 请求数据错误
     * @author dc
     * @date 2017/2/16 11:01
     */
    void loadDataError(int code);
}
