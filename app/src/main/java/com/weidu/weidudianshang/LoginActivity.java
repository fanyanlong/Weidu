package com.weidu.weidudianshang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weidu.weidudianshang.bean.LoginBean;
import com.weidu.weidudianshang.httputils.LoadingDialog;
import com.weidu.weidudianshang.login.presenter.LoginPresenter;
import com.weidu.weidudianshang.login.view.ILoginView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.login_user_et)
    EditText loginUserEt;
    @BindView(R.id.login_pwd_et)
    EditText loginPwdEt;
    @BindView(R.id.reg)
    TextView reg;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.mJizhu)
    CheckBox mJizhu;
    private SharedPreferences sp;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);
        final LoginPresenter loginPresenter = new LoginPresenter(this);
        boolean b = sp.getBoolean("记住", false);
        loadingDialog = new LoadingDialog(this);
        if (b) {
            loginUserEt.setText(sp.getString("name",""));
            loginPwdEt.setText(sp.getString("pass",""));
            mJizhu.setChecked(b);
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = loginPwdEt.getText().toString();
                String user = loginUserEt.getText().toString();
                if (!pass.isEmpty() && !user.isEmpty()) {
                    loadingDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("phone", user);
                    map.put("pwd", pass);
                    loginPresenter.getModelData(map);
                } else {
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("name", user);
                edit.putString("pass", pass);
                edit.putBoolean("记住", mJizhu.isChecked());
                edit.commit();
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    @Override
    public void getViewData(Object o) {
        LoginBean loginBean = (LoginBean) o;
        String status = loginBean.getStatus();
        if (status.equals("0000")) {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("userId", loginBean.getResult().getUserId());
            edit.putString("sessionId", loginBean.getResult().getSessionId());
            edit.putBoolean("记住", mJizhu.isChecked());
            edit.commit();
            loadingDialog.dismiss();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @OnTouch(R.id.mHidePass)
    public boolean SignEyeClick(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            loginPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            loginPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        return true;
    }
}
