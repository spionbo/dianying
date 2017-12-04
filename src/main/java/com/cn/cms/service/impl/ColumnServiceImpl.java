package com.cn.cms.service.impl;

import com.cn.cms.dao.ColumnDao;
import com.cn.cms.dao.PermissionDao;
import com.cn.cms.po.Column;
import com.cn.cms.po.Permission;
import com.cn.cms.po.SecondColumn;
import com.cn.cms.service.ColumnService;
import com.cn.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 /**
 * Book服务层接口实现类
 * @Service("mainService")用于将当前类注释为一个Spring的bean，名为mainService
 *@Transactional(propagation=Propagation.REQUIRED)
 * //如果有事务，那么加入事务，没有的话新创建一个
 * @Transactional(propagation=Propagation.NOT_SUPPORTED)
 * //这个方法不开启事务
 * @Transactional(propagation=Propagation.REQUIREDS_NEW)
 * //不管是否存在事务，都创建一个新的事务，原来的挂起，新的执行完毕，继续执行老的事务
 * @Transactional(propagation=Propagation.MANDATORY)/
 * /必须在一个已有的事务中执行，否则抛出异常  @
 * Transactional(propagation=Propagation.NEVER)
 * //不能在一个事务中执行，就是当前必须没有事务，否则抛出异常
 * @Transactional(propagation=Propagation.SUPPORTS)
 * //其他bean调用这个方法，如果在其他bean中声明了事务，就是用事务。没有声明，就不用事务。
 * @Transactional(propagation=Propagation.NESTED)
 * //如果一个活动的事务存在，则运行在一个嵌套的事务中，如果没有活动的事务，
 * 则按照REQUIRED属性执行，它使用一个单独的事
 * 务。这个书屋拥有多个回滚的保存点，内部事务的回滚不会对外部事务造成影响，
 * 它只对DataSource TransactionManager事务管理器起效。
 * @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
 * //只读，不能更新，删除
 * @Transactional(propagation=Propagation.REQUIRED,timeout=30)
 * //超时30秒
 * @Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
 * //数据库这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，
 * 通常这值就是TransactionDefinition.ISOLATION_READ_COMMITTED。
 * */
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("columnService")
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnDao columnDao;

    @Autowired
    private PermissionDao permissionDao;

    @Transactional(readOnly=true)
    public List<Column> getColumn (){
        return columnDao.selectColumn();
    }

    @Transactional(readOnly = true)
    public List<SecondColumn> getSecondColumn(Integer id){
        return columnDao.selectSecondColumnById(id);
    }

    public Integer queryColumnCount(String userId, Integer publish, Integer delTag){
        return permissionDao.queryColumnCount(userId,publish,delTag);
    }

    public List<Permission> queryColumnList(String userId, Integer publish, Integer delTag, Page page){
        return permissionDao.queryColumnList(userId,publish,delTag,page);
    };
}
