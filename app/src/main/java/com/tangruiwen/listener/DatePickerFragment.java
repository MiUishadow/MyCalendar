package com.tangruiwen.listener;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.squareup.timessquare.CalendarPickerView;
import com.tangruiwen.utils.GlobalVar;
import com.tangruiwen.timesquare.myDatePickerDialog;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tangruiwen on 15/3/3.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private SharedPreferences sharedPreferences = null;

    private CalendarPickerView calendarPickerView = null;


    public void setSharedPreferences(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public void setCalendarPickerView(CalendarPickerView calendarPickerView){
        this.calendarPickerView = calendarPickerView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();


        //return new DatePickerDialog(getActivity(),this,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

        return new myDatePickerDialog(getActivity(),this,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,monthOfYear);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        Date date = c.getTime();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(GlobalVar.startday,date.getTime());

        editor.commit();

        Calendar lastyear = Calendar.getInstance();
        lastyear.set(Calendar.YEAR,year - 1);
        lastyear.set(Calendar.MONTH,monthOfYear);
        lastyear.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        Calendar nextyear = Calendar.getInstance();
        nextyear.set(Calendar.YEAR,year + 1);
        nextyear.set(Calendar.MONTH,monthOfYear);
        nextyear.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        calendarPickerView.setStartdata(date);

        calendarPickerView.validateAndUpdate();

        calendarPickerView.init(lastyear.getTime(),nextyear.getTime()).inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(date);


    }
}
