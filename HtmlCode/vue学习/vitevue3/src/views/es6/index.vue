<template >
  <div>es6语法</div>
</template>
<script setup>
import { ref } from "@vue/reactivity";

    function init() {
        // var 会出现变量穿透的问题，正常只能在for里面生效，但是在for外面依然能获取到
        for (var aa = 0; aa <= 10; aa++) {
            console.log(aa);
        }
        console.log(aa);
        // 这个是常量 ，不能够赋值
        const bb = 123;
        // let具有作用域，和java的变量一样
        let cc = 456;

        const userinfo={
            username:"是小郭呀",
            massage:"好可爱耶"
        }
        // 不能这样去修改
        // userinfo ={massage="给小马抓走"}
        // 但是可以这样
        userinfo.massage="给小马抓走";
        console.log(userinfo);
    }
    init();
</script>
<style >
</style>