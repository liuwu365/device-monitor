/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.18-log : Database - device-monitor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`device-monitor` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `device-monitor`;

/*Table structure for table `back_ip_limit` */

DROP TABLE IF EXISTS `back_ip_limit`;

CREATE TABLE `back_ip_limit` (
  `id` bigint(30) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ip_address` varchar(100) NOT NULL COMMENT 'ip地址',
  `add_people` varchar(100) NOT NULL COMMENT '添加人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='后台IP限制表';

/*Data for the table `back_ip_limit` */

insert  into `back_ip_limit`(`id`,`ip_address`,`add_people`,`create_time`,`remarks`) values (1,'192.168.1.1','张三','2017-10-14 15:52:00','内部测试IP'),(2,'192.168.1.12','admin','2017-10-16 10:40:40','内部测试IP'),(3,'127.0.0.1','admin','2017-10-18 14:20:05',NULL),(8,'180.169.214.82','admin','2017-10-20 14:10:50','180'),(10,'192.168.1.104','admin','2017-10-20 14:28:58','104'),(11,'192.168.1.101','admin','2017-10-20 14:30:58',''),(12,'192.168.1.115','admin','2017-10-20 14:31:09',''),(13,'192.168.1.106','admin','2017-10-20 14:32:41',NULL),(14,'192.168.1.116','admin','2017-10-20 15:33:25',NULL),(15,'192.168.1.102','admin','2017-10-20 16:55:41',NULL),(16,'192.168.1.108','admin','2017-10-23 10:16:12',NULL),(17,'192.168.1.107','admin','2017-10-23 10:58:31',NULL),(18,'192.168.1.109','admin','2017-10-24 16:27:58',NULL),(20,'192.168.1.118','admin','2017-10-25 15:27:31',''),(22,'192.168.1.13','admin','2017-10-27 16:08:22',''),(23,'192.168.1.111','admin','2017-10-27 16:09:30',''),(25,'192.168.31.136','admin','2017-10-27 16:15:52',''),(26,'192.168.1.138','admin','2017-10-27 16:23:09',''),(29,'192.168.31.151','admin','2017-10-27 17:01:21',''),(30,'192.168.1.117','admin','2017-10-27 17:10:55',''),(31,'192.168.1.141','admin','2017-10-27 17:10:55',NULL),(32,'192.168.1.105','admin','2017-10-20 14:32:41',NULL),(33,'192.168.1.100','admin','2017-11-01 10:42:56',''),(34,'192.168.1.113','admin','2017-11-01 10:49:59',''),(35,'192.168.1.112','admin','2017-11-01 16:17:09',''),(36,'192.168.1.114','admin','2017-11-01 16:26:52',''),(37,'192.168.1.103','admin','2017-11-02 16:53:28',NULL),(38,'192.168.1.110','admin','2017-11-27 10:07:53',NULL),(39,'192.168.1.119','admin','2017-11-27 17:18:54',NULL);

/*Table structure for table `back_res_role` */

DROP TABLE IF EXISTS `back_res_role`;

CREATE TABLE `back_res_role` (
  `res_id` bigint(30) NOT NULL DEFAULT '0',
  `role_id` bigint(30) NOT NULL DEFAULT '0',
  `user_id` bigint(30) NOT NULL DEFAULT '0',
  PRIMARY KEY (`res_id`,`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `back_res_role` */

insert  into `back_res_role`(`res_id`,`role_id`,`user_id`) values (1,1,1),(1,2,4),(1,2,5),(9,1,1),(9,2,4),(9,2,5),(11,1,1),(11,2,4),(11,2,5),(38,1,1),(38,2,4),(38,2,5),(176,1,1),(176,2,5),(177,1,1),(177,2,5),(178,1,1),(179,1,1),(180,1,1),(181,1,1),(182,1,1);

/*Table structure for table `back_resources` */

DROP TABLE IF EXISTS `back_resources`;

CREATE TABLE `back_resources` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `parent_id` bigint(30) DEFAULT NULL COMMENT '父id',
  `res_key` varchar(50) DEFAULT NULL COMMENT '资源标识',
  `type` tinyint(2) DEFAULT NULL COMMENT '类型',
  `res_url` varchar(200) NOT NULL COMMENT 'url',
  `level` int(4) DEFAULT '0' COMMENT '优先级',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_hide` tinyint(1) DEFAULT '0' COMMENT '是否隐藏:0.否 1.是',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

/*Data for the table `back_resources` */

insert  into `back_resources`(`id`,`name`,`parent_id`,`res_key`,`type`,`res_url`,`level`,`icon`,`is_hide`,`description`) values (1,'网站设置',0,'base',0,'/base/user/list.html',1,'icon-hero',0,''),(9,'后台用户管理',1,'base_user',1,'/base/user/list.html',2,'icon-hero',0,''),(10,'角色管理',1,'base_role',1,'/base/role/list.html',2,'icon-hero',1,''),(11,'资源管理',1,'base_resources',1,'/base/resources/list.html',3,'icon-hero',0,''),(38,'登录日志',1,'user_login',1,'/base/user/login/list.html',5,'icon-hero',0,''),(62,'配置管理',0,'config_data',0,'/configManage/enumData/sysEnumDataList.html',10,'icon-hero',1,''),(111,'基础配置',62,'config',1,'/configManage/enumData/sysEnumDataList.json',11,'icon-hero',0,''),(146,'后台IP限制',1,'back_ip_limit',1,'/base/power/list.html',6,'icon-hero',1,'权限管理（后台ip限制）'),(175,'操作日志',1,'center_log',1,'/base/user/operationList.html',12,'icon-hero',1,''),(176,'设备管理',0,'device',0,'/device/device_list.html',13,'icon-hero',0,''),(177,'设备列表',176,'device_list',1,'/device/device_list.html',14,'icon-hero',0,''),(178,'地图管理',176,'device_map',0,'/device/device_map.html',15,'icon-team',0,''),(179,'报警规则',176,'warn_rule',1,'/device/warn_rule_list.html',16,'icon-rank',0,''),(180,'报警管理',176,'device_warn_list',1,'/device/device_warn_list.html',17,'icon-schedule',0,''),(181,'用户管理',0,'user',0,'/user/user_list.html',18,'icon-rank',0,''),(182,'用户列表',181,'user',1,'/user/user_list.html',19,'icon-rank',0,'');

/*Table structure for table `back_role` */

DROP TABLE IF EXISTS `back_role`;

CREATE TABLE `back_role` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `state` tinyint(2) DEFAULT NULL COMMENT '状态',
  `dep` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(50) DEFAULT NULL COMMENT '标识',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台角色表';

/*Data for the table `back_role` */

insert  into `back_role`(`id`,`state`,`dep`,`name`,`role_key`,`description`) values (1,0,'','超级管理员','super','超级管理员角色'),(2,0,'','普通人员','default','默认角色');

/*Table structure for table `back_user` */

DROP TABLE IF EXISTS `back_user`;

CREATE TABLE `back_user` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(20) NOT NULL DEFAULT '用户名',
  `account_name` varchar(20) NOT NULL DEFAULT '账号',
  `password` varchar(100) NOT NULL DEFAULT '密码',
  `credentials_salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定，0：否，1：是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

/*Data for the table `back_user` */

insert  into `back_user`(`id`,`user_name`,`account_name`,`password`,`credentials_salt`,`email`,`description`,`locked`,`create_time`) values (1,'admin','admin','ea76214573be5dce2e5b276e7cd43706','65469b9eb59e33571237f25dd2c6da5d','453563506@qq.com','',0,'2016-09-28 15:14:39'),(3,'朴树','pushu123','c65b6e73da3a2c68133b9927d236be6e','39ebfa80d3adca78f828473fe7d6fb06','pushu@163.com','',0,'2017-12-02 23:31:40'),(4,'小王','xiaowang','98996c10461665ca8e86b33089067d91','564f775703b20f3f8d12e43d7ef18bde','xiaowang@163.com','',0,'2017-12-03 12:30:47'),(5,'刘武','liuwu001','fa2ebd6ed28663f4bd3ee1286ffd1eb1','9fd7ea9df2b3982a01fa45a065930dd5','liuwu@163.com','',0,'2017-12-03 12:34:50');

/*Table structure for table `back_user_login` */

DROP TABLE IF EXISTS `back_user_login`;

CREATE TABLE `back_user_login` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(30) DEFAULT NULL COMMENT '用户id',
  `type` smallint(2) DEFAULT '0' COMMENT '客户端登录类型 0.web客户端 1.windows客户端 2.android客户端 3.iPhone客户端',
  `account_name` varchar(20) DEFAULT NULL COMMENT '账号名称',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `login_ip` varchar(40) DEFAULT NULL COMMENT '登录IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='后台用户登录记录表';

/*Data for the table `back_user_login` */

/*Table structure for table `back_user_role` */

DROP TABLE IF EXISTS `back_user_role`;

CREATE TABLE `back_user_role` (
  `user_id` bigint(30) NOT NULL,
  `role_id` bigint(30) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `back_user_role` */

insert  into `back_user_role`(`user_id`,`role_id`) values (1,1),(1,2),(3,2),(4,2),(5,2);

/*Table structure for table `device_info` */

DROP TABLE IF EXISTS `device_info`;

CREATE TABLE `device_info` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `item` varchar(100) NOT NULL DEFAULT '' COMMENT '项目(如:冷库)',
  `device_uid` varchar(100) DEFAULT '' COMMENT '设备UID',
  `device_type` varchar(100) DEFAULT '' COMMENT '设备类型',
  `longitude` double(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` double(10,6) DEFAULT NULL COMMENT '纬度',
  `address` varchar(200) DEFAULT '' COMMENT '设备位置',
  `status` smallint(2) DEFAULT '1' COMMENT '状态(1.禁用 2.运行)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `device_uid` (`device_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='设备表';

/*Data for the table `device_info` */

insert  into `device_info`(`id`,`item`,`device_uid`,`device_type`,`longitude`,`latitude`,`address`,`status`,`create_time`,`update_time`) values (1,'冷库','A000F3F3','温湿度',121.486468,31.257447,'上海宝山区林菱路25号',2,'2017-12-01 18:03:43','2017-12-02 19:04:35'),(2,'水箱','4F006992','井下水位',121.471269,31.264794,'上海宝山区西藏北路119号',2,'2017-12-01 18:04:08','2017-12-14 16:19:25');

/*Table structure for table `device_run_record` */

DROP TABLE IF EXISTS `device_run_record`;

CREATE TABLE `device_run_record` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `item` varchar(100) NOT NULL DEFAULT '' COMMENT '项目',
  `type` tinyint(2) DEFAULT '0' COMMENT '类型(1.温度 2.水位)',
  `value` double(20,2) DEFAULT '0.00' COMMENT '值 (温度/水位)',
  `value2` double(20,2) DEFAULT '0.00' COMMENT '值2(湿度/等级)',
  `date_time` timestamp NULL DEFAULT NULL COMMENT '数据时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `item` (`item`,`date_time`),
  KEY `item_2` (`item`)
) ENGINE=InnoDB AUTO_INCREMENT=2287 DEFAULT CHARSET=utf8 COMMENT='设备运行记录表';

/*Table structure for table `device_warn_record` */

DROP TABLE IF EXISTS `device_warn_record`;

CREATE TABLE `device_warn_record` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `device_id` bigint(30) DEFAULT NULL COMMENT '设备Id(预留)',
  `item` varchar(100) DEFAULT '' COMMENT '项目',
  `value` double(20,2) DEFAULT '0.00' COMMENT '值',
  `level` int(2) DEFAULT '0' COMMENT '报警级别',
  `warn_type` tinyint(2) DEFAULT NULL COMMENT '报警类型(1.偏低 2.偏高)',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态(1.待处理 2.处理中 3.人工已处理 4.系统已处理)',
  `handle_start_time` timestamp NULL DEFAULT NULL COMMENT '处理开始时间',
  `handle_end_time` timestamp NULL DEFAULT NULL COMMENT '处理结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='设备报警表';

/*Data for the table `device_warn_record` */

insert  into `device_warn_record`(`id`,`device_id`,`item`,`value`,`level`,`warn_type`,`status`,`handle_start_time`,`handle_end_time`,`create_time`,`update_time`) values (1,0,'冷库',2.00,0,2,3,'2017-12-03 15:50:09','2017-12-03 15:50:11','2017-12-01 11:29:54','2017-12-01 11:29:55'),(2,0,'锅炉',102.00,0,2,1,NULL,NULL,'2017-12-01 11:29:54','2017-12-01 11:29:55'),(3,0,'水箱',103.00,0,2,4,NULL,NULL,'2017-12-01 11:29:54','2017-12-13 18:07:43'),(4,0,'污水',50.00,0,2,1,NULL,NULL,'2017-12-02 09:29:53','2017-12-02 09:29:53'),(5,0,'冷库',3.00,0,2,2,NULL,NULL,'2017-12-01 11:29:55','2017-12-01 11:29:55'),(6,0,'污水',52.00,0,2,2,'2017-12-03 15:54:06',NULL,'2017-12-03 09:29:22','2017-12-03 15:54:06'),(7,0,'污水',53.00,0,2,2,'2017-12-03 15:54:06',NULL,'2017-12-03 09:29:22','2017-12-03 15:54:06'),(8,0,'污水',54.00,0,2,2,'2017-12-03 15:54:06',NULL,'2017-12-03 09:29:22','2017-12-03 15:54:06'),(9,1,'冷库',-23.40,1,2,1,NULL,NULL,'2017-12-09 15:19:18','2017-12-13 17:50:30'),(10,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:11:53','2017-12-17 05:12:00'),(11,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:15:00','2017-12-17 05:15:06'),(12,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:18:00','2017-12-17 05:18:06'),(13,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:21:00','2017-12-17 05:21:06'),(14,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:24:00','2017-12-17 05:24:06'),(15,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:27:00','2017-12-17 05:27:06'),(16,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:30:00','2017-12-17 05:30:06'),(17,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:33:00','2017-12-17 05:33:06'),(18,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:36:00','2017-12-17 05:36:06'),(19,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:39:00','2017-12-17 05:39:06'),(20,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:42:00','2017-12-17 05:42:06'),(21,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:45:00','2017-12-17 05:45:06'),(22,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:48:00','2017-12-17 05:48:06'),(23,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:51:00','2017-12-17 05:51:06'),(24,2,'水箱',100.00,1,2,4,NULL,NULL,'2017-12-17 05:54:01','2017-12-17 05:54:06'),(25,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 05:57:01','2017-12-17 05:57:06'),(26,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:00:01','2017-12-17 06:00:06'),(27,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:03:01','2017-12-17 06:03:06'),(28,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:06:01','2017-12-17 06:06:06'),(29,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:09:01','2017-12-17 06:09:06'),(30,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:12:00','2017-12-17 06:12:06'),(31,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:15:00','2017-12-17 06:15:07'),(32,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:18:01','2017-12-17 06:18:07'),(33,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:21:01','2017-12-17 06:21:07'),(34,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:24:01','2017-12-17 06:24:07'),(35,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:27:01','2017-12-17 06:27:07'),(36,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:30:01','2017-12-17 06:30:07'),(37,2,'水箱',101.00,1,2,4,NULL,NULL,'2017-12-17 06:33:01','2017-12-17 06:33:07'),(38,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:36:01','2017-12-17 06:36:07'),(39,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:39:01','2017-12-17 06:39:07'),(40,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:42:01','2017-12-17 06:42:07'),(41,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:45:01','2017-12-17 06:45:07'),(42,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:48:01','2017-12-17 06:48:07'),(43,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:51:01','2017-12-17 06:51:07'),(44,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:54:01','2017-12-17 06:54:07'),(45,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 06:57:01','2017-12-17 06:57:07'),(46,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 07:00:02','2017-12-17 07:00:07'),(47,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 07:03:02','2017-12-17 07:03:07'),(48,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 07:06:02','2017-12-17 07:06:07'),(49,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 07:09:02','2017-12-17 07:09:07'),(50,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 07:12:02','2017-12-17 07:12:07'),(51,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 07:15:01','2017-12-17 07:15:07'),(52,2,'水箱',102.00,1,2,4,NULL,NULL,'2017-12-17 07:18:01','2017-12-17 07:18:07'),(53,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:21:02','2017-12-17 07:21:08'),(54,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:24:02','2017-12-17 07:24:08'),(55,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:27:02','2017-12-17 07:27:08'),(56,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:30:02','2017-12-17 07:30:08'),(57,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:33:02','2017-12-17 07:33:08'),(58,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:36:02','2017-12-17 07:36:08'),(59,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:39:02','2017-12-17 07:39:08'),(60,2,'水箱',103.00,1,2,4,NULL,NULL,'2017-12-17 07:42:02','2017-12-17 07:42:08');

/*Table structure for table `device_warn_rule` */

DROP TABLE IF EXISTS `device_warn_rule`;

CREATE TABLE `device_warn_rule` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `item` varchar(100) DEFAULT '' COMMENT '项目',
  `status` smallint(2) DEFAULT '1' COMMENT '状态(1.禁用 2.启用)',
  `min_value` double(20,2) DEFAULT '0.00' COMMENT '阀值下限',
  `max_value` double(20,2) DEFAULT '0.00' COMMENT '阀值上限',
  `level` int(2) DEFAULT '0' COMMENT '报警级别',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `item` (`item`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='报警规则表';

/*Data for the table `device_warn_rule` */

insert  into `device_warn_rule`(`id`,`item`,`status`,`min_value`,`max_value`,`level`,`create_time`,`update_time`) values (1,'冷库',2,-45.00,-36.00,2,'2017-12-01 11:31:11','2017-12-03 14:44:46'),(2,'锅炉',2,80.00,100.00,1,'2017-12-01 11:31:29','2017-12-02 22:57:08'),(3,'污水',2,11.00,20.00,1,'2017-12-02 18:15:48','2017-12-02 22:59:53'),(4,'水箱',2,100.00,150.00,1,'2017-12-02 23:00:34','2017-12-04 16:39:22'),(5,'锅炉',2,121.00,130.00,3,'2017-12-04 16:45:28','2017-12-04 17:08:03'),(6,'锅炉',2,101.00,120.00,2,'2017-12-04 17:26:03','2017-12-04 17:26:03'),(7,'冷库',2,-35.00,-20.00,1,'2017-12-04 17:27:57','2017-12-04 17:38:18'),(8,'水箱',1,151.00,170.00,2,'2017-12-04 17:36:22','2017-12-04 17:36:22'),(9,'污水',2,21.00,30.00,2,'2017-12-04 18:31:21','2017-12-11 10:03:23');

/*Table structure for table `operation_log` */

DROP TABLE IF EXISTS `operation_log`;

CREATE TABLE `operation_log` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `oper_user_id` bigint(30) NOT NULL COMMENT '操作人',
  `oper_user_name` varchar(40) NOT NULL COMMENT '操作姓名',
  `target_uid` bigint(30) DEFAULT NULL COMMENT '被操作人用户ID',
  `ip_address` varchar(16) NOT NULL COMMENT 'ip地址',
  `oper_type` int(1) NOT NULL DEFAULT '0' COMMENT '日志类型 1.后台用户日志 2.资源日志(权限管理) ',
  `chat_id` bigint(30) DEFAULT '0' COMMENT '聊天id',
  `content` varchar(200) DEFAULT NULL COMMENT '操作内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录';

/*Data for the table `operation_log` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT 'Id主键',
  `account` varchar(20) NOT NULL DEFAULT '' COMMENT '用户帐号',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码(md5加密)',
  `status` smallint(2) NOT NULL DEFAULT '2' COMMENT '状态(1.禁用 2.启用)',
  `real_name` varchar(20) DEFAULT '' COMMENT '真实姓名',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机号',
  `email` varchar(30) DEFAULT '' COMMENT '邮箱',
  `sex` smallint(2) DEFAULT NULL COMMENT '性别(1.男 2.女)',
  `age` int(3) DEFAULT '0' COMMENT '年龄',
  `hotel` varchar(150) DEFAULT '' COMMENT '酒店',
  `dept` varchar(50) DEFAULT '' COMMENT '部门',
  `address` varchar(150) DEFAULT '' COMMENT '地址',
  `remark` varchar(300) DEFAULT '' COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`account`,`password`,`status`,`real_name`,`mobile`,`email`,`sex`,`age`,`hotel`,`dept`,`address`,`remark`,`create_time`,`update_time`) values (1,'rocking','e10adc3949ba59abbe56e057f20f883e',2,'刘武','18201979170','',1,22,'xx酒店','一部','','','2017-12-05 17:46:30','2017-12-07 12:30:47'),(3,'pushu001','e10adc3949ba59abbe56e057f20f883e',2,'朴树','13512345678','',1,38,'万豪大酒店','办公室','','','2017-12-09 16:13:24','2017-12-09 16:13:24');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
