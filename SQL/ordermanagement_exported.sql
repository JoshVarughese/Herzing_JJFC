CREATE DATABASE  IF NOT EXISTS `ordermanagementdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ordermanagementdb`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ordermanagementdb
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `cleaningfunctionality`
--

DROP TABLE IF EXISTS `cleaningfunctionality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cleaningfunctionality` (
  `cleaningStatus` enum('Clean','Needs Cleaning','In Progress') NOT NULL,
  `tableID` int NOT NULL,
  KEY `tableID` (`tableID`),
  CONSTRAINT `cleaningfunctionality_ibfk_1` FOREIGN KEY (`tableID`) REFERENCES `tableservice` (`tableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cleaningfunctionality`
--

LOCK TABLES `cleaningfunctionality` WRITE;
/*!40000 ALTER TABLE `cleaningfunctionality` DISABLE KEYS */;
/*!40000 ALTER TABLE `cleaningfunctionality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) NOT NULL,
  `partySize` int NOT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventorymanagement`
--

DROP TABLE IF EXISTS `inventorymanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventorymanagement` (
  `foodItemID` int NOT NULL AUTO_INCREMENT,
  `ingredientStock` int NOT NULL,
  `stockAlerts` enum('Low','Medium','High') NOT NULL,
  PRIMARY KEY (`foodItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventorymanagement`
--

LOCK TABLES `inventorymanagement` WRITE;
/*!40000 ALTER TABLE `inventorymanagement` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventorymanagement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordermanagement`
--

DROP TABLE IF EXISTS `ordermanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordermanagement` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `tableID` int NOT NULL,
  `orderTime` datetime NOT NULL,
  `orderItems` text NOT NULL,
  `orderStatus` enum('Pending','In Progress','Completed','Cancelled') NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `tableID` (`tableID`),
  CONSTRAINT `ordermanagement_ibfk_1` FOREIGN KEY (`tableID`) REFERENCES `tableservice` (`tableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordermanagement`
--

LOCK TABLES `ordermanagement` WRITE;
/*!40000 ALTER TABLE `ordermanagement` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordermanagement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentsystem`
--

DROP TABLE IF EXISTS `paymentsystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentsystem` (
  `paymentID` int NOT NULL AUTO_INCREMENT,
  `orderID` int NOT NULL,
  `paymentMethod` enum('Credit Card','Debit Card','Cash','PayPal') NOT NULL,
  `paymentAmount` decimal(10,2) NOT NULL,
  `paymentStatus` enum('Completed','Pending','Failed') NOT NULL,
  PRIMARY KEY (`paymentID`),
  KEY `orderID` (`orderID`),
  CONSTRAINT `paymentsystem_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `ordermanagement` (`orderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentsystem`
--

LOCK TABLES `paymentsystem` WRITE;
/*!40000 ALTER TABLE `paymentsystem` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentsystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservationsystem`
--

DROP TABLE IF EXISTS `reservationsystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservationsystem` (
  `reservationID` int NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) NOT NULL,
  `contactInfo` varchar(255) NOT NULL,
  `reservationDateTime` datetime NOT NULL,
  `numberOfGuests` int NOT NULL,
  `tableID` int NOT NULL,
  PRIMARY KEY (`reservationID`),
  KEY `tableID` (`tableID`),
  CONSTRAINT `reservationsystem_ibfk_1` FOREIGN KEY (`tableID`) REFERENCES `tableservice` (`tableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservationsystem`
--

LOCK TABLES `reservationsystem` WRITE;
/*!40000 ALTER TABLE `reservationsystem` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservationsystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seatingsystem`
--

DROP TABLE IF EXISTS `seatingsystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seatingsystem` (
  `tableID` int NOT NULL AUTO_INCREMENT,
  `capacity` int NOT NULL,
  `cleaningStatus` enum('Clean','Dirty','In Progress') NOT NULL,
  PRIMARY KEY (`tableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seatingsystem`
--

LOCK TABLES `seatingsystem` WRITE;
/*!40000 ALTER TABLE `seatingsystem` DISABLE KEYS */;
/*!40000 ALTER TABLE `seatingsystem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staffID` int NOT NULL AUTO_INCREMENT,
  `staffRole` enum('Server','Chef','Manager','Cleaner') NOT NULL,
  `shiftSchedule` varchar(255) NOT NULL,
  PRIMARY KEY (`staffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_service`
--

DROP TABLE IF EXISTS `table_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_service` (
  `tableid` int NOT NULL AUTO_INCREMENT,
  `order_status` enum('Available','Occupied','Reserved') NOT NULL,
  PRIMARY KEY (`tableid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_service`
--

LOCK TABLES `table_service` WRITE;
/*!40000 ALTER TABLE `table_service` DISABLE KEYS */;
INSERT INTO `table_service` VALUES (1,'Available'),(2,'Available'),(3,'Occupied');
/*!40000 ALTER TABLE `table_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tableservice`
--

DROP TABLE IF EXISTS `tableservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tableservice` (
  `tableID` int NOT NULL AUTO_INCREMENT,
  `orderStatus` enum('Available','Occupied','Reserved') NOT NULL,
  PRIMARY KEY (`tableID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tableservice`
--

LOCK TABLES `tableservice` WRITE;
/*!40000 ALTER TABLE `tableservice` DISABLE KEYS */;
/*!40000 ALTER TABLE `tableservice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `walkinqueue`
--

DROP TABLE IF EXISTS `walkinqueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `walkinqueue` (
  `walkInQueueID` int NOT NULL AUTO_INCREMENT,
  `priorityLevel` enum('High','Medium','Low') NOT NULL,
  PRIMARY KEY (`walkInQueueID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `walkinqueue`
--

LOCK TABLES `walkinqueue` WRITE;
/*!40000 ALTER TABLE `walkinqueue` DISABLE KEYS */;
/*!40000 ALTER TABLE `walkinqueue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-12 22:26:05
