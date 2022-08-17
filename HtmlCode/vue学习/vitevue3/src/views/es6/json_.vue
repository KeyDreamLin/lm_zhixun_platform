<template >
  <div>json转换和反转</div>
</template>
<script setup>
    var userList = `{
        "userSize": 2,
        "userList": [
            { "id": 1, "name": "小郭" },
            { "id": 2, "name": "小马" }
        ]
    }`;

    // 输出类型
    // console.log(typeof userList);
    if (typeof userList == "string") {
        console.log("是string");
        userList = JSON.parse(userList);
    } else {
        console.log("是object");
    }
    console.log(userList);
    console.log(userList.userSize);
    console.log(userList.userList[0].name);
</script>
<style >
</style>