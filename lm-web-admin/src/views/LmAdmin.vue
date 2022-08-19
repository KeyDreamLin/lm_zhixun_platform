<template>
    <el-container>
        <el-header>
            <lm-header></lm-header>
        </el-header>
        <el-container>
            <el-aside class="lm-aside">
                <lm-menu></lm-menu>
            </el-aside>
            <el-main class="lm-main" :style="{ 'left': $store.state.menu.menuWidth}">
                <lm-tag-list></lm-tag-list>
              
                <!-- 仪表盘 -->
                <div class="lm-dashboard__box">
                    <router-view v-slot="{ Component }">
                        <transition name="fade">
                            <keep-alive>
                                <component :is="Component"></component>
                            </keep-alive>
                        </transition>
                    </router-view>
                </div>
            </el-main>
        </el-container>
    </el-container>
</template>

<script setup>
import { LmHeader, LmMenu, LmTagList, } from "@/layouts/index.js";

</script>
<style scoped>
.el-header {
    padding: 0 !important;
    height: 50px !important;
}

.lm-aside {
    width: 210px !important;
    background: plum;
    transition: all 200ms;
}

.lm-main {
    position: absolute;
    top: 50px;
    /* 给menu位置，menu折叠的话这里也需要更改 210-->60 */
    left: 210px;
    right: 0;
    bottom: 0;
    background: peachpuff;
    padding: 0 !important;
}

.lm-dashboard__box {
    margin-top: 40px;
    padding: 20px 20px 20px;
}

/* 隐藏滚动条 */
.lm-main::-webkit-scrollbar {
    width: 0px;
}
</style>