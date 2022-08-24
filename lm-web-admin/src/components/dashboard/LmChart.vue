<template>
    <!-- 图表组件 -->
    <el-card class="mt-5">
        <template #header>
            <div class="flex flex-row justify-between align-center ">
                <span>统计订单</span>
                <div>
                    <el-check-tag checked class="mr-1">近一个月</el-check-tag>
                    <el-check-tag checked class="mr-1">近一周</el-check-tag>
                    <el-check-tag checked class="mr-1">近一三</el-check-tag>
                </div>
            </div>
        </template>
        <div :id="chartId" style="width: 100%; height:400px ;"></div>
    </el-card>
</template>
<script setup>
import * as echarts from 'echarts';
import { onMounted } from 'vue';

// 子组件向父组件提供属性
const props = defineProps({
    chartId: {
        type: String,
        default: "test"
    },
});
onMounted(() => {
    initChart();
    // console.log(document.getElementById(props.chartId));
});
function initChart() {
    // 如果想要在vue里面获取dom对象，那么dum对象有点要在mounted执行之后才可以获取  这个是唯一需要注意的
    var myChart = echarts.init(document.getElementById(props.chartId));
    myChart.setOption({
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        xAxis: {
            data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
        },
        yAxis: {},
        series: [
            {
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }
        ]
    });
}
</script>
<style scoped>
</style>