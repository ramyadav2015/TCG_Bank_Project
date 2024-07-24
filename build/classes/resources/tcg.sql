-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: tcg_bank
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer_amount_data`
--

DROP TABLE IF EXISTS `customer_amount_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_amount_data` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `amount` float DEFAULT NULL,
  `trans_type` varchar(50) DEFAULT NULL,
  `remain_amount` float DEFAULT NULL,
  `amount_data_time` varchar(50) DEFAULT NULL,
  `account_no` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_amount_data`
--

LOCK TABLES `customer_amount_data` WRITE;
/*!40000 ALTER TABLE `customer_amount_data` DISABLE KEYS */;
INSERT INTO `customer_amount_data` VALUES (2,0,'account_open',0,'14-02-2024 15:37:42','TCG100002'),(3,0,'account_open',0,'14-02-2024 16:13:44','TCG100003'),(8,0,'account_open',0,'14-02-2024 16:46:26','TCG100004'),(9,0,'account_open',0,'14-02-2024 16:50:54','TCG100005'),(10,0,'account_open',0,'14-02-2024 17:01:44','TCG100006'),(22,5000,'Send to TCG100002',15400,'19-02-2024 16:45:44','TCG100001'),(27,0,'account_open',0,'20-02-2024 10:50:57','TCG100007'),(28,0,'account_open',0,'20-02-2024 11:00:02','TCG100007'),(29,0,'account_open',0,'20-02-2024 11:02:10','TCG100007'),(30,0,'account_open',0,'20-02-2024 11:04:59','TCG100007'),(31,0,'account_open',0,'20-02-2024 11:08:06','TCG100008'),(32,2000,'Self_Deposit',2000,'20-02-2024 11:09:43','TCG100008'),(33,5000,'Self_Deposit',7000,'20-02-2024 11:09:49','TCG100008'),(34,20000,'Self_Deposit',27000,'20-02-2024 11:10:20','TCG100008'),(35,20000,'Self_Deposit',47000,'20-02-2024 11:10:25','TCG100008'),(36,1000000,'Self_Deposit',1047000,'20-02-2024 11:10:31','TCG100008'),(37,50000,'Send By TCG100008',50000,'20-02-2024 11:11:22','TCG100007'),(38,50000,'Send to TCG100007',997000,'20-02-2024 11:11:22','TCG100008'),(39,1000,'Send By TCG100008',51000,'20-02-2024 11:11:55','TCG100007'),(40,1000,'Send to TCG100007',996000,'20-02-2024 11:11:55','TCG100008'),(41,5000,'Withdraw_Self',991000,'20-02-2024 11:12:01','TCG100008'),(42,5000,'Withdraw_Self',986000,'20-02-2024 11:12:04','TCG100008'),(43,5544,'Self_Deposit',991544,'20-02-2024 11:12:12','TCG100008'),(44,5000,'Self_Deposit',996544,'20-02-2024 17:47:05','TCG100008'),(45,10000,'Withdraw_Self',986544,'20-02-2024 17:47:12','TCG100008'),(46,2000,'Send By TCG100008',2000,'20-02-2024 17:47:25','TCG100002'),(47,2000,'Send to TCG100002',984544,'20-02-2024 17:47:25','TCG100008'),(48,100000,'Self_Deposit',151000,'22-02-2024 17:11:18','TCG100007'),(49,5000,'Self_Deposit',156000,'22-02-2024 17:11:29','TCG100007'),(50,5000,'Send By TCG100007',161000,'22-02-2024 17:11:51','TCG100007'),(51,5000,'Send to TCG100007',151000,'22-02-2024 17:11:51','TCG100007'),(52,5000,'Send By TCG100007',7000,'22-02-2024 17:12:37','TCG100002'),(53,5000,'Send to TCG100002',146000,'22-02-2024 17:12:37','TCG100007'),(54,200,'Self_Deposit',146200,'22-02-2024 17:13:24','TCG100007'),(55,2000,'Self_Deposit',148200,'23-02-2024 12:45:27','TCG100007'),(56,1000,'Self_Deposit',149200,'23-02-2024 12:45:50','TCG100007'),(57,22,'Send By TCG100007',7022,'25-02-2024 09:56:16','TCG100002'),(58,22,'Send to TCG100002',149178,'25-02-2024 09:56:16','TCG100007'),(59,1400,'Withdraw_Self',147778,'25-02-2024 09:56:26','TCG100007'),(60,0,'account_open',0,'25-02-2024 10:01:20','TCG100009'),(61,10000,'Send By TCG100009',17022,'25-02-2024 10:02:46','TCG100002'),(62,10000,'Send to TCG100002',-10000,'25-02-2024 10:02:46','TCG100009'),(63,22,'Withdraw_Self',-10022,'25-02-2024 10:02:57','TCG100009'),(64,100200,'Self_Deposit',90178,'25-02-2024 10:03:05','TCG100009'),(65,33,'Send By TCG100009',17055,'25-02-2024 10:03:22','TCG100002'),(66,33,'Send to TCG100002',90145,'25-02-2024 10:03:22','TCG100009'),(67,33333300000,'Withdraw_Self',-33333200000,'25-02-2024 10:03:26','TCG100009'),(68,5,'Send By TCG100007',5,'26-02-2024 11:49:43',''),(69,5,'Send to ',147773,'26-02-2024 11:49:43','TCG100007'),(70,2000,'Send By TCG100007',2000,'26-02-2024 12:05:53','TCG100005'),(71,2000,'Send to TCG100005',145773,'26-02-2024 12:05:53','TCG100007'),(72,5000,'Withdraw_Self',140773,'26-02-2024 12:06:02','TCG100007'),(73,52,'Self_Deposit',140825,'26-02-2024 12:06:13','TCG100007'),(74,12000,'Withdraw_Self',128825,'04-03-2024 16:57:05','TCG100007'),(75,10002,'Self_Deposit',138827,'04-03-2024 16:57:15','TCG100007'),(76,5000,'Send By TCG100007',5000,'04-03-2024 16:57:32','TCG100004'),(77,5000,'Send to TCG100004',133827,'04-03-2024 16:57:32','TCG100007'),(78,2000,'Withdraw_Self',131827,'04-03-2024 17:16:41','TCG100007'),(79,2000,'Self_Deposit',133827,'04-03-2024 17:16:46','TCG100007'),(80,2000,'Withdraw_Self',131827,'04-03-2024 17:17:20','TCG100007'),(81,5000,'Withdraw_Self',979544,'04-03-2024 17:21:43','TCG100008'),(82,200000,'Self_Deposit',1179540,'04-03-2024 17:39:12','TCG100008'),(83,5000,'Withdraw_Self',126827,'13-03-2024 15:07:31','TCG100007'),(84,5440,'Self_Deposit',132267,'13-03-2024 15:07:36','TCG100007'),(85,4545,'Send By TCG100007',6545,'13-03-2024 15:07:49','TCG100005'),(86,4545,'Send to TCG100005',127722,'13-03-2024 15:07:49','TCG100007'),(87,0,'account_open',0,'13-03-2024 16:50:52','TCG100010'),(88,0,'account_open',0,'13-03-2024 16:57:25','TCG100011'),(89,0,'account_open',0,'13-03-2024 16:59:52','TCG100012'),(90,0,'account_open',0,'13-03-2024 17:07:23','TCG100013'),(91,0,'account_open',0,'13-03-2024 17:14:36','TCG100014');
/*!40000 ALTER TABLE `customer_amount_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_data`
--

DROP TABLE IF EXISTS `customer_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_data` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `ft_name` varchar(255) DEFAULT NULL,
  `mo_nome` varchar(255) DEFAULT NULL,
  `user_add` varchar(255) DEFAULT NULL,
  `user_dob` date DEFAULT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_mail` varchar(255) DEFAULT NULL,
  `user_pan` varchar(255) DEFAULT NULL,
  `user_account` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `nominee` varchar(255) DEFAULT NULL,
  `user_aadhar` varchar(10) DEFAULT NULL,
  `account_no` varchar(100) DEFAULT NULL,
  `user_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_data`
--

LOCK TABLES `customer_data` WRITE;
/*!40000 ALTER TABLE `customer_data` DISABLE KEYS */;
INSERT INTO `customer_data` VALUES (2,'Meghraj','Yadav','Shayam Singh','Sonam','Alwar','1991-10-01','Alwar','3245345345','meghraj78@gmail.com','SJKR4987','Savings','Male','Rakesh','54687542','TCG100002',NULL),(3,'Mahi','Sharma','Ramesh Sharma','Swati Sharma','Jaipur','2000-01-01','Jaipur','9785246554','Mahi4857@gmail.com','SJKR4590','Savings','Female','Ramesh','5468754256','TCG100003',NULL),(8,'Mahima','Gurjar','Narayan','Rani','Sikar','2002-09-20','Sikar','9875486857','mahima50@gmail.com','SJKR4960','Current','Female','Narayan','14528745','TCG100004',NULL),(9,'Dhiraj','Yadav','Meghraj','Mahima','Jaipur','1990-10-01','jaipur','9785246554','Dhiraj50@gamil.com','sadfsdfsd','Current','Male','Rakesh','145287454','TCG100005',NULL),(10,'karmpal','kumar','Mahendra','Maya devi','Jhunjhunu','1991-05-01','Jhunjhunu','3245345356','karmpal4068@gmail.com','SJKR4960','Savings','Male','Mahendra','ewrsdf3243','TCG100006',NULL),(14,'Ramkishore','Yadav','Mohan ','Sonam','Jaipur','1994-09-25','Jaipur','9875486857','ramkishoreyadav1994@gmail.com','SJKR4960','Savings','Male','Sita','45787854','TCG100007','hero-img1.png'),(15,'Meghraj Shingh','Yadav','Mohan ','Mahima','Word no - 3 , Bhrod , Alwar','1990-06-12','Alwar','982734982347','meghrajyadav.yadav20@gmail.com','SJKR4960','Savings','Male','Ramesh','54687542','TCG100008',NULL),(16,'sanjay','samota','mohan singh','meena devi','reengus sikar','2024-02-28','rajasthan','8209566157','samota.sanjay@gmail.com','kutps7967f','Savings','Male','celina','75269234','TCG100009','bg-01.jpg'),(17,'Sanju','Swami','Ramdhan','Surekha','Jhotwara','1996-07-17','jaipur','9785246557','Sanju@gmail.com','SJKR4960','Savings','Male','Narayan','45787854','TCG100010',NULL),(18,'RahulShingh','Yadav','Shayam Singh','Rani','Sikar','1990-02-15','Sikar','9875486857','rahulsingh@gmail.com','SJKR4987','Current','Male','Rakesh','45787854','TCG100011',NULL),(19,'Dhirajraj','Sharma','Meghraj','Surekha','Jhotwara','2000-08-25','Jaipur','9827349823','raj@gmail.com','SJKR4987','Current','Male','Ramesh','45787858','TCG100012',NULL),(20,'Meghraj Shingh','sdfsd','Mohan ','asdfasd','sdafasd','2024-03-23','asdfsd','3245345356','sdafs75@gmail.com','SJKR4564','Savings','Male','asdfsadfsda','54687542','TCG100013',NULL),(21,'ramshinf','sdfs','sadfsd','sdfsd','asdfsad','2024-03-14','asdfsd','9785246554','ramsti@gmail.com','SJKR4960','Savings','Male','sadfsd','54687542','TCG100014',NULL);
/*!40000 ALTER TABLE `customer_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_login_data`
--

DROP TABLE IF EXISTS `customer_login_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_login_data` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `c_mail` varchar(255) DEFAULT NULL,
  `c_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_login_data`
--

LOCK TABLES `customer_login_data` WRITE;
/*!40000 ALTER TABLE `customer_login_data` DISABLE KEYS */;
INSERT INTO `customer_login_data` VALUES (2,'meghraj78@gmail.com','448758'),(3,'Mahi4857@gmail.com','890509'),(8,'mahima50@gmail.com','353116'),(9,'Dhiraj50@gamil.com','230951'),(10,'karmpal4068@gmail.com','897903'),(14,'ramkishoreyadav1994@gmail.com','454545'),(15,'meghrajyadav.yadav20@gmail.com','454545'),(16,'samota.sanjay@gmail.com','974404'),(17,'Sanju@gmail.com','388916'),(18,'rahulsingh@gmail.com','564044'),(19,'raj@gmail.com','947396'),(20,'sdafs75@gmail.com','865340'),(21,'ramsti@gmail.com','677475');
/*!40000 ALTER TABLE `customer_login_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-24 16:49:28
