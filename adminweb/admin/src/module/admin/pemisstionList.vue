<style scoped>
	@import "../column/list.css";
</style>
<style>
	@import "./style/permissionList.css";
</style>
<template>
	<article class="form box horizontal column-list" v-if="list.length">
		<div class="table box-content" v-if="newlist">
			<div class="tr">
				<div class="th center w80">ID</div>
				<div class="th left flex">栏目名称</div>
				<div class="th center w80">父类ID</div>
				<div class="th center w300">拥有权限</div>
				<div class="th center w100">是否展示</div>
			</div>
			<article class="tbody">
				<div class="tr" v-for="item in newlist" v-bind:key="item.id" :class="item.className">
					<div class="td center w80">{{item.id}}</div>
					<div class="td left flex" v-html="item.name"></div>
					<div class="td center w80">{{item.parentId||"-"}}</div>
					<div class="td center w300">
						<checkbox v-for="obj in item.power"
						          v-bind:key="obj.id"
						          name = "power"
						          :parentData = "item"
						          @checkEvent="powerEvent"
						          :data="obj"></checkbox>
					</div>
					<div class="td center w100">
						<checkbox :checked="item.delTag" :data="{right:'是',error:'否'}"></checkbox>
					</div>
				</div>
			</article>
		</div>
		<div class="submit box-footer">
			<div class="btn" @click="submit">确定修改</div>
			<div class="explort">
				警告：权限开通后，管理员将直接可以更改删除相关栏目及内容，删除后不可再找回。
			</div>
		</div>
	</article>
</template>
<script>
	import checkbox from '../components/checkbox.vue';
	import mixin from '../../common/mixin/_permission';
	export default {
		mixins : [mixin],
		components:{
			checkbox
		},
		props:{
			list : Array,
			item : Object
		},
		data() {
			return {
				tablist : null , //处理后的列表
				newlist : null, //权限 功能列表
				selecteds : {} , //选择的读 更 删 写 列表
				arrlist : [],
				permissions : null,
			}
		},
		mounted() {
			this._setColumnToTree(this.list);

		},
		watch:{
			tablist(arr){
				if(arr.length){
					this.getUserPermission();
				};
			}
		},
		methods:{
			submit(){
				let self = this;
				this._pushSeleced();

				this.ajax({
					url : "/user/updateUserColumnPower",
					type : "post",
					data : {
						power : JSON.stringify(this.arrlist),
						userId : self.item.userId
					}
				}).then(data=>{
					this.$tips({
						content:"操作成功!"
					});
					this.$close();
				})
			},
			getUserPermission(){
				let self = this;
				this.ajax({
					url : "/user/userPermissionPower",
					data : {
						userId : self.item.userId
					}
				}).then(data=>{
					self.permissions = data.data;
					if(self.permissions){
						self.permissions.forEach(obj=>{
							self.tablist.forEach(tab=>{
								if(obj.power && tab.id == obj.permissionId){
									let objPowers = obj.power.replace(/\,$/,"").split(",");
									for(let i =0 ; i <objPowers.length;i++){
										let key = objPowers[i];
										tab.power.forEach(power=>{
											if(power.key==key){
												power.checked = true;
											}
										});
									}
								}
							});
						});
					}
					self.newlist = self.tablist;
				});
			},
			powerEvent( selected , item , parentData ){//记录选择的权限
				let self = this;

				if(!self.selecteds[parentData.id]){
					self.selecteds[parentData.id] = {
						userId : self.item.userId,
						permissionId : parentData.id,
						power : "",
						read : false,
						update : false,
						write : false,
						delete : false,
					};
					this.newlist.forEach(_obj=>{
						if(_obj.id == parentData.id){
							parentData.power.forEach(_obj1=>{
								self.selecteds[parentData.id][_obj1.key] = _obj1.checked;
							});
						}
					});
				}
				self.selecteds[parentData.id][item.key] = selected;
				let power = "";
				let select = self.selecteds[parentData.id];
				if(select.read){
					power += "read,";
				}
				if(select.update){
					power += "update,";
				}
				if(select.write){
					power += "write,";
				}
				if(select.delete){
					power += "delete,";
				}
				self.selecteds[parentData.id].power = power;
			},
			_pushSeleced( item ){
				let arr = [] ,
					newarr = [];
				for(let key in this.selecteds){
					arr.push(this.selecteds[key]);
				}
				if(!arr.length) return;

				if(this.arrlist.length){ //去重
					for(let i=0;i<arr.length;i++){
						let _pid = arr[i].permissionId ,
							has = false;
						for(let j=0;j<this.arrlist.length;j++){
							let obj = this.arrlist[j];
							if( obj.permissionId == _pid ){
								has = true;
								break;
							}
						}
						if(!has){
							newarr.push(arr[i]);
						}
					}
					newarr = newarr.concat(this.arrlist);
				}else{
					newarr = arr;
				}
				this.arrlist = newarr;
				//判断该ID权限是否发生改变
			},
			del(item,$event){
				this._del({
					item : item,
					$event : $event,
					url : "/permission/deleteColumn",
					name : item.name.replace(/\　+\|\-/g,""),
					data : {
						columnId : item.id
					}
				});
			}
		}
	}
</script>