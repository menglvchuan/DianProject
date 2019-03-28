package com.bawei.dian.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Bean.InsertAddress;
import com.bawei.dian.R;
import com.bawei.dian.presenter.address.AddrPresenter;
import com.bawei.dian.view.IAddrView;
import com.hengyi.wheelpicker.listener.OnCityWheelComfirmListener;
import com.hengyi.wheelpicker.ppw.CityWheelPickerPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:设置添加地址
 */
public class AddAdressActivity extends Activity implements IAddrView {
    @BindView(R.id.text_address)
    TextView textAddress;
    @BindView(R.id.real_name)
    EditText realName;
    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.address_text)
    EditText addressText;
    @BindView(R.id.address_xiang)
    EditText addressXiang;
    @BindView(R.id.zip_code)
    EditText zipCode;
    @BindView(R.id.save_address)
    Button saveAddress;
    private CityWheelPickerPopupWindow pickerPopupWindow;
    private AddrPresenter addrPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_adress);
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        final String userId = preferences.getString("userId", "");
        final String sessionId = preferences.getString("sessionId", "");


        addrPresenter = new AddrPresenter(AddAdressActivity.this);
        pickerPopupWindow = new CityWheelPickerPopupWindow(AddAdressActivity.this);
        pickerPopupWindow.setListener(new OnCityWheelComfirmListener() {
            @Override
            public void onSelected(String s, String s1, String s2, String s3) {
                addressText.setText(s + " " + s1 + " " + s2);
                zipCode.setText(s3);
            }
        });
        addressText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    pickerPopupWindow.show();
                }
            }
        });

        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String real = realName.getText().toString();
                String phone = phoneNum.getText().toString();
                String address = addressText.getText().toString();
                String addressX = addressXiang.getText().toString();
                String zipcode = zipCode.getText().toString();
                String xiang = address + addressX;
                // Toast.makeText(AddAdressActivity.this, phone, Toast.LENGTH_SHORT).show();
                addrPresenter.getAddPresenter(userId, sessionId, real, phone, xiang, zipcode);
            }
        });
    }

    @Override
    public void getAddrViewData(Object o) {
        InsertAddress insertAddress = (InsertAddress) o;
        if (insertAddress.getStatus().equals("0000")) {
            Toast.makeText(this, insertAddress.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AddressActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, insertAddress.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
