<template>
    <!-- 图表-饼图图组件 -->
    <template v-if="loading">
        <el-card class="mt-5">
            <template #header>
                <el-skeleton animated >
                    <template #template>
                        <div class="flex flex-row justify-between align-center ">
                            <el-skeleton-item variant="text" style="width: 64px; height: 28px; " />
                            <div>
                                <el-skeleton-item variant="button" class="mr-1" style="width: 86px; height: 29px; " />
                                <el-skeleton-item variant="button" class="mr-1" style="width: 86px; height: 29px; " />
                                <el-skeleton-item variant="button" class="mr-1" style="width: 86px; height: 29px; " />
                            </div>
                        </div>
                    </template>
                </el-skeleton>
            </template>
            <el-skeleton animated>
                <template #template>
                    <el-skeleton-item variant="image" style="width: 100%; height:400px ;" />
                </template>
            </el-skeleton>
        </el-card>
    </template>
    <!-- 上骨架 下主体 这里的图表不能使用v-if因为需要预先加载数据 v-show是会把代码渲染上去但是不会显示 v-if是代码都不会渲染上去 -->
    <el-card class="mt-5" v-show="!loading">
        <template #header>
            <div class="flex flex-row justify-between align-center ">
                <span>统计订单</span>
                <div>
                    <el-check-tag checked @click="selectEvent(1)" class="mr-1">近一个月</el-check-tag>
                    <el-check-tag checked @click="selectEvent(2)" class="mr-1">近一周</el-check-tag>
                    <el-check-tag checked @click="selectEvent(3)" class="mr-1">近一三</el-check-tag>
                </div>
            </div>
        </template>
        <div :id="chartId" ref="charRef" style="width: 100%; height:400px ;"></div>
    </el-card>
</template>
<script setup>
import { useResizeObserver } from '@vueuse/core';
import * as echarts from 'echarts';
import { onBeforeUnmount, onMounted, ref } from 'vue';
// 用于骨架屏
var loading = ref(true);
setTimeout(() => {
    loading.value = false;
}, 600)

const charRef = ref(null);
let myChart = null;
// 子组件向父组件提供属性
const props = defineProps({
    chartId: {
        type: String,
        default: "test"
    },
});

onMounted(() => {
    // 模拟服务器传过来的数据
    let serverRet = {
        title: {
            text: "测试饼图",
            subtext: "数据"
        },
        data: {
            name: "数据标题",
            data: [
                { value: 1048, name: 'Search Engine' },
                { value: 735, name: 'Direct' },
                { value: 580, name: 'Email' },
                { value: 484, name: 'Union Ads' },
                { value: 300, name: 'Video Ads' }
            ],
        }
    };
    initChart();
    updataChart(serverRet);
    // console.log(document.getElementById(props.chartId));
});
// 初始化图表组件
function initChart() {
    // 如果想要在vue里面获取dom对象，那么dum对象有点要在mounted执行之后才可以获取  这个是唯一需要注意的
    myChart = echarts.init(document.getElementById(props.chartId));
}
// 更新图表数据
function updataChart(serverRet) {
    // 如果没有初始化到的话就重新初始化一次呗
    if (!myChart) { initChart() }
    let option = {
        title: {
            text: serverRet.title.text,
            subtext: serverRet.title.subtext,
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        series: [
            {
                name: serverRet.data.name,
                type: 'pie',
                radius: '50%',
                data: serverRet.data.data,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 执行统计报表动画, 切记动画是统计报表
    myChart.showLoading();


    // 统计报表的类型和数据
    myChart.setOption(option);
    //动画执行结束 哈哈哈给放个延时不然看起来怪怪的
    setTimeout(() => {
        myChart.hideLoading();
    }, 100)
}
// 选择统计时间事件
const selectEvent = (num) => {
    let serverRet = {
        title: {
            text: "测试饼图",
            subtext: "数据"
        },
        data: {
            name: "数据标题",
            data: [
                { value: (1048 / (num * 5)), name: 'Search Engine' },
                { value: 735 - (num * 5), name: 'Direct' },
                { value: 12122 / 580 * (num + 5), name: 'Email' },
                { value: 484 + (num / 25), name: 'Union Ads' },
                { value: 300 / (num + 5), name: 'Video Ads' }
            ],
        }
    };
    updataChart(serverRet);
}
// 监听浏览器的变化 去刷新表格的大小
useResizeObserver(charRef, (e) => {
    // 重新改变统计报表的大小
    myChart.resize();

});
// 在页面离开之前销毁统计报表组件 其实也不用写 写上也行
onBeforeUnmount(() => {
    if (myChart) {
        myChart.dispose();
    }
})
</script>
<style scoped>
</style>