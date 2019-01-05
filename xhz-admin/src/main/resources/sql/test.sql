/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2018-12-10 23:33:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bazx_bary_baryxx
-- ----------------------------
DROP TABLE IF EXISTS `bazx_bary_baryxx`;
CREATE TABLE `bazx_bary_baryxx` (
  `ID` varchar(40) NOT NULL,
  `ZFZH` varchar(40) default NULL,
  `ZJYXJZRQ` date default NULL,
  `ZJYXKSRQ` date default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bazx_bary_baryxx
-- ----------------------------

-- ----------------------------
-- Table structure for sys_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_test`;
CREATE TABLE `sys_test` (
  `id` int(11) NOT NULL,
  `name` varchar(255) default NULL,
  `is_delete` tinyint(4) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_test
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '用户ID',
  `name` varchar(20) default NULL COMMENT '用户名',
  `birth_day` date default NULL COMMENT '出生日期',
  `pass_word` varchar(50) default NULL COMMENT '密码',
  `sex` int(11) default NULL COMMENT '性别 1-男、0-女、其他-未知',
  `email` varchar(50) default NULL COMMENT '邮箱',
  `phone` varchar(30) default NULL COMMENT '手机号',
  `id_card` varchar(20) default NULL COMMENT '身份证号',
  `avatar_id` bigint(20) default NULL COMMENT '头像ID',
  `department_cid` bigint(20) default NULL COMMENT '部门ID',
  `account` varchar(40) default NULL COMMENT '用户账号',
  `status` int(11) default NULL COMMENT '状态：1-正常，0-删除',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_time` datetime default NULL COMMENT '修改时间',
  `create_user_id` bigint(20) default NULL COMMENT '创建人ID',
  `update_user_id` bigint(20) default NULL COMMENT '修改人ID',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '张三', '2018-12-08', 'aaa', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', '阿甘', '2018-12-08', 'string', '1', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 21:37:36', '2018-12-08 21:37:36', '0', '0');
INSERT INTO `sys_user` VALUES ('4', 'string1', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('5', 'string2', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('6', 'string3', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '1', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('7', 'string4', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('8', 'string5', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('9', 'string6', '2018-12-06', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('10', 'string7', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('11', 'string8', '2018-12-08', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');
INSERT INTO `sys_user` VALUES ('12', 'string9', '2018-12-05', 'string', '0', 'string', 'string', 'string', '0', '0', 'string', '0', '2018-12-08 22:25:58', '2018-12-08 22:25:58', '0', '0');

-- ----------------------------
-- Table structure for xt_region
-- ----------------------------
DROP TABLE IF EXISTS `xt_region`;
CREATE TABLE `xt_region` (
  `REGION_ID` varchar(40) NOT NULL,
  `REGION_NAME` varchar(50) NOT NULL,
  `PARENT_ID` varchar(40) NOT NULL,
  `TYPE` tinyint(4) NOT NULL,
  `AGENCY_CODE` varchar(12) default NULL,
  PRIMARY KEY  (`REGION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_region
-- ----------------------------
INSERT INTO `xt_region` VALUES ('1', '中国', '-1', '0', '0');
INSERT INTO `xt_region` VALUES ('100', '浦北县', '27', '3', '450722');
INSERT INTO `xt_region` VALUES ('101', '市辖区', '28', '3', '450801');
INSERT INTO `xt_region` VALUES ('102', '港北区', '28', '3', '450802');
INSERT INTO `xt_region` VALUES ('103', '港南区', '28', '3', '450803');
INSERT INTO `xt_region` VALUES ('104', '覃塘区', '28', '3', '450804');
INSERT INTO `xt_region` VALUES ('105', '平南县', '28', '3', '450821');
INSERT INTO `xt_region` VALUES ('106', '桂平市', '28', '3', '450881');
INSERT INTO `xt_region` VALUES ('107', '市辖区', '29', '3', '450901');
INSERT INTO `xt_region` VALUES ('108', '玉州区', '29', '3', '450902');
INSERT INTO `xt_region` VALUES ('109', '福绵区', '29', '3', '450903');
INSERT INTO `xt_region` VALUES ('110', '容县', '29', '3', '450921');
INSERT INTO `xt_region` VALUES ('111', '陆川县', '29', '3', '450922');
INSERT INTO `xt_region` VALUES ('112', '博白县', '29', '3', '450923');
INSERT INTO `xt_region` VALUES ('113', '兴业县', '29', '3', '450924');
INSERT INTO `xt_region` VALUES ('114', '北流市', '29', '3', '450981');
INSERT INTO `xt_region` VALUES ('115', '市辖区', '30', '3', '451001');
INSERT INTO `xt_region` VALUES ('116', '右江区', '30', '3', '451002');
INSERT INTO `xt_region` VALUES ('117', '田阳县', '30', '3', '451021');
INSERT INTO `xt_region` VALUES ('118', '田东县', '30', '3', '451022');
INSERT INTO `xt_region` VALUES ('119', '平果县', '30', '3', '451023');
INSERT INTO `xt_region` VALUES ('120', '德保县', '30', '3', '451024');
INSERT INTO `xt_region` VALUES ('121', '那坡县', '30', '3', '451026');
INSERT INTO `xt_region` VALUES ('122', '凌云县', '30', '3', '451027');
INSERT INTO `xt_region` VALUES ('123', '乐业县', '30', '3', '451028');
INSERT INTO `xt_region` VALUES ('124', '田林县', '30', '3', '451029');
INSERT INTO `xt_region` VALUES ('125', '西林县', '30', '3', '451030');
INSERT INTO `xt_region` VALUES ('126', '隆林各族自治县', '30', '3', '451031');
INSERT INTO `xt_region` VALUES ('127', '靖西市', '30', '3', '451081');
INSERT INTO `xt_region` VALUES ('128', '市辖区', '31', '3', '451101');
INSERT INTO `xt_region` VALUES ('129', '八步区', '31', '3', '451102');
INSERT INTO `xt_region` VALUES ('130', '平桂区', '31', '3', '451103');
INSERT INTO `xt_region` VALUES ('131', '昭平县', '31', '3', '451121');
INSERT INTO `xt_region` VALUES ('132', '钟山县', '31', '3', '451122');
INSERT INTO `xt_region` VALUES ('133', '富川瑶族自治县', '31', '3', '451123');
INSERT INTO `xt_region` VALUES ('134', '市辖区', '32', '3', '451201');
INSERT INTO `xt_region` VALUES ('135', '金城江区', '32', '3', '451202');
INSERT INTO `xt_region` VALUES ('136', '宜州区', '32', '3', '451203');
INSERT INTO `xt_region` VALUES ('137', '南丹县', '32', '3', '451221');
INSERT INTO `xt_region` VALUES ('138', '天峨县', '32', '3', '451222');
INSERT INTO `xt_region` VALUES ('139', '凤山县', '32', '3', '451223');
INSERT INTO `xt_region` VALUES ('140', '东兰县', '32', '3', '451224');
INSERT INTO `xt_region` VALUES ('141', '罗城仫佬族自治县', '32', '3', '451225');
INSERT INTO `xt_region` VALUES ('142', '环江毛南族自治县', '32', '3', '451226');
INSERT INTO `xt_region` VALUES ('143', '巴马瑶族自治县', '32', '3', '451227');
INSERT INTO `xt_region` VALUES ('144', '都安瑶族自治县', '32', '3', '451228');
INSERT INTO `xt_region` VALUES ('145', '大化瑶族自治县', '32', '3', '451229');
INSERT INTO `xt_region` VALUES ('146', '市辖区', '33', '3', '451301');
INSERT INTO `xt_region` VALUES ('147', '兴宾区', '33', '3', '451302');
INSERT INTO `xt_region` VALUES ('148', '忻城县', '33', '3', '451321');
INSERT INTO `xt_region` VALUES ('149', '象州县', '33', '3', '451322');
INSERT INTO `xt_region` VALUES ('150', '武宣县', '33', '3', '451323');
INSERT INTO `xt_region` VALUES ('151', '金秀瑶族自治县', '33', '3', '451324');
INSERT INTO `xt_region` VALUES ('152', '合山市', '33', '3', '451381');
INSERT INTO `xt_region` VALUES ('153', '市辖区', '34', '3', '451401');
INSERT INTO `xt_region` VALUES ('154', '江州区', '34', '3', '451402');
INSERT INTO `xt_region` VALUES ('155', '扶绥县', '34', '3', '451421');
INSERT INTO `xt_region` VALUES ('156', '宁明县', '34', '3', '451422');
INSERT INTO `xt_region` VALUES ('157', '龙州县', '34', '3', '451423');
INSERT INTO `xt_region` VALUES ('158', '大新县', '34', '3', '451424');
INSERT INTO `xt_region` VALUES ('159', '天等县', '34', '3', '451425');
INSERT INTO `xt_region` VALUES ('160', '凭祥市', '34', '3', '451481');
INSERT INTO `xt_region` VALUES ('2', '广西壮族自治区', '1', '1', '45');
INSERT INTO `xt_region` VALUES ('21', '南宁市', '2', '2', '4501');
INSERT INTO `xt_region` VALUES ('22', '柳州市', '2', '2', '4502');
INSERT INTO `xt_region` VALUES ('23', '桂林市', '2', '2', '4503');
INSERT INTO `xt_region` VALUES ('24', '梧州市', '2', '2', '4504');
INSERT INTO `xt_region` VALUES ('25', '北海市', '2', '2', '4505');
INSERT INTO `xt_region` VALUES ('26', '防城港市', '2', '2', '4506');
INSERT INTO `xt_region` VALUES ('27', '钦州市', '2', '2', '4507');
INSERT INTO `xt_region` VALUES ('28', '贵港市', '2', '2', '4508');
INSERT INTO `xt_region` VALUES ('29', '玉林市', '2', '2', '4509');
INSERT INTO `xt_region` VALUES ('30', '百色市', '2', '2', '4510');
INSERT INTO `xt_region` VALUES ('31', '贺州市', '2', '2', '4511');
INSERT INTO `xt_region` VALUES ('32', '河池市', '2', '2', '4512');
INSERT INTO `xt_region` VALUES ('33', '来宾市', '2', '2', '4513');
INSERT INTO `xt_region` VALUES ('34', '崇左市', '2', '2', '4514');
INSERT INTO `xt_region` VALUES ('35', '市辖区', '21', '3', '450101');
INSERT INTO `xt_region` VALUES ('36', '兴宁区', '21', '3', '450102');
INSERT INTO `xt_region` VALUES ('37', '青秀区', '21', '3', '450103');
INSERT INTO `xt_region` VALUES ('38', '江南区', '21', '3', '450105');
INSERT INTO `xt_region` VALUES ('39', '西乡塘区', '21', '3', '450107');
INSERT INTO `xt_region` VALUES ('40', '良庆区', '21', '3', '450108');
INSERT INTO `xt_region` VALUES ('41', '邕宁区', '21', '3', '450109');
INSERT INTO `xt_region` VALUES ('42', '武鸣区', '21', '3', '450110');
INSERT INTO `xt_region` VALUES ('43', '隆安县', '21', '3', '450123');
INSERT INTO `xt_region` VALUES ('44', '马山县', '21', '3', '450124');
INSERT INTO `xt_region` VALUES ('45', '上林县', '21', '3', '450125');
INSERT INTO `xt_region` VALUES ('46', '宾阳县', '21', '3', '450126');
INSERT INTO `xt_region` VALUES ('47', '横县', '21', '3', '450127');
INSERT INTO `xt_region` VALUES ('48', '市辖区', '22', '3', '450201');
INSERT INTO `xt_region` VALUES ('49', '城中区', '22', '3', '450202');
INSERT INTO `xt_region` VALUES ('50', '鱼峰区', '22', '3', '450203');
INSERT INTO `xt_region` VALUES ('51', '柳南区', '22', '3', '450204');
INSERT INTO `xt_region` VALUES ('52', '柳北区', '22', '3', '450205');
INSERT INTO `xt_region` VALUES ('53', '柳江区', '22', '3', '450206');
INSERT INTO `xt_region` VALUES ('54', '柳城县', '22', '3', '450222');
INSERT INTO `xt_region` VALUES ('55', '鹿寨县', '22', '3', '450223');
INSERT INTO `xt_region` VALUES ('56', '融安县', '22', '3', '450224');
INSERT INTO `xt_region` VALUES ('57', '融水苗族自治县', '22', '3', '450225');
INSERT INTO `xt_region` VALUES ('58', '三江侗族自治县', '22', '3', '450226');
INSERT INTO `xt_region` VALUES ('59', '市辖区', '23', '3', '450301');
INSERT INTO `xt_region` VALUES ('60', '秀峰区', '23', '3', '450302');
INSERT INTO `xt_region` VALUES ('61', '叠彩区', '23', '3', '450303');
INSERT INTO `xt_region` VALUES ('62', '象山区', '23', '3', '450304');
INSERT INTO `xt_region` VALUES ('63', '七星区', '23', '3', '450305');
INSERT INTO `xt_region` VALUES ('64', '雁山区', '23', '3', '450311');
INSERT INTO `xt_region` VALUES ('65', '临桂区', '23', '3', '450312');
INSERT INTO `xt_region` VALUES ('66', '阳朔县', '23', '3', '450321');
INSERT INTO `xt_region` VALUES ('67', '灵川县', '23', '3', '450323');
INSERT INTO `xt_region` VALUES ('68', '全州县', '23', '3', '450324');
INSERT INTO `xt_region` VALUES ('69', '兴安县', '23', '3', '450325');
INSERT INTO `xt_region` VALUES ('70', '永福县', '23', '3', '450326');
INSERT INTO `xt_region` VALUES ('71', '灌阳县', '23', '3', '450327');
INSERT INTO `xt_region` VALUES ('72', '龙胜各族自治县', '23', '3', '450328');
INSERT INTO `xt_region` VALUES ('73', '资源县', '23', '3', '450329');
INSERT INTO `xt_region` VALUES ('74', '平乐县', '23', '3', '450330');
INSERT INTO `xt_region` VALUES ('75', '荔浦县', '23', '3', '450331');
INSERT INTO `xt_region` VALUES ('76', '恭城瑶族自治县', '23', '3', '450332');
INSERT INTO `xt_region` VALUES ('77', '市辖区', '24', '3', '450401');
INSERT INTO `xt_region` VALUES ('78', '万秀区', '24', '3', '450403');
INSERT INTO `xt_region` VALUES ('79', '长洲区', '24', '3', '450405');
INSERT INTO `xt_region` VALUES ('80', '龙圩区', '24', '3', '450406');
INSERT INTO `xt_region` VALUES ('81', '苍梧县', '24', '3', '450421');
INSERT INTO `xt_region` VALUES ('82', '藤县', '24', '3', '450422');
INSERT INTO `xt_region` VALUES ('83', '蒙山县', '24', '3', '450423');
INSERT INTO `xt_region` VALUES ('84', '岑溪市', '24', '3', '450481');
INSERT INTO `xt_region` VALUES ('86', '市辖区', '25', '3', '450501');
INSERT INTO `xt_region` VALUES ('87', '海城区', '25', '3', '450502');
INSERT INTO `xt_region` VALUES ('88', '银海区', '25', '3', '450503');
INSERT INTO `xt_region` VALUES ('89', '铁山港区', '25', '3', '450512');
INSERT INTO `xt_region` VALUES ('90', '合浦县', '25', '3', '450521');
INSERT INTO `xt_region` VALUES ('91', '市辖区', '26', '3', '450601');
INSERT INTO `xt_region` VALUES ('92', '港口区', '26', '3', '450602');
INSERT INTO `xt_region` VALUES ('93', '防城区', '26', '3', '450603');
INSERT INTO `xt_region` VALUES ('94', '上思县', '26', '3', '450621');
INSERT INTO `xt_region` VALUES ('95', '东兴市', '26', '3', '450681');
INSERT INTO `xt_region` VALUES ('96', '市辖区', '27', '3', '450701');
INSERT INTO `xt_region` VALUES ('97', '钦南区', '27', '3', '450702');
INSERT INTO `xt_region` VALUES ('98', '钦北区', '27', '3', '450703');
INSERT INTO `xt_region` VALUES ('99', '灵山县', '27', '3', '450721');

-- ----------------------------
-- Table structure for xt_zzjg_czry
-- ----------------------------
DROP TABLE IF EXISTS `xt_zzjg_czry`;
CREATE TABLE `xt_zzjg_czry` (
  `CZRY_ID` varchar(40) NOT NULL,
  `CZRY_DM` varchar(30) default NULL,
  `CZRY_MC` varchar(20) default NULL,
  `CZRY_PASS` varchar(50) default NULL,
  `SZBM_ID` varchar(40) default NULL,
  `EMAIL` varchar(30) default NULL,
  `SJHM` varchar(10) default NULL,
  `XBDM` tinyint(4) default NULL,
  `JG` varchar(100) default NULL,
  `MZ` varchar(100) default NULL,
  `SFZHM` varchar(20) default NULL,
  `LZYG` tinyint(4) default NULL,
  `YGTX` varchar(255) default NULL,
  `CZRY_ZH` varchar(30) default NULL,
  `XZRQ` date default NULL,
  `QM` varchar(255) default NULL,
  `PYM` varchar(10) default NULL,
  PRIMARY KEY  (`CZRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_zzjg_czry
-- ----------------------------

-- ----------------------------
-- Table structure for xt_zzjg_czry_gwzz
-- ----------------------------
DROP TABLE IF EXISTS `xt_zzjg_czry_gwzz`;
CREATE TABLE `xt_zzjg_czry_gwzz` (
  `CZRYID` varchar(40) NOT NULL,
  `GWZZID` varchar(40) NOT NULL,
  `BMID` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_zzjg_czry_gwzz
-- ----------------------------

-- ----------------------------
-- Table structure for xt_zzjg_czryqx
-- ----------------------------
DROP TABLE IF EXISTS `xt_zzjg_czryqx`;
CREATE TABLE `xt_zzjg_czryqx` (
  `CZRY_ID` varchar(40) NOT NULL,
  `JSID` varchar(40) default NULL,
  PRIMARY KEY  (`CZRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_zzjg_czryqx
-- ----------------------------

-- ----------------------------
-- Table structure for xt_zzjg_gwzz
-- ----------------------------
DROP TABLE IF EXISTS `xt_zzjg_gwzz`;
CREATE TABLE `xt_zzjg_gwzz` (
  `ID` varchar(40) NOT NULL,
  `GWZZMC` varchar(50) NOT NULL,
  `GWZZBM` tinyint(4) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_zzjg_gwzz
-- ----------------------------
INSERT INTO `xt_zzjg_gwzz` VALUES ('063cc024d65243158e8607872fe4834d', '行政机关负责人', '3');
INSERT INTO `xt_zzjg_gwzz` VALUES ('823ac51ebbcc4f78866151f8adcbff34', '承办机构负责人', '1');
INSERT INTO `xt_zzjg_gwzz` VALUES ('c1a87261f04a48fd826b436a505dcfab', '法制工作机构负责人', '2');

-- ----------------------------
-- Table structure for xt_zzjg_jsxx
-- ----------------------------
DROP TABLE IF EXISTS `xt_zzjg_jsxx`;
CREATE TABLE `xt_zzjg_jsxx` (
  `JSID` varchar(40) NOT NULL,
  `JSBM` varchar(40) default NULL,
  `JSMC` varchar(100) default NULL,
  `XYBS` tinyint(4) default NULL,
  `SSBM_ID` varchar(40) default NULL,
  PRIMARY KEY  (`JSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_zzjg_jsxx
-- ----------------------------

-- ----------------------------
-- Table structure for xt_zzjg_zzbm
-- ----------------------------
DROP TABLE IF EXISTS `xt_zzjg_zzbm`;
CREATE TABLE `xt_zzjg_zzbm` (
  `ZZID` varchar(40) NOT NULL,
  `QYID` varchar(40) default NULL,
  `BMMC` varchar(100) default NULL,
  `SJID` varchar(40) default NULL,
  `BMDM` varchar(40) default NULL,
  `BMLX` tinyint(4) default NULL,
  `LXDZ` varchar(255) default NULL,
  `LXDH` varchar(40) default NULL,
  `BMJC` varchar(10) default NULL,
  `DWQZ` varchar(255) default NULL,
  `PYM` varchar(100) default NULL,
  `JKYH` varchar(100) default NULL,
  `JKYHZH` varchar(20) default NULL,
  `FYDW1` varchar(100) default NULL,
  `FYDW2` varchar(100) default NULL,
  `SSFY` varchar(100) default NULL,
  `DFBM` varchar(100) default NULL,
  `YB` varchar(10) default NULL,
  `SYBS` tinyint(4) default NULL,
  `SEQ` tinyint(4) default NULL,
  PRIMARY KEY  (`ZZID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xt_zzjg_zzbm
-- ----------------------------
