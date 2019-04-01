package com.bawei.dian.Function;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dian.Adapter.ListOneAdapter;
import com.bawei.dian.Adapter.ListTwoAdapter;
import com.bawei.dian.Adapter.MyAdapter;
import com.bawei.dian.Api.Api;
import com.bawei.dian.Bean.BannaBean;
import com.bawei.dian.Bean.OneListinfo;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.Bean.ThreeListinfo;
import com.bawei.dian.Bean.TwoListinfo;
import com.bawei.dian.R;
import com.bawei.dian.activity.ListActivity;
import com.bawei.dian.activity.SouTItleActivity;
import com.bawei.dian.presenter.ShowPresenter;
import com.bawei.dian.view.ShowView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Time:2019/3/18
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class ShouYe extends Fragment implements ShowView, View.OnClickListener {

    private RecyclerView rlv;
    private FlyBanner ban;
    private ShowPresenter showPresenter;
    private RecyclerView towre;

    private TextView sou_text;
    private ImageView img_sou;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye,null,false);
        //找控件
        rlv = view.findViewById(R.id.rlv);
        ban = view.findViewById(R.id.ban);
        sou_text = view.findViewById(R.id.sou_text);
        img_sou = view.findViewById(R.id.img_sou);
        //点击搜索跳转
        sou_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SouTItleActivity.class);
                startActivity(intent);
            }
        });
        //显示
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
        showPresenter = new ShowPresenter(this);
        showPresenter.show();
        showPresenter.ban();
        img_sou.setOnClickListener(this);
        //搜索
        return view;
    }
    @Override
    public void getViewData(Show.ResultBean result) {
        MyAdapter myAdapter = new MyAdapter(getActivity(), result);
        rlv.setAdapter(myAdapter);
    }

    @Override
    public void getOneData(List<OneListinfo.ResultBean> oneList) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_onelist, null);
        //RecyclerView列表控件
        RecyclerView ontList = view.findViewById(R.id.one_list);
        towre = view.findViewById(R.id.two_list);
        //设置管理器
        ontList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        ListOneAdapter listOneAdapter = new ListOneAdapter(getActivity(), oneList);
        ontList.setAdapter(listOneAdapter);
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow_background));
        popupWindow.setAnimationStyle(R.style.MyPopupWindow_anim_style);
        //这是背景色
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        //显示
        popupWindow.showAsDropDown(sou_text);

        listOneAdapter.setOnOneClick(new ListOneAdapter.OnOneClick() {
            @Override
            public void setIdData(String id) {
                //请求二级列表
                showPresenter.getTwoList(Api.TwoListUrl,id,disposable);
            }
        });
    }

    @Override
    public void getTwoData(List<TwoListinfo.ResultBean> result) {
        //设置管理器
        towre.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        ListTwoAdapter listTwoAdapter = new ListTwoAdapter(getActivity(), result);
        towre.setAdapter(listTwoAdapter);
        listTwoAdapter.setOnTwoClick(new ListTwoAdapter.OnTwoClick() {
            @Override
            public void setIdData(String id) {
                //跳转到显示页面
                Intent intent = new Intent(getActivity(), ListActivity.class);
                //传值
                intent.putExtra("Mid", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getThreeData(List<ThreeListinfo.ResultBean> threeBean) {

    }

    @Override
    public void getBanner(List<BannaBean.ResultBean> result) {
        List<String> list=new ArrayList<>();
        for (int i=0;i<result.size();i++){
            list.add(result.get(i).getImageUrl());
        }
        ban.setImagesUrl(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_sou:
                showPresenter.getOneList(Api.OneListUrl,disposable);
                break;
        }
    }
}
