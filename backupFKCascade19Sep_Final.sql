-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: testonline
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `address_details`
--

DROP TABLE IF EXISTS `address_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address_details` (
  `address_id` int(11) NOT NULL,
  `city` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `c_time` datetime NOT NULL,
  `doorno` int(11) NOT NULL,
  `m_time` datetime NOT NULL,
  `pin_code` int(11) NOT NULL,
  `state` varchar(20) NOT NULL,
  `street_name` varchar(50) DEFAULT NULL,
  `user_id` varchar(6) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `address_details_fk0` (`user_id`),
  CONSTRAINT `address_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_details`
--

LOCK TABLES `address_details` WRITE;
/*!40000 ALTER TABLE `address_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `address_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `book_id` varchar(8) NOT NULL,
  `author` varchar(50) NOT NULL,
  `c_time` datetime NOT NULL,
  `edition_no` decimal(5,0) NOT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `m_time` datetime NOT NULL,
  `publishers` varchar(100) NOT NULL,
  `release_date` date NOT NULL,
  `libraryItems_item_id` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `books_fk01` (`libraryItems_item_id`),
  CONSTRAINT `books_fk01` FOREIGN KEY (`libraryItems_item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_format`
--

DROP TABLE IF EXISTS `item_format`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_format` (
  `item_id` varchar(8) NOT NULL,
  `available` varchar(150) NOT NULL,
  `item_type` varchar(6) NOT NULL,
  `libraryItems_item_id` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `item_format_fk01` (`libraryItems_item_id`),
  CONSTRAINT `item_format_fk01` FOREIGN KEY (`libraryItems_item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_format`
--

LOCK TABLES `item_format` WRITE;
/*!40000 ALTER TABLE `item_format` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_format` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library_items`
--

DROP TABLE IF EXISTS `library_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `library_items` (
  `item_id` varchar(8) NOT NULL,
  `c_time` datetime NOT NULL,
  `date_added` date NOT NULL,
  `description` varchar(200) NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `item_type` varchar(10) NOT NULL,
  `m_time` datetime NOT NULL,
  `price` int(11) NOT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_items`
--

LOCK TABLES `library_items` WRITE;
/*!40000 ALTER TABLE `library_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liked_list`
--

DROP TABLE IF EXISTS `liked_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liked_list` (
  `item_id` varchar(8) NOT NULL,
  `user_id` varchar(6) NOT NULL,
  `c_time` datetime NOT NULL,
  `like_status` int(11) NOT NULL,
  `m_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`,`user_id`),
  KEY `liked_list_fk1` (`user_id`),
  CONSTRAINT `liked_list_fk0` FOREIGN KEY (`item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE,
  CONSTRAINT `liked_list_fk1` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked_list`
--

LOCK TABLES `liked_list` WRITE;
/*!40000 ALTER TABLE `liked_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `liked_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_audit`
--

DROP TABLE IF EXISTS `login_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_audit` (
  `last_login_time` datetime NOT NULL,
  `user_id` varchar(6) NOT NULL,
  `login_status` char(1) NOT NULL,
  PRIMARY KEY (`last_login_time`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_audit`
--

LOCK TABLES `login_audit` WRITE;
/*!40000 ALTER TABLE `login_audit` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_details` (
  `user_id` varchar(6) NOT NULL,
  `c_time` datetime NOT NULL,
  `m_time` datetime NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `login_details_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_details`
--

DROP TABLE IF EXISTS `member_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_details` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_time` datetime NOT NULL,
  `end_date` date NOT NULL,
  `enrolled_date` date NOT NULL,
  `m_time` datetime NOT NULL,
  `renewal_date` date NOT NULL,
  `membership_type_id` int(11) NOT NULL,
  `user_id` varchar(6) NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `member_details_fk02` (`membership_type_id`),
  KEY `member_details_fk01` (`user_id`),
  CONSTRAINT `member_details_fk01` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `member_details_fk02` FOREIGN KEY (`membership_type_id`) REFERENCES `membership_details` (`member_type_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_details`
--

LOCK TABLES `member_details` WRITE;
/*!40000 ALTER TABLE `member_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership_details`
--

DROP TABLE IF EXISTS `membership_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membership_details` (
  `member_type_id` int(11) NOT NULL,
  `c_time` datetime NOT NULL,
  `max_price_limit` int(11) NOT NULL,
  `membership_cost` int(11) NOT NULL,
  `membership_type` varchar(6) NOT NULL,
  `m_time` datetime NOT NULL,
  `validity_days` int(11) NOT NULL,
  PRIMARY KEY (`member_type_id`),
  UNIQUE KEY `UK_hdo8x7jfost1la6e91pb87wwm` (`membership_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_details`
--

LOCK TABLES `membership_details` WRITE;
/*!40000 ALTER TABLE `membership_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movies` (
  `movie_id` varchar(8) NOT NULL,
  `casts` varchar(100) NOT NULL,
  `c_time` datetime NOT NULL,
  `directors` varchar(100) NOT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `m_time` datetime NOT NULL,
  `productions` varchar(100) NOT NULL,
  `release_date` date NOT NULL,
  `singers` varchar(100) NOT NULL,
  `writers` varchar(100) NOT NULL,
  `libraryItems_item_id` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `movies_fk01` (`libraryItems_item_id`),
  CONSTRAINT `movies_fk01` FOREIGN KEY (`libraryItems_item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music` (
  `music_id` varchar(8) NOT NULL,
  `c_time` datetime NOT NULL,
  `genre` varchar(20) DEFAULT NULL,
  `m_time` datetime NOT NULL,
  `productions` varchar(100) NOT NULL,
  `release_date` date NOT NULL,
  `singers` varchar(100) NOT NULL,
  `writers` varchar(100) NOT NULL,
  `libraryItems_item_id` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`music_id`),
  KEY `music_fk01` (`libraryItems_item_id`),
  CONSTRAINT `music_fk01` FOREIGN KEY (`libraryItems_item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating_table`
--

DROP TABLE IF EXISTS `rating_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating_table` (
  `item_id` varchar(8) NOT NULL,
  `user_id` varchar(6) NOT NULL,
  `c_time` datetime NOT NULL,
  `m_time` datetime NOT NULL,
  `rating` int(11) NOT NULL,
  `review` varchar(200) NOT NULL,
  PRIMARY KEY (`item_id`,`user_id`),
  KEY `rating_table_fk0` (`user_id`),
  CONSTRAINT `rating_table_fk0` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `rating_table_fk1` FOREIGN KEY (`item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating_table`
--

LOCK TABLES `rating_table` WRITE;
/*!40000 ALTER TABLE `rating_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscribed_list`
--

DROP TABLE IF EXISTS `subscribed_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscribed_list` (
  `item_id` varchar(8) NOT NULL,
  `member_id` int(11) NOT NULL,
  `c_time` datetime NOT NULL,
  `end_date` date NOT NULL,
  `m_time` datetime NOT NULL,
  `return_status` varchar(10) NOT NULL,
  `start_date` date NOT NULL,
  PRIMARY KEY (`item_id`,`member_id`),
  KEY `subscribed_list_fk01` (`member_id`),
  CONSTRAINT `subscribed_list_fk01` FOREIGN KEY (`member_id`) REFERENCES `member_details` (`member_id`) ON UPDATE CASCADE,
  CONSTRAINT `subscribed_list_fk02` FOREIGN KEY (`item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscribed_list`
--

LOCK TABLES `subscribed_list` WRITE;
/*!40000 ALTER TABLE `subscribed_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscribed_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `user_id` varchar(6) NOT NULL,
  `age` int(11) NOT NULL,
  `alternate_contact_no` int(11) DEFAULT NULL,
  `c_time` datetime NOT NULL,
  `email_id` varchar(35) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `languages` varchar(100) NOT NULL,
  `mobile_no` int(11) NOT NULL,
  `m_time` datetime NOT NULL,
  `preferred_notify` varchar(5) NOT NULL,
  `role` varchar(1) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_c1fsif9ivmojbwdugkighpr1b` (`email_id`),
  UNIQUE KEY `UK_fvckpq744rlwn6p8uelqd8ns` (`mobile_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish_list`
--

DROP TABLE IF EXISTS `wish_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wish_list` (
  `item_id` varchar(8) NOT NULL,
  `member_id` int(11) NOT NULL,
  `c_time` datetime NOT NULL,
  `m_time` datetime NOT NULL,
  `wish_status` int(11) NOT NULL,
  PRIMARY KEY (`item_id`,`member_id`),
  KEY `wish_list_fk01` (`member_id`),
  CONSTRAINT `wish_list_fk01` FOREIGN KEY (`member_id`) REFERENCES `member_details` (`member_id`) ON UPDATE CASCADE,
  CONSTRAINT `wish_list_fk02` FOREIGN KEY (`item_id`) REFERENCES `library_items` (`item_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish_list`
--

LOCK TABLES `wish_list` WRITE;
/*!40000 ALTER TABLE `wish_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `wish_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-19 22:58:32
