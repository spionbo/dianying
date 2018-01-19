<style scoped>
	@import "../column/list.css";
</style>
<style>
	@import "./style/permissionList.css";
</style>
<template>
	<article class="form horizontal column-list" v-if="list.length">
		<div class="table" v-if="tablist">
			<div class="tr">
				<div class="th center w80">ID</div>
				<div class="th left flex">栏目名称</div>
				<div class="th center w80">父类ID</div>
				<div class="th center w300">拥有权限</div>
				<div class="th center w100">是否展示</div>
			</div>
			<article class="tbody">
				<div class="tr" v-for="item in tablist" v-bind:key="item.id" :class="item.className">
					<div class="td center w80">{{item.id}}</div>
					<div class="td left flex" v-html="item.name"></div>
					<div class="td center w80">{{item.parentId||"-"}}</div>
					<div class="td center w300">
						<checkbox v-for="obj in item.power"
						          v-bind:key="obj.id"
						          name = "power"
						          :parentData = "item"
						          :checked="obj.checked"
						          @checkEvent="powerEvent"
						          :data="obj"></checkbox>
					</div>
					<div class="td center w100">
						<checkbox :checked="item.delTag" :data="{right:'是',error:'否'}"></checkbox>
					</div>
				</div>
			</article>
		</div>
		<div class="submit">
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
				tablist : null ,
				selecteds : {} ,
				arrlist : [],
				permissions : null,
			}
		},
		mounted() {
			this.getUserPermission();
			this._setColumnToTree(this.list);
		},
		methods:{
			submit(){
				this._pushSeleced();
				this.ajax({
					url : "/permission/createPermission",
					type : "post",
					data : {
						permissions : JSON.stringify(this.arrlist)
					}
				}).then(data=>{
					debugger;
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

					}
				});
			},
			powerEvent( selected , item , parentData ){//记录选择的权限
				let obj = {
					userId : this.item.userId,
					permissionId : parentData.id,
					read : false,
					update : false,
					write : false,
					delete : false,
				};
				obj[item.key] = selected;
				if(this.selecteds[parentData.id]){
					Object.assign(this.selecteds[parentData.id],obj);
				}else{
					this.selecteds[parentData.id] = obj;
				}
			},
			_pushSeleced( item ){
				let arr = [] ,
					newarr = [];
				for(let key in this.selecteds){
					arr.push(this.selecteds[key]);
				}
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