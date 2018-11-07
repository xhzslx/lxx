package com.example.jpm.librarydemo.tools.base;

/**
 * presenter的基础类
 * Created by lx464 on 2018/3/23.
 */
public class BasePresenter<V extends IView, T> implements IBaseRequestCallBack<T> {

    private IView iBaseView = null;  //基类视图

    /**
     * @param view 具体业务的视图接口对象
     * @descriptoin 构造方法
     * @author dc
     * @date 2017/2/16 15:12
     */
    public BasePresenter(V view) {
        this.iBaseView = view;
    }

    /**
     * @descriptoin 请求之前显示progress
     * @author dc
     * @date 2017/2/16 15:13
     */
    @Override
    public void beforeRequest() {
        iBaseView.showProgress();
    }

    /**
     * @descriptoin 请求异常显示异常信息
     * @author dc
     * @date 2017/2/16 15:13
     */
    @Override
    public void requestError(Throwable e, int code) {
        iBaseView.disimissProgress(); //请求错误，提示错误信息之后隐藏progress
    }

    /**
     * @descriptoin 请求完成之后隐藏progress
     * @author dc
     * @date 2017/2/16 15:14
     */
    @Override
    public void requestComplete() {
        iBaseView.disimissProgress();
    }

    /**
     * @param callBack 回调的数据
     * @descriptoin 请求成功获取成功之后的数据信息
     * @author dc
     * @date 2017/2/16 15:14
     */
    @Override
    public void requestSuccess(T callBack, int code) {
        iBaseView.setSuccessData2View(callBack, code);
    }

    //Presenter注销接口防止内存泄漏
    public void detach() {
        if (iBaseView != null) {
            iBaseView = null;
        }
    }
}
