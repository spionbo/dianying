package com.cn.cms.service.impl;

import com.cn.cms.dao.ImagesBaseDao;
import com.cn.cms.po.ImagesBase;
import com.cn.cms.service.ResourceService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Repository
public class ResourceServiceImpl implements ResourceService {
    @Resource
    private ImagesBaseDao imagesBaseDao;

    public ImagesBase findImagesBase() {
        return imagesBaseDao.findImagesBase();
    }
}
