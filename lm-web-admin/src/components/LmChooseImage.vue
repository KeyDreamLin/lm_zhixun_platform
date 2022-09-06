<template>
    <div class="lm-choose__box" @click="openEvent">
        <el-icon :size="30">
            <Plus />
        </el-icon>
    </div>
    <lm-drawer ref="DrawerRef" title="文件上传">
        <lm-uploa-File></lm-uploa-File>
    </lm-drawer>
    <lm-image ref="Upimage" width="70%"></lm-image>
</template>

<script setup>
import { ref } from 'vue';
const DrawerRef = ref(null);
const Upimage = ref(null);

const openEvent = () => {
    Upimage.value.open();
}
</script>

<style scoped>
.lm-choose__box {
    width: 100px;
    height: 100px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #d4d4d4;
    border-radius: 5px;
    border: 1px solid #d4d4d4;
    cursor:pointer;
}

.lm-choose__box:hover {
    background: #b5aee411;
}
</style>