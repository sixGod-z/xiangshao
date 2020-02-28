package com.zsy.controller;

import com.zsy.dto.GitHubUser;
import com.zsy.dto.PageDto;
import com.zsy.dto.TwoCan;
import com.zsy.pojo.User;
import com.zsy.provider.QQProvider;
import com.zsy.service.QuestionService;
import com.zsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Classname LoginController
 * @Description TODO
 * @Date 2020/2/23 15:54
 * @Created by hero
 */
@Controller
public class LoginController {
    @Autowired
    private QQProvider qqProvider;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/index", "/"})
    public String test(Model model,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer size) {
        PageDto pageDto = questionService.selLimitQuestion(page, size);
        System.out.println(pageDto.getAvatarQuestions());
        model.addAttribute("pageDto", pageDto);
        return "index";
    }


    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    public String loginQQ(String code, String state, HttpServletRequest request, HttpServletResponse response) {
        String accessToken = qqProvider.getAccessToken(new TwoCan(clientId, clientSecret, code, redirectUri, state));
        System.out.println(accessToken);
        GitHubUser gitHubUser = qqProvider.getUser(accessToken);
        System.out.println(gitHubUser);
        if (gitHubUser != null) {

            User user = new User();
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setUname(gitHubUser.getLogin());
            user.setToken(UUID.randomUUID().toString());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            user.setGmtModified(System.currentTimeMillis());
            String token = userService.createOrUpdate(user);
            //将token存储到Cookie中
            response.addCookie(new Cookie("token", token));
        }

        return "redirect:/index";
    }

    @GetMapping("/login")
    public String skipLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String uname , String pwd,Model model,
                        HttpServletResponse response){
        System.out.println(uname+"=="+pwd);
        String token = userService.selByUnameAndPwd(uname, pwd);
        if(token!=null){
            response.addCookie(new Cookie("token", token));
            return  "redirect:/index";
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }

    }


    @RequestMapping("logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        response.addCookie(new Cookie("token", ""));
        return "redirect:/index";
    }
}
