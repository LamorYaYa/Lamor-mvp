package com.example.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.baselibrary.R;

/**
 * @author Master
 * @create 2018/10/31 16:07
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment {

    private P presenter;
    private RelativeLayout iContainerView;
    private RelativeLayout iEmptyView;
    private RelativeLayout iErrorView;

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (presenter == null) {
            throw new NullPointerException("presenter not null!");
        }
        presenter.attachView((V) this);
        View viewContainer = inflater.inflate(R.layout.layout_base_container_view, container, false);
        iContainerView = viewContainer.findViewById(R.id.container_view);
        iEmptyView = viewContainer.findViewById(R.id.empty_view);
        iErrorView = viewContainer.findViewById(R.id.error_view);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = inflater.inflate(createContentView(), container, false);
        iContainerView.addView(view, layoutParams);

        View emptyView = attachEmptyView();
        if (emptyView != null) {
            iEmptyView.addView(emptyView, layoutParams);
        }

        View errorView = attachErrorView();
        if (errorView != null) {
            iErrorView.addView(errorView, layoutParams);
        }

        initView(viewContainer);
        initData();

        return viewContainer;
    }


    protected abstract P createPresenter();

    protected abstract int createContentView();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract View attachEmptyView();

    protected abstract View attachErrorView();

    public P getPresenter() {
        return presenter;
    }

    public void showContanterView() {
        iContainerView.setVisibility(View.VISIBLE);
        iEmptyView.setVisibility(View.GONE);
        iErrorView.setVisibility(View.GONE);
    }

    public void showEmptyView() {
        iContainerView.setVisibility(View.GONE);
        iEmptyView.setVisibility(View.VISIBLE);
        iErrorView.setVisibility(View.GONE);
    }

    public void showErrorView() {
        iContainerView.setVisibility(View.GONE);
        iEmptyView.setVisibility(View.GONE);
        iErrorView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            // 解绑防止内存泄漏
            presenter.detachView();
        }
    }

}
