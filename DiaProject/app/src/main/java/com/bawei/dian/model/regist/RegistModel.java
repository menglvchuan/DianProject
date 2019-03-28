package com.bawei.dian.model.regist;

import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.RegistBean;
import com.bawei.dian.utils.RetrofitManager;
import java.util.Map;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistModel implements IRegistModel{
    @Override
    public void regist(String url, Map<String, String> map, final IRegistCallBack callBack) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        iApiService.regist(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegistBean value) {
                        callBack.onStatus(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailed(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
