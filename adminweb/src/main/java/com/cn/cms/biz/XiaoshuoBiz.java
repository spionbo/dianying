package com.cn.cms.biz;

import com.cn.cms.dao.XiaoshuoDao;
import com.cn.cms.po.XiaoshuoAbout;
import com.cn.cms.po.XiaoshuoAuthor;
import com.cn.cms.po.XiaoshuoChapter;
import com.cn.cms.po.XiaoshuoClassification;
import com.cn.cms.service.XiaoshuoService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
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

    /**
     * 获取所有章节
     * @param page
     * @return
     */
    public List<XiaoshuoChapter> getAllChapterList(Page page){
        return xiaoshuoService.getAllChapterList(page);
    }

    /**
     * 新增小说作者
     * @param name
     * @param alas
     * @param birh
     * @param addess
     * @param dateOfBirth
     * @param description
     */
    public void addAuthor(String name,String alas,String birh,String addess,String dateOfBirth,String description){
        XiaoshuoAuthor author = new XiaoshuoAuthor();
        author.setName(name);
        if(StringUtils.isNotEmpty(alas)){
            author.setAlas(alas);
        }
        if(StringUtils.isNotEmpty(birh)){
            author.setBirh(birh);
        }
        if(StringUtils.isNotEmpty(addess)){
            author.setAddess(addess);
        }
        if(StringUtils.isNotEmpty(dateOfBirth)){
            author.setDateOfBirth(dateOfBirth);
        }
        if(StringUtils.isNotEmpty(description)){
            author.setDescription(description);
        }
        xiaoshuoService.addAuthor(author);
    }

    /**
     * 修改作者信息
     * @param name
     * @param alas
     * @param birh
     * @param addess
     * @param dateOfBirth
     * @param description
     */
    public void updateAuthor(String name,String alas,String birh,String addess,String dateOfBirth,String description){
        XiaoshuoAuthor author = new XiaoshuoAuthor();
        author.setName(name);
        if(StringUtils.isNotEmpty(alas)){
            author.setAlas(alas);
        }
        if(StringUtils.isNotEmpty(birh)){
            author.setBirh(birh);
        }
        if(StringUtils.isNotEmpty(addess)){
            author.setAddess(addess);
        }
        if(StringUtils.isNotEmpty(dateOfBirth)){
            author.setDateOfBirth(dateOfBirth);
        }
        if(StringUtils.isNotEmpty(description)){
            author.setDescription(description);
        }
        xiaoshuoService.updateAuthor(author);
    }

    /**
     * 删除作者
     * @param id
     */
    public void deleteAuthor(long id){
        xiaoshuoService.deleteAuthor(id);
    }

    /**
     * 获取小说作者列表
     * @param page
     * @return
     */
    public List<XiaoshuoAuthor> getAuthorList(Page page){
        return xiaoshuoService.getAuthorList(page);
    }
}
