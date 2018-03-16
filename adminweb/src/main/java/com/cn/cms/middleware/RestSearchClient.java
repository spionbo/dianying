package com.cn.cms.middleware;
import com.cn.cms.bo.XiaoshuoBean;
import com.cn.cms.enums.ESSearchTypeEnum;
import com.cn.cms.logfactory.CommonLog;
import com.cn.cms.logfactory.CommonLogFactory;
import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public class RestSearchClient {

    private CommonLog log = CommonLogFactory.getLog(this.getClass());

    private RestHighLevelClient client = null;

    public void searchXiaoshuo(){

    }

    /**
     * 创建xioashuo索引
     */
    private void createXiaoshuoIndex(){
        CreateIndexRequest request = new CreateIndexRequest(ESSearchTypeEnum.xiaoshuoAbout.getName());
        request.settings(Settings.builder()
            .put("index.number_of_shards",1)
            .put("index.number_of_replicas",0)
        );
        request.mapping("tweet",
            "  {\n" +
                    "    \"tweet\": {\n" +
                    "      \"properties\": {\n" +
                    "        \"message\": {\n" +
                    "          \"type\": \"text\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }",
            XContentType.JSON);
        /*request.alias(
                new Alias("twitter_alias")
        );*/
        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        };

        this.client.indices().createAsync(request,listener);

    }

    /**
     * 更新与创建xiaoshuo
     * @param about
     */
    public void updateXiaoshuoAbout(XiaoshuoAbout about){
        try {
            XContentBuilder builder = JsonXContent.contentBuilder()
                    .startObject()
                        .startObject("xiaoshuoAbout")
                            .field("id",about.getId())
                            .field("parentId",about.getParentId())
                            .field("author",about.getAuthor())
                            .field("count",about.getCount())
                            .field("createTime",about.getCreateTime())
                            .field("createTimeStr",about.getCreateTimeStr())
                            .field("dec",about.getDec())
                            .field("delTag",about.getDelTag())
                            .field("sort",about.getSort())
                            .field("status",about.getStatus())
                            .field("title",about.getTitle())
                            .field("updateTime",about.getUpdateTime())
                            .field("updateTimeStr",about.getUpdateTimeStr())
                        .endObject()
                    .endObject();

            IndexRequest indexRequest = new IndexRequest(ESSearchTypeEnum.xiaoshuoAbout.getIndex(), ESSearchTypeEnum.xiaoshuoAbout.getName(), String.valueOf(about.getId()))
                    .source(builder);

            IndexResponse indexResponse = client.index(indexRequest);
        }catch (IOException e){
            log.error(e);
        }
    }

    /**
     * 更新列表
     * @param chapter
     */
    public void updateXiaoshuoChapter(XiaoshuoChapter chapter){
        try {
            XContentBuilder builder = JsonXContent.contentBuilder()
                    .startObject()
                        .startObject("xiaoshuoChapter")
                            .field("id",chapter.getId())
                            .field("parentId",chapter.getParentId())
                            .field("title",chapter.getTitle())
                            .field("num",chapter.getNum())
                            .field("createTime",chapter.getCreateTime())
                            .field("createTimeStr",chapter.getCreateTimeStr())
                            .field("delTag",chapter.getDelTag())
                            .field("updateTime",chapter.getUpdateTime())
                            .field("updateTimeStr",chapter.getUpdateTimeStr())
                        .endObject()
                    .endObject();

            IndexRequest indexRequest = new IndexRequest(ESSearchTypeEnum.xiaoshuoChapter.getIndex(), ESSearchTypeEnum.xiaoshuoChapter.getName(), String.valueOf(chapter.getId()))
                    .source(builder);

            IndexResponse indexResponse = client.index(indexRequest);
        }catch (IOException e){
            log.error(e);
        }
    }

    /**
     * 初始化
     */
    public void open(){
        try{
            this.client = new RestHighLevelClient(RestClient.builder(
                    new HttpHost("localhost", 9200, "http")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 销毁
     */
    public void close(){
        if (this.client == null) {
            return;
        }
        try{
            this.client.close();
            this.client = null;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}