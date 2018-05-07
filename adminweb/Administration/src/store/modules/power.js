const URL = require("../../contant/URLS/PERMISSION");
module.exports={
	state : {
		allColumn : null, //所有栏目
		allColumnTree : null, //按树状 展示父子栏目
		currentMenuPermission : null,//所有新增栏目
	},
	mutations:{
		setCurrentMenuPermission(state,obj){
			state.currentMenuPermission = obj;
		},
		setAllColumn( state ,  obj ){
			state.allColumn = obj;
		},
		setAllColumnTree( state ,  obj ){
			state.allColumnTree = obj;
		}
	},
	actions:{
		setAllColumn(context , callback){
			this._vm.ajax({
				url : URL.ALL_COLUMN,
			}).then(data=>{
				context.commit("setAllColumn",data.data);
				callback && callback(data.data);
			});
		},
		setAllColumnTree(context , callback){
			this._vm.ajax({
				url : URL.ALL_COLUMN,
				data : {
					isTree : true
				}
			}).then(data=>{
				context.commit("setAllColumnTree",data.data);
				callback && callback(data.data);
			});
		}
	},
	getters:{
		allColumn : state=>{
			return state.allColumn;
		},
		allColumnTree : state=>{
			return state.allColumnTree;
		},
		currentMenuPermission : state=>{
			return state.currentMenuPermission;
		}
	}
};
