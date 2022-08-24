<template>
   <!-- :style="{ 'left': $store.state.menu.menuWidth }" -->
   <div class="lm-tag-list__box">
      <el-tabs v-model="tabsSelectActivateVal" type="card" @tab-remove="removeTab" @tab-change="changeTab">
         <!-- :closable="item.path != '/'" 不够首页删除的按钮 这样就删除不了首页了哈哈 -->
         <el-tab-pane v-for="item in editableTabs" :closable="item.path != '/'" :key="item.path" :label="item.title"
            :name="item.path">
         </el-tab-pane>
      </el-tabs>
   </div>
</template>

<script setup>
import { computed } from '@vue/reactivity';
import { ref, onMounted } from 'vue'
// 用于路由对象 对路由进行操作
import { useRouter, onBeforeRouteUpdate, useRoute } from 'vue-router';
import { useStore } from 'vuex';
// 用于路由对象 对路由进行操作
const router = useRouter();
// 用于获取当前路由的状态和地址
const route = useRoute();

// 当前选中的值 响应式
const tabsSelectActivateVal = ref('/')
// tabs的响应式数据
const editableTabs = ref([
   {
      title: "后台页面",
      path: "/"
   },
]);

onMounted(() => {
   // 获取当前路由 ，用户在携带路由刷新F5的时候刷新又会回到/里面但是当前路由又不是/会很怪
   addTab({ title: route.meta.title, path: route.path });
});

// 路由改变更新事件
onBeforeRouteUpdate((to, from) => {
   // 在当前路由改变，但是该组件被复用时调用
   // 举例来说，对于一个带有动态参数的路径 `/users/:id`，在 `/users/1` 和 `/users/2` 之间跳转的时候，
   // 由于会渲染同样的 `UserDetails` 组件，因此组件实例会被复用。而这个钩子就会在这个情况下被调用。
   // 因为在这种情况发生的时候，组件已经挂载好了，导航守卫可以访问组件实例 `this`
   console.log(to, from);
   addTab({ title: to.meta.title, path: to.path });
   // 每次修改路由都激活标签
   // 这句不加也行 因为addTab的函数写了，以防万一也加上
   tabsSelectActivateVal.value = to.path;
});

// 添加标签事件
const addTab = (tabItem => {
   console.log(tabItem);
   // 是否存在该标签 
   let isExist = editableTabs.value.findIndex(item => {
      return item.path == tabItem.path;
   });
   // -1 表示不存在
   if (isExist == -1) {
      editableTabs.value.push(tabItem);
   }
   // 激活标签
   tabsSelectActivateVal.value = tabItem.path;

});

// 点击删除按钮事件
const removeTab = ((thisSelectVal) => {
   // alert(thisSelectVal);

   // 删除之前先激活下一个圆元素或者上一个元素
   editableTabs.value.forEach((item, index) => {
      // 找到了这个元素
      if (item.path == thisSelectVal) {
         // 再去查找他的上一个或者下一个元素去激活
         const nextTab = editableTabs.value[index + 1] || editableTabs.value[index - 1];
         if (nextTab) {
            // 激活标签
            tabsSelectActivateVal.value = nextTab;
         }
      }
   });

   // 然后在 将对应的元素过滤掉 删除掉
   editableTabs.value = editableTabs.value.filter(viewItem => {
      return viewItem.path != thisSelectVal;
   });
});
// 点击事件
const changeTab = ((thisChangeTab) => {
   // alert(thisChangeTab);
   router.push(thisChangeTab);
});

</script>
<style>
.lm-tag-list__box {
   /* background: #eee; */
   height: 35px !important;
   /* position: relative; */
   /* left: 210px;
   right: 0;
   padding: 5px 5px 0;
   background: #fff; */
   position: fixed;
   top: 50px;
}

.lm-tag_dropdown_box {
   height: 35px !important;
   /* position: absolute;
   right: 10px;
   top: 5px;
   background: rgb(240, 240, 240);
   border-radius: 6px;
   padding: 10px; */
}

.el-dropdown-link {
   color: #000;
   display: flex;
   align-items: center;
}

.el-dropdown_tlitle {
   display: block;
}

.el-tabs__header {
   border: 0 !important;
   margin-bottom: 0 !important;
}

.el-tabs__nav {
   border: 0 !important;
}

.el-tabs__item {
   border: 0 !important;
}

.lm-tag-list__box .el-tabs__header {
   margin: 0 0 0;
}

.el-tabs {
   --el-tabs-header-height: 35px;
}

.lm-tag-list__box .el-tabs__item {
   height: 35px !important;
   padding-left: 10px;
   padding-right: 10px;
   background: rgb(240, 240, 240);
   border-radius: 6px;
   margin: 5px;
}

.lm-tag-list__box .el-tabs__item .is-top .is-closable {
   padding-left: 10px;
   padding-right: 10px;
}

.lm-tag-list__box .el-tabs--top.el-tabs--card>.el-tabs__header .el-tabs__item:last-child {
   padding-left: 10px;
   padding-right: 10px;
}

/* 选中激活的状态 */
.lm-tag-list__box .el-tabs--card>.el-tabs__header .el-tabs__item.is-active.is-closable {
   padding-left: 10px;
   padding-right: 10px;
}
</style>

