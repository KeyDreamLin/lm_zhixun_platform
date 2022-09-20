<template>
    <div class="lm-treeList-from__box">
        <!-- 树形表相关 -->
        <div class="lm-treeList__box">
            <!-- 添加 删除 搜索 -->
            <div class="lm-handle__box pb-4 flex flex-row  items-center justify-between">
                <div class="lm-handle-but__box">
                    <el-button icon="Plus" @click="OpenDrawerEvent" type="success">新增</el-button>
                </div>
                <!-- 搜索 -->
                <div class="lm-handle-search__box flex flex-row  items-center">
                    <el-input class="w-50 m-2" placeholder="请输入搜索关键词">
                        <template #suffix>
                            <el-icon class="el-input__icon">
                                <search />
                            </el-icon>
                        </template>
                    </el-input>
                    <el-button icon="search" type="success">搜索</el-button>
                </div>
            </div>
            <div class="lm-tree__box">
                <el-tree 
                    style="background:#f8f8f8;" node-key="id"
                    ref="TreeRef" :data="permissionTree" :props="{label:'name'}"
                    :default-expand-all="true" :highlight-current="true" :expand-on-click-node="false"
                >
                    <template #default="{ node, data }">
                        <div class="lm-tree-body">
                            <!-- 标签图标等 -->
                            <div class="lm-tree-title-icon flex items-center">
                                <span class="mr-1">
                                    <el-tag v-if="data.type == 1">菜单</el-tag>
                                    <el-tag type="info" v-if="data.type == 2">权限</el-tag>
                                </span>
                                <el-icon class="mr-1">
                                    <component :is="data.icon" />
                                </el-icon>
                                <span class="mr-2">{{ data.name }}</span>
                            </div>
                            <!-- 按钮控件 -->
                            <div class="lm-tree-but">
                                <el-button type="success" size="small" v-if="data.type == 1" @click="addItemEvent(data)">添加子项</el-button>
                                <el-button type="warning" size="small" @click="UpdItemEvent(data)">编辑</el-button>
                                <el-button type="danger" size="small" @click="DelItemEvent(data.id)">删除</el-button>
                            </div>
                        </div>
                    </template>
                </el-tree>
            </div>
        </div>

        <lm-drawer ref="DrawerRef" title="新增权限" @submit="drawerSubmitPermissionsEvent">
            {{ FormPermissionData }}
            <el-form ref="formRef" :model="FormPermissionData" :rules="rules" label-width="120px" status-icon>
                <el-form-item label="父亲权限ID">
                    <el-cascader placeholder="请选中父信息" 
                        :props="{
                            label:'name',
                            value:'id',
                            multiple: false, 
                            children:'children',
                            checkStrictly:true,
                        emitPath:false}" :options="SelectOfPermissionData" clearable
                         v-model="FormPermissionData.pid"/>
                </el-form-item>

                <el-form-item label="菜单规则" class="flex items-center">
                    <el-radio-group v-model="FormPermissionData.type">
                        <el-radio :label="1" size="large">菜单</el-radio>
                        <el-radio :label="2" size="large">权限</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item 
                    :label="FormPermissionData.type==1?'菜单名称':'权限名称'">
                        <el-input v-model="FormPermissionData.name" />
                </el-form-item>

                <!-- 是添加菜单的时候才显示前端路由 -->
                <el-form-item label="路由路径" v-if="FormPermissionData.type==1">
                    <el-input v-model="FormPermissionData.path" />
                </el-form-item>
                <el-form-item label="路由名称" v-if="FormPermissionData.type==1">
                    <el-input v-model="FormPermissionData.pathname" />
                </el-form-item>
               

                <el-form-item :label="FormPermissionData.type==1?'菜单代号':'权限代号'" >
                    <el-input v-model="FormPermissionData.code" />
                </el-form-item>

                <el-form-item 
                    :label="FormPermissionData.type==1?'菜单图标':'权限图标'"
                    class="felx items-center">
                        <lm-select-icon class="mr-2" v-model="FormPermissionData.icon"></lm-select-icon>
                        <el-icon>
                            <component :is="FormPermissionData.icon" />
                        </el-icon>
                </el-form-item>

                <el-form-item label="权限排序">
                    <el-input-number v-model="FormPermissionData.sorted" :min="1" :max="100" />
                </el-form-item>

                <el-form-item label="权限状态" class="flex items-center">
                    <el-switch 
                        class="mr-2" v-model="FormPermissionData.status" size="large" width="65px" active-color="#8389c7"
                        inline-prompt active-text="已发布" :active-value="1" inactive-text="不发布"
                        :inactive-value="0" />
                </el-form-item>
            
            </el-form>
        </lm-drawer>
    </div>
</template>
  
<script setup>
import { onMounted, reactive, ref } from 'vue';
import LmDrawer from '@/components/LmDrawer.vue';
import permissionService from '@/services/permission/PermissionService'
import { LmMessageError, LmMessageSuccess } from '@/utils';
/* tree相关 */
// 可以去调用Tree的一些行为和方法
const TreeRef = ref(null);
const permissionTree = ref([]);

// 新增权限的按钮
const OpenDrawerEvent = async () => {
    DrawerRef.value.open();
    // 加载权限表到页面的抽屉的下拉框
    await loadSelectBoxData();
    // 设置排序
    FormPermissionData.value.sorted = SelectOfPermissionData.value.length + 1;
}
// 加载权限表到页面的tree
const loadTreeData = async () =>{
    let serverRet =  await permissionService.permissionTree();
    // 页面的数据
    permissionTree.value = serverRet.data;
    console.log(serverRet);
}
// 加载权限表到页面的抽屉的下拉框
const loadSelectBoxData = async () =>{
    let serverRet =  await permissionService.permissionTree();
    // 选择框的数据
    SelectOfPermissionData.value = serverRet.data;
    console.log(serverRet);
}


onMounted(()=>{
    // 将权限加载到页面
    loadTreeData(); 
})

/* 抽屉form */
const DrawerRef = ref(null);
const formRef = ref(null);

// form 表单验证规则
const rules = reactive({

});

// 请求方式
// const methodList = reactive(["POST", "PUT", "DELETE"]);
// form数据模型
const FormPermissionData = ref({
    pid: 0,// 对应的父级 0 就是最大的父 
    name: "",// 权限或者菜单名字
    path: "", //路由访问路径
    pathname: "", // 路由名称
    icon: "", // 图标
    code: "",// 代号
    type: 1,// 1 菜单 2 权限
    sorted: 0, // 排序
    status: 1,// 状态
    isdelete:0,// 是否删除
})
const reset = () => {
    FormPermissionData.value = {
        pid: 0,// 对应的父级 0 就是最大的父 
        name: "",// 权限或者菜单名字
        path: "", //路由访问路径
        pathname: "", // 路由名称
        icon: "", // 图标
        code: "",// 代号
        type: 1,// 1 菜单 2 权限
        sorted: 0, // 排序
        status: 1,// 状态
        isdelete:0,// 是否删除
    }
}
// 下拉框 选择父权限的数据
const SelectOfPermissionData = ref([]);

// 抽屉提交事件 权限表添加
const drawerSubmitPermissionsEvent = async () =>{
   try {
        permissionService.saveupdateAdminPermission(FormPermissionData.value);
        LmMessageSuccess("操作成功！");
        DrawerRef.value.closeNoMsg();
        reset();
        // 将权限加载到页面
        loadTreeData(); 
   } catch (err) {
        console.log(err);
        LmMessageError("操作失败！");
   }
}
// 添加子权限事件按钮
const addItemEvent = async (item) => {
    DrawerRef.value.open();
    // 加载权限表到页面的抽屉的下拉框
    await loadSelectBoxData();
    reset();
    FormPermissionData.value.pid = item.id;
    // 设置排序 
    FormPermissionData.value.sorted = item.children.length + 1;
    console.log(item);
}
// 修改子权限事件按钮
const UpdItemEvent = async (item) => {
    DrawerRef.value.open();
    // 加载权限表到页面的抽屉的下拉框
    await loadSelectBoxData();
    reset();
    // 深拷贝
    FormPermissionData.value  = {...item};
    console.log(item);
}
// 删除子项
const DelItemEvent = async (id) => {
    try{
        await permissionService.deleteAdminPermissionById(id);
        LmMessageSuccess("删除成功");
        // 将权限加载到页面
        loadTreeData();  
    }catch(err){
        LmMessageSuccess("删除失败");
    }
}
</script>

<style >
.lm-tree-body {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
}

.el-tree-node__content {
    margin: 5px 0;
}
</style>
