import { createApp } from 'vue'
import App from './App.vue'

// element 样式导入
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import 'virtual:windi.css'
/*交互进度条的样式 */
import 'nprogress/nprogress.css'
// 导入路由
import router from '@/router'
// 导入状态管理
import store from '@/store'


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
app.mount('#app')
