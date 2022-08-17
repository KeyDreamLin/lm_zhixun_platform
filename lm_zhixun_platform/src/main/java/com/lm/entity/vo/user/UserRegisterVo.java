package com.lm.entity.vo.user;

import lombok.Data;

/**
 * Vo注册
 */
@Data
public class UserRegisterVo {

    // 用户姓名
    private String username;
    // 昵称
    private String nickname;
    // 密码
    private String password;
    // 性别
    private Integer sex;
    // 朋友们
    private String[] friends;

}
