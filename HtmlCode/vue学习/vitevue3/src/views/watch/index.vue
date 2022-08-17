<template >
  <div>
    watch属性
    <p>请输入关键词<input type="text" v-model="keyword" /></p>
    <ul v-for="(item, index) in books" :key="index">
      <li>{{ item.name }}</li>
    </ul>
  </div>
</template>
<script setup>
import { ref, watch } from "vue";
    const keyword = ref("");
    const books =ref( [
        { id: 1, name: "javascript" },
        { id: 2, name: "java" },
        { id: 3, name: "php" },
        { id: 4, name: "Go" },
        { id: 5, name: "Gogo" },
        { id: 6, name: "python" },
    ]);
    let books_copy = [...books.value];
    watch(keyword,(newval,oldval)=>{
        // console.log("新的值",newval,"旧的值",oldval);
        // 清空数组
        books.value.splice(0,books.value.length);
        books_copy.map((bookItem) => {
                //查询字符串是否存在 
                // includes 如果传入空字符串""就会返回true，因为他的底层indexOf传入空字符串的时候是返回0
                let flag = bookItem.name.includes(newval);
                console.log(flag);
                if (flag) {
                    // console.log(bookItem);
                    // 将合法的值存放进去
                    books.value.push(bookItem);
                }
            });
    });
</script>
<style >
</style>