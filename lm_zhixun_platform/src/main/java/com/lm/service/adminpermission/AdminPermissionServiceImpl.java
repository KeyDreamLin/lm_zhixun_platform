package com.lm.service.adminpermission;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.pojo.adminmenu.AdminMenu;
import com.lm.entity.pojo.adminpermission.AdminPermission;
import com.lm.entity.vo.adminpermission.AdminPermissionPageVo;
import com.lm.entity.vo.adminpermission.AdminPermissionSaveUpDataVo;
import com.lm.entity.vo.adminpermission.AdminPermissionVo;
import com.lm.entity.bo.adminpermission.AdminPermissionBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 菜单管理  服务类
* @author Lm
* @since 2022-09-14
*/
public interface AdminPermissionServiceImpl extends IService<AdminPermission> {

        /**
        * 查询用户权限管理列表信息
        * @method: findAdminPermissionList
        * @result : List<AdminPermission>
        * 创建人:Lm
        * 创建时间：2022-09-14
        * @version 1.0.0
        * @return
        */
        List<AdminPermissionBo> findAdminPermissionList() ;

        /**
        * 查询用户权限管理列表信息并分页
        * 方法名：findAdminPermission<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param adminpermissionVo
        * @return IPage<AdminPermissionBo><br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        IPage<AdminPermissionBo> findAdminPermissionPage(AdminPermissionPageVo adminpermissionVo);

        /**
        * 保存&修改用户权限管理
        * 方法名：saveupdateAdminPermission<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param adminPermissionSaveUpDataVo
        * @return AdminPermissionBo<br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        AdminPermissionBo saveupdateAdminPermission(AdminPermissionSaveUpDataVo adminPermissionSaveUpDataVo);

        /**
        * 根据Id查询用户权限管理明细信息
        * 方法名：getAdminPermissionById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param id
        * @return AdminPermissionBo <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        AdminPermissionBo getAdminPermissionById(Long id);

        /**
        * 根据Id删除用户权限管理
        * 方法名：deleteAdminPermissionById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param id
        * @return int <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        int deleteAdminPermissionById(Long id) ;

        /**
        * 根据用户权限管理ids批量删除后台权限管理管理
        * 方法名：delBatchAdminPermission<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param ids
        * @return boolean <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        boolean delBatchAdminPermission(String ids);

        /**
         * 查询整个权限表 变转换为tree
         * 查询菜单和权限信息tree
         * @return
         */
        List<AdminPermission> findAdminPermissionTree();

        /**
         * 查询权限表里面的菜单 变转换为tree
         * 查询菜单信息tree
         * @return
         */
        List<AdminPermission> findAdminPermissionsMenuTree();

        /**
         * 根据权限id删除 对应的角色权限中间表
         * @param p_id
         * @return
         */
        int deleteRolesByPermissionId (Long p_id);

}