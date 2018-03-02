package com.cn.cms.biz;

import com.cn.cms.dao.XiaoshuoDao;
import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.po.XiaoshuoClassification;
import com.cn.cms.service.XiaoshuoService;
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
    private XiaoshuoService xiaoshuoService;

    public void addClassify(String name){
        xiaoshuoService.addClassify(name);
    }

    /**
     * 分类列表
     * @param page
     * @return
     */
    public List<XiaoshuoClassification> queryClassifyList(Page page){
        Integer count = xiaoshuoService.getClassifyCount();
        page.setCount(count);
        if(page.isQuery()){
            List<XiaoshuoClassification> XiaoshuoClassification = xiaoshuoService.getClassifyList(page);
            return XiaoshuoClassification;
        }
        return null;
    }

    /**
     * 更新分类信息
     * @param classify
     */
    public void updateClassify(XiaoshuoClassification classify){
        xiaoshuoService.updateClassify(classify);
    }

    /**
     * 获取小说列表
     * @param page
     * @return
     */
    public List<XiaoshuoAbout> getList( Page page ){
        Integer count = xiaoshuoService.getListCount();
        page.setCount(count);
        if(page.isQuery()){
            return xiaoshuoService.getXiaoshuoList(page);
        }
        return null;
    }

    /**
     * 小说章节列表
     * @param page
     * @return
     */
    public List<XiaoshuoChapter> getChapterList( Page page , Long id ){
        Integer count = xiaoshuoService.getChapterCount(id);
        page.setCount(count);
        if(page.isQuery()){
            return xiaoshuoService.getXiaoshuoChapterList(page , id);
        }
        return null;
    }
}
