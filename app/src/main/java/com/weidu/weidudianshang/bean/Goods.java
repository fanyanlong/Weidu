package com.weidu.weidudianshang.bean;

import java.io.Serializable;

public class Goods implements Serializable {
    private int commodityId;
    private int count;

    public Goods(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
