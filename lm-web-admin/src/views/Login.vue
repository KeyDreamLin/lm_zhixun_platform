<template>
    <div class="login_box">
        <div class="login_main__box">
            <div class="login_main_title_box">
                <span>
                    <!--  -->
                    <el-icon>
                        <ElementPlus />
                    </el-icon>
                </span>
            </div>
            <el-form :model="Login_UserData" ref="Login_userFormRef" :rules="userLoginRules" class="login_main_form">
                <el-form-item prop="userName">
                    <el-input v-model="Login_UserData.username" placeholder="请输入账号" class="login_main_form_input">
                        <template #prefix>
                            <el-icon>
                                <User />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input v-model="Login_UserData.password" placeholder="请输入密码" show-password
                        class="login_main_form_input" @keydown.enter = "LoginEvent">
                        <template #prefix>
                            <el-icon>
                                <Lock />
                            </el-icon>
                        </template>
                    </el-input>
                </el-form-item>

                <el-form-item prop="code">
                    <el-input v-model="Login_UserData.code" placeholder="请输入验证码" class="login_main_form_input" @keydown.enter = "LoginEvent">
                        <template #prefix>
                            <el-icon>
                                <EditPen />
                            </el-icon>
                        </template>
                        <template #suffix>
                            <img class="code" @click="createCaptchaEvent" :src="captcha_imgData">
                        </template>
                    </el-input>
                </el-form-item>

                <el-button @click="LoginEvent" :loading="login_loading"  >登录</el-button>
            </el-form>
        </div>
    </div>
</template>

<script setup>
import userLogin from '@/api/UserLogin.js';
// 把需要暴露的方法和相应属性全部导入
const {// 对象解构
    Login_userFormRef,
    Login_UserData,
    LoginEvent,
    userLoginRules,
    createCaptchaEvent,
    captcha_imgData,
    login_loading,
} = userLogin();
</script>

<style>
.login_box {
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    /* background-image: linear-gradient(-225deg, #a7a6cb 0%, #8989ba 52%, #8989ba 100%); */
    background-image: linear-gradient(to right,
            #505285 0%,
            #585e92 12%,
            #65689f 25%,
            #7474b0 37%,
            #7e7ebb 50%,
            #8389c7 62%,
            #9795d4 75%,
            #a2a1dc 87%,
            #b5aee4 100%);
}

.login_main__box {
    width: 350px;
    border-radius: 20px;
    /* 边框发白光的效果 */
    box-shadow: 0 0 1em #eeeeff;
    display: flex;
    flex-flow: column;
    padding: 20px 40px 40px;
}

.login_main_form {
    display: flex;
    flex-flow: column;
    justify-content: center;
}

.login_main_form_input {
    margin-bottom: 10px;
}

.login_main_title_box {
    width: 100%;
    display: flex;
    justify-content: center;
    padding: 10px 0 10px;
    font-size: 30px;
    font-weight: 800;
    color: #fff;
}

.code {
    position: absolute;
    right: 0;
    width: 86px;
    border-radius: 0 4px 4px 0   ;

}

/* 覆盖element的样式 */
.el-input__wrapper {
    background: none;
}

.el-input__prefix {
    color: #fff;
}

.el-input__inner {
    color: #fff;
}

.el-button {
    background: none;
    color: #fff;
}

.el-icon {
    color: #fff;
}

.el-form-item__error {
    color: #FF0000;
    font-weight: 500;
    font-size: 15px;
    padding: 0;
}
</style>
