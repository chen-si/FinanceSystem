package com.heu.finance.controller.normal;

import com.heu.finance.pojo.News;
import com.heu.finance.service.OnlineUserService;
import com.heu.finance.service.normal.NewsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    @RequestMapping("/main")
    public String userMainPage(Model model, HttpServletRequest request){
        List<News> list = newsService.selectAllNews();

        model.addAttribute("newsList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","");
        model.addAttribute("activeUrl2","");

        Subject subject = SecurityUtils.getSubject();

        return "/user/main";
    }

    @RequestMapping("/logout")
    public void logout(@RequestParam("username") String username,
                       HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        onlineUserService.userLogout(username);
    }
}
