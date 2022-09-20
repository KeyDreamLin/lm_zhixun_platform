package com.lm.service.adminuser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.common.anno.login.IgnoreToken;
import com.lm.entity.pojo.adminroles.AdminRoles;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.lm.entity.vo.adminuser.AdminUserQueryVo;
import com.lm.entity.bo.adminuser.AdminUserBo;
import com.lm.entity.vo.adminuser.AdminUserRegVo;
import com.lm.entity.vo.adminuser.AdminUserUpdateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  服务类
 * @author Lm
 * @since 2022-09-08
 */
public interface IAdminUserService extends IService<AdminUser> {

    AdminUser login(String userName);

    /**
     * 获取用户拥有的角色 根据用户id
     * @param id
     * @return
     */
    List<String> getRoleByUserId(Long id);

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
     * 为用户绑定一个角色
     * @param userId
     * @param roleId
     * @return
     */
    boolean bindingRoleByUserId(Long userId,  Long roleId);
    /**
     * 为用户解除绑定一个角色
     * @param userId
     * @param roleId
     * @return
     */
    boolean unbindingRoleByUserId( Long userId,  Long roleId);

    /**
     * 查询后台用户管理管理列表信息并分页
     * 方法名：findAdminUser<br/>
     * 创建人：Lm <br/>
     * 时间：2022-09-08<br/>
     * @param adminuserQueryVo
     * @return IPage<AdminUserBo><br />
     * @throws <br/>
     * @since 1.0.0<br />
     */
    IPage<AdminUserBo> findAdminUserPage(AdminUserQueryVo adminuserQueryVo);

    /**
     * 保存&修改后台用户管理管理
     * 方法名：saveupdateAdminUser<br/>
     * 创建人：Lm <br/>
     * 时间：2022-09-08<br/>
     * @param adminUserRegVo
     * @return AdminUserBo<br />
     * @throws <br/>
     * @since 1.0.0<br />
     */
    AdminUserBo saveupdateAdminUser(AdminUserRegVo adminUserRegVo);

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

    /**
     * 状态更新
     * @param adminUserRegVo
     * @return
     */
    boolean updateAdminUser(AdminUserUpdateVo adminUserRegVo);

    /**
     * 通过admin用户id查询到对应的角色
     * @param id adminUser id
     * @return 对应角色表
     */
    List<String> getRoleNamesByUid(Long id);

    /**
     * 查询用户对应的权限表
     * @param userId
     * @return
     */
    List<String> findByUserPermission(Long userId);
}