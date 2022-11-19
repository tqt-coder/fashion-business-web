DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `products_id` int NOT NULL,
  `ip_add` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `size` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `c_id` varchar(255) NOT NULL,
  PRIMARY KEY (`products_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_title` text NOT NULL,
  `category_desc` text NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Nam','Trang phục mới nhất và tốt nhất cho nam giới'),(2,'Nữ','Trang phục mới nhất và tốt cho nữ'),(3,'Trẻ em','Trang phục trẻ em được nhiều phụ huynh yêu thích'),(4,'Áo khoác','Những mẫu áo khoác mới nhất'),(5,'Áo sơ mi','Những chiếc áo sơ mi đáp công sở đẹp nhất');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) NOT NULL,
  `customer_email` varchar(255) NOT NULL,
  `customer_pass` varchar(200) NOT NULL,
  `customer_address` varchar(400) NOT NULL,
  `customer_contact` text NOT NULL,
  `customer_image` text,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (8,'tuấn tuần','tuan@gmail.com','$2a$10$atG5JQ5ODOR.pUFS/Zab0O.vxnP2gqTmdXdU.cS0SGpvRPTMbbWIa','HCM','0943020243',NULL,'tuan'),(9,'tuấn tuần','tuan1@gmail.com','$2a$10$P8ZrognZW9GPJeV4B8IIOOMFDjgtAK52mHGI2dZSC4EhHI3tGBDWi','HCM','0943020243',NULL,'tuan3'),(10,'tuấn tuần','123@gmail.com','$2a$10$qeH44V6LEIa4Wl/8CX4nr.nuufxvMLe50RtEU1PMI9yFrTS6DVjeW','HCM','0943020243',NULL,'tuan34'),(11,'tuấn tuần','1235@gmail.com','$2a$10$jn6c4rd/uEXdeZaI3.K4muqinMFPGJ9k.VqbXnYI1qVCPORcOpS/W','HCM','0943020243',NULL,'tuan345'),(12,'tuấn tuần','12356@gmail.com','$2a$10$EJwWK7AQMqg8eI/EH2sqUOLdj.ZiDq3BA0gKICCRKx3fFxyYh4gDi','HCM','0943020243',NULL,'tuan3456'),(13,'admin','admin@gmail.com','$2a$10$nCd2Uezefso.z3UWPj9fTeVUxZdL4ol5s3QfCjxu74FFk876NKyIu','Đạt Lạt, Lâm Đồng','0943020243',NULL,'admin'),(31,'Yousaf','yo@gmail.com','123','Karachi','03002291527','2.jpeg',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discount_id` int NOT NULL AUTO_INCREMENT,
  `discount_percent` double DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,10,1),(2,5,1);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (14);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_quantity` int NOT NULL,
  `order_price` int NOT NULL,
  `c_id` int NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories` (
  `p_category_id` int NOT NULL AUTO_INCREMENT,
  `p_category_title` text NOT NULL,
  `p_category_desc` text NOT NULL,
  PRIMARY KEY (`p_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,'Áo khoác','Chất lượng tốt áo khoác tùy chỉnh và mặc thông thường'),(2,'Áo sơ mi','Áo thun được thiết kế đẹp và dễ dàng'),(3,'Quần Jean','Quần bò denim và da chất lượng cao'),(4,'Giày','Chất lượng tốt và giày đế mềm có độ bền tốt'),(5,'Áo Hoody','Những chiếc áo hoodie đầy màu sắc và tùy chỉnh mát mẻ');
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `products_id` int NOT NULL AUTO_INCREMENT,
  `product_category_id` int NOT NULL,
  `category_id` int NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `product_title` text NOT NULL,
  `product_img` text NOT NULL,
  `product_price` double NOT NULL,
  `product_description` text,
  `product_quantity` int NOT NULL,
  `discount_id` int NOT NULL,
  `discount_percent` double NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`products_id`),
  CONSTRAINT `products_chk_1` CHECK ((`product_quantity` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,4,1,'2022-11-19 05:30:05','Men Terry Jogers','product1.jpg',1400,'Comfy Sole Jogers',10,0,0,0),(2,3,2,'2022-11-19 05:30:05','Wild Leg Denim','product2.jpg',1800,'Very Stylish and easy',5,0,0,0),(3,2,2,'2022-11-19 05:30:05','Printed Days Tee','product3.jpg',800,'Very cool and comfy',20,0,0,0),(4,3,1,'2022-11-19 05:30:05','Men Stretch Skinny Fit Denim','product4.jpg',2200,'Stylish and Cool',3,0,0,0),(6,2,3,'2022-11-19 05:30:05','BOYS TANK TOP','product5.jpg',800,'Very stylish',21,0,0,0),(7,2,2,'2022-11-19 05:30:05','Black JUMPSUIT','product6.jpg',2200,'Very comfy and cool',22,0,0,0),(9,1,3,'2022-11-19 05:30:05','Black Jacket','product7.jpg',2100,'Very Warm and Comfortable',24,0,0,0),(10,1,3,'2022-11-19 05:30:05','Red Parachute Jacket','product8.jpg',2300,'Comfortable and Warm',22,0,0,0),(11,2,2,'2022-11-19 05:30:05','Printed White Tee','product9.jpg',750,'Comfortable and Cool',11,0,0,0),(12,1,2,'2022-11-19 05:30:05','Brown Coat Type Jacket','productbig1.jpg',2700,'Comfortable and Warm',5,0,0,0),(13,1,2,'2022-11-19 05:30:05','Pink Fluffy Jacket','productbig2.jpg',3200,'Comfortable and Warm',6,0,0,0),(14,4,2,'2022-11-19 05:30:05','Black High Heels','productbig3.jpg',2300,'Very Stylish and Comfortable',7,0,0,0),(15,1,1,'2022-11-19 05:30:05','Grey Royal Jacket','productbig4.jpg',3500,'Warm Stylish and Comfortable',8,0,0,0),(16,4,2,'2022-06-18 04:15:28','White Shiny Heels','whiteheels.jpg',1900,'Style and Glamour at its best',9,0,0,0),(17,5,1,'2022-06-16 11:56:59','Thrashers Hoodie','hoodie-2.png',1900,'Very comfortable, warm and cool',11,0,0,0),(18,3,2,'2022-06-16 11:57:49','Black Ripped Jeans','jeanss.png',1800,'Very Cool and stylish',13,0,0,0),(19,5,3,'2022-06-16 11:58:49','Colorful Hoodie','hoodie-4.png',2300,'Very cool',12,0,0,0),(20,1,3,'2022-06-16 11:59:35','Black Polo Jacket','boys-Puffer-Coat-With-Detachable-Hood-3.jpg',3100,'Warm and comfy',11,0,0,0),(21,5,3,'2022-06-16 12:03:43','Black Puma Hood','hoodie-3.png',1900,'Warm and Cool',10,0,0,0),(22,5,1,'2022-06-16 12:04:32','Black and White Hood','hoodie-1.png',2300,'Anime Fans Cool Hood',9,0,0,0),(23,2,1,'2022-06-21 01:25:39','B&W Tee','B&W Tee Shirt.jpg',1300,'Very Cool',8,0,0,0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slider`
--

DROP TABLE IF EXISTS `slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slider` (
  `slide_id` int NOT NULL AUTO_INCREMENT,
  `slide_name` varchar(255) NOT NULL,
  `slide_image` text NOT NULL,
  `slide_heading` varchar(100) NOT NULL,
  `slide_text` varchar(100) NOT NULL,
  PRIMARY KEY (`slide_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slider`
--

LOCK TABLES `slider` WRITE;
/*!40000 ALTER TABLE `slider` DISABLE KEYS */;
INSERT INTO `slider` VALUES (1,'Slide 1','slide_1.jpg','Summer Sale','Walk in for the Fashion, Stay in for the Style.'),(2,'Slide 2','slide_2.jpg','Black friday','Simply Eveything You Want.');
/*!40000 ALTER TABLE `slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,13,1),(2,13,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-19 16:32:18
