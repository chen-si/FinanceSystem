package com.heu.finance.service.impl;

import com.heu.finance.mapper.NewsMapper;
import com.heu.finance.pojo.News;
import com.heu.finance.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private NewsMapper newsMapper;

    @Autowired
    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public List<News> selectAllNews() {
        return newsMapper.selectAllNews();
    }
}
