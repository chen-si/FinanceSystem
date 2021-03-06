package com.heu.finance.controller.admin.permission;

import com.heu.finance.common.Msg;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.pojo.permission.Permission;
import com.heu.finance.pojo.permission.UserPermissions;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.permission.UserPermissionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 对应 admin端 用户权限界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Configuration
@RequestMapping("/admin")
public class UserPermissionController {
    private UserPermissionService userPermissionService;
    private Map<String,Integer> userPermissionMap;
    private RedisService redisService;
    private boolean flag = true;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setUserPermissionService(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    /**
     * 展示用户权限页面
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/UserPermissions")
    public String ShowUserPermission(Model model){
        List<UserPermissions> userPermissionsList = userPermissionService.getUserPermissionsByUserId(1);

        List<String> permissionList = new ArrayList<>();
        for(UserPermissions u : userPermissionsList){
            permissionList.add(u.getPermission().getPermission());
        }

        if (flag){
            userPermissionMap = new HashMap<>();
            List<Permission> userPermissions = userPermissionService.selectAllUserPermission();
            for (Permission p : userPermissions){
                userPermissionMap.put(p.getPermission(),p.getId());
            }
            flag = false;
        }

        model.addAttribute("permissionsList",permissionList);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","permissionActive");
        model.addAttribute("activeUrl2","userPermissionsActive");

        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/permission/userpermissions";
    }

    /**
     * 更新用户权限
     * @param permissions 权限列表
     * @return Msg
     */
    @RequestMapping("/updateUserPermissions")
    @ResponseBody
    public Msg updateAdminPermission(@RequestParam("userPermissions") String permissions){
        List<String> permissionList = Arrays.asList(permissions.split(";"));

        List<UserPermissions> userPermissionsList = userPermissionService.getUserPermissionsByUserId(1);

        List<String> existPermissionList = new ArrayList<>();
        for(UserPermissions u : userPermissionsList){
            existPermissionList.add(u.getPermission().getPermission());
        }

        if (permissionList.size() > existPermissionList.size()){
            //增加权限
            for(String p : permissionList){
                if(existPermissionList.contains(p)){
                    continue;
                }
                if( !(userPermissionService.addUserPermissions(1,userPermissionMap.get(p)) == 1 )){
                    return Msg.failed();
                };
            }
        }else{
            for(String p : existPermissionList){
                if (permissionList.contains(p)){
                    continue;
                }
                if ( !(userPermissionService.deleteUserPermission(1, userPermissionMap.get(p)) == 1)){
                    return Msg.failed();
                }
            }
        }
        redisService.hashRemove(RedisConfig.UserAuthorization);

        return Msg.success();
    }
}
