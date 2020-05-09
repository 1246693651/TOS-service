package com.hnist.tos.shiro.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;
import org.crazycake.shiro.AuthCachePrincipal;

/**
 * @author Pany
 * @date 2020-05-05 20:35
 * @content 鉴权用的token ，实现 AuthenticationToken
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionToken implements AuthenticationToken, AuthCachePrincipal {

    private String token;

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public String getCredentials() {
        return "ok";
    }

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
