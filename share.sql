-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: share
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article_message`
--

DROP TABLE IF EXISTS `article_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_message` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_content` longtext NOT NULL,
  `user_id` int(11) NOT NULL,
  `theme_id` int(11) NOT NULL DEFAULT '-1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_message`
--

LOCK TABLES `article_message` WRITE;
/*!40000 ALTER TABLE `article_message` DISABLE KEYS */;
INSERT INTO `article_message` VALUES (50,'<p style=\"text-align: center;\">《Umbrella》</p><p>You had my heart\' we\'ll never be a world apart</p><p>你拥有了我的心 我们不会被分隔在世界两端Maybe in magazines but you\'ll still be my star</p><p>可能你只会出现在杂志上 但你依旧是属于我的巨星</p><p>Baby \'cause in the dark you can see shiny cars Baby</p><p> 因为在黑暗中 你能看到闪烁的车灯</p><p>And that\'s when you need me there</p><p>那就是你需要我的时刻</p><p>With you I\'ll always share</p><p>我会永远和你分享</p><p><img src=\"https://1538933906.oss-cn-huhehaote.aliyuncs.com/https://1538933906.oss-accelerate.aliyuncs.com//share/images/2020-6-14/1592113246888144.png\" width=\"340\" style=\"\"></p><p><br></p><p><br></p>',11,3,'2020-06-29 05:41:00'),(51,'<p>桃夭-双笙</p><p>一首充满诗意的古风歌曲，节奏轻快 洒脱的曲风 有趣的是把二胡元素也揉进去，在副歌部分有曲折回旋的感觉，很有自己的风格，双笙作为一位具有隐藏实力型的歌手，她的经典歌曲都比较值得推荐</p><p>桃之夭夭，灼灼其华！<span class=\"ql-cursor\">﻿</span></p>',12,3,'2020-06-29 09:06:05'),(52,'<p style=\"text-align: center;\"><strong><em>《沉默的大多数》</em></strong></p><p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><br></p><hr><p style=\"text-align: center;\"><br></p><p style=\"text-align: left;\" class=\"ql-indent-1\">王小波说：我活在世上，无非想要明白些道理，</p><p style=\"text-align: left;\" class=\"ql-indent-1\">遇见些有趣的事。倘能如我所愿，我的一生就算成功。我开始得太晚了，很可能做不成什么，但我总得申明我的态度，所以就有了这本书——为我自己，也代表沉默的大多数。</p><p style=\"text-align: center;\"><br></p><hr><p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><img src=\"https://1538933906.oss-cn-huhehaote.aliyuncs.com/https://1538933906.oss-accelerate.aliyuncs.com//share/images/2020-6-14/159212552107180.png\" width=\"50%\"></img></p><p style=\"text-align: center;\"><br></p><p style=\"text-align: left;\" class=\"ql-indent-1\">在读这本是之前我对王小波知之甚少，也不明白</p><p style=\"text-align: left; line-height: 2;\">这个作家究竟有何种魅力，可以受到这么多的读者喜爱。当我读完后，发现在他的文章之中有包含着他对这个世界属于他的理性的看法。用调侃的语气来表现复杂的现象，用幽默的方式解给出他的观点。对于这样有趣的文章，喜欢的人自然不会少了。</p><p style=\"text-align: left; line-height: 2;\"><br></p><p style=\"text-align: left;\" class=\"ql-indent-1\">也许大部分人欣赏他的读者在读过他的文章后都</p><p style=\"text-align: left; line-height: 2;\">会有中相识恨晚的感觉吧，可能他表达出了大多是沉默者的心声也说不定。</p><p style=\"text-align: left;\"><br></p><p style=\"text-align: left;\"><br></p><hr><p style=\"text-align: left;\"><br></p><p style=\"text-align: left;\"><br></p><p style=\"text-align: center;\"><br></p>',9,2,'2020-06-29 09:30:01'),(54,'<p style=\"text-align: center;\">剑来</p><p style=\"text-align: center;\">大千世界，无奇不有。 我陈平安，唯有一剑，可搬山，倒海，降妖，镇魔，敕神，摘星，断江，摧城，开天！ 我叫陈平安，平平安安的平安。我是一名剑客。</p><p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><img src=\"https://1538933906.oss-cn-huhehaote.aliyuncs.com/https://1538933906.oss-accelerate.aliyuncs.com//share/images/2020-6-14/159212862759532.png\" width=\"50%\"></p><p style=\"text-align: center;\"><br></p><p style=\"text-align: center;\"><br></p>',11,2,'2020-06-29 09:57:13'),(57,'<p style=\"text-align: center;\">《边城》</p><p><br></p><p>凡事都有偶然的凑巧，结果却又如宿命的必然。                                                      —— 沈从文</p><hr><p><br></p><p><img src=\"https://1538933906.oss-cn-huhehaote.aliyuncs.com/https://1538933906.oss-accelerate.aliyuncs.com//share/images/2020-6-14/159213147948577.png\" width=\"305\" style=\"\"></p><p><br></p><hr><p><br></p><p>《边城》是沈从文的代表作。这篇作品如沈从文的其他湘西作品，着眼于普通人、善良人的命运变迁，描摹了湘女翠翠阴差阳错的生活悲剧。</p><p>《边城》讲述的故事凄美动人。它没有惊心动魄的情节，也没有撕心裂肺的感人场面，但他细腻而且真实，像一根轻柔的丝线悠悠地牵动你心灵的一角，使你在不知不觉间已然泪落满面。</p><p><br></p>',11,2,'2020-06-29 10:46:45'),(83,'<p style=\"text-align: center;\"><strong>这是演示文章<span class=\"ql-cursor\">﻿</span></strong></p>',14,1,'2020-06-29 10:39:45'),(92,'<p>I\'m interested in the app.  I just try it</p>',15,-1,'2020-07-01 01:52:38'),(93,'<h1 wx:nodeid=\"94\">你好呀！！！<strong wx:nodeid=\"96\">123456789</strong></h1>',10,2,'2020-10-13 08:14:08');
/*!40000 ALTER TABLE `article_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_message`
--

DROP TABLE IF EXISTS `comment_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_message` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_content` longtext NOT NULL,
  `article_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_message`
--

LOCK TABLES `comment_message` WRITE;
/*!40000 ALTER TABLE `comment_message` DISABLE KEYS */;
INSERT INTO `comment_message` VALUES (73,'666',50,9,'2020-06-14 05:47:20'),(74,'666',50,12,'2020-06-14 09:48:19'),(75,'哇  这就是沉默的大多数吗？\n真是有够沉默的呢！',52,11,'2020-06-14 09:51:29'),(76,'好分析\n',51,11,'2020-06-14 09:54:45'),(77,'侠之大者，为国为民',54,12,'2020-06-14 10:54:47'),(78,'真是够沉默的呢',52,12,'2020-06-14 10:55:53'),(79,'1',57,9,'2020-06-14 12:04:00'),(80,'1',71,10,'2020-06-15 06:06:49'),(81,'2',71,10,'2020-06-15 06:06:51'),(82,'3',71,10,'2020-06-15 06:06:55'),(83,'4',71,10,'2020-06-15 06:06:58'),(84,'5',71,10,'2020-06-15 06:07:00'),(85,'5',71,10,'2020-06-15 06:07:00'),(86,'5',71,10,'2020-06-15 06:07:00'),(87,'5',71,10,'2020-06-15 06:07:00'),(88,'6',71,10,'2020-06-15 06:09:23'),(89,'8',71,10,'2020-06-15 06:09:27'),(90,'123',71,10,'2020-06-15 06:10:35'),(91,'123',71,10,'2020-06-15 06:10:42'),(92,'123432',71,10,'2020-06-15 06:11:55'),(93,'12312312312',71,10,'2020-06-15 06:11:59'),(94,'1231232321341234',71,10,'2020-06-15 06:12:03'),(95,'23434234324324234',71,10,'2020-06-15 06:12:08'),(96,'435345',71,10,'2020-06-15 06:12:10'),(97,'123123',71,10,'2020-06-15 06:12:32'),(100,'评论123',57,10,'2020-06-15 10:23:43'),(107,'这是演示评论',83,10,'2020-06-15 11:55:48'),(108,'这是演示评论',83,10,'2020-06-15 11:57:43'),(110,'interesting',51,15,'2020-07-01 01:51:26'),(111,'你好呀',93,10,'2020-10-13 08:14:24'),(112,'这是评论',93,10,'2020-10-13 08:14:50'),(113,'对方可以看到通知并回复',93,10,'2020-10-13 08:15:01');
/*!40000 ALTER TABLE `comment_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_message`
--

DROP TABLE IF EXISTS `like_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_message` (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_message`
--

LOCK TABLES `like_message` WRITE;
/*!40000 ALTER TABLE `like_message` DISABLE KEYS */;
INSERT INTO `like_message` VALUES (110,14,52,'2020-06-15 09:24:07'),(112,14,84,'2020-06-15 10:41:10'),(113,10,85,'2020-06-15 10:44:34'),(115,10,87,'2020-06-15 10:59:16'),(116,10,52,'2020-06-15 11:15:20'),(117,10,90,'2020-06-15 11:16:32'),(118,10,91,'2020-06-15 11:24:00'),(120,10,83,'2020-06-29 04:02:25'),(122,15,51,'2020-07-01 01:51:12'),(123,16,52,'2020-07-01 02:05:31'),(124,10,92,'2020-07-10 05:48:05'),(125,10,57,'2020-10-13 07:50:25'),(126,10,93,'2020-10-13 08:14:17');
/*!40000 ALTER TABLE `like_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice_message`
--

DROP TABLE IF EXISTS `notice_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_message` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_type` int(11) NOT NULL,
  `send_user_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `replay_id` int(11) DEFAULT NULL,
  `like_id` int(11) DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `content` longtext,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_message`
--

LOCK TABLES `notice_message` WRITE;
/*!40000 ALTER TABLE `notice_message` DISABLE KEYS */;
INSERT INTO `notice_message` VALUES (168,2,NULL,9,NULL,110,NULL,52,NULL,'2020-06-15 09:24:07'),(170,3,NULL,11,NULL,NULL,100,57,NULL,'2020-06-15 10:23:43'),(177,6,10,9,NULL,NULL,NULL,52,'我举报这篇文章,查看详情','2020-06-15 10:51:07'),(178,6,10,9,NULL,NULL,NULL,52,'我举报这篇文章,查看详情','2020-06-15 10:54:13'),(179,6,10,9,NULL,NULL,NULL,52,'我举报这篇文章,查看详情','2020-06-15 11:03:13'),(180,2,NULL,9,NULL,116,NULL,52,NULL,'2020-06-15 11:15:20'),(182,3,NULL,14,NULL,NULL,107,83,NULL,'2020-06-15 11:55:48'),(183,3,NULL,14,NULL,NULL,107,83,NULL,'2020-06-15 11:57:43'),(185,2,NULL,14,NULL,120,NULL,83,NULL,'2020-06-29 04:02:25'),(187,2,NULL,12,NULL,122,NULL,51,NULL,'2020-07-01 01:51:12'),(188,3,NULL,12,NULL,NULL,110,51,NULL,'2020-07-01 01:51:26'),(189,2,NULL,9,NULL,123,NULL,52,NULL,'2020-07-01 02:05:31'),(190,2,NULL,15,NULL,124,NULL,92,NULL,'2020-07-10 05:48:05'),(191,1,10,15,NULL,NULL,NULL,NULL,'感谢你的体验😝','2020-07-10 05:49:35'),(192,2,NULL,11,NULL,125,NULL,57,NULL,'2020-10-13 07:50:25');
/*!40000 ALTER TABLE `notice_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replay_message`
--

DROP TABLE IF EXISTS `replay_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replay_message` (
  `replay_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `replay_user_id` int(11) DEFAULT NULL,
  `replay_content` longtext NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`replay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replay_message`
--

LOCK TABLES `replay_message` WRITE;
/*!40000 ALTER TABLE `replay_message` DISABLE KEYS */;
INSERT INTO `replay_message` VALUES (1,76,12,NULL,'有眼光','2020-06-14 10:55:17'),(2,79,9,NULL,'2','2020-06-14 12:04:50'),(3,81,10,NULL,'哈哈','2020-06-15 06:29:35'),(4,93,10,NULL,'123','2020-06-15 06:30:45'),(6,97,10,NULL,'123123123','2020-06-15 06:33:08'),(7,96,10,NULL,'12312321123','2020-06-15 06:33:42'),(8,95,10,NULL,'`121`2123','2020-06-15 06:33:52'),(10,107,10,NULL,'这是演示回复','2020-06-15 11:56:06'),(11,111,10,NULL,'你好呀','2020-10-13 08:14:41');
/*!40000 ALTER TABLE `replay_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `swiper_message`
--

DROP TABLE IF EXISTS `swiper_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `swiper_message` (
  `swiper_id` int(11) NOT NULL AUTO_INCREMENT,
  `swiper_url` varchar(255) NOT NULL,
  `swiper_type` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `web_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`swiper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `swiper_message`
--

LOCK TABLES `swiper_message` WRITE;
/*!40000 ALTER TABLE `swiper_message` DISABLE KEYS */;
INSERT INTO `swiper_message` VALUES (1,'https://s1.ax1x.com/2020/06/13/tvO2X6.jpg','1',NULL,NULL,'欢迎您的到来！',NULL),(2,'https://s1.ax1x.com/2020/06/13/tvOg6x.jpg','2',9,NULL,'',NULL),(3,'https://s1.ax1x.com/2020/06/13/tvOc11.jpg','3',NULL,52,'',NULL);
/*!40000 ALTER TABLE `swiper_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme_message`
--

DROP TABLE IF EXISTS `theme_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theme_message` (
  `theme_id` int(11) NOT NULL AUTO_INCREMENT,
  `theme_title` varchar(255) NOT NULL,
  `theme_intro` varchar(255) NOT NULL,
  `theme_use` int(11) NOT NULL DEFAULT '1',
  `theme_image` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`theme_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme_message`
--

LOCK TABLES `theme_message` WRITE;
/*!40000 ALTER TABLE `theme_message` DISABLE KEYS */;
INSERT INTO `theme_message` VALUES (1,'日常','主题介绍',1,'https://s1.ax1x.com/2020/06/14/tzrdoD.png','2020-06-14 08:32:53'),(2,'书籍','主题介绍',1,'https://s1.ax1x.com/2020/06/14/tzrtL6.png','2020-06-14 08:33:19'),(3,'音乐','主题介绍',1,'https://s1.ax1x.com/2020/06/14/tzradO.png','2020-06-14 08:33:03'),(4,'电影','主题介绍',1,'https://s1.ax1x.com/2020/06/14/tzrUeK.png','2020-06-14 08:33:14');
/*!40000 ALTER TABLE `theme_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_message`
--

DROP TABLE IF EXISTS `user_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_message` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_nickname` varchar(255) NOT NULL,
  `user_gender` tinyint(4) NOT NULL,
  `user_token` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(255) NOT NULL DEFAULT '',
  `user_other` varchar(255) DEFAULT '',
  `user_city` varchar(255) DEFAULT '',
  `user_age` varchar(255) DEFAULT '0',
  `user_province` varchar(255) DEFAULT '',
  `user_country` varchar(255) DEFAULT '',
  `user_admin` enum('2','1') NOT NULL DEFAULT '1',
  `user_allow` enum('1','2') NOT NULL DEFAULT '1',
  `user_motto` longtext,
  `user_phone` varchar(255) DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_message`
--

LOCK TABLES `user_message` WRITE;
/*!40000 ALTER TABLE `user_message` DISABLE KEYS */;
INSERT INTO `user_message` VALUES (9,'进口凉白开',1,'oMyNA5d4nrDTcnztQ4iQNpDxcWBg','https://wx.qlogo.cn/mmopen/vi_32/6ibBsxibjxCoo867UAvicbiae4oDOAicsDxHY8fvlFibcLicyE7BmliaoibWWibPlHrQ1uT9ypJT9zsU0XXAkB7CqXTJKMJA/132','','西安','0','陕西','中国','2','1','这个人很懒,什么也没有留下','','2020-06-14 04:27:25'),(10,'勿 忘 我',1,'oMyNA5cv4NoSlv5bgHwfAt55zAyQ','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI4mUsxJjImu6e9xMiccYnDenQL9c0aNgIbKHHiciczgmicpMxCbG35sMg7iczLHMialgZtVM0RibpU9FbNg/132','','西安','0','陕西','中国','1','1','这个人很懒,什么也没有留下','','2020-06-14 04:28:47'),(11,'ʚ👿ɞ',1,'oMyNA5fXNYMQ4A1yDWfuggYO6ZPE','https://wx.qlogo.cn/mmopen/vi_32/d43LH2205oSRD5Tc3I6Jg9oOpmEpOrSI8LAUzCDpHFf0KWl2KJNazLSp4GPtDOaHschtiaVfoWB125k78x48K5w/132','','渭南','0','陕西','中国','1','1','这个人很懒,什么也没有留下','','2020-06-14 05:35:41'),(12,'ェ故酒难温ガ',0,'oMyNA5XviVmXgl558yW6niXpCIRQ','https://wx.qlogo.cn/mmopen/vi_32/Y4WRUrVYt8sLVnJU6jLkUiad6r54ey0M0sicB58qCIkiaBt1h0lOiaWQExAvRLC0Ovn2bbqlTCegTOibaRyZrzvQDqg/132','','','0','','','1','1','这个人很懒,什么也没有留下','','2020-06-14 09:02:01'),(13,'Passerby',1,'oMyNA5aQIdb-09zfjxv8UPV-qDUA','https://wx.qlogo.cn/mmopen/vi_32/99B8vcao4j7Ax7fCd3tfSYJ2EOYaNSqLfOqicokFTbOhwEA6GR3Lf8BhZic4YmR0WZGsssnicevcTGDYnDzMiazqpg/132','','咸阳','0','陕西','中国','1','1','这个人很懒,什么也没有留下','','2020-06-14 11:15:57'),(14,'芷若初心',0,'oMyNA5S1V4nF-ur7YQ99yFgy4BNQ','https://wx.qlogo.cn/mmopen/vi_32/2ECkrMyefXJwdrtP0Zb2yKwOEYFWlk7cjf6ve1EwNxkBnZVoQ0PFmHbaicO9KQqaSmSibAPPLV01QG6nby0cVCSA/132','','西安','0','陕西','中国','1','1','这个人很懒,什么也没有留下','','2020-06-15 03:47:44'),(15,'李劲华',0,'oMyNA5Zz9YnStixlejveXjx9mHTw','https://wx.qlogo.cn/mmopen/vi_32/NicMfJ7OzpiaS7S4DdPHicXG1PDjjCIYVSPZdvl5QJ543oZun1ibcvqqickm66icQfWyP6vWNCREbNZWib3zOIp7hWOOQ/132','','','0','斯图加特','德国','1','1','这个人很懒,什么也没有留下','','2020-07-01 01:50:40'),(16,'R.F',1,'oMyNA5b3rYRcHTyZerTvmFiM3zEE','https://wx.qlogo.cn/mmopen/vi_32/ArTRk9WV8KDkWOdDibmjem9ctI9f7DWpaZQsqPuI1sYRKUZNlIRNYavvb7nzx202rVPXEDBnHibQy9KIKKTOgrhg/132','','青岛','0','山东','中国','1','1','Study hard','','2020-07-01 02:01:39'),(17,'Sandy',2,'oMyNA5R5DgQUiIu6xYoegFTGy0AQ','https://wx.qlogo.cn/mmopen/vi_32/XUzvuoBCNx14MJjrWZBM7RWmliafaLp2SuMojAh86dSYEeibqNbyoyG6JQnPYa81XOGfLo9xOskUQpWhLichflgJw/132','','长沙','0','湖南','中国','1','1','这个人很懒,什么也没有留下','','2020-07-02 03:54:59'),(18,'运筹帷幄',1,'oMyNA5edU3-RINuAt8JWlg2KVjaA','https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqeSjRts5ILydN2Ta1LmCf3De0P1KKL1vt9nf8CLDpDTpX2uGHSEjw1DibI7IOJ6iaj7Y3YCBYsxong/132','','石景山','0','北京','中国','1','1','这个人很懒,什么也没有留下','','2020-07-13 16:11:19');
/*!40000 ALTER TABLE `user_message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-13 20:05:15
