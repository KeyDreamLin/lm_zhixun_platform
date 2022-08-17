package com.lm.controller.page;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lm.controller.BaseController;
import com.lm.entity.bo.UserPageBo;
import com.lm.entity.pojo.User;
import com.lm.entity.vo.user.UserRegisterVo;
import com.lm.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserPagingController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("user/pages")
    public IPage<UserPageBo> findUserPages(int pageNo,
                                           int pageSize,
                                           @RequestParam(required = false) String keyword){
        log.info("{}----{}----{}",pageNo,pageSize,keyword);
        // 1、分页对象
        Page<User> db_page = new Page<>(pageNo,pageSize);
        // 2、设置查询条件
        LambdaQueryWrapper<User> lambdaQueryWrapper =
                new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isEmpty(keyword),User::getNickname,keyword);
        lambdaQueryWrapper.eq(User::getIsDelete,0);
        lambdaQueryWrapper.orderByDesc(User::getUpdateTime);
        // 3、查询分页page
        userService.page(db_page,lambdaQueryWrapper);
        // 4、使用UserPageBo传给前台
        List<User> db_page_list = db_page.getRecords();
        // 创建Bo分页对象
        Page<UserPageBo> retPage = new Page<>();
        // 创建retBo用户信息链表
        List<UserPageBo> ret_page_list = new ArrayList<>();
        // 将db的用户信息存到ret里面
        for (User user : db_page_list) {
            UserPageBo userPageBo = new UserPageBo();
            userPageBo.setId(user.getId());
            userPageBo.setNickname(user.getNickname());
            userPageBo.setUsername(user.getUsername());
            userPageBo.setAge(user.getAge());
            userPageBo.setSex(user.getSex());
            ret_page_list.add(userPageBo);
        }

        // 传入属性
        retPage.setRecords(ret_page_list);
        retPage.setTotal(db_page.getTotal());
        retPage.setSize(db_page.getSize());
        retPage.setCurrent(db_page.getCurrent());
        return retPage;
    }
    @PostMapping("user/save")
    public String saveUser(UserRegisterVo userRegisterVo){
        User user = new User();
        BeanUtils.copyProperties(userRegisterVo,user);
        user.setForbbiden(1);
        user.setIsDelete(0);
        return userService.save(user) ? "success" : "error";

    }
}
