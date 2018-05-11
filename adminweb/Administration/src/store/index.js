import Vue from "vue";
import Vuex from "vuex";
import cath from "./modules/catch";
import column from "./modules/column";
import permission from "./modules/permission";
import xiaoshuo from "./modules/xiaoshuo";
Vue.use(Vuex);
export default new Vuex.Store({
	/*state,
	mutations,*/
	modules : {
		catch : cath,//缓存
        column,//后台栏目
        permission,//后台栏目权限
		xiaoshuo,
	}
})
