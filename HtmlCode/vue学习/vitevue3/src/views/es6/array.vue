<template >
  <div>数组解构</div>
  <ul>
    <li v-for="(item,index) in array" :key="index">
        {{item}}
    </li>
  </ul>
</template>
<script setup>
    let array = [1,2,3,4,5,6,7,8];
    // 这里的abcd对应数组的值1234
    let[a,b,c,d] = array;
    console.log(a,b,c,d);
    // 对应数组的值135 , ',,'两个逗号对应的空开一个值
    let[a1,,a2,,a3] = array;
    console.log(a1,a2,a3);
    // 这个...变量的意思是，获取后面所有的数组值
    // 1 2 {3,4,5,6,7,8}
    let[b1,b2,...b3] = array;
    console.log(b1,b2,b3);

</script>
<style >
</style>