<template>
    <div class="lm-table-from__box">
        <!-- 表格相关 -->
        <div class="lm-table__box">
            <!-- 添加 删除 搜索 -->
            <div class="lm-handle__box pb-4 flex flex-row  items-center justify-between">
                <div class="lm-handle-but__box">
                    <el-button icon="Plus" @click="OpenDrawerEvent" type="success">新增</el-button>
                    <el-button icon="Delete" type="danger">批量删除</el-button>
                </div>
                <!-- 搜索 -->
                <div class="lm-handle-search__box flex flex-row  items-center">
                    <el-input v-model="input4" class="w-50 m-2" placeholder="请输入搜索关键词">
                        <template #suffix>
                            <el-icon class="el-input__icon">
                                <search />
                            </el-icon>
                        </template>
                    </el-input>
                    <el-button icon="search" type="success">搜索</el-button>
                </div>
            </div>

            <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" />
                <el-table-column prop="id" label="Id" width="180" />
                <el-table-column prop="roleName" label="角色代号" width="180" />
                <el-table-column prop="displayName" label="角色名称" />
                <el-table-column label="状态">
                    <template #default="scope">
                        <el-switch v-model="scope.row.status" active-color="#8389c7" inline-prompt active-text="是"
                            :active-value="1" inactive-text="否" :inactive-value="0" />
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="245px">
                    <template #default="scope">
                        <el-button size="small" icon="View" @click="handleEdit(scope.$index, scope.row)">预览</el-button>
                        <el-button size="small" icon="Edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button size="small" icon="Delete" type="danger"
                            @click="handleDelete(scope.$index, scope.row)">
                            删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="lm-page__box flex mt-4">
                <!--  :page-count="10"  总页数 -->
                <el-pagination @current-change="pageEvent" :page-count="10" background
                    layout="prev, pager, next , jumper" :total="1000" />
            </div>
        </div>
        <!-- 表单相关 -->
        <lm-drawer ref="DrawerRef" title="添加角色">
            <el-form ref="ruleFormRef" :model="FormRoleData" status-icon :rules="rules" label-width="120px">

                <el-form-item label="角色代号" prop="rolename" :required="true">
                    <el-input v-model="FormRoleData.roleName" type="text" />
                </el-form-item>
                <el-form-item label="角色名称" prop="displayname" :required="true">
                    <el-input v-model="FormRoleData.displayName" type="text" />
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-switch v-model="FormRoleData.status" active-color="#8389c7" inline-prompt active-text="是" :active-value="1" inactive-text="否" :inactive-value="0" />
                </el-form-item>
                {{ FormRoleData }}
            </el-form>
        </lm-drawer>
    </div>

</template>
<script setup>
import LmChooseImage from '@/components/LmChooseImage.vue';
import LmDrawer from '@/components/LmDrawer.vue';
import { ref, reactive } from 'vue';
const tableData = ref([
    {
        id: 1,
        roleName: "ROLE_USER",
        displayName: "客户端用户",
        status: 1,
    },
    {
        id: 2,
        roleName: "ROLE_ADMIN",
        displayName: "超级管理员",
        status: 1,
    },
    {
        id: 3,
        roleName: "ROLE_STAFF",
        displayName: "管理后台用户",
        status: 1,
    },
]);
const OpenDrawerEvent = () => {
    DrawerRef.value.open();
}
const handleSelectionChange = (val) => {
    console.log(val);
}
// form表单  相关
const DrawerRef = ref(null);
// 用于form表单校验回传结果
const ruleFormRef = ref(null);
// 校验规则
const rules = reactive({
    "rolename": [{ required: true, message: "必填项！", trigger: 'blur' }],
    "displayname": [{ required: true, message: "必填项！", trigger: 'blur' }],
});
const FormRoleData = ref({
    roleName: "5",
    displayName: "6",
    status: 1,
});
</script>