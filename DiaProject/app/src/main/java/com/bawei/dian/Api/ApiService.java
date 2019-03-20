package com.bawei.dian.Api;

import com.bawei.dian.Bean.Show;
import com.bawei.dian.Bean.SouBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
    /**
     * 搜索
     * @param keyword
     * @param page
     * @param count
     * @return
     */
    @GET("small/commodity/v1/findCommodityByKeyword")
    Call<SouBean> getSou(@Query("keyword") String keyword, @Query("count") int count, @Query("page") int page);
}
