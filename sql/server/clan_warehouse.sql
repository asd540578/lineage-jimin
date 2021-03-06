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
-- Table structure for `clan_warehouse`
-- ----------------------------
DROP TABLE IF EXISTS `clan_warehouse`;
CREATE TABLE `clan_warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clan_name` varchar(45) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `is_equipped` int(11) DEFAULT NULL,
  `enchantlvl` int(11) DEFAULT NULL,
  `is_id` int(11) DEFAULT NULL,
  `durability` int(11) DEFAULT NULL,
  `charge_count` int(11) DEFAULT NULL,
  `remaining_time` int(11) DEFAULT NULL,
  `last_used` datetime DEFAULT NULL,
  `attr_enchantlvl` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_id` (`clan_name`)
) ENGINE=MyISAM AUTO_INCREMENT=315258898 DEFAULT CHARSET=euckr;

-- ----------------------------
-- Records of clan_warehouse
-- ----------------------------
