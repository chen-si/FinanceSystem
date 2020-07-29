package com.heu.finance.controller.normal;

import com.heu.finance.pojo.News;
import com.heu.finance.service.OnlineUserService;
import com.heu.finance.service.NewsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户主页
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class UserMainController {
    private NewsService newsService;
    private OnlineUserService onlineUserService;

    @Autowired
    public void setOnlineUserService(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     * 显示用户主页
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/main")
    public String userMainPage(Model model){
        List<News> list = newsService.selectAllNews();

        model.addAttribute("newsList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","");
        model.addAttribute("activeUrl2","");

        Subject subject = SecurityUtils.getSubject();

        return "/user/main";
    }

    /**
     * 退出登录
     * @param username username
     * @param response response
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
