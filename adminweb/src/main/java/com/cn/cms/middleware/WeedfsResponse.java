package com.cn.cms.middleware;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Administrator on 2017/12/21 0021.
 */
@Getter
@Setter
@ToString
public class WeedfsResponse {

    private boolean status;

    private int size;

    private String fid;

    private String fileUrl;


}
