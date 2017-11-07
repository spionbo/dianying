import './style/main.css';
import './style/font-awesome.css';
Vue.use(VueRouter);
import './router';

import headbar from './module/head';
import menubar from './module/menu';
new Vue({
	router,
	components : {
		headbar,
		menubar
	},
	data(){
		return {
			isLogin : false
		}
	},
	mounted(){
		T.ajax({
            url : '/webapi/user/login' ,
            type : 'post',
            data : {
                userName : 'admin',
                pwd : '123456'
            }
        });
	},
	methods : {

	},
	template: `
	    <div id="app">
	    	<div v-if="isLogin" class='main-controller'>
		    	<headbar></headbar>
		    	<article id="Content">
		    		<menubar></menubar>
		        	<router-view class="view"></router-view>
				</article>
			</div>
			<router-view v-else class="view" name="login"></router-view>
	    </div>
	  `
}).$mount('#app');