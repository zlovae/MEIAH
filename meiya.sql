/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : meiya

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-07-12 17:54:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for my_admin
-- ----------------------------
DROP TABLE IF EXISTS `my_admin`;
CREATE TABLE `my_admin` (
  `id` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `role` int(5) DEFAULT '10' COMMENT '0-超级管理员；管理员要由超级管理员添加',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_phone_unique` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_admin
-- ----------------------------
INSERT INTO `my_admin` VALUES ('00000000000000000003', 'admin ', '13888888888', 'F3DDB2B3C8B9740561B91E269B7CEB13', '12345', '10', null);
INSERT INTO `my_admin` VALUES ('00000000000000000004', null, '13888888887', '86be8b37a3eca7be54f36855eb16b48e', '12345', '10', null);
INSERT INTO `my_admin` VALUES ('00000000000000000007', null, '138888888343', 'F3DDB2B3C8B9740561B91E269B7CEB13', '12345', '10', null);
INSERT INTO `my_admin` VALUES ('00000000000000000009', null, '1234', '58C4FCBDBD189123AB093DF7FB7D86A5', '12345', '10', null);
INSERT INTO `my_admin` VALUES ('00000000000000000010', '用户名', '13606094177', '646F902DE0FC438F261DCB39F02051D5', '12345', '0', '2019-07-10 10:26:28');

-- ----------------------------
-- Table structure for my_advert
-- ----------------------------
DROP TABLE IF EXISTS `my_advert`;
CREATE TABLE `my_advert` (
  `id` int(20) NOT NULL,
  `store_id` int(20) DEFAULT NULL COMMENT '广告的店家',
  `created_time` datetime DEFAULT NULL,
  `dead_time` datetime DEFAULT NULL,
  `poster` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '海报url',
  PRIMARY KEY (`id`),
  KEY `store_id_index` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_advert
-- ----------------------------

-- ----------------------------
-- Table structure for my_cart
-- ----------------------------
DROP TABLE IF EXISTS `my_cart`;
CREATE TABLE `my_cart` (
  `id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned zerofill DEFAULT NULL,
  `product_id` bigint(20) unsigned zerofill DEFAULT NULL,
  `quantity` int(11) DEFAULT '1' COMMENT '数量',
  `checked` int(11) DEFAULT '1' COMMENT '0-未勾选；1-已勾选',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_cart
-- ----------------------------
INSERT INTO `my_cart` VALUES ('00000000000000000006', '00000000000000000001', '00000000000000000001', '1', '0', '2019-07-10 19:51:02', '2019-07-10 19:51:06');
INSERT INTO `my_cart` VALUES ('00000000000000000007', '00000000000000000002', '00000000000000000003', '2', '1', '2019-07-10 20:36:21', '2019-07-10 20:36:21');
INSERT INTO `my_cart` VALUES ('00000000000000000009', '00000000000000000003', '00000000000000000003', '1', '1', null, '2019-07-10 21:15:47');
INSERT INTO `my_cart` VALUES ('00000000000000000010', '00000000000000000004', '00000000000000000004', '6', '1', '2019-07-10 21:19:33', '2019-07-10 21:19:33');

-- ----------------------------
-- Table structure for my_category
-- ----------------------------
DROP TABLE IF EXISTS `my_category`;
CREATE TABLE `my_category` (
  `id` int(20) NOT NULL,
  `name` varchar(40) COLLATE utf8_bin NOT NULL,
  `mark` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '类别描述',
  `status` int(5) DEFAULT '1' COMMENT '1-正常；2-已删除；',
  `creator_id` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_index` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_category
-- ----------------------------

-- ----------------------------
-- Table structure for my_comment
-- ----------------------------
DROP TABLE IF EXISTS `my_comment`;
CREATE TABLE `my_comment` (
  `id` int(20) NOT NULL,
  `entity_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `score` tinyint(1) DEFAULT '5' COMMENT '打分 1-5',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  KEY `entity_id_index` (`entity_id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_comment
-- ----------------------------

-- ----------------------------
-- Table structure for my_discount
-- ----------------------------
DROP TABLE IF EXISTS `my_discount`;
CREATE TABLE `my_discount` (
  `id` int(20) NOT NULL,
  `discount_id` int(20) DEFAULT NULL,
  `discount_type` tinyint(4) DEFAULT NULL,
  `value` decimal(10,2) DEFAULT NULL,
  `store_id` int(20) DEFAULT NULL,
  `food_id` int(20) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `dead_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `store_id_index` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_discount
-- ----------------------------

-- ----------------------------
-- Table structure for my_foods
-- ----------------------------
DROP TABLE IF EXISTS `my_foods`;
CREATE TABLE `my_foods` (
  `id` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `store_id` int(20) DEFAULT NULL COMMENT '商家id',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `photo` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址，json解析',
  `photo_sub` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '补充图片',
  `cate_id` bigint(20) DEFAULT '0' COMMENT '菜品类别，用json解析',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '商品描述',
  `stock` int(10) unsigned DEFAULT '0' COMMENT '库存',
  `price` decimal(10,2) DEFAULT '0.00',
  `status` int(11) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除，4-热销，5-新品',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `store_id_index` (`store_id`) USING BTREE,
  KEY `food_name_index` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品表，由对应店家添加和更新';

-- ----------------------------
-- Records of my_foods
-- ----------------------------
INSERT INTO `my_foods` VALUES ('00000000000000000001', '2', '2', '2', '2', '2', '2', '2', '2.00', '1', '2019-07-10 17:02:27', '2019-07-10 17:02:30');

-- ----------------------------
-- Table structure for my_order
-- ----------------------------
DROP TABLE IF EXISTS `my_order`;
CREATE TABLE `my_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `store_id` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `shipping_id` bigint(20) DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `store_id_index` (`store_id`) USING BTREE,
  UNIQUE KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of my_order
-- ----------------------------

-- ----------------------------
-- Table structure for my_order_item
-- ----------------------------
DROP TABLE IF EXISTS `my_order_item`;
CREATE TABLE `my_order_item` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单号',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  `user_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  `photo` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '单品数量',
  `discount_id` bigint(20) DEFAULT NULL COMMENT '折扣',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_index` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for my_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `my_pay_info`;
CREATE TABLE `my_pay_info` (
  `id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `store_id` int(20) NOT NULL,
  `order_id` int(20) NOT NULL,
  `shipping_id` int(11) NOT NULL COMMENT '配送单号',
  `shipping_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '配送费',
  `payment` decimal(10,2) NOT NULL COMMENT '付款金额',
  `platform` int(11) NOT NULL COMMENT '1-支付宝；2-微信；',
  `status` int(11) NOT NULL COMMENT '0-未完成；1-支付成功; 2-失败；',
  `serial_number` int(30) NOT NULL COMMENT '支付平台的流水号',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`),
  KEY `order_id_index` (`order_id`),
  KEY `store_id_index` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_pay_info
-- ----------------------------

-- ----------------------------
-- Table structure for my_store
-- ----------------------------
DROP TABLE IF EXISTS `my_store`;
CREATE TABLE `my_store` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键，用户ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '名字',
  `photo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '头像',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `cate_id` bigint(20) DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '加密后密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '给密码加salt',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，1：正常，0：冻结状态，2：注销',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL,
  `update_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '127.0.0.1',
  PRIMARY KEY (`id`),
  KEY `store_phone_index` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_store
-- ----------------------------

-- ----------------------------
-- Table structure for my_user
-- ----------------------------
DROP TABLE IF EXISTS `my_user`;
CREATE TABLE `my_user` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键，用户ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '名字',
  `sex` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-未知；1-男；2-女；',
  `birth` date NOT NULL DEFAULT '1900-01-01',
  `photo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '电话号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '加密后密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '给密码加salt',
  `role` int(11) NOT NULL DEFAULT '10' COMMENT '0-超管；1-管理员；2-商家；3-送餐员；10-普通用户；',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结状态，2：注销',
  `mark` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL,
  `update_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '127.0.0.1',
  PRIMARY KEY (`id`),
  KEY `user_phone_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of my_user
-- ----------------------------
INSERT INTO `my_user` VALUES ('00000000001', 'user1', '0', '1900-01-01', 'dd', 'dd', 'dd', 'dd', 'dd', '10', '1', 'd', '2019-07-10 17:01:07', '2019-07-10 17:01:09', '127.0.0.1');
