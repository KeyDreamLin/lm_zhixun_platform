import { ref, reactive, onMounted } from 'vue'
import adminMenuService from "@/services/adminmenu/AdminMenuService.js"
import store from '@/store';
import router from '@/router';
export default function () {

    const menuTerrData = ref([]);

    onMounted(async () => {
        // 获取菜单树
        let serverRes = await adminMenuService.tree();
        menuTerrData.value = serverRes.data;
        console.log(menuTerrData.value);
    })
    // 菜单选中项事件
    const EventMenuSelect = (indexPath) => {
        console.log(indexPath);
        router.push(indexPath);
    }

    return {
        menuTerrData,
        EventMenuSelect,
    }
}