package com.bawei.dian.model;

import com.bawei.dian.Api.Api;
import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.utils.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class ShowModel {
    /**
     * 定义一个接口
     */
    public interface OnShowListener{
        void onResult(Show.ResultBean result);
    }
    public OnShowListener onShowListener;

    public void setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }

    public void show() {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(Api.show_url, ApiService.class);
        apiService.getShow().enqueue(new Callback<Show>() {
            @Override
            public void onResponse(Call<Show> call, Response<Show> response) {
                Show body = response.body();
                Show.ResultBean result = body.getResult();
                if(onShowListener!=null){
                    onShowListener.onResult(result);
                }
            }

            @Override
            public void onFailure(Call<Show> call, Throwable t) {

            }
        });
    }
}
