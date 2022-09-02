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
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="名字" label="Name" width="180">
                    <template #default="{ $index, row }">
                        <div class="flex items-center">
                            <el-avatar :size="30"
                                src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
                            <div class="ml-3 flex flex-col">
                                <sapn>{{ row.name }}</sapn>
                                <sapn class="text-sm">编号：{{ $index }}</sapn>
                            </div>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="date" label="Date" width="180" />
                <el-table-column prop="address" label="Address" />
                <el-table-column label="状态">
                    <template #default="scope">
                        <el-switch v-model="scope.row.status" active-color="#8389c7" inline-prompt active-text="是"
                            :active-value="1" inactive-text="否" :inactive-value="0" />
                    </template>
                </el-table-column>
                <el-table-column label="操作">
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
        <lm-drawer ref="DrawerRef" title="添加用户">
            <el-form ref="ruleFormRef" :model="FormUserData" status-icon :rules="rules" label-width="120px">
                <el-form-item label="用户名" prop="username" :required="true">
                    <el-input v-model="FormUserData.username" type="text" />
                </el-form-item>
                <el-form-item label="密码" prop="password" :required="true">
                    <el-input v-model="FormUserData.password" type="text" />
                </el-form-item>
                <el-form-item label="头像" prop="avatar" :required="true">
                    <el-input v-model="FormUserData.avatar" type="text" />
                </el-form-item>
                <el-form-item label="角色" prop="roleId" :required="true">
                    <el-select v-model="form.region" placeholder="please select your zone" v-for="(itme,index) in FormUserRoleIdList">
                        <el-option label="Zone one" value="shanghai" />
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-input v-model="FormUserData.status" type="text" />
                </el-form-item>
            </el-form>
        </lm-drawer>
    </div>

</template>
<script setup>
import LmDrawer from '@/components/LmDrawer.vue';
import { ref } from 'vue';
const tableData = ref([
    {
        date: '2016-05-03',
        name: 'Tom',
        status: 1,
        address: 'No. 189, Grove St, Los Angeles',
    },
    {
        date: '2016-05-02',
        name: 'Tom',
        status: 0,
        address: 'No. 189, Grove St, Los Angeles',
    },
    {
        date: '2016-05-04',
        name: 'Tom',
        status: 1,
        address: 'No. 189, Grove St, Los Angeles',
    },
    {
        date: '2016-05-01',
        name: 'Tom',
        status: 0,
        address: 'No. 189, Grove St, Los Angeles',
    },
]);

// 分页表单 添加按钮
const OpenDrawerEvent = () => {
    DrawerRef.value.open();
}
// 分页组件回调事件
const pageEvent = (pageNo) => {
    alert(pageNo);
}



// 表单相关 
const DrawerRef = ref(null)
// 用于form表单校验回传结果
const ruleFormRef = ref(null);// 校验规则
const rules = reactive({
    "username": [{ required: true, message: "必填项！", trigger: 'blur' }],
    "password": [{ required: true, message: "必填项！", trigger: 'blur' }],
});
// form表单角色下拉框数据~数据库回传
const FormUserRoleIdList = ref([
    {
        id: 1,
        name: "超级管理员"
    },
    {
        id: 2,
        name: "财务"
    },
]);
const FormUserData = ref({
    username: "1",
    password: "2",
    avatar: "3",
    roleId: 1,
    status: 1,
})
</script>