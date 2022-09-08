package com.lm.service.adminuser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.lm.entity.vo.adminuser.AdminUserVo;
import com.lm.entity.bo.adminuser.AdminUserBo;
import java.util.List;

/**
*  服务类
* @author Lm
* @since 2022-09-08
*/
public interface IAdminUserService extends IService<AdminUser> {

        /**
        * 查询后台用户管理管理列表信息
        * @method: findAdminUserList
        * @result : List<AdminUser>
        * 创建人:Lm
        * 创建时间：2022-09-08
        * @version 1.0.0
        * @return
        */
        List<AdminUserBo> findAdminUserList() ;

        /**
        * 查询后台用户管理管理列表信息并分页
        * 方法名：findAdminUser<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param adminuserVo
        * @return IPage<AdminUserBo><br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        IPage<AdminUserBo> findAdminUserPage(AdminUserVo adminuserVo);

        /**
        * 保存&修改后台用户管理管理
        * 方法名：saveupdateAdminUser<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param adminuser
        * @return AdminUserBo<br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        AdminUserBo saveupdateAdminUser(AdminUser adminuser);

        /**
        * 根据Id查询后台用户管理管理明细信息
        * 方法名：getAdminUserById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param id
        * @return AdminUserBo <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        AdminUserBo getAdminUserById(Long id);

        /**
        * 根据Id删除后台用户管理管理
        * 方法名：deleteAdminUserById<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param id
        * @return int <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        int deleteAdminUserById(Long id) ;

        /**
        * 根据后台用户管理管理ids批量删除后台用户管理管理
        * 方法名：delBatchAdminUser<br/>
        * 创建人：Lm <br/>
        * 时间：2022-09-08<br/>
        * @param ids
        * @return boolean <br />
        * @throws <br/>
        * @since 1.0.0<br />
        */
        boolean delBatchAdminUser(String ids);

}