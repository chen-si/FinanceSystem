package com.heu.finance.common.shiro;


import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.pojo.Admin;
import com.heu.finance.pojo.permission.AdminPermissions;
import com.heu.finance.pojo.permission.UserPermissions;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.LoginService;
import com.heu.finance.service.OnlineUserService;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.permission.AdminPermissionService;
import com.heu.finance.service.permission.UserPermissionService;
import com.heu.finance.service.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 登录验证和权限认证类
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
public class UserRealm extends AuthorizingRealm {
    private UserService userService;
    private LoginService loginService;
    private UserPermissionService userPermissionService;
    private AdminPermissionService adminPermissionService;
    private RedisService redisService;
    private OnlineUserService onlineUserService;
//    private Integer count = 0;

    @Autowired
    public void setOnlineUserService(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setUserPermissionService(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @Autowired
    public void setAdminPermissionService(AdminPermissionService adminPermissionService) {
        this.adminPermissionService = adminPermissionService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();

        String currentUsername = (String) subject.getPrincipal();

        if( !onlineUserService.isLogin(currentUsername) ){
            subject.logout();
            return info;
        }

        //从redis中取出info
        SimpleAuthorizationInfo cachedInfo =
                JSON.parseObject(redisService.hashGet(RedisConfig.UserAuthorization,currentUsername),
                        SimpleAuthorizationInfo.class);
        if (cachedInfo != null){
            return cachedInfo;
        }
        cachedInfo = JSON.parseObject(
                redisService.hashGet(RedisConfig.AdminAuthorization,currentUsername),
                SimpleAuthorizationInfo.class);
        if (cachedInfo != null){
            return cachedInfo;
        }

        User user = userService.selectUserByUsername(currentUsername);
        if (user!=null){
            info.addRole("user");
            List<UserPermissions> list = userPermissionService.getUserPermissionsByUserId(1);
            for (UserPermissions up:list) {
                info.addStringPermission(up.getPermission().getPermission());
            }
            redisService.hashSet(RedisConfig.UserAuthorization,currentUsername,JSON.toJSON(info).toString());
            redisService.expire(RedisConfig.UserAuthorization,240);
        }

        Admin admin = loginService.selectUserByUserName(currentUsername);
        if (admin!=null){
            System.out.println("admin authorization");
            info.addRole("admin");
            info.addRole("user");
            List<AdminPermissions> list = adminPermissionService.getAdminPermissionsByAdminId(1);
            for (AdminPermissions ap:list) {
                info.addStringPermission(ap.getPermission().getPermission());
            }
            redisService.hashSet(RedisConfig.AdminAuthorization,currentUsername,JSON.toJSON(info).toString());
            redisService.expire(RedisConfig.AdminAuthorization,240);
        }

        return info;
    }

    /**
     * 验证用户的登录，更新用户状态，
     * @param token token存储了用户名和密码
     * @return 返回一个AuthenticationInfo
     * @throws AuthenticationException 验证用户失败
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String password = new String(userToken.getPassword());
//        System.out.println(userToken.getPassword());
//        System.out.println(password);
        //从token中取到用户名再去查用户密码
        //User user = userService.queryUserByName(userToken.getUsername());
        User user = userService.selectUserByUsername(userToken.getUsername());

        if (user != null) {
            if (!user.getPassword().equals(password)){
                throw new AuthenticationException();
            }
            onlineUserService.addUser(user.getUsername());

            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();

//            System.out.println(currentSubject);

            user.setStatus(1);
            userService.updateUserStatus(user);

            session.setAttribute("loginUser", user);
//            System.out.println(session.getId());
//            SessionHelper.addSession(userToken.getUsername(),session);
            System.out.println("执行了=>认证=>"+user.getUsername()+"登录进入系统");
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "");
        }

        Admin admin = loginService.selectUserByUserName(userToken.getUsername());
        if (admin!=null){
            if(!admin.getPassword().equals(password)){
                throw new AuthenticationException();
            }

            onlineUserService.addUser(admin.getUsername());

            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();

//            System.out.println(currentSubject);

            admin.setStatus(1);

            loginService.updateAdminStatus(admin);

            session.setAttribute("loginAdmin", admin);
//            System.out.println(session.getId());
//            SessionHelper.addSession(userToken.getUsername(),session);
            System.out.println("执行了=>认证=>"+admin.getUsername()+"登录进入系统");

            return new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),"");
        }else{
            throw new AuthenticationException();
        }
    }
}
