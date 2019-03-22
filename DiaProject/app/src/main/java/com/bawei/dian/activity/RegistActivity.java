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

import com.bawei.dian.Bean.RegistBean;
import com.bawei.dian.R;
import com.bawei.dian.presenter.regist.RegistPresenter;
import com.bawei.dian.view.IRegistView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class RegistActivity extends AppCompatActivity implements IRegistView {
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

    }
    @OnClick({R.id.et_reg_name, R.id.et_reg_yan, R.id.et_reg_pwd, R.id.text_login, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_reg_name:
                break;
            case R.id.et_reg_yan:
                break;
            case R.id.et_reg_pwd:
                break;
            case R.id.text_login:
                Intent intent = new Intent(RegistActivity.this, DengActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_regist:
                Map<String,String> map = new HashMap<>();
                String name = etRegName.getText().toString();
                String pwd = etRegPwd.getText().toString();

                    map.put("phone",name);
                    map.put("pwd",pwd);
                    registPresenter.registPre(map);

                break;
        }
    }

    @Override
    public void showMsg(Object o) {
        RegistBean registBean = (RegistBean) o;
        String status = registBean.getStatus();
        if (status.equals("0000")) {
            Intent intent = new Intent(RegistActivity.this, DengActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(RegistActivity.this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void jumpActivity() {

    }
}
