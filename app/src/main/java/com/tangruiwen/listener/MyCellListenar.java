package com.tangruiwen.listener;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.squareup.timessquare.CalendarPickerView;
import com.tangruiwen.mycalendar.R;

import java.util.Date;

/**
 * Created by tangruiwen on 15/3/5.
 */
public class MyCellListenar implements CalendarPickerView.OnDateSelectedListener {

    PopupWindow mPopWindow = null;

    Activity mainActivity = null;

    int screenWidth = 0;
    int screenHeigh = 0;

    public MyCellListenar(Activity mainActivity){
        this.mainActivity = mainActivity;

        screenWidth = mainActivity.getWindow().getWindowManager().getDefaultDisplay().getWidth();

        screenHeigh = mainActivity.getWindow().getWindowManager().getDefaultDisplay().getHeight();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDateSelected(Date date) {

        setBackDisplay(0.5f);

        initPopWindow(date);

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);

        mPopWindow.showAtLocation(layoutInflater.inflate(R.layout.activity_main,null), Gravity.CENTER,0,0);
    }

    private void initPopWindow(Date date) {

        if(mPopWindow != null){
            return;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);

        View popview = layoutInflater.inflate(R.layout.cell_click_window,null);

        int popwidth = (screenWidth/5) * 4;
        int popheight = (screenHeigh/5) * 4;

        mPopWindow = new PopupWindow(popview,popwidth,popheight);


        mPopWindow.setFocusable(true);

        mPopWindow.setOutsideTouchable(true);

        mPopWindow.update();

        mPopWindow.setAnimationStyle(R.style.pop_anim);

        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackDisplay(1.0f);
            }
        });
    }

    private void setBackDisplay(float alpha){
        WindowManager.LayoutParams ip = mainActivity.getWindow().getAttributes();

        ip.alpha = alpha;

        mainActivity.getWindow().setAttributes(ip);
    }

    @Override
    public void onDateUnselected(Date date) {
    }
}
