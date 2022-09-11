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
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="名字" label="Name" width="180">
                    <template #default="{ $index, row }">
                        <div class="flex items-center">
                            <el-avatar :size="30"
                                src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" />
                            <div class="ml-3 flex flex-col">
                                <sapn>{{ row.name }}</sapn>
                                <sapn class="text-xs">编号：{{ $index }}</sapn>
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
        <lm-drawer ref="DrawerRef" title="添加用户" @submit="drawerSubmitEvent">
            <el-form ref="ruleFormRef" :model="FormUserData" status-icon :rules="rules" label-width="120px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="FormUserData.username" type="text" placeholder="请输入用户名"/>
                </el-form-item>
                <el-form-item label="账号" prop="account">
                    <el-input v-model="FormUserData.account" type="text" placeholder="请输入账号"/>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="FormUserData.password" type="text" placeholder="请输入密码" />
                </el-form-item>
                <el-form-item label="头像" prop="avatar">
                    <lm-choose-image></lm-choose-image>
                </el-form-item>
                <el-form-item label="角色" prop="rolesId">
                    <el-select v-model="FormUserData.rolesId" placeholder="请选择角色">
                        <template v-for="(itme, index) in FormUserRoleIdList">
                            <el-option :label="itme.roleName" :value="itme.id" />
                        </template>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-switch v-model="FormUserData.status" active-color="#8389c7" inline-prompt active-text="是"
                        :active-value="1" inactive-text="否" :inactive-value="0" />
                </el-form-item>
            </el-form>
            {{FormUserData}}
        </lm-drawer>
    </div>

</template>
<script setup>
import LmChooseImage from '@/components/LmChooseImage.vue';
import LmDrawer from '@/components/LmDrawer.vue';
import adminUserService from '@/services/useradmin/AdminUserService.js';
import adminRoleService from '@/services/adminrole/AdminRoleService.js';
import { LmMessageError } from '@/utils';
import { reactive, ref } from 'vue';
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
const loadRoleDatas = async ()=> {
    const serverRet = await adminRoleService.findadminrolesList();
    // 转换为int
    serverRet.data.forEach(item=>{item.id=parseInt(item.id)});
    FormUserRoleIdList.value = serverRet.data;
    // console.log(serverRet.data);
}
// 分页表单 添加按钮 打开添加抽屉
const OpenDrawerEvent = () => {
    loadRoleDatas();
    DrawerRef.value.open();
}
// 分页组件回调事件
const pageEvent = (pageNo) => {
    alert(pageNo);
}

// 表单相关 
const DrawerRef = ref(null);
// 校验规则
const rules = reactive({
    username: [{required: true,message: '请输入用户名！',trigger: 'blur'}],
    account: [{required: true,message: '请输入账号！',trigger: 'blur'}],
    password: [{required: true,message: '请输入密码！',trigger: 'blur'}],
    rolesId: [{required: true,message: '请选择角色！',trigger: 'blur'}],
});
// 用于form表单校验回传结果
const ruleFormRef = ref(null);
// form表单角色下拉框数据~数据库回传
const FormUserRoleIdList = ref([
]);

// 管理员数据模型
// let FormUserData = ref({
//     username: "1",
//     account:"2",
//     password: "3s",
//     avatar: "https://ehall.hypt.edu.cn/anonymity/docrepo/download/file?attachmentId=searchPic&v=953",
//     status: 1,
//     isdelete : 0,
//     rolesId: 1,
// })
let FormUserData = ref({
    username: "",
    account:"",
    password: "",
    avatar: "",
    status: 1,
    isdelete : 0,
    rolesId: 1,
})
const drawerSubmitEvent = () => {
    // adminUserService
    ruleFormRef.value.validate(async (val)=>{
        if(val){
            try{
                const adminUserRet = await adminUserService.saveupdate(FormUserData.value);
                // 清空数据
                FormUserData.value = 
                { 
                    username: "",
                    account:"",
                    password: "",
                    avatar: "",
                    status: 1,
                    isdelete : 0,
                    rolesId: 1,
                };
                DrawerRef.value.closeNoMsg(); 
            }catch(err){
                console.error("保存数据出错-->",err);
                LmMessageError(err.msg);
            }
        }
        else{
            // 数据错误
        }
    })
}
</script>