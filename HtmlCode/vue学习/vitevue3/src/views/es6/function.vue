<template >
  <div>function箭头函数</div>
</template>
<script setup>
    var test1 = () => {
        console.log(11);
    };
    test1();
    // 一个参数的函数
    var test2 = (a) => {
        console.log(a);
    };
    // 可以省略括号，但是不建议
    // var test2 = a => {
    //     console.log(a);
    // };
    test2(222);


    // 多个参数的函数，不能省略括号
    var test3 = (a,b) => {
        console.log(a,b);
    };
    test3(222,111);

    // 简化返回值的写法
    var sum = (a,b) => a+b;
    console.log(sum(12,1));
    var test4 = (a,b)=>sum(a,b);
    console.log(test4(666,1));


    // 回调函数的定义
    // axios.post("").then(function(res){});
    // axios.post("").then((res)=>{});
    // axios.post("").then(res=>{});

    // 箭头函数返回对象的问题
    var test5 = function(){
        return {id:1,username:"12312"};
    }
    console.log(test5());
    // 在返回对象的时候必须加上一个括号，
    // 如果不加括号，这里大括号就会被认为函数的大括号
    var test5 = () =>( {id:1,username:"123sdfsd12"} );
    console.log(test5());
</script>
<style>
</style>