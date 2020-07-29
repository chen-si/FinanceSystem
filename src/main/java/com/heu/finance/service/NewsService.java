package com.heu.finance.service;

import com.heu.finance.pojo.News;

import java.util.List;

public interface NewsService {
    List<News> selectAllNews();
}
