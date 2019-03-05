package com.weidu.weidudianshang.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.weidu.weidudianshang.GoodsActivity;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.SearchActivity;
import com.weidu.weidudianshang.adapter.DownAdapter;
import com.weidu.weidudianshang.adapter.HomeAdapter;
import com.weidu.weidudianshang.adapter.TopAdapter;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.bean.DownBean;
import com.weidu.weidudianshang.bean.GoodsBeen;
import com.weidu.weidudianshang.bean.TopBean;
import com.weidu.weidudianshang.home.presenter.HomePresenter;
import com.weidu.weidudianshang.home.view.IHomeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IHomeView {
    @BindView(R.id.mXRecycler)
    XRecyclerView mXRecycler;
    Unbinder unbinder;
    @BindView(R.id.mGengduo)
    Button mGengduo;
    @BindView(R.id.searchimage)
    Button searchimage;
    private HomeAdapter homeAdapter;
    private PopupWindow popupWindow;
    private HomePresenter homePresenter;

    private RecyclerView mfirst;
    private RecyclerView mTwoRecyc;
    private View contentView;
    private DownAdapter downAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mXRecycler.setLayoutManager(layoutManager);
        mXRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mXRecycler.loadMoreComplete();
        mXRecycler.refreshComplete();
        mXRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mXRecycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXRecycler.loadMoreComplete();
            }
        });
        homePresenter = new HomePresenter(this);
        homePresenter.getModelData();
        homePresenter.getTopModelData();
        homePresenter.getDownModelData(1001002);
        searchimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("names","");
                startActivity(intent);
            }
        });
        popshow();
        return view;
    }

    private void popshow() {
        contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popwin, null );
        mGengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                ColorDrawable dw = new ColorDrawable(getActivity().getResources().getColor(R.color.colorWhite));
                popupWindow.setBackgroundDrawable(dw);
                popupWindow.showAsDropDown(v,0,25);
                mTwoRecyc.setVisibility(View.GONE);
            }
        });
        mfirst = contentView.findViewById(R.id.mFirstRecyc);
        mTwoRecyc=contentView.findViewById(R.id.mTwoRecyc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mfirst.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mTwoRecyc.setLayoutManager(linearLayoutManager1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //商品详情eventerbus传送id
    @Subscribe
    public void getMsgs1(String mass) {
        Intent intent = new Intent(getContext(), GoodsActivity.class);
        intent.putExtra("show", mass);
        startActivity(intent);
    }
    @Override
    public void getViewData(Object o) {
        GoodsBeen goodsBeen = (GoodsBeen) o;
        List<GoodsBeen.ProductItemBean> list = new ArrayList<>();
        list.add(goodsBeen.result.mlss);
        list.add(goodsBeen.result.rxxp);
        list.add(goodsBeen.result.pzsh);
        //多条目适配器
        homeAdapter = new HomeAdapter(getContext(), list);
        mXRecycler.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void getTopViewData(Object o) {
        TopBean topBean= (TopBean) o;
        final List<TopBean.ResultBean> result = topBean.getResult();
        TopAdapter topAdapter = new TopAdapter(getActivity(),result);
        mfirst.setAdapter(topAdapter);
        topAdapter.SetOnClickLisener(new TopAdapter.SetClick() {
            @Override
            public void SetOnClick(int i) {
                String id = result.get(i).getId();
                homePresenter.getDownModelData(Integer.parseInt(id));
                downAdapter.notifyDataSetChanged();
                mTwoRecyc.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void getDownViewData(Object o) {
        DownBean downBean = (DownBean) o;
        List<DownBean.ResultBean> result = downBean.getResult();
        downAdapter = new DownAdapter(getActivity(), result);
        mTwoRecyc.setAdapter(downAdapter);
        downAdapter.setDownOnClickListener(new DownAdapter.setDownClick() {
            @Override
            public void getName(String name) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("names",name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (homePresenter!=null){
            homePresenter.Destroy();
            System.gc();
        }
    }
}
