package com.lm.test.mapper;

import com.lm.test.entity.pojo.adminuser.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author Lm
 * @since 2022-09-08
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {

}
