package com.cn.cms.middleware;
import com.cn.cms.bo.XiaoshuoBean;
import com.cn.cms.enums.ESSearchTypeEnum;
import com.cn.cms.logfactory.CommonLog;
import com.cn.cms.logfactory.CommonLogFactory;
import com.cn.cms.middleware.bo.XiaoshuoAboutSearch;
import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import freemarker.template.utility.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.http.Header;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public class RestSearchClient {

    private CommonLog log = CommonLogFactory.getLog(this.getClass());

    private RestHighLevelClient client = null;

    private Calendar calendar = Calendar.getInstance();

    public void searchXiaoshuo(){

    }

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
    public Date convertLongAndDate(Object obj){
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        if(obj!=null){
            String str = obj.toString().replace("Z", " UTC");
            Date date =  formatter.parse(str,new ParsePosition(0));
            return date;
        }
        return null;
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
                        .field("id",chapter.getId())
                        .field("parentId",chapter.getParentId())
                        .field("title",chapter.getTitle())
                        .field("num",chapter.getNum())
                        .field("createTime",chapter.getCreateTime())
                        .field("createTimeStr",chapter.getCreateTimeStr())
                        .field("delTag",chapter.getDelTag())
                        .field("updateTime",chapter.getUpdateTime())
                        .field("updateTimeStr",chapter.getUpdateTimeStr())
                    .endObject();

            IndexRequest indexRequest = new IndexRequest(ESSearchTypeEnum.xiaoshuoChapter.getIndex(), ESSearchTypeEnum.xiaoshuoChapter.getName(), String.valueOf(chapter.getId()))
                    .source(builder);

            IndexResponse indexResponse = client.index(indexRequest);
        }catch (IOException e){
            log.error(e);
        }
    }

    /**
     * 搜索about
     * @param about
     * @return
     */

    public List<XiaoshuoAbout> searchXiaoshuoAbout(XiaoshuoAboutSearch about , Page page){
        SearchRequest searchRequest = new SearchRequest(ESSearchTypeEnum.xiaoshuoAbout.getIndex());
        searchRequest.types(ESSearchTypeEnum.xiaoshuoAbout.getName());

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        if(StringUtils.isNotEmpty(about.getTitle())){
            sourceBuilder.query(QueryBuilders.termQuery("title", about.getTitle()));
        }
        sourceBuilder.from(page.getStart());
        sourceBuilder.size(page.getPageSize());
        /*sourceBuilder.timeout(new TimeValue(60,TimeUnit.SECONDS));*/
        searchRequest.source(sourceBuilder);

        try{
            SearchResponse searchResponse = this.client.search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            List<XiaoshuoAbout> list = new ArrayList<>();
            for(SearchHit hit : hits){
                XiaoshuoAbout about1 = new XiaoshuoAbout();
                about1.setId(Long.parseLong(hit.getId()));
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                about1.setParentId(convertLong(sourceAsMap.get("parentId")));
                about1.setTitle((String) sourceAsMap.get("title"));
                about1.setAuthor((String) sourceAsMap.get("author"));
                about1.setCount(convertLong(sourceAsMap.get("count")));
                about1.setDelTag((Integer) sourceAsMap.get("delTag"));
                about1.setUpdateTime(convertLongAndDate(sourceAsMap.get("updateTime")));
                about1.setStatus((Integer) sourceAsMap.get("status"));
                about1.setDec((String) sourceAsMap.get("dec"));

                /*//转字符串
                String sourceAsString = hit.getSourceAsString();*/

                list.add(about1);
            }
            return list;
        }catch (IOException e){
            log.error(e);
        }
        return null;
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