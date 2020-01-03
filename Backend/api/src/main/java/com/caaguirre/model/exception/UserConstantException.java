package com.caaguirre.model.exception;

public class UserConstantException {

    public static final String KEY_USER_DISABLED = "USER.DISABLED";
    public static final int CODE_USER_DISABLED = 1;

    public static final String KEY_USER_INVALID_CREDENTIALS = "USER.INVALID.CREDENTIALS";
    public static final int CODE_USER_INVALID_CREDENTIALS = 2;

    public static final String KEY_USER_NULL_USERNAME = "USER.NULL.USERNAME";
    public static final int CODE_USER_NULL_USERNAME = 3;

    public static final String KEY_USER_NULL_PASSWORD = "USER.NULL.PASSWORD";
    public static final int CODE_USER_NULL_PASSWORD = 4;

    public static final String KEY_USER_NULL_EMAIL = "USER.NULL.EMAIL";
    public static final int CODE_USER_NULL_EMAIL = 5;

    public static final String KEY_USER_NULL_PERSON = "USER.NULL.PERSON";
    public static final int CODE_USER_NULL_PERSON = 6;

    public static final String KEY_USER_NULL_ROL = "USER.NULL.ROL";
    public static final int CODE_USER_NULL_ROL = 7;

    public static final String KEY_USER_NULL_STATUS = "USER.NULL.STATUS";
    public static final int CODE_USER_NULL_STATUS = 8;

    public static final String KEY_USER_EMAIL_IS_REGISTER = "USER.IS.EXIST.EMAIL";
    public static final int CODE_USER_EMAIL_IS_REGISTER = 9;

    public static final String KEY_USER_USERNAME_IS_REGISTER = "USER.IS.EXIST.USERNAME";
    public static final int CODE_USER_USERNAME_IS_REGISTER = 10;

    public static final String KEY_MESSAGE_EXCEPTION_EMAIL_IS_NOT_REGISTER = "user.is.no.exist.email";
    public static final int CODE_MESSAGE_EXCEPTION_EMAIL_IS_NOT_REGISTER = 11;

    public static final String KEY_MESSAGE_EXCEPTION_USER_IS_NOT_REGISTER = "user.is.not.exist.username";
    public static final int CODE_MESSAGE_EXCEPTION_USER_IS_NOT_REGISTER = 12;

    public static final String KEY_MESSAGE_EXCEPTION_USER_IS_NOT_PASS_EQUALS = "user.is.not.equals.password";
    public static final int CODE_MESSAGE_EXCEPTION_USER_IS_NOT_PASS_EQUALS = 13;

}
