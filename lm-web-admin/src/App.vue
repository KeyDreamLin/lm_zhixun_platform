<template>
<!-- 这个是一个什么缓存组件 -->
  <!-- <keep-alive> -->
    <router-view></router-view>
  <!-- </keep-alive> -->
</template>

<script lang="ts" setup>
import { ref } from "vue";
const value = ref(new Date());
</script>
