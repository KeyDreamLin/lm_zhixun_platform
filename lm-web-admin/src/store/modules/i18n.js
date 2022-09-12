import adminMenuService from "@/services/adminmenu/AdminMenuService.js"

export default {
    namespaced: true,
    state() {
        return {
            lang:"zh_CN"
        }
    },
    // 调用的方法commit("名称") 通常用于同步数据、变量
    mutations: {
        setLang(state,data){
            state.lang = data;
            console.log(data);
        }
    },
    // 调用的方法dispatch("名称")  通常用于异步查询
    actions: {
     
    },
    // getters["名称"]  获取数据
    getters: {
        // 获取值
        getLang(state) {
            return state.lang;
        }
    }

}