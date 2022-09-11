import lm_request from '@/utils/request.js'
export default {
    saveupdate(userAdminData) {
        let temp = lm_request.post("/adminuser/saveupdate",userAdminData);
        return temp;
    },
}