package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.DownBean;

import java.util.List;

public class DownAdapter extends RecyclerView.Adapter<DownAdapter.ViewHolder> {
    Context context;
    List<DownBean.ResultBean> result;
    private setDownClick clickListener;

    public DownAdapter(Context context, List<DownBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public DownAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.layout_down_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownAdapter.ViewHolder holder, final int position) {
        holder.down.setText(result.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.getName(result.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView down;
        public ViewHolder(View itemView) {
            super(itemView);
            down = itemView.findViewById(R.id.mDown);
        }
    }
    public void setDownOnClickListener(setDownClick clickListener){
        this.clickListener=clickListener;
    }
    public interface setDownClick{
        void getName(String name);
    }
}
