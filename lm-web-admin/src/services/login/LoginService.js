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
    },
   /**
     * 退出登录
     * @param {} user 
     * @returns 
     */
    toLogout() {
        let temp = lm_request.post("/login/logout");
        return temp;
    },
}