package com.zsy.service.impl;

import com.zsy.mapper.UserMapper;
import com.zsy.pojo.User;
import com.zsy.pojo.UserExample;
import com.zsy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2020/2/24 15:36
 * @Created by hero
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    //根据cookie查询用户
    @Override
    public User selByToken(String token) {
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(example);
        if (users.size()>0) {
            return users.get(0);
        }
        else {
            return null;
        }
    }

    //创建或者更新用户
    @Override
    public String createOrUpdate(User gitHubUser) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(gitHubUser.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if (users.size()<1) {
            //创建用户
            gitHubUser.setGmtCreate(System.currentTimeMillis());

            userMapper.insert(gitHubUser);

            return gitHubUser.getToken();
        } else {
            //更新用户
            UserExample example1 = new UserExample();
            example1.createCriteria().andAccountIdEqualTo(gitHubUser.getAccountId());

            User user = users.get(0);

            userMapper.updateByExampleSelective(gitHubUser,example);

            example1.createCriteria().andIdEqualTo(user.getId());
            userMapper.selectByExample(example1);
                return user.getToken();
        }
    }

    //根据用户名密码查询用户
    @Override
    public String selByUnameAndPwd(String uname, String pwd) {
        UserExample example = new UserExample();
        example.createCriteria().andUnameEqualTo(uname).andPwdEqualTo(pwd);
        List<User> users = userMapper.selectByExample(example);
        if(users.size() > 0){
            //更新用户
            System.out.println("okok");
            User user =users.get(0);
            user.setToken(UUID.randomUUID().toString());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateByPrimaryKeySelective(user);
            return user.getToken();
        }else {
            System.out.println("用户名密码错误");
            return null;
        }
    }
}
