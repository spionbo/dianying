/**
 * 弹窗
 * create from bo.peng 2018/3/27
 */
import Base from './Base';
import './bass.css';
class Pop extends Base{
	constructor(){
		super();
	}
	init(config){
		super.init(config);
		this.resize();
	}
	tips( obj ){
		let tips = new Base() ,
			config = {
				wrapper : 'tips',
				removeClose : true,
                paddingBottom : "0"
			};
		Object.assign(config,obj);
		tips.init(config);
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
};
((window,Pop)=>{
    let pop = new Pop();
	window.alert = (content)=>{
		pop.init({
			content : content
		})
	}
})(window,Pop);

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
			pop.requireElePop(sys,data,popData);
		},
        $require(sys,data,popData){
            pop.requireEle(sys,data,popData);
        },
		$tips(conf){
			pop.tips(conf);
		},
		$close(){
			pop.close();
		}
	})
};
export default MyPlugin;
/**
 * 弹窗调用方式
 * tips :
 * this.$tips({
 *  time:3000,//3秒
 *  content : "内容"
 * })
 * 常见弹窗：
 this.$pop({
	title : "标题",
	close : true,//是否显示关闭按钮
	content : "内容",
	icon : "icon1",//icon1 确定 2取消 3提示
	footer : {
		ok : "确定",//确定按钮
		cancel : "取消", //取消按钮
		okCallback : function(){}, //确定按钮事件
		cancelCallback : function(){},//取消按钮事件
	}
});
 *加载组件弹窗
 require.ensure([],(require)=> {
	this.$requirePop(require('组件名称'), {
			props : {} //弹窗内容相关属性
		},
		{
			props: {//弹窗相关属性 同上面的 this.$Pop
				obj: {
					title: "标题",
					close: true,//是否显示关闭按钮
					wrapper : "弹窗样式",
					superClass : "最外层"
				}
			}
		});
});
 */
