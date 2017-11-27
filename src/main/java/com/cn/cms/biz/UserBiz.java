package com.cn.cms.biz;

import com.alibaba.fastjson.JSONObject;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.User;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.CookieUtil;
import com.cn.cms.utils.EncryptUtil;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@Component
public class UserBiz extends BaseBiz{

    @Resource
    private JedisClient jedisClient;

    @Resource
    private UserService userService;

    @Resource
    private PermissionBiz permissionBiz;


    /**
     * WEB
     * 检查用户是否登录。如果登录后则写入Token
     * @param response
     * @param userName
     * @param pwd
     * @return
     */
    public String checkUserAndSetCookie(HttpServletResponse response, String userName, String pwd){
        User user = userService.findUserName(userName);
        pwd = EncryptUtil.encryptPwd(userName,pwd);
        if( user != null ){
            if(user.getPwd().equals(pwd)){
                setCookie(response,user);
                refreshUserCache(user);
                return ApiResponse.returnSuccess(StaticContants.SUCCESS_LOGIN);
            }else{
                return ApiResponse.returnFail(StaticContants.ERROR_PWD);
            }
        }else{
            return ApiResponse.returnFail(StaticContants.ERROR_NO_USER);
        }
    }

    /**
     * 设置Cookie && 设置Redis存储
     * 使用方式生成KEY
     * 按照USERID、KEY、time生成token
     * @param response
     * @param user
     */
    public void setCookie(HttpServletResponse response , User user){
        String time = String.valueOf(new Date().getTime());
        String key = EncryptUtil.token(user.getUserName(),user.getPwd(),user.getRealName(),time);
        String token = EncryptUtil.token(user.getUserId(),key,time);
        CookieUtil.addCookie(response,StaticContants.COOKIE_USER_ID,user.getUserId(),0);
        CookieUtil.addCookie(response,StaticContants.COOKIE_TIME,String.valueOf(time),0);
        CookieUtil.addCookie(response,StaticContants.COOKIE_USER_KEY,key,0);
        CookieUtil.addCookie(response,StaticContants.COOKIE_USER_TOKEN,token,0);
        CookieUtil.addCookie(response,StaticContants.COOKIE_REAL_NAME,user.getRealName(),0);
        jedisClient.set(RedisKeyContants.getToken(user.getUserId()), token, StaticContants.DEFAULT_SECONDS);
        permissionBiz.setPermissionRedis(user.getUserId(), PlatformEnum.CMS);
    }

    /**
     * refresh用户缓存
     * @param user
     */
    public void refreshUserCache(User user){
        this.jedisClient.set(RedisKeyContants.getUserKey(user.getUserId()), JSONObject.toJSONString(user));
    }

    /**
     * 清理Cookie信息
     * @param request
     * @param response
     */
    public void clearCookie(HttpServletRequest request, HttpServletResponse response){
        String userId = CookieUtil.getCookieVal(request,StaticContants.COOKIE_USER_ID);
        CookieUtil.delCookieVal(request,response,StaticContants.COOKIE_USER_ID);
        CookieUtil.delCookieVal(request,response,StaticContants.COOKIE_USER_TOKEN);
        CookieUtil.delCookieVal(request,response,StaticContants.COOKIE_USER_KEY);
        CookieUtil.delCookieVal(request,response,StaticContants.COOKIE_TIME);
        CookieUtil.delCookieVal(request,response,StaticContants.COOKIE_REAL_NAME);
        jedisClient.del(RedisKeyContants.getToken(userId));
        permissionBiz.delPermissionRedis(userId);

    }

    /**
     * 检测用户登录状态
     * @param request
     * @return
     */
    public boolean checkUserToken(HttpServletRequest request,HttpServletResponse response){
        String userId = CookieUtil.getCookieVal(request,StaticContants.COOKIE_USER_ID);
        String userKey = CookieUtil.getCookieVal(request,StaticContants.COOKIE_USER_KEY);
        String time = CookieUtil.getCookieVal(request,StaticContants.COOKIE_TIME);
        String token = CookieUtil.getCookieVal(request,StaticContants.COOKIE_USER_TOKEN);
        if(StringUtils.isNotBlank(token) && StringUtils.isNotBlank(time) && StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(userKey)){
            String currentToken = EncryptUtil.token(userId, userKey, time);
            String redisToken= jedisClient.get(RedisKeyContants.getToken(userId));
            if(StringUtils.isNotBlank(currentToken) && StringUtils.isNotBlank(redisToken) &&
                    currentToken.equals(redisToken) && currentToken.equals(token)){
                return true;
            }
        }
        clearCookie(request,response);
        return false;
    }
}
