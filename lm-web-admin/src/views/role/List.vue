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

            <el-table :data="tableData" style="width: 100%" @selection-change="TableSelectEvent">
                <el-table-column type="selection" width="55" />
                <el-table-column prop="id" label="Id" width="100" />
                <el-table-column prop="roleName" label="角色代号"/>
                <el-table-column prop="roleCode" label="角色名称" />

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
                
                <el-table-column label="操作" width="245px">
                    <template #default="scope">
                        <el-button size="small" icon="View" @click="handlePermissions(scope.$index, scope.row)">授权</el-button>
                        <el-button size="small" icon="Edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
            </div>
        </div>
        <!-- 表单相关 -->
        <lm-drawer ref="DrawerRef" :title="DrawerTiele"  @submit="drawerSubmitEvent">
            <el-form ref="ruleFormRef" :model="FormRoleData" status-icon :rules="rules" label-width="120px">

                <el-form-item label="角色名称" prop="roleName" >
                    <el-input v-model="FormRoleData.roleName" type="text" />
                </el-form-item>

                <el-form-item label="角色代号" prop="roleCode" >
                    <el-input v-model="FormRoleData.roleCode" type="text" />
                </el-form-item>
             

                <el-form-item label="发布状态" prop="status">
                    <el-switch 
                        v-model="FormRoleData.status" active-color="#8389c7" 
                        inline-prompt active-text="是" :active-value="1" inactive-text="否" :inactive-value="0"
                     />
                </el-form-item>

                <el-form-item label="删除状态" prop="isdelete">
                    <el-switch
                        v-model="FormRoleData.isdelete" active-color="#8389c7" inline-prompt 
                        active-text="是" :active-value="1" inactive-text="否" :inactive-value="0" 
                    />
                </el-form-item>

                {{ FormRoleData }}
            </el-form>
        </lm-drawer>

        <!-- 权限 相关 抽屉 -->
        <lm-drawer
         ref="PermissionDrawerRef" title="编辑权限" @submit="drawerSubmitPermissionsEvent">
            <el-tree
                ref="PermissionTreeRef"
                :data="FormPermissionData"
                show-checkbox node-key="id"
                :default-expanded-keys="PermissionDefaultExpandedKeys"
                :props="{label: 'name'}"
                :check-strictly="treeCheckStrictly"
            >
            <!-- @check-change ="treeCheckChangeEvent" -->
                <template #default="{ node, data }">
                    <template v-if="data.type==1" >
                        <el-tag 
                        class="mr-2" type="success">
                        菜单
                        </el-tag>
                    </template>
                    <template v-else >
                        <el-tag 
                        class="mr-2" type="warning">
                        权限
                        </el-tag>
                    </template>
                    <span>{{data.name}} ++ {{data.id}}</span>
                </template>
            </el-tree>
        </lm-drawer>
    </div>

</template>
<script setup>
import LmChooseImage from '@/components/LmChooseImage.vue';
import LmDrawer from '@/components/LmDrawer.vue';
import adminRoleService from '@/services/adminrole/AdminRoleService';
import permissionService from '@/services/permission/PermissionService';

import { LmMessageError, LmMessageSuccess } from '@/utils';
import { ref, reactive, onMounted } from 'vue';

// 表格 -- 数据 包括分页
const tableData = ref([]);// 表格数据
const total = ref(0); // 总条数
const pageCount = ref(1); // 总页数
const pageSize = ref(10); // 需要每一页有多少条
const pageNo = ref(1);// 当前页数
const keyword = ref("");// 关键词查询
// 表格 -- 数据 包括分页end

// 表格 -- 事件 包括查询删除等

// 打开抽屉 添加角色
const OpenDrawerEvent = () => {
    DrawerTiele.value = "添加角色"
    resetFormData();
    DrawerRef.value.open();
}

// 表格选中元素 事件
const TableSelectEvent = (val) => {
    console.log(val);
}
// 加载角列表数据
const loadRoleData = async () => {
    const params = {pageNo:pageNo.value,pageSize:pageSize.value,keyword:keyword.value};
    let serverRet = await adminRoleService.findAdminRolesPage(params);
    console.log(serverRet);
    // 将服务器的数据组装起来
    let retData = serverRet.data;
    tableData.value = retData.records;
    total.value = retData.total;
    pageSize.value =retData.size;
    pageCount.value = retData.pages;
    pageNo.value = retData.current;
}
// 分页查询事件 
const pageEvent = (retPageNo) => {
    pageNo.value = retPageNo;
    loadRoleData();
}
// 打开抽屉 编辑数据事件
const handleEdit =  (index, item) => {
    DrawerTiele.value = "编辑角色"
    FormRoleData.value = {...item};// 对象解构 等于这样写等于拷贝了一份item出来 
    DrawerRef.value.open();
    console.log("8908",index,item);
}
// 表格 删除单条数据事件
const handleDelete = async (index, item) =>{
    try{
        await adminRoleService.deleteAdminRolesById(item.id);
        // 保存成功加载一次数据
        loadRoleData();
        LmMessageSuccess("删除成功");
    }catch(err){
        console.error("保存数据出错-->",err);
        LmMessageError(err.msg);
    }
}
// 表格 -- 事件 包括查询删除等 end

// 生命周期
onMounted(()=>{
    // 加载表格数据
    loadRoleData();
})







// form表单  相关
const DrawerTiele = ref(""); // 表单标题
const DrawerRef = ref(null);
// 用于form表单校验回传结果
const ruleFormRef = ref(null);
// 校验规则
const rules = reactive({
    "rolename": [{ required: true, message: "必填项！", trigger: 'blur' }],
    "displayname": [{ required: true, message: "必填项！", trigger: 'blur' }],
});
// 表单数据
const FormRoleData = ref({
    id:"",
    roleName: "",
    roleCode: "",
    status: 1,
    isdelete : 0,
});
// 复位表单数据
const resetFormData = () =>{
    FormRoleData.value = {
        id:"",
        roleName: "",
        roleCode: "",
        status: 1,
        isdelete : 0,
    }
}
// 表单提交事件
const drawerSubmitEvent = () => {
    // 表单校验
    ruleFormRef.value.validate(async (val)=>{
        // 表单校验通过
        if(val){
            try{
                let serverRet = await adminRoleService.saveupdateAdminRoles(FormRoleData.value);
                // 清空表单数据
                resetFormData();
                // 保存成功加载一次数据
                loadRoleData();
                LmMessageSuccess("操作成功");
                DrawerRef.value.closeNoMsg(); 
            }catch(err){
                console.error("保存数据出错-->",err);
                LmMessageError(err.msg);
            }
        }
    })
}

const PermissionDrawerRef = ref(null); // 权限抽屉 ref
const FormPermissionData = ref([]); // 树形菜单 数据
const PermissionTreeRef = ref(null); // 权限Tree ref
const PermissionDefaultExpandedKeys = ref([]); // 展开的节点的key的数组
const selectRoleId = ref(-1); // 选中的需要编辑的角色id
const treeCheckStrictly = ref(false);// 是否允许父级联动 true就是可以个选个的 flase是可以联动
// 打开 编辑 权限 抽屉
const handlePermissions = async (index,item) => {
    PermissionDrawerRef.value.open();
    // 赋值 每次打开都重新赋值
    selectRoleId.value = item.id;
    treeCheckStrictly.value = true;
    let permissionServerRet = await permissionService.permissionTree();
    let permissionData = permissionServerRet.data;
    // 给form表单的tree组件赋值
    FormPermissionData.value = permissionData;
    console.log(permissionData);
    // 用于展开整个tree //=[]就是收起全部 用于展开
    PermissionDefaultExpandedKeys.value = FormPermissionData.value;

    // 查询这个角色id对应的权限列表 查询该角色拥有的权限 然后勾选起来
    let roleToPermission = await adminRoleService.findPermissionByRoleId(item.id);
    // console.log("-----!!!!!!!!!!!!!!!!!!!!!!----,",roleToPermission);
    PermissionTreeRef.value.setCheckedKeys( roleToPermission.data );
    // console.log("???",roleToPermission.data );
    treeCheckStrictly.value = false;

}
// 提交按钮 权限抽屉 
const drawerSubmitPermissionsEvent = async () => {
    // getCheckedKeys获取选中节点  getHalfCheckedKeys获取半选中的节点
    // 获取选中项
    console.log( PermissionTreeRef.value.getCheckedKeys());
    console.log( PermissionTreeRef.value.getHalfCheckedKeys());
        
    const checkedIds =[ 
        ...PermissionTreeRef.value.getCheckedKeys(),
        ...PermissionTreeRef.value.getHalfCheckedKeys()
    ];// 解构数组 一样的
    console.log(checkedIds);
    // 数组toString 就会变成1,2,3,4,5
    try{
        await adminRoleService.savePermissionByRoleId(selectRoleId.value,checkedIds.toString());
        LmMessageSuccess("授权成功！！！");
        PermissionDrawerRef.value.closeNoMsg();

    }catch(err){
        LmMessageError("授权失败！！！");
    }
   
}

// element 树形控件父子节点不相关联（勾选父节点时，子节点不会被勾选，取消父节点时，子节点全部取消）
// const treeCheckChangeEvent = (a,b,c) => {
//     // 如果为取消
//     if(b === false){
//         if(a.children){
//             //循环子集将他们的选中取消
//             a.children.map(item => {
//                 PermissionTreeRef.value.setChecked(item.id,false);
//             });
//         }
//     }
//     //如果当前节点有子集
//     else{
//         //否则(为选中状态)
//         //判断父节点id是否为空
//         if(a.pid !== 0){
//         //如果不为空则将其选中
//             PermissionTreeRef.value.setChecked(a.pid,true);
//         }   
//     }
// }
</script>