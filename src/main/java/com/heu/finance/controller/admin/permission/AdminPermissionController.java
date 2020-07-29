package com.heu.finance.controller.admin.permission;

import com.heu.finance.common.Msg;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.pojo.permission.AdminPermissions;
import com.heu.finance.pojo.permission.Permission;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.permission.AdminPermissionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 对应 admin端 管理员权限界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class AdminPermissionController {
    private AdminPermissionService adminPermissionService;
    private Map<String,Integer> adminPermissionMap;
    private RedisService redisService;
    private boolean flag = true;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setAdminPermissionService(AdminPermissionService adminPermissionService) {
        this.adminPermissionService = adminPermissionService;
    }

    /**
     * 展示管理员权限页面
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("AdminPermissions")
    public String showAdminPermissons(Model model){
        List<AdminPermissions> adminPermissionsList = adminPermissionService.getAdminPermissionsByAdminId(1);

        List<String> permissionList = new ArrayList<>();
        for(AdminPermissions a : adminPermissionsList){
            permissionList.add(a.getPermission().getPermission());
        }

        if (flag){
            adminPermissionMap = new HashMap<>();
            List<Permission> adminPermissions = adminPermissionService.selectAllAdminPermission();
            for (Permission p : adminPermissions){
                adminPermissionMap.put(p.getPermission(),p.getId());
            }
            flag = false;
        }

        model.addAttribute("permissionsList",permissionList);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","permissionActive");
        model.addAttribute("activeUrl2","adminPermissionsActive");

        model.addAttribute("session", SecurityUtils.getSubject().getSession());
        return "admin/permission/adminpermissions";
    }

    /**
     * 更新管理员权限
     * @param permissions 权限列表
     * @return Msg
     */
    @RequestMapping("/updateAdminPermissions")
    @ResponseBody
    public Msg updateAdminPermission(@RequestParam("adminPermissions") String permissions){
        List<String> oldPermissionList = Arrays.asList(permissions.split(";"));
        List<String> permissionList = new ArrayList<>(oldPermissionList);
        //admin:adminPermissions 必须存在
        if(!permissionList.contains("admin:adminPermissions")){
            permissionList.add("admin:adminPermissions");
        }

        List<AdminPermissions> adminPermissionsList = adminPermissionService.getAdminPermissionsByAdminId(1);

        List<String> existPermissionList = new ArrayList<>();
        for(AdminPermissions a : adminPermissionsList){
            existPermissionList.add(a.getPermission().getPermission());
        }

//        System.out.println(permissionList);

        if (permissionList.size() > existPermissionList.size()){
            //增加权限
            for(String p : permissionList){
                if(existPermissionList.contains(p)){
                    continue;
                }
                if( !(adminPermissionService.addAdminPermissions(1,adminPermissionMap.get(p)) == 1 )){
                    return Msg.failed();
                };
            }
        }else{
            for(String p : existPermissionList){
                if (permissionList.contains(p)){
                    continue;
                }
                if ( !(adminPermissionService.deleteAdminPermissions(1, adminPermissionMap.get(p)) == 1)){
                    return Msg.failed();
                }
            }
        }
        redisService.hashRemove(RedisConfig.AdminAuthorization);
        return Msg.success();
    }
}
