package com.heu.finance.controller.normal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.pojo.admin.userinfo.User;
import com.heu.finance.pojo.normal.News;
import com.heu.finance.service.normal.NewsService;
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

        return "/user/main";
    }
}
