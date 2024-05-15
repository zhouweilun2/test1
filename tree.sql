/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : tree

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 14/05/2024 19:06:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS `meeting`;
CREATE TABLE `meeting`  (
  `meeting` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meeting
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `send_time` datetime NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 6, 5, '2024-04-18 01:07:59', 't1', 'c1');
INSERT INTO `message` VALUES (2, 6, 5, '2024-04-18 01:01:55', 't0', 'c0');
INSERT INTO `message` VALUES (3, 5, 4, '2024-04-18 02:28:22', '1', '111');
INSERT INTO `message` VALUES (4, 5, 6, '2024-04-18 02:31:53', '111', '11111');
INSERT INTO `message` VALUES (5, 5, 6, '2024-04-18 02:36:12', '111', '11111322332');
INSERT INTO `message` VALUES (6, 6, 5, '2024-04-18 12:18:58', '阿斯顿发山东', '阿斯顿发山东阿斯顿发山东');
INSERT INTO `message` VALUES (7, 5, 6, '2024-04-18 12:19:49', '收到了你的消息', '阿斯顿发山东阿斯顿发山东按说阿斯顿发山东阿斯顿发山东\n阿斯顿发山东asdasdf\n阿斯顿发的');
INSERT INTO `message` VALUES (8, 8, 7, '2024-04-18 13:57:17', 'ahafbfaj', 'fafabkjafkb');
INSERT INTO `message` VALUES (9, 7, 8, '2024-04-18 14:31:39', '1e', '132131');
INSERT INTO `message` VALUES (10, 7, 8, '2024-04-18 14:31:41', '1e', '132131');
INSERT INTO `message` VALUES (11, 7, 8, '2024-04-18 14:31:43', '1e', '132131');
INSERT INTO `message` VALUES (12, 1, 2, '2024-04-18 19:35:41', 'nihao', 'great');
INSERT INTO `message` VALUES (13, 1, 3, '2024-04-18 19:41:54', '11', '1111');
INSERT INTO `message` VALUES (14, 1, 7, '2024-04-18 19:54:40', '123', '321');
INSERT INTO `message` VALUES (15, 1, 7, '2024-04-18 19:57:42', '111', 'sss');
INSERT INTO `message` VALUES (16, 1, 4, '2024-04-18 20:23:28', '222', '2222');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_number` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `passwords` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `energy` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 'admin', 'admin123', '管理员', 60);
INSERT INTO `user` VALUES (2, '李四', '002', '111111', '组长', 10);
INSERT INTO `user` VALUES (3, '王五', '003', '111111', '普通员工', 0);
INSERT INTO `user` VALUES (4, '1111', '111', '111111', '员工', 0);
INSERT INTO `user` VALUES (5, 'w1', '1111111', '1111111', '员工', 0);
INSERT INTO `user` VALUES (6, 'w2', '2222222', '2222222', '员工', 0);
INSERT INTO `user` VALUES (7, '11', '111', '111', '员工', 0);
INSERT INTO `user` VALUES (8, '222', '222', '222', '员工', 0);

SET FOREIGN_KEY_CHECKS = 1;
