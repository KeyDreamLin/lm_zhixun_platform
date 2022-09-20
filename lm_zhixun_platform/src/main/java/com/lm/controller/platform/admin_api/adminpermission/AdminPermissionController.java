package com.lm.controller.platform.admin_api.adminpermission;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.entity.vo.adminpermission.AdminPermissionPageVo;
import com.lm.entity.vo.adminpermission.AdminPermissionSaveUpDataVo;
import com.lm.service.adminpermission.IAdminPermissionService;
import com.lm.entity.pojo.adminpermission.AdminPermission;
import com.lm.entity.vo.adminpermission.AdminPermissionVo;
import com.lm.entity.bo.adminpermission.AdminPermissionBo;
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
 * 后台权限管理web Api
 * @author Lm
 * @since 2022-09-14
 */
@Controller
@Slf4j
public class AdminPermissionController extends BaseController {
    @Autowired
    private IAdminPermissionService adminpermissionService;

//    /**
//    * 查询后台权限管理列表信息
//    ** @path : /admin/adminpermission/load
//    * @method: findAdminPermissionList
//    * @result : List<AdminPermission>
//    * 创建人:Lm
//    * 创建时间：2022-09-14
//    * @return
//    */
//    @PostMapping("/adminpermission/load")
//    @ResponseBody
//    public List<AdminPermissionBo> findadminpermissionList() {
//        return adminpermissionService.findAdminPermissionList();
//    }
//
//    /**
//    * 查询后台权限管理列表信息并分页
//    * @path : /admin/adminpermission/list
//    * @method: findAdminPermissions
//    * @param : AdminPermissionVo
//    * @result : IPage<AdminPermissionBo>
//    * 创建人:Lm
//    * 创建时间：2022-09-14
//    * @version 1.0.0
//    */
//    @PostMapping("/adminpermission/list")
//    @ResponseBody
//    public IPage<AdminPermissionBo> findAdminPermissions(@RequestBody AdminPermissionPageVo adminPermissionPageVo){
//        return adminpermissionService.findAdminPermissionPage(adminPermissionPageVo);
//    }

    /**
    * 保存和修改后台权限管理
    * @method: saveupdate
    * @path : /admin/adminpermission/save
    * @param : adminpermissionVo
    * @result : AdminPermissionBo
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminpermission/saveupdate")
    @ResponseBody
    public AdminPermissionBo saveupdateAdminPermission(@RequestBody AdminPermissionSaveUpDataVo adminPermissionSaveUpDataVo) {
        return adminpermissionService.saveupdateAdminPermission(adminPermissionSaveUpDataVo);
    }

    /**
    * 根据后台权限管理id查询明细信息
    * @method: get/{id}
    * @path : /admin/adminpermission/get/{id}
    * @param : id
    * @result : AdminPermissionBo
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminpermission/get/{id}")
    @ResponseBody
    public AdminPermissionBo getAdminPermissionById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminpermissionService.getAdminPermissionById(new Long(id));
    }

    /**
    * 根据后台权限管理id删除后台权限管理
    * @method: delete/{id}
    * @path : /admin/adminpermission/delete/{id}
    * @param : id
    * @result : int
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminpermission/delete/{id}")
    @ResponseBody
    public int deleteAdminPermissionById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminpermissionService.deleteAdminPermissionById(new Long(id));
    }

    /**
    * 根据后台权限管理ids批量删除后台权限管理
    * @method: adminpermission/delBatch
    * @path : /admin/adminpermission/delBatch
    * @param : AdminPermissionVo
    * @result : boolean
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @version 1.0.0
    */
    @PostMapping("/adminpermission/delBatch")
    public boolean delAdminPermission(@RequestBody AdminPermissionVo adminpermissionVo) {
        log.info("你要批量删除的IDS是:{}", adminpermissionVo.getBatchIds());
        if (LmAssert.isEmpty(adminpermissionVo.getBatchIds())) {
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminpermissionService.delBatchAdminPermission(adminpermissionVo.getBatchIds());
    }

    /**
     * 查询整个权限表 变转换为tree
     * 查询菜单和权限信息tree
     * @return
     */
    @PostMapping("/adminpermission/tree")
    public List<AdminPermission> permissionsTree(){
        return adminpermissionService.findAdminPermissionTree();
    }

    /**
     * 查询权限表里面的菜单 变转换为tree
     * 查询菜单信息tree
     * @return
     */
    @PostMapping("/adminpermission/menu/tree")
    public List<AdminPermission> permissionsMenuTree(){
        return adminpermissionService.findAdminPermissionsMenuTree();
    }

}
