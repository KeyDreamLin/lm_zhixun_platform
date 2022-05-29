package com.lm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lm.entity.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
