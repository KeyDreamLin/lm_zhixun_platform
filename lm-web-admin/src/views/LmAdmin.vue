<template>
    <div class="lm-box">
        <div class="lm-header">
            <lm-header></lm-header>
        </div>
        <div class="lm-main">
            <div :style="{ 'left': $store.state.menu.menuWidth }">
                <lm-menu></lm-menu>
            </div>
            <div class="lm-main-right" :style="{ 'margin-left': $store.state.menu.menuWidth }">
                <div class="lm-main-right_top__box">
                    <lm-tag-list></lm-tag-list>
                </div>
                <div class="lm-main-right_body__box" :style="{ 'left': $store.state.menu.menuWidth }">
                    <router-view v-slot="{ Component }">
                        <transition name="fade">
                            <keep-alive>
                                <component :is="Component"></component>
                            </keep-alive>
                        </transition>
                    </router-view>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { LmHeader, LmMenu, LmTagList, } from "@/layouts/index.js";
import _404 from "./error/_404.vue";

</script>
<style scoped>
.lm-box {
    display: flex;
    flex-direction: column;
    height: 100vh;
}

.lm-header {
    height: 50px;
    width: 100%;
    background: rebeccapurple;
}

.lm-main {
    display: flex;
    position: relative;
    top: 50px;
}


.lm-main-right {
    flex-grow: 1;
    display: flex;
    flex-direction: column;
}

.lm-main-right_top__box {
    width: 100%;
    height: 50px;
    background: transparent;
}

.lm-main-right_body__box {
    position: fixed;
    top: 100px;

    right: 0;
    bottom: 0;
    padding: 10px;
    overflow: auto;
}
</style>