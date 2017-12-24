package com.cn.cms.middleware;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.exception.BizException;
import com.cn.cms.logfactory.CommonLog;
import com.cn.cms.logfactory.CommonLogFactory;
import com.cn.cms.middleware.bean.*;
import com.cn.cms.utils.EncryptUtil;
import com.cn.cms.utils.StringUtils;
import com.cn.cms.utils.UrlUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by ADMIN on 17/1/17.
 */
@Getter
@Setter
public class MSSVideoClient {

    private CommonLog log = CommonLogFactory.getLog(MSSVideoClient.class);

    private String uploadUrl = "http://upload.dvr.aodianyun.com/v2/DVR.UploadPart";

    private String queryUrl = "http://upload.dvr.aodianyun.com/v2/DVR.UploadQuery";

    private String finishUrl = "http://upload.dvr.aodianyun.com/v2/DVR.UploadComplete";

    private String listUrl = "http://openapi.aodianyun.com/v2/DVR.GetUploadDvrList";

    private String interruptUrl = "http://upload.dvr.aodianyun.com/v2/DVR.AbortUpload";

    private String deleteUrl = "http://openapi.aodianyun.com/v2/DVR.RemoveUploadDvr";

    private String accessKey = "1p5Z7yUHsCqdLiCJbB8lwDPd3Ffe1q8f";

    private String accessId = "115840986417";


}
