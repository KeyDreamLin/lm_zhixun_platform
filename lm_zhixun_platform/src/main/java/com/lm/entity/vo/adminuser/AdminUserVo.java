package com.lm.entity.vo.adminuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AdminUserVo参数类
 * 创建人:Lm<br/>
 * 时间：2022-09-08 <br/>
 *
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserVo implements java.io.Serializable  {

    //name="操作ID",desc="删除使用"
    private Long id;
	//name="查询状态",desc="0未发布1发布"
	private Integer status;
	//name="分页起始",desc="1"
	private Integer pageNo = 1;
	//name="每页显示记录数",desc="10"
	private Integer pageSize = 10;
	//name="搜索关键词",desc=""
	private String keyword;
	//name="批量删除IDS",desc="用户表格批量删除"
    private String batchIds;
    //name="删除状态",desc="0未删除1删除"
    private Integer isDelete = 0;
    //name="搜索分类"
    private Long categoryId;
}