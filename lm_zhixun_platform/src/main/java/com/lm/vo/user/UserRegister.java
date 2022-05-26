package com.lm.vo.user;

import com.lm.pojo.User;
import com.lm.pojo.UserPermission;

import java.util.List;

/**
 * VO用户注册~带权限
 */
public class UserRegister extends User {
    //用户权限列表~拥有啥权限
    private List<UserPermission> permissionList;
}
