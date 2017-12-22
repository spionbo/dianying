package com.cn.cms.biz;

import com.cn.cms.po.ImagesBase;
import com.cn.cms.service.ResourceService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Component
public class ResourceBiz {
    @Resource
    private ResourceService resourceService;

    /**
     * 获取ImagesBase信息。
     * @return
     */
    public ImagesBase findImagesBase(){
        return resourceService.findImagesBase();
    }
}
