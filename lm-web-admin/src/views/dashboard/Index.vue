<template>
    <!-- 订单组件 -->
    <lm-order></lm-order>
    <!-- 分类组件 -->
    <lm-category></lm-category>
    <el-row :gutter="20">
        <el-col :span="15" :offset="0">
            <!-- 统计组件 -->
            <lm-chart-bar chartId="chartId1"></lm-chart-bar>
        </el-col>
        <el-col :span="9" :offset="0">
            <!-- 统计组件 -->
            <lm-chart-pie chartId="chartId2"></lm-chart-pie>
        </el-col>
    </el-row>
    <el-row :gutter="20">
        <el-col :span="12" :offset="0">
            <!-- 其他交易信息 -->
            <lm-card></lm-card>
        </el-col>
        <el-col :span="12" :offset="0">
            <!-- 其他交易信息 -->
            <lm-card></lm-card>
        </el-col>
    </el-row>


</template>
<script setup>
import LmOrder from '@/components/dashboard/LmOrder.vue';
import LmCategory from '@/components/dashboard/LmCategory.vue';
import LmChartBar from '@/components/dashboard/LmChartBar.vue';
import LmChartPie from '@/components/dashboard/LmChartPie.vue';
import LmCard from '@/components/dashboard/LmCard.vue';
</script>