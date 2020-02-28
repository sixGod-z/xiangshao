package com.zsy.service;

import com.zsy.dto.AvatarQuestion;
import com.zsy.dto.PageDto;
import com.zsy.pojo.Question;

/**
 * @Classname QuestionService
 * @Description TODO
 * @Date 2020/2/27 23:10
 * @Created by hero
 */
public interface QuestionService {

    PageDto selLimitQuestion(Integer page, Integer size);

    PageDto selLimitQuestionByUserId(Integer userId, Integer page, Integer size);

    AvatarQuestion selQuestionById(Integer id);

    void createOrUpdate(Question question);

    void incViewCount(Integer id);
}
