package com.example.lamor;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.baselibrary.ILog;
import com.example.baselibrary.base.BaseActivity;
import com.example.lamor.constant.MainActivityConstant;
import com.example.lamor.constant.MainActivityPresenter;

public class MainActivity extends BaseActivity<MainActivityConstant.MainActivityView, MainActivityPresenter> implements MainActivityConstant.MainActivityView {

    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    protected int createContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ILog.e("init view");
        findViewById(R.id.show_container).setOnClickListener(v -> showContanterView());

        findViewById(R.id.show_empty).setOnClickListener(v -> showEmptyView());

        findViewById(R.id.show_error).setOnClickListener(v -> showErrorView());

        findViewById(R.id.retry).setOnClickListener(v -> Toast.makeText(MainActivity.this, "重试", Toast.LENGTH_SHORT).show());

    }

    @Override
    protected void initData() {
        ILog.e("init data");
        getPresenter().start(this);
    }

    @Override
    protected View attachEmptyView() {
        View view = LayoutInflater.from(this).inflate(R.layout.empty_layout, null);
        return view;
    }

    @Override
    protected View attachErrorView() {
        View view = LayoutInflater.from(this).inflate(R.layout.error_layout, null);
        return view;
    }

    @Override
    public void startLoading() {
        ILog.e("start Loading");
    }

    @Override
    public void hideLoading() {
        ILog.e("hide Loading");
    }

    @Override
    public void requestSuccess(String data) {
        ILog.e(data);
    }

    @Override
    public void requestFail(String data) {
        ILog.e(data);
    }
}
