package com.hnist.tos.controller;

import com.hnist.tos.exception.TOSException;
import com.hnist.tos.service.BaseService;
import com.hnist.tos.utils.CommonResult;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pany
 * @date 2020-05-05 10:31
 * @content 微信小程登录控制器
 */
@RestController
@RequestMapping(value = "/api/user")
@Validated
public class WxMiniappController extends BaseController{
    @Autowired
    private BaseService baseService;

    /**
     * 登录
     *
     * @param code
     * @return 返回session_id
     * @throws TOSException
     */
    @PostMapping("/login")
    public CommonResult login(
            @NotBlank(message = "code不可为空")
                    String code
    ) throws TOSException {
        return CommonResult.success(baseService.miniprogramLogin(code));
    }


    /**
     * 测试登录
     *
     * @param open_id
     * @return 返回session_id
     * @throws TOSException
     */
    @PostMapping("/testlogin")
    public CommonResult testlogin(
            @NotBlank(message = "openid不可为空")
                    String open_id
    ) throws TOSException {
        return CommonResult.success(baseService.testlogin(open_id));
    }
//
//    /**
//     * 需要认证的测试接口  需要 @RequiresAuthentication 注解，则调用此接口需要 header 中携带自定义登陆态 authorization
//     */
//    @RequiresRoles("user")
//    @PostMapping("/sayHello")
//    public CommonResult sayHello() {
//        Map<String, String> result = new HashMap<>();
//        result.put("words", "hello World");
//        return CommonResult.success(result);
//    }
//
//    /**
//     * 需要认证的测试接口  需要 @RequiresAuthentication 注解，则调用此接口需要 header 中携带自定义登陆态 authorization
//     */
//    @RequiresAuthentication
//    @PostMapping("/sayHello2")
//    public CommonResult sayHello2() {
//        Map<String, String> result = new HashMap<>();
//        result.put("words", "hello World 2");
//        return CommonResult.success(result);
//    }
}
