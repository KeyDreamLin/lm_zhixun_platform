<template>
    <h1>我是用户列表-Product</h1>
    <input>
    <lm-dialog width="90%" @submit="submitEvent">
    
        <div>123</div>
    </lm-dialog>
</template>

<script setup>
import LmDialog from '@/components/LmDialog.vue';
const submitEvent = ()=>{
    alert("123123123");
}
</script>