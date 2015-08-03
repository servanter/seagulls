/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60003
Source Host           : localhost:3306
Source Database       : seagulls

Target Server Type    : MYSQL
Target Server Version : 60003
File Encoding         : 65001

Date: 2015-08-02 16:07:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `crop_category`
-- ----------------------------
DROP TABLE IF EXISTS `crop_category`;
CREATE TABLE `crop_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '父ID。最大类别父ID为0，否则为上级ID',
  `zh_name` varchar(50) NOT NULL DEFAULT '' COMMENT '中文名称',
  `en_name` varchar(100) NOT NULL DEFAULT '' COMMENT '汉语拼音',
  `first_letter` varchar(2) NOT NULL DEFAULT '' COMMENT '汉语拼音首字母',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crop_category
-- ----------------------------

-- ----------------------------
-- Table structure for `crop_dict_area`
-- ----------------------------
DROP TABLE IF EXISTS `crop_dict_area`;
CREATE TABLE `crop_dict_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '父ID 省份直辖市父ID为0；没有父ID为-1；否则为上级ID',
  `zh_name` varchar(50) NOT NULL DEFAULT '' COMMENT '中文名称',
  `en_name` varchar(100) NOT NULL DEFAULT '' COMMENT '汉语拼音',
  `first_letter` varchar(2) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of crop_dict_area
-- ----------------------------

-- ----------------------------
-- Table structure for `crop_user`
-- ----------------------------
DROP TABLE IF EXISTS `crop_user`;
CREATE TABLE `crop_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(20) NOT NULL DEFAULT '' COMMENT '昵称',
  `real_name` varchar(20) NOT NULL DEFAULT '' COMMENT '真是姓名',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话号码 登陆时用',
  `password` varchar(20) NOT NULL DEFAULT '' COMMENT '密码',
  `province_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '省份',
  `city_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '城市',
  `area_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '地区',
  `head_url` varchar(200) NOT NULL DEFAULT '' COMMENT '头像',
  `identity_id` int(2) NOT NULL DEFAULT '-1' COMMENT '身份类型',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可用; 1 可用；0不可用',
  `create_time` timestamp NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '创建时间',
  `update_tme` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

