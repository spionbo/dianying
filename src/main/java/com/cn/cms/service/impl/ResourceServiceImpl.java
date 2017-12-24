package com.cn.cms.service.impl;

import com.cn.cms.dao.ImagesBaseDao;
import com.cn.cms.dao.ImagesClassifyDao;
import com.cn.cms.po.ImagesBase;
import com.cn.cms.po.ImagesClassify;
import com.cn.cms.service.ResourceService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Repository
public class ResourceServiceImpl implements ResourceService {
    @Resource
    private ImagesBaseDao imagesBaseDao;

    @Resource
    private ImagesClassifyDao imagesClassifyDao;

    public ImagesBase findImagesBase() {
        return imagesBaseDao.findImagesBase();
    }

    @Override
    public void saveImagesClassify(ImagesClassify classify) {
        imagesClassifyDao.saveImagesClassify(classify);
    }

    @Override
    public void updateImagesClassify(ImagesClassify classify) {
        imagesClassifyDao.updateImagesClassify(classify);
    }

    @Override
    public void delImagesClassify(String lastModifyUserId, Long id) {
        imagesClassifyDao.delImagesClassify(lastModifyUserId, id);
    }

    @Override
    public List<ImagesClassify> getImagesClassifyList(List<Long> ids) {
        return imagesClassifyDao.getImagesClassifyList(ids);
    }
}
