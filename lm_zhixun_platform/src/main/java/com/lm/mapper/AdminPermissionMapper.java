package com.lm.mapper;

import com.lm.entity.pojo.adminpermission.AdminPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单管理  Mapper 接口
 *
 * @author Lm
 * @since 2022-09-14
 */
@Mapper
public interface AdminPermissionMapper extends BaseMapper<AdminPermission> {
    /**
     * 根据权限id删除 对应的角色权限中间表
     * @param p_id
     * @return
     */
    int deleteRolesByPermissionId (@Param("permission_id") Long p_id);
}
