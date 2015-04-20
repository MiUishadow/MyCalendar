package com.tangruiwen.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by tangruiwen on 15/3/15.
 */
public class ScreenUtils {

    private ScreenUtils(){

    }

    private static DisplayMetrics getMetrics(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){

        DisplayMetrics outMetrics = getMetrics(context);

        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){

        DisplayMetrics outMetrics = getMetrics(context);

        return outMetrics.widthPixels;
    }

    public static Bitmap getShotWindow(Activity activity){
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap,0,0,width,height);

        view.destroyDrawingCache();

        return bp;
    }
}
