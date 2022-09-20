import lm_request from '@/utils/request.js'
export default {

    /**
     * 查询菜单树
     * @param {*} user 
     * @returns 
     */
    tree() {
        let temp = lm_request.post("/adminpermission/menu/tree");
        return temp;
    }

}