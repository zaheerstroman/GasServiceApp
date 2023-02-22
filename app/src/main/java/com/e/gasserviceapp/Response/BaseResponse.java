package com.e.gasserviceapp.Response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("status")
    boolean status;
    @SerializedName("errorCode")
    int errorCode;
    @SerializedName("msg")
    String msg;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
