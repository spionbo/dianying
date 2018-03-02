/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-02-02 11:09:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xiaoshuo_chapter
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshuo_chapter`;
CREATE TABLE `xiaoshuo_chapter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '逻辑ID',
  `parent_id` bigint(20) NOT NULL COMMENT '约束其他表的ID，指向父类',
  `title` varchar(255) NOT NULL COMMENT '标题 章节名称',
  `num` int(11) NOT NULL COMMENT '第几章',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_tag` int(2) NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  `last_modify_user_id` bigint(20) DEFAULT NULL COMMENT '最后修改人ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `last_modify_user_name` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`),
  KEY `parenid` (`parent_id`),
  CONSTRAINT `parenid` FOREIGN KEY (`parent_id`) REFERENCES `xiaoshuo_about` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xiaoshuo_chapter
-- ----------------------------
