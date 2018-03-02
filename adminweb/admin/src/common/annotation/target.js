export default {
	setVersion(val){
		return function(target){
			target.version = val;
		}

	},
	readonly(target, name, descriptor){
		descriptor.writable = false;
		return descriptor;
	},
	nonenumerable(target, name, descriptor) {
		descriptor.enumerable = false;
		return descriptor;
	},
	log(target, name, descriptor) {
		let oldValue = descriptor.value;
		descriptor.value = function() {
			console.log(`Calling "${name}" with`, arguments);
			return oldValue.apply(null, arguments);
		};
		return descriptor;
	},
	deprecate(topic){
		/*console.log(topic);
		console.log(`
			{
				props:{
					obj : {
						
					}
				},
				slot:"content"
			},
			{
				props:{
					obj : {
						title : "标题",
						close : true,
					}
				}
			}
		`);*/
		return function(target, name, descriptor) {
			const fn = descriptor.value;
			descriptor.value = function() {
				return fn.apply(this, arguments);
			};
		};
	}
}