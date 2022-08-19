import loginService from "@/services/login/LoginService.js"
export default {
    namespaced: true,

    state() {
        return {
            // 是否折叠
            isCollapse: false,
            // 内容区的位移，不然折叠菜单后会出现空余的位置
            menuWidth: "210px",
        }
    },
    mutations: {
        /*折叠方法 折叠菜单还是element写的，但是折叠完之后，主体会存在空隙需要重新为绝对定位赋值*/
        handleMenuSwitch(state) {
            state.menuWidth = state.menuWidth == "210px" ? "60px" : "210px";
            state.isCollapse = state.menuWidth == "60px";
        },
    },
    actions: {
    },
    getters: {

    }

}