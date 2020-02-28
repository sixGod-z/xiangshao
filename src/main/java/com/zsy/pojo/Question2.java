package com.zsy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Quesion
 * @Description 问题文章表
 * @Date 2020/2/24 20:15
 * @Created by hero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question2 {
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
}
