package com.tangruiwen.utils;

import android.util.Log;

/**
 * Created by tangruiwen on 15/3/15.
 */
public class L {

    private L(){

    }

    private final static boolean debug = true;

    public static void i(String TAG,String message){
        if (debug){
            Log.i(TAG,message);
        }
    }

}
