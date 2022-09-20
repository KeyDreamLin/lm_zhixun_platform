package com.lm.service.adminuser;


import com.lm.entity.pojo.adminuser.AdminUser;

// 线程共享 用于验证
public class AdminUserThreadLocal {
    //每次使用完记得删除！不然会泄露
    // 实现一个threadlocal线程副本
    static ThreadLocal<AdminUser> userThreadLocal = new ThreadLocal<>();

    // 添加
    public static void put(AdminUser isFlag) {
        userThreadLocal.set(isFlag);
    }

    // 获取
    public static AdminUser get() {
        return userThreadLocal.get();
    }

    // 删除
    public static void remove() {
        userThreadLocal.remove();
    }
}
