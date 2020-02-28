package com.zsy.service;

import com.zsy.pojo.User;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2020/2/24 15:34
 * @Created by hero
 */
public interface UserService {

    //根据cookie查询用户
    User selByToken(String token);

    //创建或者更新用户
    String createOrUpdate(User gitHubUser);

    //根据用户名密码查询用户
    String selByUnameAndPwd(String uname, String pwd);
}
