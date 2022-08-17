<template >
  <div>computed属性</div>
   <h1>我是计算属性</h1>
    <p>ID:<input type="text" v-model="shopping.id"></p>
    <p>名称:<input type="text" v-model="shopping.name"></p>
    <p>价格:<input type="text" v-model="shopping.price"></p>
    <p>数量:<input type="text" v-model="shopping.num"></p>
    <button @click="addShopping">添加商品</button>
    <table>
        <thead>
            <tr>
                <td>ID</td>
                <td>名字</td>
                <td>价格</td>
                <td>数量</td>
                <td>小计</td>
                <td>操作</td>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(shopping,index) in shoppingList" :key="index">
                <td>{{shopping.id}}</td>
                <td>{{shopping.name}}</td>
                <td><input v-model="shopping.price"></td>
                <td><input v-model="shopping.num"></td>
                <td>￥{{shopping.price * shopping.num}}</td>
                <td><button @click="delShopping(index)">删除</button></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="10">总结：{{totalShopping}}</td>
            </tr>
        </tfoot>
    </table>
</template>
<script setup> 
    import {ref , computed} from "vue";
    let shopping = ref({
        id:0,
        name:"",
        price:0,
        num:0,
    });
    let shoppingList = ref([
        {
            id:1,
            name:"电话",
            price:1888,
            num:1,
        },
        {
            id:0,
            name:"电脑",
            price: 5999,
            num:1,
        },
    ]);

    // 添加商品
    const addShopping = ()=>{
        // 拷贝对象，不然会存在浅拷贝的问题
        // 出现的问题，输入框绑定shopping,push多次shopping到shoppingList都是重复的是同一个对象，当输入框绑定的这个shopping值更改了push的目标数组里面也会更改
        //解决：使用深拷贝，创建一个变量，然后将key对应val逐个拷贝进去
        // 这里使用es6对象解构的方式去拷贝对象的值
        let {...shopping_copy} = shopping.value;
        shoppingList.value.push(shopping_copy);
    }

    // 删除商品
    const delShopping = (index)=>{
        shoppingList.value.splice(index,1);
    }

    const totalShopping =  computed(()=>{
        return shoppingList.value.map((shoppingItem)=>{
            return shoppingItem.price * shoppingItem.num;
        }).reduce((a,b)=>{return a+b});
    });
</script>
<style >
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 30px;
    }
    td {
        border: 1px solid #ccc;
        padding: 10px;
    }
</style>