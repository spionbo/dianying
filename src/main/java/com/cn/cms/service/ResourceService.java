package com.cn.cms.service;

import com.cn.cms.po.ImagesBase;
import com.cn.cms.po.ImagesClassify;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
public interface ResourceService {
    ImagesBase findImagesBase();

    void saveImagesClassify(ImagesClassify classify);

    void updateImagesClassify(ImagesClassify classify);

    void delImagesClassify(String lastModifyUserId, Long id);

    List<ImagesClassify> getImagesClassifyList(List<Long> ids);
}
