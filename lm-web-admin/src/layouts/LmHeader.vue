<template>
    <div class="lm-header__box">
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
            <!-- 全屏按钮 -->
            <div>
                <el-tooltip effect="dark" content="全屏" placement="bottom">
                    <el-icon class="lm-icon-btn" @click="handleFullScreen">
                        <!-- 不是是全屏icon -->
                        <Grid v-if="isFullscreen" />
                        <!-- 全屏icon -->
                        <FullScreen v-else />
                    </el-icon>
                </el-tooltip>
            </div>

            <!-- 国际化语言切换 -->
            <div class="mr-1 ">
                <el-dropdown >
                    <el-icon class="lm-icon-btn text-[17px]">
                        <Guide />
                    </el-icon>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="setI18n('zh_CN')">中文</el-dropdown-item>
                            <el-dropdown-item @click="setI18n('en_US')">English</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>

            <!-- 头像信息等 -->
            <el-dropdown @command="headerUserEvent">
                <span class="lm-dropdown-link">
                    <el-avatar :size="28" src="../../src/assets/img/img_01.jpg"></el-avatar>
                    <span class="ml-2">
                        {{store.getters["user/getRoleNames"]}}
                    </span>
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
        <lm-drawer title="我是修改密码" @submit="submitUpdataPwdEvent" ref="updataPwdRef">
            <template #default>

                <el-form label-position="right" label-width="100px" ref="updataPwdFromRef" :model="userPwdFormData"
                    :rules="updataPwdRules">
                    <el-form-item label="旧密码：" prop="oldPassword">
                        <el-input v-model="userPwdFormData.oldPassword" placeholder="请输入旧密码">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="新密码：" prop="newPassword">
                        <el-input v-model="userPwdFormData.newPassword" placeholder="请输入新密码">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="确认密码：" prop="ReDoNewPassword">
                        <el-input v-model="userPwdFormData.ReDoNewPassword" placeholder="请再次输入新密码">
                        </el-input>
                    </el-form-item>
                </el-form>

            </template>
        </lm-drawer>
    </div>
</template>

<script setup>
import store from '@/store';
import useLmHeader from "@/api/layouts/UseLmHeader.js"
import LmDrawer from '@/components/LmDrawer.vue';
// 把需要暴露的方法和相应属性全部导入
const {// 对象解构
    isFullscreen,
    handleIndex,
    handleExpand,
    handleRefresh,
    handleFullScreen,
    headerUserEvent,
    submitUpdataPwdEvent,
    updataPwdRef,
    userPwdFormData,
    updataPwdRules,
    updataPwdFromRef,
} = useLmHeader();
// 国际化语言切换
const setI18n = (data) => {
    console.log(data);
    store.commit("i18n/setLang",data);
}


</script>

<style scoped>
.lm-header__box {
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