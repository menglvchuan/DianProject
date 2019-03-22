package com.bawei.dian.utils;

import com.bawei.dian.Api.Api;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static volatile RetrofitManager instance;
    private static volatile OkHttpClient okHttpClient;

    /**
     * 静态块
     */
    static {
        getOkHttpClient();
    }

    private Retrofit retrofit;

    private RetrofitManager(){
        initRetrofit();
    }

    public static RetrofitManager getInstance(){
        if (instance == null){
            synchronized (RetrofitManager.class){
                if (instance == null){
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public static OkHttpClient getOkHttpClient(){
        if (okHttpClient == null){
            synchronized(OkHttpClient.class){
                okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .readTimeout(3000,TimeUnit.SECONDS)
                        .connectTimeout(3000,TimeUnit.SECONDS)
                        .build();

            }
        }
        return okHttpClient;
    }


    private void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.GOODS_SOU)
                .client(okHttpClient)
                .build();
    }

    public <T> T setCreat(Class<T> tClass){
        return retrofit.create(tClass);
    }


}
