package com.example.lamor.constant;

import android.content.Context;

import com.example.baselibrary.base.BaseView;

/**
 * Create by Administrator
 * on 2018/10/29
 */
public interface MainActivityConstant {

    interface MainActivityView extends BaseView{
        void startLoading();

        void hideLoading();

        void requestSuccess(String data);

        void requestFail(String data);
    }


    interface MainActivityPresenter{
        void start(Context context);
    }

}
