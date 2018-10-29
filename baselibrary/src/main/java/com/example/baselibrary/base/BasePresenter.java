package com.example.baselibrary.base;

/**
 * Create by Administrator
 * on 2018/10/29
 */
public abstract class BasePresenter<T extends BaseView> {

    private T iView;

    public void attachView(T view) {
        this.iView = view;
    }

    public void detachView() {
        this.iView = null;
    }

    public T getView() {
        return iView;
    }
}
