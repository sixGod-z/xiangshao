package com.zsy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname User
 * @Description TODO
 * @Date 2020/2/24 15:15
 * @Created by hero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User2 {
    private Integer id;
    private String accountId;//GitHubid
    private String name;
    private String token;//Cookie
    private long gmtCreate;
    private long gmtModified;
    private String avatarUrl;

}
