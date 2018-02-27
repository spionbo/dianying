package com.cn.cms.service;

import com.cn.cms.po.Images;
import com.cn.cms.po.ImagesBase;
import com.cn.cms.po.ImagesClassify;
import com.cn.cms.utils.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
public interface ResourceService {

    ImagesBase findImagesBase();

    void saveImagesBase(ImagesBase imagesBase);

    void updateImagesBase(ImagesBase imagesBase);

    void saveImages(Images images);

    void updateImages(Images images);

    void delImages(String lastModifyUserId, Integer id);

    Images findImages(Integer id);

    Images findImagesManage(Integer id);

    Images doFindImagesManage(Integer id);

    Integer queryImagesCount();

    List<Images> queryImagesList(Page page);

    Integer queryImagesClassifyCount();

    List<ImagesClassify> queryImagesClassifyList(Page page);

    List<ImagesClassify> findAllImagesClassify();

    ImagesClassify getImagesClassify(Integer id);

    void saveImagesClassify(ImagesClassify classify);

    void updateImagesClassify(ImagesClassify classify);

    void delImagesClassify(String lastModifyUserId, Integer id);

    List<ImagesClassify> getImagesClassifyList(List<Integer> ids);
}
