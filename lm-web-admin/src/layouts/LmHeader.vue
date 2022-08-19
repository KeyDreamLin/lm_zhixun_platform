<template>
    <div class="lm-header">
        <span class="lm-header-logo__box">
            <el-image class="lm-hader-logo_img" style="width: 24px; height: 24px"
                src="../../src/assets/img/logo-w.png" />
            <span class="lm-hader-logo_title">LmAdmin</span>
        </span>
        <el-tooltip effect="dark" content="首页" placement="bottom">
            <el-icon class="lm-icon-btn" @click="handleIndex">
                <HomeFilled />
            </el-icon>
        </el-tooltip>
        <el-tooltip effect="dark" content="折叠" placement="bottom">
            <el-icon class="lm-icon-btn" @click="handleExpand">
                <Fold v-if="$store.state.menu.isCollapse" />
                <Expand v-else />
            </el-icon>
        </el-tooltip>

        <el-tooltip effect="dark" content="刷新" placement="bottom">
            <el-icon class="lm-icon-btn" @click="handleRefresh">
                <Refresh />
            </el-icon>
        </el-tooltip>

        <div class="lm-user__box">
            <el-tooltip effect="dark" content="全屏" placement="bottom">
                <el-icon class="lm-icon-btn" @click="handleFullScreen">
                    <!-- 不是是全屏icon -->
                    <Grid v-if="isFullscreen" />
                    <!-- 全屏icon -->
                    <FullScreen v-else />
                </el-icon>
            </el-tooltip>

            <el-dropdown @command="headerUserEvent">
                <span class="lm-dropdown-link">
                    <el-avatar :size="28" src="../../src/assets/img/img_01.jpg"></el-avatar>
                    <span class="lm-user_name">
                        {{ store.state.user.username }}
                    </span>
                    <el-icon class="el-icon--right">
                        <arrow-down />
                    </el-icon>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="UpdataUserPwd">修改密码</el-dropdown-item>
                        <el-dropdown-item command="LogoutUser">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script setup>
import store from '@/store';
import useLmHeader from "@/api/layouts/UseLmHeader.js"
// 把需要暴露的方法和相应属性全部导入
const {// 对象解构
    isFullscreen,
    handleIndex,
    handleExpand,
    handleRefresh,
    handleFullScreen,
    headerUserEvent,
} = useLmHeader();
</script>

<style scoped>
.lm-header {
    background: rebeccapurple;
    height: 50px;
    display: flex;
    align-items: center;
}

.lm-header-logo__box {
    width: 210px;
    display: flex;
    justify-content: center;
    color: #fff;
}

.lm-hader-logo_img {
    margin-right: 10px;
}

.lm-hader-logo_title {
    font-weight: 700;
}


.lm-icon-btn {
    width: 50px;
    height: 50px;
    color: #fff;
    cursor: pointer;
}

.lm-icon-btn:hover {
    background: #a78bfa;
}

.lm-user__box {
    display: flex;
    align-items: center;
    margin-left: auto;
    margin-right: 1.25rem;
}

.lm-dropdown-link {
    color: #fff;
    display: flex;
    align-items: center;
}

.lm-user_name {
    margin-left: 10px;
    margin-right: 10px;
}
</style>