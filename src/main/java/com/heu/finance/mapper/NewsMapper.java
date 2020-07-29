package com.heu.finance.mapper;

import com.heu.finance.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface NewsMapper {
    List<News> selectAllNews();
}
