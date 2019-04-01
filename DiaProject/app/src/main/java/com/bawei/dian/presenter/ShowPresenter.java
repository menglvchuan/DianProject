package com.bawei.dian.presenter;

import com.bawei.dian.Bean.BannaBean;
import com.bawei.dian.Bean.OneListinfo;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.Bean.ThreeListinfo;
import com.bawei.dian.Bean.TwoListinfo;
import com.bawei.dian.activity.ListActivity;
import com.bawei.dian.model.ShowModel;
import com.bawei.dian.view.ShowView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    public ShowPresenter(ShowView view){
        showModel = new ShowModel();
        showView = view;
    }
    public void show() {
        showModel.show();
        showModel.setOnShowListener(new ShowModel.OnShowListener() {
            @Override
            public void onResult(Show.ResultBean result) {
                showView.getViewData(result);
            }

            @Override
            public void onBanner(List<BannaBean.ResultBean> result) {
                showView.getBanner(result);
            }

            @Override
            public void onOne(List<OneListinfo.ResultBean> oneList) {
                showView.getOneData(oneList);
            }

            @Override
            public void onTwo(List<TwoListinfo.ResultBean> result) {
                showView.getTwoData(result);
            }

            @Override
            public void onThree(List<ThreeListinfo.ResultBean> threeBean) {
                showView.getThreeData(threeBean);
            }
        });
    }

    public void getOneList(String oneListUrl, CompositeDisposable disposable) {
        showModel.getOneList(oneListUrl,disposable);
    }

    public void getTwoList(String twoListUrl, String id, CompositeDisposable disposable) {
        showModel.getTwoList(twoListUrl,id,disposable);
    }

    public void getThreeList(String threeListUrl, String mid, int i, int i1, CompositeDisposable disposable) {
        showModel.getThreeList(threeListUrl,mid,i,i1,disposable);
    }


    public void ban() {
        showModel.banner();
    }
}
