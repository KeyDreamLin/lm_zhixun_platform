<template>
    <div>
        <el-select v-model="modelValue" placeholder="请选择图标" clearable filterable  @change="selectEvent">
            <el-option v-for="item in eleIconList"
                :key="item" :label="item" :value="item">
                <div class="flex justify-between items-center p-0">
                    <el-icon>
                        <component :is="item" />
                    </el-icon>
                    <span>{{item}}</span>
                </div>
            </el-option>
        </el-select>
    </div>
</template>
<script setup>
import { ref } from 'vue';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
const eleIconList = ref( Object.keys(ElementPlusIconsVue));
defineProps(
    {
        modelValue: String,
        default:""
    },
)
const emit = defineEmits(['update:modelValue'])
// 选择框值修改后
const selectEvent = (val) => {
  emit('update:modelValue',val);
}
</script>