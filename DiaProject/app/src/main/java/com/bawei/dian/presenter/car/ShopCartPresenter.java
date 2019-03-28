package com.bawei.dian.presenter.car;


import com.bawei.dian.Function.GouWuChe;
import com.bawei.dian.model.car.IShopCartModel;
import com.bawei.dian.model.car.ShopCartModel;

import java.util.HashMap;

public class ShopCartPresenter implements IShopCartPresenter{
    GouWuChe shopCarFragment;
    private final ShopCartModel shopCartModel;

    public ShopCartPresenter(GouWuChe shopCarFragment) {
        this.shopCarFragment = shopCarFragment;
        shopCartModel = new ShopCartModel();
    }


    @Override
    public void getPresenterData(int uid,String sid) {
        shopCartModel.getData(uid,sid,new IShopCartModel.ModelCallBack() {
            @Override
            public void loadSuccess(Object o) {
                shopCarFragment.getViewsData(o);
            }

            @Override
            public void loadFailed(Throwable throwable) {

            }
        });
    }

    @Override
    public void getQueryData(int uid, String sid, HashMap<String, String> maps) {
        shopCartModel.queryCart(uid, sid, maps, new IShopCartModel.ModelCallBack() {
            @Override
            public void loadSuccess(Object o) {

            }

            @Override
            public void loadFailed(Throwable throwable) {

            }
        });
    }

    public  void  deatch(){
        if(shopCarFragment!=null){
            shopCarFragment=null;
        }
    }
}
