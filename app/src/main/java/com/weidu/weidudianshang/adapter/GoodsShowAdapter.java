package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.recker.flybanner.FlyBanner;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.GoodsShowBean;


import java.util.ArrayList;
import java.util.List;

public class GoodsShowAdapter extends RecyclerView.Adapter<GoodsShowAdapter.ViewHolders> {
    Context context;
    GoodsShowBean list;
    private setAddOnclick addOnclick;

    public GoodsShowAdapter(Context context, GoodsShowBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.layout_goods_item, null);
        return new ViewHolders(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders viewHolders, int i) {

        String[] split = list.getResult().getPicture().split("\\,");
        ArrayList<String> strings = new ArrayList<>();
        String s = this.list.getResult().getPicture().split("\\,")[0];
        for (int j=0;j<split.length;j++){
            strings.add(split[j]);
        }
        viewHolders.banner_shopshow.setImagesUrl(strings);
        viewHolders.dree_shopshow.setImageURI(s);
        viewHolders.t1_shopshow.setText("产品类型:"+ this.list.getResult().getCategoryName());
        viewHolders.t2_shopshow.setText("产品名称:"+ this.list.getResult().getCommodityName());
        viewHolders.t3_shopshow.setText("产品颜色及尺寸:"+ this.list.getResult().getDescribe());
        viewHolders.t4_shopshow.setText("产品价格:"+"￥"+ this.list.getResult().getPrice());
        viewHolders.name.setText(this.list.getResult().getCommodityName());
        viewHolders.shopcaradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addOnclick.setAdd(list.getResult().getCategoryId(),"1");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolders extends RecyclerView.ViewHolder {

        private final SimpleDraweeView dree_shopshow;
        private final TextView t1_shopshow;
        private final TextView t2_shopshow;
        private final TextView t3_shopshow;
        private final TextView t4_shopshow;
        private final FlyBanner banner_shopshow;
        private final TextView name;
        private final Button shopcaradd;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            dree_shopshow = itemView.findViewById(R.id.dree_shopshow);
            t1_shopshow = itemView.findViewById(R.id.t1_shopshow);
            t2_shopshow = itemView.findViewById(R.id.t2_shopshow);
            t3_shopshow = itemView.findViewById(R.id.t3_shopshow);
            t4_shopshow = itemView.findViewById(R.id.t4_shopshow);
            banner_shopshow = itemView.findViewById(R.id.banner_shopshow);
            name = itemView.findViewById(R.id.shopshow_name);
            shopcaradd = itemView.findViewById(R.id.shopcar_add);
        }
    }

        public void setOnClickLisener(setAddOnclick addOnclick){
            this.addOnclick=addOnclick;
        }
       public interface setAddOnclick{
          void setAdd(String id,String count);
        }
}
