export default {
	methods:{
		_del(obj){
			let [self,tag] = [this,$(obj.$event.currentTarget)];
			this.$pop({
				title : "删除",
				close : true,
				content : "确定要删除“"+obj.name+"”吗？",
				footer : {
					ok : "确定",
					cancel : "取消",
					okCallback : function(){
						let self = this;
						self.ajax({
							url : obj.url,
							type : "post",
							load : true,
							data : obj.data
						}).then(data=>{
							self.$closePop();
							self.$tips({
								content: "删除成功!"
							});
							obj.update();
						});
					}
				}
			});

		}
	}
}