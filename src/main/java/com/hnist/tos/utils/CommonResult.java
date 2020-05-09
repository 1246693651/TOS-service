package com.hnist.tos.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    String status;
    Object data;

    /**
     * 返回请求成功
     * @param data
     * */
    public static CommonResult success(Object data) {
        return new CommonResult("success", data);
    }

    /**
     * 返回请求失败
     * @param data
     * */
    public static CommonResult fail(Object data) {
        return new CommonResult("fail", data);
    }
}
