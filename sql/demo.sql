/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-01-22 18:16:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_database
-- ----------------------------
DROP TABLE IF EXISTS `sys_database`;
CREATE TABLE `sys_database` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据源ID',
  `name` varchar(20) NOT NULL COMMENT '数据源名称',
  `url` varchar(255) NOT NULL COMMENT '链接地址',
  `user_name` varchar(100) NOT NULL COMMENT '用户名',
  `pass_word` varchar(100) NOT NULL COMMENT '密码',
  `db_type` int(11) NOT NULL COMMENT '数据库类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='数据源管理';

-- ----------------------------
-- Records of sys_database
-- ----------------------------
INSERT INTO `sys_database` VALUES ('1', '敏捷开发框架数据库2', 'jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8', 'root', 'root', '0');
INSERT INTO `sys_database` VALUES ('3', '123', '23', '3', '41', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(200) NOT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NOT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` int(1) NOT NULL COMMENT '1删除，0未删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('383', '0', '系统管理', '', '', '0', 'fa fa-desktop', '0', '0');
INSERT INTO `sys_menu` VALUES ('384', '0', '开发工具', '', '', '0', 'fa fa-code', '1', '0');
INSERT INTO `sys_menu` VALUES ('385', '383', '用户管理', '/sys/user.html', '', '1', 'fa fa-user', '0', '0');
INSERT INTO `sys_menu` VALUES ('386', '383', '菜单管理', '/sys/menu.html', '', '1', 'fa fa-bars', '1', '0');
INSERT INTO `sys_menu` VALUES ('387', '384', '图标库', '/develop/icon.html', '', '1', 'fa fa-info', '0', '0');
INSERT INTO `sys_menu` VALUES ('388', '384', '数据库文档', '/develop/database.html', '', '1', 'fa fa-database', '1', '0');
INSERT INTO `sys_menu` VALUES ('389', '0', '功能测试', '', '', '0', 'fa fa-bug', '2', '0');
INSERT INTO `sys_menu` VALUES ('390', '389', 'swagger接口测试', '/swagger-ui.html', '', '1', 'fa fa-book', '0', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `pass_word` varchar(50) NOT NULL COMMENT '密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别 1-男、0-女、其他-未知',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `avatar_id` bigint(20) DEFAULT NULL COMMENT '头像ID',
  `department_cid` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `account` varchar(40) DEFAULT NULL COMMENT '用户账号',
  `is_deleted` int(11) NOT NULL COMMENT '状态：1-正常，0-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4', 'string11111', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('5', 'string2', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('6', 'string3', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('8', 'string5', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('9', 'string6', '2018-12-06', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('11', 'string8', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('12', 'string9', '2018-12-05', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('13', 'stringAAAAA', '2018-12-04', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('15', 'stringAAAAAdfdddd', '2018-12-02', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('16', '123', '2019-01-10', '123', '0', '', '', '', null, null, '33331111', '0', null, null, null, null);
INSERT INTO `sys_user` VALUES ('17', '11', null, '222', null, '', '', '', null, null, '1223333', '1', null, null, null, null);
