-- MySQL dump 10.13  Distrib 5.5.17, for linux2.6 (x86_64)
--
-- Host: localhost    Database: admin_devspace
-- ------------------------------------------------------


--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'admin','admin module'),(2,'rewards-reporting','This module is for reporting');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES
  (1,'ROLE_ADD_USER','Permission to add user'),
  (2,'ROLE_REMOVE_USER','Permission to remove user'),
  (3,'ROLE_SEARCH_USER','Permission to search users '),
  (4,'ROLE_MODIFY_USER','Permission to modify user'),
  (5,'ROLE_ADD_GROUP','Permission to add group'),
  (6,'ROLE_REMOVE_GROUP','Permission to remove group'),
  (7,'ROLE_SEARCH_GROUP','Permission to modify user'),
  (8,'ROLE_MODIFY_GROUP','Permission to modify group'),
  (9,'ROLE_DASHBOARD_VIEW','Permission view dashboard'),
  (10,'ROLE_TRAFFIC_VIEW_REPORT','Permission to view traffic report');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `module_permission`
--

DROP TABLE IF EXISTS `module_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module_permission` (

  `module_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
   PRIMARY KEY (`module_id`,`permission_id`),
   KEY `fk_module` (`module_id`),
   KEY `fk_permission` (`permission_id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administered_module`
--

LOCK TABLES `module_permission` WRITE;
/*!40000 ALTER TABLE `module_permission` DISABLE KEYS */;
INSERT INTO `module_permission` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(2,9),(2,10);
/*!40000 ALTER TABLE `module_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module_group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'SYSTEM_ADMIN','Allow meta-data configurations'),
  (2,'SYSTEM_MANAGER','View and Assign works'),
  (3,'SYSTEM_CUSTOMERCARE','View programs and subscribers');
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_permission`
--

DROP TABLE IF EXISTS `group_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_permission` (
  `group_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`permission_id`,`group_id`),
  KEY `fk_permission` (`permission_id`),
  KEY `fk_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_role`
--

LOCK TABLES `group_permission` WRITE;
/*!40000 ALTER TABLE `group_permission` DISABLE KEYS */;
INSERT INTO `group_permission` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(2,9),(2,10),
  (3,1),(3,3),(3,5),(3,7),(3,9),(3,10);
/*!40000 ALTER TABLE `group_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone_no` varchar(15) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
  (1,'sysadmin','098f6bcd4621d373cade4e832627b4f6','sysadmin','System','Admin','sysadmin@hsenidmobile.com','94777111111','Colombo'),
  (2,'custcare','098f6bcd4621d373cade4e832627b4f6','sysadmin','Customer','Care','custcare@hsenidmobile.com','94777111111','Colombo'),
  (3,'ccmanager','098f6bcd4621d373cade4e832627b4f6','sysadmin','CustomerCare','Manager','ccmanager@hsenidmobile.com','94777111111','Colombo');


/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `fk_user` (`user_id`),
  KEY `fk_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (1,1),(2,3),(3,2),(3,3);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;