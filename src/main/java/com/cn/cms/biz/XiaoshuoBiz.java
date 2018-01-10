package com.cn.cms.biz;

import com.cn.cms.dao.XiaoshuoDao;
import com.cn.cms.po.XiaoshuoClassification;
import com.cn.cms.utils.Page;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
@Component
public class XiaoshuoBiz extends BaseBiz {

    @Resource
    private XiaoshuoDao xiaoshuoDao;

    public void saveClassify(String name){
        xiaoshuoDao.saveClassify(name);
    }

    /**
     * 分类列表
     * @param page
     * @return
     */
    public List<XiaoshuoClassification> queryClassifyList(Page page){
        Integer count = xiaoshuoDao.queryClassifyCount();
        page.setCount(count);
        if(page.isQuery()){
            List<XiaoshuoClassification> XiaoshuoClassification = xiaoshuoDao.queryClassifyList(page);
            return XiaoshuoClassification;
        }
        return null;
    }

    public void updateClassify(Integer id , String name){

    }
}
