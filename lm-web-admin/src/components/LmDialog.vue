<template>
    <!-- before-close关闭方法前的回调 用于确认是否关闭 -->
    <div class="lm-dialog-main">
        <el-dialog v-model="isShowDialog" :top="[width=='90%'?'5vh':'15vh']" :title="title" :width="width" :fullscreen="fullscreen"
            :before-close="closeEvent" center :lock-scroll="false">
            <!-- 内容区 -->
            <div class="lm-dialog-body" :class="[fullscreen?'lm-dialog-body_full':'']">
                <slot></slot>
            </div>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="closeEvent">关闭</el-button>
                    <el-button @click="submitEvent" type="primary">确定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
import { LmMessageConfirm } from '@/utils';
import { ref } from 'vue'
// 提供属性
const props = defineProps({
    title: {
        type: String,
        default: "我是默认的标题"
    },
    // 是否全屏
    fullscreen: {
        type: Boolean,
        default: false
    },
    // 宽度 都是官方的默认值
    width: {
        type: String,
        default: "50%"
    },
    
});
const isShowDialog = ref(false);
/* ****内部方法**** */
const open = () => {
    isShowDialog.value = true;
}
const close = () => {
    isShowDialog.value = false;
}
// element Dialog自带的关闭按钮回调事件右上角叉
const closeEvent = async () => {
    let isClose = await LmMessageConfirm("你确定要关闭吗？");
    if (isClose) {
        isShowDialog.value = false;
    }
}
// 抛出句柄 让父组件可以使用里面的方法
defineExpose({
    open,
    close
})
/* ****内部方法**** */

/* ****click事件**** */
const submitEvent = () => {
    emit("submit");
}
// 将子组件的click方法抛出去给父组件 然后可以通过 @submit 来绑定方法
const emit = defineEmits(["submit"])
/* ****click事件**** */

</script>
<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}
.lm-dialog-main {
}
.lm-dialog-main  :deep(.el-dialog__body){
    padding-top: 10px;
    padding-bottom: 10px ;
}
.lm-dialog-body {
    max-height: calc(100vh - 10vh - 160px);
    overflow-y: auto;
}
.lm-dialog-body_full{
    max-height: calc(100vh - 140px);
}
</style>