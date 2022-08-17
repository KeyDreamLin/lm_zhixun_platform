
import LmButton from "../../src/components/LmButton.vue"
import LmInput from "../../src/components/LmInput.vue"
import LmCard from "../../src/components/LmCard.vue"
export default({
    // 通过插件的形式注册
    // vue 会自动找到这个install这个方法去执行
    install(app){
        // 注册组件
        app.component('LmButton',LmButton);
        app.component('LmInput',LmInput);
        app.component('LmCard',LmCard);
    }
});