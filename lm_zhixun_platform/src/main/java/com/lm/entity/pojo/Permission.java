package com.lm.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限表
 */
@Data
@TableName(value = "sys_permission")
public class Permission implements Serializable {
    
    @TableId(type = IdType.ASSIGN_ID)
    private Long permission_id;

    private String permission_name;
}
