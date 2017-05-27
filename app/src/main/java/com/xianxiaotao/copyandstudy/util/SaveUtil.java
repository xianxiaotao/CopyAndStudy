package com.xianxiaotao.copyandstudy.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by xianxiaotao on 17/5/15.
 *
 * SharedPreferences
 * 第一次启动
 * 是否登录
 * 用户名、密码
 * 清理全部（还原）
 */

public class SaveUtil {
    private static final String FIRST_BOOT = "firstBoot";
    private static final String IS_LOGGED = "isLogged";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public static boolean isFirstBoot(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(FIRST_BOOT, true);
    }

    public static void setFirstBoot(Context context, boolean isFirstBoot) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(FIRST_BOOT, isFirstBoot)
                .apply();
    }

    public static boolean isLogged(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(IS_LOGGED, false);
    }

    public static void saveLogged(Context context, boolean isLogged) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(IS_LOGGED, isLogged)
                .apply();
    }

    public static String getUsername(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(USERNAME, null);
    }

    public static void setUsername(Context context, String username) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USERNAME, username)
                .apply();
    }

    public static String getPassword(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PASSWORD, null);
    }

    public static void setPassword(Context context, String password) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PASSWORD, password)
                .apply();
    }

    public static void clear(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }
}
