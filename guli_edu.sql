/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : guli_edu

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 01/01/2021 10:14:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程科目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('1343554941320585217', '前端开发', '0', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941387694082', 'JavaScript', '1343554941320585217', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941463191554', 'jQuery', '1343554941320585217', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941500940289', 'Vue', '1343554941320585217', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941538689025', '后端开发', '0', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941572243457', 'Java', '1343554941538689025', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941622575106', 'C++', '1343554941538689025', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941681295361', '数据库开发', '0', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');
INSERT INTO `edu_subject` VALUES ('1343554941714849793', 'MySql', '1343554941681295361', 0, '2020-12-28 21:50:35', '2020-12-28 21:50:35');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int(10) UNSIGNED NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师头像',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除，0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('1189389726308478911', '张三', '高级讲师简介', '高级', 1, 'https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg', 0, 0, '2019-10-30 14:18:46', '2020-12-16 22:21:14');
INSERT INTO `edu_teacher` VALUES ('1189389726308478977', '晴天', '高级讲师简介', '高级讲师资历', 2, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png', 1, 0, '2019-10-30 11:53:03', '2019-10-30 11:53:03');
INSERT INTO `edu_teacher` VALUES ('1189390295668469762', '李刚', '高级讲师简介', '高级讲师', 2, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/b8aa36a2-db50-4eca-a6e3-cc6e608355e0.png', 2, 0, '2019-10-30 11:55:19', '2019-11-12 13:37:52');
INSERT INTO `edu_teacher` VALUES ('1189426437876985857', '王二', '高级讲师简介', '高级讲师', 2, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/e44a2e92-2421-4ea3-bb49-46f2ec96ef88.png', 0, 0, '2019-10-30 14:18:56', '2019-11-12 13:37:35');
INSERT INTO `edu_teacher` VALUES ('1189426464967995393', '王五', '高级讲师简介', '高级讲师', 1, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/65423f14-49a9-4092-baf5-6d0ef9686a85.png', 0, 0, '2019-10-30 14:19:02', '2019-11-12 13:37:18');
INSERT INTO `edu_teacher` VALUES ('1192249914833055746', '李四', '高级讲师简介', '高级讲师', 1, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/07/91871e25-fd83-4af6-845f-ea8d471d825d.png', 0, 0, '2019-11-07 09:18:25', '2019-11-12 13:37:01');
INSERT INTO `edu_teacher` VALUES ('1192327476087115778', '刘六', '1111', '11', 1, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/5805c6cd-c8ad-4a77-aafd-d2e083bfd8a4.png', 0, 0, '2019-11-07 14:26:37', '2019-11-11 16:26:26');
INSERT INTO `edu_teacher` VALUES ('1333679854010425346', '赵六', '普通讲师简介', '普通讲师', 1, 'www.baidu.com', 0, 0, '2020-12-01 15:50:30', '2020-12-01 15:50:30');
INSERT INTO `edu_teacher` VALUES ('1339214799919276034', 'test00', '普通讲师', 'test', 1, 'test', 0, 0, '2020-12-16 22:24:24', '2020-12-16 22:24:24');
INSERT INTO `edu_teacher` VALUES ('1339214799919276035', 'test01', '普通讲师', 'test01', 1, 'test01', 0, 0, '2020-12-18 22:00:48', '2020-12-18 22:09:52');
INSERT INTO `edu_teacher` VALUES ('1339214799919276036', 'test02', '高级讲师', 'test02', 1, 'test02', 0, 0, '2020-12-18 22:01:45', '2020-12-18 22:10:49');
INSERT INTO `edu_teacher` VALUES ('1339214799919276037', 'test03', '普通讲师', 'test03', 2, 'test03', 0, 0, '2020-12-18 22:03:09', '2020-12-19 16:43:11');
INSERT INTO `edu_teacher` VALUES ('1339974056260292610', 'test04', '清华大学硕士毕业', '讲师', 2, '', 1, 0, '2020-12-19 00:41:25', '2020-12-19 16:40:49');
INSERT INTO `edu_teacher` VALUES ('1340216678040117249', 'test05', '北京大学', '教授·', 1, '', 1, 0, '2020-12-19 16:45:31', '2020-12-19 16:45:31');
INSERT INTO `edu_teacher` VALUES ('1342410366900162562', '头像上传测试', '头像头像头像', 'test', 1, 'https://edu-0929.oss-cn-hangzhou.aliyuncs.com/2020/12/25/acbabb3a2b974b98b3fcb81200084292file.png', 1, 0, '2020-12-25 18:02:27', '2020-12-25 18:02:27');

SET FOREIGN_KEY_CHECKS = 1;
