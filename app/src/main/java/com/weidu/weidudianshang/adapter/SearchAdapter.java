package com.weidu.weidudianshang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.SearchBean;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    private SearchBean searchBeans;

    public SearchAdapter(Context context, SearchBean searchBeans) {
        this.context = context;
        this.searchBeans = searchBeans;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context, R.layout.layout_search,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(searchBeans.getResult().get(i).getCommodityName());
        viewHolder.imageView.setImageURI(searchBeans.getResult().get(i).getMasterPic());
    }

    @Override
    public int getItemCount() {
        return searchBeans.getResult().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
         SimpleDraweeView imageView;
         TextView  textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.img_view);
            textView=itemView.findViewById(R.id.text_view);
        }
    }
}
