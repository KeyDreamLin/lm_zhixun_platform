// 导入状态管理
import store from '@/store' //默认使用Session
import { type } from 'windicss/utils';

export default function(app){// 使 v-permission 在所有组件中都可用
    app.directive('permission', {
        mounted(el, binding){
            // 这样做是为了可以让前面v-permission赋值更加方便 v-permission="123"是数值类型需要
            const bindingVal = typeof binding.value == "object" ? binding.value : [binding.value] ;
            let checkePermisson = checkePermissonEvent(bindingVal);
            if(!checkePermisson){
                el.remove();// 移除dom对象
            }
            // console.log(checkePermisson);
            // console.log("-------------------------------",el,binding,UserPermissionsList);
        }
    });
    // 校验用户权限
    const checkePermissonEvent = (bindingVal) => {
        // 获取用户拥有的权限列表
        let UserPermissionsList = store.getters["user/getPermissions"];
        const count = bindingVal.filter(code => {
            return UserPermissionsList.includes(code+"");
        });
        // 如果匹配到对应的权限就返回true
        return count > 0;
    }
}