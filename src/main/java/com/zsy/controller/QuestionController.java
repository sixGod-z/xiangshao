package com.zsy.controller;

import com.zsy.dto.AvatarQuestion;
import com.zsy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Classname QuestionController
 * @Description TODO
 * @Date 2020/2/26 20:41
 * @Created by hero
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/questionmain/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        questionService.incViewCount(id);
        AvatarQuestion avatarQuestion = questionService.selQuestionById(id);
        model.addAttribute("avatarQuestion",avatarQuestion);
        return "questionmain";
    }
}
