package com.zsy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname QQUSer
 * @Description TODO
 * @Date 2020/2/23 17:30
 * @Created by hero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubUser {
    private long id ;
    private String login;
    private String bio;
    private String avatarUrl;
}
