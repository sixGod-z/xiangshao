package com.zsy.provider;

import com.alibaba.fastjson.JSON;
import com.zsy.dto.GitHubUser;
import com.zsy.dto.TwoCan;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Classname QQProvider
 * @Description 使用QQ登录所需要提供的一些方法
 * @Date 2020/2/23 16:58
 * @Created by hero
 */
@Component
public class QQProvider {

    //获取访问令牌
    public String getAccessToken(TwoCan twoCan){
         MediaType mediaType= MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(twoCan), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String msg = response.body().string().split("&")[0].split("=")[1];
            return msg;
        } catch (IOException e) {
        }
        return null;
    }

    //通过访问令牌获取qq用户信息
    public GitHubUser getUser(String AccessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+AccessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String msg = response.body().string();
            /*
            将JSON格式的String类型的自动转换成Java的类对象
            该类对象有而json没有的属性，将会赋值为null
            而类对象没有json中有的属性，将不做任何处理
             */

            GitHubUser gitHubUser = JSON.parseObject(msg, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
