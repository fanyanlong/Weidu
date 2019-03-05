package com.weidu.weidudianshang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.addaddress.presenter.AddPresenter;
import com.weidu.weidudianshang.addaddress.view.IAddView;
import com.weidu.weidudianshang.bean.AddaddressBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddaddressActivity extends AppCompatActivity implements IAddView {

    @BindView(R.id.addShoujianren)
    EditText addShoujianren;
    @BindView(R.id.addPhone)
    EditText addPhone;
    @BindView(R.id.addDIQU)
    EditText addDIQU;
    @BindView(R.id.addAddRESS)
    EditText addAddRESS;
    @BindView(R.id.addEMS)
    EditText addEMS;
    @BindView(R.id.adduse)
    Button adduse;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;
    private String shoujianren;
    private String phone;
    private String diqu;
    private String dizhi;
    private String ems;
    private AddPresenter addPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);
        ButterKnife.bind(this);
        addPresenter = new AddPresenter(this);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        sessionId = sp.getString("sessionId", "");

        adduse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoujianren = addShoujianren.getText().toString();
                phone = addPhone.getText().toString();
                diqu = addDIQU.getText().toString();
                dizhi = addAddRESS.getText().toString();
                ems = addEMS.getText().toString();
                Map<String,String> map=new HashMap<>();
                map.put("realName",shoujianren);
                map.put("address",dizhi+diqu);
                map.put("phone",phone);
                map.put("zipCode",ems);
                addPresenter.getAddressModel(userId,sessionId,map);
            }
        });
        addDIQU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicker();
            }
        });

    }

    private void showPicker() {
        CityPicker cityPicker = new CityPicker.Builder(AddaddressActivity.this)
                .textSize(18)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("北京市")
                .city("北京市")
                .district("昌平区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                addDIQU.setText(province+" "+city+" "+district+" ");
            }
        });
    }
    @Override
    public void getAddViewData(Object o) {
        AddaddressBean addaddressBean= (AddaddressBean) o;
        if (addaddressBean.getStatus().equals("0000")){
            startActivity(new Intent(AddaddressActivity.this,ShouaddressActivity.class));
            finish();
        }
    }
}
