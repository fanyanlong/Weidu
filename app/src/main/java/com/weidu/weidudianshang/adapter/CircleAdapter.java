package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.CircleBean;
import com.zhuang.likeviewlibrary.LikeView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CircleAdapter extends XRecyclerView.Adapter<CircleAdapter.ViewHolder> {
    Context context;
    List<CircleBean.ResultBean> list;

    public CircleAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void setList( List<CircleBean.ResultBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }
    public void addList( List<CircleBean.ResultBean> list) {
        this.list .addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_circle_item,parent,false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull CircleAdapter.ViewHolder holder, final int position) {
        //long类型转换时间为String类型
        long createTime = list.get(position).getCreateTime();
        String time = getTime(createTime, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        holder.creattime.setText(time);

        holder.shareimage.setImageURI(list.get(position).getImage());
        holder.userimage.setImageURI(list.get(position).getHeadPic());
        holder.username.setText(list.get(position).getNickName());
        holder.sharetext.setText(list.get(position).getContent());
        //点赞
        holder.goodimage.setHasLike(list.get(position).isHasLike());
        holder.goodimage.setLikeCount(list.get(position).getGreatNum());
        holder.goodimage.setOnLikeListeners(new LikeView.OnLikeListeners() {
            @Override
            public void like(boolean isCancel) {
                list.get(position).setHasLike(!isCancel);
                if (isCancel) {
                    list.get(position).delLikeCount();
                } else {
                    list.get(position).addLikeCount();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView creattime;
        private final SimpleDraweeView userimage;
        private final TextView username;
        private final SimpleDraweeView shareimage;
        private final LikeView goodimage;
        private final TextView sharetext;

        public ViewHolder(View itemView) {
            super(itemView);
            creattime = itemView.findViewById(R.id.creatTime);
            userimage = itemView.findViewById(R.id.userImage);
            username = itemView.findViewById(R.id.userName);
            shareimage = itemView.findViewById(R.id.shareImage1);
            goodimage = itemView.findViewById(R.id.goodImage);
            sharetext = itemView.findViewById(R.id.shareTextView);
        }
    }
    //转换时间
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {

        return dateFormat.format(new Date(timeInMillis));

    }
}
