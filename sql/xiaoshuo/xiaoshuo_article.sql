/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-01-31 17:20:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xiaoshuo_article
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshuo_article`;
CREATE TABLE `xiaoshuo_article` (
  `int` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) NOT NULL COMMENT '文章名称',
  `content` text NOT NULL COMMENT '内容',
  `sort` bigint(20) NOT NULL COMMENT '小说排序，按章节排序',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '小说简介id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_tag` int(2) DEFAULT '1' COMMENT '逻辑删除',
  `last_modify_user_id` bigint(20) DEFAULT NULL COMMENT '最后修改人ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`int`),
  KEY `parentid` (`parent_id`),
  CONSTRAINT `parentid` FOREIGN KEY (`parent_id`) REFERENCES `xiaoshuo_about` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoshuo_article
-- ----------------------------
