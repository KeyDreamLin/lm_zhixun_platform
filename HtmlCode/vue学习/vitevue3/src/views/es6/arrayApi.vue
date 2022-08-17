<template >
  <div>函数Api</div>
  <div v-html="html"></div>
</template>
<script setup>

let userList = [
  { id: 1, name: "小郭", age: 18, male: 0 },
  { id: 2, name: "小马", age: 123, male: 1 },

];
let html = `我是<a>${userList[0].name}</a>` ;
// map 简单使用
// 改造数组（自定义返回值）
var new_userList = userList.map((item) => {
  var id = item.id;
  var name = item.name;
  return { id, name };
});
console.log(new_userList);

// forEach 只能是单纯的遍历数组，没有return的功能
userList.forEach((item, inedx) => {
  item.temp = 11; //对象可以添加新的key
  console.log(item, item.name, inedx);
});

// filter 数组过滤
var filter_userList = userList.filter((item) => {
  return item.male == 0;
});
console.log(filter_userList);

// 数组排序 sort  可以对数字排序可以对字母排序
// var sort_userList = userList.sort((a,b)=>a.age-b.age);
// console.log("排序后",sort_userList);
// 查找数组对应的索引 findIndex
// 简化返回值写法
var select_findIndex = userList.findIndex((item) => {
  return item.name === "小马";
});
// 一个参数的函数 可以省略括号
var select_findIndex = userList.findIndex((item) => {
  return item.name === "小马";
});
// 如果没有逻辑体和处理体的情况下，可以省略return和{}  (注意return{}是要一起的，要不就一起没要不就一起加)
var select_findIndex = userList.findIndex((item) => item.name === "小马");
console.log(select_findIndex);

// 查找对应的元素 并输出 find
var select_findIndex = userList.find((item) => item.name === "小马");
console.log(select_findIndex);

// 往尾部拼接数组 类似于追加吧 conact
userList = userList.concat(userList);
console.log(userList);
</script>
<style >
</style>