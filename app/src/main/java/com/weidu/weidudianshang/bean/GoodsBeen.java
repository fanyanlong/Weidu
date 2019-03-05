package com.weidu.weidudianshang.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsBeen implements Serializable {

    public Product result;
    public String message;
    public String status;
    public static class Product{
        public ProductItemBean rxxp;
        public ProductItemBean pzsh;
        public ProductItemBean mlss;
    }

    public class ProductItemBean {

        public String id;
        public String name;
        public List<ProductItem>  commodityList;

        public  class  ProductItem{
            public String commodityId;
            public String commodityName;
            public String masterPic;
            public String price;
            public String saleNum;
        }


    }

}
