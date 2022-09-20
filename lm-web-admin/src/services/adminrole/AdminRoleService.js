import lm_request from '@/utils/request.js'
export default {

    /**
     * 查询后台角色管理列表信息
     * @returns 
     */
    findadminrolesList() {
        let temp = lm_request.post("/adminroles/load");
        return temp;
    },
    
    /**
     * 查询后台角色管理列表信息
     * @param {Vo} user 
     * @returns 
     */
    findAdminRolesPage(params) {
        let temp = lm_request.post("/adminroles/list",params);
        return temp;
    },

    /**
     * * 保存和修改后台角色管理
     * @param {AdminRolesVo} params 
     * @returns 
     */
    saveupdateAdminRoles(params={}){
        let temp = lm_request.post("/adminroles/saveupdate",params);
        return temp;
    },
    /**
     * 根据后台角色管理id删除后台角色管理
     * @param {rolesId} id 
     * @returns 
     */
    deleteAdminRolesById(id){
        let temp = lm_request.post("/adminroles/delete/"+id);
        return temp;
    },
    /**
     * 根据角色id查询对应权限列表
     * @param {role_id} p
     */
    findPermissionByRoleId(role_id=""){
        let temp = lm_request.post("/adminroles/findPermissionByRoleId",{role_id:role_id});
        return temp;
    },
    /**
     * 根据角色id查询对应权限列表
     * @param {role_id} p
     */
     savePermissionByRoleId(role_id="",permission_idStr=""){
        let temp = lm_request.post(
            "/adminroles/savePermissionByRoleId",
            {role_id:role_id,permission_idStr:permission_idStr}
        );
        return temp;
    }
}