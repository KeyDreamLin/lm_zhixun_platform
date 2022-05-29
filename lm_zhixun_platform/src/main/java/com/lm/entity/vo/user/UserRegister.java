package com.lm.entity.vo.user;

import com.lm.entity.pojo.User;
import com.lm.entity.pojo.UserPermission;

import java.util.List;

/**
 * VO用户注册~带权限
 */
public class UserRegister extends User {
    //用户权限列表~拥有啥权限
    private List<UserPermission> permissionList;
}
