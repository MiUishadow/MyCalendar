package com.tangruiwen.mycalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.tangruiwen.listener.menuListener;

/**
 * Created by tangruiwen on 15/3/15.
 */
public class MenuItem extends RelativeLayout {
    public MenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOnClickListener(menuListener.getInstance(context));
    }
}
