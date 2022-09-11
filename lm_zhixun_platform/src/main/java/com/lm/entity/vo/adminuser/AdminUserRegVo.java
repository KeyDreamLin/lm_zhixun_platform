package com.lm.entity.vo.adminuser;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用于adminUser注册
 * @author Lm
 * @since 2022-09-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserRegVo implements Serializable {

    private Long id;
     // 姓名
    private String username;
     // 密码
    private String password;
     // 账号
    private String account;
     // 头像
    private String avatar;
     // 发布状态 0 未发(离职) 1已发布(入职)
    private Integer status;
     // 删除状态 0 未删除  1 删除
    private Integer isdelete;
    // 用户权限id
    private Long rolesId;
}
