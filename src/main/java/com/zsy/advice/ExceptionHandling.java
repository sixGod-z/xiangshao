package com.zsy.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname ExceptionHandling
 * @Description TODO
 * @Date 2020/2/28 11:40
 * @Created by hero
 */
@ControllerAdvice
public class ExceptionHandling {
    //org.thymeleaf.exceptions.TemplateInputException.class

    @ExceptionHandler(java.lang.IllegalArgumentException.class)
    public ModelAndView IllegalArgumentException(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error","您查找的文章已删除或不存在");
        modelAndView.setViewName("error1");
        return modelAndView;
    }
}
