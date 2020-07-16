package com.heu.finance.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.common.SessionHelper;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.admin.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminMainController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/main")
    public String mainPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                           Model model,HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);

        List<User> list = userService.selectAllUser();
        while(list.isEmpty() && !pageNum.equals(1)){
            PageHelper.startPage(pageNum - 1,pageSize);
            list = userService.selectAllUser();
        }
        PageInfo<User> pageInfo = new PageInfo<>(list);

        model.addAttribute("userPageInfo",pageInfo);
        model.addAttribute("userList",list);

        model.addAttribute("activeUrl","indexActive");

        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        model.addAttribute("session",session);

        return "admin/main";
    }

    @RequestMapping("/updateUserStatus/{id}")
    @ResponseBody
    public Msg updateUserStatus(@PathVariable("id") Integer id,
                                @RequestParam("username") String username){
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        if( userService.updateUserStatus(user) == 1){
            SessionHelper.deleteSession(username);
            return Msg.success();
        }
        return Msg.failed();
    }
}
