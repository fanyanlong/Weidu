package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.MyFootBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyFootAdapter extends RecyclerView.Adapter<MyFootAdapter.ViewHolder> {
    Context context;
    List<MyFootBean.ResultBean> list;

    public MyFootAdapter(Context context, List<MyFootBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyFootAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_myfoot_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFootAdapter.ViewHolder holder, int position) {
        holder.footSimple.setImageURI(list.get(position).getMasterPic());
        holder.footlook.setText("已浏览"+list.get(position).getBrowseNum()+"次");
        holder.footname.setText(list.get(position).getCommodityName());
        holder.footprice.setText("￥:"+list.get(position).getPrice()+"元");
        long createTime = list.get(position).getBrowseTime();
        String time = getTime(createTime, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        holder.foottime.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView footlook;
        private final SimpleDraweeView footSimple;
        private final TextView footname;
        private final TextView footprice;
        private final TextView foottime;

        public ViewHolder(View itemView) {
            super(itemView);
            footSimple = itemView.findViewById(R.id.mfootSimple);
            footlook = itemView.findViewById(R.id.mfootLook);
            footname = itemView.findViewById(R.id.mfootName);
            footprice = itemView.findViewById(R.id.mfootPrice);
            foottime = itemView.findViewById(R.id.mfootTime);
        }
    }
    //转换时间
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {

        return dateFormat.format(new Date(timeInMillis));

    }
}
