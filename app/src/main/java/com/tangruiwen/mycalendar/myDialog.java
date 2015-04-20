package com.tangruiwen.mycalendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.squareup.timessquare.CalendarPickerView;

/**
 * Created by tangruiwen on 15/3/3.
 */
public class myDialog extends AlertDialog {

    private CalendarPickerView calendarPickerView = null;

    public void setCalendarPickerView(CalendarPickerView calendarPickerView){
        this.calendarPickerView = calendarPickerView;
    }

    protected myDialog(Context context,CalendarPickerView calendarPickerView) {
        super(context);
        this.calendarPickerView = calendarPickerView;

    }

    public AlertDialog getInstance(Context context){



        //View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //builder.setView(view);


        CharSequence[] mItems = new CharSequence[]{"周一","周二","周三","周四","周五","周六","周日"};

        boolean[] selects = new boolean[]{false,false,false,false,false,false,false};





        builder.setMultiChoiceItems(mItems, selects, new OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        builder.setPositiveButton("确定",new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("取消",new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

}
