package com.weidu.weidudianshang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.weidu.weidudianshang.adapter.GoodsShowAdapter;
import com.weidu.weidudianshang.api.Api;
import com.weidu.weidudianshang.bean.Goods;
import com.weidu.weidudianshang.bean.GoodsShowBean;
import com.weidu.weidudianshang.bean.ShopCarBean;
import com.weidu.weidudianshang.bean.SyncShopCarBean;
import com.weidu.weidudianshang.goods.presenter.Presenter;
import com.weidu.weidudianshang.goods.view.IView;
import com.weidu.weidudianshang.shopcar.presenter.ShopCarPresenter;
import com.weidu.weidudianshang.syncshopcar.presenter.SyncShopCarPresenter;
import com.weidu.weidudianshang.syncshopcar.view.ISyncShopCarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsActivity extends AppCompatActivity implements IView, ISyncShopCarView {

    @BindView(R.id.recy)
    RecyclerView recy;
    private GoodsShowAdapter goodsShowAdapter;
    private Presenter presenter;
    private SyncShopCarPresenter syncShopCarPresenter;
    private String userId;
    private String sessionId;
    private String show;
    private Goods goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences= getSharedPreferences("login", Context.MODE_PRIVATE);
        //获取凭证
        userId = sharedPreferences.getString("userId","");
        sessionId = sharedPreferences.getString("sessionId","");
        Intent intent = getIntent();
        show = intent.getStringExtra("show");

        presenter = new Presenter(this);
        presenter.getPre(show);
        syncShopCarPresenter = new SyncShopCarPresenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recy.setLayoutManager(linearLayoutManager);

    }
    @Override
    public void getView(Object o) {
        GoodsShowBean goodsShowBean= (GoodsShowBean) o;
        goodsShowAdapter = new GoodsShowAdapter(this,goodsShowBean);
        recy.setAdapter(goodsShowAdapter);
        goodsShowAdapter.setOnClickLisener(new GoodsShowAdapter.setAddOnclick() {
            @Override
            public void setAdd(String id, String count) {
                goods = new Goods(Integer.parseInt(id), 1);
                search();
            }
        });
    }
    public void search(){
        syncShopCarPresenter.getSearchModelData(userId,sessionId);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.Destroy();
            presenter=null;
            System.gc();
        }
    }
    @Override
    public void getSyncViewData(Object viewData) {
        SyncShopCarBean syncShopCarBean = (SyncShopCarBean) viewData;
        if (syncShopCarBean.getMessage().equals("同步成功")){
            Toast.makeText(GoodsActivity.this,"同步成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getSearchViewData(Object viewData) {
        ShopCarBean bean= (ShopCarBean) viewData;
        if (bean.getStatus().equals("0000")){
            List<Goods> list=new ArrayList<>();
            List<ShopCarBean.ResultBean> result = bean.getResult();
            for(ShopCarBean.ResultBean results:result){
                list.add(new Goods(results.getCommodityId(),results.getCount()));
            }
            addShopping(list);
        }
    }

    public void addShopping(List<Goods> list){
        String string="[";
//        if (list.size()==0){
//            list.add(new Goods(Integer.valueOf(show),1));
//        }else {
            for(int i=0;i<list.size();i++) {
                if (Integer.valueOf(show) == list.get(i).getCommodityId()) {
                    int count=list.get(i).getCount();
                    count++;
                    list.get(i).setCount(count);
                    break;
                }
                else if(i==list.size()-1){
                    list.add(new Goods(Integer.valueOf(show),1));
                    break;
                }
            }
       // }
        for (Goods goods:list){
            string+="{\"commodityId\":"+goods.getCommodityId()+",\"count\":"+goods.getCount()+"},";
        }
        String substring = string.substring(0, string.length() - 1);
        substring+="]";
        syncShopCarPresenter.getSyncModelData(userId,sessionId,substring);
    }
}
