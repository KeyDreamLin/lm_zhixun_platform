<template>
    <el-card class="mt-5">
        <template #header>
            <div class="flex flex-row justify-between">
                <span class="text-xl text-gray-500 font-bold">交易提示</span>
                <el-tag type="danger">
                    需要立即处理得交易订单
                </el-tag>
            </div>
        </template>
        <el-row :gutter="20">
            <el-col :span="6" :offset="0" v-for="(item, index) in serverRet" :key="index">
                <el-card>
                    <div class="flex flex-col items-center justify-center">
                        <span class="text-xl mb-2">
                            <lm-num :value="item.num"></lm-num>
                        </span>
                        <span class="text-sm text-gray-500">{{ item.text }}</span>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </el-card>
</template>
<script setup>
import LmNum from '@/components/LmNum.vue';
// 服务器的模拟数据
let serverRet = [
    { num: 123, text: "待支付" },
    { num: 113, text: "审核中" },
    { num: 673, text: "已下架" },
    { num: 1123, text: "库存告警" },
]
</script>
<style scoped>
</style>