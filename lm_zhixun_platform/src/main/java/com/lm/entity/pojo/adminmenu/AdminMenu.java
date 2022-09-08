package com.lm.entity.pojo.adminmenu;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kss_admin_menu")
public class AdminMenu {
    @TableId(type = IdType.ASSIGN_ID)
    // id
    private Long id;
    // 菜单名词
    private String name;
    // 菜单排序
    private Integer sorted;
    // 菜单链接
    private String path;
    // 菜单图标
    private String icon;
    // 菜单发布  是否发布
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    // 菜单名词
    private Long pid;
    // 菜单路径
    private String pathname;
    // 删除状态 0未删除 1删除
    private Integer isdelete;

    // 子类 pid==id
    @TableField(exist = false)
    private List<AdminMenu> children;
}
