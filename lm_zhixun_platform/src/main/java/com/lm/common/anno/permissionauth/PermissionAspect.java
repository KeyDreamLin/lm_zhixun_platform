package com.lm.common.anno.permissionauth;

import com.lm.common.ex.lthrow.ValidatorExceptionThrow;
import com.lm.common.r.UserResultEnum;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.lm.service.adminuser.AdminUserThreadLocal;
import com.lm.service.adminuser.IAdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 权限验证注解实现
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor // 用于生成构造方法的 Lombok
public class PermissionAspect {

    private final IAdminUserService adminuserService;

    @Pointcut("@annotation(com.lm.common.anno.permissionauth.HasAuth)")
    public void poinitCut(){}

    @Before("poinitCut()")
    public void beforeAdvice(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取执行的方法
        Method method = signature.getMethod();
        // 获取方法的注解
        HasAuth hasAuth = method.getAnnotation(HasAuth.class);
        // 获取权限代号
        String[] codes = hasAuth.value();
        // 然后根据登录的用户去获取对应的权限信息
        AdminUser adminUser = AdminUserThreadLocal.get();
        // 查询用户所有的权限信息 追求实时
        List<String> permissions = adminuserService.findByUserPermission(adminUser.getId());
        // 用户输入
        List<String> strings1 = Arrays.asList(codes);
        //取交集
        Collection intersection = CollectionUtils.intersection(permissions, strings1);
        // 如果没有权限直接抛出异常
        if(CollectionUtils.isEmpty(intersection)){
            throw new ValidatorExceptionThrow(UserResultEnum.USER_ROLE_AUTH_EMPTY);
        }
    }
}
