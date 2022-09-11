<template>
    <div class="lm-wrapper__box">
        <el-drawer v-model="showDrawer" :title="title" :before-close="close">
            <div class="lm-wrapper-body">
                <div class="lm-drawer-main">
                    <slot></slot>
                </div>
                <div class="lm-drawer-footer">
                    <el-button type="primary" @click="submit">提交</el-button>
                    <el-button @click="cancel">取消</el-button>
                </div>
            </div>

        </el-drawer>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { LmMessageSuccess, LmMessageConfirm } from "@/utils/index.js";

const showDrawer = ref(false);

// 提供属性
const props = defineProps({
    title: {
        type: String,
        default: "我是默认的标题"
    },
});

// 将抽屉的click方法抛出去给父组件 然后可以通过 @submit @cancel 来绑定方法
const emit = defineEmits(["submit", "cancel"])

// 打开抽屉
const open = () => {
    showDrawer.value = true;
}
// 关闭抽屉 抽屉绑定了这个方法 也顺便提供给父组件调用
const close = async () => {
    let isClose = await LmMessageConfirm("你确定要关闭吗？");
    if (isClose) {
        showDrawer.value = false;
    }
}
// 关闭抽屉 没有提示 给提交完的用
const closeNoMsg = async () => {
    showDrawer.value = false;
}

// 提交按钮
const submit = () => {
    emit("submit");
}
// 可以抛出也可以直接执行关闭事件
const cancel = () => {
    // emit("cancel");
    close();
}

// 父组件可以通过ref去进行调用value.xxx调用
defineExpose({
    open,
    close,
    closeNoMsg
})
</script>

<style scoped>
.lm-wrapper__box :deep(.el-drawer__header) {
    background: #fafafa;
    padding-bottom: 20px;
    margin-bottom: 10px;
}

.lm-wrapper-body {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    position: relative;
    z-index: 1001;
}

.lm-drawer-main {
    flex-grow: 1;
    overflow-y: auto;
    margin-bottom: 42px;
}

.lm-drawer-footer {
    height: 42px;
    position: fixed;
    bottom: 0;
}
</style>