import cath from "./modules/catch";
import power from "./modules/power";
import state from "./state";
import mutations from "./mutations";
Vue.use(Vuex);

export default new Vuex.Store({
	/*state,
	mutations,*/
	modules : {
		catch : cath,//缓存
		power,//后台栏目管理 权限
	}
})