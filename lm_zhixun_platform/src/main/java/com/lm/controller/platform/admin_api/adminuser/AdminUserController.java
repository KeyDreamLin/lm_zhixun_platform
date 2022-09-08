package com.lm.controller.platform.admin_api.adminuser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.common.ex.lthrow.ValidatorExceptionThrow;
import com.lm.common.r.UserResultEnum;
import com.lm.controller.platform.admin_api.BaseController;
import com.lm.entity.bo.adminuser.AdminUserBo;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.lm.entity.vo.adminuser.AdminUserVo;
import com.lm.service.adminuser.IAdminUserService;
import com.lm.tool.LmAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户管理web Api
 * @author Lm
 * @since 2022-09-08
 */
@Controller
@Slf4j
public class AdminUserController extends BaseController {
    @Autowired
    private IAdminUserService adminuserService;

    /**
    * 查询后台用户管理列表信息
    ** @path : /admin/adminuser/load
    * @method: findAdminUserList
    * @result : List<AdminUser>
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @return
    */
    @GetMapping("/adminuser/load")
    @ResponseBody
    public List<AdminUserBo> findadminuserList() {
        return adminuserService.findAdminUserList();
    }


    /**
    * 查询后台用户管理列表信息并分页
    * @path : /admin/adminuser/list
    * @method: findAdminUsers
    * @param : AdminUserVo
    * @result : IPage<AdminUserBo>
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @GetMapping("/adminuser/list")
    @ResponseBody
    public IPage<AdminUserBo> findAdminUsers(@RequestBody AdminUserVo adminuserVo){
        return adminuserService.findAdminUserPage(adminuserVo);
    }

    /**
    * 保存和修改后台用户管理
    * @method: saveupdate
    * @path : /admin/adminuser/save
    * @param : AdminUser
    * @result : AdminUser
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @PostMapping("/adminuser/saveupdate")
    @ResponseBody
    public AdminUserBo saveupdateAdminUser(@RequestBody AdminUser adminuser) {
        return adminuserService.saveupdateAdminUser(adminuser);
    }

    /**
    * 根据后台用户管理id查询明细信息
    * @method: get/{id}
    * @path : /admin/adminuser/get/{id}
    * @param : id
    * @result : AdminUserBo
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @GetMapping("/adminuser/get/{id}")
    @ResponseBody
    public AdminUserBo getAdminUserById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminuserService.getAdminUserById(new Long(id));
    }

    /**
    * 根据后台用户管理id删除后台用户管理
    * @method: delete/{id}
    * @path : /admin/adminuser/delete/{id}
    * @param : id
    * @result : int
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @PostMapping("/adminuser/delete/{id}")
    @ResponseBody
    public int deleteAdminUserById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminuserService.deleteAdminUserById(new Long(id));
    }

    /**
    * 根据后台用户管理ids批量删除后台用户管理
    * @method: adminuser/delBatch
    * @path : /admin/adminuser/delBatch
    * @param : AdminUserVo
    * @result : boolean
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @version 1.0.0
    */
    @PostMapping("/adminuser/delBatch")
    public boolean delAdminUser(@RequestBody AdminUserVo adminuserVo) {
        log.info("你要批量删除的IDS是:{}", adminuserVo.getBatchIds());
        if (LmAssert.isEmpty(adminuserVo.getBatchIds())) {
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return adminuserService.delBatchAdminUser(adminuserVo.getBatchIds());
    }
}
