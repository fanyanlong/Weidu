package com.weidu.weidudianshang.api;

import com.weidu.weidudianshang.bean.AddaddressBean;
import com.weidu.weidudianshang.bean.CircleBean;
import com.weidu.weidudianshang.bean.DownBean;
import com.weidu.weidudianshang.bean.GoodsBeen;
import com.weidu.weidudianshang.bean.GoodsShowBean;
import com.weidu.weidudianshang.bean.LoginBean;
import com.weidu.weidudianshang.bean.MyFootBean;
import com.weidu.weidudianshang.bean.OrderBean;
import com.weidu.weidudianshang.bean.RegisterBean;
import com.weidu.weidudianshang.bean.SearchBean;
import com.weidu.weidudianshang.bean.ShopCarBean;
import com.weidu.weidudianshang.bean.ShouAddressBean;
import com.weidu.weidudianshang.bean.SyncShopCarBean;
import com.weidu.weidudianshang.bean.TopBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    //商品
    @GET
    Observable<GoodsBeen> getGoods(@Url String url);
    //圈子
    @GET
    Observable<CircleBean> getCircle(@Url String url, @Query("page") int page,@Query("count") int count);
    //登录
    @POST
    Observable<LoginBean> getLogin(@Url String url, @QueryMap HashMap<String,String> hashMap);
    //搜索
    @GET
    Observable<SearchBean> getSearch(@Url String url, @Query("keyword") String keyword,@Query("page") String page,@Query("count") String count);
    //详情
    @GET
    Observable<GoodsShowBean> shopshows(@Url String url, @Query("commodityId") String commodityId);
    //注册
    @POST
    Observable<RegisterBean> getRegister(@Url String url, @QueryMap HashMap<String,String> hashMap);
    //查找购物车
    @GET
    Observable<ShopCarBean> getShopCar(@Url String url, @Header("userId") String userid,@Header("sessionId") String sessionid);
    @PUT
    Observable<SyncShopCarBean> getSyncshopcar(@Url String url,@Header("userId") String userid,@Header("sessionId") String sessionid,@Query("data") String data);
    //一级类目
    @GET
    Observable<TopBean> getTop(@Url String url);
    //二级类目
    @GET
    Observable<DownBean> getDown(@Url String url, @Query("firstCategoryId") int firstCategoryId);
    //根据订单状态查询订单
    @GET
    Observable<OrderBean> getOrder(@Url String url, @Header("userId") String userid,@Header("sessionId") String sessionid,@Query("status") int status,@Query("page") int page,@Query("count") int count);
    //我的足迹
    @GET
    Observable<MyFootBean> getMyfoot(@Url String url, @Header("userId") String userid, @Header("sessionId") String sessionid,@Query("page") int page, @Query("count") int count);
    //我的收获地址
    @GET
    Observable<ShouAddressBean> getShouAddress(@Url String url, @Header("userId") String userid, @Header("sessionId") String sessionid);
    //新增地址
    @POST
    Observable<AddaddressBean> getAddAddress(@Url String url, @Header("userId") String userid, @Header("sessionId") String sessionid, @QueryMap Map<String,String> map);
}
