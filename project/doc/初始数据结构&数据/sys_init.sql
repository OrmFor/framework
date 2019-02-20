-- ---------------------------------------该文件为系统初始化脚本文件-------------------------------------------------------------
/*
Navicat MySQL Data Transfer

Source Server         : wzq
Source Server Version : 50642
Source Host           : 47.99.220.201:3306
Source Database       : project

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-01-07 10:26:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(32) NOT NULL COMMENT '用户编号',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `real_name` varchar(32) NOT NULL COMMENT '姓名',
  `mobile_phone` varchar(11) NOT NULL COMMENT '手机号码',
  `pwd` varchar(100) NOT NULL COMMENT '登录密码',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '登录ip',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态，0：正常，1：锁定',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique_user_code` (`user_code`) USING BTREE,
  UNIQUE KEY `index_unique_user_name` (`user_name`) USING BTREE,
  UNIQUE KEY `index_unique_mobile_phone` (`mobile_phone`) USING BTREE,
  KEY `index_normal_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for admin_actions
-- ----------------------------
DROP TABLE IF EXISTS `admin_actions`;
CREATE TABLE `admin_actions` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(100) NOT NULL COMMENT '链接地址',
  `pid` int(32) NOT NULL DEFAULT '0' COMMENT '所属菜单id',
  `level` int(1) NOT NULL DEFAULT '0' COMMENT '菜单等级',
  `paixu` int(1) NOT NULL DEFAULT '0' COMMENT '排序',
  `is_menu` bit(1) NOT NULL DEFAULT b'0' COMMENT '菜单类型，0：功能，1：菜单',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标样式',
  `menu_pos_str` varchar(50) DEFAULT NULL COMMENT '菜单定位符',
  `sys_type` tinyint(1) DEFAULT '0' COMMENT '所属系统，0：后台系统，1：APP系统',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址表';

-- ----------------------------
-- Records of admin_actions
-- ----------------------------
INSERT INTO `admin_actions` VALUES ('1', '菜单列表', '/admin/adminActions/list', '5', '2', '1', '', 'icon-home', 'adminActions', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('2', '首页', '/admin/main', '6', '1', '0', '', 'icon-home', '', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('3', '添加菜单', '/admin/adminActions/add', '1', '2', '0', '\0', 'icon-home', 'adminActions', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('4', '保存/更新菜单', '/admin/adminActions/save', '3', '3', '0', '\0', 'icon-home', 'adminActions', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('5', '系统设置', '', '6', '1', '3', '', 'icon-cogs', '', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('6', '后台系统', '', '0', '0', '0', '', 'icon-home', '', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('7', '修改登录密码', '/admin/editPwd', '6', '1', '2', '\0', 'icon-home', '', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('8', '退出', '/admin/logout', '6', '1', '1', '\0', 'icon-home', '', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('9', '管理员管理', '', '6', '1', '4', '', 'icon-key', '', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('10', '管理员列表', '/admin/admin/list', '9', '2', '0', '', 'icon-home', 'admin', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('11', '管理员添加', '/admin/admin/add', '10', '3', '0', '\0', 'icon-home', 'admin', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('12', '保存/更新管理员', '/admin/admin/save', '11', '4', '0', '\0', 'icon-home', 'admin', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('13', '重置管理员密码', '/admin/admin/resetPwd', '10', '3', '1', '\0', 'icon-home', 'admin', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('14', '锁定管理员', '/admin/admin/lock', '10', '3', '2', '\0', 'icon-home', 'admin', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('15', '解锁管理员', '/admin/admin/unlock', '10', '3', '3', '\0', 'icon-home', 'admin', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('16', '删除管理员', '/admin/admin/del', '10', '3', '4', '\0', 'icon-home', 'admin', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('17', '系统日志', '/admin/sysLog/list', '5', '2', '2', '', 'icon-home', 'sysLog', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('18', '消息模板', '/admin/noticeType/list', '5', '2', '5', '', 'icon-home', 'noticeType', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('19', '添加消息模板', '/admin/noticeType/add', '18', '3', '0', '\0', 'icon-home', 'noticeType', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('20', '保存/更新消息模板', '/admin/noticeType/save', '19', '4', '0', '\0', 'icon-home', 'noticeType', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('21', '删除消息模板', '/admin/noticeType/del', '18', '3', '1', '\0', 'icon-home', 'noticeType', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('22', '角色列表', '/admin/roles/list', '5', '2', '0', '', 'icon-home', 'roles', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('23', '添加角色', '/admin/roles/add', '22', '3', '0', '\0', 'icon-home', 'roles', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('24', '保存/更新角色', '/admin/roles/save', '23', '4', '0', '\0', 'icon-home', 'roles', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('25', '删除角色', '/admin/roles/del', '22', '3', '1', '\0', 'icon-home', 'roles', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('26', '用户管理', '', '6', '1', '5', '', 'icon-user', '', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('27', '用户列表', '/admin/user/list', '26', '2', '0', '', 'icon-home', 'user', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('28', '重置密码', '/admin/user/resetPwd', '27', '3', '0', '\0', 'icon-home', 'user', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('29', '定时任务', '/admin/sysTaskHandel/list', '5', '2', '3', '', 'icon-home', 'sysTaskHandel', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('30', '定时任务日志', '/admin/sysTaskLog/list', '5', '2', '4', '', 'icon-home', 'sysTaskLog', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('31', '定时任务开启/关闭', '/admin/sysTaskHandel/enable', '29', '3', '0', '\0', 'icon-home', 'sysTaskHandel', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('32', '定时任务删除', '/admin/sysTaskHandel/del', '29', '3', '1', '\0', 'icon-home', 'sysTaskHandel', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('33', '参数列表', '/admin/sConfig/list', '5', '2', '6', '', 'icon-home', 'sConfig', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('34', '添加配置', '/admin/sConfig/add', '33', '3', '0', '\0', 'icon-home', 'sConfig', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('35', '保存/更新配置', '/admin/sConfig/save', '34', '4', '0', '\0', 'icon-home', 'sConfig', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('36', '删除配置', '/admin/sConfig/del', '34', '4', '1', '\0', 'icon-home', 'sConfig', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('37', '版本列表', '/admin/versionUpManage/list', '5', '2', '7', '', 'icon-home', 'versionUpManage', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('38', '添加版本', '/admin/versionUpManage/add', '37', '3', '0', '', 'icon-home', 'versionUpManage', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('39', '保存/更新版本', '/admin/versionUpManage/save', '38', '4', '0', '', 'icon-home', 'versionUpManage', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('40', '删除版本', '/admin/versionUpManage/del', '38', '4', '1', '', 'icon-home', 'versionUpManage', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('41', '保存登录密码', '/admin/editPwd/save', '7', '2', '0', '\0', 'icon-home', 'editPwd', '0', NOW(), NOW());
INSERT INTO `admin_actions` VALUES ('42', '短信记录', '/admin/notice/list', '5', '2', '8', '', 'icon-home', 'notice', '0', NOW(), NOW());

-- ----------------------------
-- Table structure for admin_role_actions
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_actions`;
CREATE TABLE `admin_role_actions` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rid` int(32) NOT NULL COMMENT '角色id（表roles的id）',
  `aid` int(32) NOT NULL COMMENT '地址id（表admin_actions的id）',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of admin_role_actions
-- ----------------------------

-- ----------------------------
-- Table structure for admin_roles
-- ----------------------------
DROP TABLE IF EXISTS `admin_roles`;
CREATE TABLE `admin_roles` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `aid` int(32) NOT NULL COMMENT '用户id（表admin的id）',
  `rid` int(32) NOT NULL COMMENT '角色id（表roles的id）',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员角色表';

-- ----------------------------
-- Records of admin_roles
-- ----------------------------

-- ----------------------------
-- Table structure for api_token
-- ----------------------------
DROP TABLE IF EXISTS `api_token`;
CREATE TABLE `api_token` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(32) NOT NULL COMMENT '用户编号',
  `device_id` varchar(200) NOT NULL COMMENT '设备编号',
  `device_name` varchar(200) NOT NULL COMMENT '设备名称',
  `token` varchar(100) NOT NULL COMMENT 'token',
  `status` tinyint(1) DEFAULT '0' COMMENT 'token类型，0：有效，1：过期，2：失效，3：另外设备登录',
  `change_time` timestamp NULL DEFAULT NULL COMMENT 'token变更时间',
  `date_add` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `date_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app登录token表';

-- ----------------------------
-- Records of api_token
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sent_user` varchar(20) NOT NULL DEFAULT '0' COMMENT '发送用户',
  `receive_user` varchar(20) NOT NULL DEFAULT '0' COMMENT '接收用户',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态，0：未读，1：已读',
  `del_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除类型，0：未删除，1：已删除',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `add_ip` varchar(15) DEFAULT '' COMMENT '添加ip',
  `nid` varchar(30) DEFAULT NULL COMMENT 'nid标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内信表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nid` varchar(30) NOT NULL DEFAULT '' COMMENT 'nid标识',
  `type` int(1) NOT NULL COMMENT '通知类型，1：短信，2：邮件，3：站内信',
  `sent_user` varchar(20) NOT NULL DEFAULT '0' COMMENT '发送用户',
  `receive_user` varchar(20) NOT NULL DEFAULT '0' COMMENT '接收用户',
  `receive_addr` varchar(50) NOT NULL DEFAULT '' COMMENT '接收地址',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态，0：未发送，1：已发送',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '标题',
  `content` text NOT NULL COMMENT '发送内容',
  `code` varchar(11) DEFAULT NULL COMMENT '验证码',
  `result` varchar(1000) DEFAULT '' COMMENT '发送结果信息',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知记录表';

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for notice_type
-- ----------------------------
DROP TABLE IF EXISTS `notice_type`;
CREATE TABLE `notice_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '发送类型，1：系统通知，2：用户通知',
  `nid` varchar(30) NOT NULL DEFAULT '' COMMENT 'nid标识',
  `notice_type` tinyint(1) NOT NULL COMMENT '通知类型，1：短信，2：邮件，3：站内信',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `title_templet` varchar(250) NOT NULL DEFAULT '' COMMENT '标题的freemarker模板',
  `templet` varchar(1024) NOT NULL DEFAULT '' COMMENT '内容的freemarker模板',
  `send` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否发送，0：不发送，1：发送',
  `can_switch` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可以切换发送类型，0：不可以切换，1：可以切换',
  `remark` varchar(250) DEFAULT '' COMMENT '备注',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `add_ip` varchar(15) DEFAULT '' COMMENT '添加IP',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_ip` varchar(15) DEFAULT '' COMMENT '更新IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique` (`nid`,`type`,`notice_type`) USING BTREE,
  KEY `index_normal` (`nid`,`type`,`notice_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知类型表';

-- ----------------------------
-- Records of notice_type
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `profile` varchar(32) NOT NULL COMMENT '说明',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------

-- ----------------------------
-- Table structure for s_config
-- ----------------------------
DROP TABLE IF EXISTS `s_config`;
CREATE TABLE `s_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `nid` varchar(50) NOT NULL DEFAULT '' COMMENT '标识',
  `value` varchar(600) DEFAULT NULL COMMENT '名称对应的值',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '类型 1:系统底层配置信息， 2:各种费率配置信息 ，3:邮件/短信配置信息 ，4:附加增值功能配置信息 ，5:第三方资金托管相关的配置 ',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态，0：禁用，1：启用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique_nid` (`nid`) USING BTREE,
  KEY `index_normal_nid` (`nid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Records of s_config
-- ----------------------------
INSERT INTO `s_config` VALUES ('1', 'app登录token过期时间', 'APP_EXPIRED_TIMES', '2592000', '1', '1', 'app登录token过期时间，秒为单位', NOW(), NOW());
-- ----------------------------
-- Table structure for s_log_template
-- ----------------------------
DROP TABLE IF EXISTS `s_log_template`;
CREATE TABLE `s_log_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `nid` varchar(50) NOT NULL COMMENT '模板类型',
  `value` varchar(1024) NOT NULL COMMENT '模板信息',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '模板备注',
  `payments_type` tinyint(1) DEFAULT '0' COMMENT '收支明细 0：不变，1：收入，2：支出，3：充值，4：提现，5：购买，6：出售',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_nid` (`nid`) USING BTREE,
  KEY `index_normal_nid` (`nid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志模板表';

-- ----------------------------
-- Records of s_log_template
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `admin_id` int(11) NOT NULL COMMENT '操作人员',
  `module_type` varchar(32) NOT NULL COMMENT '操作模块',
  `oprate_type` varchar(32) NOT NULL COMMENT '操作类型',
  `name` varchar(200) NOT NULL COMMENT '操作名称',
  `uri` varchar(200) NOT NULL COMMENT '操作url',
  `msg` varchar(1000) NOT NULL COMMENT '操作内容',
  `ip` varchar(200) NOT NULL COMMENT '操作ip',
  `date_add` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台系统操作日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_task_handel
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_handel`;
CREATE TABLE `sys_task_handel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `module_name` varchar(64) NOT NULL COMMENT '任务名称',
  `hostname` varchar(64) NOT NULL COMMENT '执行机器名称',
  `ip_address` varchar(64) NOT NULL COMMENT '执行机器ip',
  `is_enabled` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否启用，0：禁用，1：启用',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务表';

-- ----------------------------
-- Records of sys_task_handel
-- ----------------------------

-- ----------------------------
-- Table structure for sys_task_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_log`;
CREATE TABLE `sys_task_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `module_name` varchar(64) NOT NULL COMMENT '任务名称',
  `hostname` varchar(64) NOT NULL COMMENT '执行机器名称',
  `ip_address` varchar(64) NOT NULL COMMENT '执行机器ip',
  `is_success` bit(1) NOT NULL COMMENT '是否执行成功，0：失败，1：成功',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志表';

-- ----------------------------
-- Records of sys_task_log
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_code` varchar(32) NOT NULL COMMENT '用户编号',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `pwd` varchar(100) NOT NULL COMMENT '密码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态，0：已注册，1：已激活',
  `invite_code` varchar(32) NOT NULL COMMENT '邀请码',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_user_code` (`user_code`) USING BTREE,
  UNIQUE KEY `index_user_name` (`user_name`) USING BTREE,
  UNIQUE KEY `index_invite_code` (`invite_code`) USING BTREE,
  KEY `index_normal_user_code` (`user_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_invite_code_max
-- ----------------------------
DROP TABLE IF EXISTS `user_invite_code_max`;
CREATE TABLE `user_invite_code_max` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cur_number` bigint(20) NOT NULL COMMENT '数额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_invite_code_max
-- ----------------------------
INSERT INTO `user_invite_code_max` VALUES ('1', '10000');

-- ----------------------------
-- Table structure for version_up_manage
-- ----------------------------
DROP TABLE IF EXISTS `version_up_manage`;
CREATE TABLE `version_up_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `version_code` varchar(20) NOT NULL COMMENT '版本编号',
  `version_content` varchar(255) NOT NULL COMMENT '版本内容',
  `version_number` varchar(20) NOT NULL COMMENT '版本号',
  `version_level` tinyint(1) NOT NULL DEFAULT '1' COMMENT '版本等级，1：低，2：中，3：高',
  `update_url` varchar(255) NOT NULL COMMENT '更新地址',
  `channel` varchar(20) NOT NULL COMMENT '渠道号',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本表';

-- ----------------------------
-- Records of version_up_manage
-- ----------------------------
