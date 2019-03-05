package com.weidu.weidudianshang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weidu.weidudianshang.bean.LoginBean;
import com.weidu.weidudianshang.bean.RegisterBean;
import com.weidu.weidudianshang.login.presenter.LoginPresenter;
import com.weidu.weidudianshang.register.presenter.RegisterPresenter;
import com.weidu.weidudianshang.register.view.IRegisterView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    @BindView(R.id.reg_shouji)
    EditText regShouji;
    @BindView(R.id.reg_yanzheng)
    EditText regYanzheng;
    @BindView(R.id.reg_password)
    EditText regPassword;
    @BindView(R.id.reg)
    TextView reg;
    @BindView(R.id.reg_button)
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        final RegisterPresenter registerPresenter = new RegisterPresenter(this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = regPassword.getText().toString();
                String user = regShouji.getText().toString();
                if (!pass.isEmpty() && !user.isEmpty()) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("phone", user);
                    map.put("pwd", pass);
                    registerPresenter.getModelData(map);
                } else {
                    Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void getViewData(Object o) {
        RegisterBean registerBean = (RegisterBean) o;
        String status = registerBean.getStatus();
        if (status.equals("0000")) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(RegisterActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
