/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-02-02 11:09:39
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
  `dec` varchar(500) DEFAULT NULL COMMENT '简介',
  `sort` bigint(20) DEFAULT '1' COMMENT '排序，展示列表时，小说优先级',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modify_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `xiaoshuo_classify` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoshuo_about
-- ----------------------------
