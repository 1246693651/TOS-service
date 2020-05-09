package com.hnist.tos.exception.error;

public interface TOSError {
    public int getErrCode();
    public String getErrMsg();
    public TOSError setErrMsg(String msg);
}
