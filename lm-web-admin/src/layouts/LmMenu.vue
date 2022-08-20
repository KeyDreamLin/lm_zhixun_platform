<template>
    <div class="lm-menu__box">
        <!-- collapse-transition关闭折叠动画 不然看起来有点怪 宽度缩小然后再文字消失 -->
        <el-menu default-active="2" :collapse-transition="false" class="el-menu-vertical-demo"
            :collapse="$store.state.menu.isCollapse" @select="EventMenuSelect">

            <template v-for="(menu_root, index) in  menuTerrData" :key="menu_root.id">

                <template v-if="menu_root.children.length > 0">
                    <!-- 有子元素的 -->
                    <el-sub-menu :index="menu_root.name">
                        <template #title>
                            <el-icon>
                                <component :is="menu_root.icon" />
                            </el-icon>
                            <span>{{ menu_root.name }}</span>
                        </template>
                        <!-- 遍历子元素 只支持二级 -->
                        <template v-for="(menu_children, index__) in  menu_root.children" :key="menu_children.id">
                            <el-menu-item :index="menu_children.path">
                                <el-icon>
                                    <component :is="menu_children.icon" />
                                </el-icon>
                                <!-- 这里后面用了mentSelect的回调事件函数去做路由转发 -->
                                <!-- <router-link :to="menu_children.path"> -->
                                <span>
                                    {{ menu_children.name }}
                                </span>
                                <!-- </router-link> -->
                            </el-menu-item>
                        </template>
                    </el-sub-menu>
                </template>
                <!-- 无子元素的 -->
                <template v-else>
                    <el-menu-item :index="menu_root.path">
                        <el-icon>
                            <Ticket />
                        </el-icon>
                        <span>
                            {{ menu_root.name }}
                        </span>
                        <!-- <router-link :to="menu_root.path"></router-link> -->
                    </el-menu-item>
                </template>


                <!-- 无子元素 -->
            </template>


        </el-menu>
    </div>
</template>


<script setup>
import { ref } from 'vue'
const test = "add-location";
import useLmMenu from '@/api/layouts/UseLmMenu.js'
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'
const {
    menuTerrData,
    EventMenuSelect,
} = useLmMenu();

</script>

<style>
.el-menu-vertical-demo {
    position: fixed;
    top: 50px;
    left: 0;
    bottom: 0;
    /* 溢出就允许滑动 */
    overflow-y: auto;
}

/* 未折叠的状态 */
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 210px;
}

/* 不显示滚动条 */
.el-menu-vertical-demo::-webkit-scrollbar {
    display: none
}
</style>
