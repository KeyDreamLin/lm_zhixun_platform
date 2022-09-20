package com.lm.controller.platform.admin_api.adminroles;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.common.anno.login.IgnoreToken;
import com.lm.common.anno.permissionauth.HasAuth;
import com.lm.entity.vo.adminroles.AdminRolesSavePermissionVo;
import com.lm.entity.vo.adminroles.AdminRolesSaveUpdateVo;
import com.lm.service.adminroles.IAdminRolesService;
import com.lm.entity.vo.adminroles.AdminRolesVo;
import com.lm.entity.bo.adminroles.AdminRolesBo;
import com.lm.controller.platform.admin_api.BaseController;
import com.lm.common.ex.lthrow.ValidatorExceptionThrow;
import com.lm.common.r.UserResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import com.lm.tool.LmAssert;

/**
 * 后台角色管理web Api
 * @author Lm
 * @since 2022-09-14
 */
@Controller
@Slf4j
public class AdminRolesController extends BaseController {
    @Autowired
    private IAdminRolesService adminrolesService;

    /**
    * 查询后台角色管理列表信息
    ** @path : /admin/adminroles/load
    * @method: findAdminRolesList
    * @result : List<AdminRoles>
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @return
    */
    @PostMapping("/adminroles/load")
    @ResponseBody
    public List<AdminRolesBo> findadminrolesList() {
        return adminrolesService.findAdminRolesList();
    }


    /**
    * 查询后台角色管理列表信息并分页
    * @path : /admin/adminroles/list
    * @method: findAdminRoless
    * @param : AdminRolesVo
    * @result : IPage<AdminRolesBo>
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminroles/list")
    @ResponseBody
    @HasAuth("130111")
    public IPage<AdminRolesBo> findAdminRolesPage(@RequestBody AdminRolesVo adminrolesVo){
        return adminrolesService.findAdminRolesPage(adminrolesVo);
    }

    /**
    * 保存和修改后台角色管理
    * @method: saveupdate
    * @path : /admin/adminroles/save
    * @param : adminrolesVo
    * @result : AdminRolesBo
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminroles/saveupdate")
    @ResponseBody
    public AdminRolesBo saveupdateAdminRoles(@RequestBody AdminRolesSaveUpdateVo adminRolesSaveUpdateVo) {
        return adminrolesService.saveupdateAdminRoles(adminRolesSaveUpdateVo);
    }

    /**
    * 根据后台角色管理id查询明细信息
    * @method: get/{id}
    * @path : /admin/adminroles/get/{id}
    * @param : id
    * @result : AdminRolesBo
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminroles/get/{id}")
    @ResponseBody
    public AdminRolesBo getAdminRolesById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminrolesService.getAdminRolesById(new Long(id));
    }

    /**
    * 根据后台角色管理id删除后台权限管理
    * @method: delete/{id}
    * @path : /admin/adminroles/delete/{id}
    * @param : id
    * @result : int
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminroles/delete/{id}")
    @ResponseBody
    public int deleteAdminRolesById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminrolesService.deleteAdminRolesById(new Long(id));
    }

    /**
    * 根据后台角色管理ids批量删除后台权限管理
    * @method: adminroles/delBatch
    * @path : /admin/adminroles/delBatch
    * @param : AdminRolesVo
    * @result : boolean
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminroles/delBatch")
    public boolean delAdminRoles(@RequestBody AdminRolesVo adminrolesVo) {
        log.info("你要批量删除的IDS是:{}", adminrolesVo.getBatchIds());
        if (LmAssert.isEmpty(adminrolesVo.getBatchIds())) {
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminrolesService.delBatchAdminRoles(adminrolesVo.getBatchIds());
    }

    /**
     * 根据角色id查询对应权限列表 ["code1","code2"。。。]
     * @param adminRolesSavePermissionVo
     * @return List<Long> PermissionList
     */
    @PostMapping("/adminroles/findPermissionByRoleId")
    public List<String> findPermissionByRoleId(@RequestBody AdminRolesSavePermissionVo adminRolesSavePermissionVo){

        LmAssert.isNotNull(adminRolesSavePermissionVo.getRole_id(),UserResultEnum.ID_NOT_EMPTY);
        // 根据角色id查询对应的权限
        List<String> PermissionList = adminrolesService.findPermissionByRoleId(new Long(adminRolesSavePermissionVo.getRole_id()));

        return PermissionList;
    }

    /**
     * 保存权限列表 角色对应多个权限id
     * @param adminRolesSavePermissionVo
     * @return boolean
     */
    @PostMapping("/adminroles/savePermissionByRoleId")
    @IgnoreToken
    public boolean savePermissionByRoleId(@RequestBody AdminRolesSavePermissionVo adminRolesSavePermissionVo){
        LmAssert.isNotNull(adminRolesSavePermissionVo.getRole_id(),UserResultEnum.ID_NOT_EMPTY);
        LmAssert.isEmptyEx(adminRolesSavePermissionVo.getPermission_idStr(),UserResultEnum.USER_INPUT_NULL_ERROR);
        return this.adminrolesService.savePermissionByRoleId(
                new Long(adminRolesSavePermissionVo.getRole_id()),
                adminRolesSavePermissionVo.getPermission_idStr()
        );
    }
}
