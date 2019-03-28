package com.bawei.dian.presenter.detail;

import com.bawei.dian.Bean.ShopDetails;
import com.bawei.dian.Bean.ShoppBean;
import com.bawei.dian.base.BasePresenter;
import com.bawei.dian.model.detail.DetailsModel;
import com.bawei.dian.view.DetailsView;

import java.util.List;
import java.util.Map;

/**
 * Time:2019/3/27
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class DetailsPresenter extends BasePresenter {

    private final DetailsModel detailsModel;
    private final DetailsView detailsView;

    @Override
    public void date(Map map) {

    }

    @Override
    public void regdate(Map map) {

    }
    public DetailsPresenter(DetailsView view){
        detailsModel = new DetailsModel();
        detailsView = view;
    }
    public void detailsDate(int id, String userId, String sessionId) {
        detailsModel.details(id,userId,sessionId);
        detailsModel.setHdate(new DetailsModel.Hdate() {
            @Override
            public void getdate(ShopDetails.ResultBean result) {
                detailsView.view(result);
            }

            @Override
            public void chashopp(List<ShoppBean.ResultBean> result) {
                detailsView.vieww(result);
            }

            @Override
            public void addshopp(String s) {
                detailsView.viewww(s);

            }
        });
    }
//查询购物车
    public void chaShopp(String userId, String sessionId) {
        detailsModel.chaShopp(userId,sessionId);
    }

    public void addShopp(String userId, String sessionId, String sss) {
        detailsModel.addShopp(userId,sessionId,sss);
    }
}
