package com.bawei.dian.model.find;


import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.AddressList;
import com.bawei.dian.utils.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FindAddressModel implements IFindAddressModel {

    @Override
    public void getFindAddrData(String userId, String sessionId, final FindModelInterface findModelInterface) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        iApiService.findAddress(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddressList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddressList value) {
                        findModelInterface.findSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        findModelInterface.findFaile(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
