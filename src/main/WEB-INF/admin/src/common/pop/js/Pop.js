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
	resize(){
		super.resize();
		this.update();
	}
	update(){

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
				load.$pop.close();
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
	Object.setPrototypeOf(Vue.prototype,{
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
		}
	})
};
export default MyPlugin;