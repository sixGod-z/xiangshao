package com.zsy.mapper;

import com.zsy.pojo.Question;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface QuestionExtMapper {

    @Select("select * from question limit #{number},#{size}")
    List<Question> selLimitQuestion(Integer number,Integer size);

    @Update("update question set view_Count = view_Count+1 where id = #{id}")
    void incViewCount(Integer id);
}