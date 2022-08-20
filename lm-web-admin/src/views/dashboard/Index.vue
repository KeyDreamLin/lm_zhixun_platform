<template>
    <div>
        我是仪表盘 mian
        当前登录的用户是:{{ store.state.user.username }}
        <button style="background:red ;" @click="test_toLogout">退出登录</button>
        <button @click="testttt">测试测试</button>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
        <p>123</p>
    </div>
</template>
<script setup>
import store from '@/store';
import router from '@/router';
import lm_request from '@/utils/request.js'
import bannerService from "@/services/banner/BannerService.js"
async function test_toLogout() {
    let serverLogout = await store.dispatch("user/toLogout");
    console.log("index______________", serverLogout);
    if (serverLogout.code == 200) {
        alert(serverLogout.data);
        router.push("/toLogin");
    }
}
async function testttt(){
      let serverRes = await lm_request.post("/menu/tree");
    console.log("testttt______________", serverRes);
      
}
</script>