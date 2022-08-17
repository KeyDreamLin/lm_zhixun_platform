package com.lm.entity.bo.banner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class BannerBo  implements java.io.Serializable {

//    @PugDoc(name="主键")
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
    private Date createTime;
//    @PugDoc(name="更新时间")
    private Date updateTime;
//    @PugDoc(name="")
    private Integer isdelete;
}