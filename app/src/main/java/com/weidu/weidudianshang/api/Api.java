package com.weidu.weidudianshang.api;

public class Api {

    public static  String BASE_URL="http://172.17.8.100/";
    //商品展示
    public static String PRODUCT_URL="small/commodity/v1/commodityList";
    //圈子
    public static String CIRCLE_URL="small/circle/v1/findCircleList";
    //登录
    public static String LOGIN_URL="small/user/v1/login";
    //搜索
    public static String SEARCH_URL="small/commodity/v1/findCommodityByKeyword";
    //详情
    public static  String GOODS_URL="small/commodity/v1/findCommodityDetailsById";
    //注册
    public static String REGISTER_URL="small/user/v1/register";
    //查找购物车
    public static String SHOPCAR_URL="small/order/verify/v1/findShoppingCart";
    //同步购物车
    public static String SYNCSHOPCAR_URL="small/order/verify/v1/syncShoppingCart";
    //一级类目
    public static String TOP_URL="small/commodity/v1/findFirstCategory";
    //一级类目
    public static String DOWN_URL="small/commodity/v1/findSecondCategory";
    //根据订单状态查询订单
    public static String ORDER_URL="small/order/verify/v1/findOrderListByStatus";
    //我的足迹
    public static String MYFOOT_URL="small/commodity/verify/v1/browseList";
    //我的收获地址
    public static String SHOUADDRESS_URL="small/user/verify/v1/receiveAddressList";
    //新增收获地址
    public static String ADDADDRESS_URL="small/user/verify/v1/addReceiveAddress";
    //待收货
    public static String PAY_URL="small/order/verify/v1/pay";
    //我的钱包
    public static String WALLET_URL="small/user/verify/v1/findUserWallet";
}
