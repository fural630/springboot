/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-01-15 10:23:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` int(1) DEFAULT NULL COMMENT '1删除，0未删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=383 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '11', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user.html', null, '1', 'fa fa-user', '1', '0');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', null, '1', 'fa fa-user-secret', '2', '0');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu.html', null, '1', 'fa fa-folder-open', '4', '0');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-linux', '9', '0');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'sys/schedule.html', null, '1', 'fa fa-tasks', '5', '0');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '系统参数', 'sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '7', '0');
INSERT INTO `sys_menu` VALUES ('28', '1', '代码生成器', 'sys/generator.html', 'sys:generator:list,sys:generator:code', '1', 'fa fa-rocket', '10', '0');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '8', '0');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'sys/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', '6', '0');
INSERT INTO `sys_menu` VALUES ('31', '0', '功能测试', null, null, '0', 'fa fa-bug', '11', '0');
INSERT INTO `sys_menu` VALUES ('200', '0', '会员管理', null, null, '0', 'fa fa-user-circle-o', '1', '0');
INSERT INTO `sys_menu` VALUES ('201', '200', '会员管理', 'shop/shopuser.html', null, '1', 'fa fa-user-md', '1', '0');
INSERT INTO `sys_menu` VALUES ('202', '201', '查看', null, 'user:list,user:info', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('203', '201', '新增', null, 'user:save', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('204', '201', '修改', null, 'user:update', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('205', '201', '删除', null, 'user:delete', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('206', '31', 'iviewDemo', 'test/iviewDemo.html', null, '1', 'fa fa-etsy', '0', '0');
INSERT INTO `sys_menu` VALUES ('207', '200', '会员等级', 'shop/userlevel.html', null, '1', 'fa fa-star-o', '0', '0');
INSERT INTO `sys_menu` VALUES ('208', '207', '查看', null, 'userlevel:list,userlevel:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('209', '207', '新增', null, 'userlevel:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('210', '207', '修改', null, 'userlevel:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('211', '207', '删除', null, 'userlevel:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('212', '200', '收货地址管理', 'shop/address.html', null, '1', 'fa fa-map-marker', '6', '0');
INSERT INTO `sys_menu` VALUES ('213', '212', '查看', null, 'address:list,address:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('214', '212', '新增', null, 'address:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('215', '212', '修改', null, 'address:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('216', '212', '删除', null, 'address:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('222', '0', '商城配置', null, null, '0', 'fa fa-shopping-cart', '2', '0');
INSERT INTO `sys_menu` VALUES ('223', '222', '商品属性种类', 'shop/attributecategory.html', null, '1', 'fa fa-sun-o', '0', '0');
INSERT INTO `sys_menu` VALUES ('224', '223', '查看', null, 'attributecategory:list,attributecategory:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('225', '223', '新增', null, 'attributecategory:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('226', '223', '修改', null, 'attributecategory:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('227', '223', '删除', null, 'attributecategory:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('233', '243', '广告列表', 'shop/ad.html', null, '1', 'fa fa-pencil', '1', '0');
INSERT INTO `sys_menu` VALUES ('234', '233', '查看', null, 'ad:list,ad:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('235', '233', '新增', null, 'ad:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('236', '233', '修改', null, 'ad:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('237', '233', '删除', null, 'ad:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('238', '243', '广告位置', 'shop/adposition.html', null, '1', 'fa fa-map-pin', '0', '0');
INSERT INTO `sys_menu` VALUES ('239', '238', '查看', null, 'adposition:list,adposition:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('240', '238', '新增', null, 'adposition:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('241', '238', '修改', null, 'adposition:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('242', '238', '删除', null, 'adposition:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('243', '0', '推广管理', null, null, '0', 'fa fa-hand-paper-o', '5', '0');
INSERT INTO `sys_menu` VALUES ('244', '243', '优惠劵管理', 'shop/coupon.html', null, '1', 'fa fa-cc-visa', '2', '0');
INSERT INTO `sys_menu` VALUES ('245', '244', '查看', null, 'coupon:list,coupon:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('246', '244', '新增', null, 'coupon:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('247', '244', '修改', null, 'coupon:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('248', '244', '删除', null, 'coupon:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('249', '200', '会员优惠劵', 'shop/usercoupon.html', null, '1', 'fa fa-cc-visa', '2', '0');
INSERT INTO `sys_menu` VALUES ('250', '249', '查看', null, 'usercoupon:list,usercoupon:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('251', '249', '新增', null, 'usercoupon:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('252', '249', '修改', null, 'usercoupon:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('253', '249', '删除', null, 'usercoupon:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('254', '222', '品牌制造商', 'shop/brand.html', null, '1', 'fa fa-registered', '5', '0');
INSERT INTO `sys_menu` VALUES ('255', '254', '查看', null, 'brand:list,brand:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('256', '254', '新增', null, 'brand:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('257', '254', '修改', null, 'brand:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('258', '254', '删除', null, 'brand:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('259', '222', '商品规格', 'shop/specification.html', null, '1', 'fa fa-hand-rock-o', '1', '0');
INSERT INTO `sys_menu` VALUES ('260', '259', '查看', null, 'specification:list,specification:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('261', '259', '新增', null, 'specification:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('262', '259', '修改', null, 'specification:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('263', '259', '删除', null, 'specification:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('264', '200', '会员收藏', 'shop/collect.html', null, '1', 'fa fa-star', '3', '0');
INSERT INTO `sys_menu` VALUES ('265', '264', '查看', null, 'collect:list,collect:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('266', '264', '删除', null, 'collect:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('270', '243', '专题管理', 'shop/topic.html', null, '1', 'fa fa-ticket', '5', '0');
INSERT INTO `sys_menu` VALUES ('271', '270', '查看', null, 'topic:list,topic:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('272', '270', '新增', null, 'topic:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('273', '270', '修改', null, 'topic:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('274', '270', '删除', null, 'topic:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('275', '243', '专题分类', 'shop/topiccategory.html', null, '1', 'fa fa-tint', '4', '0');
INSERT INTO `sys_menu` VALUES ('276', '275', '查看', null, 'topiccategory:list,topiccategory:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('277', '275', '新增', null, 'topiccategory:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('278', '275', '修改', null, 'topiccategory:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('279', '275', '删除', null, 'topiccategory:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('280', '200', '会员足迹', 'shop/footprint.html', null, '1', 'fa fa-history', '6', '0');
INSERT INTO `sys_menu` VALUES ('281', '280', '查看', null, 'footprint:list,footprint:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('282', '280', '删除', null, 'footprint:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('283', '200', '搜索历史', 'shop/searchhistory.html', null, '1', 'fa fa-search', '6', '0');
INSERT INTO `sys_menu` VALUES ('284', '283', '查看', null, 'searchhistory:list,searchhistory:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('285', '283', '删除', null, 'searchhistory:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('286', '200', '购物车', 'shop/cart.html', null, '1', 'fa fa-shopping-cart', '6', '0');
INSERT INTO `sys_menu` VALUES ('287', '286', '查看', null, 'cart:list,cart:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('288', '286', '删除', null, 'cart:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('289', '357', '所有商品', 'shop/goods.html', null, '1', 'fa fa-shopping-bag', '1', '0');
INSERT INTO `sys_menu` VALUES ('290', '289', '查看', null, 'goods:list,goods:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('291', '289', '新增', null, 'goods:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('292', '289', '修改', null, 'goods:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('293', '289', '删除', null, 'goods:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('294', '374', '所有管理', 'shop/order.html', null, '1', 'fa fa-sitemap', '6', '0');
INSERT INTO `sys_menu` VALUES ('295', '294', '查看', null, 'order:list,order:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('296', '294', '发货', null, 'order:sendGoods', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('297', '222', '商品类型', 'shop/category.html', null, '1', 'fa fa-ship', '3', '0');
INSERT INTO `sys_menu` VALUES ('298', '297', '查看', null, 'category:list,category:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('299', '297', '新增', null, 'category:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('300', '297', '修改', null, 'category:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('301', '297', '删除', null, 'category:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('302', '1', '通用字典表', 'sys/macro.html', null, '1', 'fa fa-book', '6', '0');
INSERT INTO `sys_menu` VALUES ('303', '302', '查看', null, 'sys:macro:list,sys:macro:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('304', '302', '新增', null, 'sys:macro:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('305', '302', '修改', null, 'sys:macro:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('306', '302', '删除', null, 'sys:macro:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('307', '222', '渠道管理', 'shop/channel.html', null, '1', 'fa fa-road', '2', '0');
INSERT INTO `sys_menu` VALUES ('308', '307', '查看', null, 'channel:list,channel:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('309', '307', '新增', null, 'channel:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('310', '307', '修改', null, 'channel:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('311', '307', '删除', null, 'channel:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('312', '0', '微信公众号', null, null, '0', 'fa fa-weixin', '6', '1');
INSERT INTO `sys_menu` VALUES ('313', '0', '进销存2', null, null, '0', 'fa fa-truck', '7', '0');
INSERT INTO `sys_menu` VALUES ('314', '0', '统计报表', null, null, '0', 'fa fa-line-chart', '7', '0');
INSERT INTO `sys_menu` VALUES ('315', '222', '商品问答', 'shop/goodsissue.html', null, '1', 'fa fa-question-circle-o', '6', '0');
INSERT INTO `sys_menu` VALUES ('316', '315', '查看', null, 'goodsissue:list,goodsissue:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('317', '315', '新增', null, 'goodsissue:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('318', '315', '修改', null, 'goodsissue:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('319', '315', '删除', null, 'goodsissue:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('325', '222', '反馈', 'shop/feedback.html', null, '1', 'fa fa-mail-reply-all', '6', '0');
INSERT INTO `sys_menu` VALUES ('326', '325', '查看', null, 'feedback:list,feedback:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('327', '325', '新增', null, 'feedback:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('328', '325', '修改', null, 'feedback:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('329', '325', '删除', null, 'feedback:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('330', '244', '发放', null, 'coupon:publish', '2', null, '4', '0');
INSERT INTO `sys_menu` VALUES ('331', '222', '关键词', 'shop/keywords.html', null, '1', 'fa fa-underline', '6', '0');
INSERT INTO `sys_menu` VALUES ('332', '331', '查看', null, 'keywords:list,keywords:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('333', '331', '新增', null, 'keywords:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('334', '331', '修改', null, 'keywords:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('335', '331', '删除', null, 'keywords:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('336', '357', '用户评论', 'shop/comment.html', null, '1', 'fa fa-commenting', '6', '0');
INSERT INTO `sys_menu` VALUES ('337', '336', '查看', null, 'comment:list,comment:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('338', '336', '新增', null, 'comment:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('339', '336', '修改', null, 'comment:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('340', '336', '删除', null, 'comment:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('341', '336', '修改状态', null, 'comment:toggleStatus', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('342', '357', '产品设置', 'shop/product.html', null, '1', 'fa fa-paypal', '3', '0');
INSERT INTO `sys_menu` VALUES ('343', '342', '查看', null, 'product:list,product:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('344', '342', '新增', null, 'product:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('345', '342', '修改', null, 'product:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('346', '342', '删除', null, 'product:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('352', '357', '商品规格', 'shop/goodsspecification.html', null, '1', 'fa fa-deafness', '2', '0');
INSERT INTO `sys_menu` VALUES ('353', '352', '查看', null, 'goodsspecification:list,goodsspecification:info', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('354', '352', '新增', null, 'goodsspecification:save', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('355', '352', '修改', null, 'goodsspecification:update', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('356', '352', '删除', null, 'goodsspecification:delete', '2', null, '6', '0');
INSERT INTO `sys_menu` VALUES ('357', '0', '编辑商品', null, null, '0', 'fa fa-edit', '3', '0');
INSERT INTO `sys_menu` VALUES ('358', '357', '商品回收站', 'shop/goodshistory.html', '', '1', 'fa fa-history', '12', '0');
INSERT INTO `sys_menu` VALUES ('359', '358', '恢复', null, 'goods:back', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('360', '294', '确认收货', null, 'order:confirm', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('366', '0', 'CMS模块', null, null, '0', 'fa fa-leanpub', '6', '0');
INSERT INTO `sys_menu` VALUES ('368', '1', '部门管理', 'sys/dept.html', null, '1', 'fa fa-sitemap', '3', '0');
INSERT INTO `sys_menu` VALUES ('369', '368', '查看', null, 'sys:dept:list,sys:dept:info', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('370', '368', '新增', null, 'sys:dept:save', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('371', '368', '修改', null, 'sys:dept:update', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('372', '368', '删除', null, 'sys:dept:delete', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('373', '368', '选择部门', null, 'sys:dept:select', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('374', '0', '订单管理', null, null, '0', 'fa fa-first-order', '4', '0');
INSERT INTO `sys_menu` VALUES ('375', '0', '短信平台', null, null, '0', 'fa fa-television', '9', '0');
INSERT INTO `sys_menu` VALUES ('376', '375', '短信配置', 'sys/smslog.html', 'sys:smslog:list,sys:smslog:info', '1', 'fa fa-envelope-open', '1', '0');
INSERT INTO `sys_menu` VALUES ('377', '1', '地区管理', 'sys/region.html', null, '1', 'fa fa-map-pin', '8', '0');
INSERT INTO `sys_menu` VALUES ('378', '377', '删除', null, 'sys:region:delete', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('379', '377', '修改', '', 'sys:region:update', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('380', '377', '新增', null, 'sys:region:save', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('381', '377', '查看', null, 'sys:region:list,sys:region:info', '2', null, '0', '0');
INSERT INTO `sys_menu` VALUES ('382', '31', 'swagger-ui.html', 'swagger-ui.html', '', '1', 'fa fa-code', '0', '0');

-- ----------------------------
-- Table structure for sys_region
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region` (
  `REGION_ID` varchar(40) NOT NULL,
  `REGION_NAME` varchar(50) NOT NULL,
  `PARENT_ID` varchar(40) NOT NULL,
  `TYPE` tinyint(4) NOT NULL,
  `AGENCY_CODE` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`REGION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_region
-- ----------------------------
INSERT INTO `sys_region` VALUES ('1', '中国', '-1', '0', '0');
INSERT INTO `sys_region` VALUES ('100', '浦北县', '27', '3', '450722');
INSERT INTO `sys_region` VALUES ('101', '市辖区', '28', '3', '450801');
INSERT INTO `sys_region` VALUES ('102', '港北区', '28', '3', '450802');
INSERT INTO `sys_region` VALUES ('103', '港南区', '28', '3', '450803');
INSERT INTO `sys_region` VALUES ('104', '覃塘区', '28', '3', '450804');
INSERT INTO `sys_region` VALUES ('105', '平南县', '28', '3', '450821');
INSERT INTO `sys_region` VALUES ('106', '桂平市', '28', '3', '450881');
INSERT INTO `sys_region` VALUES ('107', '市辖区', '29', '3', '450901');
INSERT INTO `sys_region` VALUES ('108', '玉州区', '29', '3', '450902');
INSERT INTO `sys_region` VALUES ('109', '福绵区', '29', '3', '450903');
INSERT INTO `sys_region` VALUES ('110', '容县', '29', '3', '450921');
INSERT INTO `sys_region` VALUES ('111', '陆川县', '29', '3', '450922');
INSERT INTO `sys_region` VALUES ('112', '博白县', '29', '3', '450923');
INSERT INTO `sys_region` VALUES ('113', '兴业县', '29', '3', '450924');
INSERT INTO `sys_region` VALUES ('114', '北流市', '29', '3', '450981');
INSERT INTO `sys_region` VALUES ('115', '市辖区', '30', '3', '451001');
INSERT INTO `sys_region` VALUES ('116', '右江区', '30', '3', '451002');
INSERT INTO `sys_region` VALUES ('117', '田阳县', '30', '3', '451021');
INSERT INTO `sys_region` VALUES ('118', '田东县', '30', '3', '451022');
INSERT INTO `sys_region` VALUES ('119', '平果县', '30', '3', '451023');
INSERT INTO `sys_region` VALUES ('120', '德保县', '30', '3', '451024');
INSERT INTO `sys_region` VALUES ('121', '那坡县', '30', '3', '451026');
INSERT INTO `sys_region` VALUES ('122', '凌云县', '30', '3', '451027');
INSERT INTO `sys_region` VALUES ('123', '乐业县', '30', '3', '451028');
INSERT INTO `sys_region` VALUES ('124', '田林县', '30', '3', '451029');
INSERT INTO `sys_region` VALUES ('125', '西林县', '30', '3', '451030');
INSERT INTO `sys_region` VALUES ('126', '隆林各族自治县', '30', '3', '451031');
INSERT INTO `sys_region` VALUES ('127', '靖西市', '30', '3', '451081');
INSERT INTO `sys_region` VALUES ('128', '市辖区', '31', '3', '451101');
INSERT INTO `sys_region` VALUES ('129', '八步区', '31', '3', '451102');
INSERT INTO `sys_region` VALUES ('130', '平桂区', '31', '3', '451103');
INSERT INTO `sys_region` VALUES ('131', '昭平县', '31', '3', '451121');
INSERT INTO `sys_region` VALUES ('132', '钟山县', '31', '3', '451122');
INSERT INTO `sys_region` VALUES ('133', '富川瑶族自治县', '31', '3', '451123');
INSERT INTO `sys_region` VALUES ('134', '市辖区', '32', '3', '451201');
INSERT INTO `sys_region` VALUES ('135', '金城江区', '32', '3', '451202');
INSERT INTO `sys_region` VALUES ('136', '宜州区', '32', '3', '451203');
INSERT INTO `sys_region` VALUES ('137', '南丹县', '32', '3', '451221');
INSERT INTO `sys_region` VALUES ('138', '天峨县', '32', '3', '451222');
INSERT INTO `sys_region` VALUES ('139', '凤山县', '32', '3', '451223');
INSERT INTO `sys_region` VALUES ('140', '东兰县', '32', '3', '451224');
INSERT INTO `sys_region` VALUES ('141', '罗城仫佬族自治县', '32', '3', '451225');
INSERT INTO `sys_region` VALUES ('142', '环江毛南族自治县', '32', '3', '451226');
INSERT INTO `sys_region` VALUES ('143', '巴马瑶族自治县', '32', '3', '451227');
INSERT INTO `sys_region` VALUES ('144', '都安瑶族自治县', '32', '3', '451228');
INSERT INTO `sys_region` VALUES ('145', '大化瑶族自治县', '32', '3', '451229');
INSERT INTO `sys_region` VALUES ('146', '市辖区', '33', '3', '451301');
INSERT INTO `sys_region` VALUES ('147', '兴宾区', '33', '3', '451302');
INSERT INTO `sys_region` VALUES ('148', '忻城县', '33', '3', '451321');
INSERT INTO `sys_region` VALUES ('149', '象州县', '33', '3', '451322');
INSERT INTO `sys_region` VALUES ('150', '武宣县', '33', '3', '451323');
INSERT INTO `sys_region` VALUES ('151', '金秀瑶族自治县', '33', '3', '451324');
INSERT INTO `sys_region` VALUES ('152', '合山市', '33', '3', '451381');
INSERT INTO `sys_region` VALUES ('153', '市辖区', '34', '3', '451401');
INSERT INTO `sys_region` VALUES ('154', '江州区', '34', '3', '451402');
INSERT INTO `sys_region` VALUES ('155', '扶绥县', '34', '3', '451421');
INSERT INTO `sys_region` VALUES ('156', '宁明县', '34', '3', '451422');
INSERT INTO `sys_region` VALUES ('157', '龙州县', '34', '3', '451423');
INSERT INTO `sys_region` VALUES ('158', '大新县', '34', '3', '451424');
INSERT INTO `sys_region` VALUES ('159', '天等县', '34', '3', '451425');
INSERT INTO `sys_region` VALUES ('160', '凭祥市', '34', '3', '451481');
INSERT INTO `sys_region` VALUES ('2', '广西壮族自治区', '1', '1', '45');
INSERT INTO `sys_region` VALUES ('21', '南宁市', '2', '2', '4501');
INSERT INTO `sys_region` VALUES ('22', '柳州市', '2', '2', '4502');
INSERT INTO `sys_region` VALUES ('23', '桂林市', '2', '2', '4503');
INSERT INTO `sys_region` VALUES ('24', '梧州市', '2', '2', '4504');
INSERT INTO `sys_region` VALUES ('25', '北海市', '2', '2', '4505');
INSERT INTO `sys_region` VALUES ('26', '防城港市', '2', '2', '4506');
INSERT INTO `sys_region` VALUES ('27', '钦州市', '2', '2', '4507');
INSERT INTO `sys_region` VALUES ('28', '贵港市', '2', '2', '4508');
INSERT INTO `sys_region` VALUES ('29', '玉林市', '2', '2', '4509');
INSERT INTO `sys_region` VALUES ('30', '百色市', '2', '2', '4510');
INSERT INTO `sys_region` VALUES ('31', '贺州市', '2', '2', '4511');
INSERT INTO `sys_region` VALUES ('32', '河池市', '2', '2', '4512');
INSERT INTO `sys_region` VALUES ('33', '来宾市', '2', '2', '4513');
INSERT INTO `sys_region` VALUES ('34', '崇左市', '2', '2', '4514');
INSERT INTO `sys_region` VALUES ('35', '市辖区', '21', '3', '450101');
INSERT INTO `sys_region` VALUES ('36', '兴宁区', '21', '3', '450102');
INSERT INTO `sys_region` VALUES ('37', '青秀区', '21', '3', '450103');
INSERT INTO `sys_region` VALUES ('38', '江南区', '21', '3', '450105');
INSERT INTO `sys_region` VALUES ('39', '西乡塘区', '21', '3', '450107');
INSERT INTO `sys_region` VALUES ('40', '良庆区', '21', '3', '450108');
INSERT INTO `sys_region` VALUES ('41', '邕宁区', '21', '3', '450109');
INSERT INTO `sys_region` VALUES ('42', '武鸣区', '21', '3', '450110');
INSERT INTO `sys_region` VALUES ('43', '隆安县', '21', '3', '450123');
INSERT INTO `sys_region` VALUES ('44', '马山县', '21', '3', '450124');
INSERT INTO `sys_region` VALUES ('45', '上林县', '21', '3', '450125');
INSERT INTO `sys_region` VALUES ('46', '宾阳县', '21', '3', '450126');
INSERT INTO `sys_region` VALUES ('47', '横县', '21', '3', '450127');
INSERT INTO `sys_region` VALUES ('48', '市辖区', '22', '3', '450201');
INSERT INTO `sys_region` VALUES ('49', '城中区', '22', '3', '450202');
INSERT INTO `sys_region` VALUES ('50', '鱼峰区', '22', '3', '450203');
INSERT INTO `sys_region` VALUES ('51', '柳南区', '22', '3', '450204');
INSERT INTO `sys_region` VALUES ('52', '柳北区', '22', '3', '450205');
INSERT INTO `sys_region` VALUES ('53', '柳江区', '22', '3', '450206');
INSERT INTO `sys_region` VALUES ('54', '柳城县', '22', '3', '450222');
INSERT INTO `sys_region` VALUES ('55', '鹿寨县', '22', '3', '450223');
INSERT INTO `sys_region` VALUES ('56', '融安县', '22', '3', '450224');
INSERT INTO `sys_region` VALUES ('57', '融水苗族自治县', '22', '3', '450225');
INSERT INTO `sys_region` VALUES ('58', '三江侗族自治县', '22', '3', '450226');
INSERT INTO `sys_region` VALUES ('59', '市辖区', '23', '3', '450301');
INSERT INTO `sys_region` VALUES ('60', '秀峰区', '23', '3', '450302');
INSERT INTO `sys_region` VALUES ('61', '叠彩区', '23', '3', '450303');
INSERT INTO `sys_region` VALUES ('62', '象山区', '23', '3', '450304');
INSERT INTO `sys_region` VALUES ('63', '七星区', '23', '3', '450305');
INSERT INTO `sys_region` VALUES ('64', '雁山区', '23', '3', '450311');
INSERT INTO `sys_region` VALUES ('65', '临桂区', '23', '3', '450312');
INSERT INTO `sys_region` VALUES ('66', '阳朔县', '23', '3', '450321');
INSERT INTO `sys_region` VALUES ('67', '灵川县', '23', '3', '450323');
INSERT INTO `sys_region` VALUES ('68', '全州县', '23', '3', '450324');
INSERT INTO `sys_region` VALUES ('69', '兴安县', '23', '3', '450325');
INSERT INTO `sys_region` VALUES ('70', '永福县', '23', '3', '450326');
INSERT INTO `sys_region` VALUES ('71', '灌阳县', '23', '3', '450327');
INSERT INTO `sys_region` VALUES ('72', '龙胜各族自治县', '23', '3', '450328');
INSERT INTO `sys_region` VALUES ('73', '资源县', '23', '3', '450329');
INSERT INTO `sys_region` VALUES ('74', '平乐县', '23', '3', '450330');
INSERT INTO `sys_region` VALUES ('75', '荔浦县', '23', '3', '450331');
INSERT INTO `sys_region` VALUES ('76', '恭城瑶族自治县', '23', '3', '450332');
INSERT INTO `sys_region` VALUES ('77', '市辖区', '24', '3', '450401');
INSERT INTO `sys_region` VALUES ('78', '万秀区', '24', '3', '450403');
INSERT INTO `sys_region` VALUES ('79', '长洲区', '24', '3', '450405');
INSERT INTO `sys_region` VALUES ('80', '龙圩区', '24', '3', '450406');
INSERT INTO `sys_region` VALUES ('81', '苍梧县', '24', '3', '450421');
INSERT INTO `sys_region` VALUES ('82', '藤县', '24', '3', '450422');
INSERT INTO `sys_region` VALUES ('83', '蒙山县', '24', '3', '450423');
INSERT INTO `sys_region` VALUES ('84', '岑溪市', '24', '3', '450481');
INSERT INTO `sys_region` VALUES ('86', '市辖区', '25', '3', '450501');
INSERT INTO `sys_region` VALUES ('87', '海城区', '25', '3', '450502');
INSERT INTO `sys_region` VALUES ('88', '银海区', '25', '3', '450503');
INSERT INTO `sys_region` VALUES ('89', '铁山港区', '25', '3', '450512');
INSERT INTO `sys_region` VALUES ('90', '合浦县', '25', '3', '450521');
INSERT INTO `sys_region` VALUES ('91', '市辖区', '26', '3', '450601');
INSERT INTO `sys_region` VALUES ('92', '港口区', '26', '3', '450602');
INSERT INTO `sys_region` VALUES ('93', '防城区', '26', '3', '450603');
INSERT INTO `sys_region` VALUES ('94', '上思县', '26', '3', '450621');
INSERT INTO `sys_region` VALUES ('95', '东兴市', '26', '3', '450681');
INSERT INTO `sys_region` VALUES ('96', '市辖区', '27', '3', '450701');
INSERT INTO `sys_region` VALUES ('97', '钦南区', '27', '3', '450702');
INSERT INTO `sys_region` VALUES ('98', '钦北区', '27', '3', '450703');
INSERT INTO `sys_region` VALUES ('99', '灵山县', '27', '3', '450721');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `pass_word` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别 1-男、0-女、其他-未知',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `avatar_id` bigint(20) DEFAULT NULL COMMENT '头像ID',
  `department_cid` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `account` varchar(40) DEFAULT NULL COMMENT '用户账号',
  `status` int(11) DEFAULT NULL COMMENT '状态：1-正常，0-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '张三', '2018-12-08', 'aaa', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', '阿甘', '2018-12-08', 'string', '1', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 21:37:36', '2018-12-08 21:37:36', '0', '0');
INSERT INTO `sys_user` VALUES ('4', 'string1', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('5', 'string2', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('6', 'string3', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('8', 'string5', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('9', 'string6', '2018-12-06', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('11', 'string8', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('12', 'string9', '2018-12-05', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('13', 'stringAAAAA', '2018-12-04', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('14', 'stringAAAAAdfdddd', '2018-12-03', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('15', 'stringAAAAAdfdddd', '2018-12-02', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('16', 'stringAAAAAdfdddd', '2018-12-01', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('18', '123222', null, null, '1', '', '', '11', null, null, '1222', '1', null, null, null, null);
