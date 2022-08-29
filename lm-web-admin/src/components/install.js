import LmDrawer from "@/components/LmDrawer.vue";
import LmNum from "@/components/LmNum.vue";
import LmChooseImage from "@/components/LmChooseImage.vue";
import LmUploaFile from "@/components/LmUploaFile.vue";

// plugins/i18n.js
export default {
    install: (app, options) => {
        // alert("注册成功")
        // 在这里编写插件代码
        app.component("LmDrawer", LmDrawer);
        app.component("LmNum", LmNum);
        app.component("LmChooseImage", LmChooseImage);
        app.component("LmUploaFile", LmNLmUploaFileum);

    }
}