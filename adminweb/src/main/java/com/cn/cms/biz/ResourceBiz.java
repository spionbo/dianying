package com.cn.cms.biz;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.cms.contants.RedisKeyContants;
import com.cn.cms.enums.ESSearchTypeEnum;
import com.cn.cms.enums.IndexOperEnum;
import com.cn.cms.exception.BizException;
import com.cn.cms.job.IndexThread;
import com.cn.cms.middleware.JedisClient;
import com.cn.cms.middleware.MSSVideoClient;
import com.cn.cms.po.*;
import com.cn.cms.service.ResourceService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by ADMIN on 16/11/30.
 */
@Component
public class ResourceBiz {

    @Resource
    private ResourceService resourceService;

    @Resource
    private JedisClient jedisClient;

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
        /*if(images.getId()!=null && images.getId()>0){
            resourceService.updateImages(images);
            sendIndex(images, ESSearchTypeEnum.images);
        }else{
            resourceService.saveImages(images);
            sendIndex(images, ESSearchTypeEnum.images);
        }*/
    }

    /**
     * 删除图片,但是不删除图片实体。
     * @param lastModifyUserId
     * @param id
     */
    public void delImages(String lastModifyUserId, Integer id){
        /*resourceService.delImages(lastModifyUserId, id);
        delIndex(id, ESSearchTypeEnum.images);*/
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

    public ImagesClassify getImagesClassify(Integer id){
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


    public void delImagesClassify(String lastModifyUserId, Integer id){
        resourceService.delImagesClassify(lastModifyUserId, id);
    }

    public void dataInitImages(List<Images> list){
        if(StringUtils.isNotEmpty(list)) {
            List<Integer> imagesClassifyIds = new ArrayList<>();
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

    public Images getImages(Integer id){
        return resourceService.findImages(id);
    }

    public Images getImagesManage(Integer id){
        return resourceService.findImagesManage(id);
    }

    public Images doGetImagesManage(Integer id){
        return resourceService.doFindImagesManage(id);
    }

    /**
     * 获取附件列表
     * @param page
     * @return
     */
    public JSONArray findFileList(Page page){
        Long count = jedisClient.len(RedisKeyContants.REDIS_FILE_LIST);
        if( count > 0 ){
            page.setCount(count.intValue());
        }
        Set<String> set = jedisClient.zrevrange(RedisKeyContants.REDIS_FILE_LIST, page.getStart(), page.getEnd());
        if(set != null){
            Iterator<String> it = set.iterator();
            JSONArray jsonArray = new JSONArray();
            while (it.hasNext()){
                JSONObject jsonObject = JSONObject.parseObject(it.next());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        }
        return null;
    }

    public void setFileInfoToRedis(String url, String originFileName, String fileName){
        long time = System.currentTimeMillis()/1000;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        jsonObject.put("mtime", time);
        jsonObject.put("fileName", fileName);
        jsonObject.put("originFileName", originFileName);
        jedisClient.zadd(RedisKeyContants.REDIS_FILE_LIST, jsonObject.toJSONString(), time);
    }

    public Map<Integer, ImagesClassify> getImagesClassifyMap(List<Integer> ids){
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

    private void sendIndex(Base base, ESSearchTypeEnum esSearchTypeEnum){
        IndexThread indexThread = new IndexThread();
        indexThread.setId(base.getId());
        indexThread.setIndexOperEnum(IndexOperEnum.update);
        indexThread.setEsSearchTypeEnum(esSearchTypeEnum);
        threadTaskExecutor.execute(indexThread);
    }


    private void delIndex(Integer id, ESSearchTypeEnum esSearchTypeEnum){
        IndexThread indexThread = new IndexThread();
        indexThread.setId(id);
        indexThread.setIndexOperEnum(IndexOperEnum.delete);
        indexThread.setEsSearchTypeEnum(esSearchTypeEnum);
        threadTaskExecutor.execute(indexThread);
    }
}