<template>
  components: { LmCard },
  <div>组件的使用</div>
  <lm-button>123</lm-button>
  <Lm-Button>456</Lm-Button>
  <LmButton>789</LmButton>

  <lm-button lm_type="success">123</lm-button>
  <lm-button lm_type="error">123</lm-button>
  <lm-button lm_type="warning">123</lm-button>

  <br />

  <lm-input>
    <!-- 插槽 -->
    <template #left>http://</template>
    <template #right>.com</template>
  </lm-input>
  <p></p>
  <p></p>

  <lm-card lm_butText="按钮" :lm_isBut="true" class="box-card">
    <template #lm_title>
        我是标题
    </template>
    <template #content>
      <div>List item 1</div>
      <div>List item 2</div>
      <div>List item 3</div>
      <div>List item 4</div>
    </template>
  </lm-card>

</template>
<script setup>
import LmCard from "../../components/LmCard.vue";
</script>
<style>
.box-card{
    width: 400px;
}
</style>