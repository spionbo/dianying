<style>
	@import 'list.css';
</style>
<template>
	<article class="form horizontal column-list">
		<div class="title">栏目列表</div>
		<div class="content tablist">
			<div class="table" v-if="tablist">
				<div class="tr">
					<div class="th center w80">排序</div>
					<div class="th center w150">ID</div>
					<div class="th flex left">栏目名称</div>
					<div class="th flex center w100">父类ID</div>
					<div class="th left w300">权限名称</div>
					<div class="th center w200">操作</div>
				</div>
				<div class="tr" v-for="item in tablist" v-bind:key="item.id" :class="item.className">
					<div class="td center w80">{{item.sort||"-"}}</div>
					<div class="td center w150">{{item.id}}</div>
					<div class="td flex left" v-html="item.name"></div>
					<div class="td flex center w100">{{item.parentId||"-"}}</div>
					<div class="td left w300">{{item.powerKey}}</div>
					<div class="td center w200">
						<div class="btn" @click="edit(item)">编辑</div>
						<div class="btn red" @click="del(item,$event)">删除</div>
					</div>
				</div>
			</div>
		</div>
	</article>
</template>
<script>
	import mixin from "../../mixin/_permission";
	import {PERMISSION_COLUMN} from "../../contant/URLS/PERMISSION";
	import { mapGetters } from 'vuex';
	export default {
		mixins : [mixin],
		computed : {
			...mapGetters({
				list : 'allColumn'
			})
		},
		data(){
			return {
				tablist : null
			}
		},
		mounted(){
			let self = this;
			if(!this.list){
				this.$store.dispatch("setAllColumnTree",function(data){
					self.tablist = data;
					self._setColumnToTree(self.tablist);
				});
			}
		},
		methods:{
			edit(item){
				this.ajax({
					url:PERMISSION_COLUMN,
					type : "get",
					load : true,
					data : {
						columnId : item.id
					}
				}).then(data=>{
					require.ensure([],(require)=> {
						this.$requirePop(require('./edit'), {
								props : {
									data : data.data,
									columnInfo : item
								}
							},
							{
								props: {
									obj: {
										title: "栏目管理",
										close: true,
									}
								}
							});
					});
				});

			},
			del(item,$event){
				this._del({
					item : item,
					$event : $event,
					url : PERMISSION_DELETE,
					name : item.name.replace(/\+\|\-/g,""),
					data : {
						columnId : item.id
					}
				});
			}
		}
	}
</script>
