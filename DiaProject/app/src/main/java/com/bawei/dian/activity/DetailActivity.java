package com.bawei.dian.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Adapter.pageadapter;
import com.bawei.dian.Bean.AddShoppBean;
import com.bawei.dian.Bean.Bean2;
import com.bawei.dian.Bean.ShopDetails;
import com.bawei.dian.Bean.ShoppBean;
import com.bawei.dian.Bean.addMoreBean;
import com.bawei.dian.R;
import com.bawei.dian.base.BaseActivitiy;
import com.bawei.dian.presenter.detail.DetailsPresenter;
import com.bawei.dian.view.DetailsView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:详情页面
 */
public class DetailActivity extends BaseActivitiy<DetailsPresenter> implements DetailsView {
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.numbre)
    TextView numbre;
    @BindView(R.id.shoopprice)
    TextView shoopprice;
    @BindView(R.id.shoopnum)
    TextView shoopnum;
    @BindView(R.id.shoopname)
    TextView shoopname;
    @BindView(R.id.web)
    WebView web;
    private ImageView finn;
    private List<ImageView> list = new ArrayList<>();
    private ImageView add;
    private SharedPreferences sp;
    private DetailsPresenter date;
    private String userId;
    private String sessionId;
    private ShopDetails.ResultBean resultok;

    @Override
    protected DetailsPresenter initPresenter() {
        date = new DetailsPresenter(this);
        date.attachview(this);
        return date;
    }

    @Override
    protected int layoutResID() {
        return R.layout.detail_activity;
    }

    @Override
    protected void initView() {
        finn = findViewById(R.id.fin);
        add = findViewById(R.id.addshopp);
    }

    @Override
    protected void initDate() {
        //得到sp中的值
        sp = getSharedPreferences("config",MODE_PRIVATE);
        userId = sp.getString("userId", "");
        sessionId = sp.getString("sessionId", "");
        //点击加入购物车 进行判断
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userId.equals("")){
                    Intent intent = new Intent(DetailActivity.this, DengActivity.class);
                    startActivity(intent);
                    Toast.makeText(DetailActivity.this, "登录在添加购物车", Toast.LENGTH_SHORT).show();
                    return;
                }
                date.chaShopp(userId,sessionId);
            }
        });
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("vvv");
        Log.i("xxx",id+"");

        Log.d("de",userId+"***"+sessionId);

        date.detailsDate(id,userId,sessionId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    //详情数据
    @Override
    public void view(ShopDetails.ResultBean result) {
        this.resultok=result;
        String picture = result.getPicture();
        //截取字符串
        final String[] split = picture.split(",");
        for (int i = 0; i < split.length; i++) {
            ImageView imageView = new ImageView(DetailActivity.this);
            Glide.with(DetailActivity.this).load(split[i]).into(imageView);
            //把ImageView添加给集合
            list.add(imageView);
            //轮播适配器
            PageAdapter pageAdapter = new PageAdapter(list);
            pager.setAdapter(pageAdapter);
        }
        //第一次默认展示第一页的数值
        int currentItem = pager.getCurrentItem() + 1;
        numbre.setText(currentItem + "/" + split.length);
        //设置监听的方法  使当前页面跟随动
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                int currentItem = pager.getCurrentItem() + 1;
                numbre.setText(currentItem + "/" + split.length);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //名字  价格  购买数量赋值
        shoopname.setText(result.getCommodityName());
        shoopprice.setText("¥" + result.getPrice());
        shoopnum.setText("已售:" + result.getStock());

        String details = result.getDetails();
        web.loadData(details,"text/html; charset=UTF-8", null);
    }

    @Override
    public void vieww(List<ShoppBean.ResultBean> results) {
        List<addMoreBean> list1 = new ArrayList<>();
        for (int i = 0; i <results.size(); i++) {
            //查询购物车获得的id和count
            list1.add(new addMoreBean(results.get(i).getCommodityId()+"",1+""));
        }
        //详情的id  和count
        list1.add(new addMoreBean(resultok.getCommodityId()+"",1+""));
        Gson gson = new Gson();
        String  sss = gson.toJson(list1);
        date.addShopp(userId,sessionId,sss);
    }
    //得到同步之后回调过来的值
    @Override
    public void viewww(String s) {
        Toast.makeText(this,"111", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        date.detachview();
    }
}
