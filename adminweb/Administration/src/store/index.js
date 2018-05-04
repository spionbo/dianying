import Vue from "vue";
import Vuex from "vuex";
import cath from "./modules/catch";
import power from "./modules/power";
import xiaoshuo from "./modules/xiaoshuo";
Vue.use(Vuex);
export default new Vuex.Store({
	/*state,
	mutations,*/
	modules : {
		catch : cath,//缓存
		power,//后台栏目管理 权限
		xiaoshuo,
	}
})
