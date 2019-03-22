package com.bawei.dian.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.dian.Bean.AddressList;
import com.bawei.dian.R;
import com.bawei.dian.activity.AddressActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2019/3/22
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> {
    Context context;
    List<AddressList.ResultBean> resultBeanList;

    public MyAddressAdapter(Context context, List<AddressList.ResultBean> resultBeanList) {
        this.context = context;
        this.resultBeanList = resultBeanList;
    }

    @NonNull
    @Override
    public MyAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_list_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressAdapter.ViewHolder viewHolder, int i) {
        viewHolder.addrName.setText(resultBeanList.get(i).getRealName());
        viewHolder.addrPhone.setText(resultBeanList.get(i).getPhone());
        viewHolder.addrAddress.setText(resultBeanList.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return resultBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.addr_name)
        TextView addrName;
        @BindView(R.id.addr_phone)
        TextView addrPhone;
        @BindView(R.id.addr_address)
        TextView addrAddress;
        @BindView(R.id.text_updata)
        Button textUpdata;
        @BindView(R.id.text_delete)
        Button textDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
