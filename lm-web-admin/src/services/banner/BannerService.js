import lm_request from '@/utils/request.js'
export default {

    /**
     * 查询轮播图管理列表信息并分页
     * @param {*} user 
     * @returns 
     */
    findBanners() {
        let temp = lm_request.post("/banner/load");
        return temp;
    }



}