package com.bawei.dian.model.deng;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.LoginBean;
import com.bawei.dian.utils.OkHttpUtils;
import com.bawei.dian.utils.RetrofitManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class LoginModel implements ILoginModel{

    @Override
    public void login(String url, Map<String, String> map, final ILoginCallBack iLoginCallBack) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        Observable<LoginBean> login1 = iApiService.login(url, map);
        login1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        iLoginCallBack.onStatus(value);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
