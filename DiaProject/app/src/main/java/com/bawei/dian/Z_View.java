package com.bawei.dian;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Time:2019/3/19
 * <p>
 * Author:肖佳莹
 * <p>
 * Description:
 */
public class Z_View extends RelativeLayout {
    public Z_View(Context context) {
        super(context);
    }

    public Z_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.title_view, null);
        addView(view);
    }

    public Z_View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
