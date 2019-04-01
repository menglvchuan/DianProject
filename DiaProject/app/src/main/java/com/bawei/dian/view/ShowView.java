package com.bawei.dian.view;

import com.bawei.dian.Bean.BannaBean;
import com.bawei.dian.Bean.OneListinfo;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.Bean.ThreeListinfo;
import com.bawei.dian.Bean.TwoListinfo;

import java.util.List;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public interface ShowView {
    void getViewData(Show.ResultBean result);

    void getOneData(List<OneListinfo.ResultBean> oneList);

    void getTwoData(List<TwoListinfo.ResultBean> result);

    void getThreeData(List<ThreeListinfo.ResultBean> threeBean);

    void getBanner(List<BannaBean.ResultBean> result);
}
