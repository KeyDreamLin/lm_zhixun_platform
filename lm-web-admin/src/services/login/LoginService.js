import lm_request from '@/utils/request.js'
export default {

    /**
     * 登录逻辑
     * @param {} user 
     * @returns 
     */
    toLogin(user) {
        let temp = lm_request.post("/login/toLogin", user, { isToken: false });
        return temp;
    }



}