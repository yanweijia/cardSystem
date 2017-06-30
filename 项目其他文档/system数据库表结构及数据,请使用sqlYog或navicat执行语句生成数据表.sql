/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.52-0ubuntu0.14.04.1 : Database - system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `system`;

/*Table structure for table `book_account` */

DROP TABLE IF EXISTS `book_account`;

CREATE TABLE `book_account` (
  `id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户编号',
  `max_borrow_num` smallint(5) unsigned NOT NULL DEFAULT '7' COMMENT '最大借阅数',
  `borrowed_num` smallint(6) NOT NULL DEFAULT '0' COMMENT '已借阅数',
  `register_date` date NOT NULL COMMENT '用户注册日期',
  PRIMARY KEY (`id`),
  CONSTRAINT `book_account_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户图书账户表';

/*Data for the table `book_account` */

insert  into `book_account`(`id`,`max_borrow_num`,`borrowed_num`,`register_date`) values ('1001',7,0,'2017-06-28');

/*Table structure for table `book_borrow` */

DROP TABLE IF EXISTS `book_borrow`;

CREATE TABLE `book_borrow` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户编号',
  `book_id` int(10) unsigned NOT NULL COMMENT '书籍编号',
  `borrowtime` date NOT NULL COMMENT '借书时间',
  `s_backtime` date NOT NULL COMMENT '应还时间',
  `ifback` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否归还',
  `backtime` date DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `book_borrow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `book_borrow_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book_info` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='图书借阅表';

/*Data for the table `book_borrow` */

insert  into `book_borrow`(`id`,`user_id`,`book_id`,`borrowtime`,`s_backtime`,`ifback`,`backtime`) values (1,'1001',1,'2017-06-28','2017-07-01',0,NULL);

/*Table structure for table `book_info` */

DROP TABLE IF EXISTS `book_info`;

CREATE TABLE `book_info` (
  `book_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `type` enum('A马列主义毛邓思想','B哲学','C社会科学总论','D政治、法律','E军事','F经济','G文化、科学、教育、体育','H语言、文字','I文学','J艺术','K历史、地理','N自然科学总论','O数理科学和化学','P天文学、地球科学','Q生物科学','R医药、卫生','S农业科学','T工业技术','U交通运输','V航空、航天','X环境科学、安全科学','Z综合性图书') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Z综合性图书' COMMENT '图书类别',
  `author` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '未知作者' COMMENT '作者',
  `publisher` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '未知出版社' COMMENT '出版社名称',
  `pub_date` date DEFAULT NULL COMMENT '出版日期',
  `register_date` date NOT NULL COMMENT '登记日期',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '图书价格',
  `isbn` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ISBN编号',
  `bookrack` varchar(30) COLLATE utf8_unicode_ci DEFAULT '新书上架书架' COMMENT '书架及位置',
  `total_num` smallint(5) unsigned DEFAULT '0' COMMENT '书籍总共本数',
  `remain_num` smallint(5) unsigned DEFAULT '0' COMMENT '剩余本数',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='图书信息表';

/*Data for the table `book_info` */

insert  into `book_info`(`book_id`,`name`,`type`,`author`,`publisher`,`pub_date`,`register_date`,`price`,`isbn`,`bookrack`,`total_num`,`remain_num`) values (1,'深入浅出MySQL 数据库开发 优化与管理维护 第2版','T工业技术','唐汉明，翟振兴，关宝军','人民邮电出版社','2017-06-01','2017-06-27','106.20','9787115335494','新书上架书架',10,9),(2,'拳力以赴：打出来的梦想家','G文化、科学、教育、体育','邹市明','中信出版集团','2017-01-01','2017-06-27','36.80','9787508672830','1号书架100层',2,2),(3,'Go程序设计语言','G文化、科学、教育、体育','艾伦A. A. 多诺万','机械工业出版社','2017-05-06','2017-06-27','64.70','9787111558422','1号书架12层',1,1),(4,'东野圭吾：大雪中的山庄','I文学','〔日〕东野圭吾','北京十月文艺出版社','2016-01-01','2017-06-27','31.10','9787530216835','2号书架12层',3,3),(5,'孩子不挑食妈妈不担心：美味的朋友（套装全8册） [2-6岁]','R医药、卫生','丰田一彦 著；季颖 译','长江少年儿童出版社','2013-05-05','2017-06-27','101.90','12174530','4号书架3层',4,4),(6,'本能减脂','R医药、卫生','张景琦 孟令超','中信出版集团','2012-04-04','2017-06-27','35.70','9787508674728','2号书架20层',5,5),(7,'万物起源','N自然科学总论','英国《新科学家》杂志 ','湖南科学技术出版社','2017-05-09','2017-06-27','73.70','9787535792082','1号书架34层',6,6),(8,'亿级流量网站架构核心技术 跟开涛学搭建高可用高并发系统','T工业技术','张开涛','电子工业出版社','2014-06-05','2017-06-27','82.20','9787121309540','5号书架5层',7,7),(9,'凯迪克金奖绘本 寻找维尼 [4-12岁]','Z综合性图书','林赛·马蒂克','长江少年儿童出版社','2015-06-06','2017-06-27','39.60','9787556045549','1号书架12层',8,8),(10,'新版丁丁历险记 ','G文化、科学、教育、体育','[比] 埃尔热 著；王炳东 译','中国少年儿童出版社','2014-04-05','2017-06-27','327.40','11358855','5号书架12层',1,1),(11,'外婆的道歉信','Z综合性图书','弗雷德里克巴·克曼 著；孟汇一 译','天津人民出版社','2016-05-06','2017-06-27','31.20','9787201116693','新书上架书架',2,2),(12,'最后的精灵','Z综合性图书','希瓦娜·达玛利 著；景翔 译','湖南文艺出版社','2013-08-09','2017-06-27','28.00','9787540480783','新书上架书架',3,3);

/*Table structure for table `consume` */

DROP TABLE IF EXISTS `consume`;

CREATE TABLE `consume` (
  `id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户编号',
  `time` datetime NOT NULL COMMENT '消费时间,当前时间',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户消费金额',
  `goods_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '无名称' COMMENT '消费项目名称',
  PRIMARY KEY (`id`,`time`),
  CONSTRAINT `consume_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='消费(资金变动)表';

/*Data for the table `consume` */

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `course_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `course_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '课程名称',
  `start_date` date NOT NULL COMMENT '课程开始时间',
  `end_date` date NOT NULL COMMENT '课程结束时间',
  `organization_id` int(10) unsigned DEFAULT NULL COMMENT '所属部门/学院编号',
  PRIMARY KEY (`course_id`),
  KEY `organization_id` (`organization_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5557 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='课程表';

/*Data for the table `course` */

insert  into `course`(`course_id`,`course_name`,`start_date`,`end_date`,`organization_id`) values (5556,'测试课程理论知识','2017-06-01','2017-08-16',12);

/*Table structure for table `course_choice` */

DROP TABLE IF EXISTS `course_choice`;

CREATE TABLE `course_choice` (
  `user_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '学生编号',
  `course_id` int(10) unsigned NOT NULL COMMENT '课程编号',
  `grade` decimal(10,2) DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`user_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_choice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_choice_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='学生选课表';

/*Data for the table `course_choice` */

/*Table structure for table `dormitory` */

DROP TABLE IF EXISTS `dormitory`;

CREATE TABLE `dormitory` (
  `building_no` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '楼号',
  `dormitory_no` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '101A' COMMENT '宿舍号',
  `max_num` smallint(5) unsigned DEFAULT '4' COMMENT '可住人数',
  `now_num` smallint(5) unsigned DEFAULT '0' COMMENT '已住人数',
  PRIMARY KEY (`building_no`,`dormitory_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='宿舍表';

/*Data for the table `dormitory` */

insert  into `dormitory`(`building_no`,`dormitory_no`,`max_num`,`now_num`) values ('22','301A',4,1),('24','302B',4,1),('25','506C',4,1),('26','406A',4,0),('26','506C',4,1),('27','506C',4,1),('28','506C',4,1),('37','506C',4,1);

/*Table structure for table `dormitory_apply` */

DROP TABLE IF EXISTS `dormitory_apply`;

CREATE TABLE `dormitory_apply` (
  `apply_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请编号',
  `user_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '申请人',
  `radnom` tinyint(1) NOT NULL COMMENT '是否随机',
  `building_no` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '楼号',
  `dormitory_no` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '宿舍号',
  `apply_time` datetime NOT NULL COMMENT '申请时间',
  `result` tinyint(1) NOT NULL COMMENT '是否审查过(通过驳回都算)',
  PRIMARY KEY (`apply_id`),
  KEY `dormitory_apply_ibfk_2` (`building_no`,`dormitory_no`),
  KEY `dormitory_apply_ibfk_1` (`user_id`),
  CONSTRAINT `dormitory_apply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dormitory_apply_ibfk_2` FOREIGN KEY (`building_no`, `dormitory_no`) REFERENCES `dormitory` (`building_no`, `dormitory_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='宿舍入住申请表';

/*Data for the table `dormitory_apply` */

/*Table structure for table `dormitory_user` */

DROP TABLE IF EXISTS `dormitory_user`;

CREATE TABLE `dormitory_user` (
  `building_no` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '楼号',
  `dormitory_no` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '101A' COMMENT '宿舍号',
  `bed_no` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '1' COMMENT '床号',
  `user_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '入住人',
  PRIMARY KEY (`building_no`,`dormitory_no`,`bed_no`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `dormitory_user_ibfk_1` FOREIGN KEY (`building_no`, `dormitory_no`) REFERENCES `dormitory` (`building_no`, `dormitory_no`),
  CONSTRAINT `dormitory_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='宿舍入住表';

/*Data for the table `dormitory_user` */

/*Table structure for table `organization` */

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `organization_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织机构编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '组织机构名称',
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='组织机构表';

/*Data for the table `organization` */

insert  into `organization`(`organization_id`,`name`) values (1,'能源动力学院'),(2,'能源与机械工程学院'),(3,'环境与化学工程学院'),(4,'教务处'),(5,'科研处'),(6,'电气工程学院'),(7,'自动化工程学院'),(8,'学生处'),(9,'研究生处'),(10,'人事处'),(11,'财务处'),(12,'计算机科学与技术学院'),(13,'电子与信息工程学院'),(14,'123'),(15,'456'),(16,'校长办公室');

/*Table structure for table `request_modify` */

DROP TABLE IF EXISTS `request_modify`;

CREATE TABLE `request_modify` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '请求编号',
  `user_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户编号',
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '新电话',
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '新邮箱',
  `addr` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '新家庭住址',
  `validate` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已生效',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `request_modify_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户修改信息审核表';

/*Data for the table `request_modify` */

/*Table structure for table `section` */

DROP TABLE IF EXISTS `section`;

CREATE TABLE `section` (
  `section_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `organization_id` int(10) unsigned DEFAULT NULL COMMENT '所属组织机构编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '科室/班级名称',
  PRIMARY KEY (`section_id`),
  KEY `organization_id` (`organization_id`),
  CONSTRAINT `section_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='科室班级表';

/*Data for the table `section` */

insert  into `section`(`section_id`,`organization_id`,`name`) values (1,1,'能动默认分组'),(2,2,'能机默认分组'),(3,3,'环化默认分组'),(4,4,'教务默认分组'),(5,5,'科研默认分组'),(6,6,'电气默认分组'),(7,7,'自动化默认分组'),(8,8,'学生处默认分组'),(9,9,'研究生默认分组'),(10,10,'人事处默认分组'),(11,11,'财务处默认分组'),(12,12,'计算机默认分组'),(13,13,'电信默认分组'),(14,14,'789'),(15,15,'456部门'),(16,14,'123默认部门'),(17,16,'小秘书');

/*Table structure for table `teaching` */

DROP TABLE IF EXISTS `teaching`;

CREATE TABLE `teaching` (
  `teacher_id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '教师编号',
  `course_id` int(10) unsigned NOT NULL COMMENT '课程编号',
  PRIMARY KEY (`teacher_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `teaching_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teaching_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='授课表';

/*Data for the table `teaching` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户编号(学号,教职工号等)',
  `password` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码',
  `type` enum('系统管理员','一卡通管理员','学生','教学系统管理员','图书管理员','宿舍管理员') COLLATE utf8_unicode_ci NOT NULL DEFAULT '学生' COMMENT '用户类型',
  `available` enum('正常','正常销卡','退学销卡','休学销卡') COLLATE utf8_unicode_ci NOT NULL DEFAULT '正常' COMMENT '用户状态是否可用',
  `name` varchar(15) COLLATE utf8_unicode_ci NOT NULL DEFAULT '无名氏' COMMENT '姓名',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '一卡通余额',
  `organization` int(10) unsigned DEFAULT NULL COMMENT '用户组织机构',
  `section` int(10) unsigned DEFAULT NULL COMMENT '科室/班级',
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT 'NULL' COMMENT '电话',
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT 'NULL' COMMENT '邮箱',
  `addr` varchar(30) COLLATE utf8_unicode_ci DEFAULT 'NULL' COMMENT '家庭住址',
  PRIMARY KEY (`id`),
  KEY `organization` (`organization`),
  KEY `section` (`section`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`organization`) REFERENCES `organization` (`organization_id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`section`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户账户表';

/*Data for the table `user` */

insert  into `user`(`id`,`password`,`type`,`available`,`name`,`balance`,`organization`,`section`,`phone`,`email`,`addr`) values ('1000','e10adc3949ba59abbe56e057f20f883e','系统管理员','正常','管理员','15.62',10,10,'18621703545','admin@test.com','上海市浦东新区学海路28号上海电力学院'),('1001','e10adc3949ba59abbe56e057f20f883e','学生','正常','','0.00',13,NULL,'18621703545','admin@yanweijia.cn','上海市这是地址'),('1002','e10adc3949ba59abbe56e057f20f883e','教学系统管理员','正常','李四','0.00',12,12,'16666666666','hehe@222.com','伪造数据好累啊'),('1003','e10adc3949ba59abbe56e057f20f883e','图书管理员','正常','王五','0.00',12,12,'11211113333','haha@dsd.cn','我不想打字了'),('1004','e10adc3949ba59abbe56e057f20f883e','宿舍管理员','正常','张二三','0.00',12,12,'22222222222','sdlkjg@sdf.com','为什么要打这么多字'),('1005','e10adc3949ba59abbe56e057f20f883e','学生','正常','张三三','0.00',12,12,'55555555555','sdgsdf@sdf.org','啊啊啊啊啊这是地址'),('1006','e10adc3949ba59abbe56e057f20f883e','学生','正常','张思三','0.00',12,12,'55555555555','lksjdg@kugou.com','看到没我是地址'),('1007','e10adc3949ba59abbe56e057f20f883e','学生','正常','张五三','0.00',12,12,'44444444444','kugou@123.cn','地址地址是地址'),('1008','e10adc3949ba59abbe56e057f20f883e','一卡通管理员','正常','严唯嘉','0.00',10,10,'18621703545','NULL','NULL'),('12345','e10adc3949ba59abbe56e057f20f883e','学生','正常','哈哈','0.00',5,5,NULL,NULL,NULL),('2001','e10adc3949ba59abbe56e057f20f883e','一卡通管理员','正常','尼古拉斯','0.00',16,17,'1237767899','NULL','NULL');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
