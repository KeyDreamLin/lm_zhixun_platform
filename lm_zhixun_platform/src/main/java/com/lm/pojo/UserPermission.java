package com.lm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户权限表
 */
@Data
@TableName(value = "sys_user_permission")
public class UserPermission {
    private Long UserPermissionId;

    @TableId(type = IdType.ASSIGN_ID)
    private Long UserId;
    private Long PermissionId;

//    @TableField("updatetime")
    private LocalDateTime UpdateTime;

//    @TableField("updateid")
    private Long UpdateId;

//    @TableField("newtime")
    private LocalDateTime NewTime;

//    @TableField("updatetime")
    private Long NewId;
}
