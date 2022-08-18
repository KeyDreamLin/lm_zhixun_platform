<template>
    <div class="lm-header">
        <span class="lm-header-logo__box">
            <el-image style="width: 40px; height: 40px" src="src/assets/img/log-b.png" />
            <span class="font-bold">LmAdmin</span>
        </span>
        <el-icon class="lm-icon-btn"> <HomeFilled /> </el-icon>
        <el-icon class="lm-icon-btn"> <Fold /></el-icon>
        <el-icon class="lm-icon-btn"><Refresh /></el-icon>

        <div class="lm-user__box">
            <el-icon class="lm-icon-btn"><FullScreen /></el-icon>
        </div>
    </div>
</template>
<style scoped>
.lm-header {
    background: rebeccapurple;
    height: 50px;
    display: flex;
    align-items: center;
}
</style>