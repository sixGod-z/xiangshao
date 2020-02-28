package com.zsy.controller;

import com.zsy.dto.AvatarQuestion;
import com.zsy.dto.PageDto;
import com.zsy.pojo.User;
import com.zsy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ProfileControlle
 * @Description TODO
 * @Date 2020/2/26 16:27
 * @Created by hero
 */
@Controller
public class ProfileControlle {
    @Autowired
    private QuestionService questionService;

    //点击编辑
    @RequestMapping("/profile/compile/{id}")
    public String compileMain(@PathVariable(name = "id") Integer id,Model model){

        AvatarQuestion avatarQuestion = questionService.selQuestionById(id);

        model.addAttribute("title", avatarQuestion.getTitle());
        model.addAttribute("description", avatarQuestion.getDescription());
        model.addAttribute("tag", avatarQuestion.getTag());
        model.addAttribute("id",avatarQuestion.getId());
        return "fabu";
        }

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model,
                          HttpServletRequest request,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "5") Integer size) {


            if (action.equals("question")) {
                model.addAttribute("sectionName", "我的提问");
                model.addAttribute("section", "question");
                User user = (User)request.getSession().getAttribute("user");
                if(user!=null){
                    PageDto pageDto = questionService.selLimitQuestionByUserId(user.getId(),page, size);
                    model.addAttribute("pageDto", pageDto);
                }
            } else if (action.equals("revert")) {
                model.addAttribute("sectionName", "最新回复");
                model.addAttribute("section", "revert");
            }
            return "profile";
        }
    }
