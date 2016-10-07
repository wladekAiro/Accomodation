-- MySQL dump 10.13  Distrib 5.6.19, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_accomodation
-- ------------------------------------------------------
-- Server version	5.6.19-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bed`
--

DROP TABLE IF EXISTS `bed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `bed_type` varchar(255) DEFAULT NULL,
  `number` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mgfypsjne6v29yqaya1ngrrfs` (`room_id`),
  KEY `FK_ll08rt0pbj1eh4j2yrarod0lm` (`student_id`),
  CONSTRAINT `FK_ll08rt0pbj1eh4j2yrarod0lm` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_mgfypsjne6v29yqaya1ngrrfs` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bed`
--

LOCK TABLES `bed` WRITE;
/*!40000 ALTER TABLE `bed` DISABLE KEYS */;
INSERT INTO `bed` VALUES (1,'2016-09-29 01:53:47','2016-10-06 17:31:17','UPPER','Z01-H01-B01-R001-BD1','RESERVED',1,2),(2,'2016-09-29 02:47:05','2016-10-07 21:37:12','UPPER','Z01-H01-B01-R001-BD2','BOOKED',1,3);
/*!40000 ALTER TABLE `bed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `block` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `hostel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_88gm2tpcgirbcbonafv6lind1` (`hostel_id`),
  CONSTRAINT `FK_88gm2tpcgirbcbonafv6lind1` FOREIGN KEY (`hostel_id`) REFERENCES `hostel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (1,'2016-09-29 01:20:02','2016-09-29 01:20:02','B01',0,'Block1',1),(2,'2016-09-30 02:25:02','2016-10-06 20:51:17','BO2',1,'Block2',1);
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel`
--

DROP TABLE IF EXISTS `hostel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `zone_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_74qmif07dnvqcu59gk2b4lsb2` (`zone_id`),
  CONSTRAINT `FK_74qmif07dnvqcu59gk2b4lsb2` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel`
--

LOCK TABLES `hostel` WRITE;
/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` VALUES (1,'2016-09-29 01:19:08','2016-10-06 20:36:09','H01','Ruwenzori',1);
/*!40000 ALTER TABLE `hostel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `non_resident`
--

DROP TABLE IF EXISTS `non_resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `non_resident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `hostel_name` varchar(255) DEFAULT NULL,
  `land_lord_full_name` varchar(255) DEFAULT NULL,
  `land_lord_phone_number` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `sem_count` int(11) DEFAULT NULL,
  `profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l7b09a7p0ebuepfien3pxunph` (`profile_id`),
  CONSTRAINT `FK_l7b09a7p0ebuepfien3pxunph` FOREIGN KEY (`profile_id`) REFERENCES `student_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_resident`
--

LOCK TABLES `non_resident` WRITE;
/*!40000 ALTER TABLE `non_resident` DISABLE KEYS */;
/*!40000 ALTER TABLE `non_resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_users`
--

DROP TABLE IF EXISTS `role_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_users` (
  `roles_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  PRIMARY KEY (`roles_id`,`users_id`),
  KEY `FK_ljs8l2207x0igrfp8dw2edxql` (`users_id`),
  CONSTRAINT `FK_fdnyy4mlhg205occtsr1tb05w` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_ljs8l2207x0igrfp8dw2edxql` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_users`
--

LOCK TABLES `role_users` WRITE;
/*!40000 ALTER TABLE `role_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `capacity` int(11) NOT NULL,
  `cost` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `block_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s9b43gqod51f0c2xuhy48jy42` (`block_id`),
  CONSTRAINT `FK_s9b43gqod51f0c2xuhy48jy42` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'2016-09-29 01:21:04','2016-10-06 21:24:36',4,2400,'R001','QUADRUPLE',1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_item`
--

DROP TABLE IF EXISTS `room_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `clear_status` int(11) DEFAULT NULL,
  `cost` bigint(20) DEFAULT NULL,
  `item_condition` int(11) DEFAULT NULL,
  `item_name` int(11) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s5tsd1fb4bk5l9qsws1asjgbt` (`student_id`),
  CONSTRAINT `FK_s5tsd1fb4bk5l9qsws1asjgbt` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_item`
--

LOCK TABLES `room_item` WRITE;
/*!40000 ALTER TABLE `room_item` DISABLE KEYS */;
INSERT INTO `room_item` VALUES (23,'2016-09-30 01:37:31','2016-10-06 14:46:25',0,50,0,1,2),(24,'2016-09-30 01:37:31','2016-10-06 14:47:10',0,50,0,3,2),(25,'2016-09-30 01:37:31','2016-09-30 01:37:31',2,50,NULL,2,2),(26,'2016-09-30 01:37:31','2016-10-04 12:13:09',3,50,NULL,0,2),(28,'2016-10-07 21:37:12','2016-10-07 21:37:12',2,50,0,0,3);
/*!40000 ALTER TABLE `room_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_item_cost`
--

DROP TABLE IF EXISTS `room_item_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_item_cost` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `unit_cost` bigint(20) NOT NULL,
  `total_available` bigint(20) NOT NULL,
  `total_issued` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_item_cost`
--

LOCK TABLES `room_item_cost` WRITE;
/*!40000 ALTER TABLE `room_item_cost` DISABLE KEYS */;
INSERT INTO `room_item_cost` VALUES (1,'2016-09-30 01:05:17','2016-10-03 10:19:59','BROOM',50,500,1),(2,'2016-09-30 01:05:17','2016-10-06 17:35:19','CURTAIN',50,250,0),(3,'2016-09-30 01:05:17','2016-10-04 12:07:26','DUSTBIN',50,3000,1),(4,'2016-09-30 01:05:17','2016-10-04 12:13:09','MATRES',50,10000,1),(5,'2016-09-30 01:05:17','2016-10-03 00:43:19','TABLE',50,3000,0),(6,'2016-09-30 01:05:17','2016-10-03 00:43:27','CHAIR',50,3000,0);
/*!40000 ALTER TABLE `room_item_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_transfer`
--

DROP TABLE IF EXISTS `room_transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_transfer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `current_room` varchar(255) NOT NULL,
  `prefer` varchar(255) NOT NULL,
  `reason` text NOT NULL,
  `profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2cvry4bp6qosogjqqkyc0l5k4` (`profile_id`),
  CONSTRAINT `FK_2cvry4bp6qosogjqqkyc0l5k4` FOREIGN KEY (`profile_id`) REFERENCES `student_profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_transfer`
--

LOCK TABLES `room_transfer` WRITE;
/*!40000 ALTER TABLE `room_transfer` DISABLE KEYS */;
INSERT INTO `room_transfer` VALUES (1,'2016-10-07 23:08:34','2016-10-07 23:08:34','Z01-H01-B01-R001-BD2','NON residence','ssjdvndjkvndfjvnfkjvnd',2);
/*!40000 ALTER TABLE `room_transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `booking` bit(1) DEFAULT NULL,
  `off_session_booking_start_date` datetime DEFAULT NULL,
  `semester_end_date` datetime DEFAULT NULL,
  `semester_start_date` datetime DEFAULT NULL,
  `sem_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES (4,'2016-10-06 17:19:06','2016-10-06 17:31:02','','2016-10-26 00:00:00','2016-10-20 00:00:00','2016-08-14 00:00:00',0);
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_profile`
--

DROP TABLE IF EXISTS `student_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `course` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `faculty` varchar(255) NOT NULL,
  `gender` int(11) NOT NULL,
  `student_reg_no` varchar(255) NOT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6ijt5eprw9x4rhrk9glqy1bol` (`student_id`),
  CONSTRAINT `FK_6ijt5eprw9x4rhrk9glqy1bol` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_profile`
--

LOCK TABLES `student_profile` WRITE;
/*!40000 ALTER TABLE `student_profile` DISABLE KEYS */;
INSERT INTO `student_profile` VALUES (1,'2016-09-29 22:48:34','2016-09-29 22:48:34','Computer science','Computer science','Science',0,'S13/20037/10',2,''),(2,'2016-09-30 01:14:40','2016-10-07 00:39:11','Computer science','Computer science','Science',0,'S13/20038/10',3,'0711111111'),(3,'2016-10-07 01:56:52','2016-10-07 01:56:52','Computer science','Computer science','Scie',1,'S13/2058/15',4,'0733123113');
/*!40000 ALTER TABLE `student_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `login_id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i3xs7wmfu2i3jt079uuetycit` (`login_id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2016-09-29 01:02:19','2016-09-29 01:02:19','owladek@yahoo.com',NULL,'ochiwladek','ochiwladek','c060871fa8b5ef90ede04b286f4f3b3c93c14390cdac57ba9112a77124867b28a7741c87cc7d1774',NULL,'ADMIN','ACTIVE'),(2,'2016-09-29 01:42:13','2016-09-29 01:42:13','student@mail.com',NULL,'teststudent','Fred Omondi','b3005ea8666ace33eae122aad65fd0c75093efe65f86649886066438c0d39f76e7c1577c38907287',NULL,'STUDENT','ACTIVE'),(3,'2016-09-30 01:13:59','2016-09-30 01:13:59','mike@maill.com',NULL,'mike','Mike Tester','2110610ad91d41827df1311818fdc81e620cdcf5be3e2266846fbfab6826939f975e7c5b51666f88',NULL,'STUDENT','ACTIVE'),(4,'2016-10-07 01:05:36','2016-10-07 01:05:36','maina@mail.com',NULL,'maina','Jhon Maina','62b220264d1f8c187e6952b0fead5d35e23dd333386bdac394b3385c400773d472dc6aecc0c97e40',NULL,'STUDENT','ACTIVE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zone`
--

DROP TABLE IF EXISTS `zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zone`
--

LOCK TABLES `zone` WRITE;
/*!40000 ALTER TABLE `zone` DISABLE KEYS */;
INSERT INTO `zone` VALUES (1,'2016-09-29 01:06:59','2016-10-06 20:12:44','Z01','CBD'),(2,'2016-09-29 01:07:37','2016-09-29 01:07:37','Z02','Northern');
/*!40000 ALTER TABLE `zone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-07 23:16:42
