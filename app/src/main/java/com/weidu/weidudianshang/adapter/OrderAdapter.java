package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.OrderBean;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Context context;
    List<OrderBean.OrderListBean.DetailListBean> list;

    public OrderAdapter(Context context, List<OrderBean.OrderListBean.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_allorder_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.orderName.setText(list.get(position).getCommodityName());
        String split = list.get(position).getCommodityPic().split("\\,")[0];
        holder.orderPrice.setText(list.get(position).getCommodityPrice());
        holder.orderSimple.setImageURI(split);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView orderName;
        private final TextView orderPrice;
        private final SimpleDraweeView orderSimple;

        public ViewHolder(View itemView) {
            super(itemView);
            orderName = itemView.findViewById(R.id.mOrderName);
            orderPrice = itemView.findViewById(R.id.mOrderPreci);
            orderSimple = itemView.findViewById(R.id.mOrderSimple);
        }
    }
}
