<template>
    <!-- 订单组件 -->
    <lm-order></lm-order>
    <!-- 分类组件 -->
    <lm-category></lm-category>
    <el-row :gutter="20">
        <el-col :span="12" :offset="0">
            <!-- 统计组件 -->
            <lm-chart chartId="chartId1"></lm-chart>
        </el-col>
        <el-col :span="12" :offset="0">
            <!-- 统计组件 -->
            <lm-chart chartId="chartId2"></lm-chart>
        </el-col>
    </el-row>



    <!-- 其他交易信息 -->
    <lm-card></lm-card>
</template>
<script setup>
import LmOrder from '@/components/dashboard/LmOrder.vue';
import LmCategory from '@/components/dashboard/LmCategory.vue';
import LmChart from '@/components/dashboard/LmChart.vue';
import LmCard from '@/components/dashboard/LmCard.vue';
</script>