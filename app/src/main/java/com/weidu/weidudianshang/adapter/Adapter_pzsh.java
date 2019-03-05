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

public class Adapter_pzsh extends RecyclerView.Adapter<Adapter_pzsh.Viewholders> {
    Context context;
    List<GoodsBeen.ProductItemBean.ProductItem> list;

    public Adapter_pzsh(Context context, List<GoodsBeen.ProductItemBean.ProductItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.pzsh_layout, null);
        return new Viewholders(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholders viewholders, final int i) {
        viewholders.dree_adapret_rx.setImageURI(list.get(i).masterPic);
        viewholders.t1_adapret_rx.setText(list.get(i).commodityName);
        viewholders.t2_adapret_rx.setText("￥"+list.get(i).price);
        viewholders.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(list.get(i).commodityId+"");
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
            dree_adapret_rx = itemView.findViewById(R.id.pzshImage);
            t1_adapret_rx = itemView.findViewById(R.id.pzshText1);
            t2_adapret_rx = itemView.findViewById(R.id.pzshText2);
        }
    }
}
