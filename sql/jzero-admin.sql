/*
 Navicat Premium Data Transfer

 Source Server         : 笔记本数据库
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.1.141:3306
 Source Schema         : jzero-admin

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 05/07/2020 19:31:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for as_backup_record
-- ----------------------------
DROP TABLE IF EXISTS `as_backup_record`;
CREATE TABLE `as_backup_record`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `dateLine` datetime(0) NULL DEFAULT NULL,
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '备份类型;0:自动备份 1手动备份',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `index` mediumint(8) NULL DEFAULT 0,
  `dataname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '备份记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_backup_record
-- ----------------------------
INSERT INTO `as_backup_record` VALUES (1, '2018-12-06 20:57:46', 1, '/upload/sql/2018-12-06 20-57-45jzero_demo.sql', 0, 'jzero_demo');

-- ----------------------------
-- Table structure for as_common_setting
-- ----------------------------
DROP TABLE IF EXISTS `as_common_setting`;
CREATE TABLE `as_common_setting`  (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` tinyint(3) NOT NULL DEFAULT 0 COMMENT '0:通用设置；1短信设置；2邮件设置',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `issystem` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否为系统设置',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `settingname`(`name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of as_common_setting
-- ----------------------------
INSERT INTO `as_common_setting` VALUES (3, 'count', '0', 0, '', 1);
INSERT INTO `as_common_setting` VALUES (4, 'title', '控制数据客户机管理系统', 0, '网站名称', 1);
INSERT INTO `as_common_setting` VALUES (5, 'loginBg', '/upload/map/1593938765103.JPG', 0, '登录页背景大图', 1);
INSERT INTO `as_common_setting` VALUES (6, 'loginTitle', '控制数据客户机管理系统', 0, '控制数据客户机管理系统', 1);
INSERT INTO `as_common_setting` VALUES (7, 'product', '88', 1, '短信模板{product}值', 1);
INSERT INTO `as_common_setting` VALUES (8, 'length', '88', 1, '短信模板短信验证码长度值', 1);
INSERT INTO `as_common_setting` VALUES (9, 'timeLimit', '88', 1, '验证码有效时间', 1);
INSERT INTO `as_common_setting` VALUES (10, 'prompt', '88', 1, '注册手机号码框下面提示', 1);
INSERT INTO `as_common_setting` VALUES (11, 'validation', '0', 1, '短信发送验证模式（0：无验证；1为图形验证码）', 1);
INSERT INTO `as_common_setting` VALUES (12, 'phoneOnLimit', '88', 1, '同一手机每天最多短信次数', 1);
INSERT INTO `as_common_setting` VALUES (13, 'ipOnLimit', '88', 1, '同一IP每天最多短信次数', 1);
INSERT INTO `as_common_setting` VALUES (14, 'membersOnLimit', '8855', 1, '同一会员每天最多短信次数', 1);
INSERT INTO `as_common_setting` VALUES (15, 'waitTime', '882', 1, '重新发送短信等待时间', 1);
INSERT INTO `as_common_setting` VALUES (16, 'appKey', 'LTAI2VX7801DeGi8', 1, '阿里云appKey', 1);
INSERT INTO `as_common_setting` VALUES (17, 'appSecret', 'XtqlP40d8XB2v3LYDnviU47GLa7wki', 1, '阿里云appSecret', 1);
INSERT INTO `as_common_setting` VALUES (18, 'appEmaileKey', 'LTAIzBHH0Pti2xtD', 0, '阿里云appKey邮件', 1);
INSERT INTO `as_common_setting` VALUES (19, 'appEmailSecret', 'a2CNVabAudQBaTMyigMR4nP8bzdFZv', 0, '阿里云appSecret邮件', 1);
INSERT INTO `as_common_setting` VALUES (20, 'sendPeopleEmail', 'lenglh@notice.lenglh.com', 0, '发件人邮箱', 1);
INSERT INTO `as_common_setting` VALUES (21, 'sendPeopleName', '冷链汇监管平台', 0, '发送人名称', 1);
INSERT INTO `as_common_setting` VALUES (22, 'tagName', 'lenglh', 0, '控制台创建的标签', 1);
INSERT INTO `as_common_setting` VALUES (23, 'emailType', '0', 0, '邮件公司', 1);
INSERT INTO `as_common_setting` VALUES (25, 'backupType', '', 0, '', 1);
INSERT INTO `as_common_setting` VALUES (26, 'appKeyTxun', '1400129172', 1, '腾讯云appKey', 1);
INSERT INTO `as_common_setting` VALUES (27, 'appSecretTxun', '6ee63bf5dc83c6742e3df330365fa2f2', 0, '腾讯云appSecret', 1);
INSERT INTO `as_common_setting` VALUES (28, 'generator_header', '', 0, '', 1);
INSERT INTO `as_common_setting` VALUES (29, 'generator_controller_package', '', 0, '', 1);
INSERT INTO `as_common_setting` VALUES (30, 'generator_model_package', '', 0, '', 1);
INSERT INTO `as_common_setting` VALUES (31, 'homepageImage', 'upload/map/1542249448155.png', 0, '首页图', 1);
INSERT INTO `as_common_setting` VALUES (32, 'homepageNavcolor', '#fff', 0, '首页导航颜色', 1);
INSERT INTO `as_common_setting` VALUES (33, 'homepageTitle', '西创新闻模板', 0, '西创新闻模板title', 1);
INSERT INTO `as_common_setting` VALUES (34, 'isvalidate', 'false', 0, '是否开启校验', 0);

-- ----------------------------
-- Table structure for as_db_backup
-- ----------------------------
DROP TABLE IF EXISTS `as_db_backup`;
CREATE TABLE `as_db_backup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dbname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库名',
  `switch` tinyint(4) NULL DEFAULT 0 COMMENT '自动备份开关：0-关，1-开',
  `time` time(0) NULL DEFAULT '00:00:00' COMMENT '执行自动备份的时间点',
  `cycleDay` int(11) NULL DEFAULT 1 COMMENT '自动备份工作执行周期：每？天',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_db_backup
-- ----------------------------
INSERT INTO `as_db_backup` VALUES (1, 'xc_cool_app_v2', 1, '11:15:00', 3);
INSERT INTO `as_db_backup` VALUES (2, 'xc_jzero_api', 1, '11:15:00', 2);
INSERT INTO `as_db_backup` VALUES (3, 'xc_jzero_hal', 1, '11:15:00', 3);

-- ----------------------------
-- Table structure for as_friendship_link
-- ----------------------------
DROP TABLE IF EXISTS `as_friendship_link`;
CREATE TABLE `as_friendship_link`  (
  `id` mediumint(8) NOT NULL DEFAULT 0,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接',
  `isshow` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否展示',
  `orderby` smallint(6) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_friendship_link
-- ----------------------------
INSERT INTO `as_friendship_link` VALUES (0, '百度链接', 'http://www.baidu.com', 1, NULL);

-- ----------------------------
-- Table structure for as_front_nav
-- ----------------------------
DROP TABLE IF EXISTS `as_front_nav`;
CREATE TABLE `as_front_nav`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `upid` mediumint(8) NOT NULL DEFAULT 0 COMMENT '上级菜单id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url资源',
  `blank` tinyint(1) NULL DEFAULT NULL,
  `dateline` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `isClosed` tinyint(1) NULL DEFAULT 0 COMMENT '是否启用',
  `color` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单背景\r\n',
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `orders` mediumint(8) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `upid_idx`(`upid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻导航' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_front_nav
-- ----------------------------
INSERT INTO `as_front_nav` VALUES (1, '捐赠流程', 0, '/security/content/localContent/1', 0, '2018-10-10 14:01:18', 1, NULL, '2018-11-02 10:16:08', 1);
INSERT INTO `as_front_nav` VALUES (2, '捐赠流程', 1, '/index/newsContent?contentid=1', 0, '2018-10-10 14:01:18', 1, NULL, '2018-11-02 10:31:34', 1);
INSERT INTO `as_front_nav` VALUES (4, '信息公开', 0, 'ss', 0, NULL, 1, NULL, '2018-11-02 10:16:22', 2);
INSERT INTO `as_front_nav` VALUES (5, '捐赠方式', 1, '/index/newsContent?contentid=2', 0, '2018-10-10 14:01:18', 1, NULL, '2018-11-05 10:33:57', 2);
INSERT INTO `as_front_nav` VALUES (6, '捐赠鸣谢', 1, '/index/newsContent?contentid=3', 0, '2018-10-11 17:05:28', 1, NULL, '2018-11-05 10:34:05', 3);
INSERT INTO `as_front_nav` VALUES (7, '新闻中心', 0, '1', 0, '2018-10-12 10:03:26', 1, NULL, '2018-11-02 10:16:29', 3);
INSERT INTO `as_front_nav` VALUES (8, '新闻中心', 7, '/index/newsList?catid=1', 0, '2018-10-12 10:03:46', 1, NULL, '2018-11-02 10:31:41', 1);
INSERT INTO `as_front_nav` VALUES (10, '捐赠免税', 1, '/index/newsContent?contentid=4', 0, '2018-11-02 10:05:08', 1, NULL, '2018-11-05 10:34:12', 4);
INSERT INTO `as_front_nav` VALUES (11, '年度报告', 4, '/index/newsContent?contentid=5', 0, '2018-11-02 10:05:53', 1, NULL, '2018-11-02 10:17:28', 6);
INSERT INTO `as_front_nav` VALUES (12, '政策文件', 4, '/index/newsContent?contentid=6', 0, '2018-11-02 10:06:28', 1, NULL, NULL, 7);
INSERT INTO `as_front_nav` VALUES (13, '筹资项目', 0, 's', 0, '2018-11-02 10:12:23', 1, NULL, '2018-11-02 10:15:41', 4);
INSERT INTO `as_front_nav` VALUES (14, '筹资项目', 13, '/index/newsList?catid=2', 0, '2018-11-02 10:13:30', 1, NULL, '2018-11-02 10:31:48', 6);
INSERT INTO `as_front_nav` VALUES (15, '捐赠列表', 0, '#', 1, '2018-11-02 10:14:53', 1, NULL, '2018-11-22 15:47:26', 5);
INSERT INTO `as_front_nav` VALUES (16, '捐赠列表', 15, 'http://www.baidu.com', 1, '2018-11-02 10:15:25', 1, NULL, '2018-11-22 15:48:13', 1);
INSERT INTO `as_front_nav` VALUES (17, '下载中心', 0, '1', 0, '2018-11-02 10:17:10', 1, NULL, NULL, 6);
INSERT INTO `as_front_nav` VALUES (18, '下载中心', 17, '/index/newsContent?contentid=4', 0, '2018-11-02 10:18:00', 1, NULL, NULL, 1);
INSERT INTO `as_front_nav` VALUES (19, '关于我们', 0, '2', 0, '2018-11-02 10:18:37', 1, NULL, NULL, 7);
INSERT INTO `as_front_nav` VALUES (20, '简介', 19, '/index/newsContent?contentid=5', 0, '2018-11-02 10:19:17', 1, NULL, NULL, 1);
INSERT INTO `as_front_nav` VALUES (21, '章程', 19, '/index/newsContent?contentid=6', 0, '2018-11-02 10:19:39', 1, NULL, NULL, 2);
INSERT INTO `as_front_nav` VALUES (22, '联系方式', 19, '/index/newsContent?contentid=10', 0, '2018-11-02 10:20:02', 1, NULL, NULL, 3);

-- ----------------------------
-- Table structure for as_log_email
-- ----------------------------
DROP TABLE IF EXISTS `as_log_email`;
CREATE TABLE `as_log_email`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收email',
  `apiType` tinyint(2) NULL DEFAULT NULL COMMENT '接口类型',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '发送状态',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `dateline` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `uid` mediumint(8) NULL DEFAULT NULL COMMENT '发送这id',
  `ip` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '发送者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发送邮件记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_log_email
-- ----------------------------
INSERT INTO `as_log_email` VALUES (2, '545811710@qq.com', 0, 1, '阿里云邮件发送:内容：大兄弟我建议你去网站自己下;主题:麻烦给我一下教学资料;错误码：ok', '2018-08-27 09:36:34', 1, '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for as_log_phone
-- ----------------------------
DROP TABLE IF EXISTS `as_log_phone`;
CREATE TABLE `as_log_phone`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tel` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `apiType` tinyint(2) NULL DEFAULT NULL COMMENT '接口类型',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '发送状态',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `dateline` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `uid` mediumint(8) NULL DEFAULT NULL COMMENT '发送这id',
  `ip` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '发送者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信发送记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_log_phone
-- ----------------------------
INSERT INTO `as_log_phone` VALUES (16, '15390267470', 0, 1, '阿里云短信发送:模板id+11;参数:[14000b];错误码：ok', '2018-08-27 09:36:34', 1, '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for as_news_cat
-- ----------------------------
DROP TABLE IF EXISTS `as_news_cat`;
CREATE TABLE `as_news_cat`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `sort` double NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sort_idx`(`sort`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻标题' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_news_cat
-- ----------------------------
INSERT INTO `as_news_cat` VALUES (1, '军事新闻', NULL, 1.5, NULL, NULL, NULL, NULL);
INSERT INTO `as_news_cat` VALUES (2, '娱乐新闻', NULL, 3.5, NULL, NULL, NULL, NULL);
INSERT INTO `as_news_cat` VALUES (4, '4', NULL, 4.6, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for as_news_contents
-- ----------------------------
DROP TABLE IF EXISTS `as_news_contents`;
CREATE TABLE `as_news_contents`  (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '新闻类容',
  `news_cat_id` tinyint(4) NULL DEFAULT NULL COMMENT '该条新闻类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻内容\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_news_contents
-- ----------------------------
INSERT INTO `as_news_contents` VALUES (1, '中国科大教育基金会在线捐赠 流程说明 ', 'author', '2018-09-30 16:36:51', '2018-11-05 10:59:30', '<p style=\"text-indent:2em;\">\n	<span style=\"line-height:2;font-size:14px;\">1. \n在浏览器地址栏输入http://jz.ustc.edu.cn/，会进入网上在线捐赠系统首页，点击右侧“网上在线支付”按钮，即可进入捐赠流程。</span>\n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"line-height:2;font-size:14px;\">2. \n捐赠信息采集页面，我们建议您最好将所有项目都填好，以便于我们来统计您的捐赠信息，至少将前面带有蓝色星号的项目都填好，验证无误以后点击“下 \n一步”按钮。</span>\n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"line-height:2;font-size:14px;\">3. \n捐赠信息确认页面，我们将采集到的您的捐赠信息一一列出，以便于您能够确认信息的准确性，再确认无误以后点击“去银行付款”按钮，来完成本次捐赠。</span>\n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"line-height:2;font-size:14px;\">4. \n目前我们的系统支持支付宝支付，您只要有支付宝签约的银行网银，即可完成支付。如果您已经拥有支付宝账户，请在左边栏登录后支付，如果您没有支付 \n宝账户，在右边栏通过您的手机或者邮箱也可以进行支付。</span>\n</p>\n<p style=\"text-indent:2em;\">\n	<span style=\"line-height:2;font-size:14px;\">5. \n支付成功以后，支付宝会自动为您跳转至捐赠成功的页面。在您付款成功以后，我们会为您发送短信和邮件来通知您捐赠成功的结果，同时您可以 \n通过邮件中电子回单上的验证码，来到捐赠首页上验证回单的真实性。</span>\n</p>', 2);
INSERT INTO `as_news_contents` VALUES (2, '捐赠方式', 'auto', '2018-09-19 11:02:59', '2018-11-05 11:02:01', '<div style=\"text-align:left;\">\n	<strong><span style=\"font-size:14px;line-height:2;\">★ \n1.基本户</span></strong> \n</div>\n<strong><span style=\"font-size:14px;line-height:2;\">开户名称：中国科学技术大学教育基金会</span></strong><span><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">账  号：182703469395</span></strong><span><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">开 户 \n行：中国银行合肥南城支行</span></strong><span><br />\n</span><span><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">★ 2.美元户</span></strong><span><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">开户名称：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">USTC \nEducation Foundation</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">账  号：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">178227924507</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">开 户 行：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">Bank of \nChina Hefei Nacheng Branch</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">Swift \nCode：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">BKCHCNBJ780</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">★ 3.港币户</span></strong><span><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">开户名称：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">USTC \nEducation Foundation</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">账  号：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">179727924433</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">开 户 行：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">Bank of \nChina Hefei Nacheng Branch</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">Swift \nCode：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">BKCHCNBJ780</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">★ 4.欧元户</span></strong><span><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">开户名称：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">USTC \nEducation Foundation</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">账  号：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">179732612019</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">开 户 行：</span></strong><span><strong><span style=\"font-size:14px;line-height:2;\">Bank of \nChina Hefei Nancheng Branch</span></strong><br />\n</span><strong><span style=\"font-size:14px;line-height:2;\">Swift \nCode：BKCHCNBJ780</span></strong>', 1);
INSERT INTO `as_news_contents` VALUES (3, '捐赠鸣谢', 'author', '2018-09-30 16:17:19', '2018-11-05 11:10:34', '<table style=\"width:100%;\" cellpadding=\"2\" cellspacing=\"5\" border=\"1\" bordercolor=\"#CCCCCC\">\n	<tbody>\n		<tr>\n			<td style=\"text-align:center;\">\n				<strong><span style=\"font-size:14px;line-height:2;\">姓名</span></strong> \n			</td>\n			<td style=\"text-align:center;\">\n				<strong><span style=\"font-size:14px;line-height:2;\">捐赠项目</span></strong> \n			</td>\n			<td style=\"text-align:center;\">\n				<strong><span style=\"font-size:14px;line-height:2;\">金额</span></strong> \n			</td>\n			<td style=\"text-align:center;\">\n				<strong><span style=\"font-size:14px;line-height:2;\">捐赠时间</span></strong> \n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">王海波</span> \n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">中国科大60周年校庆捐赠</span> \n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">600.00</span> \n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-28 10:48</span> \n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">鲍伟伟&nbsp;</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">爱心助学金</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2000.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-22 17:50</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">顾晓涛&nbsp;</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">六十周年校庆·西区活动中心室内座椅捐赠</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">5000.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-16 10:45</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">张北松&nbsp;</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">爱心助学金</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">100.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-15 13:13</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">吴红桔&nbsp;</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">通用基金</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">100.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-14 19:30</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">侯爽</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">专项奖学金</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">3000.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-10 04:21</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">何海阳&nbsp;</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">校友工作发展基金</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">1.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-09 14:40</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">徐克建&nbsp;</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">中国科大60周年校庆捐赠</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">60.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-09 07:18</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n		<tr>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">姚建平</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">爱心助学金</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">1000.00</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n			<td style=\"text-align:center;\">\n				<span style=\"font-size:14px;line-height:2;\">2018-10-08 10:37</span><span style=\"font-size:14px;line-height:2;\"></span><br />\n			</td>\n		</tr>\n	</tbody>\n</table>\n<br />\n<br />', 1);
INSERT INTO `as_news_contents` VALUES (4, '公益捐赠免税计算方法说明 ', 'author', '2018-09-30 16:23:54', '2018-11-05 11:11:32', '<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; FONT-SIZE: 14px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">根据《中华人民共和国企业所得税法》第九条中规定：“企业发生的公益性捐赠支出，在年度利润总额 \n12%以内的部分，准予在计算应纳税所得额时扣除。”</span>\n	</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">现就企业捐赠免税计算方法举例如下：</span>\n</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">某企业，年度利润总额1000万元，企业所得税率25%：</span>\n	</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">1、如该企业当年未发生公益性支出，则应正常缴税：</span>\n</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">应纳税额=1000*25%=250万元</span>\n	</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">2、如该企业向基金会捐赠大于等于120万元（该企业年度利润的12%）：</span>\n</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">应纳税额=（1000-120）*25%=220万元</span>\n	</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">3、如向基金会捐赠少于120万元：</span>\n</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">应纳税额=（1000-实际捐赠）*25%</span>\n	</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">所得税法第六条第二款及其实施条例第二十四条规定，个人的公益性捐赠在应纳税所得额30%以 \n内的部分，可以从其应纳税所得额中扣除；个人捐赠免税计算方法举例：</span>\n</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">张女士月收入10000元，按照现在的个税费用扣除标准3500元，假如在不考虑“三险一金”的情况下：</span>\n	</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">1、张女士未发生公益捐赠：张女士的“应交税金”为：10000元-3500元=6500元*税率-速算扣除数</span>\n</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">2、假设张女士捐款2000元，不到3000元，张女士的应交税金” \n为：10000-（3500+2000）=4500元*税率-速算扣除数</span>\n	</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">3、张女士捐款等于或多于3000元，张女士的“应交税金” \n为：10000-（3500+3000）=3500*税率-速算扣除数</span>\n</p>\n<p style=\"BOX-SIZING: border-box; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN-TOP: 0px; TEXT-INDENT: 2em; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; MARGIN-BOTTOM: 10px; BORDER-TOP: 0px; BORDER-RIGHT: 0px; PADDING-TOP: 0px; font-variant-numeric: inherit; font-stretch: inherit\" vertical-align:baseline;text-align:justify;text-indent:2em;color:#585858;background-color:#ffffff;white-space:normal;?=\"\"><span style=\"font-size:14px;line-height:2;\">根据《财政部国家税务总局民政部关于公益性捐赠税前扣除有关问题的通知》（财税［2008］160号） \n第八条相关规定，县级以上人民政府及其组成部门和直属机构在接受捐赠时，应按照行政管理级次分别使 \n用由财政部或省、自治区、直辖市财政部门印制的公益性捐赠票据，并加盖本单位的印章</span>\n	</p>', 1);
INSERT INTO `as_news_contents` VALUES (5, '年度报告', 'author', '2018-09-30 16:26:13', '2018-11-05 11:13:14', '<p>\n	<span style=\"font-size:16px;\"><strong>暂无年度报告相关信息！</strong></span>\n</p>\n<p>\n	<br />\n</p>', 2);
INSERT INTO `as_news_contents` VALUES (6, '1', '1', '2018-11-05 11:35:13', '2018-11-05 15:19:46', '<img src=\"/upload/kindeditor/1541402380628.jpg\" alt=\"\" />11', 1);

-- ----------------------------
-- Table structure for as_notes_model
-- ----------------------------
DROP TABLE IF EXISTS `as_notes_model`;
CREATE TABLE `as_notes_model`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板内容',
  `type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '接口类型;0:阿里云',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信签名',
  `modelid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板ID',
  `issystem` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是系统的基础模板',
  `systemtype` tinyint(2) NULL DEFAULT -1 COMMENT '系统基础模板类型；issystem为有效。0：注册验证短信模板；1：绑定手机短信模板；2：找回密码短信模板；',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_notes_model
-- ----------------------------
INSERT INTO `as_notes_model` VALUES (11, '设备报警！设备：${deviceName} 离线了，请及时查看处理！', 0, '冷链汇测试专用', 'SMS_128635095', 0, -1);

-- ----------------------------
-- Table structure for as_notes_send_history
-- ----------------------------
DROP TABLE IF EXISTS `as_notes_send_history`;
CREATE TABLE `as_notes_send_history`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tel` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `apiType` tinyint(2) NULL DEFAULT NULL COMMENT '接口类型',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '发送状态',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `dateline` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `uid` mediumint(8) NULL DEFAULT NULL COMMENT '发送这id',
  `ip` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '发送者IP',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_notes_send_history
-- ----------------------------
INSERT INTO `as_notes_send_history` VALUES (1, '15390267470', 0, 1, '500', '2018-08-03 16:44:38', NULL, NULL);

-- ----------------------------
-- Table structure for as_phone_code
-- ----------------------------
DROP TABLE IF EXISTS `as_phone_code`;
CREATE TABLE `as_phone_code`  (
  `id` mediumint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT 0,
  `dateline` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '验证码' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of as_phone_code
-- ----------------------------
INSERT INTO `as_phone_code` VALUES (7, '15390267470', '816351', 0, NULL);
INSERT INTO `as_phone_code` VALUES (8, '17308151228', '817052', 1, NULL);
INSERT INTO `as_phone_code` VALUES (9, '13902211717', '727148', 0, NULL);
INSERT INTO `as_phone_code` VALUES (10, '18380586197', '341842', 1, NULL);
INSERT INTO `as_phone_code` VALUES (11, '18081238153', '720005', 1, NULL);
INSERT INTO `as_phone_code` VALUES (12, '18308465352', '367537', 1, NULL);
INSERT INTO `as_phone_code` VALUES (13, '13145845014', '474682', 1, NULL);
INSERT INTO `as_phone_code` VALUES (14, '13810045792', '647585', 1, NULL);
INSERT INTO `as_phone_code` VALUES (15, '18435119314', '565157', 1, NULL);

-- ----------------------------
-- Table structure for as_restore_record
-- ----------------------------
DROP TABLE IF EXISTS `as_restore_record`;
CREATE TABLE `as_restore_record`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `dateLine` datetime(0) NULL DEFAULT NULL,
  `type` tinyint(2) NULL DEFAULT 0 COMMENT '备份类型;0:自动备份 1手动备份',
  `bid` mediumint(8) NULL DEFAULT NULL COMMENT '还原记录的ID',
  `uid` mediumint(8) NULL DEFAULT NULL COMMENT '备份人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库还原记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for as_shedule_log
-- ----------------------------
DROP TABLE IF EXISTS `as_shedule_log`;
CREATE TABLE `as_shedule_log`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dateline` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sec_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_menu`;
CREATE TABLE `sec_menu`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_menu` mediumint(8) UNSIGNED NULL DEFAULT 0,
  `iconcss` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图标的样式',
  `ref` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单的DWZ tabID',
  `orderby` int(11) NULL DEFAULT 0,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `type` mediumint(8) NULL DEFAULT NULL COMMENT '0:目录 1：菜单 2：按钮',
  `prems` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单资源' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sec_menu
-- ----------------------------
INSERT INTO `sec_menu` VALUES (4, NULL, '系统管理', 0, 'layui-icon-app', '', 0, NULL, 0, NULL);
INSERT INTO `sec_menu` VALUES (41, '登录界面、管理员查看用户信息', '用户管理', 4, '', 'userList', 10, '/security/user', 1, 'security:user:index');
INSERT INTO `sec_menu` VALUES (42, '部门管理', '部门管理', 4, 'icon-bumenguanli', 'orgList', 11, '/security/org', 1, 'security:org:index');
INSERT INTO `sec_menu` VALUES (43, '用户的角色管理。', '角色管理', 4, 'icon-jiaoseguanli', 'roleList', 12, '/security/role', 1, 'security:role:index');
INSERT INTO `sec_menu` VALUES (46, '菜单列表', '菜单管理', 4, 'icon-erji-caidanguanli', 'menuList', 15, '/security/menu', 1, 'security:menu:index');
INSERT INTO `sec_menu` VALUES (64, '查看用户', '查看', 41, '', '', 0, '/security/user/view/**', 2, 'security:user:view');
INSERT INTO `sec_menu` VALUES (65, '添加用户', '添加', 41, '', '', 0, '/security/user/add/**;/security/user/save/**', 2, 'security:user:add');
INSERT INTO `sec_menu` VALUES (67, '编辑用户', '编辑', 41, '', '', 0, '/security/user/edit/**;/security/user/update/**', 2, 'security:user:edit');
INSERT INTO `sec_menu` VALUES (69, '部门相关', '查看', 42, '', '', 0, '/security/org/view/**', 2, 'security:org:view');
INSERT INTO `sec_menu` VALUES (70, '部门相关', '添加', 42, '', '', 0, '/security/org/add/**;/security/org/save/**', 2, 'security:org:add');
INSERT INTO `sec_menu` VALUES (72, '部门相关', '编辑', 42, '', '', 0, '/security/org/edit/**;/security/org/update/**', 2, 'security:org:edit');
INSERT INTO `sec_menu` VALUES (74, '角色相关', '查看', 43, '', '', 0, '/security/role/view/**', 2, 'security:role:view');
INSERT INTO `sec_menu` VALUES (75, '角色相关', '添加', 43, '', '', 0, '/security/role/add/**;/security/role/save/**', 2, 'security:role:add');
INSERT INTO `sec_menu` VALUES (77, '角色相关', '编辑', 43, '', '', 0, '/security/role/edit/**;/security/role/update/**', 2, 'security:role:edit');
INSERT INTO `sec_menu` VALUES (79, '菜单相关', '查看', 46, '', '', 0, '/security/menu/view/**', 2, 'security:menu:view');
INSERT INTO `sec_menu` VALUES (80, '菜单相关', '添加', 46, '', '', 0, '/security/menu/add/**;/security/menu/save/**', 2, 'security:menu:add');
INSERT INTO `sec_menu` VALUES (82, '菜单相关', '编辑', 46, '', '', 0, '/security/menu/edit/**;/security/menu/update/**', 2, 'security:menu:edit');
INSERT INTO `sec_menu` VALUES (87, '用户相关', '删除', 41, '', '', 0, '/security/user/delete/**', 2, 'security:user:delete');
INSERT INTO `sec_menu` VALUES (88, '部门相关', '删除', 42, '', '', 0, '/security/org/delete/**', 2, 'security:org:delete');
INSERT INTO `sec_menu` VALUES (89, '角色相关', '删除', 43, '', '', 0, '/security/role/delete/**', 2, 'security:role:delete');
INSERT INTO `sec_menu` VALUES (90, '菜单相关', '删除', 46, '', '', 0, '/security/menu/delete/**', 2, 'security:menu:delete');
INSERT INTO `sec_menu` VALUES (91, '设置系统基本设置', '变量设置', 4, NULL, '', 1, '/security/commonsetting', 1, 'security:commonsetting:index');
INSERT INTO `sec_menu` VALUES (92, NULL, '添加', 91, '', '', 2, '/security/commonsetting/add/**', 2, 'security:commonsetting:add');
INSERT INTO `sec_menu` VALUES (93, NULL, '编辑', 91, '', '', 2, '/security/commonsetting/edit/**', 2, 'security:commonsetting:edit');
INSERT INTO `sec_menu` VALUES (94, NULL, '删除', 91, '', '', 5, '/security/commonsetting/delete/**', 2, 'security:commonsetting:delete');
INSERT INTO `sec_menu` VALUES (98, NULL, '平台管理', 0, 'layui-icon-set', '', 2, NULL, 0, '222');
INSERT INTO `sec_menu` VALUES (99, '短信设置', '短信设置', 98, '', '', 2, '/security/notesetting', 1, 'security:notesetting:index');
INSERT INTO `sec_menu` VALUES (100, '代码生成', '代码生成', 4, '', '', 47, '/security/generator', 1, 'security:generator:index');
INSERT INTO `sec_menu` VALUES (101, NULL, '导出', 100, '', '', 48, '/security/generator/export', 2, 'security:generator:export');
INSERT INTO `sec_menu` VALUES (103, '数据备份 还原', '备份还原', 4, NULL, '', 66, '/security/backup', 1, 'security:backup:index');
INSERT INTO `sec_menu` VALUES (105, '查看在线用户', '在线用户', 4, NULL, '', 105, '/security/online', 1, 'security:online:index');
INSERT INTO `sec_menu` VALUES (106, '踢出在线用户', '踢出', 105, 'layui-icon-delete', '', 106, '/security/online/delete/**', 2, 'security:online:delete');
INSERT INTO `sec_menu` VALUES (125, '导航列表', '导航管理', 129, NULL, '', 200, '/security/nav', 1, 'security:nav:index');
INSERT INTO `sec_menu` VALUES (126, NULL, '编辑', 125, '', '', 0, '/security/nav/editNav', NULL, 'security:nav:edit');
INSERT INTO `sec_menu` VALUES (127, '内容crud', '内容管理', 129, NULL, '', 201, '/security/content', 1, 'security:content:contentList');
INSERT INTO `sec_menu` VALUES (128, '新闻分类', '新闻分类', 129, NULL, '', 202, '/security/cat', 1, 'security:cat');
INSERT INTO `sec_menu` VALUES (129, '#', '首页管理', 0, 'layui-icon-home', '', 200, NULL, 0, '#');
INSERT INTO `sec_menu` VALUES (130, '友情链接', '友情链接', 129, NULL, '', 205, '/security/link', 1, 'security:link:index');
INSERT INTO `sec_menu` VALUES (131, NULL, '添加', 130, NULL, '', 210, '#', 2, 'security:link:add');
INSERT INTO `sec_menu` VALUES (132, NULL, '编辑', 130, NULL, '', 211, '#', 2, 'security:link:edit');
INSERT INTO `sec_menu` VALUES (133, NULL, '删除', 130, NULL, '', 213, '#', 2, 'security:link:delete');
INSERT INTO `sec_menu` VALUES (135, '日志管理', '日志管理', 4, '#', '', 50, '/security/log', 1, 'security:logs:index');
INSERT INTO `sec_menu` VALUES (136, '系统内的操作日志', '业务日志', 135, NULL, '', 51, '/security/log/operations', 2, 'security:logs:operations');
INSERT INTO `sec_menu` VALUES (137, '系统日志，进入可查看日志文件系统的结构树，并下载对应的日志文件', '系统日志', 135, NULL, '', 52, '/security/log/system', 2, 'security:logs:system');
INSERT INTO `sec_menu` VALUES (138, '基础设置', '平台设置', 98, NULL, '', 100, '/security/systemsetting', 1, 'security:systemsetting:index');
INSERT INTO `sec_menu` VALUES (139, '地区管理', '地区管理', 98, NULL, '', 122, '/security/district', 1, 'security:district:index');
INSERT INTO `sec_menu` VALUES (140, '支付设置', '支付设置', 98, NULL, '', 11, '/security/paysetting', 1, 'security:paysetting');
INSERT INTO `sec_menu` VALUES (141, '路径设置', '上传路径设置', 0, NULL, '', 1, NULL, 0, '#');
INSERT INTO `sec_menu` VALUES (142, NULL, '路径设置', 4, NULL, '', 16, '/uploadsrc', 1, '#');
INSERT INTO `sec_menu` VALUES (143, '文件上传日志', '上传日志', 4, NULL, '', 17, '/uploadlog', 1, '#');

-- ----------------------------
-- Table structure for sec_org
-- ----------------------------
DROP TABLE IF EXISTS `sec_org`;
CREATE TABLE `sec_org`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `parent_org` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `level` tinyint(3) NOT NULL DEFAULT 0 COMMENT '0:所有，1:第一级，2，第二级',
  `displayorder` smallint(6) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sec_org
-- ----------------------------
INSERT INTO `sec_org` VALUES (5, '西创', '', 0, 1, 1);
INSERT INTO `sec_org` VALUES (6, '开发部', '', 5, 2, 2);
INSERT INTO `sec_org` VALUES (7, '销售部', '', 5, 2, 3);

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名字：用英文',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述：用中文',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sec_role
-- ----------------------------
INSERT INTO `sec_role` VALUES (1, 'Admin', 'admin');
INSERT INTO `sec_role` VALUES (7, 'normalUser', '普通用户');
INSERT INTO `sec_role` VALUES (9, 'manager', '平台管理员');

-- ----------------------------
-- Table structure for sec_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_menu`;
CREATE TABLE `sec_role_menu`  (
  `id` mediumint(8) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` mediumint(8) UNSIGNED NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE,
  CONSTRAINT `mend_id_for` FOREIGN KEY (`menu_id`) REFERENCES `sec_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4924 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sec_role_menu
-- ----------------------------
INSERT INTO `sec_role_menu` VALUES (4769, 9, 4);
INSERT INTO `sec_role_menu` VALUES (4770, 9, 41);
INSERT INTO `sec_role_menu` VALUES (4771, 9, 64);
INSERT INTO `sec_role_menu` VALUES (4772, 9, 65);
INSERT INTO `sec_role_menu` VALUES (4773, 9, 67);
INSERT INTO `sec_role_menu` VALUES (4774, 9, 87);
INSERT INTO `sec_role_menu` VALUES (4775, 9, 42);
INSERT INTO `sec_role_menu` VALUES (4776, 9, 69);
INSERT INTO `sec_role_menu` VALUES (4777, 9, 70);
INSERT INTO `sec_role_menu` VALUES (4778, 9, 72);
INSERT INTO `sec_role_menu` VALUES (4779, 9, 88);
INSERT INTO `sec_role_menu` VALUES (4780, 9, 105);
INSERT INTO `sec_role_menu` VALUES (4781, 9, 106);
INSERT INTO `sec_role_menu` VALUES (4782, 9, 135);
INSERT INTO `sec_role_menu` VALUES (4783, 9, 136);
INSERT INTO `sec_role_menu` VALUES (4784, 9, 98);
INSERT INTO `sec_role_menu` VALUES (4785, 9, 99);
INSERT INTO `sec_role_menu` VALUES (4786, 9, 138);
INSERT INTO `sec_role_menu` VALUES (4787, 9, 139);
INSERT INTO `sec_role_menu` VALUES (4788, 9, 140);
INSERT INTO `sec_role_menu` VALUES (4789, 9, 129);
INSERT INTO `sec_role_menu` VALUES (4790, 9, 125);
INSERT INTO `sec_role_menu` VALUES (4791, 9, 126);
INSERT INTO `sec_role_menu` VALUES (4792, 9, 127);
INSERT INTO `sec_role_menu` VALUES (4793, 9, 128);
INSERT INTO `sec_role_menu` VALUES (4794, 9, 130);
INSERT INTO `sec_role_menu` VALUES (4795, 9, 131);
INSERT INTO `sec_role_menu` VALUES (4796, 9, 132);
INSERT INTO `sec_role_menu` VALUES (4797, 9, 133);
INSERT INTO `sec_role_menu` VALUES (4830, 7, 90);
INSERT INTO `sec_role_menu` VALUES (4831, 7, 142);
INSERT INTO `sec_role_menu` VALUES (4896, 1, 41);
INSERT INTO `sec_role_menu` VALUES (4897, 1, 64);
INSERT INTO `sec_role_menu` VALUES (4898, 1, 65);
INSERT INTO `sec_role_menu` VALUES (4899, 1, 67);
INSERT INTO `sec_role_menu` VALUES (4900, 1, 87);
INSERT INTO `sec_role_menu` VALUES (4901, 1, 42);
INSERT INTO `sec_role_menu` VALUES (4902, 1, 69);
INSERT INTO `sec_role_menu` VALUES (4903, 1, 70);
INSERT INTO `sec_role_menu` VALUES (4904, 1, 72);
INSERT INTO `sec_role_menu` VALUES (4905, 1, 88);
INSERT INTO `sec_role_menu` VALUES (4906, 1, 43);
INSERT INTO `sec_role_menu` VALUES (4907, 1, 74);
INSERT INTO `sec_role_menu` VALUES (4908, 1, 75);
INSERT INTO `sec_role_menu` VALUES (4909, 1, 77);
INSERT INTO `sec_role_menu` VALUES (4910, 1, 89);
INSERT INTO `sec_role_menu` VALUES (4911, 1, 46);
INSERT INTO `sec_role_menu` VALUES (4912, 1, 79);
INSERT INTO `sec_role_menu` VALUES (4913, 1, 80);
INSERT INTO `sec_role_menu` VALUES (4914, 1, 82);
INSERT INTO `sec_role_menu` VALUES (4915, 1, 90);
INSERT INTO `sec_role_menu` VALUES (4916, 1, 91);
INSERT INTO `sec_role_menu` VALUES (4917, 1, 92);
INSERT INTO `sec_role_menu` VALUES (4918, 1, 93);
INSERT INTO `sec_role_menu` VALUES (4919, 1, 94);
INSERT INTO `sec_role_menu` VALUES (4920, 1, 106);
INSERT INTO `sec_role_menu` VALUES (4921, 1, 137);
INSERT INTO `sec_role_menu` VALUES (4922, 1, 142);
INSERT INTO `sec_role_menu` VALUES (4923, 1, 143);

-- ----------------------------
-- Table structure for sec_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_user`;
CREATE TABLE `sec_role_user`  (
  `user_id` mediumint(8) UNSIGNED NOT NULL,
  `role_id` mediumint(8) UNSIGNED NOT NULL,
  INDEX `FK_ROLE_USER1`(`user_id`) USING BTREE,
  INDEX `FK_ROLE_USER2`(`role_id`) USING BTREE,
  CONSTRAINT `sec_role_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sec_role_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户所对应的角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sec_role_user
-- ----------------------------
INSERT INTO `sec_role_user` VALUES (1, 1);
INSERT INTO `sec_role_user` VALUES (2, 9);
INSERT INTO `sec_role_user` VALUES (9, 7);

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `grouptype` tinyint(3) NOT NULL DEFAULT 0 COMMENT '0:普通用户,1：商家，2:代理，3、其他',
  `username` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号或者登录名,商家前面+8',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '昵名',
  `email` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱可以为NULL',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '加密密码',
  `plainPassword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '未加密密码',
  `gender` tinyint(3) NOT NULL DEFAULT 0 COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `identify` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '身份证',
  `org` mediumint(8) NOT NULL DEFAULT 0 COMMENT '部门或者公司',
  `headimgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户所在国家',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户所在省份',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户所在城市',
  `notes` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '工作格言',
  `enabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止用户',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `regtime` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `openid` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `subscribe_time` datetime(0) NULL DEFAULT NULL COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `money` smallint(6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '余额',
  `online` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `logintime` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  UNIQUE INDEX `openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通用用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sec_user
-- ----------------------------
INSERT INTO `sec_user` VALUES (1, 0, 'admin', '管理员', 'hsongjiang1982@qq.com', '99e3eebe213fe35afe14f3913777cf04533e2573', '123456', 1, '510228196325146579', 1, '', '', NULL, '', '', 1, 'ed73d8df71da759f', '2017-03-21 17:43:38', '1', NULL, 0, 1, '2020-07-05 16:45:54', '111', '1234567');
INSERT INTO `sec_user` VALUES (2, 0, 'manager', 'manager', '123456@qq.com', '7335299c94c8450711cc6f27452ec8a003772115', '654321', 0, '', 0, '', '', '', '', '', 1, 'fba72a31da870809', '2018-07-04 12:29:17', NULL, NULL, 0, 1, '2019-06-13 17:42:59', NULL, 'manager');
INSERT INTO `sec_user` VALUES (8, 0, 'o4M7r0zWrsqeSkXx6AcmWtg4YJ8Y', '', NULL, '783aad50cc24d40482f2311d0fbf59656a93ed5d', '123456', 0, '', 0, '', '', '', '', '', 1, 'd097e0124d01d2af', '2019-09-02 15:47:10', 'o4M7r0zWrsqeSkXx6AcmWtg4YJ8Y', NULL, 0, 0, NULL, NULL, '');
INSERT INTO `sec_user` VALUES (9, 0, 'test', '曾润', '448751172@qq.com', '771391915da7987063f347d1e2ae5b80c93748b4', '123456', 0, '', 0, '', '', '', '', '', 1, '2a61d98b78b408b4', '2019-12-14 20:39:16', NULL, NULL, 0, 1, '2020-06-22 12:55:01', NULL, '曾润');

-- ----------------------------
-- Table structure for upload_log
-- ----------------------------
DROP TABLE IF EXISTS `upload_log`;
CREATE TABLE `upload_log`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recode` mediumint(8) NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uploadTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of upload_log
-- ----------------------------
INSERT INTO `upload_log` VALUES (34, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 13:12:01');
INSERT INTO `upload_log` VALUES (35, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 13:20:01');
INSERT INTO `upload_log` VALUES (36, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 13:21:01');
INSERT INTO `upload_log` VALUES (37, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 13:25:01');
INSERT INTO `upload_log` VALUES (38, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 15:36:01');
INSERT INTO `upload_log` VALUES (39, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 15:46:02');
INSERT INTO `upload_log` VALUES (41, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 16:08:03');
INSERT INTO `upload_log` VALUES (42, 'G:\\\\2020-06-22.jpg', 200, '上传成功', '2020-06-22 16:09:01');

-- ----------------------------
-- Table structure for upload_src
-- ----------------------------
DROP TABLE IF EXISTS `upload_src`;
CREATE TABLE `upload_src`  (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) NOT NULL COMMENT '0：数据，1：图片，2：视频',
  `tid` mediumint(8) NULL DEFAULT NULL COMMENT '模板id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoUpload` tinyint(1) NULL DEFAULT 0 COMMENT '1：开启自动上传，0：关闭自动上传',
  `dateline` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of upload_src
-- ----------------------------
INSERT INTO `upload_src` VALUES (2, 1, 0, '图片', 'G:\\', 0, '2020-06-22 16:09:17');
INSERT INTO `upload_src` VALUES (3, 2, 0, '视频', 'G:\\结冰风洞项目\\数据管理系统', 0, '2019-12-18 15:26:27');
INSERT INTO `upload_src` VALUES (5, 0, 7, '控制系统数据', 'G:\\结冰风洞项目\\数据管理系统', 0, '2020-04-01 19:39:24');

SET FOREIGN_KEY_CHECKS = 1;
