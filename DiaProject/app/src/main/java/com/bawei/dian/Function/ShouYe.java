package com.bawei.dian.Function;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Adapter.MyAdapter;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.R;
import com.bawei.dian.activity.SouTItleActivity;
import com.bawei.dian.presenter.ShowPresenter;
import com.bawei.dian.view.ShowView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Time:2019/3/18
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class ShouYe extends Fragment implements ShowView{

    private RecyclerView rlv;
    private FlyBanner ban;
    private ShowPresenter showPresenter;

    private TextView sou_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye,null,false);
        //找控件
        rlv = view.findViewById(R.id.rlv);
        ban = view.findViewById(R.id.ban);
        sou_text = view.findViewById(R.id.sou_text);
        //点击搜索跳转
        sou_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SouTItleActivity.class);
                startActivity(intent);
            }
        });
        //轮播
        List<String> list = new ArrayList<>();
        list.add("http://172.17.8.100/images/small/banner/cj.png");
        list.add("http://172.17.8.100/images/small/banner/hzp.png");
        list.add("http://172.17.8.100/images/small/banner/px.png");
        for (int i=0;i<list.size();i++){
            list.get(i);
        }
        ban.setImagesUrl(list);
        //显示
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
        showPresenter = new ShowPresenter(this);
        showPresenter.show();
        //搜索
        return view;
    }
    @Override
    public void getViewData(Show.ResultBean result) {
        MyAdapter myAdapter = new MyAdapter(getActivity(), result);
        rlv.setAdapter(myAdapter);
    }

}
