package com.hnist.tos.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hnist.tos.dao.UserDao;
import com.hnist.tos.exception.TOSException;
import com.hnist.tos.exception.error.TOSEMError;
import com.hnist.tos.shiro.session.SessionToken;
import com.hnist.tos.service.BaseService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pany
 * @date 2020-05-02 17:15
 * @content
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private UserDao userDao;

    @Value("${wx.miniapp.appid}")
    private String appid;

    @Value("${wx.miniapp.secret}")
    private String appSecret;

    /**
     * 小程序登录返回token到前端
     * @param code
     * @return
     * @throws TOSException
     */
    public Map<String, String> miniprogramLogin(String code) throws TOSException {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw new TOSException(TOSEMError.LOGIN_CHECK_USERINFO_FAIL);
        }
        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
//        sessionKey = jsonObject.get("session_key")+"";
//        openId = jsonObject.get("openid")+"";
        Map<String, String> mapResult = new HashMap<>();
        //shiro登录
        Subject subject = SecurityUtils.getSubject();
        SessionToken sessionToken = new SessionToken(jsonObject.get("openid")+"");
        subject.login(sessionToken);
        String id = (String) subject.getSession().getId();
        mapResult.put("token",id);
        return mapResult;
    }

    @Override
    public String testlogin(String open_id) {
        Subject subject = SecurityUtils.getSubject();
        String id = (String) subject.getSession().getId();
        SessionToken sessionToken = new SessionToken(open_id);
        subject.login(sessionToken);
        return "测试登录成功:"+id;
    }
}
