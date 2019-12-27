package com.imuons.shopntrips.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.imuons.shopntrips.model.LoginResponseModel;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceUtils {
    static String PREFERENCE_NAME = "ProductPref";
    static String LOGIN_OBJECT = "login_object";
    static String DASHBOARD_OBJECT = "dashboard_object";
    static String USER_NAME = "user_name";
    static String USER_PPASSWORD = "user_password";
    static String USER_ID = "userid";
    static String ACCESSTOKEN = "accesstoken";
    static String AVA_BAL = "ava_bal";
    static String ID = "id";
    static String HISTORY_ID = "history_id";
    static String To_USER_ID = "touserid";
    static String Final_Ewallet = "finalewallet";
    static String Final_Topup = "finaltopup";
    static String REQ_ID = "requestid";


    public static void storeLoginObject(LoginResponseModel model, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(model);
        editor.putString(LOGIN_OBJECT, json);
        editor.apply();
    }

    public static LoginResponseModel getLoginObject(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        Gson gson = new Gson();
        String json = prefs.getString(LOGIN_OBJECT, "");
        return gson.fromJson(json, LoginResponseModel.class);
    }

//    public static void storeDashboardObject(DashboardResponseModel model, Context context) {
//        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(model);
//        editor.putString(DASHBOARD_OBJECT, json);
//        editor.apply();
//    }
//
//    public static DashboardResponseModel getDashboardObject(Context context) {
//        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
//        Gson gson = new Gson();
//        String json = prefs.getString(DASHBOARD_OBJECT, "");
//        return gson.fromJson(json, DashboardResponseModel.class);
//    }

    public static void storeUserName(Context context, String userId) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(USER_NAME, userId);
        editor.commit();
    }

    public static String getUserName(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(USER_NAME, null);
    }

    public static void storeId(Context context, String id) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(ID, id);
        editor.commit();
    }


    public static String getId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(ID, null);
    }

    public static void storefinalewallet(Context context, String finalewallet) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(Final_Ewallet, finalewallet);
        editor.commit();
    }


    public static String getfinalewallet(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(Final_Ewallet, null);
    }

    public static void storefinaltopup(Context context, String finaltopup) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(Final_Topup, finaltopup);
        editor.commit();
    }


    public static String getfinaltopup(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(Final_Topup, null);
    }

    public static void storePassword(Context context, String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(USER_PPASSWORD, password);
        editor.commit();
    }

    public static String getPassword(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(USER_PPASSWORD, null);
    }

    public static void clearPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferences.edit().remove(LOGIN_OBJECT).apply();
    }

    public static void clearID(Context context) {
        SharedPreferences preferencess = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferencess.edit().remove(USER_ID).apply();

    }

    public static void clearAva_bal(Context context) {
        SharedPreferences preferencess = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferencess.edit().remove(AVA_BAL).apply();

    }

    public static void clearAccess_Token(Context context) {
        SharedPreferences preferencess = context.getSharedPreferences(PREFERENCE_NAME, 0);
        preferencess.edit().remove(ACCESSTOKEN).apply();

    }

    public static void storeAccessToken(Context context, String accesstoken) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(ACCESSTOKEN, accesstoken);
        editor.commit();
    }

    public static String getAccesstoken(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(ACCESSTOKEN, null);
    }


    public static void storeAvaBal(Context context, Integer advanceWalletBalance) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putInt(AVA_BAL, advanceWalletBalance);
        editor.commit();
    }

    public static int getAvaBal(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getInt(AVA_BAL, 0);
    }

    public static void storeToUserId(Context context, String touserid) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(To_USER_ID, touserid);
        editor.commit();
    }

    public static String getToUserId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(To_USER_ID, null);
    }

    public static void storeHistoryId(Context context, String historyid) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(HISTORY_ID, historyid);
        editor.commit();
    }

    public static String getHistoryId(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(HISTORY_ID, null);
    }

    public static void storeReqid(Context context, String requestid) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(REQ_ID, requestid);
        editor.commit();
    }

    public static String getReqid(Context context) {
        final SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return prefs.getString(REQ_ID, null);
    }
}
