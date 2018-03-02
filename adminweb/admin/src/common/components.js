Vue.component("pop",{
	template : `
		<transition :name="transition">
			<div class='pop transition' v-show='showModal' @click="closethisPop" :class="obj.superClass">
				<div class="content-wrapper" :class="obj.wrapper" @click='closePropagtion($event)'>
					<a v-if="obj.close" @click="close" href="javascript:void(0)" class='close'><i class="fa fa-times"/></a>
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
		transition : {
			type:String,
			default : 'down'
		},
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
		closethisPop(){
			if(this.obj.removeClose) return;
			this.close();
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
				title = wrapper.children(".title"),
				content = wrapper.children(".content"),
				titHeight = title.height()+26,
				width = window.innerWidth-50,
				height = window.innerHeight-50;

			if(wrapper.width()>=width){
				wrapper.width(width);
			}else{
				wrapper.width('auto')
			}
			if(wrapper.height()>=height){
				wrapper.height(height);
				content.height(height-titHeight);
			}else{
				wrapper.height('auto');
				content.height("auto");
			}
		}
	}
});
Vue.component("page",{
	template : `
	<ul class="pagination" v-if="page" >
		<li v-show="page.pageCount>20 && current!=1" @click="goto(1)">
			<a href="javascript:void(0)" >
				第一页
			</a>
		</li>
		<li :class="current != 1?'':'disabled'" @click="current != 1 && current-- && goto(current)" >
			<a href="javascript:void(0)">上一页</a>
		</li><li v-for="index in pages" @click="goto(index)" :class="{'active':current == index}" :key="index">
			<a href="javascript:void(0)" >{{index}}</a>
		</li><li :class="(allpage != current && allpage != 0)?'':'disabled' " @click="(allpage != current && allpage != 0) && current++ && goto(current++)">
			<a href="javascript:void(0)" >下一页</a>
		</li>
		<li v-show="page.pageCount>20 && current!=page.pageCount" @click="goto(page.pageCount)">
			<a href="javascript:void(0)" >
				最后一页
			</a>
		</li>
		<li class="pageCount">
			共有{{page.pageCount}}页
		</li>
	</ul>
	`,
	props:{
		page : Object
	},
	data(){
		return{
			current:1,
			showItem:5,
			allpage:1
		}
	},
	watch:{
		page( obj ){
			this.current = obj.page;
			this.allpage = obj.pageCount;
		}
	},
	mounted(){

	},
	computed:{
		pages(){
			let pag = [];
			if( this.current < this.showItem ){ //如果当前的激活的项 小于要显示的条数
				//总页数和要显示的条数那个大就显示多少条
				let i = Math.min(this.showItem,this.allpage);
				while(i){
					pag.unshift(i--);
				}
			}else{ //当前页数大于显示页数了
				let middle = this.current - Math.floor(this.showItem / 2 ),//从哪里开始
					i = this.showItem;
				if( middle >  (this.allpage - this.showItem)  ){
					middle = (this.allpage - this.showItem) + 1
				}
				while(i--){
					pag.push( middle++ );
				}
			}
			return pag
		}
	},
	methods:{
		goto:function(index){
			if(index == this.current) return;
			this.current = index;
			this.$emit('$change',index);
		}
	}
});