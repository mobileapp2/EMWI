package com.imuons.shopntrips.utils;

public class Constants {
    //Regex Constants
    public static String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    public static String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //Network Code Constants
    public static int RESPONSE_CODE_OK = 200;
    public static int RESPONSE_CODE = 201;
    public static int RESPONSE_ERROR = 401;
    public static int RESPONSE_ERRORS = 404;

}
