<template>
    <div v-if="list">
        <select v-model="selectObj" v-if="!disabled">
            <option value="-1" selected>{{placeholder}}</option>
            <option v-for="item in list" :key="item.item.id"
                    v-bind:value="item"
            >
                {{item.item.name}}
            </option>
        </select>
        <select v-model="selectObj" v-else disabled="disabled">
            <option value="-1" selected>{{placeholder}}</option>
            <option v-for="item in list" :key="item.item.id"
                    v-bind:value="item"
            >
                {{item.item.name}}
            </option>
        </select>
    </div>
</template>
<script>
	export default {
		props:{
			list : Array,
			index : String, //第几个select
			select : Number, //显示哪个 option
			placeholder : String,
            disabled : Boolean
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
				if(_obj!=-1){
					this.list.forEach(obj=>{
						if(obj.item.id == _obj.item.id){
							self.selectObj = obj;
						}
					});
				}else{
					self.selectObj = -1;
				}

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
