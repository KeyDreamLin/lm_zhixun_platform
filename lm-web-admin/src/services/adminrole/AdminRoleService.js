import lm_request from '@/utils/request.js'
export default {

    /**
     * 查询后台权限管理列表信息
     * @param {*} user 
     * @returns 
     */
     findadminrolesList() {
        let temp = lm_request.get("/adminroles/load");
        return temp;
    }

}