package com.tangruiwen.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;

import com.squareup.timessquare.CalendarPickerView;

/**
 * Created by tangruiwen on 15/3/4.
 */
public class DialogUtil {

    public static SharedPreferences sharedPreferences;
    public static CalendarPickerView calendarPickerView;

    public static Activity context;

    public static FragmentManager fragmentManager;


    public static AlertDialog getDialog(final Context context, final SharedPreferences sharedPreferences, final CalendarPickerView calendarPickerView){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        CharSequence[] mItems = new CharSequence[]{"周日","周一","周二","周三","周四","周五","周六"};

        final boolean[] selects = new boolean[]{false,false,false,false,false,false,false};

        String weekstr = sharedPreferences.getString(GlobalVar.weeknum,"");

        for (int i = 0;i < selects.length;i++){
            if(weekstr.contains(String.valueOf(i+1))){
                selects[i] = true;
            }
        }

        builder.setMultiChoiceItems(mItems, selects, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selects[which] = isChecked;
            }
        });

        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weekstr = "";
                for(int i = 0;i < selects.length;i++){
                    if (selects[i]){
                        weekstr+=String.valueOf(i+1)+",";
                    }
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(GlobalVar.weeknum,weekstr);
                editor.commit();


                calendarPickerView.validateAndUpdate();
            }
        });

        builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    public static AlertDialog getDialog(){
        return getDialog(context,sharedPreferences,calendarPickerView);
    }
}
