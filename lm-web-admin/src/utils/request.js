// 请求工具类
import axios from 'axios'
// 状态管理
import store from '@/store';
// 路由管理 
import router from '@/router';
import errorCode from '@/utils/errorCode';
// 导入弹窗工具类
import { LmMessageError } from "@/utils/index.js";

// create an axios instance
const lm_request = axios.create({
    baseURL: "/admin",
    timeout: 5000,
    isToken: true, // 是否需要带token请求
})

// 添加请求拦截器
lm_request.interceptors.request.use((config) => {
    // console.log("server request yes-->", config);
    if (config.isToken) {
        // 请求头携带token_jj 用于校验登录状态
        config.headers['token_jj'] = store.getters["user/getTokenJj"];
        config.headers['token_user_id'] = store.getters["user/getUserId"];
        config.headers['token_uuid'] = store.getters["user/getTokenUuid"];
        // console.log("携带token",store.getters["user/getTokenJj"]);
        // console.log("server request yes-->", config);

    }
    // console.log("不携带token");
    return config
}, error => {
    console.log("server request error-->", error) // for debug
})


// 添加响应拦截器
lm_request.interceptors.response.use((response) => {
    let res_data = response.data;
    // 因为后端对直接返回string的接口做了处理，使用string发挥的接口到js的时候是个string而不是object，所有需要加个判断然后转化成一下
    // 可以看com.lm.common.ex.handler.GlobalResponseHandler 是直接使用流输出的
    if (typeof res_data === "string") {
        res_data = JSON.parse(res_data);
    }
    if (res_data.code == 200) { return res_data }
    // 业务的错误，全部抛出到页面处理 1、
    // alert("1-----err");
    // 100107
    // console.log(res_data);

    // 用户已在其他地方登录
    if (res_data.code == 100107) {
        // 清除用户在本地的状态
        store.dispatch("user/toLogout");
        // 然后跳转到登录页面
        router.push("/toLogin");
    }

    // 如果第一个为空 那就用第二个
    let errorObj = errorCode[res_data.code] || errorCode["default"];
    // console.log("server response yes-->", errorObj) // 
    return Promise.reject(errorObj);
    // return response;
}, function (err) {
   
    // console.log("server response error-->", err) // 
    if(err.code === "ECONNABORTED"){
        LmMessageError("服务器超时了喂，等等再刷新试试？");
    }
    else if(err.code === "ERR_BAD_RESPONSE"){
        LmMessageError("服务器走丢了喂...");
    }
    console.log("server response error-->", err) // 
    return err;
});
export default lm_request;