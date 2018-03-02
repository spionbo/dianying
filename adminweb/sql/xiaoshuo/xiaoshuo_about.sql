/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : tt528

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-03-02 10:25:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xiaoshuo_about
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshuo_about`;
CREATE TABLE `xiaoshuo_about` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) NOT NULL COMMENT '书名，文章名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '约束分类id',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态 0  1  0完结，1正在更新',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `author_id` varchar(20) DEFAULT NULL COMMENT '作者id',
  `dec` varchar(500) DEFAULT NULL COMMENT '简介',
  `sort` bigint(20) DEFAULT '1' COMMENT '排序，展示列表时，小说优先级',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `count` bigint(20) DEFAULT NULL COMMENT '文字总数',
  `del_tag` int(2) DEFAULT '1' COMMENT '逻辑删除',
  `source` varchar(255) DEFAULT NULL COMMENT '来源',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `last_modify_user_name` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `xiaoshuo_classify` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
