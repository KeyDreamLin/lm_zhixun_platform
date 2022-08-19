// 导入vue-router，引入createRouter和createWebHashHistory方法
//import { createRouter, createWebHashHistory } from 'vue-router'
import { createRouter, createWebHistory } from 'vue-router'
import store from '@/store'
import Index from '@/views/LmAdmin.vue';


// 自定义后台子路由
const routes_children = [
    {
        path: "/",
        name: "dashboard",
        meta: { title: "控制面板" },
        component: () => import("@/views/dashboard/Index.vue"),
    },
    {
        path: "/user/list",
        name: "userList",
        meta: { title: "用户列表" },
        component: () => import("@/views/user/List.vue"),
    },
    {
        path: "/user/add",
        name: "userAdd",
        meta: { title: "用户添加" },
        component: () => import("@/views/user/Add.vue"),
    }
];

//4 :定义路由配置规则
const routes = [
    {
        path: "/",
        meta: { title: "首页" },
        name: "adminIndex",
        component: Index,
        // 导入配置
        children: routes_children
    },
    {
        path: "/:pathMatch(.*)*",
        meta: { title: "404" },
        name: "NotFound",
        component: () =>
            import('@/views/error/_404.vue'),
    },
    {
        path: "/login",
        meta: { title: "登录" },
        name: "login",
        component: () =>
            import('../views/Login.vue'),
    }, {
        path: "/toLogin",
        redirect: "/login"
    },
];

// 创建路由对象
const router = createRouter({
    // createWebHashHistory() 路由访问模式，hash模式 /#/路由地址
    //history: createWebHashHistory(),
    // createWebHistory 路由访问模式， /路由地址 
    history: createWebHistory(),
    routes
});


router.beforeEach((to, from, next) => {
    // 如果是登录页面就不拦截
    if (to.path == "/login") {
        next();
    } else {
        // 开始检验是否登录。如果没有登录直接退出
        const isLogin = store.getters["user/isLogin"];
        // 这里在未登录之前，你在地址栏输入啥都会跳转到登录页面，登录后输入路由以外的地址会跳到404
        if (!isLogin) {
            // 重定向到登录去
            next("/toLogin");
        }
    }

    // 登录就直接访问
    next();
});

router.afterEach((to, from) => {
    // 标题切换
    document.title = to.meta.title + "-LmAdmin-后台管理系统" || "LmAdmin-后台管理系统";
});

// 导出路由对象
export default router