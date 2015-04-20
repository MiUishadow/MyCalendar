package com.tangruiwen.mycalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.tangruiwen.listener.menuListener;
import com.tangruiwen.utils.ScreenUtils;

/**
 * Created by tangruiwen on 15/3/15.
 */
public class SlidingMenu extends HorizontalScrollView {



    private int mScreenWidth;

    private int mMenuRightPadding = 25;

    private int mMenuWidth;

    private int mHalfMenuWidth;

    private boolean once;

    private Context context;

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        mScreenWidth = ScreenUtils.getScreenWidth(context) / 2;

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if(!once){
            LinearLayout wrapper = (LinearLayout) getChildAt(0);

            ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);

            ViewGroup content = (ViewGroup) wrapper.getChildAt(1);

            mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMenuRightPadding, content.getResources().getDisplayMetrics());

            mMenuWidth = mScreenWidth - mMenuRightPadding;

            mHalfMenuWidth = mMenuWidth / 2;

            menu.getLayoutParams().width = mMenuWidth;

            content.getLayoutParams().width = mScreenWidth;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth,0);

            once = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if(scrollX > mHalfMenuWidth){
                    this.smoothScrollTo(mMenuWidth,0);
                }else{
                    this.smoothScrollTo(0,0);
                }
                return true;
        }

        return super.onTouchEvent(ev);
    }


}
