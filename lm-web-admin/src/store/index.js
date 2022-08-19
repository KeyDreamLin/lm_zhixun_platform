import { createStore } from 'vuex'
// 持久化管理信息
import VuexPersistence from 'vuex-persist'
import user from "@/store/modules/user.js"
import menu from "@/store/modules/menu.js"

// 本地缓存vuex管理信息
// 为什么要适应vuex-persist组件，因为vuex数据库如果不持久化有一个bug
// 当然用户刷新F5或者右键刷新的时候，vuex数据就会自动丢失。
const vuexLocal = new VuexPersistence({
    key: "lm-admin-web-vuex",
    storage: window.localStorage
})


// 创建一个新的 store 实例
// 创建状态管
const store = createStore({
    plugins: [vuexLocal.plugin],
    modules: {
        user,
        menu
    }
})

// 导出状态管理
export default store;