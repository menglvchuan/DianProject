package com.bawei.dian.model.car;


import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.SelectShopBean;
import com.bawei.dian.Bean.TongBuCart;
import com.bawei.dian.utils.RetrofitManager;

import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShopCartModel implements IShopCartModel {
    @Override
    public void getData(int uid,String sid, final ModelCallBack modelCallBack) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        iApiService.findCart(uid,sid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SelectShopBean>() {
                               @Override
                               public void accept(SelectShopBean shopCart) throws Exception {
                                   modelCallBack.loadSuccess(shopCart);
                               }
                           });
    }

    @Override
    public void queryCart(int uid,String sid,HashMap<String, String> pramrs, ModelCallBack modelCallBack) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        iApiService.tbCars(uid,sid,pramrs)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<TongBuCart>() {
                        @Override
                        public void accept(TongBuCart tongBuCart) throws Exception {

                        }
                    });
    }
}
