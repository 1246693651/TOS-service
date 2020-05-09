package com.hnist.tos.controller;


import com.hnist.tos.entity.User;
import com.hnist.tos.exception.TOSException;
import com.hnist.tos.exception.error.TOSEMError;
import com.hnist.tos.service.UserService;
import com.hnist.tos.shiro.session.SessionToken;
import com.hnist.tos.utils.CommonResult;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;


/**
 * @author Pany
 * @date 2020-04-30 11:27
 * @content
 */
@RestController
@RequestMapping(value = "/api/user")
@Validated
public class UserController extends BaseController{
    @Autowired
    private UserService userService;


    /**
     * 获取用户信息
     * @return
     * @throws TOSException
     */
    @RequiresAuthentication
    @GetMapping("/getUserInfo")
    public CommonResult getUserInfo() throws TOSException {
        //获取session中的安全数据(openid)
        Subject subject = SecurityUtils.getSubject();
        //subject获取所有的安全集合
        PrincipalCollection principals = subject.getPrincipals();
        //获取已经认证对用户数据
        User user = (User) principals.getPrimaryPrincipal();
        return CommonResult.success(userService.findById(user.getId()));
    }

    /**
     * 更新用户昵称和头像
     * @param nickname
     * @param avatar
     * @return
     * @throws TOSException
     */
    @RequiresAuthentication
    @PostMapping("/updateUserInfo")
    public CommonResult updateUserInfo(
            @NotBlank(message = "昵称不能为空")
                    String nickname,
            @NotBlank(message = "头像不能为空")
                    String avatar
    ) throws TOSException {
        //获取session中的安全数据(openid)
        Subject subject = SecurityUtils.getSubject();
        //subject获取所有的安全集合
        PrincipalCollection principals = subject.getPrincipals();
        User user1 = (User) principals.getPrimaryPrincipal();
        user1.setNickname(nickname);
        user1.setAvatar(avatar);
        userService.update(user1);
        return CommonResult.success("更新用户数据成功");
    }
}
