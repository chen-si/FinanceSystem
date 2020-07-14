package com.heu.finance.mapper.normal;

import com.heu.finance.pojo.normal.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface NewsMapper {
    List<News> selectAllNews();
}
