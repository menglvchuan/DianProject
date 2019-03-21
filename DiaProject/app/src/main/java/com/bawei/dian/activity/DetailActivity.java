package com.bawei.dian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dian.Adapter.pageadapter;
import com.bawei.dian.Bean.Bean2;
import com.bawei.dian.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Description:
 */
public class DetailActivity extends AppCompatActivity {
    private MyHandler MyHandler =new MyHandler();
    private ViewPager kpage;
    private TextView knumber;
    private List<ImageView> list=new ArrayList<>();
    private WebView kwe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        kwe = findViewById(R.id.wbe);
        kpage = findViewById(R.id.pager);
        knumber = findViewById(R.id.numbre);
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("vvv");
        String url="http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId="+id;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request builder = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(builder);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message = new Message();
                message.obj=string;
                MyHandler.sendMessage(message);
            }
        });
    }
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String obj = (String) msg.obj;
            Gson gson = new Gson();
            Bean2 bean2 = gson.fromJson(obj, Bean2.class);
            Bean2.ResultBean result = bean2.getResult();
            String picture = result.getPicture();
            final String[] split = picture.split(",");
            for (int i=0;i<split.length;i++){
                ImageView imageView = new ImageView(DetailActivity.this);
                Glide.with(DetailActivity.this).load(split[i]).into(imageView);
                list.add(imageView);
                pageadapter pageadapter = new pageadapter(list);
                kpage.setAdapter(pageadapter);
            }

            //第一次默认展示第一页的数值
            int currentItem = kpage.getCurrentItem()+1;
            knumber.setText(currentItem+"/"+split.length);
            //设置监听的方法  使当前页面跟随动
            kpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    int currentItem = kpage.getCurrentItem()+1;
                    knumber.setText(currentItem+"/"+split.length);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });

            String details = result.getDetails();
            String mimeType = "text/html";
            String enCoding = "utf-8";
            kwe.loadDataWithBaseURL(null, details, mimeType, enCoding, null);
        }
    }
}
