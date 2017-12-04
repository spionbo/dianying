const http = '';
export default {
    userName(val){//用户名/，5到16位（字母，数字，下划线，减号）
        let reg = /^[a-zA-Z0-9_-]{5,16}$/;
        if (val == '') {
            return "用户名不能为空";
        } else if (reg.test(val) == false) { //手机号校验提示
            return "5-16位(字母,数字,下划线,减号)";
        }
        return true;
    },
    mobile(val){//手机
        let reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
        if (val == '') {
            return "手机号不能为空";
        } else if (reg.test(val) == false) { //手机号校验提示
            return "请输入正确格式的手机号";
        }
        return true;
    },
    pwd(val){
        let reg = /^(\w){6,20}$/;
        if(val==''){
            return '请输入密码';
        }else if(val.length<6){
            return '注册密码不能少于6位';
        }else if(reg.test(val)==false){
            return '注册密码为6-20位数字、字母或两者组合';
        }
        return true;
    },
    imgCode(val , num){//图形验证码  num 为长度
        num = num || 4;
        let reg = new RegExp("^[0-9A-Za-z]{"+num+"}$");
        if (val == '') {
            return "验证码不能为空";
        } else if (val.length < num) {
            return "请输入正确的验证码";
        } else if (reg.test(val) == false) {
            return "图片验证码格式错误";
        }
        return true;
    },
    varMobile( val ){ //手机号是否被注册
        return new Promise((resolve, reject) => {
            T.ajax({
                url: http + '/existMobilePhone',
                data: {
                    mobilePhone: val
                },
                success : function( data) {
                    if (data.success) {
                        if (data.map.exists) {
                            resolve({status:false, msg:"此号码已经注册"});
                        } else {
                            resolve({status:true});
                        }
                    } else {
                        resolve({status:false, msg:"系统错误" + data.errorCode});
                    }
                }
            })
        });
    },
    sendMsg( mobile , imgCode , callback ){ //发送短信
        let self = this;
        T.ajax({
            url : http+'/sendRegMsg' ,
            data : {
                mobilePhone : mobile,
                picCode : imgCode,
                type : 1
            },
            success:function(data){
                if(data.success){
                    self.reSendYzm(callback);
                }else{
                    if(data.errorCode=='1001'){ return callback(false,"图片验证码不正确");}
                    if(data.errorCode=='1002'){ return callback(false,"当天短信发送超过限制");}
                    if(data.errorCode=='1003'){ return callback(false,"短信发送失败");}
                    if(data.errorCode=='8888'){ return callback(false,"频繁操作");}
                    callback(false,"系统错误"+data.errorCode);
                }
            }
        })
    },
    shortMessageTime : 60,
    reSendYzm( callback ){ //倒计时
        Verify.shortMessageTime -= 1;
        if(Verify.shortMessageTime>0){
            callback(true,{
                status : -1,
                time : Verify.shortMessageTime
            });
            setTimeout(()=>{
                Verify.reSendYzm(callback);
            },1000);
        }else{
            Verify.shortMessageTime = 60;
            callback(true,{
                status : 0,
                time : Verify.shortMessageTime
            });
        }
    }
};