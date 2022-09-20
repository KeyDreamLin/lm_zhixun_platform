<template>
    <div class="lm-table-from__box">
        <!-- 表格相关 -->
        <div class="lm-table__box">
            <!-- 添加 删除 搜索 -->
            <div class="lm-handle__box pb-4 flex flex-row  items-center justify-between">
                <div class="lm-handle-but__box">
                    <el-button icon="Plus" @click="OpenDrawerEvent" type="success">新增</el-button>
                    <el-button icon="Delete" type="danger" @click="SelectByidDelAdminUserEvent">批量删除</el-button>
                </div>
                <!-- 搜索 -->
                <div class="lm-handle-search__box flex flex-row  items-center">
                    <el-input v-model="keyword" class="w-50 m-2" placeholder="请输入搜索关键词">
                        <template #suffix>
                            <el-icon class="el-input__icon">
                                <search />
                            </el-icon>
                        </template>
                    </el-input>
                    <el-button icon="search" @click="queryKeywordEvent" type="success">搜索</el-button>
                </div>
            </div>
            <el-table 
                :data="tableData" 
                style="width: 100%" 
                @selection-change="TableSelectEvent"
            >
                <!-- 多选 单选 -->
                <el-table-column type="selection" width="55" />

                <el-table-column prop="名字" label="Name" width="240">
                    <template #default="{ $index, row }">
                        <div class="flex items-center">
                            <el-avatar :size="30"
                                :src="row.avatar" />
                            <div class="ml-3 flex flex-col">
                                <sapn>{{ row.username }}</sapn>
                                <sapn class="text-xs">编号：{{ row.id }}</sapn>
                            </div>
                        </div>
                    </template>
                </el-table-column>

                <el-table-column prop="account" label="账号" />

                <el-table-column prop="updateTime" label="更新时间"/>
                
                <el-table-column label="发布状态" >
                    <template #default="{ $index, row }">
                        <el-switch 
                            v-model="row.status" active-color="#8389c7" inline-prompt 
                            active-text="是" :active-value="1" inactive-text="否" :inactive-value="0"
                            @click="EventUpdataStatus('status',$index);"
                        />
                    </template>
                </el-table-column>

                <el-table-column label="删除状态" >
                    <template #default="{ $index, row }">
                        <el-switch
                            v-model="row.isdelete" active-color="#8389c7" inline-prompt 
                            active-text="是" :active-value="1" inactive-text="否" :inactive-value="0" 
                            @click="EventUpdataStatus('isdelete',$index);"
                         />
                    </template>
                </el-table-column>
                
                <el-table-column label="操作" width="280px">
                    <template #default="scope">
                        <el-button size="small" icon="View" @click="RoleEvent(scope.row)">查看角色</el-button>
                        <el-button size="small" icon="Edit" @click="EditEvent(scope.$index, scope.row)">编辑</el-button>
                        <el-button size="small" icon="Delete" type="danger"
                            @click="handleDelete(scope.$index, scope.row)">
                            删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="lm-page__box flex mt-4">
                <!--  :page-count="10"  总页数 -->
                <el-pagination @current-change="pageEvent" 
                    :page-count="pageCount" :total="total" :current-page="pageNo"
                    layout="prev, pager, next , jumper"  background  />
                    <!-- {{total}}++{{pageSize}}++{{pageCount}} -->
            </div>
        </div>
        <!-- 表单相关 -->
        <lm-drawer ref="DrawerRef" :title="DrawerTitle" @submit="drawerSubmitEvent">
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
                
                <el-form-item label="发布状态" prop="status">
                    <el-switch v-model="FormUserData.status" active-color="#8389c7" inline-prompt active-text="是"
                        :active-value="1" inactive-text="否" :inactive-value="0" />
                </el-form-item>
                <el-form-item label="删除状态" prop="isdelete">
                    <el-switch
                        v-model="FormUserData.isdelete" active-color="#8389c7" inline-prompt 
                        active-text="是" :active-value="1" inactive-text="否" :inactive-value="0" 
                    />
                </el-form-item>
                
            </el-form>
            {{FormUserData}}
        </lm-drawer>
        <lm-dialog ref="RoleDiaLogRef" title="查看角色" :isbutton="false">
            <el-table :data="RoleTableData" style="width: 100%">
                <el-table-column prop="roleName" label="角色名称" style="width: 100%" />

                <el-table-column label="操作" style="width: 100%" >
                    <template #default="{ $index, row }">
                        <el-button 
                        :type="row.isAuth ? 'danger' : 'success'"
                        size="default" @click="UpdRoleEvent(row)" >
                            {{ row.isAuth ? "解除绑定" : "绑定关系" }}
                        </el-button>
                              
                    </template>
                </el-table-column>
            </el-table>
        </lm-dialog>
    </div>

</template>
<script setup>
import LmChooseImage from '@/components/LmChooseImage.vue';
import LmDrawer from '@/components/LmDrawer.vue';
import adminUserService from '@/services/useradmin/AdminUserService.js';
import adminRoleService from '@/services/adminrole/AdminRoleService.js';
import { LmMessageError, LmMessageSuccess } from '@/utils';
import { onMounted, reactive, ref } from 'vue';
// 表数据
const tableData = ref([]);
const total = ref(0); // 总条数
const pageSize = ref(10); // 需要每一页有多少条
const pageCount = ref(1); // 总页数
const pageNo = ref(1);// 当前页数
const keyword = ref("");// 关键词查询
// 加载admin用户数据
const loadUserAdminData = async () =>{
    // 组装数据 并查询
    let serverRetUserAdminData = await adminUserService.findAdminUsers({
        pageNo:pageNo.value,
        pageSize:pageSize.value,
        keyword:keyword.value
    });
    // 将服务器的数据组装起来
    let retData = serverRetUserAdminData.data;
    tableData.value = retData.records;
    total.value = retData.total;
    pageSize.value =retData.size;
    pageCount.value = retData.pages;
    pageNo.value = retData.current;
    console.log("--------------------",serverRetUserAdminData);
}
// 生命周期
onMounted(()=>{
    // 加载一次数据
    loadUserAdminData();
})
// 关键词查询
const queryKeywordEvent = () => {
    pageNo.value = 1;
    console.log(pageNo.value);
    loadUserAdminData();
}


// 分页表单 添加按钮 打开添加抽屉
const OpenDrawerEvent = () => {
    loadRoleDatas();
    DrawerTitle.value = "添加用户"
    DrawerRef.value.open();
}

// 表格选中的元素列表
let selectAdminUserIdList = [];
// 批量删除按钮事件
const SelectByidDelAdminUserEvent = async () => {
    let params_batchIds = {batchIds:""};
    if(selectAdminUserIdList.length < 0){
        LmMessageError("请选择你要删除的用户！");
        return;
    }
    // 拼接参数 list转String id1,id2,id3,id4 的格式 后端处理就行
    params_batchIds.batchIds = selectAdminUserIdList.toString();
    if(params_batchIds.batchIds == ""){
        LmMessageError("请选择你要删除的用户！");
        return;
    }
    try {
        // 请求服务器
        let serverRet = await adminUserService.delAdminUser(params_batchIds);
        if(serverRet.data == true){
            LmMessageSuccess("批量删除成功！");
            // 加载一次数据
            loadUserAdminData();
        }
        else{
            LmMessageError("批量删除失败！");
        }
    } catch (err) {
        LmMessageError("批量删除失败！");
    }
}
// 表格选中事件
const TableSelectEvent = (selection) => {
    // 每次事件都覆盖上次的数据 不然取消选中会累加上去
    selectAdminUserIdList = [];
    selection.forEach(item=>{
        // 将表格中选中的值 取出id放到list里面
        selectAdminUserIdList.push(item.id);
    });
    console.log(selectAdminUserIdList);
}
// 分页组件回调事件
const pageEvent = (ToPageNo) => {
    pageNo.value = ToPageNo;
    loadUserAdminData();
}
// 更新状态 删除 发布状态
const EventUpdataStatus = async (field,index) =>{
    // 获取参数
    let selectDataStatus = tableData.value[index][field];
    let selectDataId = tableData.value[index].id;
    // 组装参数
    let params = {};
    params[field] = selectDataStatus;
    params.id = selectDataId;
    console.log(params);
    // 发送到服务器
    try{
        let serverRet = await adminUserService.updateAdminUser(params);
        console.log(serverRet);
        if(serverRet.data == true){
            LmMessageSuccess("修改成功！");
        }
        else{
            LmMessageError("修改失败！");
        }
    }catch(err){
        LmMessageError("修改失败！");
    }
}
// 删除用户
const handleDelete = async (index,data)=>{
    try{
        let serverRet = await adminUserService.deleteAdminUserById(data.id);
        if(serverRet.data > 0){
            LmMessageSuccess("删除成功！");
            // 删除成功加载一次数据
            loadUserAdminData();
        }
        else{
            LmMessageError("删除失败！");
        }
    }catch(err){
        LmMessageError("删除失败！");
    }
    console.log(index,data);
}
// 表格 编辑按钮事件  打开抽屉
const EditEvent = (index,data) => {
    data.password = "";
    // 还是浅拷贝问题 直接赋值data的话抽屉更改数值就会把页面的数据改动 
    // 深拷贝解决
    let {...copyData} = data;
    FormUserData.value = copyData;
    console.log(index,data);
    loadRoleDatas();
    DrawerTitle.value = "编辑用户"
    DrawerRef.value.open();
}
// 表单相关 
const DrawerTitle = ref("");
const DrawerRef = ref(null);
// 校验规则
const rules = reactive({
    username: [{required: true,message: '请输入用户名！',trigger: 'blur'}],
    account: [{required: true,message: '请输入账号！',trigger: 'blur'}],
    // password: [{required: true,message: '请输入密码！',trigger: 'blur'}],
    rolesId: [{required: true,message: '请选择角色！',trigger: 'blur'}],
});
// 用于form表单校验回传结果
const ruleFormRef = ref(null);
// form表单角色下拉框数据~数据库回传
const FormUserRoleIdList = ref({});
const loadRoleDatas = async ()=> {
    const serverRet = await adminRoleService.findadminrolesList();
    console.log("loadRoleDatas",serverRet);
    // 转换为int
    serverRet.data.forEach(item=>{item.id=parseInt(item.id)});
    FormUserRoleIdList.value = serverRet.data;
}
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
// 抽屉提交事件  添加和修改功能
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
                // 保存成功加载一次数据
                loadUserAdminData();
                LmMessageSuccess("操作成功");

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


// 查看角色相关

const RoleDiaLogRef = ref(null); // 角色弹窗
const RoleTableData = ref([]);// 展示所有角色表格
const selectUserid = ref(null); // 要查看的用户角色
// 查看用户拥有的角色
const RoleEvent = async (data) => {
    // // 所有的角色
    // let ServerRoleList = await adminRoleService.findadminrolesList();
    // RoleTableData.value = ServerRoleList.data;
    // console.log("----------------------",RoleTableData.value);
    // // 用户拥有的角色
    let ServerResponse = await adminUserService.findRoleByUserid(data.id);
    RoleTableData.value = ServerResponse.data;
    // 要查看的用户角色
    selectUserid.value = data.id;
    RoleDiaLogRef.value.open();
    console.log("----------------------",ServerResponse);
}
// 操作用户角色
const UpdRoleEvent = async (Ritem) => {
    await userRoleRelation[Ritem.isAuth ? "unbind" : "binding"](selectUserid.value, Ritem.id);
    // console.log( Ritem.isAuth ? "解除绑定" : "绑定关系");
    // 重新加载用户角色列表
    let ServerResponse = await adminUserService.findRoleByUserid(selectUserid.value);
    RoleTableData.value = ServerResponse.data;
}
// 操作用户绑定角色
const userRoleRelation = {
    binding(userId, roleId, row) {
        adminUserService.bindingRoleByUserId(userId, roleId).then(res => {
            LmMessageSuccess("绑定成功");
        })
    },
    unbind(userId, roleId, row) {
        return adminUserService.unbindingRoleByUserId(userId, roleId).then(res => {
            LmMessageSuccess("解除绑定成功");
        })
    }
}

</script>