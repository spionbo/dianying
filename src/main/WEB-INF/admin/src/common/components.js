Vue.component("pop",{
	template : `
				<transition name="down">
					<div class='pop transition' v-show='showModal' @click="close">
						<div class="content-wrapper" @click='closePropagtion($event)'>
							<a v-if="obj.close" @click="close" href="javascript:void(0)" class='close'>关闭</a>
							<div v-if="obj.title" class='title'>
								<slot name='title'></slot>
							</div>
							<div class='content'>
								<slot name='content'></slot>
							</div>
							<div v-if="obj.footer" class='footer'>
								<slot name='footer'></slot>
							</div>
						</div>
					</div>
				</transition>
			`,
	props : {
		obj : Object,
		closeCallback : {
			type: Function,
			default: function () {

			}
		},
		closePop : Function
	},
	data(){
		return {
			showModal : false
		}
	},
	mounted(){
		window.onresize = ()=>{
			if(this.showModal){
				setTimeout(this.setSize,200);
			}
		}
	},
	methods : {
		closePropagtion : function( e ){
			e.stopPropagation();
		},
		show : function(callback){
			this.showModal = true;
			$('html').css({height:'100%',overflow:'hidden'});
			setTimeout(()=>{
				this.setSize();
				if($.type(callback) == 'function'){
					callback();
				}
			},310);
		},
		close : function(){
			this.showModal = false;
			$('html').removeAttr('style');
			setTimeout(()=>{
				$(this.$el).parent().remove();
			},310);
			this.closeCallback();
			this.closePop && this.closePop();
		},
		setSize(){
			let ele = $(this.$el) ,
				wrapper = ele.find('.content-wrapper'),
				width = window.innerWidth-50,
				height = window.innerHeight-50;

			if(wrapper.width()>=width){
				wrapper.width(width);
			}else{
				wrapper.width('auto')
			}
			if(wrapper.height()>=height){
				wrapper.height(height);
			}else{
				wrapper.height('auto')
			}
		}
	}
});