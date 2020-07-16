package com.heu.finance.common.shiro;


import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.common.SessionHelper;
import com.heu.finance.pojo.Admin;
import com.heu.finance.pojo.permission.AdminPermissions;
import com.heu.finance.pojo.permission.UserPermissions;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.LoginService;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.admin.permission.AdminPermissionService;
import com.heu.finance.service.admin.permission.UserPermissionService;
import com.heu.finance.service.admin.userinfo.UserService;
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


public class UserRealm extends AuthorizingRealm {
    private UserService userService;
    private LoginService loginService;
    private UserPermissionService userPermissionService;
    private AdminPermissionService adminPermissionService;
    private RedisService redisService;
//    private Integer count = 0;

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

        //从redis中取出info
        SimpleAuthorizationInfo cachedInfo =
                JSON.parseObject(redisService.get(RedisConfig.UserAuthorizationPrefix+currentUsername),
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
            redisService.set(RedisConfig.UserAuthorizationPrefix+currentUsername,JSON.toJSON(info).toString());
            redisService.expire(RedisConfig.UserAuthorizationPrefix+currentUsername,120);
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
            redisService.set(RedisConfig.UserAuthorizationPrefix+currentUsername,JSON.toJSON(info).toString());
            redisService.expire(RedisConfig.UserAuthorizationPrefix+currentUsername,120);
        }
        //info.addStringPermission(currentUser.getPrams());

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