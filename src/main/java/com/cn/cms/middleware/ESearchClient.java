package com.cn.cms.middleware;

import com.alibaba.fastjson.JSONArray;
import com.cn.cms.contants.StaticContants;
import com.cn.cms.enums.ESSearchTypeEnum;
import com.cn.cms.logfactory.CommonLog;
import com.cn.cms.logfactory.CommonLogFactory;
import com.cn.cms.po.Base;
import com.cn.cms.po.Images;
import com.cn.cms.utils.DateUtils;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by ADMIN on 17/1/5.
 */
@Getter
@Setter
public class ESearchClient {

    private CommonLog log = CommonLogFactory.getLog(this.getClass());

    private TransportClient client;

    private List<String> clusterList;

    private String url;

    private Calendar calendar = Calendar.getInstance();

    private String clusterName;

    private String timeout;


    /**
     * 转换类型
     * @param obj
     * @return
     */
    public Long convertLong(Object obj){
        if(obj!=null){
            return Long.parseLong(obj.toString());
        }
        return 0L;
    }

    /**
     * 转换类型
     * @param obj
     * @return
     */
    public Integer convertInteger(Object obj){
        if(obj!=null){
            return Integer.parseInt(obj.toString());
        }
        return 0;
    }

    /**
     * 转换类型
     * @param obj
     * @return
     */
    public Date convertLongAndDate(Object obj){
        if(obj!=null){
            if(Long.parseLong(obj.toString()) > 0 ) {
                calendar.setTimeInMillis(Long.parseLong(obj.toString()));
                return calendar.getTime();
            }else {
                return null;
            }
        }
        return null;
    }



    /**
     * 统一更新
     * @param base
     * @param esSearchTypeEnum
     * @return
     */
    public boolean update(Base base, ESSearchTypeEnum esSearchTypeEnum){
        boolean bool = false;

        switch (esSearchTypeEnum){

            case images:{
                bool = this.updateImages((Images) base);
                break;
            }
            default:{
                break;
            }
        }
        return bool;
    }

    /**
     * 删除索引
     * @param id
     * @param esSearchTypeEnum
     * @return
     */
    public boolean delete(Long id, ESSearchTypeEnum esSearchTypeEnum){
        boolean bool = false;
        DeleteResponse response = client.prepareDelete(esSearchTypeEnum.getIndex(),
                esSearchTypeEnum.getName(), String.valueOf(id)).get();
        if(response.status() == RestStatus.OK) {
            bool = true;
        }
        return bool;
    }


    /**
     * 更新图片索引
     * @param images
     * @return
     */
    public boolean updateImages(Images images){
        boolean bool = false;
        try {
            XContentBuilder builder = jsonBuilder().startObject();
            builder = builder.field("id", images.getId());
            builder = builder.field("imageUrl", images.getImageUrl());
            builder = builder.field("imageWidthPixel", images.getImageWidthPixel());
            builder = builder.field("imageHeightPixel", images.getImageHeightPixel());
            builder = builder.field("orgWidthPixel", images.getOrgWidthPixel());
            builder = builder.field("orgHeightPixel", images.getOrgHeightPixel());
            builder = builder.field("imageTitle", images.getImageTitle());
            builder = builder.field("uploadUserId", images.getUploadUserId());
            builder = builder.field("uploadTime", DateUtils.convertDateToMillis(images.getUploadTime()));
            builder = builder.field("imagePath", images.getImagePath());
            builder = builder.field("watermark", images.getWatermark());
            builder = builder.field("compress", images.getCompress());
            builder = builder.field("platform", images.getPlatform());
            builder = builder.field("createTime", DateUtils.convertDateToMillis(images.getCreateTime()));
            builder = builder.field("updateTime", DateUtils.convertDateToMillis(images.getUpdateTime()));
            builder = builder.field("delTag", images.getDelTag());
            builder = builder.field("lastModifyUserId", images.getLastModifyUserId());
            builder = builder.field("createUserId", images.getCreateUserId());
            builder = builder.field("keyword", images.getKeyword());
            builder = builder.field("keywordArray", JSONArray.toJSONString(images.getKeyword().split(StaticContants.REGEX_SPLIT)));
            builder = builder.field("imagesClassifyId", images.getImagesClassifyId());
            builder = builder.endObject();
            IndexResponse response = client.prepareIndex(ESSearchTypeEnum.images.getIndex(),
                    ESSearchTypeEnum.images.getName(), String.valueOf(images.getId()))
                    .setSource(builder)
                    .get();
            bool = true;
        } catch (IOException e) {
            log.error(e);
        }
        return bool;
    }



    /**
     * 初始化
     */
    public void open(){
        try {
            Settings settings = Settings.builder()
                    .put("client.transport.sniff", false)
                    .put("cluster.name", clusterName)
                    .put("client.transport.ping_timeout", timeout).build();
            this.client = new PreBuiltTransportClient(settings);
            if(clusterList!=null){
                for(int i=0; i<clusterList.size(); i++){
                    String items = clusterList.get(i);
                    if(StringUtils.isNotBlank(items)){
                        String[] item = items.split(":");
                            this.client = this.client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(item[0]),Integer.parseInt(item[1])));
                    }
                }
            }
        } catch (UnknownHostException e) {
            log.error(e);
        }
    }

    /**
     * 销毁
     */
    public void close(){
        if (this.client == null) {
            return;
        }
        this.client.close();
        this.client = null;
    }

    public static void main(String[] args){
        ESearchClient eSearchClient = new ESearchClient();

    }



}
