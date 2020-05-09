package com.hnist.tos.controller;

import com.hnist.tos.exception.error.TOSEMError;
import com.hnist.tos.exception.TOSException;
import com.hnist.tos.utils.CommonResult;
import com.hnist.tos.validator.ValidationResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.apache.shiro.authc.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content 异常处理
 */
@RestController
public class BaseController {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Object handleException(HttpServletRequest request, Exception ex) {
        Map<String, Object> dataMap = new HashMap<>();
        // 未知异常
        dataMap.put("errCode", TOSEMError.UNKNOWN_ERROR.getErrCode());
        dataMap.put("errMsg", TOSEMError.UNKNOWN_ERROR.getErrMsg() + ":" + ex.getMessage());
        ex.printStackTrace();
        return CommonResult.fail(dataMap);
    }

    /**
     * 内部异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = TOSException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Object handleEMException(HttpServletRequest request, TOSException ex) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("errCode", ex.getErrCode());
        dataMap.put("errMsg", ex.getErrMsg());
        return CommonResult.fail(dataMap);
    }

    /**
     * 登录状态异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Object handleUnauthenticatedException(HttpServletRequest request, UnauthenticatedException ex) {
        Map<String, Object> dataMap = new HashMap<>();
        String message = ex.getMessage();
        dataMap.put("errCode", TOSEMError.INVALIED_LOGIN_STATUS.getErrCode());
        dataMap.put("errMsg", TOSEMError.INVALIED_LOGIN_STATUS.getErrMsg() + ":" + message);
        return CommonResult.fail(dataMap);
    }

    /**
     * 权限异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException ex) {
        Map<String, Object> dataMap = new HashMap<>();
        String message = ex.getMessage();
        dataMap.put("errCode", TOSEMError.PERMISSION_DENIED.getErrCode());
        dataMap.put("errMsg", TOSEMError.PERMISSION_DENIED.getErrMsg() + ":" + message);
        return CommonResult.fail(dataMap);
    }

    /**
     * 参数验证异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Object handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        Map<String, Object> dataMap = new HashMap<>();
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<?>> constraintViolationSet = ex.getConstraintViolations();
        constraintViolationSet.forEach(constraintViolation -> {
            String propertyName = constraintViolation.getPropertyPath().toString();
            String errMsg = constraintViolation.getMessage();
            validationResult.getErrorMsgMap().put(propertyName, propertyName + ":" + errMsg);
        });
        dataMap.put("errCode", TOSEMError.PARAMENT_AUTH_FAIL.getErrCode());
        dataMap.put("errMsg", TOSEMError.PARAMENT_AUTH_FAIL.getErrMsg() + ":" + validationResult.getErrorMsg());
        return CommonResult.fail(dataMap);
    }
}
