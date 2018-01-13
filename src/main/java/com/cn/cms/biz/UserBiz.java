package com.cn.cms.biz;

import com.alibaba.fastjson.JSONObject;
import com.cn.cms.bo.UserBean;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.PlatformEnum;
import com.cn.cms.middleware.JedisClient;
import com.cn.cms.po.Base;
import com.cn.cms.po.User;
import com.cn.cms.service.UserService;
import com.cn.cms.utils.CookieUtil;
import com.cn.cms.utils.EncryptUtil;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
     * 返回Map
     * @param userIds
     * @return
     */
    public Map<String, UserBean> getUserBeanMap(List<String> userIds){
        return toBeanMap(getUserCacheList(userIds));
    }

    public Map<String, UserBean> toBeanMap(List<User> users){
        Map<String, UserBean> map = new HashMap<String, UserBean>();
        if(users!=null && users.size()>0) {
            for (int i = 0; i < users.size(); i++) {
                UserBean userBean = new UserBean(users.get(i));
                map.put(userBean.getUserId(),userBean);
            }
        }
        return map;
    }

    /**
     * 缓存中获取用户信息
     * @param userIds
     * @return
     */
    public List<User> getUserCacheList(List<String> userIds){
        Map<String, String> map = new HashMap<>();
        for(int i=0;i<userIds.size();i++){
            if(StringUtils.isNotBlank(userIds.get(i))) {
                map.put(userIds.get(i), RedisKeyContants.getUserKey(userIds.get(i)));
            }
        }
        List<String> list = jedisClient.mget(map.values().toArray(new String[map.size()]));
        List<User> result = new ArrayList<>();
        for(int i=0; i <list.size();i++){
            if(StringUtils.isNotBlank(list.get(i))) {
                result.add(JSONObject.parseObject(list.get(i), User.class));
            }
        }
        return result;
    }


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
    public boolean checkUserToken(HttpServletRequest request){
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
        return false;
    }

    /**
     * 根据UserId获取用户基础信息
     * @param userId
     * @return
     */
    public UserBean getUserBean(String userId){
        User user = this.getUserCache(userId);
        UserBean userBean = new UserBean(user);
        return userBean;
    }

    public User getUserCache(String userId){
        String str = jedisClient.get(RedisKeyContants.getUserKey(userId));
        if(StringUtils.isNotBlank(str)){
            return JSONObject.parseObject(str, User.class);
        }
        return null;
    }

    /**
     * 加载创建人和修改人
     * @param list
     */
    public void dataInitBase(List<? extends Base> list){
        if(StringUtils.isNotEmpty(list)) {
            List<String> userIds = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Base base = list.get(i);
                if (StringUtils.isNotBlank(base.getCreateUserId())) {
                    userIds.add(base.getCreateUserId());
                }
                if (StringUtils.isNotBlank(base.getLastModifyUserId())) {
                    userIds.add(base.getLastModifyUserId());
                }
            }
            if (userIds.size() > 0) {
                Map<String, UserBean> map = getUserBeanMap(userIds);
                for (int i = 0; i < list.size(); i++) {
                    Base base = list.get(i);
                    base.setCreateUserName(map.get(base.getCreateUserId()) != null ? map.get(base.getCreateUserId()).getRealName() : "");
                    base.setLastModifyUserName(map.get(base.getLastModifyUserId()) != null ? map.get(base.getLastModifyUserId()).getRealName() : "");
                }
            }
        }
    }

    /**
     * 分页获取用户列表
     * @param page
     * @return
     */
    public List<UserBean> listUser(Page page){
        Integer count = userService.queryUserCount();
        page.setCount(count);
        List<User>  userList = null;
        if(page.isQuery()){
            userList = userService.queryUserList(page);
        }
        List<UserBean> userBeanList = toBean(userList);
        return userBeanList;
    }

    /**
     * PO 转 BO
     * @param users
     * @return
     */
    public List<UserBean> toBean(List<User> users){
        List<UserBean> userBeanList = new ArrayList<UserBean>();
        if(users!=null && users.size()>0) {
            for (int i = 0; i < users.size(); i++) {
                UserBean userBean = new UserBean(users.get(i));
                userBeanList.add(userBean);
            }
        }
        return userBeanList;
    }

    /**
     * 加载创建人和修改人
     * @param list
     */
    public void dataInit(List<UserBean> list){
        if(StringUtils.isNotEmpty(list)) {
            List<String> userIds = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                UserBean userBean = list.get(i);
                if (StringUtils.isNotBlank(userBean.getCreateUserId())) {
                    userIds.add(userBean.getCreateUserId());
                }
                if (StringUtils.isNotBlank(userBean.getLastModifyUserId())) {
                    userIds.add(userBean.getLastModifyUserId());
                }
            }
            if (userIds.size() > 0) {
                Map<String, UserBean> map = getUserBeanMap(userIds);
                for (int i = 0; i < list.size(); i++) {
                    UserBean userBean = list.get(i);
                    userBean.setCreateUserName(map.get(userBean.getCreateUserId()) != null ? map.get(userBean.getCreateUserId()).getRealName() : "");
                    userBean.setLastModifyUserName(map.get(userBean.getLastModifyUserId()) != null ? map.get(userBean.getLastModifyUserId()).getRealName() : "");
                }
            }
        }

    }
}
