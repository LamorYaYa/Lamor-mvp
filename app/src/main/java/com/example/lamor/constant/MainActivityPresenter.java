package com.example.lamor.constant;

import android.content.Context;

import com.example.baselibrary.base.BasePresenter;

/**
 * Create by Administrator
 * on 2018/10/29
 */
public class MainActivityPresenter extends BasePresenter<MainActivityConstant.MainActivityView> implements MainActivityConstant.MainActivityPresenter {

    @Override
    public void start(Context context) {
        getView().startLoading();
        getView().hideLoading();
        getView().requestSuccess("call back request success");
        getView().requestFail("call back request fail");
    }
}
