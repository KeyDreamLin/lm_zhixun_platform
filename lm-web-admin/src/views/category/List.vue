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
        <lm-dialog ref="DialogRef" title="分类添加" @submit="submitEvent">
            {{FormData}}
            <el-form ref="FormRef" :model="FormData" status-icon :rules="rules" label-width="80px">
                <el-form-item label="分类名称" prop="rolename" :required="true">
                    <el-input v-model="FormData.roleName" type="text" />
                </el-form-item>
                <el-form-item label="分类父级">
                    <el-select 
                        v-model="FormData.rolename"  
                        placeholder="请选择分类父级"
                        style="width: 100% ;"  
                    >
                        <el-option 
                            v-for="item in [{ label: '课程', value: 1 }, { label: '视频', value: 0 }] "
                            :key="item.value" 
                            :label="item.label" 
                            :value="item.value" 
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-radio-group v-model="FormData.status" class="ml-4">
                        <el-radio :label="1" size="large">发布</el-radio>
                        <el-radio :label="0" size="large">不发布</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="排序">
                    <el-input-number 
                        placeholder="请选择排序" 
                        v-model="FormData.status" 
                        :min="1" :max="100" 
                    />
                </el-form-item>
                {{ FormRoleData }}
            </el-form>
        </lm-dialog>
    </div>

</template>
<script setup>
import LmChooseImage from '@/components/LmChooseImage.vue';
import LmDialog from '@/components/LmDialog.vue';
import LmDrawer from '@/components/LmDrawer.vue';
import { LmMessageError, LmMessageSuccess } from '@/utils';
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
    DialogRef.value.open();
}
const handleSelectionChange = (val) => {
    console.log(val);
}
// form表单  相关
const DialogRef = ref(null);
// 用于form表单校验回传结果
const FormRef = ref(null);
// 校验规则
const rules = reactive({
    "rolename": [{ required: true, message: "必填项！", trigger: 'blur' }],
    "displayname": [{ required: true, message: "必填项！", trigger: 'blur' }],
});
const FormData = ref({
    roleName: "5",
    displayName: "6",
    status: 0,
});
const submitEvent = () => {
    FormRef.value.validate(valid => {
        if(valid){
            LmMessageSuccess("提交成功！");
            DialogRef.value.close();
        }
        else{
            LmMessageError("提交失败，表单数据有误！");
        }
    });
}
</script>