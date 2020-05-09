package com.hnist.tos.service;

import java.util.Map;

/**
 * @author Pany
 * @date 2020-05-02 17:14
 * @content
 */
public interface BaseService {
    Map<String, String> miniprogramLogin(String code);

    String testlogin(String open_id);
}
