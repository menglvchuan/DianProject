package com.bawei.dian.Function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.dian.R;
import com.bawei.dian.activity.AddressActivity;
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
 * Description:
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
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine, null, false);
        unbinder = ButterKnife.bind(this, view);
        myAddress.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click() {
                Intent intent = new Intent(getActivity(),AddressActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
