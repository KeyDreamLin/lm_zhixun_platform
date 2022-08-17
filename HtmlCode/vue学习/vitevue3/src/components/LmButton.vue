<template >
    <button :class="'lm-but lm-button-'+lm_type">
        <!-- 插槽用于定义内容,也可以是html标签 -->
        <slot></slot>
    </button>

</template>
<script setup>
    var props = defineProps({
        lm_type:{
            type:String,
            default:""
        }
    });
</script>
<style >
    .lm-but{
        border:none;
        border-radius: 4px;
        background: #ef9999;
        padding: 5px 16px;
    }
    .lm-button-success{
        background: lawngreen;
    }
    .lm-button-warning{
        background: orange;
    }
    .lm-button-error{
        background: red;
    }
</style>