package com.lm.entity.bo.roles;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* RolesBo参数类
* 创建人:Lm<br/>
* 时间：2022-09-08 <br/>
*
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesBo implements java.io.Serializable  {
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
    private LocalDateTime createTime;
    // 
    private LocalDateTime updateTime;
}