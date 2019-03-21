package com.bawei.dian.model.sou;

import android.util.Log;

import com.bawei.dian.Api.Api;
import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.JsonBean;
import com.bawei.dian.utils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Time:2019/3/20
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class SouModel {
    //定义接口
    public interface OnSouListener{
        void onSou(List<JsonBean.ResultBean> beans);
    }
    private OnSouListener onSouListener;

    public void setOnSouListener(OnSouListener onSouListener) {
        this.onSouListener = onSouListener;
    }

    public void sou(String keyword, int count, int page) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.GOODS_SOU, ApiService.class);
        apiService.getSou(keyword, count, page).enqueue(new Callback<JsonBean>() {
            @Override
            public void onResponse(Call<JsonBean> call, Response<JsonBean> response) {
                JsonBean body = response.body();
                List<JsonBean.ResultBean> beans = body.getResult();
                if(onSouListener!=null){
                    onSouListener.onSou(beans);
                }
            }
            @Override
            public void onFailure(Call<JsonBean> call, Throwable t) {

            }
        });
    }
}
