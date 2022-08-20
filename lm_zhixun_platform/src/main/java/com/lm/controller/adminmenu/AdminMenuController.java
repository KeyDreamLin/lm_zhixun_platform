package com.lm.controller.adminmenu;

import com.lm.controller.BaseController;
import com.lm.entity.bo.banner.BannerBo;
import com.lm.entity.pojo.AdminMenu;
import com.lm.service.adminmenu.AdminMenuService;
import com.lm.service.banner.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
