package com.lm.service.adminroles;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.entity.vo.adminroles.AdminRolesSaveUpdateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lm.mapper.AdminRolesMapper;
import com.lm.service.adminroles.AdminRolesServiceImpl;
import com.lm.entity.pojo.adminroles.AdminRoles;
import com.lm.entity.vo.adminroles.AdminRolesVo;
import com.lm.entity.bo.adminroles.AdminRolesBo;
import com.lm.tool.LmAssert;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
/**
*  服务实现类
*
* @author Lm
* @since 2022-09-14
*/
@Service
public class IAdminRolesService extends ServiceImpl<AdminRolesMapper, AdminRoles> implements AdminRolesServiceImpl {

    /**
    * 查询后台权限管理管理列表信息
    * @method: findAdminRolesList
    * @result : List<AdminRoles>
    * 创建人:Lm
    * 创建时间：2022-09-14
    * @return List<AdminRolesBo>
    */
    public List<AdminRolesBo> findAdminRolesList(){
        // 1：设置查询条件
        LambdaQueryWrapper<AdminRoles> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(AdminRoles::getStatus, 1);
        lambdaQueryWrapper.eq(AdminRoles::getIsdelete, 0);
        List<AdminRoles> list = this.list(lambdaQueryWrapper);
        List<AdminRolesBo> list_bo = new ArrayList<>();
            list.forEach(item->{
            AdminRolesBo bo = new AdminRolesBo();
            BeanUtils.copyProperties(item,bo);
            list_bo.add(bo);
        });
        return list_bo;
    }

    /**
    * 查询后台权限管理管理列表信息并分页
    * 方法名：findAdminRoles<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param adminrolesVo
    * @return IPage<AdminRolesBo><br />
    */
    @Override
    public IPage<AdminRolesBo> findAdminRolesPage(AdminRolesVo adminrolesVo){
        // 先设置分页信息
        Page<AdminRoles> page = new Page<>(adminrolesVo.getPageNo(),adminrolesVo.getPageSize());
        // 设置条件查询
        LambdaQueryWrapper<AdminRoles> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(AdminRoles::getStatus, 1);
        lambdaQueryWrapper.eq(AdminRoles::getIsdelete, 0);
        lambdaQueryWrapper.and(LmAssert.isNotEmpty(adminrolesVo.getKeyword()) , wrapper->{wrapper
            .like(AdminRoles::getRoleName, adminrolesVo.getKeyword())
            .or()
            .like(AdminRoles::getRoleCode, adminrolesVo.getKeyword());
        });
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(AdminRoles::getCreateTime);
        // 查询分页返回
        IPage<AdminRoles> results = this.page(page, lambdaQueryWrapper);
        IPage<AdminRolesBo> results_bo = new Page<>();
        ArrayList<AdminRolesBo> results_list_bo = new ArrayList<>();
        BeanUtils.copyProperties(results,results_bo,"records");
        results.getRecords().forEach(item->{
        AdminRolesBo adminrolesBo = new AdminRolesBo();
            BeanUtils.copyProperties(item,adminrolesBo);
            results_list_bo.add(adminrolesBo);
        });
        results_bo.setRecords(results_list_bo);
        return results_bo;
    }

    /**
    * 保存&修改后台权限管理管理
    * 方法名：saveupdateAdminRoles<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param adminRolesSaveUpdateVo
    * @return AdminRolesBo<br />
    */
    @Override
    public AdminRolesBo saveupdateAdminRoles(AdminRolesSaveUpdateVo adminRolesSaveUpdateVo){
        AdminRoles adminroles = new AdminRoles();
        BeanUtils.copyProperties(adminRolesSaveUpdateVo,adminroles);
        boolean flag = this.saveOrUpdate(adminroles);
        AdminRolesBo adminrolesBo = new AdminRolesBo();
        BeanUtils.copyProperties(adminRolesSaveUpdateVo,adminrolesBo);
        return flag ? adminrolesBo : null;
    }

    /**
    * 根据Id删除后台权限管理管理
    * 方法名：deleteAdminRolesById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param id
    * @return int <br />
    */
    @Override
    public int deleteAdminRolesById(Long id){
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
    * 根据Id查询后台权限管理管理明细信息
    * 方法名：getAdminRolesById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param id
    * @return AdminRolesBo <br />
    */
    @Override
    public AdminRolesBo getAdminRolesById(Long id){
        AdminRoles byId = this.getById(id);
        AdminRolesBo adminrolesBo = new AdminRolesBo();
        BeanUtils.copyProperties(byId,adminrolesBo);
        return adminrolesBo;
    }

    /**
    * 根据后台权限管理管理ids批量删除后台权限管理管理
    * 方法名：delBatchAdminRoles<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-14<br/>
    * @param ids
    * @return boolean <br />
    */
    @Override
    public boolean delBatchAdminRoles(String ids){
        // 1 : 把数据分割
        String[] strings = ids.split(",");
        // 2: 组装成一个List<AdminRoles>
        List<AdminRoles> adminrolesList = Arrays.stream(strings).map(idstr -> {
            AdminRoles adminroles = new AdminRoles();
            adminroles.setId(new Long(idstr));
            adminroles.setIsdelete(1);
            return adminroles;
        }).collect(Collectors.toList());
        // 3: 批量删除
        return this.updateBatchById(adminrolesList);
    }

    /**
     * 根据角色id查询对应权限列表
     * @param id
     * @return
     */
    @Override
    public List<String> findPermissionByRoleId(Long id) {
        return this.baseMapper.findPermissionByRoleId(id);
    }

    /**
     * 保存权限列表 角色对应多个权限id
     * @param r_id
     * @param p_idStr
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 事务
    public boolean savePermissionByRoleId(@NotNull Long r_id, @NotBlank String p_idStr) {
        // 因为添加了事务 无论出现了什么样的错误 数据都将回滚
        String[] p_idList = p_idStr.split(",");
        // 先根据id删除角色下的所有权限
        this.baseMapper.deletePermissionByRoleId(r_id);
        // 然后在添加 遍历前端返回的权限列 然后对应角色 遍历权限一个个添加
        Arrays.stream(p_idList).forEach(p_id->{
            this.baseMapper.savePermissionByRoleId(r_id,new Long(p_id));
        });
        return true;
    }

}
