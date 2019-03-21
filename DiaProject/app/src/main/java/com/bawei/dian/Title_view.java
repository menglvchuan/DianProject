package com.bawei.dian;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

/**
 * Time:2019/3/21
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class Title_view extends RelativeLayout {
    public Title_view(Context context) {
        super(context);
    }

    public Title_view(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(LayoutInflater.from(context).inflate(R.layout.title_view,null));
    }

    public Title_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
