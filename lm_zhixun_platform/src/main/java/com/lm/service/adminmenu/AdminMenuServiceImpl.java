package com.lm.service.adminmenu;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lm.entity.pojo.adminmenu.AdminMenu;
import com.lm.mapper.AdminMenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuServiceImpl extends ServiceImpl<AdminMenuMapper, AdminMenu> implements AdminMenuService {

    /**
     * 查询admin菜单树
     * @return
     */
    @Override
    public List<AdminMenu> findAdminMenuTree() {
        // 1、先查询全部菜单数据
        LambdaQueryWrapper<AdminMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AdminMenu::getStatus,1);
        lambdaQueryWrapper.eq(AdminMenu::getIsdelete,0);
        List<AdminMenu> db_ret_allMenu = this.list(lambdaQueryWrapper);

        List<AdminMenu> rootMenu = db_ret_allMenu.stream().filter(
                root -> root.getPid().equals(0L)
        ).collect(Collectors.toList());
        // 通过父元素循环
        rootMenu.forEach(rootItem->AdminMenuTreeRecursive(rootItem,db_ret_allMenu));
        return rootMenu;
    }

    /**
     * 给adminMenu做多级菜单用的遍历~树
     * @param root
     * @param allList
     */
    private void AdminMenuTreeRecursive(AdminMenu root ,List<AdminMenu> allList){
        List<AdminMenu> children_list =  allList.stream()
                .filter(
                        allItem -> allItem.getPid().equals(root.getId())
        ).collect(Collectors.toList());
        if (children_list == null){
            // 如果不赋值一个空数组的话，他可能会是一个null ，但debug看他也是一个空数据，算了加上吧
            children_list = new ArrayList<>();
            root.setChildren(children_list);
        }
        else{
            // 将子元素放到父类里面
            root.setChildren(children_list);
            // 将子元素再次遍历 无限子类的实现
            children_list.forEach(children_item->AdminMenuTreeRecursive(children_item,allList));

        }
    }
}
