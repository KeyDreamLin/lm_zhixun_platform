package com.lm.entity.pojo.adminpermission;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lm.entity.pojo.adminmenu.AdminMenu;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单管理 
 * </p>
 *
 * @author Lm
 * @since 2022-09-14
 */
@Getter
@Setter
@TableName("kss_permission")
public class AdminPermission implements Serializable {

     // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

     // 菜单名词
    private String name;

     // 菜单排序
    private Integer sorted;

     // 菜单链接
    private String path;

     // 菜单图标
    private String icon;

     // 菜单发布
    private Integer status;

     // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

     // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

     // 菜单名称
    private Long pid;

     // 路径名称
    private String pathname;

     // 删除状态 0未删除 1删除
    private Integer isdelete;

     // 1菜单 2 权限
    private Integer type;

     // 代号
    private String code;

    // 子类 pid==id
    @TableField(exist = false)
    private List<AdminPermission> children;
}
