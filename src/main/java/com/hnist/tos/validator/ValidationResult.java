package com.hnist.tos.validator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content
 */
@Data
public class ValidationResult {
    // 是否有错误
    private boolean hasError = false;
    // 错误信息存储
    private Map<String, String> errorMsgMap = new HashMap<String, String>();
    public String getErrorMsg() {
        return StringUtils.join(this.errorMsgMap.values().toArray(), ",");
    }
}
