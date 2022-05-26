package com.lm.service.userpermission;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.mapper.UserPermissionMapper;
import com.lm.pojo.UserPermission;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {
}
