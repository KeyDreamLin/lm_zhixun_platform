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
    }
}