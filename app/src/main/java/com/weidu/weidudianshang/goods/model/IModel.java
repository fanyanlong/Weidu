package com.weidu.weidudianshang.goods.model;

public interface IModel {
    void getModel(String url, String pagecommodityId, SetModel setModel);
    public interface SetModel{
        void getModelData(Object o);
    }
}
