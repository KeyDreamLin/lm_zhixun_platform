<template>
  <!-- 可视区域 -->
  <div class="container">
    <!-- 侧边栏 start -->
    <div class="silder">
      <!-- 侧边栏遮罩 -->
      <div class="silder_wrapper">
        <ul>
          <li class="silder_items">
            <router-link to="/es6">第一课:es6语法</router-link>
            <ul class="silder_sub">
              <li class="silder_items">
                <router-link to="/es6/array">数组解构</router-link>
              </li>
              <li class="silder_items">
                <router-link to="/es6/object">对象解构</router-link>
              </li>
              <li class="silder_items">
                <router-link to="/es6/function">箭头函数</router-link>
              </li>
             
              <li class="silder_items">
                <router-link to="/es6/arrayApi">函数Api</router-link>
              </li>
               <li class="silder_items">
                <router-link to="/es6/module_">模块导入导出</router-link>
              </li>
                    <li class="silder_items">
                <router-link to="/es6/json_">json转换和反转</router-link>
              </li>
            </ul>
          </li>
          <li class="silder_items">
            <router-link to="/computed">第二课:computed语法</router-link>
          </li>
          <li class="silder_items">
            <router-link to="/watch">第三课:watch语法</router-link>
          </li>
          <li class="silder_items">
            <router-link to="/comp/comp">第四课:组件使用</router-link>
          </li>
        </ul>
      </div>
    </div>
    <!-- 侧边栏 end -->

    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>
<script setup>
</script>
<style scoped>
.container {
  display: flex;
}
.silder {
  width: 240px;
  height: 100vh;
}
.silder_wrapper {
  width: inherit;
  height: inherit;
  position: fixed;
  top: 0;
  background: #fff;
}
.silder_items a {
  display: flex;
  align-items: center;
  padding-left: 20px;
  height: 50px;
  transition: 200ms;
  border-bottom: 1px solid rgb(238, 238, 238);
}

.silder_items a:hover {
  color: #fff;
  background: #000;
}
.silder_sub > .silder_items a {
  padding-left: 50px;
}
.main {
  flex-grow: 1;
  background: #efefef;
}
</style>
