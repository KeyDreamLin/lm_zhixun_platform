package com.lm.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页UserBo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageBo implements Serializable {
    // 主键
    private Long id;
    // 昵称
    private String nickname;
    // 真实姓名
    private String username;
    // 年龄
    private Integer age;
    // 性别
    private Integer sex;
}
