import lm_request from '@/utils/request.js'
export default {

    /**
     * 查询整个权限表 以tree的方式返回
     * @returns 菜单和权限树
     */
    permissionTree() {
        let temp = lm_request.post("/adminpermission/tree");
        return temp;
    },
    /**
    * 保存和修改后台权限管理
    * @returns 
    */
    saveupdateAdminPermission(params={}){
        let temp = lm_request.post("/adminpermission/saveupdate",params);
        return temp;
    },
    deleteAdminPermissionById(id=""){
        if(!id){return}
        let temp = lm_request.post(`/adminpermission/delete/${id}`);
        return temp;
    }
}