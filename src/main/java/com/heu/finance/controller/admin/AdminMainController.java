package com.heu.finance.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.OnlineUserService;
import com.heu.finance.service.userinfo.UserService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 主控制器 控制admin主页以及强制用户下线功能以及admin登出
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class AdminMainController {
    private UserService userService;
    private OnlineUserService onlineUserService;

    @Autowired
    public void setOnlineUserService(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 显示admin的主页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param orderBy 查询字段，默认为'default'
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */

    @RequestMapping("/main")
    public String mainPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                           @RequestParam(value = "orderBy",defaultValue = "default") String orderBy,
                           Model model){
        if (orderBy.equals("")){
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
        model.addAttribute("orderBy",orderBy);

        model.addAttribute("activeUrl","indexActive");

        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        model.addAttribute("session",session);

        return "admin/main";
    }

    /**
     * 更新用户状态——强制下线功能
     * @param id 用户ID
     * @param username 用户昵称
     * @return Msg
     */
    @RequestMapping("/updateUserStatus/{id}")
    @ResponseBody
    public Msg updateUserStatus(@PathVariable("id") Integer id,
                                @RequestParam("username") String username){
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        if( userService.updateUserStatus(user) == 1){
            onlineUserService.userLogout(username);
            return Msg.success();
        }
        return Msg.failed();
    }

    /**
     * 登出功能
     * @param username 用户名
     * @param response response给浏览器返回消息
     * @throws IOException 抛出异常
     */
    @RequestMapping("/logout")
    public void logout(@RequestParam("username") String username,
                       HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        onlineUserService.userLogout(username);
    }
}
