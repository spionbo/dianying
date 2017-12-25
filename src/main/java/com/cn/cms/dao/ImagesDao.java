package com.cn.cms.dao;

import com.cn.cms.po.Images;
import com.cn.cms.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/12/25 0025.
 */
public interface ImagesDao {
    void saveImages(@Param(value = "p1") Images images);

    void updateImages(@Param(value = "p1") Images images);

    Images findImages(@Param(value = "id") Integer id);

    Images findImagesManage(@Param(value = "id") Integer id);

    void delImages(@Param(value = "lastModifyUserId") String lastModifyUserId, @Param(value = "id") Integer id);

    Integer queryImagesCount();

    List<Images> queryImagesList(@Param(value = "page") Page page);
}
