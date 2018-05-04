/**
 * 弹窗基类
 * create from bo.peng 2018/3/27
 */
import Vue from "vue";
import store from '../../store';
export default class Base{
	get author(){
		return "bo.peng";
	}
	get email(){
		return "spion@qq.com";
	}
	constructor(){
		this.config = {
			winPop : {}
		};
	}
	init(config){
		Object.assign(this.config.winPop , config);
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
	set parent(value){
		this._parent = value;
	}
	get parent(){
		return this._parent;
	}
	requireEle(){//加载 element
        /*require.ensure([],(require)=>{
            $('<div class="child">').appendTo('body');
            let sys = require('./pwdPop.vue');
            sys = sys.default ? sys.default : sys;
            Vue.component(
                'investmentPop',
                sys
            );
            new Vue({
                el : '.child' ,
                render(h){
                    return h('investmentPop',{
                        props : {
                            transition : 'rotate3d',
                            product : self.product,
                            user : self.user,
                            coupon : self.selectCoupon
                        }
                    })
                }
            });
        });*/
    }
	requireElePop(sys , data , popData){ // popData 弹窗的props属性
		let self = this,
			childName = "child"+new Date().getTime() ,
			name = "elename"+new Date().getTime() ,
			dataconfig = {
				props:{
					obj : {}
				},
				slot:"content"
			},
			popconfig = {
				props:{
					obj : {}
				},
				ref : "requirepop"
			};
		Object.assign(dataconfig , data);
		if(popData.props&&!popData.props.superClass){
			popData.props.superClass = "clearPadding";
		}
		Object.assign(popconfig , popData);
		data = dataconfig;
		popData = popconfig;

		$(`<div class="${name}${childName}" style="position:absolute;left:0;top:0">
				<div class="${childName}"></div>
			</div>
		`).appendTo('body');
		try{
			sys = sys.default ? sys.default : sys;
			Vue.component(
				name,
				sys
			);
		}catch (e){

		}
		new Vue({
			store,
			el : '.'+childName ,
			mounted(){
			    if(popData.props.obj.showPop!==false){
			        setTimeout(()=>{
				        this.$refs.requirepop.show();
                    },50)
                }
				self.close = this.close;
			},
			render(h){
				return h("pop" , {...popData}, [
					h("div" ,
						{
							slot : "title",
							domProps: {
								innerHTML: popData.props.obj.title
							}
						} ,
						//[popData.props.obj.title]
					),
					h(name,{...data})
				]);
			},
			methods : {
				close(){
					this.$refs.requirepop.close();
				}
			}
		});
	}
	createEle(){
		let self = this,
			win = this.config.winPop,
			childName = "child"+new Date().getTime();
		$(`
			<div class="`+childName+`" style="position:absolute;left:0;top:0">
				<pop ref="pop" :transition='win.transition' :obj="win">
					<div v-if="win.title" slot='title' v-html="win.title"></div>
				    <div slot="content" style="padding: 10px 20px; text-align:center;" v-html="win.content">
				    
				    </div>
				    <div slot='footer' v-if="win.footer">
				        <div class="btn" @click="okCallback">{{win.footer.ok}}</div>
				        <div class="btn cancel" v-if="win.footer.cancel" @click="cancelCallback">{{win.footer.cancel}}</div>
					</div>
				</pop>
			</div>
		`).appendTo('body');
		this.component = new Vue({
			store,
			el:'.'+childName,
			data(){
				return {
					win : win
				};
			},
			mounted(){
				self.$pop = this.$refs.pop;
				this.$refs.pop.show();
				self.close = this.close;
			},
			methods:{
				okCallback(){
					if(this.win.footer.okCallback){
						this.win.footer.okCallback.call(this);
					}else{
						this.$refs.pop.close();
					};
				},
				cancelCallback(){
					let self = this;
					if(this.win.footer.cancelCallback){
						this.win.footer.cancelCallback.call(this);
					}else{
						self.$refs.pop.close();
					};
				},
				$closePop(){
					this.close();
				},
				close(){
					this.$refs.pop.close();
				}
			}
		});
	}
}
