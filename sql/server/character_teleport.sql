/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 60008
Source Host           : localhost:3306
Source Database       : bone

Target Server Type    : MYSQL
Target Server Version : 60008
File Encoding         : 65001

Date: 2011-10-05 19:40
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_teleport`
-- ----------------------------
DROP TABLE IF EXISTS `character_teleport`;
CREATE TABLE `character_teleport` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `char_id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL DEFAULT '',
  `locx` int(10) unsigned NOT NULL DEFAULT '0',
  `locy` int(10) unsigned NOT NULL DEFAULT '0',
  `mapid` int(10) unsigned NOT NULL DEFAULT '0',
  `randomX` int(10) unsigned NOT NULL DEFAULT '0',
  `randomY` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `key_id` (`char_id`)
) ENGINE=MyISAM AUTO_INCREMENT=315261651 DEFAULT CHARSET=euckr;

-- ----------------------------
-- Records of character_teleport
-- ----------------------------
