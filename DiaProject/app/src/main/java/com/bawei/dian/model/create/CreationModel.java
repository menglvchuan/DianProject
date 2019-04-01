package com.bawei.dian.model.create;

import com.bawei.dian.Api.ApiService;
import com.bawei.dian.Bean.CreationShopBean;
import com.bawei.dian.Bean.DefaultBean;
import com.bawei.dian.Bean.SelectAddressBean;
import com.bawei.dian.utils.RetrofitManager;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CreationModel implements ICreationModel {
    //创建订单
    @Override
    public void getData(HashMap<String, String> maps,HashMap<String, String> pramrs, final ModelCallBack modelCallBack) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        iApiService.CreatDing(maps,pramrs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CreationShopBean>() {
                    @Override
                    public void accept(CreationShopBean creationShopBean) throws Exception {
                        modelCallBack.loadSuccess(creationShopBean);
                    }
                });

    }

    //设置默认收货地址
    @Override
    public void gteDiZhiData(HashMap<String, String> pramrs, HashMap<String, String> maps, final ModelCallBack modelCallBack) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        iApiService.deFaultDi(pramrs,maps)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<DefaultBean>() {
                        @Override
                        public void accept(DefaultBean defaultBean) throws Exception {
                            modelCallBack.loadSuccess(defaultBean);
                        }
                    });
    }
    //查询收货地址
    @Override
    public void getShouHuo(HashMap<String, String> maps, final ModelCallBack modelCallBack) {
        ApiService iApiService = RetrofitManager.getInstance().setCreat(ApiService.class);
        iApiService.ShouHuo(maps)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<SelectAddressBean>() {
                        @Override
                        public void accept(SelectAddressBean selectAddressBean) throws Exception {
                                modelCallBack.loadSuccess(selectAddressBean);
                        }
                    });
    }
}
