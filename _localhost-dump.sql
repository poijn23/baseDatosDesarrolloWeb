-- MySQL dump 10.13  Distrib 9.6.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: amazon_xalapa
-- ------------------------------------------------------
-- Server version	9.6.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `idarticulo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `Descripcion` varchar(255) NOT NULL,
  `existencias` int NOT NULL DEFAULT '0',
  `precio` decimal(10,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idarticulo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,'Paleta','una paleta',2,10),(2,'Gomita','Una gomita',500,1);
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `Numero_Cliente` int NOT NULL AUTO_INCREMENT,
  `Saldo` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Limite_Credito` decimal(10,2) NOT NULL DEFAULT '0.00',
  `Descuento` decimal(5,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`Numero_Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `ID_Pedido` int NOT NULL,
  `Numero_Articulo` int NOT NULL,
  `Cantidad` int NOT NULL,
  PRIMARY KEY (`ID_Pedido`,`Numero_Articulo`),
  KEY `fk_detalle_articulo` (`Numero_Articulo`),
  CONSTRAINT `fk_detalle_articulo` FOREIGN KEY (`Numero_Articulo`) REFERENCES `articulo` (`idarticulo`),
  CONSTRAINT `fk_detalle_pedido` FOREIGN KEY (`ID_Pedido`) REFERENCES `pedido` (`ID_Pedido`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `ID_Direccion` int NOT NULL AUTO_INCREMENT,
  `Numero_Cliente` int NOT NULL,
  `Calle` varchar(255) NOT NULL,
  `Colonia` varchar(255) NOT NULL,
  `Estado` varchar(100) NOT NULL,
  `Ciudad` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_Direccion`),
  KEY `fk_direccion_cliente` (`Numero_Cliente`),
  CONSTRAINT `fk_direccion_cliente` FOREIGN KEY (`Numero_Cliente`) REFERENCES `cliente` (`Numero_Cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `ID_Pedido` int NOT NULL AUTO_INCREMENT,
  `Fecha` datetime NOT NULL,
  `Numero_Cliente` int NOT NULL,
  `ID_Direccion_Envio` int NOT NULL,
  PRIMARY KEY (`ID_Pedido`),
  KEY `fk_pedido_cliente` (`Numero_Cliente`),
  KEY `fk_pedido_direccion` (`ID_Direccion_Envio`),
  CONSTRAINT `fk_pedido_cliente` FOREIGN KEY (`Numero_Cliente`) REFERENCES `cliente` (`Numero_Cliente`),
  CONSTRAINT `fk_pedido_direccion` FOREIGN KEY (`ID_Direccion_Envio`) REFERENCES `direccion` (`ID_Direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-10 22:58:16
