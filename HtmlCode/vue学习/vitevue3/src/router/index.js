// 导入vue-router，引入createRouter和createWebHashHistory方法
//import { createRouter, createWebHashHistory } from 'vue-router'
import { createRouter, createWebHistory } from 'vue-router'

/*导入spa页面 */
import Es6 from '../views/es6/index.vue'
// import About from '../views/About.vue'
// 路由注册
const routes = [
    // 这里提供了两种路由方式，都差不多吧，反正效果是一样的
   
    {
        path: "/es6",
        name: "es6",
        component: Es6
    },
    {
        path: "/es6/array",
        name: "es6_array",
        component:()=> import ('../views/es6/array.vue')
    },
    {
        path: "/es6/object",
        name: "es6_object",
        component:()=> import ('../views/es6/object.vue')
    },
    {
        path: "/es6/function",
        name: "es6_function",
        component:()=> import ('../views/es6/function.vue')
    },
    {
        path: "/es6/arrayApi",
        name: "es6_arrayApi",
        component:()=> import ('../views/es6/arrayApi.vue')
    },
    {
        path: "/es6/module_",
        name: "es6_module_",
        component:()=> import ('../views/es6/module_.vue')
    },
    {
        path: "/es6/json_",
        name: "es6_json_",
        component:()=> import ('../views/es6/json_.vue')
    },

    {
        path: "/comp/comp",
        name: "comp_button",
        component:()=> import ('../views/comp/comp.vue')
    },

    {
        path: "/computed",
        name: "computed",
        component:()=> import ('../views/computed/index.vue')
    },
    {
        path: "/watch",
        name: "watch",
        component:()=> import ('../views/watch/index.vue')
    },
]

// 创建路由对象
const router = createRouter({
    // createWebHashHistory() 路由访问模式，hash模式 /#/路由地址
    //history: createWebHashHistory(),
    // createWebHistory 路由访问模式， /路由地址 
    history: createWebHistory(),
    routes
})

// 导出路由对象
export default router