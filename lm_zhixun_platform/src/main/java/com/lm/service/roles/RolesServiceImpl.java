package com.lm.service.roles;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lm.mapper.RolesMapper;
import com.lm.service.roles.IRolesService;
import com.lm.entity.pojo.roles.Roles;
import com.lm.entity.vo.roles.RolesVo;
import com.lm.entity.bo.roles.RolesBo;
import com.lm.tool.LmAssert;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
/**
*  服务实现类
*
* @author Lm
* @since 2022-09-08
*/
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    /**
    * 查询用户权限管理管理列表信息
    * @method: findRolesList
    * @result : List<Roles>
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @return List<RolesBo>
    */
    public List<RolesBo> findRolesList(){
        // 1：设置查询条件
        LambdaQueryWrapper<Roles> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(Roles::getStatus, 1);
        lambdaQueryWrapper.eq(Roles::getIsdelete, 0);
        List<Roles> list = this.list(lambdaQueryWrapper);
        List<RolesBo> list_bo = new ArrayList<>();
            list.forEach(item->{
            RolesBo bo = new RolesBo();
            BeanUtils.copyProperties(item,bo);
            list_bo.add(bo);
        });
        return list_bo;
    }

    /**
    * 查询用户权限管理管理列表信息并分页
    * 方法名：findRoles<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param rolesVo
    * @return IPage<RolesBo><br />
    */
    @Override
    public IPage<RolesBo> findRolesPage(RolesVo rolesVo){
        // 先设置分页信息
        Page<Roles> page = new Page<>(rolesVo.getPageNo(),rolesVo.getPageSize());
        // 设置条件查询
        LambdaQueryWrapper<Roles> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(Roles::getStatus, 1);
        lambdaQueryWrapper.eq(Roles::getIsdelete, 0);
        lambdaQueryWrapper.and(LmAssert.isNotEmpty(rolesVo.getKeyword()) , wrapper->{wrapper
            .like(Roles::getRoleName, rolesVo.getKeyword())
            .or()
            .like(Roles::getRoleCode, rolesVo.getKeyword());
        });
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(Roles::getCreateTime);
        // 查询分页返回
        IPage<Roles> results = this.page(page, lambdaQueryWrapper);
        IPage<RolesBo> results_bo = new Page<>();
        ArrayList<RolesBo> results_list_bo = new ArrayList<>();
        BeanUtils.copyProperties(results,results_bo,"records");
        results.getRecords().forEach(item->{
        RolesBo rolesBo = new RolesBo();
            BeanUtils.copyProperties(item,rolesBo);
            results_list_bo.add(rolesBo);
        });
        results_bo.setRecords(results_list_bo);
        return results_bo;
    }

    /**
    * 保存&修改用户权限管理管理
    * 方法名：saveupdateRoles<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param roles
    * @return RolesBo<br />
    */
    @Override
    public RolesBo saveupdateRoles(Roles roles){
        boolean flag = this.saveOrUpdate(roles);
        RolesBo rolesBo = new RolesBo();
        BeanUtils.copyProperties(roles,rolesBo);
        return flag ? rolesBo : null;
    }

    /**
    * 根据Id删除用户权限管理管理
    * 方法名：deleteRolesById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param id
    * @return int <br />
    */
    @Override
    public int deleteRolesById(Long id){
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
    * 根据Id查询用户权限管理管理明细信息
    * 方法名：getRolesById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param id
    * @return RolesBo <br />
    */
    @Override
    public RolesBo getRolesById(Long id){
        Roles byId = this.getById(id);
        RolesBo rolesBo = new RolesBo();
        BeanUtils.copyProperties(byId,rolesBo);
        return rolesBo;
    }

    /**
    * 根据用户权限管理管理ids批量删除用户权限管理管理
    * 方法名：delBatchRoles<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param ids
    * @return boolean <br />
    */
    @Override
    public boolean delBatchRoles(String ids){
        // 1 : 把数据分割
        String[] strings = ids.split(",");
        // 2: 组装成一个List<Roles>
        List<Roles> rolesList = Arrays.stream(strings).map(idstr -> {
            Roles roles = new Roles();
            roles.setId(new Long(idstr));
            roles.setIsdelete(1);
            return roles;
        }).collect(Collectors.toList());
        // 3: 批量删除
        return this.updateBatchById(rolesList);
    }

}
