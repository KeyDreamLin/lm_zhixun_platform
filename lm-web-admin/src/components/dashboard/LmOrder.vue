<template>
    <el-row :gutter="20">
        <el-col :span="6" v-for="i in 4">
            <el-card class="box-card">
                <div class="flex justify-between">
                    <span class="text-sm text-gray-400">订单支付</span>
                    <el-tag type="success">年</el-tag>
                </div>
                <span class="text-2xl text-gray-700">
                    <lm-num :value="testData1"></lm-num>
                </span>
                <el-divider style="border-top: 0.8px #f5f5f5 solid; " direction="horizontal" content-position="left" />
                <div class="flex justify-between">
                    <span class="text-sm text-sm-800">总支付订单</span>
                    <span>
                        <lm-num :value="testData2"></lm-num>
                    </span>
                </div>
            </el-card>
        </el-col>

    </el-row>
</template>
<script setup>
    const testData1 = 488;
    const testData2 = 1888;

</script>
<style scoped>
</style>
