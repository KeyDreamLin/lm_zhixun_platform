package com.lm.entity.vo.adminroles;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于保存角色权限
 * 创建人:Lm<br/>
 * 时间：2022-09-14 <br/>
 *
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRolesSavePermissionVo implements java.io.Serializable  {
    // 角色id
    private String role_id;
    // 角色权限 id
    private String permission_idStr;
}