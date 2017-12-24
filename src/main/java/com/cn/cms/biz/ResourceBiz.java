package com.cn.cms.biz;

import com.cn.cms.exception.BizException;
import com.cn.cms.middleware.JedisClient;
import com.cn.cms.middleware.MSSVideoClient;
import com.cn.cms.po.Images;
import com.cn.cms.po.ImagesBase;
import com.cn.cms.po.ImagesClassify;
import com.cn.cms.service.ResourceService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Component
public class ResourceBiz {
    @Resource
    private ResourceService resourceService;

    @Resource
    private JedisClient jedisClient;

    @Resource
    private MSSVideoClient mssVideoClient;

    @Resource(name = "threadTaskExecutor")
    private ThreadPoolTaskExecutor threadTaskExecutor;


    /**
     * 获取ImagesBase信息。
     * @return
     */
    public ImagesBase findImagesBase(){
        return resourceService.findImagesBase();
    }

    /**
     * 保存ImagesBase
     * @param imagesBase
     */
    public void saveImagesBase(ImagesBase imagesBase){
        if(imagesBase.getId()!=null && imagesBase.getId()>0){
            resourceService.updateImagesBase(imagesBase);
        }else{
            resourceService.saveImagesBase(imagesBase);
        }
    }

    /**
     * 保存Images
     * @param images
     */
    public void saveImages(Images images){
        if(images.getId()!=null && images.getId()>0){
            resourceService.updateImages(images);
            sendIndex(images, ESSearchTypeEnum.images);
        }else{
            resourceService.saveImages(images);
            sendIndex(images, ESSearchTypeEnum.images);
        }
    }

    /**
     * 删除图片,但是不删除图片实体。
     * @param lastModifyUserId
     * @param id
     */
    public void delImages(String lastModifyUserId, Long id){
        resourceService.delImages(lastModifyUserId, id);
        delIndex(id, ESSearchTypeEnum.images);
    }


    /**
     * 根据page获取
     * @param page
     * @return
     */
    public List<Images> listImages(Page page){
        Integer count = resourceService.queryImagesCount();
        page.setCount(count);
        if(page.isQuery()){
            List<Images> images = resourceService.queryImagesList(page);
            return images;
        }
        return null;
    }

    public List<ImagesClassify> listImagesClassify(Page page){
        Integer count = resourceService.queryImagesClassifyCount();
        page.setCount(count);
        if(page.isQuery()){
            List<ImagesClassify> imagesClassifyList = resourceService.queryImagesClassifyList(page);
            return imagesClassifyList;
        }
        return null;
    }

    public List<ImagesClassify> findAllImagesClassify(){
        return resourceService.findAllImagesClassify();
    }

    public ImagesClassify getImagesClassify(Long id){
        return resourceService.getImagesClassify(id);
    }

    /**
     * 修改图片分类
     * @param classify
     * @throws BizException
     */
    public void saveImagesClassify(ImagesClassify classify) throws BizException{
        if(classify.getId() !=null && classify.getId() > 0){

            resourceService.updateImagesClassify(classify);
        } else {
            resourceService.saveImagesClassify(classify);
        }
    }


    public void delImagesClassify(String lastModifyUserId, Long id){
        resourceService.delImagesClassify(lastModifyUserId, id);
    }

    public void dataInitImages(List<Images> list){
        if(StringUtils.isNotEmpty(list)) {
            List<Long> imagesClassifyIds = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Images images = list.get(i);
                if(images.getImagesClassifyId()!=null){
                    imagesClassifyIds.add(images.getImagesClassifyId());
                }
            }
            if (imagesClassifyIds.size() > 0) {
                Map<Integer, ImagesClassify> map = getImagesClassifyMap(imagesClassifyIds);
                for (int i = 0; i < list.size(); i++) {
                    Images images = list.get(i);
                    images.setImagesClassifyName(map.get(images.getImagesClassifyId())!=null?map.get(images.getImagesClassifyId()).getClassifyName():"");
                }
            }
        }
    }

    public Images getImages(Long id){
        return resourceService.findImages(id);
    }

    public Images getImagesManage(Long id){
        return resourceService.findImagesManage(id);
    }

    public Images doGetImagesManage(Long id){
        return resourceService.doFindImagesManage(id);
    }

    public Map<Integer, ImagesClassify> getImagesClassifyMap(List<Long> ids){
        List<ImagesClassify> list = resourceService.getImagesClassifyList(ids);
        Map<Integer, ImagesClassify> map = new HashMap<>();
        if(list!=null && list.size()>0){
            for(int i = 0;i<list.size(); i++){
                ImagesClassify imagesClassify = list.get(i);
                map.put(imagesClassify.getId(), imagesClassify);
            }
        }
        return map;
    }
}
