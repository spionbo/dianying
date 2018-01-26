module.exports={
	state : {
		addColumn : {},//所有新增栏目
	},
	mutations:{
		setCatch(state,obj){
			$.extend(state.addColumn,obj);
		}
	},
	actions:{},
	getters:{
		addColumn : state=>{
			return state.addColumn;
		}
	}
};