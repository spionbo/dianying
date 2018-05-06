/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : tt528

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 05/06/2018 11:22:37 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `fhvkxetpgjkm`
-- ----------------------------
DROP TABLE IF EXISTS `fhvkxetpgjkm`;
CREATE TABLE `fhvkxetpgjkm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fhvkxetpgjkm_ibfk_1` (`user_id`),
  CONSTRAINT `fhvkxetpgjkm_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `images_base`
-- ----------------------------
DROP TABLE IF EXISTS `images_base`;
CREATE TABLE `images_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_url` varchar(255) DEFAULT NULL,
  `base_path` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_tag` int(2) NOT NULL DEFAULT '1',
  `last_modify_user_id` int(11) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `last_modify_user_name` varchar(18) DEFAULT NULL,
  `create_time_str` varchar(255) DEFAULT NULL,
  `update_time_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `novel_column`
-- ----------------------------
DROP TABLE IF EXISTS `novel_column`;
CREATE TABLE `novel_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column_name` varchar(18) DEFAULT NULL,
  `column_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_tag` int(2) NOT NULL DEFAULT '1',
  `last_modify_user_id` int(11) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `last_modify_user_name` varchar(18) DEFAULT NULL,
  `create_time_str` varchar(255) DEFAULT NULL,
  `update_time_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `column_id` (`column_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(18) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_tag` int(2) NOT NULL DEFAULT '1',
  `last_modify_user_id` int(11) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `last_modify_user_name` varchar(18) DEFAULT NULL,
  `create_time_str` varchar(255) DEFAULT NULL,
  `update_time_str` varchar(255) DEFAULT NULL,
  `sort` int(9) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `permission_user_power`
-- ----------------------------
DROP TABLE IF EXISTS `permission_user_power`;
CREATE TABLE `permission_user_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_tag` int(2) NOT NULL DEFAULT '1',
  `last_modify_user_id` varchar(255) DEFAULT NULL,
  `create_user_id` varchar(255) DEFAULT NULL,
  `last_modify_user_name` varchar(18) DEFAULT NULL,
  `create_time_str` varchar(255) DEFAULT NULL,
  `update_time_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `permission_user_power_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4644 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `head_image` varchar(255) DEFAULT NULL,
  `real_name` varchar(10) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `idfa` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_tag` int(2) NOT NULL DEFAULT '1',
  `last_modify_user_id` varchar(255) DEFAULT NULL,
  `create_user_id` varchar(255) DEFAULT NULL,
  `last_modify_user_name` varchar(18) DEFAULT NULL,
  `create_time_str` varchar(255) DEFAULT NULL,
  `update_time_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `xiaoshuo_about`
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

-- ----------------------------
--  Table structure for `xiaoshuo_article`
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
  PRIMARY KEY (`id`),
  KEY `parentid` (`parent_id`),
  CONSTRAINT `parentid` FOREIGN KEY (`parent_id`) REFERENCES `xiaoshuo_chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53524 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `xiaoshuo_chapter`
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
) ENGINE=InnoDB AUTO_INCREMENT=64025 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `xiaoshuo_classify`
-- ----------------------------
DROP TABLE IF EXISTS `xiaoshuo_classify`;
CREATE TABLE `xiaoshuo_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `classify_name` varchar(20) DEFAULT NULL COMMENT '栏目分类名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_tag` int(2) NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  `last_modify_user_id` bigint(20) DEFAULT NULL COMMENT '最后修改人ID',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人名称',
  `last_modify_user_name` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
