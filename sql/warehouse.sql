/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 11/05/2021 11:29:14
*/


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_customer
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer`;
CREATE TABLE `bus_customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `connectionperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_customer
-- ----------------------------
INSERT INTO `bus_customer` VALUES (1, '校园超市', '111', '长沙雨花区六街', '027-9123131', '李老板', '138123123123', '中国银行', '654431331343413', '213123@sina.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (2, '步步高超市', '222', '长沙雨花区六街', '0755-9123131', '张老板', '138123123123', '中国银行', '654431331343413', '213123@sina.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (3, '刘某人超市', '430000', '长沙雨花区六街', '027-11011011', '刘总', '13434134131', '招商银行', '6543123341334133', '6666@66.com', '545341', 1);
INSERT INTO `bus_customer` VALUES (7, '天天旺超市', '1412', '长沙雨花区六街', '13728191321', '李总', '13728191321', '招商银行', '654431331343413', '13728191321@163.com', '121213', 1);
INSERT INTO `bus_customer` VALUES (9, 'shop', '412723', '长沙雨花区六街', '13576489382', '罗总', '13576489382', '招商银行', '654431331343413', '13576489382@qq.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (10, 'shop1', '412723', '长沙雨花区六街', '13728191321', '小龙', '13576489382', '招商银行', '654431331343413', '13576489382@qq.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (11, 'shop2', '333', '长沙雨花区六街', '13728191321', '小卓', '13576489382', '招商银行', '654431331343413', '13576489382@qq.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (12, 'shop3', '555', '长沙雨花区六街', '13728191321', '小旺', '13576489382', '招商银行', '654431331343413', '13576489382@qq.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (13, 'shop4', '666', '长沙雨花区六街', '13728191321', '小玲', '13576489382', '招商银行', '654431331343413', '13576489382@qq.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (14, 'shop5', '777', '长沙雨花区六街', '13728191321', '小建', '13576489382', '招商银行', '654431331343413', '13576489382@qq.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (15, '新型超市', '888', '长沙雨花区六街', '13728191321', '小焕', '13576489382', '招商银行', '654431331343413', '13576489382@qq.com', '430000', 1);

-- ----------------------------
-- Table structure for bus_deliver
-- ----------------------------
DROP TABLE IF EXISTS `bus_deliver`;
CREATE TABLE `bus_deliver`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customerid` int(0) DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `delivertime` datetime(0) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(0) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deliverprice` decimal(10, 2) DEFAULT NULL,
  `goodsid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_deliver
-- ----------------------------
INSERT INTO `bus_deliver` VALUES (3, 1, NULL, '2021-04-12 10:32:44', '最终管理员', 2, '', 5.00, 7);
INSERT INTO `bus_deliver` VALUES (5, 1, NULL, '2021-04-12 10:55:05', '最终管理员', 1000, '', 5.00, 3);
INSERT INTO `bus_deliver` VALUES (6, 1, NULL, '2021-04-12 10:55:48', '最终管理员', 2, '', 5.00, 3);
INSERT INTO `bus_deliver` VALUES (7, 1, NULL, '2021-04-12 11:41:16', '最终管理员', 2, '', 5.00, 2);
INSERT INTO `bus_deliver` VALUES (8, 3, NULL, '2021-04-13 03:16:09', '最终管理员', 1000, 'test', 5.00, 5);
INSERT INTO `bus_deliver` VALUES (9, 1, NULL, '2021-04-13 08:14:29', '最终管理员', 2, '', 5.00, 2);

-- ----------------------------
-- Table structure for bus_deliverback
-- ----------------------------
DROP TABLE IF EXISTS `bus_deliverback`;
CREATE TABLE `bus_deliverback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `customerid` int(0) DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deliverbacktime` datetime(0) DEFAULT NULL,
  `deliverbackprice` double(10, 2) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(0) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodsid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_goods
-- ----------------------------
DROP TABLE IF EXISTS `bus_goods`;
CREATE TABLE `bus_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `produceplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodspackage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `promitcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `number` int(0) DEFAULT NULL,
  `dangernum` int(0) DEFAULT NULL,
  `goodsimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(0) DEFAULT NULL,
  `providerid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sq0btr2v2lq8gt8b4gb8tlk0i`(`providerid`) USING BTREE,
  CONSTRAINT `bus_goods_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_goods
-- ----------------------------
INSERT INTO `bus_goods` VALUES (1, '矿泉水', '湖北', '500ML', '瓶', 'PH12345', 'PZ1234', '好喝', 2, 1000, 10, '2018-12-25/userface1.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (2, '旺旺饼干', '长沙', '包', '袋', 'PH12312312', 'PZ12312', '好吃不上火', 4, 982, 10, '2018-12-25/userface2.jpg', 1, 1);
INSERT INTO `bus_goods` VALUES (3, '旺旺大礼包', '长沙', '盒', '盒', '11', '11', '111', 28, 500, 10, '2018-12-25/userface3.jpg', 1, 1);
INSERT INTO `bus_goods` VALUES (4, '牛奶', '武汉', '200ML', '瓶', '11', '111', '12321', 3, 1000, 10, '2018-12-25/userface4.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (5, '饮料', '武汉', '300ML', '瓶', '1234', '12321', '22221111', 3, 10000, 100, '2018-12-25/userface5.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (7, '饼干', '湖北', '包', '袋', '12312', '2132', '213', 123, 1000, 100, '2019-09-27/48E8C8FE8F2D4536820435CCCD968AEC.jpg', 1, 2);

-- ----------------------------
-- Table structure for bus_inport
-- ----------------------------
DROP TABLE IF EXISTS `bus_inport`;
CREATE TABLE `bus_inport`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `inporttime` datetime(0) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(0) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `inportprice` double DEFAULT NULL,
  `providerid` int(0) DEFAULT NULL,
  `goodsid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_1o4bujsyd2kl4iqw48fie224v`(`providerid`) USING BTREE,
  INDEX `FK_ho29poeu4o2dxu4rg1ammeaql`(`goodsid`) USING BTREE,
  CONSTRAINT `bus_inport_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_inport_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `bus_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_inport
-- ----------------------------
INSERT INTO `bus_inport` VALUES (16, NULL, '2021-04-12 04:54:48', '超级管理员', 2, '', 2, 1, 2);

-- ----------------------------
-- Table structure for bus_outport
-- ----------------------------
DROP TABLE IF EXISTS `bus_outport`;
CREATE TABLE `bus_outport`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `providerid` int(0) DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `outputtime` datetime(0) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `outportprice` double(10, 2) DEFAULT NULL,
  `number` int(0) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodsid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_outport
-- ----------------------------
INSERT INTO `bus_outport` VALUES (9, 1, NULL, '2021-04-12 06:24:00', '超级管理员', 2.00, 2, '', 2);
INSERT INTO `bus_outport` VALUES (10, 1, NULL, '2021-04-17 02:21:54', '最终管理员', 2.00, 2, '', 2);

-- ----------------------------
-- Table structure for bus_provider
-- ----------------------------
DROP TABLE IF EXISTS `bus_provider`;
CREATE TABLE `bus_provider`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `providername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `connectionperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_provider
-- ----------------------------
INSERT INTO `bus_provider` VALUES (1, '旺旺食品', '434000', '湖北', '0728-4124312', '李毅', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 1);
INSERT INTO `bus_provider` VALUES (2, '好丽友集团', '430000', '湖南', '0728-4124312', '张磊', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 1);
INSERT INTO `bus_provider` VALUES (3, '农夫山泉集团', '513131', '湖南', '0789-21312', '小晨', '15867873291', '建设银行', '512314141541', '123131', '312312', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open` int(0) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(0) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int(0) DEFAULT NULL COMMENT '排序码【为了调事显示顺序】',
  `createtime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '总公司', 1, 'BOSS', '长沙', 1, 1, '2021-04-12 14:06:32');
INSERT INTO `sys_dept` VALUES (2, 1, '物流部', 0, '发货进货', '长沙', 1, 2, '2021-04-12 14:06:32');
INSERT INTO `sys_dept` VALUES (3, 1, '运营部', 0, '无', '武汉', 1, 3, '2021-04-12 14:06:32');
INSERT INTO `sys_dept` VALUES (4, 1, '人事部门', 0, '无', '长沙', 1, 4, '2021-04-12 14:06:32');
INSERT INTO `sys_dept` VALUES (5, 2, '物流一部', 0, '物流一部', '武汉', 1, 5, '2021-04-12 14:06:32');
INSERT INTO `sys_dept` VALUES (6, 2, '物流二部', 0, '', '武汉', 1, 6, '2021-04-12 14:06:32');
INSERT INTO `sys_dept` VALUES (7, 3, '运营一部', 0, '运营一部', '武汉', 1, 7, '2021-04-12 14:06:32');
INSERT INTO `sys_dept` VALUES (24, 4, '人事一部', 1, '333', '长沙', 1, 12, '2021-04-12 02:11:09');
INSERT INTO `sys_dept` VALUES (25, 4, '人事二部', 1, '444', '长沙', 1, 13, '2021-04-17 02:11:25');

-- ----------------------------
-- Table structure for sys_loginfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginfo`;
CREATE TABLE `sys_loginfo`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loginip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `logintime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 293 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_loginfo
-- ----------------------------
INSERT INTO `sys_loginfo` VALUES (256, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 11:34:50');
INSERT INTO `sys_loginfo` VALUES (257, '最终管理员-system', '127.0.0.1', '2021-04-12 11:44:40');
INSERT INTO `sys_loginfo` VALUES (258, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 11:45:46');
INSERT INTO `sys_loginfo` VALUES (259, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 11:48:16');
INSERT INTO `sys_loginfo` VALUES (260, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 11:50:50');
INSERT INTO `sys_loginfo` VALUES (261, '最终管理员-system', '127.0.0.1', '2021-04-12 11:57:56');
INSERT INTO `sys_loginfo` VALUES (262, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 12:36:53');
INSERT INTO `sys_loginfo` VALUES (263, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 12:37:11');
INSERT INTO `sys_loginfo` VALUES (264, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 12:42:27');
INSERT INTO `sys_loginfo` VALUES (265, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 12:49:16');
INSERT INTO `sys_loginfo` VALUES (266, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 13:20:37');
INSERT INTO `sys_loginfo` VALUES (267, '最终管理员-system', '127.0.0.1', '2021-04-12 13:31:35');
INSERT INTO `sys_loginfo` VALUES (268, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 13:40:23');
INSERT INTO `sys_loginfo` VALUES (269, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 13:42:41');
INSERT INTO `sys_loginfo` VALUES (270, '最终管理员-system', '127.0.0.1', '2021-04-12 13:43:37');
INSERT INTO `sys_loginfo` VALUES (271, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-12 13:44:16');
INSERT INTO `sys_loginfo` VALUES (272, '最终管理员-system', '127.0.0.1', '2021-04-12 13:48:04');
INSERT INTO `sys_loginfo` VALUES (273, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-13 03:13:52');
INSERT INTO `sys_loginfo` VALUES (274, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-13 08:14:07');
INSERT INTO `sys_loginfo` VALUES (275, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-13 14:01:52');
INSERT INTO `sys_loginfo` VALUES (276, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-14 08:46:27');
INSERT INTO `sys_loginfo` VALUES (277, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-14 09:29:49');
INSERT INTO `sys_loginfo` VALUES (278, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-14 12:01:53');
INSERT INTO `sys_loginfo` VALUES (279, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-14 12:30:41');
INSERT INTO `sys_loginfo` VALUES (280, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-14 14:35:39');
INSERT INTO `sys_loginfo` VALUES (281, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-15 03:10:29');
INSERT INTO `sys_loginfo` VALUES (282, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-15 06:31:02');
INSERT INTO `sys_loginfo` VALUES (283, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-15 07:16:30');
INSERT INTO `sys_loginfo` VALUES (284, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-15 11:46:50');
INSERT INTO `sys_loginfo` VALUES (285, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-16 10:52:16');
INSERT INTO `sys_loginfo` VALUES (286, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-16 12:57:31');
INSERT INTO `sys_loginfo` VALUES (287, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-17 00:24:48');
INSERT INTO `sys_loginfo` VALUES (288, '孙七-sq', '0:0:0:0:0:0:0:1', '2021-04-17 01:22:40');
INSERT INTO `sys_loginfo` VALUES (289, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-17 01:23:02');
INSERT INTO `sys_loginfo` VALUES (290, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-17 02:19:51');
INSERT INTO `sys_loginfo` VALUES (291, '孙七-sq', '0:0:0:0:0:0:0:1', '2021-04-17 02:25:44');
INSERT INTO `sys_loginfo` VALUES (292, '最终管理员-system', '0:0:0:0:0:0:0:1', '2021-04-22 02:41:45');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `percode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open` int(0) DEFAULT NULL,
  `ordernum` int(0) DEFAULT NULL,
  `available` int(0) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, 'menu', '仓库物流管理系统', NULL, '&#xe668;', NULL, NULL, 1, 1, 1);
INSERT INTO `sys_permission` VALUES (2, 1, 'menu', '基础管理', NULL, '&#xe857;', '', '', 0, 2, 1);
INSERT INTO `sys_permission` VALUES (3, 1, 'menu', '物流管理', NULL, '&#xe63c;', '', NULL, 0, 3, 1);
INSERT INTO `sys_permission` VALUES (5, 1, 'menu', '系统管理', NULL, '&#xe614;', '', '', 0, 5, 1);
INSERT INTO `sys_permission` VALUES (7, 2, 'menu', '客户管理', NULL, '&#xe651;', '/bus/toCustomerManager', '', 0, 7, 1);
INSERT INTO `sys_permission` VALUES (8, 2, 'menu', '供应商管理', NULL, '&#xe658;', '/bus/toProviderManager', '', 0, 8, 1);
INSERT INTO `sys_permission` VALUES (9, 2, 'menu', '商品管理', NULL, '&#xe657;', '/bus/toGoodsManager', '', 0, 9, 1);
INSERT INTO `sys_permission` VALUES (10, 3, 'menu', '商品进货', NULL, '&#xe756;', '/bus/toInportManager', '', 0, 10, 1);
INSERT INTO `sys_permission` VALUES (11, 3, 'menu', '退货查询', NULL, '&#xe65a;', '/bus/toOutportManager', '', 0, 11, 1);
INSERT INTO `sys_permission` VALUES (14, 5, 'menu', '部门管理', NULL, '&#xe770;', '/sys/toDeptManager', '', 0, 14, 1);
INSERT INTO `sys_permission` VALUES (15, 5, 'menu', '菜单管理', NULL, '&#xe857;', '/sys/toMenuManager', '', 0, 15, 1);
INSERT INTO `sys_permission` VALUES (16, 96, 'menu', '权限管理', '', '&#xe857;', '/sys/toPermissionManager', '', 0, 16, 1);
INSERT INTO `sys_permission` VALUES (17, 96, 'menu', '角色管理', '', '&#xe650;', '/sys/toRoleManager', '', 0, 17, 1);
INSERT INTO `sys_permission` VALUES (18, 96, 'menu', '用户管理', '', '&#xe612;', '/sys/toUserManager', '', 0, 18, 1);
INSERT INTO `sys_permission` VALUES (21, 97, 'menu', '登陆日志', NULL, '&#xe675;', '/sys/toLoginfoManager', '', 0, 21, 1);
INSERT INTO `sys_permission` VALUES (23, 97, 'menu', '图标展示', NULL, '&#xe670;', '../resources/page/icon.html', NULL, 0, 23, 1);
INSERT INTO `sys_permission` VALUES (30, 14, 'permission', '添加部门', 'dept:create', '', NULL, NULL, 0, 24, 1);
INSERT INTO `sys_permission` VALUES (31, 14, 'permission', '修改部门', 'dept:update', '', NULL, NULL, 0, 26, 1);
INSERT INTO `sys_permission` VALUES (32, 14, 'permission', '删除部门', 'dept:delete', '', NULL, NULL, 0, 27, 1);
INSERT INTO `sys_permission` VALUES (34, 15, 'permission', '添加菜单', 'menu:create', '', '', '', 0, 29, 1);
INSERT INTO `sys_permission` VALUES (35, 15, 'permission', '修改菜单', 'menu:update', '', NULL, NULL, 0, 30, 1);
INSERT INTO `sys_permission` VALUES (36, 15, 'permission', '删除菜单', 'menu:delete', '', NULL, NULL, 0, 31, 1);
INSERT INTO `sys_permission` VALUES (38, 16, 'permission', '添加权限', 'permission:create', '', NULL, NULL, 0, 33, 1);
INSERT INTO `sys_permission` VALUES (39, 16, 'permission', '修改权限', 'permission:update', '', NULL, NULL, 0, 34, 1);
INSERT INTO `sys_permission` VALUES (40, 16, 'permission', '删除权限', 'permission:delete', '', NULL, NULL, 0, 35, 1);
INSERT INTO `sys_permission` VALUES (42, 17, 'permission', '添加角色', 'role:create', '', NULL, NULL, 0, 37, 1);
INSERT INTO `sys_permission` VALUES (43, 17, 'permission', '修改角色', 'role:update', '', NULL, NULL, 0, 38, 1);
INSERT INTO `sys_permission` VALUES (44, 17, 'permission', '角色删除', 'role:delete', '', NULL, NULL, 0, 39, 1);
INSERT INTO `sys_permission` VALUES (46, 17, 'permission', '分配权限', 'role:selectPermission', '', NULL, NULL, 0, 41, 1);
INSERT INTO `sys_permission` VALUES (47, 18, 'permission', '添加用户', 'user:create', '', NULL, NULL, 0, 42, 1);
INSERT INTO `sys_permission` VALUES (48, 18, 'permission', '修改用户', 'user:update', '', NULL, NULL, 0, 43, 1);
INSERT INTO `sys_permission` VALUES (49, 18, 'permission', '删除用户', 'user:delete', '', NULL, NULL, 0, 44, 1);
INSERT INTO `sys_permission` VALUES (51, 18, 'permission', '用户分配角色', 'user:selectRole', '', NULL, NULL, 0, 46, 1);
INSERT INTO `sys_permission` VALUES (52, 18, 'permission', '重置密码', 'user:resetPwd', NULL, NULL, NULL, 0, 47, 1);
INSERT INTO `sys_permission` VALUES (53, 14, 'permission', '部门查询', 'dept:view', NULL, NULL, NULL, 0, 48, 1);
INSERT INTO `sys_permission` VALUES (54, 15, 'permission', '菜单查询', 'menu:view', NULL, NULL, NULL, 0, 49, 1);
INSERT INTO `sys_permission` VALUES (55, 16, 'permission', '权限查询', 'permission:view', NULL, NULL, NULL, 0, 50, 1);
INSERT INTO `sys_permission` VALUES (56, 17, 'permission', '角色查询', 'role:view', NULL, NULL, NULL, 0, 51, 1);
INSERT INTO `sys_permission` VALUES (57, 18, 'permission', '用户查询', 'user:view', NULL, NULL, NULL, 0, 52, 1);
INSERT INTO `sys_permission` VALUES (68, 7, 'permission', '客户查询', 'customer:view', NULL, NULL, NULL, NULL, 60, 1);
INSERT INTO `sys_permission` VALUES (69, 7, 'permission', '客户添加', 'customer:create', NULL, NULL, NULL, NULL, 61, 1);
INSERT INTO `sys_permission` VALUES (70, 7, 'permission', '客户修改', 'customer:update', NULL, NULL, NULL, NULL, 62, 1);
INSERT INTO `sys_permission` VALUES (71, 7, 'permission', '客户删除', 'customer:delete', NULL, NULL, NULL, NULL, 63, 1);
INSERT INTO `sys_permission` VALUES (73, 21, 'permission', '日志查询', 'info:view', NULL, NULL, NULL, NULL, 65, 1);
INSERT INTO `sys_permission` VALUES (74, 21, 'permission', '日志删除', 'info:delete', NULL, NULL, NULL, NULL, 66, 1);
INSERT INTO `sys_permission` VALUES (75, 21, 'permission', '日志批量删除', 'info:batchdelete', NULL, NULL, NULL, NULL, 67, 1);
INSERT INTO `sys_permission` VALUES (81, 8, 'permission', '供应商查询', 'provider:view', NULL, NULL, NULL, NULL, 73, 1);
INSERT INTO `sys_permission` VALUES (82, 8, 'permission', '供应商添加', 'provider:create', NULL, NULL, NULL, NULL, 74, 1);
INSERT INTO `sys_permission` VALUES (83, 8, 'permission', '供应商修改', 'provider:update', NULL, NULL, NULL, NULL, 75, 1);
INSERT INTO `sys_permission` VALUES (84, 8, 'permission', '供应商删除', 'provider:delete', NULL, NULL, NULL, NULL, 76, 1);
INSERT INTO `sys_permission` VALUES (91, 9, 'permission', '商品查询', 'goods:view', NULL, NULL, NULL, 0, 79, 1);
INSERT INTO `sys_permission` VALUES (92, 9, 'permission', '商品添加', 'goods:create', NULL, NULL, NULL, 0, 80, 1);
INSERT INTO `sys_permission` VALUES (94, 9, 'permission', '商品修改', 'goods:update', NULL, 'goods:update', NULL, 0, 81, 1);
INSERT INTO `sys_permission` VALUES (96, 1, 'menu', '人事管理', NULL, '&#xe613;', '', NULL, 0, 83, 1);
INSERT INTO `sys_permission` VALUES (97, 1, 'menu', '其他', '7', '&#xe631;', '', NULL, 0, 20, 1);
INSERT INTO `sys_permission` VALUES (98, 3, 'menu', '商品发货', NULL, '&#xe609;', '/bus/toDelivertManager', NULL, 0, 16, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(0) DEFAULT NULL,
  `createtime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '最终管理员', '拥有所有菜单权限', 1, '2021-02-08 14:06:32');
INSERT INTO `sys_role` VALUES (4, '基础管理员', '可以对基础管理进行操作', 1, '2021-02-09 14:28:32');
INSERT INTO `sys_role` VALUES (5, '物流管理员', '可以对仓库进行操作', 1, '2021-02-09 14:29:33');
INSERT INTO `sys_role` VALUES (6, '人事管理员', '人事管理员', 1, '2021-04-12 14:06:32');
INSERT INTO `sys_role` VALUES (7, '系统管理员', '可以对系统进行操作', 1, '2021-02-09 14:29:48');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `rid` int(0) NOT NULL,
  `pid` int(0) NOT NULL,
  PRIMARY KEY (`pid`, `rid`) USING BTREE,
  INDEX `FK_tcsr9ucxypb3ce1q5qngsfk33`(`rid`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 30);
INSERT INTO `sys_role_permission` VALUES (1, 31);
INSERT INTO `sys_role_permission` VALUES (1, 32);
INSERT INTO `sys_role_permission` VALUES (1, 34);
INSERT INTO `sys_role_permission` VALUES (1, 35);
INSERT INTO `sys_role_permission` VALUES (1, 36);
INSERT INTO `sys_role_permission` VALUES (1, 38);
INSERT INTO `sys_role_permission` VALUES (1, 39);
INSERT INTO `sys_role_permission` VALUES (1, 40);
INSERT INTO `sys_role_permission` VALUES (1, 42);
INSERT INTO `sys_role_permission` VALUES (1, 43);
INSERT INTO `sys_role_permission` VALUES (1, 44);
INSERT INTO `sys_role_permission` VALUES (1, 46);
INSERT INTO `sys_role_permission` VALUES (1, 47);
INSERT INTO `sys_role_permission` VALUES (1, 48);
INSERT INTO `sys_role_permission` VALUES (1, 49);
INSERT INTO `sys_role_permission` VALUES (1, 51);
INSERT INTO `sys_role_permission` VALUES (1, 52);
INSERT INTO `sys_role_permission` VALUES (1, 53);
INSERT INTO `sys_role_permission` VALUES (1, 54);
INSERT INTO `sys_role_permission` VALUES (1, 55);
INSERT INTO `sys_role_permission` VALUES (1, 56);
INSERT INTO `sys_role_permission` VALUES (1, 57);
INSERT INTO `sys_role_permission` VALUES (1, 68);
INSERT INTO `sys_role_permission` VALUES (1, 69);
INSERT INTO `sys_role_permission` VALUES (1, 70);
INSERT INTO `sys_role_permission` VALUES (1, 71);
INSERT INTO `sys_role_permission` VALUES (1, 73);
INSERT INTO `sys_role_permission` VALUES (1, 74);
INSERT INTO `sys_role_permission` VALUES (1, 75);
INSERT INTO `sys_role_permission` VALUES (1, 81);
INSERT INTO `sys_role_permission` VALUES (1, 82);
INSERT INTO `sys_role_permission` VALUES (1, 83);
INSERT INTO `sys_role_permission` VALUES (1, 84);
INSERT INTO `sys_role_permission` VALUES (1, 91);
INSERT INTO `sys_role_permission` VALUES (1, 92);
INSERT INTO `sys_role_permission` VALUES (1, 94);
INSERT INTO `sys_role_permission` VALUES (1, 96);
INSERT INTO `sys_role_permission` VALUES (1, 97);
INSERT INTO `sys_role_permission` VALUES (4, 2);
INSERT INTO `sys_role_permission` VALUES (4, 7);
INSERT INTO `sys_role_permission` VALUES (4, 8);
INSERT INTO `sys_role_permission` VALUES (4, 9);
INSERT INTO `sys_role_permission` VALUES (4, 68);
INSERT INTO `sys_role_permission` VALUES (4, 69);
INSERT INTO `sys_role_permission` VALUES (4, 70);
INSERT INTO `sys_role_permission` VALUES (4, 71);
INSERT INTO `sys_role_permission` VALUES (4, 81);
INSERT INTO `sys_role_permission` VALUES (4, 82);
INSERT INTO `sys_role_permission` VALUES (4, 83);
INSERT INTO `sys_role_permission` VALUES (4, 84);
INSERT INTO `sys_role_permission` VALUES (4, 91);
INSERT INTO `sys_role_permission` VALUES (4, 92);
INSERT INTO `sys_role_permission` VALUES (4, 94);
INSERT INTO `sys_role_permission` VALUES (5, 1);
INSERT INTO `sys_role_permission` VALUES (5, 3);
INSERT INTO `sys_role_permission` VALUES (5, 10);
INSERT INTO `sys_role_permission` VALUES (5, 11);
INSERT INTO `sys_role_permission` VALUES (5, 98);
INSERT INTO `sys_role_permission` VALUES (6, 1);
INSERT INTO `sys_role_permission` VALUES (6, 16);
INSERT INTO `sys_role_permission` VALUES (6, 17);
INSERT INTO `sys_role_permission` VALUES (6, 18);
INSERT INTO `sys_role_permission` VALUES (6, 38);
INSERT INTO `sys_role_permission` VALUES (6, 39);
INSERT INTO `sys_role_permission` VALUES (6, 40);
INSERT INTO `sys_role_permission` VALUES (6, 42);
INSERT INTO `sys_role_permission` VALUES (6, 43);
INSERT INTO `sys_role_permission` VALUES (6, 44);
INSERT INTO `sys_role_permission` VALUES (6, 46);
INSERT INTO `sys_role_permission` VALUES (6, 47);
INSERT INTO `sys_role_permission` VALUES (6, 48);
INSERT INTO `sys_role_permission` VALUES (6, 49);
INSERT INTO `sys_role_permission` VALUES (6, 51);
INSERT INTO `sys_role_permission` VALUES (6, 52);
INSERT INTO `sys_role_permission` VALUES (6, 55);
INSERT INTO `sys_role_permission` VALUES (6, 56);
INSERT INTO `sys_role_permission` VALUES (6, 57);
INSERT INTO `sys_role_permission` VALUES (6, 96);
INSERT INTO `sys_role_permission` VALUES (7, 5);
INSERT INTO `sys_role_permission` VALUES (7, 14);
INSERT INTO `sys_role_permission` VALUES (7, 15);
INSERT INTO `sys_role_permission` VALUES (7, 16);
INSERT INTO `sys_role_permission` VALUES (7, 17);
INSERT INTO `sys_role_permission` VALUES (7, 18);
INSERT INTO `sys_role_permission` VALUES (7, 21);
INSERT INTO `sys_role_permission` VALUES (7, 23);
INSERT INTO `sys_role_permission` VALUES (7, 30);
INSERT INTO `sys_role_permission` VALUES (7, 31);
INSERT INTO `sys_role_permission` VALUES (7, 34);
INSERT INTO `sys_role_permission` VALUES (7, 35);
INSERT INTO `sys_role_permission` VALUES (7, 36);
INSERT INTO `sys_role_permission` VALUES (7, 38);
INSERT INTO `sys_role_permission` VALUES (7, 39);
INSERT INTO `sys_role_permission` VALUES (7, 40);
INSERT INTO `sys_role_permission` VALUES (7, 42);
INSERT INTO `sys_role_permission` VALUES (7, 43);
INSERT INTO `sys_role_permission` VALUES (7, 44);
INSERT INTO `sys_role_permission` VALUES (7, 46);
INSERT INTO `sys_role_permission` VALUES (7, 47);
INSERT INTO `sys_role_permission` VALUES (7, 48);
INSERT INTO `sys_role_permission` VALUES (7, 49);
INSERT INTO `sys_role_permission` VALUES (7, 51);
INSERT INTO `sys_role_permission` VALUES (7, 52);
INSERT INTO `sys_role_permission` VALUES (7, 53);
INSERT INTO `sys_role_permission` VALUES (7, 54);
INSERT INTO `sys_role_permission` VALUES (7, 55);
INSERT INTO `sys_role_permission` VALUES (7, 56);
INSERT INTO `sys_role_permission` VALUES (7, 57);
INSERT INTO `sys_role_permission` VALUES (7, 73);
INSERT INTO `sys_role_permission` VALUES (7, 74);
INSERT INTO `sys_role_permission` VALUES (7, 75);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `rid` int(0) NOT NULL,
  `uid` int(0) NOT NULL,
  PRIMARY KEY (`uid`, `rid`) USING BTREE,
  INDEX `FK_203gdpkwgtow7nxlo2oap5jru`(`rid`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 2);
INSERT INTO `sys_role_user` VALUES (4, 3);
INSERT INTO `sys_role_user` VALUES (4, 8);
INSERT INTO `sys_role_user` VALUES (5, 4);
INSERT INTO `sys_role_user` VALUES (5, 8);
INSERT INTO `sys_role_user` VALUES (6, 5);
INSERT INTO `sys_role_user` VALUES (6, 8);
INSERT INTO `sys_role_user` VALUES (7, 6);
INSERT INTO `sys_role_user` VALUES (7, 8);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` int(0) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deptid` int(0) DEFAULT NULL,
  `hiredate` datetime(0) DEFAULT NULL,
  `mgr` int(0) DEFAULT NULL,
  `available` int(0) DEFAULT 1,
  `ordernum` int(0) DEFAULT NULL,
  `type` int(0) DEFAULT NULL COMMENT '用户类型[0超级管理员1，管理员，2普通用户]',
  `imgpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_3rrcpvho2w1mx1sfiuuyir1h`(`deptid`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`deptid`) REFERENCES `sys_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '最终管理员', 'system', '长沙', 1, '超级管理员', '532ac00e86893901af5f0be6b704dbc7', 1, '2021-04-12 11:06:34', NULL, 1, 1, 0, '../resources/images/defaultusertitle.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (2, '李四', 'ls', '长沙', 0, '长沙', 'b07b848d69e0553b80e601d31571797e', 1, '2021-04-10 11:06:36', 1, 1, 2, 1, '../resources/images/defaultusertitle.jpg', 'FC1EE06AE4354D3FBF7FDD15C8FCDA71');
INSERT INTO `sys_user` VALUES (3, '王五', 'ww', '长沙', 1, '管理员', '3c3f971eae61e097f59d52360323f1c8', 3, '2021-04-10 11:06:38', 2, 1, 3, 1, '../resources/images/defaultusertitle.jpg', '3D5F956E053C4E85B7D2681386E235D2');
INSERT INTO `sys_user` VALUES (4, '赵六', 'zl', '长沙', 1, '程序员', '2e969742a7ea0c7376e9551d578e05dd', 4, '2021-04-10 11:06:40', 3, 1, 4, 1, '../resources/images/defaultusertitle.jpg', '6480EE1391E34B0886ACADA501E31145');
INSERT INTO `sys_user` VALUES (5, '孙七', 'sq', '长沙', 1, '程序员', 'beb46b6860ab7939bad9c58cebd848bd', 2, '2021-04-10 11:06:43', 4, 1, 5, 1, '../resources/images/defaultusertitle.jpg', 'C1E8583C6FE847D5AE44554DCEAD4B06');
INSERT INTO `sys_user` VALUES (6, '刘八', 'lb', '长沙', 1, '程序员', 'bcee2b05b4b591106829aec69a094806', 4, '2021-04-10 11:21:12', 3, 1, 6, 1, '../resources/images/defaultusertitle.jpg', 'E6CCF54A09894D998225878BBD139B20');
INSERT INTO `sys_user` VALUES (8, '秦九', 'lp', '长沙', 1, '长沙', '83c6312d3124527c06f9f32c9f0f4122', 7, '2021-04-10 08:47:38', 3, 1, 7, 1, NULL, '9A77217BD788418683C5D69CDC85B4AA');

SET FOREIGN_KEY_CHECKS = 1;
