package com.weidu.weidudianshang.home.presenter;

public interface IHomePresenter {
    void getModelData();
    //一级类目
    void getTopModelData();
    //二级类目
    void getDownModelData(int firstCategoryId);
}
