package com.xianxiaotao.copyandstudy.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 本类中放的是任意一个项目中都可以使用的工具
 *
 * Created by xianxiaotao on 17/4/10.
 */
public class CommonUtils {

    /**
     * 把dp值转换成px。<br />
     * 在160dpi的屏幕上，1dp=1px，因此1dp = 1/160英寸.<br />
     * 当屏幕密度是240dpi时，1px = 1/240英寸，此时1dp = 240/160px.
     */
    public static int dp2px(Context context, int dpValue) {
        // 得到当前的(屏幕密度/160)s的值。此时就是1dp = density * px
        float density = getPxValue(TypedValue.COMPLEX_UNIT_DIP, dpValue);
        // 加0.5是为了四舍五入
        return (int) (density + 0.5);
    }

    /**
     * 将任意单位的数值转换成px
     * @param unit 单位
     * @param value 数值
     */
    public static float getPxValue(int unit,float value){
       return TypedValue.applyDimension(unit,value, Resources.getSystem().getDisplayMetrics());
    }

    public static int px2dp(Context context, int pxValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5);
    }

    /**
     * 判断sd卡是否可用
     */
    public static boolean isSDEnable() {
        String en = Environment.getExternalStorageState();
        return en.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取屏幕的宽度,单位px
     */
    public static int getScreentWidth(Context c) {
        return (int) getWindowInfo(c, 0);
    }

    /**
     * 获取屏幕的高度,单位px
     */
    public static int getScreenHeight(Context c) {
        return (int) getWindowInfo(c, 1);
    }

    /**
     * 获取屏幕密度
     */
    public static int getScreenDensityDpi(Context c) {
        return (int) getWindowInfo(c, 2);
    }

    /**
     * 获取相对屏幕密度,即屏幕密度除以160以后的值
     */
    public static float getScreenDensity(Context c) {
        return getWindowInfo(c, 3);
    }

    private static float getWindowInfo(Context c, int type) {
        WindowManager wm = (WindowManager) c
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        switch (type) {
            case 0:
                return outMetrics.widthPixels;
            case 1:
                return outMetrics.heightPixels;
            case 2:
                return outMetrics.densityDpi;
            case 3:
                return outMetrics.density;
        }
        return -1f;
    }

    /**
     * 得到当前sdk的版本号.如2.3.5对应的值是10.
     */
    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取应用的版本号
     */
    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            return info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取版本名
     */
    public static String getVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 安装apk
     *
     * @param apkFile apk文件
     */
    public static void installApk(Context context, File apkFile) {
        if (apkFile != null && apkFile.exists()) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setDataAndType(Uri.fromFile(apkFile),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    /**
     * 判断是否有网络
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager manger = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manger.getActiveNetworkInfo();
            return (info != null && info.isConnectedOrConnecting());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取网络连接的类型.
     *
     * @return -1：当前没有连接网络 <br/> 1: wifi连接 <br/>2: 手机连接 <br/>3: 其他连接
     */
    public static int getNetWorkType(Context context) {
        ConnectivityManager manger = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manger.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {
            int name = info.getType();
            switch (name) {
                case ConnectivityManager.TYPE_WIFI:
                    return 1;
                case ConnectivityManager.TYPE_MOBILE:
                    return 2;
                default:
                    return 3;
            }
        } else {
            return -1;
        }
    }

    /**
     * s is a phone number or not
     */
    public static boolean isPhoneNumber(String s) {
        String pattern = "^((13[0-9])|(14[5,7])|(15[^(4,\\D)])|(17[0,6,7,8])|(18[0-9]))\\d{8}$";
        Pattern phone = Pattern.compile(pattern);
        return phone.matcher(s).matches();
    }

    /**
     * 验证邮箱地址是否正确
     */
    public static boolean checkEmail(String email) {
        boolean flag;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
