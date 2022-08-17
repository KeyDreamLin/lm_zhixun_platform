import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

import router from './router/index.js'

import LmUI from "../src/components/install.js"

const app = createApp(App)

app.use(router);
// 全局注册
// app.component("LmButton",LmButton);
// 安装插件，会执行里面的方法
app.use(LmUI);

app.mount('#app');
