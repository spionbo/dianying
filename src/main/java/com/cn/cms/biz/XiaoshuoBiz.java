package com.cn.cms.biz;

import com.cn.cms.dao.XiaoshuoDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
}
