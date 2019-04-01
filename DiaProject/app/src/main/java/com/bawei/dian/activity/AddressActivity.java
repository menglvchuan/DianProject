package com.bawei.dian.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Adapter.MyAddressAdapter;
import com.bawei.dian.Bean.AddressList;
import com.bawei.dian.Bean.InsertAddress;
import com.bawei.dian.R;
import com.bawei.dian.presenter.address.AddrPresenter;
import com.bawei.dian.presenter.find.FindAddressPresenter;
import com.bawei.dian.view.IAddrView;
import com.bawei.dian.view.IFindAddressView;
import com.hengyi.wheelpicker.ppw.CityWheelPickerPopupWindow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:添加地址页面
 */
public class AddressActivity extends Activity  implements IFindAddressView {

    @BindView(R.id.text_add)
    TextView textAdd;
    @BindView(R.id.text_finish)
    TextView textFinish;
    @BindView(R.id.relative_add)
    RelativeLayout relativeAdd;
    @BindView(R.id.address_recycler_view)
    RecyclerView addressRecyclerView;
    @BindView(R.id.add_address)
    Button addAddress;
    private FindAddressPresenter findAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String userId = preferences.getString("userId", "");
        String sessionId = preferences.getString("sessionId", "");

        findAddressPresenter = new FindAddressPresenter(AddressActivity.this);
        findAddressPresenter.getFindPresenter(userId,sessionId);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this,AddAdressActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void getFindView(Object o) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        addressRecyclerView.setLayoutManager(linearLayoutManager);
        AddressList addressList = (AddressList) o;
       // Log.i("123", "getFindView: "+addressList.getResult().get(0).getAddress());
        List<AddressList.ResultBean> resultBeanList = addressList.getResult();
        //Log.i("add",resultBeanList.toString());
        MyAddressAdapter addressAdapter = new MyAddressAdapter(this,resultBeanList);
        addressRecyclerView.setAdapter(addressAdapter);
    }
}
