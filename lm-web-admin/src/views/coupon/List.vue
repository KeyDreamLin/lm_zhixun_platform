<template>
    <div>
        <h1>我是优惠券管理-Conpue</h1>

        当前登录的用户是:{{ store.state.user.username }}
        <button style="background:red ;" @click="test_toLogout">退出登录</button>
        <button @click="testttt">测试测试</button>
        <lm-drawer title="我是修改密码" ref="testRef">
            <template #default>
                sss<input>
                sss<input>
                sss<input>
                sss<input>
                sss<input>
            </template>
        </lm-drawer>

    </div>

</template>
<script setup>
import store from '@/store';
import router from '@/router';
import lm_request from '@/utils/request.js'
import bannerService from "@/services/banner/BannerService.js"
import LmDrawer from '@/components/LmDrawer.vue';
import { ref } from 'vue';
async function test_toLogout() {
    let serverLogout = await store.dispatch("user/toLogout");
    console.log("index______________", serverLogout);
    if (serverLogout.code == 200) {
        alert(serverLogout.data);
        router.push("/toLogin");
    }
}
const testRef = ref(null);
async function testttt() {
    let serverRes = await lm_request.post("/menu/tree");
    console.log("testttt______________", serverRes);
    testRef.value.open();
}
</script>