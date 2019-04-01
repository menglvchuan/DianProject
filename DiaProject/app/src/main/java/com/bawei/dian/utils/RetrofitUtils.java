package com.bawei.dian.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private RetrofitUtils(){

    }
    public static RetrofitUtils getInstance(){
        if(retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }
    private static Retrofit retrofit;
    public static synchronized Retrofit getRetrofit(String ShowUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(ShowUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return RetrofitUtils.retrofit;
    }
    public <T> T getApiService(String ShowUrl,Class<T> service){
        Retrofit retrofit = getRetrofit(ShowUrl);
        T t = retrofit.create(service);
        return t;
    }
}
