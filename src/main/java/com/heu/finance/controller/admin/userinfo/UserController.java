package com.heu.finance.controller.admin.userinfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.admin.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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

    @RequestMapping("/getUserById/{id}")
    @ResponseBody
    public Msg getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        if (user != null){
            return Msg.success().add("user",user);
        }
        return Msg.failed();
    }

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

    @RequestMapping("/deleteUserById/{id}")
    @ResponseBody
    public Msg deleteUserById(@PathVariable("id") Integer id){
        if(userService.deleteUserById(id) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }

}
