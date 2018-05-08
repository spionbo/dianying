package com.cn.cms.po;

import lombok.Getter;
import lombok.Setter;

/**
 * 小说作者
 * Created by Administrator on 2018/5/8.
 */
@Getter
@Setter
public class XiaoshuoAuthor extends Base {
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 别名
	 */
	private String alas;
	
	/**
	 * 出生地
	 */
	private String birh;
	
	/**
	 * 地址
	 */
	private String addess;
	/**
	 * 出生日期
	 */
	private String dateOfBirth;
	/**
	 * 作者说明
	 */
	private String description;
	
}
