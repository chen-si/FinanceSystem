package com.heu.finance.pojo.admin.permission;


import com.heu.finance.pojo.admin.Admin;
import com.heu.finance.pojo.admin.userinfo.User;
import com.heu.finance.service.LoginService;
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
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


public class UserRealm extends AuthorizingRealm {
    UserService userService;
    LoginService loginService;
    UserPermissionService userPermissionService;
    AdminPermissionService adminPermissionService;

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
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();

        String currentUserUsername = (String) subject.getPrincipal();
        User user = userService.selectUserByUsername(currentUserUsername);
        if (user!=null){
            info.addRole("user");
            List<UserPermissions> list = userPermissionService.getUserPermissionsByUserId(1);
            for (UserPermissions up:list) {
                info.addStringPermission(up.getPermission().getPermission());
            }
        }

        String currentAdminUsername = (String) subject.getPrincipal();
        Admin admin = loginService.selectUserByUserName(currentAdminUsername);
        if (admin!=null){
            info.addRole("admin");
            info.addRole("user");
            List<AdminPermissions> list = adminPermissionService.getAdminPermissionsByAdminId(1);
            for (AdminPermissions ap:list) {
                info.addStringPermission(ap.getPermission().getPermission());
            }
        }
        //info.addStringPermission(currentUser.getPrams());

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String password = new String(userToken.getPassword());
        System.out.println(userToken.getPassword());
        System.out.println(password);
        //从token中取到用户名再去查用户密码
        //User user = userService.queryUserByName(userToken.getUsername());
        User user = userService.selectUserByUsername(userToken.getUsername());

        if (user != null) {
            if (!user.getPassword().equals(password)){
                throw new AuthenticationException();
            }
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            user.setStatus(1);
            userService.updateUserStatus(user);
            session.setAttribute("loginUser", user);
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
            admin.setStatus(1);
            loginService.updateAdminStatus(admin);
            session.setAttribute("loginAdmin", admin);
            System.out.println("执行了=>认证=>"+admin.getUsername()+"登录进入系统");
            return new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),"");
        }else{
            throw new AuthenticationException();
        }
    }
}
