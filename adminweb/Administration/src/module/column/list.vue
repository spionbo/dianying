<style>
    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
    }
</style>
<template>
	<article class="form horizontal column-list">
		<div class="title">栏目列表</div>
		<div class="content">
			<div class="table" v-if="tablist">
				<div class="tr">
					<div class="th center w80">排序</div>
					<div class="th center w150">ID</div>
					<div class="th flex left">栏目名称</div>
					<div class="th flex center w100">父类ID</div>
					<div class="th left w300">权限名称</div>
					<div class="th center w200">操作</div>
				</div>
                <el-tree
                    :data="data4"
                    show-checkbox
                    node-key="id"
                    default-expand-all
                    :expand-on-click-node="false">
                    <span class="custom-tree-node" slot-scope="{ node, data }">
                        <span>{{ node.label }}</span>
                        <span>
                            <el-button
                                type="text"
                                size="mini"
                                @click="() => append(data)">
                                    Append
                            </el-button>
                            <el-button
                                type="text"
                                size="mini"
                                @click="() => remove(node, data)">
                                    Delete
                            </el-button>
                        </span>
                    </span>
                </el-tree>
				<!--<div class="tr" v-for="item in tablist" v-bind:key="item.id" :class="item.className">
					<div class="td center w80">{{item.sort||"-"}}</div>
					<div class="td center w150">{{item.id}}</div>
					<div class="td flex left" v-html="item.name"></div>
					<div class="td flex center w100">{{item.parentId||"-"}}</div>
					<div class="td left w300">{{item.powerKey}}</div>
					<div class="td center w200">
						<div class="btn" @click="edit(item)">编辑</div>
						<div class="btn red" @click="del(item,$event)">删除</div>
					</div>
				</div>-->
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
				list : 'allColumnTree'
			})
		},
		data(){
            const data = [{
                id: 1,
                label: '一级 1',
                children: [{
                    id: 4,
                    label: '二级 1-1',
                    children: [{
                        id: 9,
                        label: '三级 1-1-1'
                    }, {
                        id: 10,
                        label: '三级 1-1-2'
                    }]
                }]
            }, {
                id: 2,
                label: '一级 2',
                children: [{
                    id: 5,
                    label: '二级 2-1'
                }, {
                    id: 6,
                    label: '二级 2-2'
                }]
            }, {
                id: 3,
                label: '一级 3',
                children: [{
                    id: 7,
                    label: '二级 3-1'
                }, {
                    id: 8,
                    label: '二级 3-2'
                }]
            }];
			return {
			    data : null,
                data4: JSON.parse(JSON.stringify(data)),
                tablist: null,
			}
		},
		mounted(){
            this.$store.dispatch("getAllColumnTree");
			/*let self = this;
            this.$store.dispatch("setAllColumnTree",function(data){
                self.tablist = data;
                self._setColumnToTree(self.tablist);
            });*/
		},
		methods:{
            handleClick(row) {
                console.log(row);
            },
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
