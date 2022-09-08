package com.lm.entity.bo.adminuser;

import com.lm.entity.pojo.adminuser.AdminUser;
import lombok.Data;

import java.io.Serializable;

/**
 * 管理员后台登录回调
 */

@Data
public class AdminUserTokenBo implements Serializable {
    // 存储jwt 登录秘钥
    private String tokenJj;
    // 用于下线的UUID
    private String tokenUuid;
    //  登录的用户信息
    private AdminUser user;
}
