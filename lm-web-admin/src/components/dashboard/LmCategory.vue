<template>
    <el-row :gutter="20" class="mt-5">
        <template v-if="loading">
            <el-col :span="3" v-for="(item, index) in iconNavs">
                <el-card class="box-card " @click="router.push(item.path)">
                    <el-skeleton>
                        <template #template>
                            <div class="flex flex-col items-center">
                                <el-skeleton-item variant="text" style="width: 30%; height: 26px; " />
                                <el-skeleton-item class="mt-3" variant="text" style="width: 30%; height: 15px; " />
                            </div>
                        </template>
                    </el-skeleton>
                </el-card>
            </el-col>
        </template>
        <!-- 上骨架 下主体 -->
        <template v-else>
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
        </template>
    </el-row>
</template>
<script setup>
import router from '@/router';
import { reactive, ref } from 'vue';
// 用于骨架屏
var loading = ref(true);
setTimeout(() => {
    loading.value = false;
}, 600)


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
    box-shadow: 0px 0px 10px #b5aee4;
}
</style>
