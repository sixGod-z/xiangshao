package com.zsy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname TwoCan
 * @Description QQ回调后再次访问所需要的参数
 * @Date 2020/2/23 16:46
 * @Created by hero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoCan {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
