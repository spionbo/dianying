import {D as Decorator} from './annotation/target';
@Decorator.setVersion("1.0.0")
class Base{

	@D.readonly
	@D.nonenumerable
	static author : "bo.peng";

	@D.readonly
	static emial : "spion@qq.com";

	getItem(key , obj){
		return key in obj?obj[key]:null;
	}
	*generator(){
		yield this.valueOf();
	}
	@D.readonly
	*trueAndfalse(){
		while (true){
			yield true;
			yield false;
		}
	}
	@D.log
	name(){

	}
}