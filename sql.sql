create database clothing_store;
use clothing_store;

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
                        `cart_id` int NOT NULL AUTO_INCREMENT,
                        `products_id` int NOT NULL,
                        `money` double NOT NULL,
                        `quantity` int NOT NULL,
                        `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `customer_id` int NOT NULL,
                        `status` int DEFAULT NULL,
                        PRIMARY KEY (`cart_id`)
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
INSERT INTO `category` VALUES (1,'Nam','Trang phục mới nhất và tốt nhất cho nam giới'),(2,'Nữ','Trang phục mới nhất và tốt cho nữ'),(3,'Trẻ em','Trang phục trẻ em được nhiều phụ huynh yêu thích');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,'Áo khoác','Chất lượng tốt áo khoác tùy chỉnh và mặc thông thường'),(2,'Áo sơ mi','Áo thun được thiết kế đẹp và dễ dàng'),(3,'Quần Jean','Quần bò denim và da chất lượng cao'),(4,'Giày','Chất lượng tốt và giày đế mềm có độ bền tốt'),(5,'Áo Hoody','Những chiếc áo hoodie đầy màu sắc và tùy chỉnh mát mẻ'),(6,'Nón','Những người bạn không thể thiếu trong mùa hè oi bức');
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
                            PRIMARY KEY (`products_id`),
                            CONSTRAINT `products_chk_1` CHECK ((`product_quantity` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,4,1,'2022-11-19 03:33:52','Men Terry Jogers','product1.jpg',145500,'Mẫu thời trang Thu - Đông đẹp nhất năm',10),
                              (2,3,2,'2022-11-19 03:33:52','Wild Leg Denim','product2.jpg',177800,'Rất phong cách và dễ dàng',5),
                              (3,2,2,'2022-11-19 03:33:51','Printed Days Tee','product3.jpg',80770,'Rất mát mẻ và thoải mái',20),
                              (4,3,1,'2022-11-19 06:09:25','Men Stretch Skinny','product4.jpg',227700,'Phong cách và Mát mẻ',3),
                              (6,2,3,'2022-11-19 03:33:51','BOYS TANK TOP','shirt_caro_msh1009.jpg',888976,'Rất hợp thời trang',21),
                              (7,2,2,'2022-11-19 03:33:52','Black JUMPSUIT','product6.jpg',299909,'Rất thoải mái và mát mẻ',22),
                              (9,1,3,'2022-11-19 04:11:49','Black Jacket','white_jacket.jpg',321100,'Rất ấm áp và thoải mái',24),
                              (10,1,3,'2022-11-19 04:11:49','Red Parachute Jacket','yellow_jacket.jpg',23000,'Rất thoải mái và ấm áp',22),
                              (11,2,2,'2022-11-19 03:33:51','Printed White Tee','short_shirt_msh1010.jpg',75000,'Rất thoải mái và ấm áp',11),
                              (12,1,2,'2022-11-19 03:33:51','Brown Coat Type Jacket','productbig1.jpg',270000,'Thoải mái và ấm áp',5),
                              (13,1,2,'2022-11-19 03:33:51','Pink Fluffy Jacket','productbig2.jpg',320000,'Rất thoải mái và ấm áp',6),
                              (14,4,2,'2022-11-19 03:33:51','Black High Heels','productbig3.jpg',230000,'Rất phong cách và thoải mái',7),
                              (15,1,1,'2022-11-19 03:33:51','Grey Royal Jacket','productbig4.jpg',350000,'Phong cách ấm áp và thoải mái',8),
                              (16,4,2,'2022-11-19 03:33:51','White Shiny Heels','short_shirt_msh1010.jpg',190000,'Phong cách và sự quyến rũ ở mức tốt nhất',9),
                              (17,5,1,'2022-11-19 03:33:52','Thrashers Hoodie','hoodie-2.png',190000,'Rất thoải mái, ấm áp và mát mẻ',11),
                              (18,3,2,'2022-11-19 03:33:52','Black Ripped Jeans','jeanss.png',180000,'Rất tuyệt và phong cách',13),
                              (19,5,3,'2022-11-19 03:33:51','Colorful Hoodie','hoodie-4.png',230000,'Rất tuyệt',12),
                              (20,1,3,'2022-11-19 04:11:49','Black Polo Jacket','blue_jacket.jpg',310000,'Ấm áp và thoải mái',11),
                              (21,5,3,'2022-11-19 03:33:52','Black Puma Hood','hoodie-3.png',788900,'Ấm áp và mát mẻ',10),
                              (22,5,1,'2022-11-19 03:33:51','Black and White Hood','hoodie-1.png',210300,'Những người hâm mộ anime Cool Hood',9),
                              (23,2,1,'2022-11-19 03:33:51','B&W Tee','women_shirt_grande.jpg',13000,'Rất tuyệt vời',8),
                              (24,6,1,'2022-11-19 03:47:56','UV fabric hat MHA','hat_wonman.jpg',169000,'vải  chống nắng uv 98%, vải tricot UV 60% Cotton + 40% Poly. Sản phẩm có thể đội đi chơi, du lịch, trải nghiệm,.....',5),
                              (25,6,2,'2022-11-19 03:34:30','Hat 3D MHA 1002','hat_3d.jpg',98000,'Nón vải tiện lợi và thời trang',2),
                              (26,6,3,'2022-11-19 03:36:07','MINIONS Embroidered Hat','hat_fashion.jpg',190900,'Sản phẩm có thể đội đi chơi, du lịch, trải nghiệm,.....',1),
                              (27,6,1,'2022-11-19 03:38:26','Khaki Hat 6 Points Top Silicon Logo','hat_logo_silicon.jpg',123000,'Sản phẩm form nón năng động, trẻ trung phù hợp với nhiều outfit.',2);
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

