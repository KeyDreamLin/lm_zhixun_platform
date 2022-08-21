import { ref, reactive, onMounted, computed } from 'vue'
// 导入状态管理 可以拿到状态管理的所有东西
import { useStore } from 'vuex';
// 用于路由对象 对路由进行操作
import { useRouter } from 'vue-router';
// 用于获取当前路由的状态和地址
import { useRoute } from 'vue-router';
export default function () {
    // 状态管理 可以获取数据 也可以对其操作
    const store = useStore();
    // 用于路由对象 对路由进行操作
    const router = useRouter();
    // 用于获取当前路由的状态和地址
    const route = useRoute();
    // 菜单数据 
    const menuTerrData = computed(() => store.state.menu.serverMenuTreeData);
    // 激活菜单项 传入路由path
    const defaultActive = computed(() => { return route.path; });
    onMounted(async () => {
        // 获取菜单树
        // await store.dispatch("menu/findServerMenuTree");
        // menuTerrData.value = store.getters["menu/getMenuTree"];
        // menuTerrData.value = store.state.menu.serverMenuTreeData;
        // console.log("==================",menuTerrData);
        // console.log("==================",defaultActive);
        // console.log(defaultActive);
    })
    // 菜单选中项事件
    const EventMenuSelect = (indexPath) => {
        console.log(indexPath);
        router.push(indexPath);
    }

    return {
        menuTerrData,
        EventMenuSelect,
        defaultActive,
    }
}