import lm_request from '@/utils/request.js'
export default {
    saveupdate(userAdminData) {
        let temp = lm_request.post("/adminuser/saveupdate",userAdminData);
        return temp;
    },
    /**
     * 查询后台用户管理列表信息并分页
     * @param {查询vo} adminUserQueryVo 
     * @returns 
     */
    findAdminUsers(adminUserQueryVo){
        let temp =lm_request.post("/adminuser/list", adminUserQueryVo);
        return temp;
    },
    /**
     * 根据后台用户管理id删除后台用户管理
     * @param {*} id 
     * @returns 
     */
    deleteAdminUserById(id){
        let temp =lm_request.post("/adminuser/delete/"+id);
        return temp;
    },
    /**
    * 根据后台用户管理ids批量删除后台用户管理
    * @param {batchIds 批量删除} params 
    * @returns 
    */
    delAdminUser(params={batchIds:"",id:""}){
        if(params.batchIds==""){return;}
        console.log(params);
        let temp = lm_request.post("/adminuser/delBatch/",params);
        return temp;
    },
    /**
    * 后台用户状态更新
    * isdelete、status
    * @param {id 通过id更新} params 
    * @returns 
    */
    updateAdminUser(params={id:""}){
        if(params.id==""){return;}
        let temp = lm_request.post("/adminuser/update", params);
        return temp;
    }
}