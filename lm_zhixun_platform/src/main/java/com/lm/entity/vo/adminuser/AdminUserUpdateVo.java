package com.lm.entity.vo.adminuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserUpdateVo implements java.io.Serializable  {

	//name="注解")
	private Long id;

	//name="发布状态 0 未发(离职) 1已发布(入职)")
	private Integer status;

	//name="删除状态 0 未删除  1 删除")
	private Integer isdelete;

}