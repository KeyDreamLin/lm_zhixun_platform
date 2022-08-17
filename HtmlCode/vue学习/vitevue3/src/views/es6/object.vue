<template >
  <div>对象解构</div>
</template>
<script setup>

    const userPage = {
        pageNo:1,
        pageSize:10,
        total:1577,
        pages:122,
        records:[
            {id:1,username:"小郭"},
            {id:2,username:"aa"},
            {id:3,username:"bb"},
            {id:4,username:"cc"},
        ]
    }
    // 这里对象的解构，一定是要和对象的key一致
    const {pageNo,pageSize,total,pages,records} = userPage;
    console.log(pageNo,pageSize,total,pages,records);
    // 别名
    const {pageNo:a,pageSize:b,total:c,pages:d,records:e} = userPage;
    console.log(a,b,c,d,e);
    // ... 获取对象剩下的值
    // 获取除了pageSize以外所有的值
    const {pageSize:a1,...endval} = userPage;
    console.log(a1,endval);
</script>
<style >
</style>