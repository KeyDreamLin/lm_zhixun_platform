package com.lm.entity.vo.adminroles;

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
 * adminRoles保存或者修改
 * 创建人:Lm<br/>
 * 时间：2022-09-14 <br/>
 *
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRolesSaveUpdateVo implements java.io.Serializable  {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	private String roleCode;

	private String roleName;

	private Integer status;

	private Integer isdelete;

}