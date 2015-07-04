-------------------------------------------------------------------------------------------
------------------------------------ GESTION DES MENUS ------------------------------------
-------------------------------------------------------------------------------------------

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `id` varchar(15) NOT NULL,
  `libelleKey` varchar(50) NOT NULL,
  `lien` varchar(100) NOT NULL,
  `color` varchar(30) NOT NULL,
  `iconClass` varchar(40) NOT NULL,
  `role` varchar(20) NOT NULL,
  `actif` int(1) NOT NULL,
  `ordre` int(4) NOT NULL,
  `ecole_id` varchar(15) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `flagInsert` int(1) NOT NULL DEFAULT '0',
  `flagUpdate` int(1) NOT NULL DEFAULT '0',
  `flagDelete` int(1) NOT NULL DEFAULT '0',
  `lastUpdate` datetime NOT NULL DEFAULT '2011-01-01 01:01:01',
  PRIMARY KEY (`id`),
  KEY `fk_module_user_idx` (`user_id`),
  KEY `fk_module_ecole_idx` (`ecole_id`),
  CONSTRAINT `fk_module_ecole` FOREIGN KEY (`ecole_id`) REFERENCES `ecole` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_module_user` FOREIGN KEY (`user_id`) REFERENCES `usersnawat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` varchar(15) NOT NULL,
  `libelleKey` varchar(45) NOT NULL,
  `lien` varchar(100) NOT NULL,
  `iconClass` varchar(40) NOT NULL,
  `role` varchar(20) NOT NULL,
  `menuType` varchar(20) NOT NULL,
  `actif` int(1) NOT NULL,
  `ordre` int(4) NOT NULL,
  `parentMenu_id` varchar(45) DEFAULT NULL,
  `module_id` varchar(15) NOT NULL,
  `ecole_id` varchar(15) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `flagInsert` int(1) NOT NULL DEFAULT '0',
  `flagUpdate` int(1) NOT NULL DEFAULT '0',
  `flagDelete` int(1) NOT NULL DEFAULT '0',
  `lastUpdate` datetime NOT NULL DEFAULT '2011-01-01 01:01:01',
  PRIMARY KEY (`id`),
  KEY `fk_menu_menu_idx` (`parentMenu_id`),
  KEY `fk_menu_module_idx` (`module_id`),
  KEY `fk_menu_usersnawat_idx` (`user_id`),
  KEY `fk_menu_ecole_idx` (`ecole_id`),
  CONSTRAINT `fk_menu_menu` FOREIGN KEY (`parentMenu_id`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu_ecole` FOREIGN KEY (`ecole_id`) REFERENCES `ecole` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu_module` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu_usersnawat` FOREIGN KEY (`user_id`) REFERENCES `usersnawat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

