package com.cn.cms.biz;

import com.cn.cms.bo.PermissionBean;
import com.cn.cms.bo.UserBean;
import com.cn.cms.po.Permission;
import com.cn.cms.service.ColumnService;
import com.cn.cms.utils.Page;
import com.cn.cms.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4 0004.
 */
@Component
public class ColumnBiz extends BaseBiz {

    @Resource
    ColumnService columnService;

    @Resource
    PermissionBiz permissionBiz;

    public List<PermissionBean> listColumn(Page page){
        Integer count = columnService.queryColumnCount(null,null,null);
        page.setCount(count);
        if(page.isQuery()) {
            List<Permission> permissions = columnService.queryColumnList(null, null, null, page);
            return permissionBiz.getPermission(permissions);
        }
        return null;
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
