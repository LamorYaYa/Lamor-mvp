package com.example.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.baselibrary.R;
import com.example.baselibrary.interfa.MultiStateLayout;

/**
 * Create by Administrator
 * on 2018/10/29
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity {

    private P presenter;
    private RelativeLayout iContainerView;
    private RelativeLayout iEmptyView;
    private RelativeLayout iErrorView;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
        }

        if (presenter == null) {
            throw new NullPointerException("presenter not null!");
        }

        presenter.attachView((V) this);
        setContentView(R.layout.layout_base_container_view);

        iContainerView = findViewById(R.id.container_view);
        iEmptyView = findViewById(R.id.empty_view);
        iErrorView = findViewById(R.id.error_view);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        try {
            View containerView = LayoutInflater.from(this).inflate(createContentView(), null);
            iContainerView.addView(containerView, layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        View emptyView = attachEmptyView();
        if (emptyView != null) {
            iEmptyView.addView(emptyView, layoutParams);
        }


        View errorView = attachErrorView();
        if (errorView != null) {
            iErrorView.addView(errorView, layoutParams);
        }


        initView();
        initData();
    }

    protected abstract P createPresenter();

    protected abstract int createContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract View attachEmptyView();

    protected abstract View attachErrorView();

    public P getPresenter() {
        return presenter;
    }

    public void showContanterView() {
        if (this instanceof MultiStateLayout) {
            iContainerView.setVisibility(View.VISIBLE);
            iEmptyView.setVisibility(View.GONE);
            iErrorView.setVisibility(View.GONE);
        }
    }

    public void showEmptyView() {
        if (this instanceof MultiStateLayout) {
            iContainerView.setVisibility(View.GONE);
            iEmptyView.setVisibility(View.VISIBLE);
            iErrorView.setVisibility(View.GONE);
        }

    }

    public void showErrorView() {
        if (this instanceof MultiStateLayout) {
            iContainerView.setVisibility(View.GONE);
            iEmptyView.setVisibility(View.GONE);
            iErrorView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            // 解绑防止内存泄漏
            presenter.detachView();
        }
    }

}
