package com.example.newhns.data;

/**
 * Created by Wakeel on 21-Jan-18.
 */
public class GenerericResponseModel {

    private String  code ;
    private String  message ;
    private String  key;
    private String  locale;
    private String token;
    private String activationCode;

    public GenerericResponseModel(){}

    public GenerericResponseModel(String code, String message, String key, String locale, String token, String activationCode) {
        this.code = code;
        this.message = message;
        this.key = key;
        this.locale = locale;
        this.token = token;
        this.activationCode = activationCode;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
