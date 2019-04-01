package com.bawei.dian.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bawei.dian.Adapter.ListOneAdapter;
import com.bawei.dian.Adapter.ListThreeAdapter;
import com.bawei.dian.Adapter.ListTwoAdapter;
import com.bawei.dian.Api.Api;
import com.bawei.dian.Bean.BannaBean;
import com.bawei.dian.Bean.OneListinfo;
import com.bawei.dian.Bean.Show;
import com.bawei.dian.Bean.ThreeListinfo;
import com.bawei.dian.Bean.TwoListinfo;
import com.bawei.dian.R;
import com.bawei.dian.base.BaseActivitiy;
import com.bawei.dian.presenter.ShowPresenter;
import com.bawei.dian.utils.SearchBoxView;
import com.bawei.dian.view.ShowView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ListActivity extends BaseActivitiy implements ShowView {
    private SearchBoxView searchBox;
    private RecyclerView recycle;
    private ImageView common;
    private ShowPresenter showPresenter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private RecyclerView towre;


    @Override
    public void getViewData(Show.ResultBean result) {

    }

    @Override
    public void getOneData(List<OneListinfo.ResultBean> oneList) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_onelist, null, false);
        //RecyclerView列表控件
        RecyclerView ontList = view.findViewById(R.id.one_list);
        towre = view.findViewById(R.id.two_list);
        //设置管理器
        ontList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        ListOneAdapter listOneAdapter = new ListOneAdapter(this, oneList);
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
        popupWindow.showAsDropDown(searchBox);
        listOneAdapter.setOnOneClick(new ListOneAdapter.OnOneClick() {
            @Override
            public void setIdData(String id) {
                //请求二级列表
                showPresenter.getTwoList(Api.TwoListUrl, id, disposable);
            }
        });
    }

    @Override
    public void getTwoData(List<TwoListinfo.ResultBean> result) {
        //设置管理器
        towre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //设置适配器
        ListTwoAdapter listTwoAdapter = new ListTwoAdapter(this, result);
        towre.setAdapter(listTwoAdapter);
        listTwoAdapter.setOnTwoClick(new ListTwoAdapter.OnTwoClick() {
            @Override
            public void setIdData(String id) {
                Log.i("three-------", id);
                showPresenter.getThreeList(Api.ThreeListUrl, id, 1, 5, disposable);
            }
        });
    }

    @Override
    public void getThreeData(List<ThreeListinfo.ResultBean> threeBean) {

        Log.i("three",threeBean.size()+"");
        ListThreeAdapter listThreeAdapter = new ListThreeAdapter(this, threeBean);
        recycle.setAdapter(listThreeAdapter);
    }

    @Override
    public void getBanner(List<BannaBean.ResultBean> result) {

    }

    @Override
    protected Object initPresenter() {
        return null;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        searchBox = findViewById(R.id.li_search);
        recycle = findViewById(R.id.li_recycle);
        common = findViewById(R.id.li_common);
        //设置样式
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recycle.setLayoutManager(manager);


        showPresenter = new ShowPresenter(this);
        common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListActivity.this,"1111",Toast.LENGTH_SHORT).show();
                showPresenter.getOneList(Api.OneListUrl, disposable);
            }
        });

    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        String mid = intent.getStringExtra("Mid");
        Log.i("three-------", mid);
        showPresenter.getThreeList(Api.ThreeListUrl, mid, 1, 6, disposable);
        //回调放法
        searchBox.setOnIntent(new SearchBoxView.OnIntent() {
            @Override
            public void onintent() {
                //跳转到搜索页面
                Intent intent = new Intent(ListActivity.this, SouTItleActivity.class);
                startActivity(intent);
            }
        });
    }

}
