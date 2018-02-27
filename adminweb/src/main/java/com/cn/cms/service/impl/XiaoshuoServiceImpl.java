package com.cn.cms.service.impl;

import com.cn.cms.biz.XiaoshuoBiz;
import com.cn.cms.service.XiaoshuoService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/1/10 0010.
 */
@Repository
public class XiaoshuoServiceImpl implements XiaoshuoService {

    @Resource
    private XiaoshuoBiz xiaoshuoBiz;

    public void addClassify(String name){
        xiaoshuoBiz.addClassify(name);
    };
}
