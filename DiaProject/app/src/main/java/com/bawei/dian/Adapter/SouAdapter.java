package com.bawei.dian.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.dian.Bean.JsonBean;
import com.bawei.dian.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:搜索
 */
public class SouAdapter extends RecyclerView.Adapter<SouAdapter.MyViewHolder> {
    Context context;
    List<JsonBean.ResultBean> beans;

    public SouAdapter(Context context, List<JsonBean.ResultBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @NonNull
    @Override
    public SouAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sou_layout, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SouAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.textName.setText(beans.get(i).getCommodityName());
        myViewHolder.textPrice.setText("￥"+beans.get(i).getPrice()+"");
        Uri uri = Uri.parse(beans.get(i).getMasterPic());
        myViewHolder.simpleView.setImageURI(uri);
        /**
         *  final int commodityId = data.get(i).getCommodityId();
         *         myViewholder.itemView.setOnClickListener(new View.OnClickListener() {
         *                 @Override
         *                 public void onClick(View v) {
         *                     if(onShowListener!=null){s
         *                         onShowListener.getData(commodityId);
         *                     }
         *                 }
         *             });
         */
        final int id = beans.get(i).getCommodityId();
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onShowListener!=null){
                    onShowListener.getData(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.simple_view)
        SimpleDraweeView simpleView;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_price)
        TextView textPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    //定义一个接口
    public interface OnShowListener{
        void getData(int position);
    }
    public OnShowListener onShowListener;
    public void setOnShowListener(OnShowListener onShowListener){
        this.onShowListener=onShowListener;
    }
}
