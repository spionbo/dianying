import Vue from "vue";
import VueRouter from 'vue-router';
import Jquery from "../static/plug/jquery-1.10.2.min";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
/*import './style/element-variables.scss'*/
import T from "./common/global";
import './style/main.css';
/*import './style/font-awesome.css';*/
import store from './store';
import './router';
import Pop from './common/pop/Pop';
import './common/vuePlugin';
import './middle/components';
import './mixin';
import menuBar from './module/menu';
import headerBar from './module/head';
Vue.use(VueRouter);
Vue.use(Pop);
Vue.use(ElementUI);
if (!Object.assign) {
    Object.assign = $.extend;
}
Object.assign(window,{
    $ : Jquery,
    T : T,
    Vue : Vue,
});
window.Main = new Vue({
	router,
	store,
	components : {
        elContainer : ElementUI.Container,
        elMain : ElementUI.Main,
        elHeader : ElementUI.Header,
		menuBar,
        headerBar
	},
	data(){
		return {
			isLogin : true
		}
	},
	watch : {
		'$route'(to, from){
			require('./router/route').call(this,to,from);
		},
		isLogin( val ){
			if(val){
				router.push('main');
			}else{
				router.push('login');
			}
		}
	},
	mounted(){
		T.init();
	},
	methods : {
		setLoginStatus(boolean){
			this.isLogin = boolean;
		},
	},
	template: `
	    <div id="app">
            <el-container v-if="isLogin">
                <el-header class="header"><header-bar></header-bar></el-header>
                <el-container>
                    <menu-bar></menu-bar>
                    <el-main class="view">
			            <transition name="bounce">
			                <router-view></router-view>
			            </transition>
		        	</el-main>
                </el-container>
            </el-container>
            <router-view v-else class="view" name="login"></router-view>
	    	<!--<div v-if="isLogin" class='main-controller'>
		    	<headbar></headbar>
		    	<article id="Content">
		    		<menubar></menubar>
		    		<div class="view">
			            <transition name="bounce">
			                <router-view></router-view>
			            </transition>
		        	</div>
				</article>
			</div>
			<router-view v-else class="view" name="login"></router-view>-->
	    </div>
	  `
}).$mount('#app');
