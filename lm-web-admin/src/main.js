import { createApp } from 'vue'
import App from './App.vue'

// element组件导入
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// 自定义组件导入
import LmUi from '@/components/install.js'

// 一个css库
import 'virtual:windi.css'

/*交互进度条的样式 */
import 'nprogress/nprogress.css'

// 导入路由
import router from '@/router'

// 导入状态管理
import store from '@/store' //默认使用Session

// 导入自定义的permission指令 
import Vpermission from '@/directive/Vpermission'

const app = createApp(App);
app.use(ElementPlus)
// 引入所有图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// 注册路由
app.use(router)

// 注册状态管理
app.use(store)

// 注册自定义组件
app.use(LmUi)

// 导入自定义的permission指令 
Vpermission(app);

app.mount('#app')
