<template>
  <el-upload
    :headers="headers"
    :data="data"
    method="post"
    class="upload-demo"
    :name="name"
    :action="action"
    :before-upload="uploadBefore"
    :on-success="uploadSuccess"
    :on-progress="uploadProgress"
    :on-error="uploadError"
    :limit="1"
    :auto-upload="false"
    drag
    :multiple="false"
  >
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">
      Drop file here or <em>click to upload</em>
    </div>
    <template #tip>
      <div class="el-upload__tip">
        jpg/png files with a size less than 500kb
      </div>
    </template>
  </el-upload>
</template>

<script setup>
import { UploadFilled } from '@element-plus/icons-vue'

defineProps({
    data:{
        type:Object,
        default:{}
    },
    headers:{
        type:Object,
        default:{}
    },
    name:{
        type:String,
        default:"file"
    },
    action:{
        type:String,
        default:"https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
    },
})

//自定义事件
const emit = defineEmits(["before","success","error","progress"])
// 上传回调
const uploadBefore = (file)=>emit("before",file);
const uploadSuccess = (response,file)=>emit("success",response);
const uploadError = (err,file)=>emit("error",err,file)
const uploadProgress = (evt,file)=>emit("progress",evt,file);

</script>
