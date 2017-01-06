CREATE DATABASE  IF NOT EXISTS `pizza_shefu` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pizza_shefu`;
-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: pizza_shefu
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Pizza','Pizza is a flatbread generally topped with tomato sauce and cheese and baked in an oven. It is commonly topped with a selection of meats, vegetables and condiments.','admin','Pizza.jpg','1','2016-12-01'),(2,'Beverage','Freshly cooked pasta with prawns, vegetables & sliced mushrooms baked with a white cream sauce and mozzarella cheese','Kalhara','Beverage.jpg','1','2016-12-06'),(3,'Topping','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown','Somaweera','Topping.jpg','0','2016-12-04'),(4,'Desserts','Freshly cooked pasta with prawns, vegetables & sliced mushrooms baked with a white cream sauce and mozzarella cheese','Samantha','Desserts.jpg','1','2016-11-22'),(5,'Salad','Freshly cooked pasta with prawns, vegetables & sliced mushrooms baked with a white cream sauce and mozzarella cheese','Lorence','Salad.jpg','1','2016-10-19'),(46,'IceCream','Ice Cream is of different flavours .','admin','IceCream.jpg','1','2016-11-14'),(48,'Pasta2','There are different types of Pasta2','admin','Pasta.jpg','1','2016-10-16');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category_type`
--

LOCK TABLES `category_type` WRITE;
/*!40000 ALTER TABLE `category_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contact_us`
--

LOCK TABLES `contact_us` WRITE;
/*!40000 ALTER TABLE `contact_us` DISABLE KEYS */;
INSERT INTO `contact_us` VALUES (17,'complaint','Mr.','value','value','0711234567','value','2016-12-22','11:15:10','to review');
/*!40000 ALTER TABLE `contact_us` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'HwCvWkArpe',5.25,'2016-09-13','used','0711234567'),(2,'7PkzURJXiv',7.50,'2016-11-19','used','0119123789'),(3,'eEHVov6RmV',8.25,'2016-01-19','expired','0710710710'),(4,'tiQU2CM2bX',17.25,'2016-11-19','used','0719789852'),(5,'jTfoL6BBX7',25.25,'2016-12-19','active','0852741258'),(6,'gXxc4kEcnSvm9EPms44D',10.00,'2017-01-20','active','0711111111'),(7,'3RmB6t8hJITgKZq5MJPm',10.00,'2017-01-21','active','0721234567'),(8,'7RqswLAQY3aM1hd3FpdL',10.00,'2017-01-21','used','0711234568'),(9,'ePIvYKx3vbw4vM94cmcc',10.00,'2017-01-21','active','0711234567'),(18,'6ncxws9erUJGK1QBOJBI',10.00,'2017-01-29','active','0785863300'),(19,'D5NaaB2rw6TQTJL278rW',10.00,'2017-01-29','active','0715642138'),(20,'qkfFxHjCFvj8NUShZ1mf',10.00,'2017-01-29','active','0715642138'),(21,'TbMS4Jma7dH5kcpRcDLc',10.00,'2017-01-29','used','0715642138'),(22,'81e7mpbl4Lr24IsM7FhE',10.00,'2017-01-29','used','0785863300'),(23,'lFoTJN61sZOalwgWCS4y',10.00,'2017-01-29','active','0715642138'),(24,'Ly4XbPLzUwEQCOnYY3Ms',10.00,'2017-01-29','used','0715642138'),(25,'4cGRUHv1WhiZJQbwgaIs',10.00,'2017-01-29','active','0785863300'),(26,'b69TBPQrbq9qdj4ewlJb',10.00,'2017-02-01','active','0785863300'),(27,'klGOTqp2mr7cBOdLEKmh',10.00,'2017-02-02','active','0859632586'),(28,'ZAEhptoycCCOn6BlORhg',10.00,'2017-02-02','active','0859632586'),(29,'MZtMvJq1tLMWBWKheOpF',10.00,'2017-02-02','active','0859632586'),(30,'NMdiWNOBY3RjffEN2Pub',10.00,'2017-02-02','active','0859632586'),(31,'ia1JYO2GMyr3DE3xuRLN',10.00,'2017-02-02','active','0859632586'),(32,'WDmm91lHzkX6ayxsPNit',10.00,'2017-02-02','active','0859632586'),(33,'IT89b8vU8nETAWFnL4Yw',10.00,'2017-02-03','active','0859632586');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Mr','Kasun','Kalhara','kkalla','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','mail@mail.com','Test lane','bug free','application','0715642138','2016-09-13','active'),(2,'Ms','Capricone','Silver','testre','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','hulkmail!@gmail.com','Creeps Lane','Town hall','Axa townx','0859632586','2016-09-13','active'),(3,'Mrs','Sunimali','Kawya','skroxsa','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','linker@tes.com','linken park','howler street','New York','0213694562','2015-12-13','active'),(4,'Mr','Rio','De Guera','braziliansta','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','brrocks@bro.com','St. Petersburge','Stakeout lane','Losta rico','0716548965','2011-02-23','inactive'),(5,'Mr','Unknown','User','anonymous','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','anonymous@anony.mou','Dark net','Deep web','Internet','0110110111','1000-04-01','active'),(6,'miss','Dineesha','Suraweera','dinu','5c776c4eeab77660eafeeba140473d50d4ad1586','mm@kli.lk','No. 35,','Nugegoda.','Colombo','+94721325325','2016-12-21','active'),(7,'mr','www','www','wwww','8089abe3df8d6a6a677ba5a8fd85cf1d2fa5d9fa','menukaishan5@gmail.com','Meth Niwasa','Gandara','Kandana','+94717388432','2016-12-21','inactive'),(8,'mr','bvbnvn','vbhn','customer3','79879c9c73c57b3361724d96707bc0ad685a8374','cgg@gsfgsgd.com','23','Gangoda','Colombo','+94778902334','2016-10-07','inactive'),(9,'mr','customer4','','customer4','e6900589a51e0413396503cbdf469233b547e46f','jdfdgf@gmail.com','23','Balangoda','Gampaha','+94778549474','2016-10-07','active'),(10,'mr','dhfh','','cusTest','f6ee6dd69ea22a0253e44e354c2c08fabf76d1c7','cgg@gsfgsgd.com','445/B','ghfh','Colombo','+94778549474','2016-10-07','inactive'),(11,'mr','customer','','cusTest1234','79879c9c73c57b3361724d96707bc0ad685a8374','difna4@gmail.com','445','udugampola','Colombo','+94778549474','2016-10-07','inactive'),(12,'mr','Manula','','cusTest2324','cusTest2324','manula344@gmail.com','445','ghfh','Colombo','+94778902334','2016-10-07','active'),(13,'mr','qqqqq','gfghfh','tharin','f6ee6dd69ea22a0253e44e354c2c08fabf76d1c7','deqfeifna344@gmail.com','445','Temple Road','Mathale','+94778549474','2016-10-07','active'),(14,'mr','www','','userLast123','06133c6802bb95963417c71ec5d68da01850482a','cgg@gsfgsgd.com','445/B','Gangoda','Gampaha','+94778549474','2016-10-11','active'),(15,'mr','Cus05','','userLast33','f6ee6dd69ea22a0253e44e354c2c08fabf76d1c7','difna4@gmail.com','445/B','udugampola','Gampaha','+94778902334','2016-10-11','active'),(16,'mr','sfs','','fhfh2324','c345a75185dd3135db57201381323e276db30d28','fhfhfh@ryrty.fk','sfd','sfdf','sfsf','+94556776733','2016-10-12','active'),(17,'mr','fghgt','fyty','kkalla123','86ca1a149c539cc8dde50dcc88e1821e9442e59d','gfgds@gamil.com','yty','fhf','Ja-Ela','+94689877543','2016-10-13','active'),(18,'mr','check','dgd','qqqqq111','19907e7aca85de47a97a2e7d28f944e3f2379037','qqq@qqq.com','qqqq','qqqq','Gampaha','+94880098765','2016-10-13','active'),(19,'mr','cus1','cus12','cus1','5da0c3f33ac08837440d18da49ff6897c7b921f6','nnn@gmail.com','qqq','qqqq','Ja-Ela','+94778909675','2016-10-20','active'),(20,'mr','gg','detet','qqqqq','7c566b05cfe6eb0070f92dc5368d2f19fe86a844','dsf@gmail.com','dgdg','dgsdg','Colombo','+94778900567','2016-10-26','active'),(21,'mr','user1','user12','user123','95c946bf622ef93b0a211cd0fd028dfdfcf7e39e','sss@jhjk.com','qqq','Paliyagoda','Colombo','+94889957648','2016-10-26','active'),(22,'mr','test1','test12','testre1','7288edd0fc3ffcbe93a0cf06e3568e28521687bc','qq@gmail.com','test1','Kiribathgoda','Gampaha','+94776678900','2016-10-26','active'),(23,'mr','demo1','demo11','demo123','cbdbe4936ce8be63184d9f2e13fc249234371b9a','demo@gmail.com','No:1','Kirillawala','Ja-Ela','+94778900987','2016-11-01','active'),(24,'mr','Thanuji','cus11','cus12','d99f5ecc9fe7d3fd6d45b259c54edecbe4b0bf94','difna@gmail.com','cus11','cus11','Kandana','+94778909887','2016-12-21','active'),(25,'mr','Menuka','Ishan','qqqq123','7c566b05cfe6eb0070f92dc5368d2f19fe86a844','menukaishan5@gmail.com','Meth Niwasa','Gandara','Gampaha','+94717388432','2016-12-22','active'),(26,'mr','Menuka123','Ishan','mnb123','1502564725b5998a894fc07f893b4eb56ae94733','menukaishan5@gmail.com','Meth Niwasa','Gandara','Matara','+94717388432','2016-12-22','active'),(28,'Dr','Menuka','Ishan','demo','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','menukaishan5@gmail.com','Meth Niwasa','Gandara',NULL,'717388432','2016-12-22','active'),(29,'Dr','Jack','Sparrow','Jack007','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','pizzaemail@mail.com','Reid Avenue ','Colombo 7','','0711234569','2016-12-22','active'),(43,'Mr','frisby','test','frisby123','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','kasunutube@ymail.com','123','abc',NULL,'123456789','2016-12-29','active'),(45,'Mr','frisby','test','frisby1234','9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684','kasunutube@ymail.com','123','abc',NULL,'123456789','2016-12-29','active');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (21,NULL,'','','2016-12-17','11:45:00.000000','pending','',NULL,1,72),(22,NULL,'Kasun Kalhara','Test lane,bug free,application','2016-12-30','11:03:00.000000','pending','',NULL,2,73),(23,NULL,'Kasun Kalhara','Test lane,bug free,application','2016-12-30','11:16:00.000000','pending','',NULL,2,74),(24,NULL,'','','2016-12-30','11:16:00.000000','pending','',NULL,1,75),(25,NULL,'','','2016-12-17','11:45:00.000000','pending','',NULL,1,76),(26,NULL,'','','2016-12-30','11:21:00.000000','pending','',NULL,1,77),(27,NULL,'Kasun Kalhara','Test lane,bug free,application','2016-12-30','11:23:00.000000','pending','',NULL,2,78),(28,NULL,'','','2016-12-17','11:45:00.000000','pending','',NULL,1,79),(29,NULL,'','','2017-01-02','16:26:00.000000','pending','',NULL,1,80),(30,NULL,'','','2017-01-03','08:44:00.000000','pending','',NULL,1,81),(31,NULL,'','','2017-01-03','12:13:00.000000','pending','',NULL,1,82),(32,NULL,'Capricone Silver','Creeps Lane,Town hall,Axa townx','2017-01-03','12:14:00.000000','pending','',NULL,2,83),(33,NULL,'','','2017-01-03','12:13:00.000000','pending','',NULL,1,84),(34,NULL,'Capricone Silver','Creeps Lane,Town hall,Axa townx','2017-01-03','12:17:00.000000','pending','p[]',NULL,2,85),(35,NULL,'','','2017-01-03','14:01:00.000000','pending','',NULL,1,86),(36,NULL,'','','2017-01-04','13:55:00.000000','pending','',NULL,1,87);
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery_method`
--

LOCK TABLES `delivery_method` WRITE;
/*!40000 ALTER TABLE `delivery_method` DISABLE KEYS */;
INSERT INTO `delivery_method` VALUES (1,'pickup'),(2,'Door Step');
/*!40000 ALTER TABLE `delivery_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,'seasonal',1),(2,'coupen',1),(3,'seasonal',2),(4,'coupen',2),(5,'seasonal',3);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'2016-08-22','08:26:25.000000','when an unknown printer took a galley of type and scrambled it to make a type specimen book.',1,2,1),(15,'2015-12-20','09:23:26.000000','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text',3,1,5),(16,'2016-08-15','12:33:32.350342','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text',5,2,4),(17,'2016-04-05','15:12:00.000000','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text',2,3,3),(18,'2016-08-08','12:34:00.000000','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text',4,5,2),(19,'2016-05-08','03:45:00.000000','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text',3,4,1);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'System Admin','GROUP_SYS_ADMIN',1),(2,'System Manager','GROUP_ADM_MANAGER',1),(3,'Customer Manager','GROUP_CC_MANAGER',1),(4,'Main Chef','GROUP_CUSTOMERCARE',1),(5,'Reporting Manager','GROUP_RPT_MANAGER',2),(6,'Cashier','GROUP_CUSTOMERCARE',1);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `group_permission`
--

LOCK TABLES `group_permission` WRITE;
/*!40000 ALTER TABLE `group_permission` DISABLE KEYS */;
INSERT INTO `group_permission` VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(3,2),(1,3),(2,3),(3,3),(1,4),(2,4),(3,4),(1,5),(2,5),(3,5),(1,6),(3,6),(1,7),(3,7),(1,8),(3,8),(1,9),(3,9),(1,10),(3,10),(1,11),(3,11),(1,12),(3,12),(1,13),(3,13),(1,14),(3,14),(1,15),(3,15),(1,16),(3,16),(1,17),(3,17),(1,18),(3,18),(1,19),(3,19),(1,20),(2,20),(1,21),(2,21),(1,22),(2,22),(1,23),(2,23),(1,24),(2,24),(1,25),(2,25),(1,26),(2,26),(1,27),(2,27),(1,28),(2,28),(1,29),(3,29),(4,29),(5,29),(1,30),(5,30),(1,31),(5,31),(1,32),(2,32),(3,32),(4,32),(5,32),(1,33),(3,33),(4,33),(1,34),(2,34),(3,34),(4,34),(5,34);
/*!40000 ALTER TABLE `group_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `group_staff`
--

LOCK TABLES `group_staff` WRITE;
/*!40000 ALTER TABLE `group_staff` DISABLE KEYS */;
INSERT INTO `group_staff` VALUES (1,1),(2,2),(3,3),(4,4),(4,5),(5,6),(1,90);
/*!40000 ALTER TABLE `group_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,'0715646542'),(2,'0756465649'),(3,'0771123456'),(4,'0112345654'),(12,'0721212321'),(18,'0721234567'),(19,'0785863300');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Thanduri Pizza','Lorem Ipsum is simply dummy text of the printing...',2,'iimh.png',2,NULL,NULL),(2,'Cheese Lover','Lorem Ipsum is simply dummy text of the printing ..',3,'iimh.png',1,NULL,NULL),(3,'Chicken Lasanga','standard dummy text',2,'iimh.png',2,NULL,NULL),(4,'Garlic Bread','Lorem Ipsum is simply dummy text of the printing...',1,'iimh.png',15,NULL,NULL),(37,'cake1','',4,'iimh.png',7,NULL,NULL),(65,'vanila','changed',3,'iimh.png',6,NULL,NULL),(67,'Cappuccino','Italian cappuccino',4,'iimh.png',12,NULL,NULL),(68,'italian crush','editted text',2,'iimh.png',6,NULL,NULL),(69,'Dried chillies','tested',1,'Dried chillies.jpg',6,NULL,NULL),(109,'pudding3','edited desc',4,'iimh.png',11,NULL,NULL),(118,'sad','edt',1,'iimh.png',8,NULL,NULL),(126,'test','editted',4,'iimh.png',4,NULL,NULL),(127,'pork','pork potions for toppings editted',2,'iimh.png',15,NULL,NULL),(128,'cucumber salad','salad of cucumber pieces seasoned with pepper salt',1,'iimh.png',16,NULL,NULL),(130,'Gourmet pizza new','new arrival',2,'iimh.png',1,NULL,NULL),(131,'Lime','added for editing',4,'iimh.png',14,NULL,NULL),(132,'rgeg','rege',1,'reg',3,NULL,NULL),(133,'brownie1','added --now editted',4,'iimh.png',4,NULL,NULL),(137,'ice tea','dd',4,'iimh.png',11,NULL,NULL),(140,'cccccc','vvvv',4,'iimh.png',1,NULL,NULL),(145,'green tea','erfwer',4,'iimh.png',11,NULL,NULL),(150,'crushed','',3,'iimh.png',13,NULL,NULL),(151,'cheda','',1,'add_item.png',19,NULL,NULL),(152,'chillie sauce','rfrfq',1,NULL,17,NULL,NULL),(154,'ch','Chocolate pudding',4,'iimh.png',6,NULL,NULL),(185,'green tea1','',4,'iimh.png',11,NULL,NULL),(186,'Orange','',4,'iimh.png',13,NULL,NULL),(190,'ewFWERF','',3,'iimh.png',11,NULL,NULL),(191,'R4A3R','',2,'iimh.png',4,NULL,NULL),(192,'htds ','hjhyj',2,'images.png',20,NULL,NULL),(193,'kkkkkkk','jj',3,'restaurant.jpg',6,NULL,NULL),(194,'img','33',3,'exampleFileUpload.jpg',20,NULL,NULL),(195,'nhfe','rr',4,'images.png',12,NULL,NULL),(196,'ss','',3,'images.png',6,NULL,NULL),(197,'n','',4,'n.jpg',11,NULL,NULL),(200,'klhnlknh','',3,'images.png',19,NULL,NULL),(201,'vfer','heherh',3,'parma-pizza-chef.png',19,NULL,NULL),(204,'ttt','',3,'parma-pizza-chef.png',6,NULL,NULL),(205,'gvh','',3,'gvh.jpg',11,NULL,NULL),(206,'hf','',2,'images.png',14,NULL,NULL),(208,'vgfx','',3,'vgfx.jpg',11,NULL,NULL),(209,'Stawberry-nuts','',4,'images.png',6,NULL,NULL),(210,'ertyuiop','',2,'parma-pizza-chef.png',18,NULL,NULL),(211,'bgr','',2,'exampleFileUpload.jpg',4,NULL,NULL),(212,'rrtt','',2,'parma-pizza-chef.png',17,NULL,NULL),(214,'ryhe','',3,'images.png',6,NULL,NULL),(216,'tt','',3,'tt.jpg',11,NULL,NULL),(217,'Lime crush','',3,'private-dining.jpg',13,NULL,NULL),(218,'gggfe','',3,'images.png',15,NULL,NULL),(219,'yhyt','',2,'restaurant.jpg',7,NULL,NULL),(221,'gnhv n','',1,'delete_package.png',11,NULL,NULL),(222,'ggrew','',4,'delete_package.png',20,NULL,NULL),(223,'rg','',2,'add_new_package.png',1,NULL,NULL),(225,'wqad','',3,'success_add_package.png',9,NULL,NULL),(226,'Apple juice1','',4,'images.png',14,NULL,NULL),(227,'fresh Salad 3','',4,'jjj.png',6,NULL,NULL),(228,'Bbq Pizza','',2,'view_package.png',2,NULL,NULL),(229,'fdaf','',3,'parma-pizza-chef.png',8,NULL,NULL),(230,'pisggggg','',3,'images.png',7,NULL,NULL),(231,'hy','',3,'parma-pizza-chef.png',1,NULL,NULL),(232,'hkhg','',3,'delete_package.png',13,NULL,NULL),(233,'fof','',2,'add_new_package.png',2,NULL,NULL),(235,'wsdsw','',1,'images.png',6,NULL,NULL),(236,'Orange crush','',3,'images.png',13,NULL,NULL),(239,'ef','',2,'images.png',17,NULL,NULL),(241,'bfgs','',3,'images.png',11,NULL,NULL),(243,'tehesdh','',2,'parma-pizza-chef.png',15,NULL,NULL),(244,'rta','',3,'images.png',12,NULL,NULL),(246,'eeeee','',4,'parma-pizza-chef.png',11,NULL,NULL),(248,'dddfrtt4r','',2,'images.png',18,NULL,NULL),(254,'eee2see','',2,'add_item.png',2,NULL,NULL),(257,'dhdhd','',3,'chrt.jpg',11,NULL,NULL),(259,'ytnhhht','',3,'chrt.jpg',2,NULL,NULL),(261,'rfgrfrr','',2,'chrt.jpg',2,NULL,NULL),(262,'new_Arrival','',3,NULL,7,NULL,NULL),(263,'Black-tea','',3,'Black-tea.jpg',13,NULL,NULL),(264,'Custard','',4,'Custard.jpg',7,NULL,NULL),(265,'Fruit salad','',1,'Fruit salad.jpg',8,NULL,NULL),(266,'nbgg','',3,'nbgg.jpg',11,NULL,NULL),(267,'jjhuhhuuooggg','test item',3,'jjhuhhuuooggg.jpg',6,NULL,NULL),(268,'bawt','',4,'bawt.jpg',13,NULL,NULL),(269,'gbugb','',2,'gbugb.jpg',1,NULL,NULL),(270,'hhh','',3,'hhh.jpg',15,NULL,NULL),(271,'NY thincrust','',2,'NY thincrust.jpg',122,NULL,NULL),(272,'Gourmet12','hblhfhfbghjv gbpihrgbxsi',2,'Gourmet12.jpg',114,NULL,NULL),(273,'New italian1','',2,'New italian1.jpg',122,NULL,NULL),(274,'New arriaval big','',2,NULL,122,NULL,NULL),(275,'New crust 1','',1,'New crust 1.jpg',122,NULL,NULL),(276,'Italian sweet2','',2,'Italian sweet2.jpg',122,NULL,NULL),(277,'Brown beef','',2,'Brown beef.jpg',18,NULL,NULL),(278,'creme cheese','',1,'creme cheese.jpg',19,NULL,NULL),(279,'ddfed','',2,'ddfed.jpg',17,NULL,NULL),(280,'greezy','',4,'greezy.jpg',19,NULL,NULL),(281,'New gourmet big','',2,'New gourmet big.jpg',123,NULL,NULL),(282,'New dark coffee','',4,'New dark coffee.jpg',12,NULL,NULL),(283,'Italian spicy big','',2,'Italian spicy big.jpg',122,NULL,NULL),(284,'choclt 1','',3,'choclt 1.jpg',125,NULL,NULL),(285,'Mixed pasta single 1','New arrival for Christmas season a plate full of sty pasta. ',2,'Mixed pasta single 1.jpg',131,NULL,NULL),(286,'Chilly pasta','This is a new plate for spicy lovers. Enjoy Chilly Pasta today',1,'Chilly pasta.jpg',132,NULL,NULL);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'admin','admin module'),(2,'rewards-reporting','This module is for reporting');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (2,'New Year',1700.00,'New Year.jpg','2016-12-01',''),(5,'Feasty',256.25,'/a/dummy/image/path/','2016-12-01',''),(6,'Test-package',2460.00,'dummy.png','2016-12-01',''),(19,'Testing_pkg',1400.00,'Testing_pkg.jpg','2016-12-01',''),(20,'TESRT!',4450.00,'chrt.jpg','2016-12-01',''),(21,'Family Pack2',2890.00,'Family Pack2.jpg','2016-12-01',''),(22,'Budget Pack',3540.00,'Budget Pack.jpg','2016-12-01',''),(24,'nmki',2500.00,'nmki.jpg','2016-12-01',''),(25,'bgth',1000.00,'salads 01.jpg','2016-12-01',''),(27,'htry',2220.00,'salads 01.jpg','2016-12-01',''),(28,'nn',1200.00,'imageTest.png','2016-12-01',''),(29,'feee',2010.02,'imageTest.png','2016-12-01',''),(30,'jjjjjj',770.00,'imageTest.png','2016-12-01',''),(31,'feasty2',3210.00,'imageTest.png','2016-12-01',''),(32,'frtt',2220.00,'imageTest.png','2016-12-01',''),(33,'uuuu',1230.00,'imageTest.png','2016-12-01',''),(34,'hhllr',2120.00,'imageTest.png','2016-12-01',''),(35,'mnbv',1110.00,'imageTest.png','2016-12-01',''),(36,'christmas1',3330.00,'imageTest.png','2016-12-01',''),(37,'bgtre',3450.00,'imageTest.png','2016-12-01',''),(38,'aavn',1230.00,'imageTest.png','2016-12-01',''),(39,'awed',3330.00,'imageTest.png','2016-12-01',''),(40,'cdsa',1230.00,'imageTest.png','2016-12-01',''),(41,'ddd',330.00,'imageTest.png','2016-12-01',''),(42,'dddds',3330.00,'imageTest.png','2016-12-01',''),(43,'ggggere',1230.00,'imageTest.png','2016-12-01',''),(44,'yyhyy',1140.00,'imageTest.png','2016-12-01',''),(45,'mnbvvd',1230.00,'imageTest.png','2016-12-01',''),(46,'uuu',110.00,'imageTest.png','2016-12-01',''),(47,'hjh',100.00,'imageTest.png','2016-12-01',''),(48,'jj',100.00,'imageTest.png','2016-12-01',''),(49,'h',100.00,'imageTest.png','2016-12-01',''),(50,'ghg',100.00,'imageTest.png','2016-12-01',''),(51,'kol12',110.00,'imageTest.png','2016-12-01',''),(52,'nnn6',100.00,'imageTest.png','2016-12-01',''),(53,'jk',110.00,'imageTest.png','2016-12-01',''),(54,'zdd',1230.00,'imageTest.png','2016-12-01',''),(55,'ddddd',120.00,'imageTest.png','2016-12-01',''),(56,'wede',110.00,'imageTest.png','2016-12-01',''),(57,'bgthe',550.00,'imageTest.png','2016-12-01',''),(58,'jyyy',5550.00,'imageTest.png','2016-12-01',''),(59,'dfggg',440.00,'imageTest.png','2016-12-01',''),(60,'frgr',4440.00,'imageTest.png','2016-12-01',''),(61,'kuyd',6660.00,'imageTest.png','2016-12-01',''),(62,'ghhhhh',5550.00,'imageTest.png','2016-12-01',''),(63,'rtrtrtr',4440.00,'imageTest.png','2016-12-01',''),(64,'edde',8880.00,'imageTest.png','2016-12-01',''),(65,'dfe',4440.00,'imageTest.png','2016-12-01',''),(66,'dddd',3330.00,'imageTest.png','2016-12-01',''),(67,'tyyy',4630.00,'imageTest.png','2016-12-01',''),(68,'yyyyy6',550.00,'imageTest.png','2016-12-01',''),(69,'dddddd',230.00,'imageTest.png','2016-12-01',''),(70,'vfghhh',5550.00,'imageTest.png','2016-12-01',''),(71,'tdhh',440.00,'imageTest.png','2016-12-01',''),(72,'wdff',440.00,'imageTest.png','2016-12-01',''),(73,'wdddew',3330.00,'imageTest.png','2016-12-01',''),(74,'fffww',330.00,'imageTest.png','2016-12-01',''),(75,'ddgggg',40.00,'imageTest.png','2016-12-01',''),(76,'ccs',40.00,'imageTest.png','2016-12-01',''),(77,'mnbv66',660.00,'imageTest.png','2016-12-01',''),(78,'gfrr',4470.00,'imageTest.png','2016-12-01',''),(79,'fgfgvf',4440.00,'imageTest.png','2016-12-01',''),(80,'dfer',4440.00,'imageTest.png','2016-12-01',''),(81,'fbff',220.00,'imageTest.png','2016-12-01',''),(82,'rrr',330.00,'imageTest.png','2016-12-01',''),(83,'rrgg',43430.00,'imageTest.png','2016-12-01',''),(84,'ffrre',3330.00,'imageTest.png','2016-12-01',''),(85,'ghtg',5550.00,'imageTest.png','2016-12-01',''),(86,'thth',5550.00,'imageTest.png','2016-12-01',''),(87,'ggfffr',5550.00,'imageTest.png','2016-12-01',''),(88,'gtfht',440.00,'imageTest.png','2016-12-01',''),(89,'gthtt',880.00,'imageTest.png','2016-12-01',''),(90,'ghtt',980.00,'imageTest.png','2016-12-01',''),(91,'ghhg',450.00,'imageTest.png','2016-12-01',''),(92,'ghgh',440.00,'imageTest.png','2016-12-01',''),(93,'fgdgr',2440.00,'fgdgr.jpg','2016-12-01',''),(94,'ggd',440.00,'imageTest.png','2016-12-01',''),(95,'gffgg',3330.00,'images.png','2016-12-01',''),(96,'Package New1',2340.00,'parma-pizza-chef.png','2016-12-01',''),(97,'New Pack 2',1230.00,'','2016-12-01',''),(98,'mnbgt',765.00,'','2016-12-01',''),(99,'Charistmas-11',1500.00,'private-dining.jpg','2016-12-01',''),(100,'gggddd',3330.00,'chrt.jpg','2016-12-01',''),(101,'New Deal 1',2440.00,'New Deal 1.jpg','2016-12-01',''),(102,'delight pack1',3410.00,'delight pack1.jpg','2016-12-01',''),(103,'budget deal',1790.00,'budget deal.jpg','2016-12-01',''),(104,'mnAt',1230.00,'mnAt.jpg','2016-12-01',''),(105,'asasas',444.00,'asasas.jpg','2016-12-01',''),(106,'packNEw',1020.00,'packNEw.jpg','2016-12-01',''),(107,'nnnn',5550.08,'nnnn.jpg','2016-12-01',''),(108,'kjjhg',4470.00,'kjjhg.jpg','2016-12-01',''),(109,'vg vvv',550.00,'vg vvv.jpg','2016-12-01',''),(110,'mjy',1450.00,'mjy.jpg','2016-12-01',''),(111,'ddf',3330.00,'ddf.jpg','2016-12-01',''),(112,'new Kids2',2340.00,'new Kids2.jpg','2016-12-01',''),(113,'hbh',440.00,'hbh.jpg','2016-12-01',''),(114,'wdd',330.00,'wdd.jpg','2016-12-01',''),(115,'mki',3330.00,'mki.jpg','2016-12-01',''),(116,'hgfttt',4440.00,'hgfttt.jpg','2016-12-01',''),(117,'ngbcc',440.04,'ngbcc.jpg','2016-12-01',''),(118,'hhhhhgg',4440.00,'hhhhhgg.jpg','2016-12-01',''),(119,'htdd',440.00,'htdd.jpg','2016-12-01',''),(120,'bgdd',330.00,'bgdd.jpg','2016-12-01',''),(121,'mnbvvx',220.00,'mnbvvx.jpg','2016-12-01',''),(122,'jgdr',4460.00,'jgdr.jpg','2016-12-01',''),(123,'bgtsss',220.00,'bgtsss.jpg','2016-12-01',''),(124,'kkkkl',1470.00,'kkkkl.jpg','2016-12-01',''),(125,'bjjjjj',6540.00,'bjjjjj.jpg','2016-12-01',''),(126,'mkm',660.00,'mkm.jpg','2016-12-01',''),(127,'jjjj',770.00,'jjjj.jpg','2016-12-01',''),(128,'tggtthh',140.00,'tggtthh.jpg','2016-12-01',''),(129,'klll',650.00,'klll.jpg','2016-12-01',''),(130,'hhh',550.00,'hhh.jpg','2016-12-01',''),(131,'qqqqq',3330.00,'qqqqq.jpg','2016-12-01',''),(132,'mmm',440.00,'mmm.jpg','2016-12-01',''),(133,'mncx',1230.00,'mncx.jpg','2016-12-01',''),(134,'kmu',440.00,'kmu.jpg','2016-12-01',''),(135,'bcxe',440.00,'bcxe.jpg','2016-12-01',''),(136,'hfg',550.00,'hfg.jpg','2016-12-01',''),(137,'effe',220.00,'effe.jpg','2016-12-01',''),(138,'efe',330.00,'efe.jpg','2016-12-01',''),(139,'mnbg',320.00,'mnbg.jpg','2016-12-01',''),(140,'ffff',3210.00,'ffff.jpg','2016-12-01',''),(141,'grggrg',440.00,'grggrg.jpg','2016-12-01',''),(142,'gghh',330.00,'gghh.jpg','2016-12-01',''),(143,'nhnnhhh',550.05,'nhnnhhh.jpg','2016-12-01',''),(144,'eeeeewssss',220.00,'eeeeewssss.jpg','2016-12-01',''),(145,'gdww',220.00,'gdww.jpg','2016-12-01',''),(146,'feeeqqq',110.01,'feeeqqq.jpg','2016-12-01',''),(147,'jbhjf',140.00,'jbhjf.jpg','2016-12-01',''),(148,'mnbvcx',220.00,'mnbvcx.jpg','2016-12-01',''),(149,'efbaa',230.00,'efbaa.jpg','2016-12-01',''),(151,'erre',30.00,'erre.jpg','2016-12-01',''),(152,'muurg',330.00,'muurg.jpg','2016-12-01',''),(153,'fggrer',440.00,'fggrer.jpg','2016-12-01',''),(154,'fddzz',2220.00,'fddzz.jpg','2016-12-01',''),(155,'ggggbbb',4440.00,'ggggbbb.jpg','2016-12-01',''),(156,'gfeggj',330.00,'gfeggj.jpg','2016-12-01',''),(157,'fvkmo',2140.00,'fvkmo.jpg','2016-12-01',''),(158,'bgsa',330.00,'bgsa.jpg','2016-12-01',''),(159,'ujuj',660.00,'ujuj.jpg','2016-12-01',''),(160,'sfeee',3440.00,'sfeee.jpg','2016-12-01',''),(161,'ddad',2220.00,'ddad.jpg','2016-12-01',''),(162,'ggghd',4440.00,'ggghd.jpg','2016-12-01',''),(163,'hjkhj',660.00,'hjkhj.jpg','2016-12-01',''),(172,'ghrfttr',3440.00,'ghrfttr.jpg','2016-12-01',''),(174,'rffffdd',330.00,'rffffdd.jpg','2016-12-01',''),(177,'hjhjh',550.00,'hjhjh.jpg','2016-12-01',''),(178,'newHlk',4330.00,'newHlk.jpg','2016-12-01',''),(179,'uju',3450.00,'uju.jpg','2016-12-01',''),(180,'Christmas budget 1',3000.00,'Christmas budget 1.jpg','2016-12-01',''),(181,'hjhjgg',1450.00,'hjhjgg.jpg','2016-12-01',''),(183,'New Christamas budget pack',2750.00,'New Christamas budget pack.jpg','2016-12-01','');
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `package_item`
--

LOCK TABLES `package_item` WRITE;
/*!40000 ALTER TABLE `package_item` DISABLE KEYS */;
INSERT INTO `package_item` VALUES (19,233,4,'Large'),(20,231,2,'medium'),(20,109,3,'medium'),(21,228,2,'large'),(21,137,2,'regular'),(21,65,2,'other'),(22,3,2,'large'),(22,186,2,'regular'),(22,151,3,'medium'),(23,3,2,'large'),(23,186,2,'regular'),(23,151,3,'medium'),(24,2,2,'large'),(24,212,2,'regular'),(24,65,2,'other'),(25,130,3,'medium'),(25,67,3,'medium'),(25,4,1,'medium'),(26,2,2,'large'),(26,217,2,'regular'),(27,2,1,'large'),(27,109,1,'medium'),(28,1,1,'large'),(28,217,1,'regular'),(29,2,1,'large'),(29,109,2,'medium'),(29,212,1,'regular'),(30,109,1,'medium'),(30,65,2,'other'),(30,4,1,'medium'),(31,2,2,'large'),(31,152,2,'medium'),(31,65,1,'other'),(32,1,2,'large'),(33,131,1,'regular'),(33,209,2,'regular'),(33,4,1,'medium'),(34,2,1,'large'),(34,109,1,'medium'),(34,212,1,'regular'),(35,228,1,'large'),(35,109,1,'medium'),(36,211,3,'large'),(36,226,3,'regular'),(37,2,1,'large'),(37,109,2,'medium'),(37,212,2,'regular'),(38,3,1,'large'),(38,109,2,'medium'),(39,2,2,'large'),(39,109,2,'medium'),(40,109,1,'medium'),(40,212,1,'regular'),(40,65,1,'other'),(41,2,1,'large'),(41,109,2,'medium'),(42,2,1,'large'),(42,109,1,'medium'),(42,212,1,'regular'),(43,2,1,'large'),(43,109,1,'medium'),(43,65,1,'other'),(44,2,1,'large'),(44,109,1,'medium'),(45,2,2,'large'),(45,212,1,'regular'),(46,2,1,'large'),(46,109,1,'medium'),(47,2,1,'large'),(47,109,1,'medium'),(48,2,1,'large'),(49,2,1,'large'),(49,109,1,'medium'),(50,2,1,'large'),(50,109,1,'medium'),(51,2,1,'large'),(51,109,1,'medium'),(52,2,1,'large'),(53,2,1,'large'),(54,2,3,'large'),(55,2,1,'large'),(56,109,1,'medium'),(57,65,1,'other'),(58,212,2,'regular'),(59,109,2,'medium'),(60,2,3,'large'),(60,109,5,'medium'),(61,2,1,'large'),(62,2,1,'large'),(62,109,1,'medium'),(63,2,1,'large'),(63,109,1,'medium'),(64,2,1,'large'),(65,2,5,'large'),(66,2,2,'large'),(67,2,2,'large'),(68,212,1,'regular'),(69,2,2,'large'),(70,2,1,'large'),(70,109,1,'medium'),(71,2,1,'large'),(72,2,1,'large'),(73,2,2,'large'),(73,109,1,'medium'),(73,65,3,'other'),(74,2,1,'large'),(74,109,1,'medium'),(75,2,1,'large'),(76,2,1,'large'),(77,2,2,'large'),(77,109,2,'medium'),(78,2,2,'large'),(78,212,4,'regular'),(79,2,1,'large'),(79,109,2,'medium'),(80,2,2,'large'),(80,109,2,'medium'),(81,2,1,'large'),(81,109,2,'medium'),(82,2,2,'large'),(83,2,2,'large'),(83,109,3,'medium'),(84,2,1,'large'),(84,109,2,'medium'),(85,2,1,'large'),(86,2,0,'large'),(87,2,1,'large'),(88,2,2,'large'),(89,2,1,'large'),(89,109,1,'medium'),(90,2,3,'large'),(91,2,1,'large'),(91,109,1,'medium'),(92,2,1,'large'),(93,2,1,'large'),(93,109,1,'medium'),(94,2,1,'large'),(94,109,1,'medium'),(94,65,3,'other'),(95,3,2,'medium'),(95,69,2,'regular'),(95,128,3,'regular'),(96,2,2,'regular'),(96,150,1,'medium'),(96,152,1,'medium'),(97,3,1,'large'),(97,109,1,'medium'),(98,2,1,'large'),(98,109,1,'medium'),(99,2,1,'large'),(99,226,1,'regular'),(99,222,1,'regular'),(100,2,2,'large'),(100,109,3,'medium'),(101,130,2,'regular'),(101,67,2,'medium'),(101,227,1,'medium'),(102,1,1,'large'),(102,209,3,'regular'),(102,128,2,'regular'),(103,2,1,'large'),(103,109,2,'medium'),(103,151,1,'medium'),(104,109,1,'medium'),(104,65,2,'other'),(104,4,1,'medium'),(105,2,4,'large'),(106,2,1,'large'),(106,109,1,'medium'),(107,2,1,'large'),(107,109,1,'medium'),(108,2,1,'large'),(108,109,1,'medium'),(109,109,1,'medium'),(110,2,1,'large'),(110,109,1,'medium'),(111,2,1,'large'),(111,109,1,'medium'),(112,3,2,'large'),(112,217,2,'regular'),(112,152,0,'medium'),(113,2,1,'large'),(114,2,1,'large'),(115,2,1,'large'),(115,109,1,'medium'),(116,2,1,'large'),(116,109,1,'medium'),(116,212,1,'regular'),(117,2,1,'large'),(118,2,1,'large'),(118,109,1,'medium'),(119,2,1,'large'),(119,109,1,'medium'),(120,2,2,'large'),(120,109,2,'medium'),(121,2,1,'large'),(122,2,1,'large'),(122,109,1,'medium'),(122,212,1,'regular'),(123,109,2,'medium'),(124,2,1,'large'),(124,109,1,'medium'),(125,2,1,'large'),(125,109,1,'medium'),(126,2,1,'large'),(126,109,1,'medium'),(127,109,1,'medium'),(127,212,1,'regular'),(128,2,1,'large'),(128,109,1,'medium'),(129,2,1,'large'),(129,109,1,'medium'),(130,65,4,'other'),(131,2,3,'large'),(131,109,2,'medium'),(132,2,1,'large'),(132,109,1,'medium'),(133,2,2,'large'),(133,109,2,'medium'),(134,2,1,'large'),(134,109,1,'medium'),(135,109,2,'medium'),(135,212,2,'regular'),(136,2,1,'large'),(136,109,1,'medium'),(137,2,1,'large'),(137,109,1,'medium'),(138,2,1,'large'),(139,2,1,'large'),(140,109,1,'medium'),(141,212,1,'regular'),(142,2,1,'large'),(143,109,1,'medium'),(143,212,1,'regular'),(144,2,1,'large'),(145,2,1,'large'),(146,109,1,'medium'),(147,109,1,'regular'),(148,212,1,'regular'),(149,212,1,'regular'),(150,2,1,'large'),(150,212,1,'regular'),(151,2,0,'large'),(151,217,0,'regular'),(152,212,1,'regular'),(152,65,1,'other'),(153,2,1,'large'),(154,2,1,'large'),(154,109,1,'medium'),(155,109,1,'medium'),(155,212,1,'regular'),(155,65,2,'other'),(155,4,1,'medium'),(156,2,1,'large'),(156,109,1,'medium'),(157,2,2,'large'),(157,109,2,'medium'),(157,212,3,'regular'),(158,2,1,'large'),(159,2,1,'large'),(160,2,1,'large'),(160,109,1,'medium'),(161,2,2,'large'),(161,109,2,'medium'),(162,2,3,'large'),(162,109,3,'medium'),(163,2,1,'large'),(163,109,1,'medium'),(164,2,2,'large'),(164,212,2,'regular'),(164,4,2,'medium'),(165,2,1,'large'),(165,109,1,'medium'),(166,2,1,'large'),(166,109,1,'medium'),(166,2,1,'large'),(166,109,1,'medium'),(166,212,1,'regular'),(167,2,1,'large'),(167,109,1,'medium'),(168,2,2,'large'),(168,109,2,'medium'),(168,212,2,'regular'),(169,2,2,'large'),(169,109,2,'medium'),(169,212,3,'regular'),(170,2,1,'large'),(170,109,1,'medium'),(170,212,1,'regular'),(171,2,2,'large'),(171,109,2,'medium'),(171,212,1,'regular'),(172,3,2,'large'),(172,67,2,'medium'),(172,152,3,'medium'),(173,2,2,'large'),(173,109,3,'medium'),(174,228,1,'large'),(174,109,1,'medium'),(178,2,2,'large'),(178,109,1,'medium'),(178,212,3,'regular'),(178,4,4,'medium'),(179,109,1,'medium'),(179,212,1,'regular'),(179,65,1,'other'),(179,271,3,'large'),(179,145,3,'medium'),(179,152,2,'medium'),(180,274,2,'medium'),(180,137,3,'regular'),(181,272,1,'medium'),(181,109,1,'medium'),(182,272,2,'medium'),(182,109,2,'medium'),(183,271,3,'regular'),(183,278,3,'regular'),(183,286,2,'medium');
/*!40000 ALTER TABLE `package_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Visa'),(2,'Master'),(3,'devspace'),(4,'paypal'),(5,'payza'),(6,'Visa/Master Card'),(7,'Master'),(8,'Mobile');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_transaction`
--

LOCK TABLES `payment_transaction` WRITE;
/*!40000 ALTER TABLE `payment_transaction` DISABLE KEYS */;
INSERT INTO `payment_transaction` VALUES (17,'2016-12-30','10:43:00.000000',1600.00,'pending','online',NULL,NULL,8,21,19,72),(18,'2016-12-30','10:45:36.000000',294.00,'pending','online',1,NULL,8,22,NULL,73),(19,'2016-12-30','10:46:32.000000',934.50,'pending','online',1,NULL,8,23,NULL,74),(20,'2016-12-30','10:47:07.000000',462.00,'pending','online',1,NULL,8,24,NULL,75),(21,'2016-12-30','10:51:00.000000',1600.00,'pending','online',NULL,NULL,8,25,19,76),(22,'2016-12-30','10:52:23.000000',340.20,'pending','online',1,NULL,8,26,NULL,77),(23,'2016-12-30','10:54:03.000000',472.50,'pending','online',1,NULL,8,27,NULL,78),(24,'2016-12-30','10:59:29.000000',1600.00,'pending','online',NULL,NULL,8,28,19,79),(25,'2017-01-02','15:58:11.000000',6243.30,'pending','online',NULL,NULL,8,29,19,80),(26,'2017-01-03','08:14:56.000000',462.00,'pending','online',2,NULL,8,30,NULL,81),(27,'2017-01-03','11:43:50.000000',462.00,'pending','online',2,NULL,8,31,NULL,82),(28,'2017-01-03','11:44:44.000000',4987.50,'pending','online',2,NULL,8,32,NULL,83),(29,'2017-01-03','11:46:24.000000',9056.88,'pending','online',2,NULL,8,33,NULL,84),(30,'2017-01-03','11:48:02.000000',2450.70,'pending','online',2,NULL,8,34,NULL,85),(31,'2017-01-03','13:31:46.000000',189.00,'pending','online',2,NULL,8,35,NULL,86),(32,'2017-01-04','13:25:50.000000',5029.50,'pending','online',2,NULL,8,36,NULL,87);
/*!40000 ALTER TABLE `payment_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_transaction_discount`
--

LOCK TABLES `payment_transaction_discount` WRITE;
/*!40000 ALTER TABLE `payment_transaction_discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_transaction_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'ADD_USER','Permission to add user'),(2,'EDIT_USER','Permission to modify user'),(3,'BLOCK_USER','Permission to remove user from user list and block'),(4,'BLOCK_USER_RESTORE','Permission to restore a blocked users'),(5,'VIEW_USER','Permission to view the details of user'),(6,'ADD_ITEM','Permission to add an item'),(7,'EDIT_ITEM','Permission to modify an item'),(8,'DELETE_ITEM','Permission to remove an item'),(9,'VIEW_ITEMS','Permission to view the details of items'),(10,'ADD_PACKAGE','Permission to add an package'),(11,'EDIT_PACKAGE','Permission to modify an package'),(12,'DELETE_PACKAGE','Permission to remove an package'),(13,'VIEW_PACKAGE','Permission to view the details of packages'),(14,'ADD_CATEGORY','Permission to add a new category'),(15,'EDIT_CATEGORY','Permission to modify a category details'),(16,'DELETE_CATEGORY','Permission to remove a category'),(17,'VIEW_CATEGORY','Permission to view the details of categories'),(18,'APPROVE_COMMENTS','Permission to approve user comments'),(19,'DELETE_COMMENTS','Permission to remove a comment'),(20,'ADD_GROUP','Permission to add a new group'),(21,'EDIT_GROUP','Permission to modify a group'),(22,'DELETE_GROUP','Permission to remove a group'),(23,'VIEW_GROUP','Permission to view groups'),(24,'ADD_PERMISSION','Permission to add permission'),(25,'EDIT_PERMISSION','Permission to modify permission'),(26,'DELETE_PERMISSION','Permission to delete permission'),(27,'ADD_MODULE','Permission to add module'),(28,'MODIFY_MODULE','Permission to modify module'),(29,'VIEW_REPORTS','Permission to view reports'),(30,'CREATE_REPORTS','Permission to generate a reports'),(31,'DELETE_REPORTS','Permission to delete the created reports'),(32,'VIEW_PROFILE','View the profile'),(33,'VIEW_COMMENTS','Permission to view the user comments'),(34,'LOGIN','Login to the system, Only for active status');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (49,'item',1,2,3,3,'large','AAASS SSS'),(50,'item',109,1,NULL,NULL,'regular',''),(51,'item',272,1,NULL,NULL,'regular',''),(52,'item',137,1,NULL,NULL,'regular',''),(53,'item',1,2,3,3,'large','AAASS SSS'),(54,'item',69,2,NULL,NULL,'regular',''),(55,'item',127,2,NULL,NULL,'regular',''),(56,'item',1,2,3,3,'large','AAASS SSS'),(57,'item',127,1,NULL,NULL,'regular',''),(58,'item',243,2,NULL,NULL,'regular',''),(59,'item',272,2,280,278,'medium',''),(60,'item',137,1,NULL,NULL,'regular',''),(61,'item',137,1,NULL,NULL,'regular',''),(62,'item',65,3,NULL,NULL,'medium',''),(63,'item',69,1,NULL,NULL,'regular',''),(64,'item',272,2,192,278,'medium',''),(65,'item',281,2,278,151,'regular',''),(66,'item',221,1,NULL,NULL,'medium',''),(67,'item',281,2,NULL,NULL,'regular',''),(68,'item',285,1,NULL,NULL,'medium',''),(69,'item',286,2,NULL,NULL,'medium',''),(70,'item',69,1,NULL,NULL,'regular',''),(71,'item',272,2,280,278,'medium','gh fh fgth fgh\nfgh\nfghhhhhhhhhhhhhhfg fghfffffffffffffffffffffffffffffhghhhhhhhhhhhhhhhfgh');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `seasonal_discount`
--

LOCK TABLES `seasonal_discount` WRITE;
/*!40000 ALTER TABLE `seasonal_discount` DISABLE KEYS */;
INSERT INTO `seasonal_discount` VALUES (1,10.25,'2016-05-04','2016-09-29','active'),(2,5.25,'2016-12-01','2016-12-31','inactive'),(3,15.00,'2017-04-01','2017-04-30','active'),(4,8.25,'2016-02-08','2016-02-15','inactive'),(5,12.25,'2016-07-11','2016-07-19','inactive');
/*!40000 ALTER TABLE `seasonal_discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` VALUES (72,'PS00001','2016-12-30','10:43:00',1600.00,NULL,19),(73,'PS00002','2016-12-30','10:45:36',294.00,1,NULL),(74,'PS00003','2016-12-30','10:46:32',934.50,1,NULL),(75,'PS00004','2016-12-30','10:47:07',462.00,1,NULL),(76,'PS00005','2016-12-30','10:51:00',1600.00,NULL,19),(77,'PS00006','2016-12-30','10:52:22',340.20,1,NULL),(78,'PS00007','2016-12-30','10:54:03',472.50,1,NULL),(79,'PS00008','2016-12-30','10:59:29',1600.00,NULL,19),(80,'PS00009','2017-01-02','15:58:11',6243.30,NULL,19),(81,'PS00010','2017-01-03','08:14:55',462.00,2,NULL),(82,'PS00011','2017-01-03','11:43:50',462.00,2,NULL),(83,'PS00012','2017-01-03','11:44:44',4987.50,2,NULL),(84,'PS00013','2017-01-03','11:46:24',9056.88,2,NULL),(85,'PS00014','2017-01-03','11:48:02',2450.70,2,NULL),(86,'PS00015','2017-01-03','13:31:46',189.00,2,NULL),(87,'PS00016','2017-01-04','13:25:50',5029.50,2,NULL);
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shopping_cart_product`
--

LOCK TABLES `shopping_cart_product` WRITE;
/*!40000 ALTER TABLE `shopping_cart_product` DISABLE KEYS */;
INSERT INTO `shopping_cart_product` VALUES (72,49),(73,50),(74,51),(75,52),(76,53),(77,54),(78,55),(79,56),(80,57),(80,58),(80,59),(81,60),(82,61),(83,62),(83,63),(83,64),(84,65),(84,66),(85,67),(85,68),(85,69),(86,70),(87,71);
/*!40000 ALTER TABLE `shopping_cart_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES ('large',1280.00,1),('large',960.00,2),('large',1380.00,3),('large',120.00,65),('large',990.00,68),('large',987.00,126),('large',976.00,210),('large',789.00,211),('large',890.00,228),('large',1240.00,271),('large',1460.00,274),('large',1395.00,275),('large',1440.00,283),('large',860.00,286),('medium',890.00,1),('medium',740.00,3),('medium',390.00,4),('medium',650.00,37),('medium',90.00,65),('medium',680.00,67),('medium',590.00,68),('medium',420.00,109),('medium',589.00,126),('medium',400.00,127),('medium',820.00,130),('medium',158.00,132),('medium',250.00,133),('medium',210.00,145),('medium',322.00,150),('medium',540.00,151),('medium',200.00,152),('medium',430.00,154),('medium',610.00,191),('medium',688.00,192),('medium',578.00,211),('medium',53.00,218),('medium',7474.00,221),('medium',656.00,223),('medium',450.00,227),('medium',730.00,228),('medium',455.00,229),('medium',767.00,230),('medium',766.00,231),('medium',674.00,233),('medium',52.00,241),('medium',364.00,243),('medium',675.00,244),('medium',543.00,254),('medium',379.00,257),('medium',265.00,259),('medium',480.00,261),('medium',625.00,262),('medium',555.00,267),('medium',532.00,268),('medium',369.00,269),('medium',444.00,270),('medium',940.00,271),('medium',1650.00,272),('medium',888.00,273),('medium',985.00,274),('medium',875.00,275),('medium',900.00,276),('medium',375.00,277),('medium',485.00,278),('medium',540.00,280),('medium',590.00,281),('medium',860.00,283),('medium',645.00,284),('medium',920.00,285),('medium',347.00,286),('other',150.00,65),('other',786.00,118),('regular',560.00,1),('regular',490.00,2),('regular',470.00,3),('regular',240.00,4),('regular',380.00,37),('regular',70.00,65),('regular',450.00,67),('regular',390.00,68),('regular',180.00,69),('regular',280.00,109),('regular',368.00,118),('regular',577.00,126),('regular',250.00,127),('regular',310.00,128),('regular',650.00,130),('regular',100.00,131),('regular',88.00,132),('regular',160.00,133),('regular',440.00,137),('regular',1447.00,140),('regular',135.00,145),('regular',200.00,150),('regular',320.00,151),('regular',110.00,152),('regular',224.00,154),('regular',340.00,185),('regular',210.00,186),('regular',452.00,190),('regular',370.00,191),('regular',125.00,192),('regular',144.00,193),('regular',2211.00,194),('regular',44232.00,195),('regular',33.00,196),('regular',240.00,197),('regular',808.00,200),('regular',45.00,201),('regular',4532.00,204),('regular',465.00,205),('regular',54321.00,206),('regular',565.00,208),('regular',400.00,209),('regular',457.00,210),('regular',566.00,212),('regular',56.00,214),('regular',536.00,216),('regular',185.00,217),('regular',545.00,219),('regular',576.00,222),('regular',352.00,223),('regular',355.00,225),('regular',325.00,226),('regular',580.00,228),('regular',454.00,230),('regular',666.00,232),('regular',6464.00,235),('regular',140.00,236),('regular',34.00,239),('regular',42.00,241),('regular',453.00,243),('regular',532.00,244),('regular',222.00,246),('regular',740.00,248),('regular',334.00,254),('regular',657.00,257),('regular',664.00,259),('regular',320.00,261),('regular',485.00,262),('regular',140.00,263),('regular',280.00,264),('regular',187.00,265),('regular',550.00,266),('regular',141.00,267),('regular',333.00,268),('regular',125.00,269),('regular',44.00,270),('regular',690.00,271),('regular',890.00,272),('regular',333.00,273),('regular',485.00,275),('regular',580.00,276),('regular',295.00,277),('regular',375.00,278),('regular',344.00,279),('regular',370.00,280),('regular',360.00,281),('regular',310.00,282),('regular',357.00,284),('regular',650.50,285);
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Mr','admin','d033e22ae348aeb5660fc2140aec35850c4da997','Piumantha','Heshan','sysadmin@pizzashefu.com','+94778908976','No:2, Dangarama Road','Kollupitiya','Colombo','System Adinistrator','Adminintration','Colombo','2015-08-04','active'),(2,'Mrs','adminmanager','47597d7f4c61063f651df834bed02203b816d996','Piumika','Dankotuva','adminmanager@pizzashefu.com','+94710098765','No:32, ','Kadawatha','Gampaha','System Manager','Adminintration','Colombo','2015-09-22','active'),(3,'Mr','ccmanager','e928bc3e58bde5afa2de18334666022411a5b290','Isuru','Godakumbura','ccmanager@pizzashefu.com','+94774352231','No:556,Raddoluwa','Katunayaka','Gampaha','Customer Manager','Adminintration','Colombo','2016-01-12','active'),(4,'Mr','customercare','d9ba6e298a34f4648ce07ec93ef099c58fff8e50','Thanuja','Abeyrathna','customercare@pizzashefu.com','+94713456722','No:54,','Pilimathalawa','Kandy','Cashier','Finance','Colombo','2014-10-14','active'),(5,'Mrs','customercare2','507db975e3a0024893db4aba771625dc2131d135','Manjula','Priyanthi','customercare2@pizzashefu.com','+94712340098','No:43','Baseline Road','Colombo','Main Chef','Logistic','Colombo','2016-04-26','active'),(6,'Mr','reportmanager','e0ef0b3a7d4e03ed6a746896826ad04dffa0c199','Nadun','Udayanga','reportmanager@pizzashefu.com','+94772345670','No:889,','Maligawaththa Lane','Kandy','Reporting Manager','Reporting','Colombo','2014-03-17','active'),(9,'mr','fhdf','5f4d828167ef89b571b2f34653e943e1059727d9','gfh','hfgh','hgfdh','546547','gbfh','cghf','fgfdh','sysAdmin','administration','1','2016-09-28','inactive'),(10,'mr','fhgh','346b7f84f8832adf84efe2847bb468b4a55c1cd0','gnf','gjfdgj','ghgfmnjf','65465765','gngfj','ghfgh','ghfgh','System Admin','Administration','Colombo','2016-09-28','inactive'),(11,'mr','cashier1','7dfa962e6080d8516d0aac719064f8584c0c5f74','name1','name2','name1@gmail.com','+94778765467','91/A','Baseline Road','Colombo','Cashier','Finance','Gampaha','2016-09-29','active'),(12,'mr','dgfhg','9e8535a5524d47e72de30cb86d07f38cecacd255','Namal','Perera','namal@yahoo.com','+94778891112','44','Udugampola','Gampaha','System Admin','Administration','Colombo','2016-09-29','active'),(13,'mr','cashier','c72c109eb688f084eb4081fe12423f15efca7536','hfhftgh','rggy','fhghj','5436547659','fhhf','fhgh','dfhgfh','Cashier','Administration','Colombo','2016-09-29','active'),(14,'mr','tttyyynnn','70b3cc1fe0cb7f1ec8300539bee4a1435f5d1b5e','rgr','etrt','fgy54ryt','546576','fhfgh','fgfdh','fgfd','Cashier','Finance','Gampaha','2016-09-29','inactive'),(15,'mr','qqqqwwww','012a4b48b33473966a0d5ddb64ffac4e348e31dd','juyhj','hjhjk','jhkmhnkm','76878','hjhk','hjh','jhkjh','System Manager','Finance','Colombo','2016-09-29','active'),(16,'mr','namesuccess','1ff9325037614417996f9b771594616fa5dd917b','nameSuccess1','nameSuccess2','success@gmail.com','+94008674542','345/A','Gangaarama Road','Colombo','System Manager','Administration','Gampaha','2016-09-30','active'),(18,'mr','namesuccess34','1ff9325037614417996f9b771594616fa5dd917b','nameSuccess1','nameSuccess2','success@gmail.com','+94008674542','345/A','Gangaarama Road','Colombo','System Manager','Administration','Gampaha','2016-09-30','active'),(19,'mr','3435tfhgfhg','b3be0cff30c9b11a0622c22d00e87699a8694ee3','gfrrgr','etret','gfrh','5465876','fhfd','fhgf','fhgfd','Reporting Manager','Administration','Ja-Ela','2016-09-30','active'),(20,'mr','1222','31bb10d7d63a35c6b01de85afab8947039dd04ec','Upeka','wrwr','rthyt','75768','bvhng','bvn','Kandana','Reporting Manager','Finance','Ja-Ela','2016-09-30','active'),(21,'mr','565768dgf','71d8881ac44241756fe81adc6981ed2f9e20b413','try','yhry','dgfh','ry','gf','gf','gfghf','Main Chef','Reporting','Gampaha','2016-09-30','active'),(22,'mr','543546','74f4c62779bf24c28a8dd35659271ad098b6e686','','','','','','tuyti','hfhjt','Main Chef','Finance','Colombo','2016-09-30','inactive'),(23,'mr','122345','18e20c595b05bbcd72ff7686ba818b3e1f24de16','dgdfg','dgdg','gfhfh','54y765yhfh','gfh','gfhh','fgfh','Reporting Manager','Finance','Colombo','2016-09-30','active'),(24,'mr','difna','412f6f3150489c0ef355f83328e925e75f4878cc','dgdfg','dgdg','gfhfh','54y765yhfh','gfh','gfhh','fgfh','Main Chef','Finance','Colombo','2016-09-30','inactive'),(26,'mr','difna1233','941e46e59c8f92f3f2022baf4200bd34a0001c59','','','','','','','','Reporting Manager','Finance','Colombo','2016-09-30','active'),(28,'mr','sfsdyhug','941e46e59c8f92f3f2022baf4200bd34a0001c59','','','','','','','','Main Chef','Finance','Colombo','2016-09-30','inactive'),(30,'mr','sfsdyhug245w6d','941e46e59c8f92f3f2022baf4200bd34a0001c59','wwwwww','','','','','','','Customer Manager','Finance','Colombo','2016-09-30','inactive'),(31,'mr','user123','0440c124f8e76d08a1df569264d523f57f8b1cb5','gfh','fhfh','tgrfyhefyhf','4354657','sdsg','dgdfg','54tgxfgf','Customer Manager','Reporting','Colombo','2016-09-30','inactive'),(32,'mr','dddddd','73bb218010f5ee9b22b5759ca43f7480a4421b93','dddddd','','','','','','','Main Chef','Finance','Colombo','2016-09-30','inactive'),(33,'mr','111','aa88578262eb4c65098b445a34a9a554d84502fb','','','','','','','','Main Chef','Finance','Colombo','2016-09-30','inactive'),(34,'mr','32324','14ce18b5486c9dcf1e1aea673d9f89b56c0754cc','','','','','','','','Main Chef','Finance','Colombo','2016-09-30','inactive'),(35,'mr','gfgf','479c1043755743f5e026e33674430f35277bd33a','','','','','','','','Cashier','Finance','Colombo','2016-09-30','inactive'),(36,'mr','fyhg','b20a382f0e9943cde17d58771b8ab199827b9d75','','','jghjhg','','','','','Cashier','Finance','Colombo','2016-09-30','inactive'),(37,'mr','fryfyh','006cff2af6e033e865cc071a21731d55a3f50476','','yryty','','yty','','','','Cashier','Finance','Colombo','2016-09-30','inactive'),(38,'mr','hfhgfhg','a3026f262bbb54aa9486bca7244e83e1f3dc5108','','hgj','nvcmc','hncgjn','','','','Cashier','Finance','Colombo','2016-09-30','inactive'),(39,'mr','dgdg','4bc360e54edd070e5d1db85e5b9c1445106e40a1','fgfhd','dgdg','ddgdg','dgfgh','','','','Reporting Manager','Finance','Colombo','2016-10-03','inactive'),(40,'mr','dfgfdhgfh','dd31b02fccc5b7edc3bfd66ad32af79c7d0e4279','gtutu','','yrytytu','54657gfy','ghjgj','ghgj','dyhdyty','Reporting Manager','Finance','Colombo','2016-10-03','inactive'),(41,'mr','etert','f55c05c147b7483e073f6200d8ea98ba4887164b','fdyry','','sdtt','dtry','dtdt','dtrt','ytry','Reporting Manager','Finance','Colombo','2016-10-03','inactive'),(43,'mr','dgdgrr555','ee11e888044bf94d89740f89381b6c1ebfdc47ad','test1','','dtdgf','654657','dgfdg','dfdg','dfdg','Customer Manager','Finance','Colombo','2016-10-03','inactive'),(44,'mr','test2','99c309a2347053b7504f4c268787e0f3b6bb25c8','dgvd','','6565','sfsf','','fhfd','dhfh','Customer Manager','Finance','Colombo','2016-10-03','inactive'),(45,'mr','fdfd','99220d2e50081613916244869780d50c96dc0f77','test3','','fhgfhgh@fhfhf.ff','fgfh','w443','fgdg','xvfxfv','Customer Manager','Finance','Colombo','2016-10-03','inactive'),(47,'mr','ttttsfd','98a46b987cc1a031527a1f1fa2d313ea2bd53dc5','test5','tttt','testt@gmail.com','+94578900934','23/A','Bopitiya','Ja-Ela','System Manager','Administration','Gampaha','2016-10-04','inactive'),(48,'mr','test5','ae520f65ff524a9267143a101ef6c7751085cb9d','test7','','my@gmail.com','+94434586898','342','malwatta road','Peradeniya','System Manager','Finance','Colombo','2016-10-04','inactive'),(50,'mr','test5a','ae520f65ff524a9267143a101ef6c7751085cb9d','ddg','','my@gmail.com','+94434586898','342','malwatta road','Peradeniya','System Manager','Finance','Colombo','2016-10-04','inactive'),(54,'mr','test5sss','ae520f65ff524a9267143a101ef6c7751085cb9d','yrfyf','','my123@gmail.com','+94434586000','342','dsfdg','Colombo','System Manager','Finance','Colombo','2016-10-04','inactive'),(56,'mr','test5dfgdg','7e9bf44a6ed1cb1da33c8974a8c5f5985a6c7876','ddggd','','my12fdfg3@gmail.com','+94434586099','rr45','fsfgvdc','Colombo','	 System Admin','Finance','Colombo','2016-10-04','inactive'),(59,'mr','test56rytreg','a1ab0c4f8a1934f394bd238b480a8aed2f2c0061','dgdgs','','name@gkfdsf.fhf','+94567899001','3453','vgdg','dgdhf','System Admin','Finance','Colombo','2016-10-04','inactive'),(61,'mr','test51','da84dea20f9ab5f4a4500852dfb6733dde1fdb9e','dfsf','','name@gkfdsf.fhf','+94567899001','3453','vgdg','dgdhf','	 System Admin','Finance','Colombo','2016-10-04','inactive'),(65,'mr','test5dsfsf','da84dea20f9ab5f4a4500852dfb6733dde1fdb9e','dgdgs','','name@gkfdsf.com','+94567899001','','dsfg','Gampaha','Cashier','Finance','Colombo','2016-10-04','active'),(68,'mr','test9','a1ab0c4f8a1934f394bd238b480a8aed2f2c0061','sfsf','','difna@hsenidlanka.com','+94567899001','','dsfg','Gampaha','Cashier','Finance','Colombo','2016-10-04','inactive'),(70,'mr','test57','da84dea20f9ab5f4a4500852dfb6733dde1fdb9e','dgdgs','','name@gkfdsf.fhf','+94567899001','','dsfg','dgdhf','Cashier','Finance','Colombo','2016-10-04','active'),(71,'mr','userLast','32400c9817c6bce3bb261bec6dc91f65eaa68218','fhfh','dgf','difna4@gmail.com','+94778902334','445','ghfh','Colombo','Cashier','Logistic','Colombo','2016-10-05','active'),(72,'mr','userLast12345566','06133c6802bb95963417c71ec5d68da01850482a','name12','name123','difna344@gmail.com','+94778902334','445/A','udugampola','Gampaha','Cashier','Logistic','Gampaha','2016-10-05','active'),(73,'mr','userLast89','fc03ea7ab829aa62aca93582daaedd49250aec07','qqqqq','name123','difna344fs@gmail.com','+94778902334','445/B','udugampola','Gampaha','System Admin','Finance','Colombo','2016-10-05','active'),(74,'mr','staff4','1977ea34a86a0c9636ec01f8b0457ce8b9e3e267','staff4','','jdfdgf@gmail.com','+94778549474','23','Balangoda','Gampaha','System Admin','Finance','Colombo','2016-10-07','active'),(75,'mr','userLast455','06133c6802bb95963417c71ec5d68da01850482a','qqqqq','name123','deqfeifna344@gmail.com','+94778902334','445/B','Gangoda','Gampaha','System Admin','Finance','Colombo','2016-10-07','active'),(76,'mr','tharin','082de9ba1e0940cfea9cf4b632069499d064718e','Tharindu','Naveen','tharindutna@gmail.com','+94712234567','NO:3','Temple Road','Mathale','System Manager','Administration','Ja-Ela','2016-10-07','active'),(77,'mr','dddd','385992a147061966a15ae7ddafc801e721c0185d','ddd','ddd','fdgdg@gmail.com','+94778899055','ewrwr','fddg','Colombo','Reporting Manager','Reporting','Colombo','2016-10-19','active'),(79,'mr','staff1','e51157a25105c5db6deb0fcc52482ecb5ded2173','staff1','staff12','staff@gmail.com','+94778987654','No34','Baselin','Gampaha','Cashier','Administration','Gampaha','2016-10-25','active'),(80,'mr','demo123','54ec8312118a3d290a4ad233858f1f784fa3b095','demo1','demo11','demo@gmail.com','+94778900987','No:1','Kirillawala','Colombo','Cashier','Finance','Gampaha','2016-11-01','active'),(81,'mr','sudarimadu','30bd23ab01c2538eb2406ad464db3a15e291f689','Sudari','Madu','satff@yahoo.lk','+94778900098','No.23','Temple Road','Colombo','System Admin','Administration','Ja-Ela','2016-12-21','active'),(82,'mr','wwww','35dc4e8716cf015089c8686c6b08b43d15bb242a','www','www','www@gmail.com','+94778890098','www','www','Kandana','System Manager','Administration','Kadana','2016-12-21','active'),(90,'mr','mnb123','1ce3c14c2a6c7e0d279a7053241f48611a6ca9a3','Menuka123','Ishan','menukaishan5@gmail.com','+94717388432','Meth Niwasa','Gandara','Matara','System Admin','Finance','Colombo','2016-12-22','active');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sub_category`
--

LOCK TABLES `sub_category` WRITE;
/*!40000 ALTER TABLE `sub_category` DISABLE KEYS */;
INSERT INTO `sub_category` VALUES (6,'Ice-cream','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','Mrsd',4),(7,'Pudding','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','nivax',4),(8,'Salad','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','dextra',4),(9,'Brownies','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','Roeia',4),(10,'Cake','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','Gehab',4),(11,'Tea','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','Jein',2),(12,'Coffe-Latte','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','Ozan',2),(13,'Soft drinks','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','Jein',2),(14,'Fresh juice','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','Jein',2),(15,'Veg-salad','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','deimn',5),(16,'Spicy salad','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','deimn',5),(17,'Veggies','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','nivax',3),(18,'Meat','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','nivax',3),(19,'Cheese','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','nivax',3),(20,'Dips','Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text','nivax',3),(114,'ddd','ddddddddddddddd','admin',1),(115,'ssssssss','ssssssss','admin',1),(122,'Italian','','nht',1),(123,'Gourmet','','feff',1),(124,'Vanila','can be in large and midium','admin',46),(125,'Chocalate','this is of chocolate fovour in small size','admin',46),(130,'Spicy','this is the Sri Lankan style','admin',48),(131,'Mixed Pasta','This is the mixture of Salads ','admin',48),(132,'Chilly','The chilly taste pasta','admin',48);
/*!40000 ALTER TABLE `sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'veg'),(2,'non-veg'),(3,'carbonated'),(4,'non-carbonated');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `verification_code`
--

LOCK TABLES `verification_code` WRITE;
/*!40000 ALTER TABLE `verification_code` DISABLE KEYS */;
INSERT INTO `verification_code` VALUES (9,43,'Hg1XJJ7qFsHmy1bc%!AjdTUwxEakrS3Y'),(21,45,'jC9@l8QLCMjH$PXr95ltdz9$pcnuse%2'),(22,45,'1x3e%eRTpAwqweJBYHwF5qmanRCFukQL');
/*!40000 ALTER TABLE `verification_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-06 13:28:34
