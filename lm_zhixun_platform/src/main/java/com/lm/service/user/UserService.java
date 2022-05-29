package com.lm.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.pojo.User;

public interface UserService extends IService<User> {
    User queryUserById(Long UserId);
    User login(String userName);
}
