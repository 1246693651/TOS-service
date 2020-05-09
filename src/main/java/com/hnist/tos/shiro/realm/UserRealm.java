package com.hnist.tos.shiro.realm;

import com.hnist.tos.entity.Role;
import com.hnist.tos.entity.User;
import com.hnist.tos.shiro.session.SessionToken;
import com.hnist.tos.service.RoleService;
import com.hnist.tos.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Pany
 * @date 2020-05-04 18:14
 * @content 自定义realm
 */
public class UserRealm extends AuthorizingRealm {

    public void setName(String name) {
        super.setName("userRealm");
    }

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        // 定义该Realm可以处理哪个类型的token
        return token instanceof SessionToken;
    }


    /**
     * 授权方法（通过安全数据）
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取已经认证对用户数据
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }
        info.setRoles(roles);//设置权限

        return info;
    }

    /**
     * 认证方法（登录）
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SessionToken sessionToken = (SessionToken) authenticationToken;
        //String openId = (String) JwtTokenUtil.getClaims(sessionToken.getCredentials()).get("open_id");
        String openId = sessionToken.getPrincipal();
        //根据open_id查询用户
        User user = userService.findByOpenId(openId);
        //根据用户是否存在
        //3 . 先从本地数据库中查找用户是否存在
        if (user == null) {
            user = new User();
            user.setOpenId(openId);    //不存在就新建用户
            //4.保存更新user
            userService.add(user);
            user=userService.findByOpenId(openId);//再查出来
        }
        //构造方法：安全数据,密码,realm域名(这里密码为ok)
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, "ok", this.getName());
        return info;
    }
}
