package com.bawei.dian.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dian.Bean.Show;
import com.bawei.dian.R;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Time:2019/3/20
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class MyRxxpAdapter extends RecyclerView.Adapter<MyRxxpAdapter.ViewHolder> {
    Context context;
    List<Show.ResultBean.RxxpBean.CommodityListBean> commodityList;

    public MyRxxpAdapter(Context context, List<Show.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public MyRxxpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rxxp_show_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRxxpAdapter.ViewHolder viewHolder, final int i) {
        /*Uri parse = Uri.parse(commodityList.get(i).getMasterPic());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(parse)
                .build();
        PipelineDraweeController controller= (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(viewHolder.rxxpSimpleView.getController())
                .build();
        viewHolder.rxxpSimpleView.setController(controller);*/
        RoundingParams cornersRadius = RoundingParams.fromCornersRadius(5f);
        cornersRadius.setRoundAsCircle(false);
        cornersRadius.setCornersRadius(8);
        //viewHolder.rxxpSimpleView.setImageURI(parse);
        //viewHolder.rxxpSimpleView.getHierarchy().setRoundingParams(cornersRadius);
        viewHolder.rxxpTitle.setText(commodityList.get(i).getCommodityName());
        viewHolder.rxxpPrice.setText("￥:" + commodityList.get(i).getPrice());
        Glide.with(context).load(commodityList.get(i).getMasterPic()).into(viewHolder.rxxpSimpleView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(commodityList.get(i).getCommodityId() + "");
               // context.startActivity(new Intent(context, DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView rxxpTitle;
        private final TextView rxxpPrice;
        private final ImageView rxxpSimpleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rxxpTitle = itemView.findViewById(R.id.rxxp_title);
            rxxpPrice = itemView.findViewById(R.id.rxxp_price);
            rxxpSimpleView = itemView.findViewById(R.id.rxxp_simple_view);
        }
    }
}
