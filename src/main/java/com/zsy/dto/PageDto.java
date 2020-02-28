package com.zsy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname page
 * @Description TODO
 * @Date 2020/2/25 20:50
 * @Created by hero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PageDto {
    private Integer page;//页数
    private Integer size;//显示几个
    private Integer pages;//总页数
    private Integer number;//从第几个开始
    private Integer userId;//根据用户id查询
    private List<AvatarQuestion> avatarQuestions;
}
