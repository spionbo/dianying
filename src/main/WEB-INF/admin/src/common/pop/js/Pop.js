import Base from './Base';
import '../bass.css';
class Pop extends Base{
	constructor(){
		super();
	}
	init(config){
		super.init(config);
		this.resize();
	}
	tips( obj ){
		let tips = new Base();
		tips.init({
			wrapper : 'tips',
			removeClose : true,
			content : obj.content
		});
		setTimeout(function(){
			tips.$pop.close();
		},obj.time || 800 );
	}
	load(){
		let load;
		return{
			add(){
				load = new Base();
				load.init({
					wrapper : 'loadding',
					removeClose : true,
					content : `
						<div class="sk-spinner sk-spinner-wave">
							<div class="sk-rect1"></div>
							<div class="sk-rect2"></div>
							<div class="sk-rect3"></div>
							<div class="sk-rect4"></div>
							<div class="sk-rect5"></div>
						</div>
					`
				});
			},
			remove(){
				if(load) load.$pop.close();
			}
		};
	}
}

let MyPlugin = function(){

};
MyPlugin.install = function (Vue, options) {
	let pop = new Pop(options) ,
		loading = pop.load();
	// 4. 添加实例方法
	Object.assign(Vue.prototype,{
		$pop(config){
			pop.init(config);
			return pop;
		},
		$pops:{
			loadding(){
				loading.add();
				return pop;
			},
			removeLoadding(){
				loading.remove();
				return pop;
			}
		},
		$requirePop(sys,data,popData){
			pop.requireEle(sys,data,popData);
		},
		$tips(conf){
			pop.tips(conf);
		}
	})
};
export default MyPlugin;