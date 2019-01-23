/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.1.0

Source Server         : 新豪智敏捷开发框架开发库
Source Server Version : 100200
Source Host           : 172.16.5.11:1521
Source Schema         : XHZ

Target Server Type    : ORACLE
Target Server Version : 100200
File Encoding         : 65001

Date: 2019-01-23 15:10:50
*/


-- ----------------------------
-- Table structure for SYS_DATABASE
-- ----------------------------
DROP TABLE "XHZ"."SYS_DATABASE";
CREATE TABLE "XHZ"."SYS_DATABASE" (
"ID" VARCHAR2(40 BYTE) NOT NULL ,
"NAME" VARCHAR2(60 BYTE) NOT NULL ,
"URL" VARCHAR2(255 BYTE) NOT NULL ,
"USER_NAME" VARCHAR2(40 BYTE) NOT NULL ,
"PASS_WORD" VARCHAR2(40 BYTE) NOT NULL ,
"DB_TYPE" NUMBER NOT NULL ,
"LAST_TEST_TIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "XHZ"."SYS_DATABASE" IS '数据源配置';
COMMENT ON COLUMN "XHZ"."SYS_DATABASE"."ID" IS '数据源ID';
COMMENT ON COLUMN "XHZ"."SYS_DATABASE"."NAME" IS '数据源名称';
COMMENT ON COLUMN "XHZ"."SYS_DATABASE"."URL" IS '链接地址';
COMMENT ON COLUMN "XHZ"."SYS_DATABASE"."USER_NAME" IS '用户名';
COMMENT ON COLUMN "XHZ"."SYS_DATABASE"."PASS_WORD" IS '密码';
COMMENT ON COLUMN "XHZ"."SYS_DATABASE"."DB_TYPE" IS '数据库类型';
COMMENT ON COLUMN "XHZ"."SYS_DATABASE"."LAST_TEST_TIME" IS '最近测试链接时间';

-- ----------------------------
-- Records of SYS_DATABASE
-- ----------------------------
INSERT INTO "XHZ"."SYS_DATABASE" VALUES ('d027a701319a4862933bf667a85251c0', '123', '123', '23', '123', '1', null);

-- ----------------------------
-- Table structure for SYS_MENU
-- ----------------------------
DROP TABLE "XHZ"."SYS_MENU";
CREATE TABLE "XHZ"."SYS_MENU" (
"MENU_ID" VARCHAR2(40 BYTE) NOT NULL ,
"PARENT_ID" VARCHAR2(40 BYTE) NOT NULL ,
"NAME" VARCHAR2(20 BYTE) NOT NULL ,
"URL" VARCHAR2(255 BYTE) NULL ,
"PERMS" VARCHAR2(255 BYTE) NULL ,
"TYPE" CHAR(1 BYTE) NOT NULL ,
"ICON" VARCHAR2(30 BYTE) NULL ,
"ORDER_NUM" CHAR(2 BYTE) NOT NULL ,
"IS_DELETED" CHAR(1 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "XHZ"."SYS_MENU" IS '菜单管理';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."MENU_ID" IS '菜单ID';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."PARENT_ID" IS '父菜单ID';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."NAME" IS '菜单名称';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."URL" IS '菜单URL';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."PERMS" IS '授权标识';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."TYPE" IS '菜单类型';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."ICON" IS '菜单图标';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."ORDER_NUM" IS '排序号';
COMMENT ON COLUMN "XHZ"."SYS_MENU"."IS_DELETED" IS '删除标识';

-- ----------------------------
-- Records of SYS_MENU
-- ----------------------------
INSERT INTO "XHZ"."SYS_MENU" VALUES ('f58a5c7175c549139b7769c22722ce5a', '0', '系统管理', null, null, '0', 'fa fa-desktop', '0 ', '0');
INSERT INTO "XHZ"."SYS_MENU" VALUES ('143bad3a894e484f811e5c24db298b6b', 'f58a5c7175c549139b7769c22722ce5a', '用户管理', '/sys/user.html', null, '1', 'fa fa-user', '0 ', '0');
INSERT INTO "XHZ"."SYS_MENU" VALUES ('0f42d387706f498fa583e96bb7cc43ba', '0', '开发工具', null, null, '0', 'fa fa-code', '1 ', '0');
INSERT INTO "XHZ"."SYS_MENU" VALUES ('9d6d722665614b3e9d8dc8e0eed3ebc2', 'f58a5c7175c549139b7769c22722ce5a', '菜单管理', '/sys/menu.html', null, '1', 'fa fa-bars', '1 ', '0');
INSERT INTO "XHZ"."SYS_MENU" VALUES ('66d772c698634771a1c3d6477454fb39', '0f42d387706f498fa583e96bb7cc43ba', '图标库', '/develop/icon.html', null, '1', 'fa fa-info', '0 ', '0');
INSERT INTO "XHZ"."SYS_MENU" VALUES ('95f06aa70f2848a89206133d0846a19a', '0f42d387706f498fa583e96bb7cc43ba', '数据源配置', '/develop/database.html', null, '1', 'fa fa-database', '0 ', '0');
INSERT INTO "XHZ"."SYS_MENU" VALUES ('d02a6be9f15644edb13cda9c9ff4864b', '0', '功能测试', null, null, '0', 'fa fa-bug', '2 ', '0');
INSERT INTO "XHZ"."SYS_MENU" VALUES ('8c4a889d2e0a4d9486d8323818bf9f03', 'd02a6be9f15644edb13cda9c9ff4864b', 'swagger', '/swagger-ui.html', null, '1', 'fa fa-book', '0 ', '0');

-- ----------------------------
-- Table structure for SYS_USER
-- ----------------------------
DROP TABLE "XHZ"."SYS_USER";
CREATE TABLE "XHZ"."SYS_USER" (
"ID" VARCHAR2(40 BYTE) NOT NULL ,
"NAME" VARCHAR2(40 BYTE) NULL ,
"ACCOUNT" VARCHAR2(255 BYTE) NULL ,
"DEPT_ID" VARCHAR2(40 BYTE) NULL ,
"BIRTH_DAY" DATE NULL ,
"PASS_WORD" VARCHAR2(50 BYTE) NULL ,
"SEX" CHAR(1 BYTE) NULL ,
"EMAIL" VARCHAR2(60 BYTE) NULL ,
"PHONE" VARCHAR2(30 BYTE) NULL ,
"ID_CARD" VARCHAR2(20 BYTE) NULL ,
"AVATAR_ID" VARCHAR2(255 BYTE) NULL ,
"IS_DELETED" CHAR(1 BYTE) NULL ,
"CREATE_TIME" DATE NULL ,
"UPDATE_TIME" DATE NULL ,
"CREATE_USER_ID" VARCHAR2(40 BYTE) NULL ,
"UPDATE_USER_ID" VARCHAR2(40 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "XHZ"."SYS_USER" IS '用户信息';
COMMENT ON COLUMN "XHZ"."SYS_USER"."ID" IS '用户ID';
COMMENT ON COLUMN "XHZ"."SYS_USER"."NAME" IS '用户名';
COMMENT ON COLUMN "XHZ"."SYS_USER"."ACCOUNT" IS '用户账号';
COMMENT ON COLUMN "XHZ"."SYS_USER"."DEPT_ID" IS '部门ID';
COMMENT ON COLUMN "XHZ"."SYS_USER"."BIRTH_DAY" IS '出生日期';
COMMENT ON COLUMN "XHZ"."SYS_USER"."PASS_WORD" IS '密码';
COMMENT ON COLUMN "XHZ"."SYS_USER"."SEX" IS '性别';
COMMENT ON COLUMN "XHZ"."SYS_USER"."EMAIL" IS '邮箱';
COMMENT ON COLUMN "XHZ"."SYS_USER"."PHONE" IS '手机号';
COMMENT ON COLUMN "XHZ"."SYS_USER"."ID_CARD" IS '身份证号';
COMMENT ON COLUMN "XHZ"."SYS_USER"."AVATAR_ID" IS '头像ID';
COMMENT ON COLUMN "XHZ"."SYS_USER"."IS_DELETED" IS '删除标识';
COMMENT ON COLUMN "XHZ"."SYS_USER"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "XHZ"."SYS_USER"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "XHZ"."SYS_USER"."CREATE_USER_ID" IS '创建人ID';
COMMENT ON COLUMN "XHZ"."SYS_USER"."UPDATE_USER_ID" IS '修改人ID';

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO "XHZ"."SYS_USER" VALUES ('3fd523b9fc234b06b73ccefa4284aea5', '2', '2334', null, null, '12', null, null, '1', null, null, '0', null, null, null, null);
INSERT INTO "XHZ"."SYS_USER" VALUES ('0b515447984d492db3d9da7129898590', '123', '123', null, null, '123', null, null, null, null, null, '0', null, null, null, null);
INSERT INTO "XHZ"."SYS_USER" VALUES ('c61755734c9e4a40bf8a89334cb94568', '123', '33111', null, null, '123', null, null, null, null, null, '0', null, null, null, null);

-- ----------------------------
-- Indexes structure for table SYS_DATABASE
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_DATABASE
-- ----------------------------
ALTER TABLE "XHZ"."SYS_DATABASE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_DATABASE" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_DATABASE" ADD CHECK ("URL" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_DATABASE" ADD CHECK ("USER_NAME" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_DATABASE" ADD CHECK ("PASS_WORD" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_DATABASE" ADD CHECK ("DB_TYPE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_DATABASE
-- ----------------------------
ALTER TABLE "XHZ"."SYS_DATABASE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table SYS_MENU
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_MENU
-- ----------------------------
ALTER TABLE "XHZ"."SYS_MENU" ADD CHECK ("MENU_ID" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_MENU" ADD CHECK ("PARENT_ID" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_MENU" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_MENU" ADD CHECK ("TYPE" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_MENU" ADD CHECK ("ORDER_NUM" IS NOT NULL);
ALTER TABLE "XHZ"."SYS_MENU" ADD CHECK ("IS_DELETED" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_MENU
-- ----------------------------
ALTER TABLE "XHZ"."SYS_MENU" ADD PRIMARY KEY ("MENU_ID");

-- ----------------------------
-- Indexes structure for table SYS_USER
-- ----------------------------

-- ----------------------------
-- Checks structure for table SYS_USER
-- ----------------------------
ALTER TABLE "XHZ"."SYS_USER" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table SYS_USER
-- ----------------------------
ALTER TABLE "XHZ"."SYS_USER" ADD PRIMARY KEY ("ID");
