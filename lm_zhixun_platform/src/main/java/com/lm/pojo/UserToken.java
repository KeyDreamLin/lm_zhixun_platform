package com.lm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户token
 */
@Data
@TableName(value = "sys_user_token")
public class UserToken {

    @TableId(type = IdType.ASSIGN_ID)//雪花算法
    private Long idToken;//id令牌
    //用户令牌(用户id&创建时间)
    private String UserIdToken;
    //创建时间戳
    private Long NewTimestamp;
    //token状态 （0 用户手动注销，1 token正常），主要以newTimer为主要判断
    private int state;
}
