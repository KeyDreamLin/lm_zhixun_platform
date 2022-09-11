package com.lm.controller.platform.admin_api.adminroles;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.mapper.AdminRolesMapper;
import com.lm.service.adminroles.AdminRolesServiceImpl;
import com.lm.entity.pojo.adminroles.AdminRoles;
import com.lm.entity.vo.adminroles.AdminRolesVo;
import com.lm.entity.bo.adminroles.AdminRolesBo;
import com.lm.controller.platform.admin_api.BaseController;
import com.lm.common.ex.lthrow.ValidatorExceptionThrow;
import com.lm.common.r.UserResultEnum;
import com.lm.service.adminroles.IAdminRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.lm.tool.LmAssert;

/**
 * 后台权限管理web Api
 * @author Lm
 * @since 2022-09-11
 */
@Controller
@Slf4j
public class AdminRolesController extends BaseController {
    @Autowired
    private IAdminRolesService adminrolesService;

    /**
    * 查询后台权限管理列表信息
    ** @path : /admin/adminroles/load
    * @method: findAdminRolesList
    * @result : List<AdminRoles>
    * 创建人:Lm
    * 创建时间：2022-09-11
    * @return
    */
    @GetMapping("/adminroles/load")
    @ResponseBody
    public List<AdminRolesBo> findadminrolesList() {
        return adminrolesService.findAdminRolesList();
    }


    /**
    * 查询后台权限管理列表信息并分页
    * @path : /admin/adminroles/list
    * @method: findAdminRoless
    * @param : AdminRolesVo
    * @result : IPage<AdminRolesBo>
    * 创建人:Lm
    * 创建时间：2022-09-11
    * @version 1.0.0
    */
    @GetMapping("/adminroles/list")
    @ResponseBody
    public IPage<AdminRolesBo> findAdminRoless(@RequestBody AdminRolesVo adminrolesVo){
        return adminrolesService.findAdminRolesPage(adminrolesVo);
    }

    /**
    * 保存和修改后台权限管理
    * @method: saveupdate
    * @path : /admin/adminroles/save
    * @param : AdminRoles
    * @result : AdminRoles
    * 创建人:Lm
    * 创建时间：2022-09-11
    * @version 1.0.0
    */
    @PostMapping("/adminroles/saveupdate")
    @ResponseBody
    public AdminRolesBo saveupdateAdminRoles(@RequestBody AdminRoles adminroles) {
        return adminrolesService.saveupdateAdminRoles(adminroles);
    }

    /**
    * 根据后台权限管理id查询明细信息
    * @method: get/{id}
    * @path : /admin/adminroles/get/{id}
    * @param : id
    * @result : AdminRolesBo
    * 创建人:Lm
    * 创建时间：2022-09-11
    * @version 1.0.0
    */
    @GetMapping("/adminroles/get/{id}")
    @ResponseBody
    public AdminRolesBo getAdminRolesById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminrolesService.getAdminRolesById(new Long(id));
    }

    /**
    * 根据后台权限管理id删除后台权限管理
    * @method: delete/{id}
    * @path : /admin/adminroles/delete/{id}
    * @param : id
    * @result : int
    * 创建人:Lm
    * 创建时间：2022-09-11
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
    * 根据后台权限管理ids批量删除后台权限管理
    * @method: adminroles/delBatch
    * @path : /admin/adminroles/delBatch
    * @param : AdminRolesVo
    * @result : boolean
    * 创建人:Lm
    * 创建时间：2022-09-11
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
}
