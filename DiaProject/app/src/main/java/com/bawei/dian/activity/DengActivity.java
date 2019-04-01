package com.bawei.dian.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Bean.LoginBean;
import com.bawei.dian.MainActivity;
import com.bawei.dian.R;
import com.bawei.dian.presenter.deng.LoginPresenter;
import com.bawei.dian.view.LoginView;
import com.bawei.dian.view.ShowView;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:登录
 */
public class DengActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.ck_rem_deng)
    CheckBox ckRemPwd;
    @BindView(R.id.text_reg_deng)
    TextView textReg;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private SharedPreferences preferences;
    private Unbinder bind;
    private LoginPresenter loginPresenter;
    private LoginBean loginBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deng_activity);
        bind = ButterKnife.bind(this);

        preferences = getSharedPreferences("config", MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        ckRemPwd.setChecked(flag);
        if (flag) {
            String name = preferences.getString("name", "");
            String pwd = preferences.getString("pwd", "");
            etLoginName.setText(name);
            etLoginPwd.setText(pwd);

        }
        //初始化presenter
        loginPresenter = new LoginPresenter(this);
        final HashMap<String,String> map = new HashMap<>();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etLoginName.getText().toString();
                String pwd = etLoginPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(DengActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    map.put("phone",name);
                    map.put("pwd",pwd);
                    if (loginPresenter !=null){
                        loginPresenter.loginPre(map);
                    }
                    SharedPreferences.Editor edit = preferences.edit();
                    if (ckRemPwd.isChecked()) {
                        edit.putBoolean("flag", true);
                        edit.putString("name", name);
                        edit.putString("pwd", pwd);
                    } else {
                        edit.putBoolean("flag", false);
                    }
                    edit.commit();
                }
            }

        });
        //点击快速注册 跳转页面
        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DengActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void showMsg(Object o) {
        loginBean = (LoginBean) o;
        if (loginBean!=null){
            //System.out.println(loginBean.getMessage());
            SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String status = loginBean.getStatus();
            if (status.equals("0000")) {
                Log.i("123", "showMsg: "+loginBean.getResult().getUserId());
                editor.putString("userId",loginBean.getResult().getUserId()+"");
                editor.putString("sessionId",loginBean.getResult().getSessionId()+"");
                Log.d("sb_login",loginBean.getResult().getUserId()+"***"+loginBean.getResult().getSessionId());
                editor.putString("image",loginBean.getResult().getHeadPic());
                editor.putString("nikname",loginBean.getResult().getNickName());
                editor.commit();
                Intent intent = new Intent(DengActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(DengActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void jumpActivity() {

    }
}

