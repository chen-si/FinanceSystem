package com.heu.finance.controller.normal;

import com.heu.finance.pojo.News;
import com.heu.finance.service.normal.NewsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserMainController {
    private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
    @RequestMapping("/user/main")
    public String userMainPage(Model model, HttpServletRequest request){
        List<News> list = newsService.selectAllNews();

        model.addAttribute("newsList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","");
        model.addAttribute("activeUrl2","");

        Subject subject = SecurityUtils.getSubject();

        return "/user/main";
    }
}
