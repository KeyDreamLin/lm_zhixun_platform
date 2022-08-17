<template >
  <div>module导入导出</div>
</template>
<script setup>
    import { ref } from "@vue/reactivity";
    // 整体导入
    import * as lm_util from "../../module/util.js"
    console.log(lm_util.isEmpty(""));
    console.log(lm_util.isEmpty("123"));
    console.log(lm_util.sum_1111(1,2));

    // // 按需导入
    // import {isEmpty , sum} from "../../module/util.js"
    // console.log(isEmpty(""));
    // console.log(isEmpty("123"));
    // console.log(sum(1,2));
</script>
<style >
</style>