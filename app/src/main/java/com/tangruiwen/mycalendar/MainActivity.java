package com.tangruiwen.mycalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.tangruiwen.listener.DatePickerFragment;
import com.tangruiwen.listener.MyCellListenar;
import com.tangruiwen.timesquare.ClassDayDecrator;
import com.tangruiwen.timesquare.myCalendarPickerView;
import com.tangruiwen.utils.DialogUtil;
import com.tangruiwen.utils.GlobalVar;
import com.tangruiwen.utils.L;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private CalendarPickerView calendarPickerView = null;


    private SharedPreferences sharedPreferences = null;


    private long exittime = 0;

    ImageButton customFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        //保存选项信息
        sharedPreferences = getSharedPreferences(GlobalVar.spName, Activity.MODE_PRIVATE);

        //固定悬浮按钮
        customFAB = (ImageButton) findViewById(R.id.customFAB);


        long startime = sharedPreferences.getLong(GlobalVar.startday,0);
        Date startday = null;

        if(startime != 0){
            startday = new Date(startime);
        }



        List<CalendarCellDecorator> decorators = new ArrayList<CalendarCellDecorator>();

        decorators.add(new ClassDayDecrator(sharedPreferences));

        Calendar nextyear = Calendar.getInstance();
        nextyear.add(Calendar.YEAR,1);

        Calendar lastyear = Calendar.getInstance();
        lastyear.add(Calendar.YEAR,-1);

        //设置calendarPickView
        calendarPickerView = (CalendarPickerView) findViewById(R.id.calendar_view);

        //设置修饰上课日期的装饰类
        calendarPickerView.setDecorators(decorators);


        //设置学期开始日期
        calendarPickerView.setStartdata(startday);

        //设置日期点击事件处理类
        calendarPickerView.setOnDateSelectedListener(new MyCellListenar(this));
        //设置
        calendarPickerView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                if (firstVisibleItem != (totalItemCount/2)){
                    customFAB.setVisibility(View.VISIBLE);
                }else{
                    customFAB.setVisibility(View.GONE);
                }

            }
        });


        calendarPickerView.init(lastyear.getTime(),nextyear.getTime()).inMode(CalendarPickerView.SelectionMode.SINGLE).withSelectedDate(new Date());

        //创建浮动窗口
        //creatflow();




        customFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarPickerView.scrollToDate(new Date());

            }
        });

        customFAB.setVisibility(View.GONE);

        DialogUtil.calendarPickerView = calendarPickerView;
        DialogUtil.context = this;
        DialogUtil.fragmentManager = getFragmentManager();
        DialogUtil.sharedPreferences = sharedPreferences;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            DatePickerFragment dft = new DatePickerFragment();

            dft.setSharedPreferences(sharedPreferences);

            dft.setCalendarPickerView(calendarPickerView);

            dft.show(getFragmentManager(),"");

            return true;
        }else if(id == R.id.action_weeknum){

            AlertDialog alertDialog = DialogUtil.getDialog(this, sharedPreferences, calendarPickerView);

            alertDialog.show();
        }else if(id == R.id.action_exit){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // 两次返回退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis() - exittime) > 2000){
                Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();

                exittime = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onStart() {
        super.onStart();

        calendarPickerView.validateAndUpdate();

        L.i("MainActivity","onStart");
    }
}
