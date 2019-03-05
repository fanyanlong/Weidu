package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.GoodsBeen;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private final int BANNER_VIEW=0;
    private final int Mlss_VIEW=3;
    private final int Pzsh_VIEW=2;
    private final int Rxxp_VIEW=1;
    Context context;
    List<GoodsBeen.ProductItemBean> homelist;

    public HomeAdapter(Context context, List<GoodsBeen.ProductItemBean> homelist) {
        this.context = context;
        this.homelist = homelist;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER_VIEW:
                return 0;
            case Rxxp_VIEW:
                return 1;
            case Pzsh_VIEW:
                return 3;
            case Mlss_VIEW:
                return 2;
        }
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==Rxxp_VIEW){
            view = View.inflate(context,R.layout.layout_rxxp,null);
            return new ViewHolder2(view);
        }else if(viewType==Pzsh_VIEW){
            view = View.inflate(context,R.layout.layout_pzsh,null);
            return new ViewHolder3(view);
        }else if(viewType==Mlss_VIEW){
            view = View.inflate(context,R.layout.layout_mlss,null);
            return new ViewHolder4(view);
        }else{
            view = View.inflate(context,R.layout.layout_banner,null);
            return new ViewHolder1(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            final ArrayList<String> list = new ArrayList<>();
            list.add("http://172.17.8.100/images/small/banner/cj.png");
            list.add("http://172.17.8.100/images/small/banner/hzp.png");
            list.add("http://172.17.8.100/images/small/banner/lyq.png");
            list.add("http://172.17.8.100/images/small/banner/px.png");
            list.add("http://172.17.8.100/images/small/banner/wy.png");

            ((ViewHolder1) holder).mxbanner.setData(list,null);
            ((ViewHolder1) holder).mxbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(list.get(position)).into((ImageView) view);
                }
            });

        }
        if (holder instanceof ViewHolder2){
            List<GoodsBeen.ProductItemBean.ProductItem> commodityList = homelist.get(1).commodityList;
            GridLayoutManager layoutManager=new GridLayoutManager(context,3);
            ((ViewHolder2) holder).recy_rx.setLayoutManager(layoutManager);
            ((ViewHolder2) holder).recy_rx.setAdapter(new Adapter_rxxp(context,commodityList));
        }
        if (holder instanceof ViewHolder3){

            List<GoodsBeen.ProductItemBean.ProductItem> commodityList = homelist.get(2).commodityList;
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            ((ViewHolder3) holder).recy_pz.setLayoutManager(gridLayoutManager);
            ((ViewHolder3) holder).recy_pz.setAdapter(new Adapter_pzsh(context,commodityList));
        }
        if (holder instanceof ViewHolder4){
            List<GoodsBeen.ProductItemBean.ProductItem> commodityList = homelist.get(0).commodityList;
//
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            ((ViewHolder4) holder).recy_ml.setLayoutManager(layoutManager);
            ((ViewHolder4) holder).recy_ml.setAdapter(new Adapter_mlss(context,commodityList));
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private XBanner mxbanner;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            mxbanner = itemView.findViewById(R.id.mXBanner);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        RecyclerView recy_rx;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            recy_rx = itemView.findViewById(R.id.recy_rx);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {

        RecyclerView recy_pz;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            recy_pz = itemView.findViewById(R.id.recy_pz);
        }
    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {

        RecyclerView recy_ml;

        public ViewHolder4(@NonNull View itemView) {
            super(itemView);
            recy_ml = itemView.findViewById(R.id.recy_ml);
        }
    }
    SetClick setClick;
    public void setOnClickLiener(SetClick setClick){
        this.setClick=setClick;
    }
    public interface SetClick{
        void setOnclick(int j);
    }
}
