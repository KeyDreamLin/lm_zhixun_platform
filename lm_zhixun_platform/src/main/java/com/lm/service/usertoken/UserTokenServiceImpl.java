package com.lm.service.usertoken;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.entity.pojo.UserToken;
import com.lm.mapper.UserTokenMapper;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

}
