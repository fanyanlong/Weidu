package com.weidu.weidudianshang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.adapter.CircleAdapter;
import com.weidu.weidudianshang.bean.CircleBean;
import com.weidu.weidudianshang.circle.presenter.CirclePresenter;
import com.weidu.weidudianshang.circle.view.ICircleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CircleFragment extends Fragment implements ICircleView {
    @BindView(R.id.mXRecycler)
    XRecyclerView mXRecycler;
    Unbinder unbinder;
    private int page=1;
    private int count=8;
    private CircleAdapter circleAdapter;
    private CirclePresenter circlePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_circle, null);

        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mXRecycler.setLayoutManager(layoutManager);
        mXRecycler.setLoadingMoreEnabled(true);
        mXRecycler.setPullRefreshEnabled(true);
        mXRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page++;
                circlePresenter = new CirclePresenter(CircleFragment.this);
                circlePresenter.getModelData(page,count);
            }

            @Override
            public void onLoadMore() {
                circlePresenter = new CirclePresenter(CircleFragment.this);
                circlePresenter.getModelData(page,count);
            }
        });
        circlePresenter = new CirclePresenter(this);
        circlePresenter.getModelData(page,count);
        return view;
    }

    @Override
    public void getViewData(Object viewData) {
        CircleBean circleBean = (CircleBean) viewData;
        List<CircleBean.ResultBean> result = circleBean.getResult();
        circleAdapter = new CircleAdapter(getContext());
        mXRecycler.setAdapter(circleAdapter);
        if (page == 1) {
            circleAdapter.setList(result);
        } else {
            circleAdapter.addList(result);
        }
        page += 2;
        mXRecycler.refreshComplete();
        mXRecycler.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (circlePresenter!=null){
            circlePresenter.Destroy();
            System.gc();
        }
    }
}
