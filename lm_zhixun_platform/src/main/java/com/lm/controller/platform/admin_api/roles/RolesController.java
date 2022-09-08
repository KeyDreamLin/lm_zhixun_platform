package com.lm.controller.platform.admin_api.roles;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.service.roles.IRolesService;
import com.lm.entity.pojo.roles.Roles;
import com.lm.entity.vo.roles.RolesVo;
import com.lm.entity.bo.roles.RolesBo;
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
 * 用户权限管理web Api
 * @author Lm
 * @since 2022-09-08
 */
@Controller
@Slf4j
public class RolesController extends BaseController {
    @Autowired
    private IRolesService rolesService;

    /**
    * 查询用户权限管理列表信息
    ** @path : /admin/roles/load
    * @method: findRolesList
    * @result : List<Roles>
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @return
    */
    @GetMapping("/roles/load")
    @ResponseBody
    public List<RolesBo> findrolesList() {
        return rolesService.findRolesList();
    }


    /**
    * 查询用户权限管理列表信息并分页
    * @path : /admin/roles/list
    * @method: findRoless
    * @param : RolesVo
    * @result : IPage<RolesBo>
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @GetMapping("/roles/list")
    @ResponseBody
    public IPage<RolesBo> findRoless(@RequestBody RolesVo rolesVo){
        return rolesService.findRolesPage(rolesVo);
    }

    /**
    * 保存和修改用户权限管理
    * @method: saveupdate
    * @path : /admin/roles/save
    * @param : Roles
    * @result : Roles
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @PostMapping("/roles/saveupdate")
    @ResponseBody
    public RolesBo saveupdateRoles(@RequestBody Roles roles) {
        return rolesService.saveupdateRoles(roles);
    }

    /**
    * 根据用户权限管理id查询明细信息
    * @method: get/{id}
    * @path : /admin/roles/get/{id}
    * @param : id
    * @result : RolesBo
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @GetMapping("/roles/get/{id}")
    @ResponseBody
    public RolesBo getRolesById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return rolesService.getRolesById(new Long(id));
    }

    /**
    * 根据用户权限管理id删除用户权限管理
    * @method: delete/{id}
    * @path : /admin/roles/delete/{id}
    * @param : id
    * @result : int
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @PostMapping("/roles/delete/{id}")
    @ResponseBody
    public int deleteRolesById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return rolesService.deleteRolesById(new Long(id));
    }

    /**
    * 根据用户权限管理ids批量删除用户权限管理
    * @method: roles/delBatch
    * @path : /admin/roles/delBatch
    * @param : RolesVo
    * @result : boolean
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @PostMapping("/roles/delBatch")
    public boolean delRoles(@RequestBody RolesVo rolesVo) {
        log.info("你要批量删除的IDS是:{}", rolesVo.getBatchIds());
        if (LmAssert.isEmpty(rolesVo.getBatchIds())) {
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return rolesService.delBatchRoles(rolesVo.getBatchIds());
    }
}
