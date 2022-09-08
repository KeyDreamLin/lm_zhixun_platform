package com.lm;

//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.lm.test.entity.bo.adminuser.AdminUserBo;
//import com.lm.test.entity.pojo.adminuser.AdminUser;
//import com.lm.test.entity.vo.adminuser.AdminUserVo;
//import com.lm.test.service.adminuser.IAdminUserService;
//import com.lm.test.service.adminuser.impl.AdminUserServiceImpl;
import com.lm.tool.LmAssert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = LmMybatisPlusGeneratorDemoApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
class LmMybatisPlusGeneratorDemoApplicationTests {
    @Autowired
//    private IAdminUserService iAdminUserService;
    @Test
    void contextLoads() {
//        List<AdminUserBo> adminUserList = iAdminUserService.findAdminUserList();
//        log.info("----------------------123123123--------------{}",adminUserList.toString());

//        AdminUserVo adminuserVo = new AdminUserVo();
//        // 先设置分页信息
//        Page<AdminUser> page = new Page<>(adminuserVo.getPageNo(),adminuserVo.getPageSize());
//        // 设置条件查询
//        LambdaQueryWrapper<AdminUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        // 2：查询发布的 0 未发布  1 发布
//        lambdaQueryWrapper.eq(AdminUser::getStatus, 1);
//        lambdaQueryWrapper.eq(AdminUser::getIsdelete, 0);
//        lambdaQueryWrapper.and(LmAssert.isNotEmpty(adminuserVo.getKeyword()) , wrapper->{wrapper
//            .like(AdminUser::getUsername, adminuserVo.getKeyword())
//            .or()
//            .like(AdminUser::getAccount, adminuserVo.getKeyword());
//        });
//        // 根据时间排降序
//        lambdaQueryWrapper.orderByDesc(AdminUser::getCreateTime);
//        // 查询分页返回
//        IPage<AdminUser> results = iAdminUserService.page(page, lambdaQueryWrapper);
//        IPage<AdminUserBo> results_bo = new Page<>();
//        ArrayList<AdminUserBo> adminUserBos = new ArrayList<>();
//        BeanUtils.copyProperties(results,results_bo,"records");
//        results.getRecords().forEach(item->{
//            AdminUserBo adminUserBo = new AdminUserBo();
//            BeanUtils.copyProperties(item,adminUserBo);
//            adminUserBos.add(adminUserBo);
//        });
//        results_bo.setRecords(adminUserBos);

//        AdminUserVo adminuserVo = new AdminUserVo();
//        adminuserVo.setKeyword("小");
//        IPage<AdminUserBo> results = iAdminUserService.findAdminUserPage(adminuserVo);
//        log.info("---------------------------------------------------------------------------------{}",results.toString());

//        iAdminUserService.delBatchAdminUser("2,3");

    }

//    /**
//     * 查询${title}管理列表信息并分页
//     * 方法名：find${entity}<br/>
//     * 创建人：${author} <br/>
//     * 时间：${date}<br/>
//     * @param {voLowerClassName}Vo
//     * @return IPage<${entity}Bo><br />
//     * @throws <br/>
//     * @since 1.0.0<br />
//     */
//    public IPage<AdminUserBo> findAdminUserPage(AdminUserVo adminuserVo){
//        return null;
//    }

}
