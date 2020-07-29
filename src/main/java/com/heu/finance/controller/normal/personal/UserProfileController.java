package com.heu.finance.controller.normal.personal;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户个人信息页面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class UserProfileController {
    @Resource
    private UserService userService;

    /**
     * 展示用户个人信息
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/profile")
    public String profile(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        User user = userService.selectUserByUsername(loginUser.getUsername());
        model.addAttribute("user",user);
        return "user/personal/profile";
    }

    /**
     * 保存更改的用户信息
     * @param user user
     * @param id id
     * @return Msg
     */
    @RequestMapping(value="/updateUserProfile/{id}")
    @ResponseBody
    public Msg updateUser(User user,@PathVariable("id") Integer id){
        System.out.println(user);
        user.setId(id);
        if( userService.updateUserInfos(user) == 1 ){
            return Msg.success();
        }else{
            return Msg.failed();
        }
    }

}
