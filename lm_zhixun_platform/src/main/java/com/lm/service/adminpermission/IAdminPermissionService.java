package com.lm.service.adminpermission;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.entity.pojo.adminmenu.AdminMenu;
import com.lm.entity.vo.adminpermission.AdminPermissionPageVo;
import com.lm.entity.vo.adminpermission.AdminPermissionSaveUpDataVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lm.mapper.AdminPermissionMapper;
import com.lm.service.adminpermission.AdminPermissionServiceImpl;
import com.lm.entity.pojo.adminpermission.AdminPermission;
import com.lm.entity.vo.adminpermission.AdminPermissionVo;
import com.lm.entity.bo.adminpermission.AdminPermissionBo;
import com.lm.tool.LmAssert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
/**
* 菜单管理  服务实现类
*
* @author Lm
* @since 2022-09-14
*/
@Service
public class IAdminPermissionService extends ServiceImpl<AdminPermissionMapper, AdminPermission> implements AdminPermissionServiceImpl {

    /**
    * 查询用户权限管理列表信息
    * @method: findAdminPermissionList
    * @result : List<AdminPermission>
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @return List<AdminPermissionBo>
    */
    public List<AdminPermissionBo> findAdminPermissionList(){
        // 1：设置查询条件
        LambdaQueryWrapper<AdminPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(AdminPermission::getStatus, 1);
        lambdaQueryWrapper.eq(AdminPermission::getIsdelete, 0);
        List<AdminPermission> list = this.list(lambdaQueryWrapper);
        List<AdminPermissionBo> list_bo = new ArrayList<>();
            list.forEach(item->{
            AdminPermissionBo bo = new AdminPermissionBo();
            BeanUtils.copyProperties(item,bo);
            list_bo.add(bo);
        });
        return list_bo;
    }

    /**
    * 查询用户权限管理列表信息并分页
    * 方法名：findAdminPermission<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param adminpermissionVo
    * @return IPage<AdminPermissionBo><br />
    */
    @Override
    public IPage<AdminPermissionBo> findAdminPermissionPage(AdminPermissionPageVo adminpermissionVo){
        // 先设置分页信息
        Page<AdminPermission> page = new Page<>(adminpermissionVo.getPageNo(),adminpermissionVo.getPageSize());
        // 设置条件查询
        LambdaQueryWrapper<AdminPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(AdminPermission::getStatus, 1);
        lambdaQueryWrapper.eq(AdminPermission::getIsdelete, 0);
//        lambdaQueryWrapper.and(LmAssert.isNotEmpty(adminpermissionVo.getKeyword()) , wrapper->{wrapper
//            .like(AdminPermission::getUsername, adminpermissionVo.getKeyword())
//            .or()
//            .like(AdminPermission::getAccount, adminpermissionVo.getKeyword());
//        });
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(AdminPermission::getCreateTime);
        // 查询分页返回
        IPage<AdminPermission> results = this.page(page, lambdaQueryWrapper);
        IPage<AdminPermissionBo> results_bo = new Page<>();
        ArrayList<AdminPermissionBo> results_list_bo = new ArrayList<>();
        BeanUtils.copyProperties(results,results_bo,"records");
        results.getRecords().forEach(item->{
        AdminPermissionBo adminpermissionBo = new AdminPermissionBo();
            BeanUtils.copyProperties(item,adminpermissionBo);
            results_list_bo.add(adminpermissionBo);
        });
        results_bo.setRecords(results_list_bo);
        return results_bo;
    }

    /**
    * 保存&修改用户权限管理
    * 方法名：saveupdateAdminPermission<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param adminPermissionSaveUpDataVo
    * @return AdminPermissionBo<br />
    */
    @Override
    public AdminPermissionBo saveupdateAdminPermission(AdminPermissionSaveUpDataVo adminPermissionSaveUpDataVo){
        AdminPermission adminpermission = new AdminPermission();
        BeanUtils.copyProperties(adminPermissionSaveUpDataVo,adminpermission);
        boolean flag = this.saveOrUpdate(adminpermission);
        AdminPermissionBo adminpermissionBo = new AdminPermissionBo();
        BeanUtils.copyProperties(adminPermissionSaveUpDataVo,adminpermissionBo);
        return flag ? adminpermissionBo : null;
    }

    /**
    * 根据Id删除权限 并且 删除对应中间表
    * 方法名：deleteAdminPermissionById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param id
    * @return int <br />
    */
    @Override
    @Transactional(rollbackFor = Exception.class) // 事务
    public int deleteAdminPermissionById(Long id){
        // 顺便把角色对应权限的中间表删除
        this.deleteRolesByPermissionId(id);
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
    * 根据Id查询用户权限管理明细信息
    * 方法名：getAdminPermissionById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param id
    * @return AdminPermissionBo <br />
    */
    @Override
    public AdminPermissionBo getAdminPermissionById(Long id){
        AdminPermission byId = this.getById(id);
        AdminPermissionBo adminpermissionBo = new AdminPermissionBo();
        BeanUtils.copyProperties(byId,adminpermissionBo);
        return adminpermissionBo;
    }

    /**
    * 根据用户权限管理ids批量删除后台权限管理管理
    * 方法名：delBatchAdminPermission<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param ids
    * @return boolean <br />
    */
    @Override
    public boolean delBatchAdminPermission(String ids){
        // 1 : 把数据分割
        String[] strings = ids.split(",");
        // 2: 组装成一个List<AdminPermission>
        List<AdminPermission> adminpermissionList = Arrays.stream(strings).map(idstr -> {
            AdminPermission adminpermission = new AdminPermission();
            adminpermission.setId(new Long(idstr));
            adminpermission.setIsdelete(1);
            return adminpermission;
        }).collect(Collectors.toList());
        // 3: 批量删除
        return this.updateBatchById(adminpermissionList);
    }



    /**
     * 查询整个权限表 变转换为tree
     * 查询菜单和权限信息tree
     * @return
     */
    @Override
    public List<AdminPermission> findAdminPermissionTree() {
        // 1、先查询全部菜单数据
        LambdaQueryWrapper<AdminPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AdminPermission::getStatus,1);
        lambdaQueryWrapper.eq(AdminPermission::getIsdelete,0);
        List<AdminPermission> db_ret_allMenu = this.list(lambdaQueryWrapper);

        List<AdminPermission> rootMenu = db_ret_allMenu.stream().filter(
                root -> root.getPid().equals(0L)
        ).collect(Collectors.toList());
        // 通过父元素循环
        rootMenu.forEach(rootItem->AdminPermissionTreeRecursive(rootItem,db_ret_allMenu));
        return rootMenu;
    }

    /**
     * 查询整个权限表 变转换为tree
     * @param root
     * @param allList
     */
    private void AdminPermissionTreeRecursive(AdminPermission root ,List<AdminPermission> allList){
        List<AdminPermission> children_list =  allList.stream()
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
            children_list.forEach(children_item->AdminPermissionTreeRecursive(children_item,allList));

        }
    }



    /**
     * 权限表里面的菜单数据
     * @return
     */
    @Override
    public List<AdminPermission> findAdminPermissionsMenuTree() {
        // 1、先查询全部菜单数据
        LambdaQueryWrapper<AdminPermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AdminPermission::getStatus,1);
        lambdaQueryWrapper.eq(AdminPermission::getIsdelete,0);
        lambdaQueryWrapper.eq(AdminPermission::getType,1);
        List<AdminPermission> db_ret_allMenu = this.list(lambdaQueryWrapper);

        List<AdminPermission> rootMenu = db_ret_allMenu.stream().filter(
                root -> root.getPid().equals(0L)
        ).collect(Collectors.toList());
        // 通过父元素循环
        rootMenu.forEach(rootItem->AdminPermissionsMenuTreeRecursive(rootItem,db_ret_allMenu));
        return rootMenu;
    }

    /**
     * 权限表里面的菜单数据 遍历拼接层树
     * @param root
     * @param allList
     */
    private void AdminPermissionsMenuTreeRecursive(AdminPermission root ,List<AdminPermission> allList){
        List<AdminPermission> children_list =  allList.stream()
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
            children_list.forEach(children_item->AdminPermissionTreeRecursive(children_item,allList));

        }
    }

    /**
     * 根据权限id删除 对应的角色权限中间表
     * @param p_id
     * @return
     */
    @Override
    public int deleteRolesByPermissionId (Long p_id){
        return this.baseMapper.deleteRolesByPermissionId(p_id);
    }
}
