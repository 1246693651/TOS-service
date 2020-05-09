package com.hnist.tos.exception;

import com.hnist.tos.exception.error.TOSEMError;
import com.hnist.tos.exception.error.TOSError;


/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content
 */
public class TOSException extends RuntimeException implements TOSError {
    private TOSEMError TOSEMError;

    public TOSException(TOSEMError TOSEMError) {
        super();
        this.TOSEMError = TOSEMError;
    }

    @Override
    public int getErrCode() {
        return this.TOSEMError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.TOSEMError.getErrMsg();
    }

    @Override
    public TOSError setErrMsg(String msg) {
        return this.TOSEMError.setErrMsg(msg);
    }
}
