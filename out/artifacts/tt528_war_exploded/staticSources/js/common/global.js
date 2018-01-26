(function(window){
window.T = {
    body : $('body'),
    init(){
        T.mobile();
        T.setImgSize();
        window.onresize = T.setImgSize;
    },
    setItem( key , value , obj ){
        localStorage.setItem(key,JSON.stringify(value));
    },
    getItem( name ){
        return JSON.parse(localStorage.getItem(name));
    },
    update(){
        T.width = T.body.width();
        T.height = T.body.height();
    },
    mobile : function(){
        let ua = navigator.userAgent,
            uaLower = ua.toLowerCase(),
            IS_IPAD = ua.match(/iPad/i) != null,
            IS_IPHONE = !IS_IPAD && ((ua.match(/iPhone/i) != null) || (ua.match(/iPod/i) != null)),
            IS_IOS = IS_IPAD || IS_IPHONE,
            IS_ANDROID = !IS_IOS && ua.match(/android/i) != null,
            IS_MOBILE = IS_IOS || IS_ANDROID ;

        T.Iphone = IS_IOS;
        T.Android = IS_ANDROID;
        T.Mobile = IS_MOBILE

        function is_weixin() {
            let ua = uaLower;
            if (ua.match(/micromessenger/i) == "micromessenger") {
                return true;
            } else {
                return false;
            }
        }
        function is_weixin_ios() {
            let ua = uaLower;
            if (ua.match(/micromessenger/i) == "micromessenger" && T.Iphone )
            { return true; } else { return false; }
        };
        function is_weibo() {
            let ua = uaLower;
            if (ua.match(/Weibo/i) == "weibo")
            { return true; } else { return false; }
        };
        function is_qq() {
            let ua = uaLower;
            if (ua.match(/QQ\//i) == "qq/")
            { return true; } else { return false; }
        };
        T.weixin = is_weixin();
        T.ios_weixin = is_weixin_ios();
        T.weibo = is_weibo();
        T.qq = is_qq();
    },
    setImgSize(){
        if(!T.Mobile) return;
        T.update();
        var  originWidth = 375
            ,ratio = T.width/originWidth;

        $('.img-size').each(function(){
            var self = $(this);
            $.each(['height','width','fontSize','left','right','bottom','top' ,'margin','padding', 'paddingTop' ,'lineHeight' , 'paddingLeft' , 'paddingRight' , 'paddingBottom','marginTop', 'marginLeft' , 'marginRight' , 'marginBottom'],function( i , str ){
                var num = self.attr('data-'+str);
                if( num ){
                    num = num*ratio/2/16+'rem';
                    self.css(str,num)
                }
            });
        });
    },
    addScript(src,callback){
        var head = document.head,
            s = document.createElement("script");
        s.src = src;
        s.onload = function() {
            callback();
        };
        head.insertBefore( s, head.firstChild );
    },
    loadding( b ){
        var load = $("#LOAD");
        if(!b){
            $('body').append($('<div id="LOAD"><i></i></div>'));
        }else{
            load.remove();
        }
    },
    ajax( obj , bizType ,_data ){
       
    }
}
})(window);