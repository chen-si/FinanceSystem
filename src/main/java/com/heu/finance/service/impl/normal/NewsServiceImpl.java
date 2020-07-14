package com.heu.finance.service.impl.normal;

import com.heu.finance.mapper.normal.NewsMapper;
import com.heu.finance.pojo.normal.News;
import com.heu.finance.service.normal.NewsService;
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
