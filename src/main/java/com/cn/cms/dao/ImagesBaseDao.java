package com.cn.cms.dao;

import com.cn.cms.po.ImagesBase;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
public interface ImagesBaseDao {
    ImagesBase findImagesBase();
    void saveImagesBase(@Param(value = "p1") ImagesBase imagesBase);

    void updateImagesBase(@Param(value = "p1") ImagesBase imagesBase);
}
