package com.tangruiwen.utils;

import java.util.Calendar;

/**
 * Created by tangruiwen on 15/3/16.
 */
public class Schedule {

    public static final int REP_WEEK = 1;       //每周重复
    public static final int REP_DOUBWEEK = 2;   //双周重复
    public static final int REP_BODDWEEK = 3;   //单周重复

    private Calendar start_time;
    private Calendar end_time;

    private String S_detail;

    private int day_of_week;

    private int rep_state;

    public Calendar getStart_time() {
        return start_time;
    }

    public void setStart_time(Calendar start_time) {
        this.start_time = start_time;
    }

    public Calendar getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Calendar end_time) {
        this.end_time = end_time;
    }

    public String getS_detail() {
        return S_detail;
    }

    public void setS_detail(String s_detail) {
        S_detail = s_detail;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public int getRep_state() {
        return rep_state;
    }

    public void setRep_state(int rep_state) {
        this.rep_state = rep_state;
    }
}
