package com.tangruiwen.mycalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tangruiwen.utils.GlobalVar;
import com.tangruiwen.utils.Schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tangruiwen on 15/3/15.
 */
public class DetailActivity extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    private Switch rep_week,rep_boddweek,rep_doubweek;

    private Button btn_cancel,btn_done;

    private TextView text_starttime,text_endtime;

    private int rep_state;

    private EditText s_detail;

    private Schedule schedule;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        rep_week = (Switch) findViewById(R.id.rep_week);
        rep_boddweek = (Switch) findViewById(R.id.rep_boddweek);
        rep_doubweek = (Switch) findViewById(R.id.rep_doubweek);


        rep_week.setOnCheckedChangeListener(this);
        rep_doubweek.setOnCheckedChangeListener(this);
        rep_boddweek.setOnCheckedChangeListener(this);

        btn_done = (Button) findViewById(R.id.btn_done);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_done.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        s_detail = (EditText) findViewById(R.id.s_detail);

        text_starttime = (TextView) findViewById(R.id.text_starttime);

        text_endtime = (TextView) findViewById(R.id.text_endtime);

        Calendar calendar = Calendar.getInstance();

        text_starttime.setText(sdf.format(calendar.getTime()));

        calendar.add(Calendar.HOUR_OF_DAY,2);

        text_endtime.setText(sdf.format(calendar.getTime()));

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            //关闭，直接返回
            rep_state = 0;

            return;
        }
        switch (buttonView.getId()) {
            case R.id.rep_week:
                //每周重复
                rep_state = Schedule.REP_WEEK;

                rep_boddweek.setChecked(false);
                rep_doubweek.setChecked(false);
                break;
            case R.id.rep_boddweek:
                //单周重复
                rep_state = Schedule.REP_BODDWEEK;

                rep_week.setChecked(false);
                rep_doubweek.setChecked(false);
                break;
            case R.id.rep_doubweek:
                //双周重复
                rep_state = Schedule.REP_DOUBWEEK;
                rep_week.setChecked(false);
                rep_boddweek.setChecked(false);

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_done:

                saveSchedule();
                finish();
                break;
        }
    }

    private void saveSchedule() {
        String detail_context = s_detail.getText().toString().trim();
        if (detail_context==null || "".equals(detail_context)){
            Toast.makeText(this,"不能创建空白日程",Toast.LENGTH_SHORT).show();

        }else{

        }
    }
}
