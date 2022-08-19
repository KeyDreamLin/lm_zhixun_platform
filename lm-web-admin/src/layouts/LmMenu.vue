<template>
    <div class="lm-menu__box">
        <!-- collapse-transition关闭折叠动画 不然看起来有点怪 宽度缩小然后再文字消失 -->
        <el-menu default-active="2" :collapse-transition="false" class="el-menu-vertical-demo"  :collapse="$store.state.menu.isCollapse" @open="handleOpen"
            @close="handleClose">
            <el-sub-menu index="1">
                <template #title>
                    <el-icon>
                        <location />
                    </el-icon>
                    <span>控制面板</span>
                </template>
                <el-menu-item index="1-1">
                    <router-link to="/">后台首页</router-link>
                </el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
                <template #title>
                    <el-icon>
                        <User />
                    </el-icon><span>用户管理</span>
                </template>
                <el-menu-item index="2-1">
                    <router-link to="/user/list">用户列表</router-link>
                </el-menu-item>
                <el-menu-item index="2-2">
                    <router-link to="/user/add">用户添加</router-link>
                </el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
</template>


<script setup>
import { ref } from 'vue'
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'

const isCollapse = ref(true)
const handleOpen = (key, keyPath) => {
    console.log(key, keyPath)
}
const handleClose = (key, keyPath) => {
    console.log(key, keyPath)
}
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
