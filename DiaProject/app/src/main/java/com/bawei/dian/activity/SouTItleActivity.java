package com.bawei.dian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Adapter.SouAdapter;
import com.bawei.dian.Bean.JsonBean;
import com.bawei.dian.R;
import com.bawei.dian.presenter.sou.SouPresenter;
import com.bawei.dian.view.SouView;

import java.util.List;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:标题栏搜索
 */
public class SouTItleActivity extends AppCompatActivity implements SouView {
    private String keyword="板鞋";
    private int count=10;
    private int page=1;
    private EditText ed_seek;
    private TextView title_sou_text;
    private SouPresenter souPresenter;
    private RecyclerView title_rlv;
    private SwipeRefreshLayout swip;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_sou);
        //找控件
        title_rlv = findViewById(R.id.title_sou_rlv);
        ed_seek = findViewById(R.id.ed_seek);
        title_sou_text = findViewById(R.id.sou_text);
        swip = findViewById(R.id.title_swip);
        //mvp
        souPresenter = new SouPresenter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SouTItleActivity.this, 2);
        title_rlv.setLayoutManager(gridLayoutManager);
        //souPresenter = new SouPresenter(this);
        title_sou_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword = ed_seek.getText().toString();
                souPresenter.sou(keyword,count,page);
            }
        });
        swip.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                souPresenter.sou(keyword,count,page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swip.setRefreshing(false);
                    }
                },2000);
            }
        });
        title_rlv.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                page++;
                souPresenter.sou(keyword,count,page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swip.setRefreshing(false);
                    }
                },2000);
            }
        });
    }


    @Override
    public void getSouData(List<JsonBean.ResultBean> beans) {
        if(beans.size()!=0) {
            SouAdapter souAdapter = new SouAdapter(SouTItleActivity.this, beans);
            souAdapter.setOnShowListener(new SouAdapter.OnShowListener() {
                @Override
                public void getData(int position) {
                    Intent intent = new Intent(SouTItleActivity.this, DetailActivity.class);
                    intent.putExtra("vvv",position);
                    startActivity(intent);
                }
            });
            title_rlv.setAdapter(souAdapter);
        }else {
            Toast.makeText(SouTItleActivity.this,"已经是最底了",Toast.LENGTH_SHORT).show();
        }
    }
}
