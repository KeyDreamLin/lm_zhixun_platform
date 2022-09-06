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
            <!-- 表格 start -->
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
            <!-- 表格 end -->
        </div>
        <!-- 表格相关 -->
        <!-- 表单相关 -->
        <lm-dialog title="添加商品" fullscreen width="90%" ref="DialogRef" @submit="submitEvent">
            <el-form :model="courseFormData" ref="courseFormRef" :rules="rules" label-width="80px">
                {{courseFormData}}
                <el-row :gutter="20" style="width: 100%;">
                    <el-col :span="16">
                        <el-form-item label="课程标题" required>
                            <el-input 
                                type="text" 
                                v-model="courseFormData.name" 
                                placeholder="请输入课程标题" 
                                maxlength="100" 
                                show-word-limit
                            />
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="课程分类" required >
                            <el-select 
                                v-model="courseFormData.categoryId"  
                                placeholder="请选择课程分类"
                                style="width: 100% ;"  
                            >
                                <el-option 
                                    v-for="item in [{ label: 'Java', value: 1 }, { label: 'Web', value: 0 }]"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value" 
                                />
                            </el-select>
                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="课程封面" >
                            <lm-choose-image></lm-choose-image>
                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="课程标签" >
                            <el-input 
                                type="text" 
                                class="mr-2" 
                                style="width: 20% ;" 
                                v-model="tag_input"
                                placeholder="请输入课程标签"
                                @blur="AddendTagEvent"
                                @keyup.enter="AddendTagEvent"
                                clearable
                            />
                            <el-tag 
                                v-for="(item,index) in tags_list" 
                                :key="index"
                                size="large" 
                                class="mx-1" 
                                round 
                                closable
                                @close="TagDeleteEvent(index)"
                            >{{item}}</el-tag>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="课程售价" >
                            <el-input type="text" v-model="courseFormData.price" placeholder="请输入课程售价">
                                <template #append>元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="课程原价" >
                            <el-input type="text" v-model="courseFormData.realprice" placeholder="请输入课程原价">
                                <template #append>元</template>
                            </el-input>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8"  style=" position: relative;" >
                        <el-form-item label="上架时间" >
                            <el-date-picker
                                v-model="courseFormData.uptime"
                                type="datetime"
                                placeholder="请选择日期和时间"
                                style="width: 100% ;"
                            />
                        </el-form-item>
                    </el-col> 

                    <el-col :span="8">
                        <el-form-item label="有效天数">
                            <el-input-number 
                                placeholder="请选择有效天数" 
                                v-model="courseFormData.days" 
                                :min="1" :max="10000" 
                                style="width: 100%;" 
                            />
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="课程状态"  class="flex items-center">
                            <el-select 
                                v-model="courseFormData.isShow"  
                                placeholder="请选择课程状态"
                                style="width: 100% ;"  
                            >
                                <el-option 
                                    v-for="item in [{ label: '显示', value: 1 }, { label: '不显示', value: 0 }] "
                                    :key="item.value" 
                                    :label="item.label" 
                                    :value="item.value" 
                                />
                            </el-select>
                        </el-form-item>
                    </el-col>

                    <el-col :span="8">
                        <el-form-item label="是否推荐" >
                            <el-select 
                                v-model="courseFormData.isPush"  
                                placeholder="请选择是否推荐"
                                style="width: 100% ;"  
                            >
                                <el-option 
                                    v-for="item in [{ label: '推荐', value: 1 }, { label: '不推荐', value: 0 }]"
                                    :key="item.value" 
                                    :label="item.label" 
                                    :value="item.value" 
                                />
                            </el-select>
                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="课程简介" >
                            <el-input 
                                placeholder="请输入课程简介" 
                                v-model="courseFormData.description"
                                type="textarea" 
                                :rows="4" 
                            />
                        </el-form-item>
                    </el-col>

                    <el-col :span="24">
                        <el-form-item label="课程详情" >
                            <lm-editor v-model="courseFormData.htmlContent" ></lm-editor>
                        </el-form-item>
                    </el-col>
                </el-row>

            </el-form>
        </lm-dialog>
        <!-- 表单相关 -->
    </div>

</template>

<script setup>
import LmDialog from '@/components/LmDialog.vue';
import LmEditor from '@/components/LmEditor.vue';
import LmChooseImage from '@/components/LmChooseImage.vue';
import { reactive, ref } from 'vue';
import { LmMessageError } from '@/utils';
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
    DialogRef.value.open();
}
// 分页组件回调事件
const pageEvent = (pageNo) => {
    console.log(pageNo);
}

// Dialog form 相关
const DialogRef = ref(null);
const courseFormRef = ref(null);
const courseFormData = reactive({
    name:"",
    img:"",
    categoryId:"",
    categoryName:"",
    tags:"哈哈,张三,李四",
    price:"",
    realprice:"",
    uptime:"",
    days:1,
    isShow:1,
    isPush:1,
    description:"",
    htmlContent:""
});
const rules = reactive({

});
// 用于添加tags列表
const tag_input = ref("");
// tags组件
const tags_list = ref(courseFormData.tags.split(","));
// 添加tag标签 input事件
const AddendTagEvent = () => {
    console.log(tag_input.value);
    if(tag_input.value){
        const index = tags_list.value.findIndex(item=>item==tag_input.value);
        if (index == -1){
            tags_list.value.push(tag_input.value);
            courseFormData.tags = tags_list.value.join(",");
            tag_input.value = "";
        }
        else{
            LmMessageError("标签已存在，请勿重复添加！");
        }
    }
}
// 删除tag标签
const TagDeleteEvent = (index) => {
    tags_list.value.splice(index,1);
    courseFormData.tags = tags_list.value.join(",");
}
// 提交
const submitEvent = () => {
    courseFormData.tags = tags_list.value.join(",");
    console.log("商品提交data--->",courseFormData);
    DialogRef.value.close();
}

</script>