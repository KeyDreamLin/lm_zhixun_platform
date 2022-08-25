<template>
    <el-row :gutter="20">
        <template v-if="loading">
            <el-col :span="6" v-for="i in 4">
                <el-skeleton>
                    <template #template>
                        <el-card class="box-card">
                            <div class="flex justify-between">
                                <el-skeleton-item variant="text" style="width: 30%;height: 15px; " />
                                <el-skeleton-item variant="text" style="width: 30%;" />
                            </div>
                            <span class="text-2xl text-gray-700">
                                <el-skeleton-item variant="text" style="width: 30%; height: 20px; " />
                            </span>
                            <el-divider style="border-top: 0.8px #f5f5f5 solid; " direction="horizontal"
                                content-position="left" />
                            <div class="flex justify-between">
                                <el-skeleton-item variant="text" style="width: 30%; height: 15px;" />
                                <el-skeleton-item variant="text" style="width: 30%;height: 15px; " />
                            </div>
                        </el-card>
                    </template>
                </el-skeleton>
            </el-col>
        </template>
        <!-- 上骨架 下主体 -->
        <template v-else>
            <el-col :span="6" v-for="i in 4">
                <el-card class="box-card">
                    <div class="flex justify-between">
                        <span class="text-sm text-gray-400">订单支付</span>
                        <el-tag type="success">年</el-tag>
                    </div>
                    <span class="text-2xl text-gray-700">
                        <lm-num :value="testData1"></lm-num>
                    </span>
                    <el-divider style="border-top: 0.8px #f5f5f5 solid; " direction="horizontal"
                        content-position="left" />
                    <div class="flex justify-between">
                        <span class="text-sm text-sm-800">总支付订单</span>
                        <span>
                            <lm-num :value="testData2"></lm-num>
                        </span>
                    </div>
                </el-card>
            </el-col>
        </template>

    </el-row>

</template>
<script setup>
import { reactive, ref } from 'vue'
// 用于骨架屏
var loading = ref(true);
setTimeout(() => {
    loading.value = false;
}, 600)

const testData1 = 488;
const testData2 = 1888;

</script>
<style scoped>
</style>
