import cath from "./modules/catch";
import state from "./state";
import mutations from "./mutations";
Vue.use(Vuex);

export default new Vuex.Store({
	/*state,
	mutations,*/
	modules : {
		catch : cath,//缓存
	}
})