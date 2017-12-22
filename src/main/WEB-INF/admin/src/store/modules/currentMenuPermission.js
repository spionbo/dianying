module.exports={
	state : {
		currentMenuPermission : {},//所有新增栏目
	},
	mutations:{
		setCurrentMenuPermission(state,obj){
			state.currentMenuPermission = obj;
		}
	},
	actions:{},
	getters:{
		currentMenuPermission : state=>{
			return state.currentMenuPermission;
		}
	}
};