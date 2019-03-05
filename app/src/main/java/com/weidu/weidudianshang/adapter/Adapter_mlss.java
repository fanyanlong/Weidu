package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.GoodsBeen;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class Adapter_mlss extends RecyclerView.Adapter<Adapter_mlss.Viewholders> {
    Context context;
    List<GoodsBeen.ProductItemBean.ProductItem> list;

    public Adapter_mlss(Context context, List<GoodsBeen.ProductItemBean.ProductItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.mlss_layout, null);
        return new Viewholders(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholders holder, final int position) {
        holder.dree_adapret_rx.setImageURI(list.get(position).masterPic);
        holder.t1_adapret_rx.setText(list.get(position).commodityName);
        holder.t2_adapret_rx.setText("￥"+list.get(position).price);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(list.get(position).commodityId+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholders extends RecyclerView.ViewHolder {

        private final SimpleDraweeView dree_adapret_rx;
        private final TextView t1_adapret_rx;
        private final TextView t2_adapret_rx;

        public Viewholders(@NonNull View itemView) {
            super(itemView);
            dree_adapret_rx = itemView.findViewById(R.id.mlssImage);
            t1_adapret_rx = itemView.findViewById(R.id.mlssText);
            t2_adapret_rx = itemView.findViewById(R.id.mlssText2);
        }
    }
}
