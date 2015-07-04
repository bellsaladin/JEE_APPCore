-- -----------------------------------------------------------------------------------------
-- ---------------------------------- GESTION DES MENUS ------------------------------------
-- -----------------------------------------------------------------------------------------

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES ('E003000000001','module.scolarite','a','darkorchid','fa fa-folder-open-o fa-2x','ROLE_USER',1,1,'E003','E003000000001',0,1,0,'2014-01-08 01:14:18'),('E003000000002','module.paiement','a','cornflowerblue','fa fa-sign-in fa-2x','ROLE_USER',1,2,'E003','E003000000001',0,1,0,'2014-01-07 19:03:51'),('E003000000003','module.emploiTemps','a','#D2691E','fa fa-calendar fa-2x','ROLE_USER',1,3,'E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000004','module.vieScolaire','a','cadetblue','fa fa-briefcase fa-2x','ROLE_USER',1,4,'E003','E003000000001',0,0,0,'2011-01-01 01:01:01'),('E003000000005','module.transport','a','violet','fa fa-truck fa-2x','ROLE_USER',1,5,'E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000006','module.comptabilite','a','yellowgreen','fa fa-compress fa-2x','ROLE_USER',1,6,'E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000007','module.administration','a','#FF6347','fa fa-building-o fa-2x','ROLE_USER',1,7,'E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000008','module.sms','a','steelblue','fa fa-tablet fa-2x','ROLE_USER',1,8,'E003','E003000000001',0,0,0,'2014-01-04 01:01:01');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('E003000000001','menu.preinscription','/views/test.xhtml','fa fa-pencil-square-o fa-lg','ROLE_USER','MENU',1,1,NULL,'E003000000001','E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000002','menu.testAdmission','/views/home.xhtml','fa fa-pencil-square-o fa-lg','ROLE_USER','MENU',1,2,NULL,'E003000000001','E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000003','menu.ficheEleve','/views/home.xhtml','fa fa-pencil-square-o fa-lg','ROLE_ADMIN','MENU',1,3,NULL,'E003000000001','E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000004','menu.ficheFamille','/views/home.xhtml','fa fa-pencil-square-o fa-lg','ROLE_ADMIN','MENU',1,4,NULL,'E003000000001','E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000005','menu.listes','/views/home.xhtml','fa fa-pencil-square-o fa-lg','ROLE_USER','RAPPORT',1,5,NULL,'E003000000001','E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000006','menu.listePreinscription','/views/home.xhtml','fa fa-pencil-square-o fa-lg','ROLE_USER','RAPPORT',1,6,'E003000000005','E003000000001','E003','E003000000001',0,0,0,'2014-01-04 01:01:01'),('E003000000007','menu.listeEleves','/views/home.xhtml','fa fa-pencil-square-o fa-lg','ROLE_USER','RAPPORT',1,7,'E003000000005','E003000000001','E003','E003000000001',0,0,0,'2014-01-04 01:01:01');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;