import { reactive, ref } from 'vue';
// 状态管理
import store from '@/store';
// 路由组件
import router from '@/router';
// 导入全屏组件
import { useFullscreen } from '@vueuse/core'
// 导入弹窗工具类
import { LmMessageSuccess, LmMessageConfirm } from "@/utils/index.js";
// 解构对象 isFullscreen是否全屏(这个也是响应式的值)   toggle全屏切换
const { isFullscreen, toggle } = useFullscreen();
export default function () {

    // 获取抽屉里面的方法 value.xxx调用
    const updataPwdRef = ref(null);

    // 表单数据
    const userPwdFormData = reactive({
        oldPassword: "1",
        newPassword: "2",
        ReDoNewPassword: "3",
    });
    // 获取表单状态
    const updataPwdFromRef = ref(null);
    // 校验规则对象
    const updataPwdRules = reactive({
        oldPassword: [
            { required: true, message: '请输入旧密码', trigger: 'blur' },
            { min: 4, max: 20, message: '你的密码必须是4位~20位', trigger: 'blur' },
        ],
        newPassword: [
            { required: true, message: '请输入新密码', trigger: 'blur' },
            { min: 4, max: 20, message: '你的密码必须是4位~20位', trigger: 'blur' },
        ],
        ReDoNewPassword: [
            { required: true, message: '请输入新密码', trigger: 'blur' },
            { min: 4, max: 20, message: '你的密码必须是4位~20位', trigger: 'blur' },
        ],

    });

    // 抽屉~提交更新密码事件
    const submitUpdataPwdEvent = () => {
        updataPwdFromRef.value.validate((valid) => {
            if (!valid) {
                console.log("提交更新密码事件~表单校验失败");
                return;
            }
            console.log("提交更新密码事件~表单校验成功~提交密码保存",userPwdFormData);

        })
    }


    // 跳转首页
    const handleIndex = () => {
        router.push("/");
    }
    // 折叠菜单
    const handleExpand = () => {
        // alert("折叠菜单")
        store.commit("menu/handleMenuSwitch");
    }

    // 刷新
    const handleRefresh = () => {
        window.location.reload();
    }

    // 全屏幕
    const handleFullScreen = () => {
        toggle();
    }

    /* 下拉框回调事件的方法，不需要暴露出去 start */
    // 修改密码
    const UpdataUserPwd = () => {
        // LmMessageSuccess("UpdataUserPwd");
        updataPwdRef.value.open();

    }
    // 退出登录
    const LogoutUser = async () => {
        let Ret = await LmMessageConfirm("你确定退出登录吗？");
        if (Ret) {
            let serverLogout = await store.dispatch("user/toLogout");
            console.log("index______________", serverLogout);
            if (serverLogout.code == 200) {
                router.push("/toLogin");
                LmMessageSuccess(serverLogout.data);
            }
        }
    }
    // 自定义用户事件对象
    const LmHeaderUserEventObj = {
        UpdataUserPwd,
        LogoutUser
    };

    // 下拉框事件回调函数，然后传入我自定义用户事件对象中执行对应的函数
    const headerUserEvent = (command) => {
        // 这一句相当于 LmHeaderUserEventObj.一个方法名() 然后去执行函数
        LmHeaderUserEventObj[command]();
        //     alert(command);
    }

    return {
        isFullscreen,
        handleIndex,
        handleExpand,
        handleRefresh,
        handleFullScreen,
        headerUserEvent,
        submitUpdataPwdEvent,
        updataPwdRef,
        userPwdFormData,
        updataPwdRules,
        updataPwdFromRef,

    }
}