package com.zsy.mapper;

import com.zsy.dto.PageDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Classname QuestionMapper
 * @Description TODO
 * @Date 2020/2/24 20:22
 * @Created by hero
 */
@Mapper
public interface QuestionMapper2 {
//
//    //添加问题信息
//    @Insert("insert into question value(default,#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
//    void insQuestion(Question question);
//
//    //分页查询所有问题文章
//    @Select("select * from question limit #{number},#{size}")
//    List<Question> selLimitQuestion(PageDto pageDto);
//
//    //查询总数
//    @Select("select Count(*) from question")
//    Integer selAllCount();
//
//    //根据用户id分页查询所有问题文章
//    @Select("select * from question where creator=#{userId} limit #{number},#{size} ")
//    List<Question> selLimitQuestionByUserId(PageDto pageDto);
//
//    //根据用户id查询总数
//    @Select("select Count(*) from question where creator=#{userId}")
//    Integer selAllCountByUserId(Integer userId);
//
//    //根据问题文章id查询问题
//    @Select("select * from question where id = #{id}")
//    Question selQuestionById(Integer id);
//
//    //更新问题
//    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
//    void updQuestion(Question question);
}
