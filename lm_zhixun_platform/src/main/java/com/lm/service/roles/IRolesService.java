package com.lm.service.roles;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.pojo.roles.Roles;
import com.lm.entity.vo.roles.RolesVo;
import com.lm.entity.bo.roles.RolesBo;
import java.util.List;

/**
*  服务类
* @author Lm
* @since 2022-09-08
*/
public interface IRolesService extends IService<Roles> {

        /**
        * 查询用户权限管理管理列表信息
        * @method: findRolesList
        * @result : List<Roles>
        * 创建人:Lm
        * 创建时间：2022-09-08
        * @version 1.0.0
        * @return
        */
        List<RolesBo> findRolesList() ;

        /**
        * 查询用户权限管理管理列表信息并分页
        * 方法名：findRoles<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param rolesVo
        * @return IPage<RolesBo><br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        IPage<RolesBo> findRolesPage(RolesVo rolesVo);

        /**
        * 保存&修改用户权限管理管理
        * 方法名：saveupdateRoles<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param roles
        * @return RolesBo<br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        RolesBo saveupdateRoles(Roles roles);

        /**
        * 根据Id查询用户权限管理管理明细信息
        * 方法名：getRolesById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param id
        * @return RolesBo <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        RolesBo getRolesById(Long id);

        /**
        * 根据Id删除用户权限管理管理
        * 方法名：deleteRolesById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param id
        * @return int <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        int deleteRolesById(Long id) ;

        /**
        * 根据用户权限管理管理ids批量删除用户权限管理管理
        * 方法名：delBatchRoles<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param ids
        * @return boolean <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        boolean delBatchRoles(String ids);

}