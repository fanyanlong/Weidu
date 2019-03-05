package com.weidu.weidudianshang.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.leon.lib.settingview.LSettingItem;
import com.weidu.weidudianshang.LoginActivity;
import com.weidu.weidudianshang.R;
import com.weidu.weidudianshang.activity.FootActivity;
import com.weidu.weidudianshang.activity.ShouaddressActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MyFragment extends Fragment {

    private long exitTime = 0;
    Unbinder unbinder;
    @BindView(R.id.h_back)
    ImageView hBack;
    @BindView(R.id.h_head)
    ImageView hHead;
    @BindView(R.id.user_line)
    ImageView userLine;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_val)
    TextView userVal;
    @BindView(R.id.myFoot)
    LSettingItem myFoot;
    @BindView(R.id.myAddress)
    LSettingItem myAddress;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_mine, null);

        unbinder = ButterKnife.bind(this, view);
        //设置背景磨砂效果
        Glide.with(this).load(R.mipmap.myhead3)
                .bitmapTransform(new BlurTransformation(getActivity(), 25), new CenterCrop(getActivity()))
                .into(hBack);
        //设置圆形图像
        Glide.with(this).load(R.mipmap.myhead3)
                .bitmapTransform(new CropCircleTransformation(getActivity()))
                .into(hHead);
        myFoot.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(getActivity(), FootActivity.class));
            }
        });
        hHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });
        myAddress.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                startActivity(new Intent(getActivity(), ShouaddressActivity.class));
            }
        });
        return view;
    }

    private void showPopwindow() {
        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(getActivity(), R.layout.layout_myhead_pop, null);

        Button btnCamera = (Button) popView.findViewById(R.id.btn_camera_pop_camera);
        Button btnAlbum = (Button) popView.findViewById(R.id.btn_camera_pop_album);
        Button btnCancel = (Button) popView.findViewById(R.id.btn_camera_pop_cancel);
        Button cancelloginbtn = (Button) popView.findViewById(R.id.btn_cancel_login);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView, width, height);
        popWindow.setAnimationStyle(R.style.pop_animation);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 100);
                popWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        cancelloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().finish();
//                System.exit(0);
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x30000000));
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果requestCode匹配，切权限申请通过
        if (requestCode == 100) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
