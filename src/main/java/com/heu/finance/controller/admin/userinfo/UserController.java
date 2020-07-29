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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对应 admin端 用户管理界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 展示admin端的用户管理页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param orderBy 查询字段，默认为'default'
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/userlist")
    public String selectUserAll(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "orderBy",defaultValue = "default") String orderBy,
                                Model model, HttpServletRequest request){
//        System.out.println(pageNum+" "+pageSize);
        if(orderBy.equals("")){
            orderBy = "default";
        }
        PageHelper.startPage(pageNum,pageSize);

        List<User> list = userService.selectAllUserOrderBy(orderBy);
        while(list.isEmpty() && !pageNum.equals(1)){
            PageHelper.startPage(pageNum - 1,pageSize);
            list = userService.selectAllUserOrderBy(orderBy);
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);
        model.addAttribute("userPageInfo",pageInfo);
        model.addAttribute("userList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","userInfoActive");
        model.addAttribute("activeUrl2","userInfoActive");
        model.addAttribute("orderBy",orderBy);

        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/userinfo/userinfo";
    }

    /**
     * 添加用户信息
     * @param username username
     * @param password password
     * @param realname realname
     * @param phone phone
     * @param email email
     * @return Msg
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Msg AddUser(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "realname") String realname,
                       @RequestParam(value = "phone") String phone,
                       @RequestParam(value = "email") String email){
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setRealname(realname);
        user.setUsername(username);
        user.setPhone(phone);
        userService.insertUser(user);

        if (user.getId() != null) {
            return Msg.success();
        }
        return Msg.failed();
    }

    /**
     * 获取并返回修改用户信息回显数据
     * @param id 需要回显的用户项id
     * @return Msg
     */
    @RequestMapping("/getUserById/{id}")
    @ResponseBody
    public Msg getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        if (user != null){
            return Msg.success().add("user",user);
        }
        return Msg.failed();
    }

    /**
     * 修改/更新对应ID的用户信息
     * @param id ID
     * @param password password
     * @param realname realname
     * @param phone phone
     * @param email email
     * @return Msg
     */
    @RequestMapping("/updateUserProfile/{id}")
    @ResponseBody
    public Msg updateUserProfile(@PathVariable("id") Integer id,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "realname") String realname,
                                 @RequestParam(value = "phone") String phone,
                                 @RequestParam(value = "email") String email){
        User user = new User();
        user.setPassword(password);
        user.setId(id);
        user.setPhone(phone);
        user.setRealname(realname);
        user.setEmail(email);
        System.out.println(user);

        if(userService.updateUserInfos(user) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }

    /**
     * 删除对应ID的用户
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/deleteUserById/{id}")
    @ResponseBody
    public Msg deleteUserById(@PathVariable("id") Integer id){
        if(userService.deleteUserById(id) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }

}
