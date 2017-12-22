package com.cn.cms.web.controller;

import com.cn.cms.biz.ResourceBiz;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.CompressEnum;
import com.cn.cms.middleware.WeedfsClient;
import com.cn.cms.middleware.WeedfsResponse;
import com.cn.cms.po.ImagesBase;
import com.cn.cms.utils.FileUtil;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.web.ann.CheckAuth;
import com.cn.cms.web.ann.CheckToken;
import com.cn.cms.web.ann.NotSaveBody;
import com.cn.cms.web.result.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Controller
@RequestMapping(value = "/webapi/upload/",produces = "application/json; charset=UTF-8")
@ResponseBody
public class UploadController extends BaseController{

    @Resource
    private ResourceBiz resourceBiz;

    @Resource
    private WeedfsClient weedfsClient;

    @NotSaveBody
    @CheckToken
    @CheckAuth(name = "images:upload")
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    public String upload(HttpServletRequest request,
                         @RequestParam(value = "baseCode") String baseCode,
                         @RequestParam(value = "suffix") String suffix,
                         @RequestParam(value = "watermark", defaultValue = "0") Integer watermark,
                         @RequestParam(value = "width", defaultValue = "0") Integer width,
                         @RequestParam(value = "height", defaultValue = "0") Integer height) throws Exception{
        String[] baseCodes = baseCode.split(",");
        if(baseCodes.length>1){
            baseCode = baseCodes[1];
        }
        byte[] bytes = FileUtil.base64Upload(baseCode);
        ImagesBase imagesBase = resourceBiz.findImagesBase();

        String relativePath = FileUtil.getRelativePath(imagesBase.getBasePath(), suffix);
        String absPath = StringUtils.concatUrl(imagesBase.getBasePath(), relativePath);
        String urlPath = StringUtils.concatUrl(imagesBase.getBaseUrl(), relativePath);
        Map<String, Object> map = FileUtil.compress(bytes, width, height, absPath, watermark);
        if(StaticContants.IMAGESON != 1 ) {
            File file = new File(absPath);
            WeedfsResponse weedfsResponse = weedfsClient.upload(file);
            map.put("imageUrl", weedfsResponse.getFileUrl());
            map.put("imagePath", weedfsResponse.getFid());
            map.put("fid", weedfsResponse.getFid());
            map.put("size", weedfsResponse.getSize());
            file.delete();
        } else {
            map.put("imageUrl", urlPath);
            map.put("imagePath", relativePath);
            map.put("fid", "19,1011212");
            map.put("size", "100");
        }
        map.put("uploadUserId", getCurrentUserId(request));
        map.put("watermark", watermark);
        map.put("compress", (width>0 || height>0)? CompressEnum.compress.getType() : CompressEnum.nocompress.getType());
        return ApiResponse.returnSuccess(map);
    }
}
