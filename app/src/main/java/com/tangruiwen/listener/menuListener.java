package com.tangruiwen.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tangruiwen.mycalendar.DetailActivity;
import com.tangruiwen.mycalendar.R;
import com.tangruiwen.utils.DialogUtil;
import com.tangruiwen.utils.GlobalVar;
import com.tangruiwen.utils.L;

/**
 * Created by tangruiwen on 15/3/15.
 */
public class menuListener implements View.OnClickListener {

    Context context;

    private static menuListener singleObject = null;

    public menuListener(){

    }

    public static menuListener getInstance(Context context){
        if (singleObject == null){
            singleObject = new menuListener();
            singleObject.context = context;
        }

        return singleObject;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.menu_item1:
                DatePickerFragment dft = new DatePickerFragment();

                dft.setSharedPreferences(DialogUtil.sharedPreferences);

                dft.setCalendarPickerView(DialogUtil.calendarPickerView);

                dft.show(DialogUtil.fragmentManager,"");

                break;
            case R.id.menu_item2:
                DialogUtil.getDialog().show();

                break;

            case R.id.menu_setDetail:

                Intent intent = new Intent(context, DetailActivity.class);

                context.startActivity(intent);

                break;

            case R.id.menu_exit:
                DialogUtil.context.finish();

                break;
        }

    }
}
