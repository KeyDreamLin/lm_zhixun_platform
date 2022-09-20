package com.lm.service.adminuser;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lm.common.ex.lthrow.ValidatorExceptionThrow;
import com.lm.common.r.UserResultEnum;
import com.lm.entity.pojo.adminroles.AdminRoles;
import com.lm.entity.vo.adminuser.AdminUserQueryVo;
import com.lm.entity.vo.adminuser.AdminUserRegVo;
import com.lm.entity.vo.adminuser.AdminUserUpdateVo;
import com.lm.tool.pwd.MD5Util;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.lm.mapper.AdminUserMapper;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.lm.entity.bo.adminuser.AdminUserBo;
import com.lm.tool.LmAssert;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public AdminUser login(String userName) {
        LambdaQueryWrapper<AdminUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(AdminUser::getAccount,userName);
        AdminUser user = this.getOne(userLambdaQueryWrapper);
        return user;
    }

    /**
     * 获取用户拥有的角色 根据用户id
     * @param id
     * @return
     */
    @Override
    public List<String> getRoleByUserId(Long id) {
        return this.baseMapper.findRoleByUserId(id);
    }


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
     * 为用户绑定一个角色
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public boolean bindingRoleByUserId(Long userId, Long roleId) {
        int count = this.baseMapper.countUserRole(userId, roleId);
        // 如果存在就不添加了
        if(count == 0){
            // 角色
            return this.baseMapper.saveUserRole(userId, roleId)>0;
        }
        return true;
    }

    /**
     * 为用户解除绑定一个角色
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public boolean unbindingRoleByUserId(Long userId, Long roleId) {
        return this.baseMapper.delUserRole(userId, roleId) > 0;
    }

    /**
    * 查询后台用户管理管理列表信息并分页
    * 方法名：findAdminUser<br/>
    * 创建人：Lm <br/>
    * 时间：2022-09-08<br/>
    * @param adminuserQueryVo
    * @return IPage<AdminUserBo><br />
    */
    @Override
    public IPage<AdminUserBo> findAdminUserPage(AdminUserQueryVo adminuserQueryVo){
        // 先设置分页信息
        Page<AdminUser> page = new Page<>(adminuserQueryVo.getPageNo(), adminuserQueryVo.getPageSize());
        // 设置条件查询
        LambdaQueryWrapper<AdminUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(AdminUser::getStatus, 1);
        lambdaQueryWrapper.eq(AdminUser::getIsdelete, 0);
        lambdaQueryWrapper.and(LmAssert.isNotEmpty(adminuserQueryVo.getKeyword()) , wrapper->{wrapper
            .like(AdminUser::getUsername, adminuserQueryVo.getKeyword())
            .or()
            .like(AdminUser::getAccount, adminuserQueryVo.getKeyword());
        });
        // 根据时间排降序
        lambdaQueryWrapper.orderByAsc(AdminUser::getCreateTime);
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
    * @param adminUserRegVo
    * @return AdminUserBo<br />
    */
    @Override
    @Transactional(rollbackFor = Exception.class) // 事务
    public AdminUserBo saveupdateAdminUser(AdminUserRegVo adminUserRegVo){
        // 在编辑的情况下 通过id查询数据库的账号和vo进行对比 如果数据库的账号和vo的账号不一致就需要验证账号是否存在 反之不验证
        // 怎么样区分编辑还是添加
        // 添加的时候id是null 判断是null就好了
        // 查询出数据库的admin用户数据 !当前编辑的用户
        AdminUser this_db_edit_adminUser = this.getById(adminUserRegVo.getId());
        if((adminUserRegVo!=null && adminUserRegVo.getId() == null) ||
           (adminUserRegVo != null && !this_db_edit_adminUser.getAccount().equals(adminUserRegVo.getAccount()))
        ){
            // 1、保证账号的唯一性
            LambdaQueryWrapper<AdminUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(AdminUser::getAccount,adminUserRegVo.getAccount());
            Long db_count = this.count(lambdaQueryWrapper);
            // 大于0账号已存在
            if (db_count > 0){
                throw  new ValidatorExceptionThrow(UserResultEnum.ACCOUNT_REG_ERROR);
            }
        }
        // 如果是不是空就执行 null "" 返回true
        // 如果密码不为空
        if(LmAssert.isNotEmpty(adminUserRegVo.getPassword())){
            // 2、密码MD5加密
            adminUserRegVo.setPassword(MD5Util.strToMd5s(adminUserRegVo.getPassword()));
        }
        else{
            //如果密码为空那就表示编辑状态下需要将 原始的密码回填回去 因为在编辑状态下密码是可以允许“”放行的
            adminUserRegVo.setPassword(this_db_edit_adminUser.getPassword());
        }


        // 3、将用户信息插入到数据库
        AdminUser db_adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserRegVo,db_adminUser);
        // 插入 会自动填充id
        boolean flag = this.saveOrUpdate(db_adminUser);
        // 插入或者更新成功后绑定权限
        if (flag){
            // 编辑状态下 不需要查询添加数据
            int count = this.baseMapper.countUserRole(db_adminUser.getId(), adminUserRegVo.getRolesId());
            if(count == 0){
                // 角色
                this.baseMapper.saveUserRole(db_adminUser.getId(), adminUserRegVo.getRolesId());
            }
        }
        // 返回给前端使用
        AdminUserBo adminuserBo = new AdminUserBo();
        BeanUtils.copyProperties(adminUserRegVo,adminuserBo);
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
    /**
     * 状态更新
     * @param adminUserUpdateVo
     * @return
     */
    @Override
    public boolean updateAdminUser(AdminUserUpdateVo adminUserUpdateVo){
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(adminUserUpdateVo,adminUser);
        return updateById(adminUser);
    }

    /**
     * 通过admin用户id查询到对应的角色
     *
     * @param id adminUser id
     * @return 对应角色表
     */
    @Override
    public List<String> getRoleNamesByUid(Long id) {
        List<String> RolesNameList = this.baseMapper.findUserRolesByUid(id)
                .stream().map(AdminRoles::getRoleName).collect(Collectors.toList());
        return RolesNameList;
    }

    /**
     * 查询用户对应的权限表
     * @param userId
     * @return
     */
    @Override
    public List<String> findByUserPermission(Long userId) {
        return this.baseMapper.findByUserPermission(userId);
    }

}
