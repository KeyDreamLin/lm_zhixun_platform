package com.lm.entity.vo.adminuser;

import com.baomidou.mybatisplus.annotation.*;
import com.lm.entity.valid.annotation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "{AdminUserRegVo.NotBlank.username.message}")
    @Size(min = 4, max = 20, message = "{AdminUserRegVo.username}")
    private String username;
    // 密码
    @NotBlank
//    @Size(min = 4,max=20,message = "密码长度要在4~20位以内")
    @ValidPassword
    private String password;
    // 账号
    @NotBlank(message = "{AdminUserRegVo.NotBlank.account.message}")
//    @Size(min = 4,max=20,message = "账号长度要在4~20位以内")
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
