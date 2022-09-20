package com.lm.entity.vo.adminpermission;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * AdminPermissionVo参数类
 * 创建人:Lm<br/>
 * 时间：2022-09-14 <br/>
 *
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminPermissionSaveUpDataVo implements java.io.Serializable  {
    // 主键
    private Long id;
    // 菜单名词
    private String name;
    // 菜单排序
    private Integer sorted;
    // 路径名称
    private String pathname;
    // 菜单链接
    private String path = pathname;
    // 菜单图标
    private String icon;
    // 菜单发布
    private Integer status;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
    // 菜单名称
    private Long pid;

    // 删除状态 0未删除 1删除
    private Integer isdelete;
    // 1菜单 2 权限
    private Integer type;
    // 代号
    private String code;
}