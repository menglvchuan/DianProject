package com.bawei.dian.view;

import com.bawei.dian.Bean.ShopDetails;
import com.bawei.dian.Bean.ShoppBean;

import java.util.List;

/**
 * Time:2019/3/27
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public interface DetailsView {
    void view(ShopDetails.ResultBean result);

    void vieww(List<ShoppBean.ResultBean> results);

    void viewww(String s);
}
