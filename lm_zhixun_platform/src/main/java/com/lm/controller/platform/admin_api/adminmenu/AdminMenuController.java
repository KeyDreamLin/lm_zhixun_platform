package com.lm.controller.platform.admin_api.adminmenu;

import com.lm.controller.platform.admin_api.BaseController;
import com.lm.entity.pojo.adminmenu.AdminMenu;
import com.lm.service.adminmenu.AdminMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AdminMenuController extends BaseController {
    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 查询后台菜单信息
     * @return
     */
    @PostMapping("/menu/tree")
    public List<AdminMenu> tree() {
        return adminMenuService.findAdminMenuTree();
    }

}
