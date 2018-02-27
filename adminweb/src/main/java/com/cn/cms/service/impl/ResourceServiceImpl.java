package com.cn.cms.service.impl;

import com.cn.cms.dao.ImagesBaseDao;
import com.cn.cms.dao.ImagesClassifyDao;
import com.cn.cms.dao.ImagesDao;
import com.cn.cms.po.Images;
import com.cn.cms.po.ImagesBase;
import com.cn.cms.po.ImagesClassify;
import com.cn.cms.service.ResourceService;
import com.cn.cms.utils.Page;
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

    @Resource
    private ImagesDao imagesDao;

    public ImagesBase findImagesBase() {
        return imagesBaseDao.findImagesBase();
    }

    public void saveImagesBase(ImagesBase imagesBase) {
        imagesBaseDao.saveImagesBase(imagesBase);
    }

    public void updateImagesBase(ImagesBase imagesBase) {
        imagesBaseDao.updateImagesBase(imagesBase);
    }

    public void saveImages(Images images) {
        imagesDao.saveImages(images);
        //sendIndex(images, ESSearchTypeEnum.images);
    }

    public void updateImages(Images images) {
        imagesDao.updateImages(images);
        //sendIndex(images, ESSearchTypeEnum.images);
    }

    public void delImages(String lastModifyUserId, Integer id) {
        imagesDao.delImages(lastModifyUserId, id);
//        delIndex(id, ESSearchTypeEnum.images);
    }

    @Override
    public Images findImages(Integer id) {
        return imagesDao.findImages(id);
    }

    @Override
    public Images findImagesManage(Integer id) {
        return imagesDao.findImagesManage(id);
    }

    @Override
    public Images doFindImagesManage(Integer id) {
        return imagesDao.findImagesManage(id);
    }

    public Integer queryImagesCount() {
        return imagesDao.queryImagesCount();
    }

    public List<Images> queryImagesList(Page page) {
        return imagesDao.queryImagesList(page);
    }

    @Override
    public Integer queryImagesClassifyCount() {
        return imagesClassifyDao.queryImagesClassifyCount();
    }

    @Override
    public List<ImagesClassify> queryImagesClassifyList(Page page) {
        return imagesClassifyDao.queryImagesClassifyList(page);
    }

    @Override
    public List<ImagesClassify> findAllImagesClassify() {
        return imagesClassifyDao.findAllImagesClassify();
    }

    @Override
    public ImagesClassify getImagesClassify(Integer id) {
        return imagesClassifyDao.getImagesClassify(id);
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
    public void delImagesClassify(String lastModifyUserId, Integer id) {
        imagesClassifyDao.delImagesClassify(lastModifyUserId, id);
    }

    @Override
    public List<ImagesClassify> getImagesClassifyList(List<Integer> ids) {
        return imagesClassifyDao.getImagesClassifyList(ids);
    }

}
