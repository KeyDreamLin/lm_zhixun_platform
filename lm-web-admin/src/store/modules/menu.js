import adminMenuService from "@/services/adminmenu/AdminMenuService.js"

export default {
    namespaced: true,

    state() {
        return {
            // 是否折叠
            isCollapse: false,
            // 内容区的位移，不然折叠菜单后会出现空余的位置
            menuWidth: "210px",
            // 多级菜单数据
            serverMenuTreeData: null,
         
        }
    },
    // 调用的方法commit("名称") 通常用于同步数据、变量
    mutations: {
        /*折叠方法 折叠菜单还是element写的，但是折叠完之后，主体会存在空隙需要重新为绝对定位赋值*/
        handleMenuSwitch(state) {
            state.menuWidth = state.menuWidth == "210px" ? "60px" : "210px";
            state.isCollapse = state.menuWidth == "60px";

        },
        // 设置值MenuTree
        setMenuTree(state, data) {
            // 带上排序
            state.serverMenuTreeData = data.sort((a,b)=>{
                return a.sorted - b.sorted;
            });
        },

    },
    // 调用的方法dispatch("名称")  通常用于异步查询
    actions: {
        // 查询服务器菜单树数据
        async findServerMenuTree(context) {
            let serverRes = await adminMenuService.tree();
            context.commit("setMenuTree", serverRes.data);
            // console.log("-------",state.serverMenuTreeData);
        }
    },
    // getters["名称"]  获取数据
    getters: {
        // 获取值
        getMenuTree(state) {
            return state.serverMenuTreeData;
        }
    }

}