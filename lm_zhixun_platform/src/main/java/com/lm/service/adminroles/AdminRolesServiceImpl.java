package com.lm.service.adminroles;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.pojo.adminpermission.AdminPermission;
import com.lm.entity.pojo.adminroles.AdminRoles;
import com.lm.entity.vo.adminroles.AdminRolesSaveUpdateVo;
import com.lm.entity.vo.adminroles.AdminRolesVo;
import com.lm.entity.bo.adminroles.AdminRolesBo;
import java.util.List;

/**
*  服务类
* @author Lm
* @since 2022-09-14
*/
public interface AdminRolesServiceImpl extends IService<AdminRoles> {

        /**
        * 查询后台权限管理管理列表信息
        * @method: findAdminRolesList
        * @result : List<AdminRoles>
        * 创建人:Lm
        * 创建时间：2022-09-14
        * @version 1.0.0
        * @return
        */
        List<AdminRolesBo> findAdminRolesList() ;

        /**
        * 查询后台权限管理管理列表信息并分页
        * 方法名：findAdminRoles<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param adminrolesVo
        * @return IPage<AdminRolesBo><br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        IPage<AdminRolesBo> findAdminRolesPage(AdminRolesVo adminrolesVo);

        /**
        * 保存&修改后台权限管理管理
        * 方法名：saveupdateAdminRoles<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param adminRolesSaveUpdateVo
        * @return AdminRolesBo<br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        AdminRolesBo saveupdateAdminRoles(AdminRolesSaveUpdateVo adminRolesSaveUpdateVo);

        /**
        * 根据Id查询后台权限管理管理明细信息
        * 方法名：getAdminRolesById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param id
        * @return AdminRolesBo <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        AdminRolesBo getAdminRolesById(Long id);

        /**
        * 根据Id删除后台权限管理管理
        * 方法名：deleteAdminRolesById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param id
        * @return int <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        int deleteAdminRolesById(Long id) ;

        /**
        * 根据后台权限管理管理ids批量删除后台权限管理管理
        * 方法名：delBatchAdminRoles<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-14<br/>
        * @param ids
        * @return boolean <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        boolean delBatchAdminRoles(String ids);

        /**
         * 根据角色id查询对应权限列表
         * @param id
         * @return
         */
        List<String> findPermissionByRoleId(Long id);

        /**
         * 保存权限列表 角色对应多个权限id
         * @param r_id
         * @param p_idStr
         * @return
         */
        boolean savePermissionByRoleId(Long r_id,String p_idStr);
}