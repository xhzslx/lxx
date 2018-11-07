package com.example.jpm.librarydemo.tools.base;

public interface IView<T>{
    /**
     * @descriptoin	请求前加载progress
     * @author	dc
     * @date 2017/2/16 11:00
     */
    void showProgress();

    /**
     * @descriptoin	请求结束之后隐藏progress
     * @author	dc
     * @date 2017/2/16 11:01
     */
    void disimissProgress();

    /**
     * @descriptoin	请求数据成功
     * @author	dc
     * @date 2017/2/16 11:01
     */
    void setSuccessData2View(T t, int code);

    /**
     * @descriptoin	请求数据错误
     * @author	dc
     * @param throwable 异常类型
     * @date 2017/2/16 11:01
     */
    void loadDataError(Throwable throwable,int code);

}
