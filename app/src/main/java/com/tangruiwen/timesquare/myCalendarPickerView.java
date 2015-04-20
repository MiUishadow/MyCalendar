package com.tangruiwen.timesquare;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarPickerView;
import com.tangruiwen.utils.ScreenUtils;

/**
 * Created by tangruiwen on 15/3/15.
 */
public class myCalendarPickerView extends CalendarPickerView {

    private int mScreenWidth;
    private int mScreenHeight;

    public void setmScreenWidth(int mScreenWidth) {
        this.mScreenWidth = mScreenWidth;
    }

    public void setmScreenHeight(int mScreenHeight) {
        this.mScreenHeight = mScreenHeight;
    }

    public myCalendarPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScreenHeight = ScreenUtils.getScreenHeight(context);
        mScreenWidth = ScreenUtils.getScreenWidth(context);

        //getLayoutParams().width = mScreenWidth;
        //getLayoutParams().height = mScreenHeight;

        setLayoutParams(new ViewGroup.LayoutParams(mScreenWidth,mScreenHeight));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.i("onMeasure","widthMeasure : "+ widthMeasureSpec);
        Log.i("onMeasure","heightMeasure : " + heightMeasureSpec);

        Log.i("onMeasure","mScreenWidth : "+ mScreenWidth);
        Log.i("onMeasure","mScreenHeight : " + mScreenHeight);

        super.onMeasure(widthMeasureSpec/2, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
