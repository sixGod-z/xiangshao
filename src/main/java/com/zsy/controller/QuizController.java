package com.zsy.controller;

import com.zsy.pojo.Question;
import com.zsy.pojo.User;
import com.zsy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname QuestionController
 * @Description TODO
 * @Date 2020/2/24 20:37
 * @Created by hero
 */
@Controller
public class QuizController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/fabu")
    public String skipFabu() {

        return "fabu";
    }

    @PostMapping("/fabu")
    public String insQuestion(Question question, HttpServletRequest request, Model model, Integer id) {
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        if (question.getTitle().equals("")) {
            model.addAttribute("error", "标题不能为空");
            return null;
        }
        if (question.getDescription().equals("")) {
            model.addAttribute("error", "问题补充不能为空");
            return null;
        }
        if (question.getTag().equals("")) {
            model.addAttribute("error", "标签不能为空");
            return null;
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "请先登录。。。");
            return null;
        }
        question.setCreator(user.getId());
        question.setGmtModified(System.currentTimeMillis());
        question.setCommentCount(0);
        question.setViewCount(0);
        question.setLikeCount(0);
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/index";
    }
}
