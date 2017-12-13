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
	}
}