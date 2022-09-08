package com.lm.entity.pojo.adminuser;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lm
 * @since 2022-09-08
 */
@Getter
@Setter
@TableName("kss_admin_user")
public class AdminUser implements Serializable {



     // 注解
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;


     // 姓名
    private String username;


     // 密码
    private String password;


     // 账号
    private String account;


     // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


     // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


     // 头像
    private String avatar;


     // 发布状态 0 未发(离职) 1已发布(入职)
    private Integer status;


     // 删除状态 0 未删除  1 删除
    private Integer isdelete;


}
