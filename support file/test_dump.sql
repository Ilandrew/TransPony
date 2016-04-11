-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: transpony
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `CAR`
--

DROP TABLE IF EXISTS `CAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAR` (
  `id_car` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `license_plate` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_vendor_car` int(10) unsigned NOT NULL,
  `id_model_car` int(10) unsigned NOT NULL,
  `id_car_type` int(10) unsigned NOT NULL,
  `fuel_consumption` decimal(6,3) NOT NULL,
  PRIMARY KEY (`id_car`),
  KEY `IXFK_CAR_CAR_TYPE` (`id_car_type`),
  KEY `IXFK_CAR_MODEL_CAR` (`id_model_car`),
  KEY `IXFK_CAR_VENDOR_CAR` (`id_vendor_car`),
  CONSTRAINT `FK_CAR_CAR_TYPE` FOREIGN KEY (`id_car_type`) REFERENCES `CAR_TYPE` (`id_car_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CAR_MODEL_CAR` FOREIGN KEY (`id_model_car`) REFERENCES `MODEL_CAR` (`id_model_car`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CAR_VENDOR_CAR` FOREIGN KEY (`id_vendor_car`) REFERENCES `VENDOR_CAR` (`id_vendor_car`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAR`
--

LOCK TABLES `CAR` WRITE;
/*!40000 ALTER TABLE `CAR` DISABLE KEYS */;
INSERT INTO `CAR` VALUES (1,'0891-CK',1,1,1,24.200),(2,'0892-CK',1,1,1,24.200),(3,'0893-CK',1,1,1,24.200),(4,'0123-KL',2,2,2,12.700),(5,'0124-KL',2,2,2,12.700),(6,'7777-HI',3,3,3,28.000);
/*!40000 ALTER TABLE `CAR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CARGO`
--

DROP TABLE IF EXISTS `CARGO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CARGO` (
  `id_cargo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `weight` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `id_cargo_type` int(10) unsigned DEFAULT NULL,
  `id_provider` int(10) unsigned DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_cargo`),
  KEY `IXFK_CARGO_CARGO_TYPE` (`id_cargo_type`),
  KEY `IXFK_CARGO_PROVIDER` (`id_provider`),
  CONSTRAINT `FK_CARGO_CARGO_TYPE` FOREIGN KEY (`id_cargo_type`) REFERENCES `CARGO_TYPE` (`id_cargo_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CARGO_PROVIDER` FOREIGN KEY (`id_provider`) REFERENCES `PROVIDER` (`id_provider`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CARGO`
--

LOCK TABLES `CARGO` WRITE;
/*!40000 ALTER TABLE `CARGO` DISABLE KEYS */;
INSERT INTO `CARGO` VALUES (1,'Кольцо(единое)',1,1,11,1,1000000.00),(2,'Пиво',20000,15000,8,2,5000.00),(3,'Керпичи',5000,1000,10,2,2700.00),(4,'Яблоки',1000,200,10,1,1300.00),(5,'Конфеты',2000,4000,10,1,2200.00),(6,'Мармеладки',5000,7000,10,1,7000.00);
/*!40000 ALTER TABLE `CARGO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CARGO_TYPE`
--

DROP TABLE IF EXISTS `CARGO_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CARGO_TYPE` (
  `id_cargo_type` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_cargo_type`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CARGO_TYPE`
--

LOCK TABLES `CARGO_TYPE` WRITE;
/*!40000 ALTER TABLE `CARGO_TYPE` DISABLE KEYS */;
INSERT INTO `CARGO_TYPE` VALUES (1,'Взрывчатое'),(2,'Газы'),(3,'Легковоспломиняющиеся жидкости'),(4,'Легковоспломиняющиеся твёрдые вещества'),(5,'Окисляющие вещества и органические пер-оксиды'),(6,'Радиоактивные вещества'),(7,'Коррозийные вещества'),(8,'Ядовитые инфекционные вещества'),(9,'Прочие опасные вещества'),(10,'Другое'),(11,'Магические');
/*!40000 ALTER TABLE `CARGO_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CAR_TYPE`
--

DROP TABLE IF EXISTS `CAR_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CAR_TYPE` (
  `id_car_type` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_car_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CAR_TYPE`
--

LOCK TABLES `CAR_TYPE` WRITE;
/*!40000 ALTER TABLE `CAR_TYPE` DISABLE KEYS */;
INSERT INTO `CAR_TYPE` VALUES (1,'Грузовик'),(2,'Микроавтобус'),(3,'Спорткар');
/*!40000 ALTER TABLE `CAR_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHECK_POINT`
--

DROP TABLE IF EXISTS `CHECK_POINT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CHECK_POINT` (
  `id_check_point` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `x` float(6,3) NOT NULL,
  `y` float(6,3) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_check_point_type` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_check_point`),
  KEY `IXFK_CHECK_POINT_CHECK_POINT_TYPE` (`id_check_point_type`),
  CONSTRAINT `FK_CHECK_POINT_CHECK_POINT_TYPE` FOREIGN KEY (`id_check_point_type`) REFERENCES `CHECK_POINT_TYPE` (`id_check_point_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHECK_POINT`
--

LOCK TABLES `CHECK_POINT` WRITE;
/*!40000 ALTER TABLE `CHECK_POINT` DISABLE KEYS */;
INSERT INTO `CHECK_POINT` VALUES (1,1.000,2.000,'Дворец эльфов',2),(2,2.000,3.000,'Тёмная гавань',7),(3,3.000,4.000,'Шкаф',7),(4,4.000,5.000,'Врата света',6),(5,5.000,6.000,'Врата тьмы',1),(6,6.000,7.000,'Родник жизни',4),(7,7.000,8.000,'Радужные облака',7),(8,8.000,9.000,'Склад единорогов',3),(9,1.000,17.000,'Пещеры гоблинов',2);
/*!40000 ALTER TABLE `CHECK_POINT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHECK_POINT_TYPE`
--

DROP TABLE IF EXISTS `CHECK_POINT_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CHECK_POINT_TYPE` (
  `id_check_point_type` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_check_point_type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHECK_POINT_TYPE`
--

LOCK TABLES `CHECK_POINT_TYPE` WRITE;
/*!40000 ALTER TABLE `CHECK_POINT_TYPE` DISABLE KEYS */;
INSERT INTO `CHECK_POINT_TYPE` VALUES (1,'Растаможка'),(2,'Загрузка'),(3,'Разгрузка'),(4,'Заправка'),(5,'Отдых'),(6,'Затомаживание'),(7,'Проезд');
/*!40000 ALTER TABLE `CHECK_POINT_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DELIVERY_POINT`
--

DROP TABLE IF EXISTS `DELIVERY_POINT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DELIVERY_POINT` (
  `id_delivery_point` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_delivery_point`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DELIVERY_POINT`
--

LOCK TABLES `DELIVERY_POINT` WRITE;
/*!40000 ALTER TABLE `DELIVERY_POINT` DISABLE KEYS */;
INSERT INTO `DELIVERY_POINT` VALUES (1,'Страна Грёз, г. Надежды пр. Единорогов д.26');
/*!40000 ALTER TABLE `DELIVERY_POINT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEE`
--

DROP TABLE IF EXISTS `EMPLOYEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEE` (
  `id_employee` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `second_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `middle_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `initials` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_user_credantials` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_employee`),
  KEY `IXFK_EMPLOYEE_USER_CREDANTIALS` (`id_user_credantials`),
  CONSTRAINT `FK_EMPLOYEE_USER_CREDANTIALS` FOREIGN KEY (`id_user_credantials`) REFERENCES `USER_CREDANTIALS` (`id_user_credantials`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEE`
--

LOCK TABLES `EMPLOYEE` WRITE;
/*!40000 ALTER TABLE `EMPLOYEE` DISABLE KEYS */;
INSERT INTO `EMPLOYEE` VALUES (1,'Максим','Дударев','Дмитриевич','Дударев М. Д.',1),(2,'Андрей','Ильюкевич','Викторович','Ильюкевич А. В.',2),(3,'Вадим','Видничук','Николаевич','Видничук В. Н.',3),(4,'Водила','Водителев','Драйверович','Водителев В. Д.',4),(5,'Авраам','Бернштейн','Рахамимович','Бернштейн А. Р.',5),(6,'Мефодий','Кириленко','Яковович','Кириленко М. Я.',6),(7,'Вин','Дизель','Маккартни','Дизель В. М.',7);
/*!40000 ALTER TABLE `EMPLOYEE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `M2M_CHECK_POINT_ROUTE`
--

DROP TABLE IF EXISTS `M2M_CHECK_POINT_ROUTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `M2M_CHECK_POINT_ROUTE` (
  `id_route` int(10) unsigned NOT NULL,
  `id_check_point` int(10) unsigned NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id_route`,`id_check_point`),
  KEY `IXFK_M2M_CHECK_POINT_ROUTE_CHECK_POINT` (`id_check_point`),
  KEY `IXFK_M2M_CHECK_POINT_ROUTE_ROUTE` (`id_route`),
  CONSTRAINT `FK_M2M_CHECK_POINT_ROUTE_CHECK_POINT` FOREIGN KEY (`id_check_point`) REFERENCES `CHECK_POINT` (`id_check_point`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_M2M_CHECK_POINT_ROUTE_ROUTE` FOREIGN KEY (`id_route`) REFERENCES `ROUTE` (`id_route`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `M2M_CHECK_POINT_ROUTE`
--

LOCK TABLES `M2M_CHECK_POINT_ROUTE` WRITE;
/*!40000 ALTER TABLE `M2M_CHECK_POINT_ROUTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `M2M_CHECK_POINT_ROUTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `M2M_EMPLOYEE_POSITION`
--

DROP TABLE IF EXISTS `M2M_EMPLOYEE_POSITION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `M2M_EMPLOYEE_POSITION` (
  `id_user_position` int(10) unsigned NOT NULL,
  `id_employee` int(10) unsigned NOT NULL,
  `begin` date NOT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`id_employee`,`id_user_position`),
  KEY `IXFK_M2M_EMPLOYEE_POSITION_EMPLOYEE` (`id_employee`),
  KEY `IXFK_M2M_EMPLOYEE_POSITION_USER_POSITION` (`id_user_position`),
  KEY `IXFK_USER_TIME_POSITION_USER_POSITION` (`id_user_position`),
  CONSTRAINT `FK_M2M_EMPLOYEE_POSITION_EMPLOYEE` FOREIGN KEY (`id_employee`) REFERENCES `EMPLOYEE` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_M2M_EMPLOYEE_POSITION_USER_POSITION` FOREIGN KEY (`id_user_position`) REFERENCES `USER_POSITION` (`id_user_position`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `M2M_EMPLOYEE_POSITION`
--

LOCK TABLES `M2M_EMPLOYEE_POSITION` WRITE;
/*!40000 ALTER TABLE `M2M_EMPLOYEE_POSITION` DISABLE KEYS */;
INSERT INTO `M2M_EMPLOYEE_POSITION` VALUES (4,1,'2016-02-12','2016-06-21'),(1,2,'2016-02-12','2016-06-21'),(2,3,'2016-02-12','2016-06-21'),(5,4,'2016-02-12','2016-06-21'),(3,5,'2016-02-12','2016-06-21'),(5,6,'2016-02-12','2016-06-21'),(5,7,'2016-02-12','2016-06-21');
/*!40000 ALTER TABLE `M2M_EMPLOYEE_POSITION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `M2M_EMPLOYEE_STATUS`
--

DROP TABLE IF EXISTS `M2M_EMPLOYEE_STATUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `M2M_EMPLOYEE_STATUS` (
  `id_employee` int(10) unsigned NOT NULL,
  `id_empoyee_status` int(10) unsigned NOT NULL,
  `begin` date NOT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`id_employee`,`id_empoyee_status`),
  KEY `IXFK_M2M_EMPLOYEE_STATUS_EMPLOYEE` (`id_employee`),
  KEY `IXFK_M2M_EMPLOYEE_STATUS_USER_STATUS` (`id_empoyee_status`),
  CONSTRAINT `FK_M2M_EMPLOYEE_STATUS_EMPLOYEE` FOREIGN KEY (`id_employee`) REFERENCES `EMPLOYEE` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_M2M_EMPLOYEE_STATUS_USER_STATUS` FOREIGN KEY (`id_empoyee_status`) REFERENCES `USER_STATUS` (`id_empoyee_status`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `M2M_EMPLOYEE_STATUS`
--

LOCK TABLES `M2M_EMPLOYEE_STATUS` WRITE;
/*!40000 ALTER TABLE `M2M_EMPLOYEE_STATUS` DISABLE KEYS */;
INSERT INTO `M2M_EMPLOYEE_STATUS` VALUES (1,1,'2016-02-12','2016-06-21'),(2,1,'2016-02-12','2016-06-21'),(3,1,'2016-02-12','2016-06-21'),(4,1,'2016-02-12','2016-06-21'),(5,1,'2016-02-12','2016-06-21'),(6,1,'2016-02-12','2016-06-21'),(7,1,'2016-02-12','2016-06-21');
/*!40000 ALTER TABLE `M2M_EMPLOYEE_STATUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `M2M_RECIEVER_DELIVERY_POINT`
--

DROP TABLE IF EXISTS `M2M_RECIEVER_DELIVERY_POINT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `M2M_RECIEVER_DELIVERY_POINT` (
  `id_reciever` int(10) unsigned NOT NULL,
  `id_delivery_point` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_reciever`,`id_delivery_point`),
  KEY `IXFK_M2M_RECIEVER_DELIVERY_POINT_DELIVERY_POINT` (`id_delivery_point`),
  KEY `IXFK_M2M_RECIEVER_DELIVERY_POINT_RECIEVER` (`id_reciever`),
  CONSTRAINT `FK_M2M_RECIEVER_DELIVERY_POINT_DELIVERY_POINT` FOREIGN KEY (`id_delivery_point`) REFERENCES `DELIVERY_POINT` (`id_delivery_point`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_M2M_RECIEVER_DELIVERY_POINT_RECIEVER` FOREIGN KEY (`id_reciever`) REFERENCES `RECIEVER` (`id_reciever`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `M2M_RECIEVER_DELIVERY_POINT`
--

LOCK TABLES `M2M_RECIEVER_DELIVERY_POINT` WRITE;
/*!40000 ALTER TABLE `M2M_RECIEVER_DELIVERY_POINT` DISABLE KEYS */;
INSERT INTO `M2M_RECIEVER_DELIVERY_POINT` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `M2M_RECIEVER_DELIVERY_POINT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MODEL_CAR`
--

DROP TABLE IF EXISTS `MODEL_CAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MODEL_CAR` (
  `id_model_car` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_model_car`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MODEL_CAR`
--

LOCK TABLES `MODEL_CAR` WRITE;
/*!40000 ALTER TABLE `MODEL_CAR` DISABLE KEYS */;
INSERT INTO `MODEL_CAR` VALUES (1,'X62'),(2,'1018'),(3,'F4000K'),(5,'Соник');
/*!40000 ALTER TABLE `MODEL_CAR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROVIDER`
--

DROP TABLE IF EXISTS `PROVIDER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROVIDER` (
  `id_provider` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(13) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_provider`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROVIDER`
--

LOCK TABLES `PROVIDER` WRITE;
/*!40000 ALTER TABLE `PROVIDER` DISABLE KEYS */;
INSERT INTO `PROVIDER` VALUES (1,'Пони корпорейтед','+375291234567','Селестия, г. Понивиль ул. Пегасов д. 1','sparcal@pony.org'),(2,'ГриффинЛенд','+104111123222','США, г. Куахок ул. Ложечная д. 18','sexeboy@rambler.net');
/*!40000 ALTER TABLE `PROVIDER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RECIEVER`
--

DROP TABLE IF EXISTS `RECIEVER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RECIEVER` (
  `id_reciever` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(13) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_reciever`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RECIEVER`
--

LOCK TABLES `RECIEVER` WRITE;
/*!40000 ALTER TABLE `RECIEVER` DISABLE KEYS */;
INSERT INTO `RECIEVER` VALUES (1,'Эльфы западного побережья','Страна Арнор, г. Серая гавань Ратуша','+042','generic_elf_n42@mdlert.lv'),(2,'ГоблинТорг','Туманные горы, г. Мория ул. Центральная д. 15 ','+012','goblin@narod.ru');
/*!40000 ALTER TABLE `RECIEVER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROUTE`
--

DROP TABLE IF EXISTS `ROUTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROUTE` (
  `id_route` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `total_length` decimal(10,2) NOT NULL,
  `count` int(11) DEFAULT '0',
  `id_employee` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_route`),
  KEY `IXFK_ROUTE_EMPOYEE` (`id_employee`),
  CONSTRAINT `FK_ROUTE_EMPOYEE` FOREIGN KEY (`id_employee`) REFERENCES `EMPLOYEE` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROUTE`
--

LOCK TABLES `ROUTE` WRITE;
/*!40000 ALTER TABLE `ROUTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROUTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRIP`
--

DROP TABLE IF EXISTS `TRIP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRIP` (
  `id_trip` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_trip_status` int(10) unsigned NOT NULL,
  `start_date` date NOT NULL,
  `expected_finish_date` date NOT NULL,
  `real_finish_date` date DEFAULT NULL,
  `real_fuel_consumption` decimal(6,3) DEFAULT NULL,
  `expected_fuel_consuption` decimal(6,3) NOT NULL,
  `driver_profit` decimal(10,2) NOT NULL,
  `expenses` decimal(10,2) NOT NULL,
  `id_waybill` int(10) unsigned NOT NULL,
  `id_route` int(10) unsigned NOT NULL,
  `id_car` int(10) unsigned NOT NULL,
  `id_employee` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_trip`),
  KEY `IXFK_TRIP_CAR` (`id_car`),
  KEY `IXFK_TRIP_EMPLOYEE` (`id_employee`),
  KEY `IXFK_TRIP_ROUTE` (`id_route`),
  KEY `IXFK_TRIP_TRIP_STATUS` (`id_trip_status`),
  KEY `IXFK_TRIP_WAYBILL` (`id_waybill`),
  CONSTRAINT `FK_TRIP_CAR` FOREIGN KEY (`id_car`) REFERENCES `CAR` (`id_car`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TRIP_EMPLOYEE` FOREIGN KEY (`id_employee`) REFERENCES `EMPLOYEE` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TRIP_ROUTE` FOREIGN KEY (`id_route`) REFERENCES `ROUTE` (`id_route`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TRIP_TRIP_STATUS` FOREIGN KEY (`id_trip_status`) REFERENCES `TRIP_STATUS` (`id_trip_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TRIP_WAYBILL` FOREIGN KEY (`id_waybill`) REFERENCES `WAYBILL` (`id_waybill`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRIP`
--

LOCK TABLES `TRIP` WRITE;
/*!40000 ALTER TABLE `TRIP` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRIP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRIP_STATUS`
--

DROP TABLE IF EXISTS `TRIP_STATUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRIP_STATUS` (
  `id_trip_status` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_trip_status`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRIP_STATUS`
--

LOCK TABLES `TRIP_STATUS` WRITE;
/*!40000 ALTER TABLE `TRIP_STATUS` DISABLE KEYS */;
INSERT INTO `TRIP_STATUS` VALUES (1,'Готов к выезду'),(2,'В пути'),(3,'Завершён'),(4,'Убит'),(5,'Задержан');
/*!40000 ALTER TABLE `TRIP_STATUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_CREDANTIALS`
--

DROP TABLE IF EXISTS `USER_CREDANTIALS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_CREDANTIALS` (
  `id_user_credantials` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_user_credantials`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_CREDANTIALS`
--

LOCK TABLES `USER_CREDANTIALS` WRITE;
/*!40000 ALTER TABLE `USER_CREDANTIALS` DISABLE KEYS */;
INSERT INTO `USER_CREDANTIALS` VALUES (1,'logist','logist'),(2,'ruler','ruler'),(3,'admin','admin'),(4,'driver','drver'),(5,'acc','acc'),(6,'driver1','driver1'),(7,'driver2','driver2');
/*!40000 ALTER TABLE `USER_CREDANTIALS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_POSITION`
--

DROP TABLE IF EXISTS `USER_POSITION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_POSITION` (
  `id_user_position` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_user_position`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_POSITION`
--

LOCK TABLES `USER_POSITION` WRITE;
/*!40000 ALTER TABLE `USER_POSITION` DISABLE KEYS */;
INSERT INTO `USER_POSITION` VALUES (1,'Руководитель'),(2,'Администратор'),(3,'Бухгалтер'),(4,'Логист'),(5,'Водитель');
/*!40000 ALTER TABLE `USER_POSITION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_STATUS`
--

DROP TABLE IF EXISTS `USER_STATUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_STATUS` (
  `id_empoyee_status` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_empoyee_status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_STATUS`
--

LOCK TABLES `USER_STATUS` WRITE;
/*!40000 ALTER TABLE `USER_STATUS` DISABLE KEYS */;
INSERT INTO `USER_STATUS` VALUES (1,'Работает'),(2,'Болеет'),(3,'В отпуске'),(4,'Уволен');
/*!40000 ALTER TABLE `USER_STATUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VENDOR_CAR`
--

DROP TABLE IF EXISTS `VENDOR_CAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VENDOR_CAR` (
  `id_vendor_car` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_vendor_car`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VENDOR_CAR`
--

LOCK TABLES `VENDOR_CAR` WRITE;
/*!40000 ALTER TABLE `VENDOR_CAR` DISABLE KEYS */;
INSERT INTO `VENDOR_CAR` VALUES (1,'Mersedes'),(2,'Газ'),(3,'Ferrari'),(4,'МАЗ');
/*!40000 ALTER TABLE `VENDOR_CAR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WAYBILL`
--

DROP TABLE IF EXISTS `WAYBILL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WAYBILL` (
  `id_waybill` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_cargo` int(10) unsigned DEFAULT NULL,
  `profit` decimal(10,2) NOT NULL,
  `id_reciever` int(10) unsigned DEFAULT NULL,
  `id_delivery_point` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_waybill`),
  KEY `IXFK_WAYBILL_CARGO` (`id_cargo`),
  KEY `IXFK_WAYBILL_DELIVERY_POINT` (`id_delivery_point`),
  KEY `IXFK_WAYBILL_RECIEVER` (`id_reciever`),
  CONSTRAINT `FK_WAYBILL_CARGO` FOREIGN KEY (`id_cargo`) REFERENCES `CARGO` (`id_cargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_WAYBILL_DELIVERY_POINT` FOREIGN KEY (`id_delivery_point`) REFERENCES `DELIVERY_POINT` (`id_delivery_point`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_WAYBILL_RECIEVER` FOREIGN KEY (`id_reciever`) REFERENCES `RECIEVER` (`id_reciever`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WAYBILL`
--

LOCK TABLES `WAYBILL` WRITE;
/*!40000 ALTER TABLE `WAYBILL` DISABLE KEYS */;
INSERT INTO `WAYBILL` VALUES (1,2,100.14,2,1),(2,1,1300.00,1,1),(3,6,990.00,1,1),(4,5,220.00,1,1),(5,3,5200.00,2,1),(6,4,1200.00,2,1);
/*!40000 ALTER TABLE `WAYBILL` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-10 23:23:31
