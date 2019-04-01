package com.bawei.dian.Function;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.dian.R;
import com.bawei.dian.activity.AddressActivity;
import com.bawei.dian.activity.DengActivity;
import com.bumptech.glide.Glide;
import com.leon.lib.settingview.LSettingItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Time:2019/3/18
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:我的
 */
public class Mine extends Fragment {

    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.item_ziliao)
    LSettingItem itemZiliao;
    @BindView(R.id.my_circle)
    LSettingItem myCircle;
    @BindView(R.id.my_foot)
    LSettingItem myFoot;
    @BindView(R.id.my_wallet)
    LSettingItem myWallet;
    @BindView(R.id.my_address)
    LSettingItem myAddress;
    @BindView(R.id.profile_text)
    TextView profile_text;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine, null, false);

        SharedPreferences config = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE);

        unbinder = ButterKnife.bind(this, view);
        myAddress.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Intent intent = new Intent(getActivity(),AddressActivity.class);
                startActivity(intent);
            }
        });
        initData();

        profile_text.setText(config.getString("nikname","肖狗子"));
        Glide.with(this).load(config.getString("image","")).into(profileImage);
        return view;
    }

    private void initData() {
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
