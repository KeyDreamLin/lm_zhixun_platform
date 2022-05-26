package com.lm.service.usertoken;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.mapper.UserTokenMapper;
import com.lm.pojo.UserToken;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

}
