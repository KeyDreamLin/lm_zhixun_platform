<template>
    <div class="lm-treeList-from__box">
        <!-- 树形表相关 -->
        <div class="lm-treeList__box">
            <!-- 添加 删除 搜索 -->
            <div class="lm-handle__box pb-4 flex flex-row  items-center justify-between">
                <div class="lm-handle-but__box">
                    <el-button icon="Plus" @click="OpenDrawerEvent" type="success">新增</el-button>
                    <el-button icon="Delete" type="danger">批量删除</el-button>
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
                <el-tree style="background:#f8f8f8;" ref="TreeRef" :data="data" show-checkbox node-key="id"
                    :default-expand-all="false" :highlight-current="true" :expand-on-click-node="true">
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
                                <span class="mr-2">{{ data.label }}</span>
                            </div>
                            <!-- 按钮控件 -->
                            <div class="lm-tree-but">
                                <el-switch class="mr-2" v-model="data.status" width="60px" active-color="#8389c7"
                                    inline-prompt active-text="已发布" :active-value="1" inactive-text="不发布"
                                    :inactive-value="0" />
                                <el-button type="success" size="small">添加</el-button>
                                <el-button type="danger" size="small">删除</el-button>
                            </div>
                        </div>
                    </template>
                </el-tree>
            </div>
        </div>

        <lm-drawer ref="DrawerRef" title="新增权限">
            {{ permissionManger }}
            <el-form ref="formRef" :model="permissionManger" :rules="rules" label-width="120px" status-icon>
                <el-form-item label="父亲权限ID">
                    <el-cascader :props="{ label: 'label',value:'id',emitPath:false,checkStrictly:true}" :options="data" :show-all-levels="false" v-model="permissionManger.pid" :model-value="permissionManger.pid" placeholder="请选择父权限ID" />
                </el-form-item>
                <el-form-item label="菜单规则" class="flex items-center">
                    <el-radio-group v-model="permissionManger.menutype">
                        <el-radio :label="1" size="large">菜单</el-radio>
                        <el-radio :label="2" size="large">权限</el-radio>
                    </el-radio-group>
                </el-form-item>
                <!-- 是添加菜单的时候才显示前端路由 -->
                <el-form-item label="前端路由" v-if="permissionManger.menutype==1">
                    <el-input v-model="permissionManger.frontpath" />
                </el-form-item>
                <el-form-item label="权限名称" prop="permissionname">
                    <el-input v-model="permissionManger.permissionname" />
                </el-form-item>
                <el-form-item label="权限代号" prop="permissioncode">
                    <el-input v-model="permissionManger.permissioncode" />
                </el-form-item>
                <el-form-item label="请求方式">
                    <el-select v-model="permissionManger.method" placeholder="permissionManger.method">
                        <el-option v-for="item in methodList" :key="item" :label="item" :value="item" />
                    </el-select>
                </el-form-item>
                <el-form-item label="权限排序">
                    <el-input-number v-model="permissionManger.sort" :min="1" :max="10" />
                </el-form-item>
                <el-form-item label="权限状态">
                    <el-switch v-model="permissionManger.status" active-color="#8389c7" inline-prompt active-text="是"
                        :active-value="1" inactive-text="否" :inactive-value="0" />
                </el-form-item>
            </el-form>
        </lm-drawer>
    </div>
</template>
  
<script setup>
import { reactive, ref } from 'vue';
import LmDrawer from '@/components/LmDrawer.vue';
/* tree相关 */
// 可以去调用Tree的一些行为和方法
const TreeRef = ref(null);
// 新建按钮
const OpenDrawerEvent = () => {
    DrawerRef.value.open();
}

const data = [
    {
        id: 1,
        label: '后台面板',
        icon: "add-location",
        type: 2,
        pid: 0,
        status: 1,
        children: [
            {
                id: 2,
                label: '主控台',
                icon: "home-filled",
                type: 1,
                pid: 1,
                status: 1,
            },
        ],
    },
    {
        id: 3,
        label: '商品管理',
        icon: "shopping-bag",
        type: 1,
        pid: 0,
        children: [
            {
                id: 4,
                label: '商品管理',
                icon: "apple",
                type: 1,
                pid: 3,
            },
        ],
    },
];

/* 抽屉form */
const DrawerRef = ref(null);
const formRef = ref(null);
// form 表单验证规则
const rules = reactive({
    "permissionname": [{ required: true, message: "请输入权限名", trigger: 'blur' }],
    "permissioncode": [{ required: true, message: "请输入权限代号", trigger: 'blur' }],
    "status": [{ required: true, message: "请选择状态", trigger: 'blur' }],
});
// 请求方式
const methodList = reactive(["POST", "PUT", "DELETE"]);
// form数据模型
const permissionManger = reactive({
    pid: 0,
    menutype: 1,//1 菜单 2 权限
    frontpath: "/product/list",
    permissionname: "1",
    permissioncode: "2",
    method: "POST",
    sort: 3,
    status: 1
})
</script>

<style >
.lm-tree-body {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
}

.el-tree-node__content {
    margin-bottom: 5px;
}
</style>
