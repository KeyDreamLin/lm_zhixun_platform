<template>
   <div class="lm-tag-list" :style="{ 'left': $store.state.menu.menuWidth }">
      我是标签用于切换仪表盘内容
      {{ $store.state.menu.menuWidth }}
      {{ $store.state.menu.isCollapse }}
   </div>
</template>
<style scoped>
.lm-tag-list {
   background: #eee;
   height: 40px !important;
   position: fixed;
   left: 210px;
   right: 0;
}
</style>