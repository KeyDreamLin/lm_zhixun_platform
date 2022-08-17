package com.lm.entity.bo;

import com.lm.entity.pojo.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserTokenBo implements Serializable {
    // 存储jwt 登录秘钥
    private String tokenJj;
    // 用于下线的UUID
    private String tokenUuid;
    //  登录的用户信息
    private User user;
}
