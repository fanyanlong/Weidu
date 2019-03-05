package com.weidu.weidudianshang.shopcar;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.bean.Goods;
import com.weidu.weidudianshang.bean.ShopCarBean;

import java.util.List;

public class CustomAddView extends RelativeLayout implements View.OnClickListener {
    Context mContext;
    private TextView mEditCar;
    private int num;
    private Context context;
    List<Goods> mlist;
    private int position;

    public CustomAddView(Context context) {
        super(context);
        init(context);
    }

    public CustomAddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }



    public CustomAddView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = View.inflate(context, R.layout.layout_shopcar_count, null);
        ImageView addIamge = (ImageView) view.findViewById(R.id.add_car);
        ImageView jianIamge = (ImageView) view.findViewById(R.id.jian_car);
        mEditCar = view.findViewById(R.id.shop_cartext);
        addIamge.setOnClickListener(this);
        jianIamge.setOnClickListener(this);
        addView(view);

        mEditCar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s!=null){
                    num = Integer.parseInt(s.toString());
                }

                //TODO:改变数量
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    //获取数量
    public void setData(Context context, List<Goods> list, int i) {
            this.context = context;
            this.mlist = list;
            position = i;
        num=list.get(i).getCount();

        mEditCar.setText(this.num + "");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_car:
                //改变数量，设置数量，改变对象内容，回调，局部刷新
                num++;

                mEditCar.setText(num + "");
              /*  mList.get(position).setNum(num);
                mCallBackListener.callBack();*/
//                mProductsAdapter.notifyItemChanged(position);
                break;
            case R.id.jian_car:
                if (num > 1) {
                    num--;
                } else {
                    Toast.makeText(mContext, "商品数量不能小于1", Toast.LENGTH_LONG).show();
                }
                mEditCar.setText(num + "");
              /*  mList.get(position).setNum(num);
                mCallBackListener.callBack();*/
//                mProductsAdapter.notifyItemChanged(position);
                break;
            default:
                break;
        }
    }
}
