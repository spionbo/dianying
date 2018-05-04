import Jquery from "../../static/plug/jquery-1.10.2.min";
window.$ = Jquery;
let T = {
	body : $('body'),
	info : {}, //用户信息
	init : function( obj ){
		$.extend( T , obj );
	},
	setItem : function( key , value , obj ){
		localStorage.setItem(key,JSON.stringify(value));
	},
	getItem : function( name ){
		return JSON.parse(localStorage.getItem(name));
	},
    setImgSize() {
        T.update();
        let originWidth = T.Mobile?375:750,
            ratio = T.width / originWidth;
        $('.img-size').each(function () {
            let self = $(this);
            $.each(['height', 'width', 'fontSize', 'left',
                'right', 'bottom', 'top', 'paddingTop',
                'margin',
                'padding',
                'borderWidth',
                'lineHeight', 'paddingLeft', 'paddingRight',
                'paddingBottom', 'marginTop', 'marginLeft',
                'marginRight', 'marginBottom'], function (i, str) {
                let num = self.attr('data-' + str);
                if (num) {
                    num = num * ratio / 2 / 16 + 'rem';
                    self.css(str, num)
                }
            });
        });
    },
}
export default T;
