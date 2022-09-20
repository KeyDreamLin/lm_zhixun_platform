package com.lm.mapper;

import com.lm.entity.pojo.adminroles.AdminRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Lm
 * @since 2022-09-14
 */
@Mapper
public interface AdminRolesMapper extends BaseMapper<AdminRoles> {
    /**
     * 根据角色id查询对应权限列表
     * @param id
     * @return
     */
    List<String> findPermissionByRoleId(@Param("role_id") Long id);

    /**
     * 根据角色id删除对应权限列表
     * @param id
     * @return
     */
    int deletePermissionByRoleId(@Param("role_id") Long id);

    /**
     * 保存权限列表 角色对应多个权限id
     * @param r_id
     * @param p_id
     * @return
     */
    int savePermissionByRoleId(@Param("role_id") Long r_id,@Param("permission_id") Long p_id);
}
