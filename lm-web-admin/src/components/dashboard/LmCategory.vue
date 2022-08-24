<template>
    <el-row :gutter="20" class="mt-5">
        <el-col :span="3" v-for="(item, index) in iconNavs">
            <el-card class="box-card " @click="router.push(item.path)">
                <div class="flex flex-col items-center">
                    <el-icon :size="26" :color="item.color">
                        <component :is="item.icon" />
                    </el-icon>
                    <span class="mt-3 text-sm font-bold text-gray-600 ">{{ item.title }}</span>
                </div>
            </el-card>
        </el-col>

    </el-row>
</template>
<script setup>
import router from '@/router';
import { reactive } from 'vue';
const iconNavs = reactive([{
    icon: "user",
    color: "#585e92",
    path: "/category/list",
    title: "分类"
}, {
    icon: "shopping-cart",
    color: "#65689f",
    path: "/product/list",
    title: "商品"
}, {
    icon: "tickets",
    color: "#7474b0",
    path: "/user/list",
    title: "分类"
}, {
    icon: "chat-dot-square",
    color: "#7e7ebb",
    path: "/comment/list",
    title: "评价"
}, {
    icon: "picture",
    color: "#8389c7",
    path: "/image/list",
    title: "图库"
}, {
    icon: "bell",
    color: "#9795d4",
    path: "/notice/list",
    title: "公告"
}, {
    icon: "set-up",
    color: "#a2a1dc",
    path: "/notice/list",
    title: "安装"
}, {
    icon: "files",
    color: "#b5aee4",
    path: "/coupon/list",
    title: "优惠券"
}]);
        
</script>
<style scoped>
.box-card {
    cursor: pointer;
}
.box-card:hover {
    /* 四周阴影 */
    box-shadow:  0px 0px 10px #b5aee4;
}
</style>
