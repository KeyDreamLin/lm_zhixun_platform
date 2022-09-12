package com.lm.entity.bo.adminuser;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* AdminUserBo参数类
* 创建人:Lm<br/>
* 时间：2022-09-08 <br/>
*
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserBo implements java.io.Serializable  {
    // 注解
    private Long id;
    // 姓名
    private String username;
    // 密码
    private String password;
    // 账号
    private String account;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
    // 头像
    private String avatar;
    // 发布状态 0 未发(离职) 1已发布(入职)
    private Integer status;
    // 删除状态 0 未删除  1 删除
    private Integer isdelete;
}