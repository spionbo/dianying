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
	*load(){
		let load;
		while (true){
			load = new Base();
			load.init({
				wrapper : 'loadding',
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
			yield;
			load.$pop.close();
			yield;
		}
	}
}

let MyPlugin = function(){

};
MyPlugin.install = function (Vue, options) {
	let pop = new Pop(options);
	// 4. 添加实例方法
	Object.setPrototypeOf(Vue.prototype,{
		$pop(config){
			pop.init(config);
			return pop;
		},
		$pops : {
			loadding(){
				pop.load().next();
				return pop;
			},
			removeLoadding(){
				pop.load().next();
				return pop;
			}
		}
	})
};
export default MyPlugin;