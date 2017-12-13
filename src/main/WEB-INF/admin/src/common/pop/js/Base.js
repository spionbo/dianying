export default class Base{
	constructor(){
		this.config = {
			winPop : {}
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
		let win = this.config.winPop,
			childName = "child"+new Date().getTime();
		$(`
			<div class="`+childName+`">
				<pop ref="pop" :obj="win">
					<div v-if="win.title" slot='title'>
						{{win.title}}
					</div>
				    <div slot="content" style="padding: 10px 20px; text-align:center;" v-html="win.content">
				       
				    </div>
				    <div slot='footer' v-if="win.footer">
				        <div class="btn" @click="okCallback">{{win.footer.ok}}</div>
				        <div class="btn cancel" @click="cancelCallback">{{win.footer.cancel}}</div>
					</div>
				</pop>
			</div>
		`).appendTo('body');
		new Vue({
			el: '.'+childName,
			data(){
				return {
					win : win
				}
			},
			mounted(){
				this.$refs.pop.show();
			},
			methods:{
				okCallback(){
					this.win.footer.okCallback();
				},
				cancelCallback(){
					this.win.footer.cancelCallback();
				}
			}
		});
	}
}