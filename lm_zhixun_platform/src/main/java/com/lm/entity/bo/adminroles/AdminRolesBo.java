package com.lm.entity.bo.adminroles;

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
* AdminRolesBo参数类
* 创建人:Lm<br/>
* 时间：2022-09-14 <br/>
*
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRolesBo implements java.io.Serializable  {
    // 
    private Long id;
    // 
    private String roleCode;
    // 
    private String roleName;
    // 
    private Integer status;
    // 
    private Integer isdelete;
    // 
    private Date createTime;
    // 
    private Date updateTime;
    // 是否授权
    private Boolean isAuth;
}