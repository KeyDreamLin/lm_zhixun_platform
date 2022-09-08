package com.lm.service.adminmenu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.pojo.adminmenu.AdminMenu;

import java.util.List;

public interface AdminMenuService extends IService<AdminMenu> {
    /**
     * 查询admin菜单树
     * @return
     */
    List<AdminMenu> findAdminMenuTree();
}
