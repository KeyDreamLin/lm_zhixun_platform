package com.lm.entity.bo;

import com.lm.entity.pojo.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserBo implements Serializable {
    // 存储jwt 登录秘钥
    private String token;
    // 用于下线的UUID
    private String tokenUuid;
    private User user;
}
