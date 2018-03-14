package com.cn.cms.middleware;
import com.cn.cms.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
public class RestSearchClient {

    private RestHighLevelClient client = null;

    public void searchXiaoshuo(){

    }

    private void createNews(){

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