package com.lm.entity.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vo登录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    // 用户姓名
    private String username;
    // 密码
    private String password;
    // 验证码
    private String code;
    // 验证码UUID
    private  String codeuuid;
}
