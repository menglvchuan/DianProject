package com.bawei.dian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Adapter.CreationRecyAdapter;
import com.bawei.dian.Adapter.PopCreationAdapter;
import com.bawei.dian.Bean.AddressListBean;
import com.bawei.dian.Bean.CreationJsonBean;
import com.bawei.dian.Bean.CreationMsgBean;
import com.bawei.dian.Bean.CreationShopBean;
import com.bawei.dian.Bean.DefaultBean;
import com.bawei.dian.Bean.SelectAddressBean;
import com.bawei.dian.Bean.SelectShopBean;
import com.bawei.dian.R;
import com.bawei.dian.base.BaseActivitiy;
import com.bawei.dian.base.BaseActivity;
import com.bawei.dian.presenter.create.CreationPresenter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Time:2019/3/29
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:创建订单
 */
public class CreationActivity extends BaseActivity {
    private TextView text_name;
    private TextView text_phone;
    private TextView text_address;
    private ImageView image_pop;
    private PopupWindow popupWindow;
    private RecyclerView pop_recy;
    private TextView text_allnum;
    private TextView text_allprice;
    private TextView text_go;
    private RecyclerView creation_recy;
    private CreationPresenter mIpresenterImpl;
    private CreationRecyAdapter creationRecyAdapter;
    private List<SelectShopBean.ResultBean> list;
    private PopCreationAdapter popCreationAdapter;
    private int addressId;
    private HashMap<String, String> maps;
    //温暖的印记 紫罗兰永恒花园 恋与制作人 天龙八部
    //无头骑士异闻录 折原临也  平和岛静雄
    /**
     * 提交订单
     *
     */
    private void setCreation() {
        text_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交的字符串
                List<CreationJsonBean> addlist=new ArrayList<>();
                for (SelectShopBean.ResultBean bean : list){
                    CreationJsonBean creationShopBean=new CreationJsonBean();
                    creationShopBean.setCommodityId(bean.getCommodityId());
                    creationShopBean.setAmount(bean.getCount());
                    addlist.add(creationShopBean);
                }
                String orderInfo = new Gson().toJson(addlist);
                //支付的总金额
                String totalPrice = text_allprice.getText().toString();

                initCreationUrl(orderInfo,totalPrice,addressId);

                finish();
            }
        });
    }

    /**
     *    //创建订单的接口
     * @param orderInfo
     * @param totalPrice
     * @param addressId
     */

    private void initCreationUrl(String orderInfo, String totalPrice, int addressId) {
        SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
        String usserId = preferences.getString("usserId", "");
        String seessionId = preferences.getString("seessionId", "");
        maps = new HashMap<>();
        maps.put("usserId",usserId);
        maps.put("seessionId",seessionId);
        HashMap<String,String> params=new HashMap<>();
        params.put("orderInfo",orderInfo);
        params.put("totalPrice",totalPrice);
        params.put("addressId",String.valueOf(addressId));
        mIpresenterImpl.getPresenterData(maps,params);
    }
    private void initPopRecy() {
      /*  //布局管理器
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(CreationActivity.this);
        linearLayoutManager1.setOrientation(OrientationHelper.VERTICAL);
        pop_recy.setLayoutManager(linearLayoutManager1);
        popCreationAdapter = new PopCreationAdapter(CreationActivity.this);
        pop_recy.setAdapter(popCreationAdapter);
        popCreationAdapter.setChange(new PopCreationAdapter.ClickChange() {
            @Override
            public void onClick(int id) {
                HashMap<String,String> params=new HashMap<>();
                params.put("id",String.valueOf(id));
                mIpresenterImpl.getDiZhiData(maps,params);
            }
        });*/
    }

    /**
     * 弹出popupWindow
     */
    private void initPoPupwindow() {
        /*// 用于PopupWindow的View
        View contentView=LayoutInflater.from(this).inflate(R.layout.creation_bill_pop, null, false);
        pop_recy = contentView.findViewById(R.id.pop_creation_recy);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT, true);
       *//* // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);*//*
        // 设置此参数获得焦点，否则无法点击，即：事件拦截消费
        popupWindow.setFocusable(true);
        // 实例化一个ColorDrawable颜色
        ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.orange));
        // 设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        //点击弹出
        image_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(v,0,26);
                image_pop.setSelected(!image_pop.isSelected());
            }
        });
        popupWindow.dismiss();
*/
    }

    /**
     * 得到购物车页面传过来的值
     */
    private void initIntent() {
        Intent intent = getIntent();
        list = (List<SelectShopBean.ResultBean>) intent.getSerializableExtra("creation_bill");
        creationRecyAdapter.setList(list);
        //算出数量和总价
        setAllNumPrice(list);
    }

    /**
     * 总价 数量
     * @param list
     */
    private void setAllNumPrice(List<SelectShopBean.ResultBean> list) {
        //这里不能break跳出，因为还有需要计算后面点击商品的价格和数量，所以必须跑完整个循环
        double totalPrice=0;
        //商品购买的数量
        int num=0;
        for (int i=0;i<list.size();i++){
            totalPrice=totalPrice+list.get(i).getPrice()*list.get(i).getCount();
            num=num+list.get(i).getCount();
        }
        text_allnum.setText(""+num);
        text_allprice.setText(""+totalPrice);
    }

    /**
     * 你所选择的商品
     */
    private void initRecy() {
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        creation_recy.setLayoutManager(linearLayoutManager);
        creationRecyAdapter = new CreationRecyAdapter(CreationActivity.this);
        creation_recy.setAdapter(creationRecyAdapter);
        creationRecyAdapter.setOnClick(new CreationRecyAdapter.ShopClick() {
            @Override
            public void shopPrice(List<SelectShopBean.ResultBean> list) {
                //这里不能break跳出，因为还有需要计算后面点击商品的价格和数量，所以必须跑完整个循环
                double totalPrice=0;
                //商品购买的数量
                int num=0;
                for (int i=0;i<list.size();i++){
                    totalPrice=totalPrice+list.get(i).getPrice()*list.get(i).getCount();
                    num=num+list.get(i).getCount();
                }
                text_allnum.setText(""+num);
                text_allprice.setText(""+totalPrice);
            }
        });
    }

    /**
     * 找控件
     * @param savedInstanceState
     */
    @Override
    public void initView(Bundle savedInstanceState) {
        text_name = findViewById(R.id.creation_name);
        text_phone = findViewById(R.id.creation_phone);
        text_address = findViewById(R.id.creation_address);
        image_pop = findViewById(R.id.creation_image_pop);
        creation_recy = findViewById(R.id.creation_shop_recy);
        text_allnum = findViewById(R.id.cretion_text_allnum);
        text_allprice = findViewById(R.id.cretion_text_allprice);
        text_go = findViewById(R.id.creation_text_go);
    }

    public Context getActivitys() {
        return CreationActivity.this;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public int initLayout() {
        return R.layout.activity_creation;
    }

    @Override
    public void initData() {
        //互绑
        initIpresenter();
        //初始化recy
        initRecy();
        //获取传递过来的值
        initIntent();
        //初始化initPoPupwindow
        initPoPupwindow();
        //初始化PoPupwindow的RecyclerView
        initPopRecy();
        //提交订单
        setCreation();
    }

    private void initIpresenter() {
        mIpresenterImpl = new CreationPresenter(this);
    }

    /**
     * 地址 默认地址
     * @param object
     */
    public void  getShopData(Object object){
        if (object instanceof SelectAddressBean){
            SelectAddressBean selectAddressBean= (SelectAddressBean) object;
            List<SelectAddressBean.ResultBean> result = selectAddressBean.getResult();
            for (int i=0;i<result.size();i++){
                if (selectAddressBean.getResult().get(i).getWhetherDefault()==1){
                    text_name.setText(result.get(i).getRealName());
                    text_phone.setText(result.get(i).getPhone());
                    AddressListBean addressListBean = new Gson().fromJson(result.get(i).getAddress(), AddressListBean.class);
                    text_address.setText(addressListBean.getCity()+addressListBean.getAddress());
                    addressId=result.get(i).getId();
                }
            }
            Log.i("asadasd", "getViewData: "+result.size());
            popCreationAdapter.setList(result);
        }
    }

    /**
     * 点击提交订单 失败
     * @param object
     */
    public void getViewData(Object object) {
        if (object instanceof CreationShopBean){
            CreationShopBean creationShopBean= (CreationShopBean) object;
            if (creationShopBean.getStatus().equals("0000")){
                EventBus.getDefault().postSticky(new CreationMsgBean("creation","send"));
                Toast.makeText(this, creationShopBean.getMessage(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "创建失败", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void getDizhiData(Object object) {
        /*if (object instanceof DefaultBean){
            DefaultBean defaultBean= (DefaultBean) object;
            if (defaultBean.getStatus().equals("0000")){

            }
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIpresenterImpl.deatch();
    }
}
