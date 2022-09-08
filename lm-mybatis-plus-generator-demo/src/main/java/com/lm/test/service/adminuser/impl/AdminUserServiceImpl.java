package com.lm.test.service.adminuser.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lm.test.mapper.AdminUserMapper;
import com.lm.test.service.adminuser.IAdminUserService;
import com.lm.test.entity.pojo.adminuser.AdminUser;
import com.lm.test.entity.vo.adminuser.AdminUserVo;
import com.lm.test.entity.bo.adminuser.AdminUserBo;
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
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {

    /**
    * 查询后台用户管理管理列表信息
    * @method: findAdminUserList
    * @result : List<AdminUser>
    * 创建人:Lm
    * 创建时间：2022-09-08
    * @return List<AdminUserBo>
    */
    public List<AdminUserBo> findAdminUserList(){
        // 1：设置查询条件
        LambdaQueryWrapper<AdminUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(AdminUser::getStatus, 1);
        lambdaQueryWrapper.eq(AdminUser::getIsdelete, 0);
        List<AdminUser> list = this.list(lambdaQueryWrapper);
        List<AdminUserBo> list_bo = new ArrayList<>();
            list.forEach(item->{
            AdminUserBo bo = new AdminUserBo();
            BeanUtils.copyProperties(item,bo);
            list_bo.add(bo);
        });
        return list_bo;
    }

    /**
    * 查询后台用户管理管理列表信息并分页
    * 方法名：findAdminUser<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param adminuserVo
    * @return IPage<AdminUserBo><br />
    */
    @Override
    public IPage<AdminUserBo> findAdminUserPage(AdminUserVo adminuserVo){
        // 先设置分页信息
        Page<AdminUser> page = new Page<>(adminuserVo.getPageNo(),adminuserVo.getPageSize());
        // 设置条件查询
        LambdaQueryWrapper<AdminUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(AdminUser::getStatus, 1);
        lambdaQueryWrapper.eq(AdminUser::getIsdelete, 0);
        lambdaQueryWrapper.and(LmAssert.isNotEmpty(adminuserVo.getKeyword()) , wrapper->{wrapper
            .like(AdminUser::getUsername, adminuserVo.getKeyword())
            .or()
            .like(AdminUser::getAccount, adminuserVo.getKeyword());
        });
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(AdminUser::getCreateTime);
        // 查询分页返回
        IPage<AdminUser> results = this.page(page, lambdaQueryWrapper);
        IPage<AdminUserBo> results_bo = new Page<>();
        ArrayList<AdminUserBo> results_list_bo = new ArrayList<>();
        BeanUtils.copyProperties(results,results_bo,"records");
        results.getRecords().forEach(item->{
        AdminUserBo adminuserBo = new AdminUserBo();
            BeanUtils.copyProperties(item,adminuserBo);
            results_list_bo.add(adminuserBo);
        });
        results_bo.setRecords(results_list_bo);
        return results_bo;
    }

    /**
    * 保存&修改后台用户管理管理
    * 方法名：saveupdateAdminUser<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param adminuser
    * @return AdminUserBo<br />
    */
    @Override
    public AdminUserBo saveupdateAdminUser(AdminUser adminuser){
        boolean flag = this.saveOrUpdate(adminuser);
        AdminUserBo adminuserBo = new AdminUserBo();
        BeanUtils.copyProperties(adminuser,adminuserBo);
        return flag ? adminuserBo : null;
    }

    /**
    * 根据Id删除后台用户管理管理
    * 方法名：deleteAdminUserById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param id
    * @return int <br />
    */
    @Override
    public int deleteAdminUserById(Long id){
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
    * 根据Id查询后台用户管理管理明细信息
    * 方法名：getAdminUserById<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param id
    * @return AdminUserBo <br />
    */
    @Override
    public AdminUserBo getAdminUserById(Long id){
        AdminUser byId = this.getById(id);
        AdminUserBo adminuserBo = new AdminUserBo();
        BeanUtils.copyProperties(byId,adminuserBo);
        return adminuserBo;
    }

    /**
    * 根据后台用户管理管理ids批量删除后台用户管理管理
    * 方法名：delBatchAdminUser<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param ids
    * @return boolean <br />
    */
    @Override
    public boolean delBatchAdminUser(String ids){
        // 1 : 把数据分割
        String[] strings = ids.split(",");
        // 2: 组装成一个List<AdminUser>
        List<AdminUser> adminuserList = Arrays.stream(strings).map(idstr -> {
            AdminUser adminuser = new AdminUser();
            adminuser.setId(new Long(idstr));
            adminuser.setIsdelete(1);
            return adminuser;
        }).collect(Collectors.toList());
        // 3: 批量删除
        return this.updateBatchById(adminuserList);
    }

}
