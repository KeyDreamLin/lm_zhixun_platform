// 导入vue-router，引入createRouter和createWebHashHistory方法
//import { createRouter, createWebHashHistory } from 'vue-router'
import { createRouter, createWebHistory } from 'vue-router'
import { LmMessageError } from "@/utils/index.js";

import store from '@/store'

import LmAdmin from '@/views/LmAdmin.vue';
import Dashboard from '@/views/dashboard/Index.vue'
import ProductList from '@/views/product/List.vue'
import CategoryList from '@/views/category/List.vue'
import CouponList from '@/views/coupon/List.vue'
import UserList from '@/views/user/List.vue'
import OrderList from '@/views/order/List.vue'
import ImageList from '@/views/image/List.vue'
import NoticeList from '@/views/notice/List.vue'
import LevelList from '@/views/level/List.vue'

import ManagerList from '@/views/manager/List.vue'
import RoleList from '@/views/role/List.vue'
import PermissionList from '@/views/permission/List.vue'
// import { fa } from 'element-plus/es/locale';
// 自定义后台子路由
const routes_children = [
    {
        path: '/',
        name: "dashboard",
        meta: { title: '后台首页' },
        component: Dashboard,
    }, {
        path: '/user/list',
        name: "/user/list",
        meta: { title: '用户管理' },
        component: UserList
    }, {
        path: '/manager/list',
        name: "/manager/list",
        meta: { title: '后台管理员' },
        component: ManagerList
    }, {
        path: '/role/list',
        name: "/role/list",
        meta: { title: '角色管理' },
        component: RoleList
    }, {
        path: '/permission/list',
        name: "/permission/list",
        meta: { title: '权限管理' },
        component: PermissionList
    }, {
        path: '/user/list',
        name: "/user/list",
        meta: { title: '用户管理' },
        component: UserList
    }, {
        path: '/level/list',
        name: "/level/list",
        meta: { title: '会员等级' },
        component: LevelList
    }, {
        path: "/category/list",
        name: "/category/list",
        component: CategoryList,
        meta: { title: "分类列表" }
    }, {
        path: "/coupon/list",
        name: "/coupon/list",
        component: CouponList,
        meta: { title: "优惠券管理" }
    }, {
        path: "/order/list",
        name: "/order/list",
        component: OrderList,
        meta: {
            title: "订单列表"
        }
    }, {
        path: "/image/list",
        name: "/image/list",
        component: ImageList,
        meta: {
            title: "图库列表"
        }
    }, {
        path: "/notice/list",
        name: "/notice/list",
        component: NoticeList,
        meta: {
            title: "公告列表"
        }
    }, {
        path: '/product/list',
        name: "/product/list",
        meta: { title: '产品列表' },
        component: ProductList
    }
];

//4 :定义路由配置规则
const routes = [
    {
        path: "/",
        meta: { title: "首页" },
        name: "adminIndex",
        component: LmAdmin,
        // 导入配置
        // children: routes_children
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
// 动态注册路由方法- 考虑到
// 数据库添加了一个新的地址，但是路由中不存在该地址的时候就需要动态的去加载路由
export function registerRoutes(menuList) {
    // 是否有新的路由
    let isNewRoutes = false;
    console.log("regi、sterRoutes", menuList);
    // 添加路由
    const findAndAddRoutesByMenus = (arr) => {
        arr.forEach(e => {
            // 一定要查询我router.js中routes_children存在的路由，才去绑定，否则不绑定
            let item = routes_children.find(o => o.path == e.path)
            // item存在，并且没有绑定过router.hasRoute，
            if (item && !router.hasRoute(item.path)) {
                // 如果没有绑定，就开始绑定。这里就是adminIndex,代表不论多个子元素，最中绑定全部挂载admin这集
                // 也就意味着：后续所有的子元素，孙子元素等的路由访问，都跳转到LmAdmin.vue的router-view的位置
                router.addRoute("adminIndex", item)
                isNewRoutes = true;
            }

            // 递归，如果当前子菜单还有子元素，
            if (e.children && e.children.length > 0) {
                findAndAddRoutesByMenus(e.children)
            }
        })
    }

    // 开始递归调用动态绑定路由关系
    findAndAddRoutesByMenus(menuList);
    return isNewRoutes;
}

/* 
   这个开关，是用来控制动态路由注册只绑定一次 
   只要不刷新、F5，第一次就是false, 后面永远都是 true
*/
let loadNewRoute = false;
router.beforeEach(async (to, from, next) => {
    const isLogin = store.getters["user/isLogin"];
    
    // 没有登录，强制跳转回登录页
    // 这里在未登录之前，你在地址栏输入啥都会跳转到登录页面，登录后输入路由以外的地址会跳到404
    if (!isLogin && to.path != "/login") {
        LmMessageError("请先登录")
        next("/toLogin")
    }

    // 防止重复登录
    // if (isLogin && to.path == "/login") {
    //     LmMessageError("不要重复登录。请退出账号后再重新登陆！")
    //     // 返回你上一级
    //     next({ path: from.path ? from.path : "/" })
    // }

    let hasNewRoute = false; //F5的问题
    if (isLogin && !loadNewRoute) {
        console.log("1111111");
        // 锁住 不锁起来就会一直访问 这里
        // [Vue Router warn]: Unexpected error when starting the router: Error: Infinite redirect in navigation guardat
        loadNewRoute = true;
        await store.dispatch("menu/findServerMenuTree");
        let MenuTree = store.getters["menu/getMenuTree"];
        hasNewRoute = registerRoutes(MenuTree);
    }
    console.log("loadNewRoute",loadNewRoute);
    // fullPath能缓存路由跳转后面携带的参数  
    // 如果存在新路由就要缓存起来 直接next就会404
    hasNewRoute ? next(to.fullPath) : next();

});

router.afterEach((to, from) => {
    // 标题切换
    document.title = to.meta.title + "-LmAdmin-后台管理系统" || "LmAdmin-后台管理系统";
});

// 导出路由对象
export default router