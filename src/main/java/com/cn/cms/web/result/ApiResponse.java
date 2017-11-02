package com.cn.cms.web.result;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/10/19 0019.
 */
@Getter
@Setter
public class ApiResponse {

    private Integer code;

    private String message;

    private Object data;

    public ApiResponse(Integer code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static String returnSuccess(){
        ApiResponse apiResponse = new ApiResponse(ErrorCodeEnum.SUCCESS_CODE_DETAIL.getType(),ErrorCodeEnum.SUCCESS_CODE_DETAIL.getMessage(),null);
        return JSONObject.toJSONString(apiResponse);
    }

    public static String returnSuccess(Object data){
        ApiResponse apiResponse = new ApiResponse(ErrorCodeEnum.SUCCESS_CODE_DETAIL.getType(),ErrorCodeEnum.SUCCESS_CODE_DETAIL.getMessage(),data);
        return JSONObject.toJSONString(apiResponse);
    }
}
