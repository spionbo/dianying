/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-02-02 11:09:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xiaoshuo_article
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshuo_article`;
CREATE TABLE `xiaoshuo_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `content` text NOT NULL COMMENT '内容',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '小说简介id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_tag` int(2) DEFAULT '1' COMMENT '逻辑删除',
  `last_modify_user_id` bigint(20) DEFAULT NULL COMMENT '最后修改人ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `last_modify_user_name` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`int`),
  KEY `parentid` (`parent_id`),
  CONSTRAINT `parentid` FOREIGN KEY (`parent_id`) REFERENCES `xiaoshuo_chapter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoshuo_article
-- ----------------------------
