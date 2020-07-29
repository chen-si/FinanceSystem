package com.heu.finance.controller.admin.userinfo;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 对应 admin端 用户管理界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class UserReputationController {
    @Autowired
    private UserService userService;

    /**
     * 展示admin端的用户信誉页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param orderBy 查询字段，默认为'default'
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/reputationList")
    public String selectUserAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "orderBy",defaultValue = "default") String orderBy,
                                Model model) {
        if ("".equals(orderBy)){
            orderBy = "default";
        }
        //引入pagehelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.selectAllUserOrderBy(orderBy);
        //PageInfo封装分页信息
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", userList);
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "reputationActive");
        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        model.addAttribute("orderBy",orderBy);

        return "admin/userinfo/reputation";
    }

    /**
     * 获取并返回修改用户信誉回显数据
     * @param id 需要回显的用户信誉项id
     * @return Msg
     */
    @RequestMapping("/getUserReputationById/{id}")
    @ResponseBody
    public Msg getUserInfoById(@PathVariable("id") Integer id){
        User user = userService.selectUserReputationById(id);
        if( user != null){
            return Msg.success().add("user", user);
        }
        return Msg.failed();
    }

    /**
     * 更新对应ID的用户信誉
     * @param user xuser
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/updateUserReputation/{id}")
    @ResponseBody
    public Msg updateUserProfile(User user, @PathVariable("id") Integer id){
        user.setId(id);
        int i = userService.updateUserProfile(user);
        if(i == 1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }
}
