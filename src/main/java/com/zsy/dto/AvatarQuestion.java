package com.zsy.dto;

import com.zsy.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname AvatarQuestion
 * @Description TODO
 * @Date 2020/2/25 13:21
 * @Created by hero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarQuestion {
    private Integer id;
    private String title;//标题
    private String description;//内容
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;//创建人id
    private Integer commentCount;//评论数
    private Integer viewCount;//阅读数
    private Integer likeCount;//点赞数
    private String tag;//标签
    private Integer pages;//总页数
    private User user;
}
