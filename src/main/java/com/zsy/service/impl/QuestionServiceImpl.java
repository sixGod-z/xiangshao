package com.zsy.service.impl;

import com.zsy.dto.AvatarQuestion;
import com.zsy.dto.PageDto;
import com.zsy.mapper.QuestionExtMapper;
import com.zsy.mapper.QuestionMapper;
import com.zsy.mapper.UserMapper;
import com.zsy.pojo.Question;
import com.zsy.pojo.QuestionExample;
import com.zsy.pojo.User;
import com.zsy.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname QuestionServiceImpl
 * @Description TODO
 * @Date 2020/2/24 20:32
 * @Created by hero
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    //分页查询全部问题
    @Override
    public PageDto selLimitQuestion(Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        Integer count = (int)questionMapper.countByExample(new QuestionExample());
        Integer pages = count%size==0 ? count/size : count/size+1;
        pageDto.setPages(pages);
        pageDto.setSize(size);

        //判断用户输入的页数是否合法
        if(page>pageDto.getPages()){
            pageDto.setPage(pageDto.getPages());
        }else{
            pageDto.setPage(page);
        }
        pageDto.setNumber(size*(pageDto.getPage()-1));
        List<Question> questions = questionExtMapper.selLimitQuestion(pageDto.getNumber(), size);
        List<AvatarQuestion> list = new ArrayList<>();

        if(questions!=null) {
            for (Question question : questions) {
                AvatarQuestion avatarQuestion = new AvatarQuestion();
                //spring提供的一个工具类，将一个question类的所有属性都copy到avatarQuestion类中
                BeanUtils.copyProperties(question,avatarQuestion);
                avatarQuestion.setUser(userMapper.selectByPrimaryKey(question.getCreator()));
                list.add(avatarQuestion);
            }
            pageDto.setAvatarQuestions(list);
            return pageDto;
        }
        return null;
    }

    //根据用户id分页查询问题
    @Override
    public PageDto selLimitQuestionByUserId(Integer userId, Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer count =  (int)questionMapper.countByExample(questionExample);

        Integer pages = count%size==0 ? count/size : count/size+1;
        pageDto.setPages(pages);
        pageDto.setSize(size);
        pageDto.setUserId(userId);
        //判断用户输入的页数是否合法
        if(page<=0){
            pageDto.setPage(1);
        }else if(page>pageDto.getPages()){
            pageDto.setPage(pageDto.getPages());
        }else{
            pageDto.setPage(page);
        }
        pageDto.setNumber(size*(pageDto.getPage()-1));
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(pageDto.getNumber(), size));

        List<AvatarQuestion> list = new ArrayList<>();

        if(questions!=null) {
            for (Question question : questions) {
                AvatarQuestion avatarQuestion = new AvatarQuestion();
                //spring提供的一个工具类，将一个question类的所有属性都copy到avatarQuestion类中
                BeanUtils.copyProperties(question,avatarQuestion);
                avatarQuestion.setUser(userMapper.selectByPrimaryKey(question.getCreator()));
                list.add(avatarQuestion);
            }
            pageDto.setAvatarQuestions(list);
            return pageDto;
        }

        return null;
    }

    //根据问题文章id查询问题
    @Override
    public AvatarQuestion selQuestionById(Integer id) {
        AvatarQuestion avatarQuestion = new AvatarQuestion();

        Question question = questionMapper.selectByPrimaryKey(id);

        BeanUtils.copyProperties(question,avatarQuestion);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        avatarQuestion.setUser(user);
        return avatarQuestion;
    }

    //创建或者更新文章

    @Override
    public void createOrUpdate(Question question) {
        if(question.getId()!=null){
            //更新
            questionMapper.updateByPrimaryKeySelective(question);

        }else{
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.insert(question);
        }
    }

    //增加阅读数
    @Override
    public void incViewCount(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question!=null) {
            questionExtMapper.incViewCount(id);
        }
    }
}
