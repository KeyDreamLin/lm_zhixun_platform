package com.lm.service.adminroles;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.pojo.adminroles.AdminRoles;
import com.lm.entity.vo.adminroles.AdminRolesVo;
import com.lm.entity.bo.adminroles.AdminRolesBo;
import java.util.List;

/**
*  服务类
* @author Lm
* @since 2022-09-11
*/
public interface AdminRolesServiceImpl extends IService<AdminRoles> {

        /**
        * 查询后台权限管理管理列表信息
        * @method: findAdminRolesList
        * @result : List<AdminRoles>
        * 创建人:Lm
        * 创建时间：2022-09-11
        * @version 1.0.0
        * @return
        */
        List<AdminRolesBo> findAdminRolesList() ;

        /**
        * 查询后台权限管理管理列表信息并分页
        * 方法名：findAdminRoles<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-11<br/>
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
        * 时间：2022-09-11<br/>
        * @param adminroles
        * @return AdminRolesBo<br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        AdminRolesBo saveupdateAdminRoles(AdminRoles adminroles);

        /**
        * 根据Id查询后台权限管理管理明细信息
        * 方法名：getAdminRolesById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-11<br/>
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
        * 时间：2022-09-11<br/>
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
        * 时间：2022-09-11<br/>
        * @param ids
        * @return boolean <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        boolean delBatchAdminRoles(String ids);

}