<template>
    <div>
        我是后台首页
        当前登录的用户是:{{store.state.user.username}}
        <button @click="test">12312</button>
    </div>
</template>
<script setup>
import store from '@/store';
import bannerService from "@/services/banner/BannerService.js"
function test(){
     bannerService.findBanners().then((ret)=>{
        console.log(ret);
     });
}
</script>