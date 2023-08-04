package com.example.demo1.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo1.dao.Tags;
import com.example.demo1.dao.dyhTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagsMapper extends BaseMapper<Tags> {
    List<Tags> selectAll();


}
