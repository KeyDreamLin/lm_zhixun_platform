package com.lm.service.usertoken;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.pojo.UserToken;

public interface UserTokenService extends IService<UserToken> {

    //删除一个token，先查询是否存在，然后删除 好像不需要这么麻烦
//    public boolean deleteTokenByUserId(int userId);
    //添加一下token，先查询是否存在，如果存在就修改token，如果不存在就添加
}
