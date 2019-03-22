package com.bawei.dian.model.address;

import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.InsertAddress;
import com.bawei.dian.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class AddrModel implements IAddrModel{
    @Override
    public void getAddData(String userId, String sessionId, String realName, String phone, String address, String zipCode, final AddModelInterface addModelInterface) {
        ApiService apiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        apiService.insertAddress(userId, sessionId, realName, phone, address, zipCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InsertAddress>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InsertAddress value) {
                        addModelInterface.loadAddSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addModelInterface.loadAddFailed(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
