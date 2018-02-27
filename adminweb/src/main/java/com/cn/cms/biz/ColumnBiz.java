package com.cn.cms.biz;

import com.cn.cms.bo.ColumnBean;
import com.cn.cms.bo.PermissionBean;
import com.cn.cms.bo.UserBean;
import com.cn.cms.po.Column;
import com.cn.cms.po.Permission;
import com.cn.cms.service.ColumnService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.Permissions;
import java.util.*;

/**
 * Created by Administrator on 2017/12/4 0004.
 */
@Component
public class ColumnBiz extends BaseBiz {

    @Resource
    ColumnService columnService;

    public List<ColumnBean> listColumn(){
        List<Column> columns = columnService.queryColumnList(null, null, null);
        if(StringUtils.isNotEmpty(columns)){
            return getColumn(columns);
        }
        return null;
    }

    private List<ColumnBean> getColumn(List<Column> columns){

        List<ColumnBean> columnBeans = new ArrayList<>();
        List<Column> catchList = new ArrayList<>();

        for(int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column.getParentId() == null) {
                ColumnBean columnBean = new ColumnBean();
                columnBean.setColumn(column);
                columnBeans.add(columnBean);
            } else {
                catchList.add(column);
            }
        }

        List<ColumnBean> result = setColumn(columnBeans, catchList);

        return result;
    }

    private List<ColumnBean> setColumn(List<ColumnBean> columnBeans,List<Column> catchList){

        for(int i=0;i<columnBeans.size();i++){
            ColumnBean parent = columnBeans.get(i);
            Column parentColumn = parent.getColumn();

            List<Column> childList = new ArrayList<>();
            List<ColumnBean> list = new ArrayList<>();

            for(int j=0;j<catchList.size();j++){
                Column child = catchList.get(j);
                if(child.getParentId() == parentColumn.getId()){
                    childList.add(child);
                }
            }
            for(int k=0;k<childList.size();k++){
                ColumnBean columnBean = new ColumnBean();
                columnBean.setColumn(childList.get(k));
                list.add(columnBean);
            }
            parent.setColumnBeans(list);
        }
        return columnBeans;
    }


    public void dataInit( List<PermissionBean> list ){
        if(StringUtils.isNotEmpty(list)) {
            /*List<String> userIds = new ArrayList<>();
            List<Long> channelIds = new ArrayList<>();
            List<Long> columnIds = new ArrayList<>();
            if (StringUtils.isNotEmpty(list)) {
                for (int i = 0; i < list.size(); i++) {
                    if (StringUtils.isNotBlank(list.get(i).getWriteUserId())) {
                        userIds.add(list.get(i).getWriteUserId());
                    }
                    if (StringUtils.isNotBlank(list.get(i).getLastModifyUserId())) {
                        userIds.add(list.get(i).getLastModifyUserId());
                    }
                    if (StringUtils.isNotBlank(list.get(i).getBuildUserId())) {
                        userIds.add(list.get(i).getBuildUserId());
                    }
                    if (list.get(i).getChannelId() != null) {
                        channelIds.add(list.get(i).getChannelId());
                    }
                    if (list.get(i).getColumnId() != null) {
                        columnIds.add(list.get(i).getColumnId());
                    }
                }
                Map<String, UserBean> map = userBiz.getUserBeanMap(userIds);
                Map<Long, Channel> channelMap = channelBiz.getChannelsMap(channelIds);
                Map<Long, NewsColumn> newsColumnMap = this.getNewsColumnMap(columnIds);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setWriteUserName(map.get(list.get(i).getWriteUserId()) != null ?
                            map.get(list.get(i).getWriteUserId()).getRealName() : "");
                    list.get(i).setLastModifyUserName(map.get(list.get(i).getLastModifyUserId()) != null ?
                            map.get(list.get(i).getLastModifyUserId()).getRealName() : "");
                    list.get(i).setBuildUserName(map.get(list.get(i).getBuildUserId()) != null ?
                            map.get(list.get(i).getBuildUserId()).getRealName() : "");
                    list.get(i).setChannelName(channelMap.get(list.get(i).getChannelId()) != null ?
                            channelMap.get(list.get(i).getChannelId()).getChannelName() : "");
                    list.get(i).setColumnName(newsColumnMap.get(list.get(i).getColumnId()) != null ?
                            newsColumnMap.get(list.get(i).getColumnId()).getColumnName() : "");
                }
            }*/
        }
    }
}
