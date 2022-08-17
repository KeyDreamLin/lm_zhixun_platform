import lm_request from '@/utils/request.js'
export default {

    /**
     * 登录逻辑
     * @param {} user 
     * @returns 
     */
    createCaptcha() {
        let temp = lm_request.post("/captcha", { isToken: false });
        return temp;
    }



}