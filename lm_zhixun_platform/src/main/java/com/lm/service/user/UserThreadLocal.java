package com.lm.service.user;


import com.lm.entity.pojo.User;

public class UserThreadLocal {
    //每次使用完记得删除！不然会泄露
    // 实现一个threadlocal线程副本
    static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    // 添加
    public static void put(User isFlag) {
        userThreadLocal.set(isFlag);
    }

    // 获取
    public static User get() {
        return userThreadLocal.get();
    }

    // 删除
    public static void remove() {
        userThreadLocal.remove();
    }
}
