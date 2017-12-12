function readonly(target, name, descriptor){
	descriptor.writable = false;
	return descriptor;
}
export default class Base{
	@readonly
	name(){ return "bo.peng" }
	constructor(Vue, options){
		this.Vue = Vue;
		this.options = options;
		this.config = {
			winPop : false
		};
	}
	init( config ){
		Object.assign(this.config.winPop,config);
		this.createEle();
		this.resize();
	}
	update(){
		this.width = $(window).width();
		this.height = $(window).height();
	}
	resize(){
		this.update();
	}
	set parent( value ){
		this._parent = value;
	}
	get parent(){
		return this._parent;
	}
	createEle(){
		let childName = "child"+new Date().getTime();
		$(`
			<div class="`+childName+`">
				<pop ref="pop" :obj="win">
				    <div slot="content" style="padding: 10px 20px;">
				       <img src="/images/escrow-account/upgrade-mark.jpg">
				    </div>
				    <div slot='footer'>
				        <a class="btn" href="/activitys/huaruiBank" target="_blank" style="margin-right:10px;border: 1px solid #fd9927;color: #fd9927;background:#fff;">了解银行存管</a>
				        <div @click="activate" class="btn" >激活存管账户</div>
					</div>
				</pop>
			</div>
		`).appendTo('body');
		new Vue({
			el: '.'+childName,
			data(){
				return {
					win : {
						footer: true,
					}
				}
			},
			mounted(){
				this.$refs.pop.show();
			},
			methods:{
				activate(){
					location.href="/main/createEscrowAccount"
				}
			}
		});
	}
	remove(){

	}
}