package com.lm.entity.vo.banner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Banner实体
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerVo implements java.io.Serializable {
    private Long id;
    // 发布状态
    private Integer status;
    // 标题
    private String title;
    // 页码
    private Integer pageNo = 1;
    // 页面显示数量
    private Integer pageSize = 2;
    // 关键字查询
    private String keyword;
    private String batchIds;
    private Integer isDelete = 0;
    // 分类id
    private Long categoryId;
}