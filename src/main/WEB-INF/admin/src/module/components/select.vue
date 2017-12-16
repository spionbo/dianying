<template>
<select v-model="selectObj" v-if="list">
	<option value="-1" selected>{{placeholder}}</option>
	<option v-for="item in list" :key="item.item.id"
	        v-bind:value="item"
	>
		{{item.item.name}}
	</option>
</select>
</template>
<script>
	export default {
		props:{
			list : Array,
			index : String, //第几个select
			select : Number, //显示哪个 option
			placeholder : String,
		},
		data() {
			return {
				selectObj : -1
			}
		},
		watch:{
			selectObj( obj ){
				this.$emit('$change',obj,this.index);
			},
			select( _obj ){
				let self = this;
				this.list.forEach(obj=>{
					if(obj.item.id == _obj.item.id){
						self.selectObj = obj;
					}
				});
			}
		},
		mounted() {
		},
		methods : {
			clear(){
				this.selectObj = -1;
			}
		}
	}
</script>