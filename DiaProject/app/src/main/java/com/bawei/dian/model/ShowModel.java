package com.bawei.dian.model;

import android.util.Log;

import com.bawei.dian.Api.Api;
import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.BannaBean;
import com.bawei.dian.Bean.OneListinfo;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.Bean.ThreeListinfo;
import com.bawei.dian.Bean.TwoListinfo;
import com.bawei.dian.utils.RetrofitUtils;
import com.bawei.dian.utils.UtilDate;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
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
    public void getOneList(String oneListUrl, CompositeDisposable disposable) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(oneListUrl, ApiService.class);
        Flowable<OneListinfo> oneList = apiService.getOneList();
        DisposableSubscriber<OneListinfo> disposableSubscriber = oneList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<OneListinfo>() {
                    @Override
                    public void onNext(OneListinfo oneListinfo) {
                        List<OneListinfo.ResultBean> oneList = oneListinfo.getResult();
                        if(onShowListener!=null){
                            onShowListener.onOne(oneList);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTwoList(String twoListUrl, String id, CompositeDisposable disposable) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(twoListUrl, ApiService.class);
        final Flowable<TwoListinfo> twoList = apiService.getTwoList(id);
        twoList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<TwoListinfo>() {
                    @Override
                    public void onNext(TwoListinfo twoListinfo) {
                        String message = twoListinfo.getMessage();
                        List<TwoListinfo.ResultBean> result = twoListinfo.getResult();
                        if(onShowListener!=null){
                            onShowListener.onTwo(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getThreeList(String threeListUrl, String mid, int i, int i1, CompositeDisposable disposable) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(threeListUrl, ApiService.class);
        final Flowable<ThreeListinfo> threeList = apiService.getThreeList(mid, i, i1);
        threeList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ThreeListinfo>() {
                    @Override
                    public void onNext(ThreeListinfo threeListinfo) {
                        List<ThreeListinfo.ResultBean> threeBean = threeListinfo.getResult();
                        if(onThreeListener!=null){
                            Log.i("three",threeListinfo.getResult().size()+"---------");
                            onThreeListener.onThree(threeBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void banner() {
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.GOODS_SOU, ApiService.class);
        Call<BannaBean> getban = getserviser.getban();
        getban.enqueue(new Callback<BannaBean>() {
            @Override
            public void onResponse(Call<BannaBean> call, Response<BannaBean> response) {
                BannaBean body = response.body();
                List<BannaBean.ResultBean> result = body.getResult();
                if(onShowListener!=null){
                    onShowListener.onBanner(result);
                }
            }

            @Override
            public void onFailure(Call<BannaBean> call, Throwable t) {

            }
        });
    }

    /**
     * 定义一个接口
     */
    public interface OnThreeListener{
        void onThree(List<ThreeListinfo.ResultBean> threeBean);

    }
    public OnThreeListener onThreeListener;

    public void setOnThreeListener(OnThreeListener onThreeListener) {
        this.onThreeListener = onThreeListener;
    }

    public interface OnShowListener{
        void onResult(Show.ResultBean result);
        void onBanner(List<BannaBean.ResultBean> result);
        void onOne(List<OneListinfo.ResultBean> oneList);
        void onTwo(List<TwoListinfo.ResultBean> result);


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
                Log.i("xx",result.toString());
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
