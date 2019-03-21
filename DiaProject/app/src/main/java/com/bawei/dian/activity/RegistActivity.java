package com.bawei.dian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.R;
import com.bawei.dian.presenter.regist.RegistPresenter;
import com.bawei.dian.view.DengView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class RegistActivity extends AppCompatActivity implements View.OnClickListener,DengView {
    @BindView(R.id.et_reg_name)
    EditText etRegName;
    @BindView(R.id.et_reg_yan)
    EditText etRegYan;
    @BindView(R.id.et_reg_pwd)
    EditText etRegPwd;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.btn_regist)
    Button btnRegist;

    private Unbinder bind;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        bind = ButterKnife.bind(RegistActivity.this);
        registPresenter = new RegistPresenter(this);
        textLogin.setOnClickListener(this);
        btnRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_login:
                Intent intent = new Intent(RegistActivity.this, DengActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_regist:
                HashMap<String,String> params = new HashMap<>();
                String name = etRegName.getText().toString();
                String pwd = etRegPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegistActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    params.put("phone",name);
                    params.put("pwd",pwd);
                    registPresenter.registPre(params);
                }
                break;
        }
    }

    @Override
    public void getViewData(String status) {
        if (status.equals("0000")){

            finish();
        }
    }
}
