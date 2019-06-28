/*
 Navicat Premium Data Transfer

 Source Server         : alibaba
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 47.97.196.216:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 28/06/2019 13:15:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `Book_ID` int(10) NOT NULL AUTO_INCREMENT,
  `Book_Name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `Publish_Year` date NULL DEFAULT NULL,
  `Writer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `Press` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `Comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`Book_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for buyer
-- ----------------------------
DROP TABLE IF EXISTS `buyer`;
CREATE TABLE `buyer`  (
  `Buyer_ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '买家ID',
  `User_Name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '买家名字',
  `Sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Buyer_ID`) USING BTREE,
  UNIQUE INDEX `User_Name`(`User_Name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1240 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller`  (
  `Seller_ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Seller_Name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`Seller_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `Seller_ID` int(11) NOT NULL,
  `Book_ID` int(11) NOT NULL,
  `Stock_Num` int(11) NOT NULL,
  `Price` decimal(10, 2) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
