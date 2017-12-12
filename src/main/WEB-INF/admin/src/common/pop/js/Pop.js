import Base from './Base';
import '../bass.css';

@version("1.1.0")
class Pop extends Base{
	constructor( Vue, options ){
		super(Vue, options);
	}
	init(config){
		super.init(config);
		this.resize();
	}
	resize(){
		super.resize();
		this.update();
	}
	@log
	update(){

	}
}
function version(isTestable){
	return function(target) {
		target.version = isTestable;
	}
}
function log(target,name,descriptor){
	let oldValue = descriptor.value;
	descriptor.value = function() {
		console.log(`更新"${name}" with`, arguments);
		return oldValue.apply(null, arguments);
	};

	return descriptor;
}
let MyPlugin = function(){

};
MyPlugin.install = function (Vue, options) {
	// 4. 添加实例方法
	Vue.prototype.$pop = function (config) {
		let pop = new Pop(Vue,options);
		pop.init(config);
		pop.update(2,4);
		// 逻辑...
	}
};
export default MyPlugin;