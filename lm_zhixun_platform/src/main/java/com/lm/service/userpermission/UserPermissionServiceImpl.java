package com.lm.service.userpermission;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.entity.pojo.UserPermission;
import com.lm.mapper.UserPermissionMapper;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {
}
