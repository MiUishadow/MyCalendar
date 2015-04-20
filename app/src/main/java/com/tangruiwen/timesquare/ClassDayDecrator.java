package com.tangruiwen.timesquare;

import android.content.SharedPreferences;
import android.graphics.Color;


import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.MonthCellDescriptor;
import com.tangruiwen.utils.GlobalVar;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tangruiwen on 15/3/3.
 */
public class ClassDayDecrator implements CalendarCellDecorator{

    private SharedPreferences sharedPreferences = null;

    public ClassDayDecrator(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void decorate(CalendarCellView cellView, Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        MonthCellDescriptor monthCellDescriptor = (MonthCellDescriptor) cellView.getTag();

        String weekContext = sharedPreferences.getString(GlobalVar.weeknum,null);



        if (weekContext != null && !monthCellDescriptor.isHighlighted()){


            if(weekContext.contains(String.valueOf(cal.get(Calendar.DAY_OF_WEEK)))){

                cellView.setTextColor(Color.RED);
            }else{

                if(monthCellDescriptor.isCurrentMonth()&&!monthCellDescriptor.isHighlighted()){
                    cellView.setTextColor(Color.BLACK);
                }else{
                    cellView.setTextColor(Color.GRAY);
                }

            }
        }

        String context = (String) cellView.getText();


        if(context.equals("0")){
            cellView.setText("");
        }
    }
}
