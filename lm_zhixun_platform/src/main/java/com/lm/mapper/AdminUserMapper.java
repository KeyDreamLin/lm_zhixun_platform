package com.lm.mapper;

import com.lm.entity.pojo.adminroles.AdminRoles;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Lm
 * @since 2022-09-08
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    /**
     * 查询用户是否已经绑定角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    int countUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);


    /**
     * 保存用户角色信息
     *
     * @param userId
     * @param roleId
     * @return
     */
    int saveUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 删除用户角色信息
     *
     * @param userId
     * @param roleId
     * @return
     */
    int delUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 通过admin用户id查询到对应的角色
     * @param id adminUser id
     * @return 对应角色表
     */
    List<AdminRoles> findUserRolesByUid(@Param("userId") Long id);

    /**
     * 查询用户对应的权限表
     * @param userId
     * @return
     */
    List<String> findByUserPermission(@Param("userId") Long userId);

    /**
     * 查询用户拥有的角色 根据用户id
     * @param userId
     * @return
     */
    List<String> findRoleByUserId(@Param("userId") Long userId);

}
