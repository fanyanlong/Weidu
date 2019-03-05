package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.ShopCarBean;

import java.util.List;

public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.ViewHolder> {
    Context context;
    List<ShopCarBean.ResultBean> list;
    private setOnClick onClick;

    public ShopCarAdapter(Context context, List<ShopCarBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ShopCarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_shopcar_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCarAdapter.ViewHolder holder, int position) {
        holder.shopcarName.setText(list.get(position).getCommodityName());
        holder.shopcarPrice.setText("￥:"+list.get(position).getPrice()+"元");
        holder.shopcarSimple.setImageURI(list.get(position).getPic());
        holder.checks.setChecked(list.get(position).isCheck());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ShopCarBean.ResultBean> result) {
        this.list=result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView shopcarName;
        private final TextView shopcarPrice;
        private final SimpleDraweeView shopcarSimple;
         CheckBox checks;
        public ViewHolder(View itemView) {
            super(itemView);
            shopcarName = itemView.findViewById(R.id.mShopCarName);
            shopcarPrice = itemView.findViewById(R.id.mShopCarPrice);
            shopcarSimple = itemView.findViewById(R.id.mShopCarSimple);
            checks=itemView.findViewById(R.id.check);
        }
    }

    public void setCheckAllListenner(setOnClick onClick){
        this.onClick=onClick;
    }
    public interface setOnClick{
        void CallBack(List<ShopCarBean.ResultBean> list);
    }

}
