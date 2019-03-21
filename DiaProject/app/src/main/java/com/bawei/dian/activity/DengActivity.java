package com.bawei.dian.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.dian.R;

import butterknife.BindView;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class DengActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deng_activity);
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        TextView textReg = findViewById(R.id.text_reg_deng);
        //点击快速注册 跳转页面
        textReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DengActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
    }
}
