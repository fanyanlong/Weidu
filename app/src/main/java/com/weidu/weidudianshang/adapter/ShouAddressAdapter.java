package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.ShouAddressBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShouAddressAdapter extends RecyclerView.Adapter<ShouAddressAdapter.ViewHolder> {
    Context context;
    List<ShouAddressBean.ResultBean> list;

    public ShouAddressAdapter(Context context, List<ShouAddressBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShouAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_shouaddress_item,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ShouAddressAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getRealName());
        holder.phone.setText(list.get(position).getPhone());
        holder.address.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView name;
        private  TextView phone;
        private  TextView address;
        public ViewHolder(View itemView) {
            super(itemView);
            phone = itemView.findViewById(R.id.addressPhone);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.addressName);
        }
    }

}
