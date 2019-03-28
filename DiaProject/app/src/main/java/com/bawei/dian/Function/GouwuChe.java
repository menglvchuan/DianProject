package com.bawei.dian.Function;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Adapter.SelectShopAdapter;
import com.bawei.dian.Bean.SelectShopBean;
import com.bawei.dian.Bean.ShopSelectListBean;
import com.bawei.dian.R;
import com.bawei.dian.base.BaseFragment;
import com.bawei.dian.presenter.car.ShopCartPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time:2019/3/18
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class GouWuChe extends BaseFragment {
    @BindView(R.id.shop_recy)
    RecyclerView shopRecy;
    @BindView(R.id.shop_box_all)
    CheckBox shopBoxAll;
    @BindView(R.id.shop_text_allprice)
    TextView shopTextAllprice;
    @BindView(R.id.shop_text_go)
    TextView shopTextGo;
    private Unbinder unbinder;
    private ShopCartPresenter mIpresenterImpl;
    private int d;
    private SelectShopAdapter selectShopAdapter;
    List<SelectShopBean.ResultBean> shop_list = new ArrayList<>();
    private String sessionId;
    private long exitTime = 0;

    @Override
    public void initData() {
        //互绑
        initPresenter();
        //初始化RecyclerView
        initRecy();
        //结算总价格,数量
        getpriceCount();
        shopBoxAll.setChecked(false);
        //点击复选框进行判断
        onClickCheckAll();
        //点击跳转进行创建订单
        onClickCreation();
        
    }
    //点击跳转支付订单
    private void onClickCreation() {

    }

    private void onClickCheckAll() {
        shopBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断全选时商品的状态
                checkAll(shopBoxAll.isChecked());
                selectShopAdapter.notifyDataSetChanged();
            }
        });
    }
    private void checkAll(boolean checked) {
        double totalPrice = 0;
        int num = 0;

        for (int i = 0; i < shop_list.size(); i++) {
            //遍历商品，改变状态
            shop_list.get(i).setIscheck(checked);
            totalPrice = totalPrice + (shop_list.get(i).getPrice() * shop_list.get(i).getCount());
            num = num + shop_list.get(i).getCount();
        }

        if (checked) {
            shopTextAllprice.setText("" + totalPrice);
            shopTextGo.setText("去结算(" + num + ")");
        } else {
            shopTextAllprice.setText("0");
            shopTextGo.setText("去结算");
        }
    }

    private void getpriceCount() {
        selectShopAdapter.setOnClick(new SelectShopAdapter.ShopClick() {
            @Override
            public void shopPrice(List<SelectShopBean.ResultBean> list) {
                //在这里重新遍历已经改变状态后的数据
                //这里不能break跳出，因为还有需要计算后面点击商品的价格和数量，所以必须跑完整个循环
                double totalPrice = 0;
                //勾选商品的数量，不是该商品购买的数量
                int num = 0;
                //所有商品总数，和上面的数量做比对，如果两者相等，则说明全选
                int totalNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    totalNum = totalNum + list.get(i).getCount();
                    if (list.get(i).isIscheck()) {
                        totalPrice = totalPrice + list.get(i).getPrice() * list.get(i).getCount();
                        num = num + list.get(i).getCount();
                    }

                }
                if (num < totalNum) {
                    shopBoxAll.setChecked(false);
                } else {
                    shopBoxAll.setChecked(true);
                }
                shopTextAllprice.setText("" + totalPrice);
                shopTextGo.setText("去结算(" + num + ")");
                if (list.size() == 0) {
                    shopBoxAll.setChecked(false);
                }
            }
        });
        selectShopAdapter.setRemove(new SelectShopAdapter.RemoveCallBack() {
            @Override
            public void removeposition(List<SelectShopBean.ResultBean> list, int position) {
                //在这里重新遍历已经改变状态后的数据
                //这里不能break跳出，因为还有需要计算后面点击商品的价格和数量，所以必须跑完整个循环
                double totalPrice = 0;
                //勾选商品的数量，不是该商品购买的数量
                int num = 0;
                //所有商品总数，和上面的数量做比对，如果两者相等，则说明全选
                int totalNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    totalNum = totalNum + list.get(i).getCount();
                    if (list.get(i).isIscheck()) {
                        totalPrice = totalPrice + list.get(i).getPrice() * list.get(i).getCount();
                        num = num + list.get(i).getCount();
                    }

                }
                if (num < totalNum) {
                    shopBoxAll.setChecked(false);
                } else {
                    shopBoxAll.setChecked(true);
                }
                shop_list.remove(position);
                shopTextAllprice.setText("" + totalPrice);
                shopTextGo.setText("去结算(" + num + ")");
                //添加购物车的集合
                List<ShopSelectListBean> addlist = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    int commodityId = list.get(i).getCommodityId();
                    int count = list.get(i).getCount();
                    addlist.add(new ShopSelectListBean(Integer.valueOf(commodityId), count));
                }
                String data = "[";
                for (ShopSelectListBean bean : addlist) {
                    data += "{\"commodityId\":" + bean.getCommodityId() + ",\"count\":" + bean.getCount() + "},";
                }
                String substring = data.substring(0, data.length() - 1);
                substring += "]";
                HashMap<String, String> params = new HashMap<>();
                params.put("data", substring);
                mIpresenterImpl.getQueryData(d, sessionId, params);
                if (list.size() == 0) {
                    shopBoxAll.setChecked(false);
                }
            }
        });
    }

    private void initRecy() {
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shopRecy.setLayoutManager(linearLayoutManager);
        //设置适配器
        selectShopAdapter = new SelectShopAdapter(getActivity());
        shopRecy.setAdapter(selectShopAdapter);
    }
    private void initPresenter() {
        mIpresenterImpl = new ShopCartPresenter(this);
        SharedPreferences preferences = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        String userId = preferences.getString("userId", "");
        sessionId = preferences.getString("sessionId", "");
        d = Integer.parseInt(userId);

        //查询购物车
        mIpresenterImpl.getPresenterData(d, sessionId);
    }
    public void getViewsData(Object o) {
        if (o instanceof SelectShopBean) {
            SelectShopBean selectShopBean = (SelectShopBean) o;
            // Log.i("asda", "getViewData: " + selectShopBean.getMessage());
            selectShopAdapter.setList(selectShopBean.getResult());
            shop_list = selectShopBean.getResult();
            shopBoxAll.setChecked(false);
        }
    }
    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public int ResIdLayout() {
        return R.layout.gouwuche;
    }

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
        mIpresenterImpl.getPresenterData(d,sessionId);
    }

    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

                    //双击退出
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        Toast.makeText(getActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();
                    } else {
                        getActivity().finish();
                        System.exit(0);
                    }
                    return true;
                }

                return false;
            }
        });
    }
}
