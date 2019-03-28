package com.bawei.dian.model.detail;


import android.util.Log;
import android.widget.Toast;

import com.bawei.dian.Api.Api;
import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.AddShoppBean;
import com.bawei.dian.Bean.ShopDetails;
import com.bawei.dian.Bean.ShoppBean;
import com.bawei.dian.utils.RetrofitUtils;
import com.bawei.dian.utils.UtilDate;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Time:2019/3/27
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class DetailsModel {
    public void details(int id, String userId, String sessionId) {

        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.SHOP_DETAILS, ApiService.class);
        Call<ShopDetails> call= getserviser.getdetails(id);

        call.enqueue(new Callback<ShopDetails>() {
            @Override
            public void onResponse(Call<ShopDetails> call, Response<ShopDetails> response) {
               ShopDetails.ResultBean result = response.body().getResult();
                hdate.getdate(result);
            }

            @Override
            public void onFailure(Call<ShopDetails> call, Throwable t) {

            }
        });
    }
    //查询购物车
    public void chaShopp(String userId, String sessionId) {

        ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.GOODS_SOU, userId, sessionId, ApiService.class);
        Call<ShoppBean> sHopp = apiService.getSHopp(userId, sessionId);
        sHopp.enqueue(new Callback<ShoppBean>() {
            @Override
            public void onResponse(Call<ShoppBean> call, Response<ShoppBean> response) {
                ShoppBean body = response.body();
                List<ShoppBean.ResultBean> result = body.getResult();
                hdate.chashopp(result);
            }

            @Override
            public void onFailure(Call<ShoppBean> call, Throwable t) {

            }
        });
    }

    //进行同步购物车  及添加购物车
    public void addShopp(String userId, String sessionId, String sss) {
        Log.i("aaa",userId+"--"+sessionId+"--"+sss);
        ApiService apiService = UtilDate.getUtilDate().getserviserHand(Api.AddToCart, userId, sessionId, ApiService.class);
        Call<AddShoppBean> shoppBeanCall = apiService.getaddshopp(sss);
        shoppBeanCall.enqueue(new Callback<AddShoppBean>() {
            @Override
            public void onResponse(Call<AddShoppBean> call, Response<AddShoppBean> response) {
                AddShoppBean body = response.body();
                String message = body.getMessage();
                Log.d("mad",message);
                hdate.addshopp(message);
            }

            @Override
            public void onFailure(Call<AddShoppBean> call, Throwable t) {

            }
        });
    }

    //接口回调
    public interface Hdate{
        void getdate(ShopDetails.ResultBean result);
        void chashopp( List<ShoppBean.ResultBean> result);
        void addshopp(String s);
    }
    public Hdate hdate;
    public void setHdate(Hdate hdate) {
        this.hdate = hdate;
    }
}
