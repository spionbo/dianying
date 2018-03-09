package com.cn.cms.middleware;
import com.cn.cms.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHost;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8 0008.
 */
@Getter
@Setter
public class RestSearchClient {

    //private static RestClient restClient = null;

    private List<String> clusterList;
    private String clusterName;
    private String timeout;

    public void searchXiaoshuo(){

    }

    /**
     * 初始化
     */
    public void open(){



        /*try{
            if(clusterList!=null){
                for (int i=0; i<clusterList.size();i++){
                    String items = clusterList.get(i);
                    if(StringUtils.isNotBlank(items)){
                        String[] item = items.split(":");
                        this.restClient = RestClient.builder(
                                new HttpHost(item[0],Integer.parseInt(item[1]),"http")
                        ).build();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    /**
     * 销毁
     */
    public void close(){
        /*if (this.restClient == null) {
            return;
        }
        try{
            this.restClient.close();
            this.restClient = null;
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}