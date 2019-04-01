package com.bawei.dian.presenter.create;

import com.bawei.dian.activity.CreationActivity;
import com.bawei.dian.model.create.CreationModel;
import com.bawei.dian.model.create.ICreationModel;

import java.util.HashMap;

public class CreationPresenter implements ICreationPresenter {
    CreationActivity shopCarFragment;
    private final CreationModel creationModel;

    public CreationPresenter(CreationActivity shopCarFragment) {
        this.shopCarFragment = shopCarFragment;
        creationModel = new CreationModel();
    }


    @Override
    public void getPresenterData(HashMap<String, String> maps,HashMap<String, String> pramrs) {
        creationModel.getData(maps,pramrs,new ICreationModel.ModelCallBack() {
            @Override
            public void loadSuccess(Object o) {
                shopCarFragment.getViewData(o);
            }

            @Override
            public void loadFailed(Throwable throwable) {

            }
        });
    }

    @Override
    public void getDiZhiData(HashMap<String, String> maps, HashMap<String, String> pramrs) {
        creationModel.gteDiZhiData(maps, pramrs, new ICreationModel.ModelCallBack() {
            @Override
            public void loadSuccess(Object o) {
                shopCarFragment.getDizhiData(o);
            }

            @Override
            public void loadFailed(Throwable throwable) {

            }
        });
    }

    @Override
    public void getShouHuo(HashMap<String, String> maps) {
        creationModel.getShouHuo(maps, new ICreationModel.ModelCallBack() {
            @Override
            public void loadSuccess(Object o) {
                shopCarFragment.getShopData(o);
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
