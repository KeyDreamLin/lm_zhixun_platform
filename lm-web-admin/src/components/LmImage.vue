<template>
    <!-- before-close关闭方法前的回调 用于确认是否关闭 -->
    <div class="lm-dialog-main">
        <el-dialog v-model="isShowDialog" :top="[width == '70%' ? '5vh' : '15vh']" :title="title" :width="width"
            :fullscreen="fullscreen" :before-close="closeEvent" center :lock-scroll="false">
            <!-- 内容区 -->
            <div class="lm-dialog-body" :class="[fullscreen ? 'lm-dialog-body_full' : '']">
                <el-container>

                    <el-header style="padding: 0; height: 100%;" class="mb-3">
                        <div class="flex">
                            <el-button type="primary" class="mr-1" >
                                <el-icon class="ml-3"><DocumentAdd /></el-icon>
                                <sapn>添加分组</sapn>
                            </el-button>
                            <el-button type="primary" class="mr-3" @click="upImageEvent" >
                                <sapn>点击上传文件</sapn>
                                <el-icon class="ml-3"><DocumentAdd /></el-icon>
                            </el-button>
                            <el-input 
                                class="mr-3" 
                                style="width:30%"
                                placeholder="请输入关键字搜索..."
                                maxlength="10"
                                show-word-limit
                            ></el-input>
                            <el-button class="pr-2 pl-2" type="primary" icon="Search"/>
                        </div>
                    </el-header>

                    <el-container>
                        <el-aside width="200px">
                            <ul>
                                <li class="lm-cate-item__box">
                                    <a href="#">全部</a>
                                    <el-icon class="del_iocn"><Delete /></el-icon>
                                </li>
                                <li class="lm-cate-item__box">
                                    <a href="#">未分组</a>
                                    <el-icon class="del_iocn"><Delete /></el-icon>
                                </li>
                                <li class="lm-cate-item__box">
                                    <a href="#">轮播图</a>
                                    <el-icon class="del_iocn"><Delete /></el-icon>
                                </li> 
                                <li class="lm-cate-item__box">
                                    <a href="#">导航</a>
                                    <el-icon class="del_iocn"><Delete /></el-icon>
                                </li>
                                <li class="lm-cate-item__box">
                                    <a href="#">课程封面</a>
                                    <el-icon class="del_iocn"><Delete /></el-icon>
                               </li>
                            </ul>
                        </el-aside>
                        <el-container>
                            <el-main>
                                <div style="max-height: 400px;">
                                    <el-row :gutter="20" >
                                        <el-col class="mb-[10px]" v-for="i in 100" :span="3">
                                            <div class="lm-img-item__box"  :class="[selectImageIndex==i?'active':'']" @click="selectImageEvent(i)">
                                                <el-avatar shape="square" v-if="i%3==0" :size="100" fit="contain" src="https://vkceyugu.cdn.bspapp.com/VKCEYUGU-09e62055-ce24-4aa7-9485-0bde1484b6b9/1543fe97-0934-477c-b3eb-ad7774735aa0.png" />
                                                <el-avatar shape="square" v-if="i%3==1" :size="100" fit="contain" src="https://7463-tcb-k0u9wigxhygf3vdfc9501-ce7297-1308187260.tcb.qcloud.la/2021/12/24/35103809-76714418-16403090256433530302.png.png" />
                                                <el-avatar shape="square" v-if="i%3==2" :size="100" fit="contain" src="https://7463-tcb-xbyxeeqfphyrnbaae7dbd-322b87-1308187260.tcb.qcloud.la/2022/04/24/77482552-49723568-icon_dayao.png" />
                                                <span class="lm-image-tag-select" >
                                                    <el-icon><Check /></el-icon>
                                                </span>
                                            </div>
                                        </el-col>
                                    </el-row>
                                </div>
                            </el-main>
                            <el-footer class="flex items-center pl-1">
                                    <!--  :page-count="10"  总页数 -->
                                    <el-pagination
                                        @current-change="pageEvent" 
                                        :page-count="10" background
                                        layout="prev, pager, next , jumper" 
                                        :total="1000"
                                        :pager-count="5" />
                            </el-footer>
                        </el-container>
                    </el-container>
                </el-container>
            </div>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="closeEvent">关闭</el-button>
                    <el-button @click="submitEvent" type="primary">确定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
    <lm-drawer ref="DrawerRef" title="文件上传">
        <lm-uploa-File></lm-uploa-File>
    </lm-drawer>
    
</template>

<script setup>
import { LmMessageConfirm } from '@/utils';
import { ref } from 'vue'
const DrawerRef = ref(null);
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
// 选中图片的索引
const selectImageIndex = ref(-1);
// 图片选中事件
const selectImageEvent = (index) =>{
    selectImageIndex.value = index;
}
const upImageEvent = () => {
    DrawerRef.value.open();
}
// 将子组件的click方法抛出去给父组件 然后可以通过 @submit 来绑定方法
const emit = defineEmits(["submit"])
/* ****click事件**** */

</script>
<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}

.lm-dialog-main {}

.lm-dialog-main :deep(.el-dialog__body) {
    padding-top: 10px;
    padding-bottom: 10px;
}

.lm-dialog-body {
    max-height: calc(100vh - 10vh - 160px);
    overflow-y: auto;
}


.lm-dialog-body_full {
    max-height: calc(100vh - 140px);
}
.lm-cate-item__box{
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #e4e4e4;
}
.lm-cate-item__box > a{
    display: block;
    flex-grow: 1;
    padding: 10px;
}
.lm-cate-item__box:hover .del_iocn {
    display: block;
}
.lm-cate-item__box>.del_iocn{
    display: none;
    font-size: 16px;
    line-height: 16px;
}


/* 图片选中标签 */
.lm-image-tag-select{
    display: none;
    color: #55ff00;
    font-size: 50px;
    position: absolute;
}
.lm-img-item__box{
    @apply bg-white w-[100px] h-[100px] flex justify-center items-center;
    position: relative;
    cursor: pointer;
}
.lm-img-item__box.active>.lm-image-tag-select{
    display: block;
}

</style>