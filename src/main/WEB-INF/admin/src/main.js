import './style/main.css';
import './style/font-awesome.css';
import store from './store';

Vue.use(VueRouter);
import './router';
import Pop from './common/pop/js/Pop';
import './common/components';
Vue.use(Pop);

import headbar from './module/head';
import menubar from './module/menu';
window.Main = new Vue({
	router,
	store,
	components : {
		headbar,
		menubar
	},
	data(){
		return {
			isLogin : true
		}
	},
	watch : {
		isLogin( val ){
			debugger;
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
		}
	},
	template: `
	    <div id="app">
	    	<div v-if="isLogin" class='main-controller'>
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
			<router-view v-else class="view" name="login"></router-view>
	    </div>
	  `
}).$mount('#app');