package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.TopBean;


import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {
    Context context;
    List<TopBean.ResultBean> list;

    public TopAdapter(Context context,List<TopBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.layout_top_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.top.setText(list.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClick.SetOnClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView top;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            top = itemView.findViewById(R.id.mTop);
        }
    }
    SetClick setClick;
    public void SetOnClickLisener(SetClick setClick){
        this.setClick=setClick;
    }

    public interface SetClick{
        void SetOnClick(int i);
    }
}
