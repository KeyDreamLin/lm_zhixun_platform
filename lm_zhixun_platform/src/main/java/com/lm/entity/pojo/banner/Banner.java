package com.lm.entity.pojo.banner;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * Banner实体
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("kss_banner")
public class Banner  implements java.io.Serializable {

//    @PugDoc(name="主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
//    @PugDoc(name="标题")
    private String title;
//    @PugDoc(name="链接地址")
    private String hreflink;
//    @PugDoc(name="打开方式")
    private String opentype;
//    @PugDoc(name="描述")
    private String description;
//    @PugDoc(name="封面图标")
    private String img;
//    @PugDoc(name="排序字段")
    private Integer sorted;
//    @PugDoc(name="发布状态")
    private Integer status;
//    @PugDoc(name="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
//    @PugDoc(name="更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
//    @PugDoc(name="")
    private Integer isdelete;
}