package com.bawei.dian.Api;

import com.bawei.dian.Bean.AddShoppBean;
import com.bawei.dian.Bean.AddressList;
import com.bawei.dian.Bean.InsertAddress;
import com.bawei.dian.Bean.JsonBean;
import com.bawei.dian.Bean.LoginBean;
import com.bawei.dian.Bean.RegistBean;
import com.bawei.dian.Bean.SelectShopBean;
import com.bawei.dian.Bean.ShopDetails;
import com.bawei.dian.Bean.ShoppBean;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.Bean.TongBuCart;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public interface ApiService {
    //首页展示
    @GET("commodityList")
    Call<Show> getShow();
    //首页搜索
    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<JsonBean> getSou(@Query("keyword") String keyword, @Query("count") int count, @Query("page") int page);
    /**
     * 注册
     * @param url
     * @param map
     * @return
     */
    @POST
    @FormUrlEncoded
    Observable<RegistBean> regist(@Url String url, @FieldMap Map<String,String> map);
    /**
     * 登录
     * @param url
     * @param map
     * @return
     */
    @POST
    @FormUrlEncoded
    Observable<LoginBean> login(@Url String url, @FieldMap Map<String,String> map);
    /**
     * 收货地址列表
     * @param userId
     * @param sessionId
     * @return
     */
    @GET(Api.ADDRESS)
    Observable<AddressList> findAddress(@Header("userId") String userId, @Header("sessionId") String sessionId);
    /**
     * 添加收货地址
     * @param userId
     * @param sessionId
     * @param realName
     * @param phone
     * @param address
     * @param zipCode
     * @return
     */
    @POST(Api.INSERT_ADDRESS)
    @FormUrlEncoded
    Observable<InsertAddress> insertAddress(@Header("userId") String userId, @Header("sessionId") String sessionId, @Field("realName") String realName, @Field("phone") String phone, @Field("address") String address, @Field("zipCode") String zipCode);
    /**
     * 商品详情
     * @param commodityId
     * @return
     */
    @GET("v1/findCommodityDetailsById")
    Call<ShopDetails> getdetails(@Query("commodityId")int commodityId);
    /**
     * 查询购物车
     */
    @GET("small/order/verify/v1/findShoppingCart")
    Call<ShoppBean> getSHopp(@Header("userId") String s, @Header("sessionId") String a);
    /**
     * 同步购物车
     */
    @PUT("small/order/verify/v1/syncShoppingCart")
    Call<AddShoppBean> getaddshopp(@Query("data") String s);
    //查询购物车
    @GET(Api.LookCart)
    Observable<SelectShopBean> findCart(@Header("userId") int uid, @Header("sessionId") String sid);
    //购物车页面的添加
    @PUT(Api.AddToCart1)
    Observable<TongBuCart> tbCars(@Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap HashMap<String,String> map);

}
