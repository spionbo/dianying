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

		}
	},
	methods : {

	},
	template: `
	    <div id="app">
	    	<headbar></headbar>
	    	<article id="Content">
	    		<menubar ></menubar>
	        	<router-view class="view"></router-view>
			</article>
	    </div>
	  `
}).$mount('#app');