package com.bawei.dian.Function;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.dian.R;

/**
 * Time:2019/3/18
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:订单
 */
public class DingDan extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdan,null,false);
        return view;
    }
}
