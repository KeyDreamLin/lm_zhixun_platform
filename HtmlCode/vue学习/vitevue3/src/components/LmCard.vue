<template>
  <div class="lm-card" >
    <div class="lm-card__header">
      <span>
        <slot name="lm_title"></slot>
      </span>
      <button v-if="lm_isBut" @click="butEvent">{{ lm_butText }}</button>
    </div>
    <div class="lm-card__body">
      <slot name="content"></slot>
    </div>
  </div>
</template>


<script setup>
    var props = defineProps({
        lm_title: {
            type: String,
            default: "",
        },
        lm_butText: {
            tyep: String,
            default: "",
        },
        lm_isBut:{
            type:Boolean,
            default:false,
            validator:function(val){
                return typeof val =='boolean';
            }
        }
    });
    function butEvent() {
        alert("123q");
    };
</script>
<style>
.lm-card {
  /* width: 26.875rem; */
  background: rgb(255, 255, 255);
  /* margin-left: 1.25rem; */
}
.lm-card__header {
  padding: 1.125rem 1.25rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.lm-card__header > button {
  border: none;
  color: #606266;
  background: none;
  padding: .5rem .9375rem;
}
.lm-card__header > button:hover {
  background: #f5f7fa;
}
.lm-card__body {
  padding: 1.125rem 1.25rem;
}
.item {
  margin-top: .625rem;
}
</style>