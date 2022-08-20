import { ref, reactive, onMounted } from 'vue'
// 路由管理 
import router from '@/router';
// 状态管理
import store from '@/store';
import nprogress from 'nprogress'
import * as utils from '@/utils/index.js';
import captchaService from "@/services/code/CaptchaService.js"
// 导入弹窗工具类
import {LmMessageSuccess,LmMessageError} from "@/utils/index.js";

function UserLogin() {
    //定义form表单对象，用于校验
    const Login_userFormRef = ref(null);
    // 验证码图像
    const captcha_imgData = ref(null);
    // 按钮登录状态
    const login_loading = ref(false);
    const Login_UserData = reactive({
        username: "yykk",
        password: "123456",
        // 用户登录验证码
        code: "",
        codeuuid: "",
    });
    // 登录事件 表单提交
    const LoginEvent = () => {
        // 1: 提交表单校验 校验表单输入
        Login_userFormRef.value.validate(async (valid) => {
            // 如果为valid = false 还存在非法数据.
            if (!valid) {
                console.log("存在非法数据");
                return;
            }
            try {
                nprogress.start();
                login_loading.value = true;
                // 拷贝一份userdata,如果不拷贝直接加密的话，直接将加密的值赋值到对象那input就会显示加密后的数据会很怪
                let {...userCopy} = Login_UserData;
                userCopy.username = utils.encryptByDES(Login_UserData.username);
                userCopy.password = utils.encryptByDES(Login_UserData.password);
                let ret = await store.dispatch("user/toLogin", userCopy);
                // console.log(ret);
                // LmMessageSuccess(ret);
                // 成功就跳转到首页去
                router.push("/");
            } catch (error) {
                createCaptchaEvent();
                Login_UserData.password = "";
                Login_UserData.code = "";
                // alert("3-----err");
                // 失败就弹窗
                LmMessageError(error.msg);
            }finally{
                login_loading.value = false;
                nprogress.done();
            }

            // console.log(store.state.count); // -> 1
            // async await异步转同步
            // await 这个是等待函数，因为axios是异步执行，如果不加等待会马上执行下一句，那就造成data为空

        });
    }

    //4: 定义验证规则
    const userLoginRules = reactive({
        username: [
            { required: true, message: '请输入用户名', trigger: 'submit' },
            { min: 4, max: 20, message: '你的用户名必须是4位~20位', trigger: 'submit' }
        ],
        password: [
            { required: true, message: '请输入密码', trigger: 'submit' },
            { min: 4, max: 220, message: '你的密码必须是4位~20位', trigger: 'submit' }
        ],
        code: [{
            required: true,
            message: '请输入验证码',
            trigger: 'submit'
        }]
    });
    // 5 : 生成验证码
    const createCaptchaEvent = async () => {
        try {
            var serverCode = await captchaService.createCaptcha();

            captcha_imgData.value = serverCode.data.img;
            Login_UserData.codeuuid = serverCode.data.codeuuid;
            // console.log(captcha_imgData.value);
            // console.log(Login_UserData);
        } catch (err) {

        }
    };

    // 6 : 生命周期初始化验证码
    onMounted(() => {
        // const isLogin = store.getters["user/isLogin"];
        // if (isLogin) {
        //     router.push("/");
        //     return;
        // }

        createCaptchaEvent();
        // 后端5分钟清除Redis中的验证码，前端4分钟刷新，这样就不会后端Redis已经删除了这个验证码前端还显示这个验证码出来
        setInterval(createCaptchaEvent, 4 * 60 * 1000);

    });


    // 暴露方法给页面使用
    return {
        Login_userFormRef,
        Login_UserData,
        LoginEvent,
        userLoginRules,
        captcha_imgData,
        createCaptchaEvent,
        login_loading,
    };
};

export default UserLogin;