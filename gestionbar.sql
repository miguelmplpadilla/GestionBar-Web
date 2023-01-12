-- --------------------------------------------------------
-- Host:                         sql11.freemysqlhosting.net
-- Versión del servidor:         5.5.62-0ubuntu0.14.04.1 - (Ubuntu)
-- SO del servidor:              debian-linux-gnu
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para sql11475961
CREATE DATABASE IF NOT EXISTS `sql11475961` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sql11475961`;

-- Volcando estructura para tabla sql11475961.bar
CREATE TABLE IF NOT EXISTS `bar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `contrasena` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.bar: ~3 rows (aproximadamente)
DELETE FROM `bar`;
/*!40000 ALTER TABLE `bar` DISABLE KEYS */;
INSERT INTO `bar` (`id`, `nombre`, `contrasena`) VALUES
	(1, 'admin', '753951aA.'),
	(10, 'Juanlillo', 'juan2449'),
	(11, 'Random', 'agosto1971');
/*!40000 ALTER TABLE `bar` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.categorias: ~7 rows (aproximadamente)
DELETE FROM `categorias`;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`id`, `nombre`) VALUES
	(1, 'Carne'),
	(2, 'Bebidas'),
	(3, 'Fruta'),
	(4, 'Verdura'),
	(5, 'Pescado'),
	(6, 'Celulosa'),
	(7, 'Preparados');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.codigostrazabilidad
CREATE TABLE IF NOT EXISTS `codigostrazabilidad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_producto` int(11) NOT NULL,
  `codigoTrazabilidad` varchar(1000) DEFAULT NULL,
  `codigo_trazabilidad` varchar(1000) DEFAULT NULL,
  `fk_bar` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bar` (`fk_bar`),
  KEY `fk_producto` (`fk_producto`),
  CONSTRAINT `fk_bar` FOREIGN KEY (`fk_bar`) REFERENCES `bar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_producto` FOREIGN KEY (`fk_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.codigostrazabilidad: ~9 rows (aproximadamente)
DELETE FROM `codigostrazabilidad`;
/*!40000 ALTER TABLE `codigostrazabilidad` DISABLE KEYS */;
INSERT INTO `codigostrazabilidad` (`id`, `fk_producto`, `codigoTrazabilidad`, `codigo_trazabilidad`, `fk_bar`) VALUES
	(2, 2, NULL, '5449000000996', 1),
	(3, 33, NULL, '367218362178367812', 1),
	(4, 2, NULL, '378127312', 1),
	(7, 47, NULL, '1', 1),
	(8, 47, NULL, '7622210646712', 1),
	(9, 47, NULL, '7622210646712', 1),
	(10, 46, NULL, '20724696', 1),
	(11, 2, NULL, '43243243', 1),
	(12, 2, NULL, '8765757645687567', 1);
/*!40000 ALTER TABLE `codigostrazabilidad` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.comparativaprecio
CREATE TABLE IF NOT EXISTS `comparativaprecio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `precio` float NOT NULL,
  `fecha` date DEFAULT NULL,
  `iva` float NOT NULL DEFAULT '0',
  `fk_bar` int(11) NOT NULL,
  `fk_producto` int(11) NOT NULL,
  `fk_proveedor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comparativaprecio_ibfk_1` (`fk_proveedor`),
  KEY `comparativaprecio_ibfk_2` (`fk_producto`),
  KEY `fk_barprecio` (`fk_bar`),
  CONSTRAINT `comparativaprecio_ibfk_1` FOREIGN KEY (`fk_proveedor`) REFERENCES `proveedores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comparativaprecio_ibfk_2` FOREIGN KEY (`fk_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_barprecio` FOREIGN KEY (`fk_bar`) REFERENCES `bar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.comparativaprecio: ~27 rows (aproximadamente)
DELETE FROM `comparativaprecio`;
/*!40000 ALTER TABLE `comparativaprecio` DISABLE KEYS */;
INSERT INTO `comparativaprecio` (`id`, `precio`, `fecha`, `iva`, `fk_bar`, `fk_producto`, `fk_proveedor`) VALUES
	(17, 3.2, '2022-03-01', 0, 11, 46, 4),
	(18, 3.4, '2022-03-01', 0, 11, 46, 6),
	(19, 5.19, '2022-03-02', 0, 11, 9, 8),
	(20, 1.016, '2022-03-02', 0, 11, 48, 4),
	(21, 1.295, '2022-03-02', 0, 11, 49, 4),
	(22, 1.49, '2022-03-02', 0, 11, 50, 8),
	(23, 1.35, '2022-03-02', 0, 11, 48, 3),
	(24, 3.729, '2022-03-03', 0, 11, 51, 4),
	(25, 1.375, '2022-03-03', 0, 11, 52, 4),
	(26, 3.2, '2022-03-03', 0, 11, 46, 4),
	(27, 3.775, '2022-03-03', 0, 11, 46, 4),
	(28, 3.449, '2022-03-03', 0, 11, 53, 4),
	(29, 0.319, '2022-03-03', 0, 11, 57, 4),
	(30, 1.071, '2022-03-03', 0, 11, 58, 4),
	(31, 2.794, '2022-03-03', 0, 11, 62, 4),
	(32, 3.201, '2022-03-03', 0, 11, 62, 4),
	(33, 4.873, '2022-03-03', 0, 11, 2, 4),
	(34, 1.969, '2022-03-03', 0, 11, 69, 4),
	(35, 9.339, '2022-03-03', 0, 11, 70, 4),
	(36, 0.935, '2022-03-03', 0, 11, 63, 4),
	(37, 0.22, '2022-03-03', 0, 11, 59, 4),
	(38, 1.76, '2022-03-03', 0, 11, 60, 4),
	(39, 6.721, '2022-03-03', 0, 11, 61, 4),
	(41, 4, '2022-03-04', 6, 1, 2, 1),
	(42, 1, '2022-03-04', 21, 1, 2, 2),
	(43, 10, '2022-03-05', 10, 1, 8, 1),
	(44, 7, '2022-03-05', 14, 1, 9, 1);
/*!40000 ALTER TABLE `comparativaprecio` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.datosbar
CREATE TABLE IF NOT EXISTS `datosbar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_bar` int(11) DEFAULT NULL,
  `fotoPerfil` longtext,
  `correoElectronico` longtext,
  `correo_electronico` varchar(255) DEFAULT 'correo@correo.com',
  `foto_perfil` varchar(255) DEFAULT 'https://www.compuserviciosbcs.com/images/items/no-img.jpg',
  PRIMARY KEY (`id`),
  KEY `fkBar` (`fk_bar`),
  CONSTRAINT `fkBar` FOREIGN KEY (`fk_bar`) REFERENCES `bar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.datosbar: ~3 rows (aproximadamente)
DELETE FROM `datosbar`;
/*!40000 ALTER TABLE `datosbar` DISABLE KEYS */;
INSERT INTO `datosbar` (`id`, `fk_bar`, `fotoPerfil`, `correoElectronico`, `correo_electronico`, `foto_perfil`) VALUES
	(5, 1, 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', 'correo@correo.com', 'miguelpadillal@hotmail.es', 'https://i.imgur.com/sq15Yjl.png'),
	(7, 10, NULL, NULL, 'correo@correo.com', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg'),
	(8, 11, NULL, NULL, 'correo@correo.com', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg');
/*!40000 ALTER TABLE `datosbar` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.fk_barproveedor
CREATE TABLE IF NOT EXISTS `fk_barproveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_bar` int(11) NOT NULL,
  `fk_proveedor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_barproveedor_ibfk_1` (`fk_bar`),
  KEY `fk_barproveedor_ibfk_2` (`fk_proveedor`),
  CONSTRAINT `fk_barproveedor_ibfk_1` FOREIGN KEY (`fk_bar`) REFERENCES `bar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_barproveedor_ibfk_2` FOREIGN KEY (`fk_proveedor`) REFERENCES `proveedores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.fk_barproveedor: ~1 rows (aproximadamente)
DELETE FROM `fk_barproveedor`;
/*!40000 ALTER TABLE `fk_barproveedor` DISABLE KEYS */;
INSERT INTO `fk_barproveedor` (`id`, `fk_bar`, `fk_proveedor`) VALUES
	(7, 1, 1);
/*!40000 ALTER TABLE `fk_barproveedor` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.fk_productosproveedores
CREATE TABLE IF NOT EXISTS `fk_productosproveedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `precio` float NOT NULL,
  `iva` float NOT NULL,
  `fk_bar` int(11) NOT NULL DEFAULT '0',
  `fk_producto` int(11) NOT NULL,
  `fk_proveedor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_productosproveedores_ibfk_1` (`fk_producto`),
  KEY `fk_productosproveedores_ibfk_2` (`fk_proveedor`),
  KEY `fk_barproductoproveedor` (`fk_bar`),
  CONSTRAINT `fk_barproductoproveedor` FOREIGN KEY (`fk_bar`) REFERENCES `bar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_productosproveedores_ibfk_1` FOREIGN KEY (`fk_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_productosproveedores_ibfk_2` FOREIGN KEY (`fk_proveedor`) REFERENCES `proveedores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.fk_productosproveedores: ~33 rows (aproximadamente)
DELETE FROM `fk_productosproveedores`;
/*!40000 ALTER TABLE `fk_productosproveedores` DISABLE KEYS */;
INSERT INTO `fk_productosproveedores` (`id`, `precio`, `iva`, `fk_bar`, `fk_producto`, `fk_proveedor`) VALUES
	(17, 10, 0, 11, 2, 1),
	(18, 7, 0, 11, 8, 1),
	(19, 5, 0, 11, 9, 1),
	(20, 5.6, 0, 11, 12, 1),
	(21, 3, 0, 11, 37, 2),
	(22, 2, 0, 11, 39, 2),
	(23, 3, 0, 11, 21, 7),
	(24, 3, 0, 11, 45, 3),
	(25, 3.775, 0, 11, 46, 4),
	(26, 3.4, 0, 11, 46, 6),
	(27, 5.19, 0, 11, 9, 8),
	(28, 1.016, 0, 11, 48, 4),
	(29, 1.295, 0, 11, 49, 4),
	(30, 1.49, 0, 11, 50, 8),
	(31, 1.35, 0, 11, 48, 3),
	(32, 3.729, 0, 11, 51, 4),
	(33, 1.375, 0, 11, 52, 4),
	(34, 3.449, 0, 11, 53, 4),
	(35, 0.319, 0, 11, 57, 4),
	(36, 1.071, 0, 11, 58, 4),
	(38, 3.201, 0, 11, 62, 4),
	(39, 4.873, 0, 11, 2, 4),
	(40, 1.969, 0, 11, 69, 4),
	(41, 9.339, 0, 11, 70, 4),
	(42, 0.935, 0, 11, 63, 4),
	(43, 0.22, 0, 11, 59, 4),
	(44, 1.76, 0, 11, 60, 4),
	(45, 6.721, 0, 11, 61, 4),
	(46, 5, 0, 11, 21, 1),
	(48, 4, 0, 1, 2, 1),
	(49, 1, 0, 1, 2, 2),
	(50, 10, 21, 1, 8, 1),
	(51, 7, 5, 1, 9, 1);
/*!40000 ALTER TABLE `fk_productosproveedores` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.hibernate_sequence: ~1 rows (aproximadamente)
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.mesas
CREATE TABLE IF NOT EXISTS `mesas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ocupada` tinyint(4) NOT NULL,
  `fk_bar` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mesas_ibfk_1` (`fk_bar`),
  CONSTRAINT `mesas_ibfk_1` FOREIGN KEY (`fk_bar`) REFERENCES `bar` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.mesas: ~0 rows (aproximadamente)
DELETE FROM `mesas`;
/*!40000 ALTER TABLE `mesas` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesas` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `fk_categoria` int(11) DEFAULT NULL,
  `ingredientes` mediumtext,
  `alergenos` mediumtext,
  `trazas` text,
  `img` longtext,
  `codigo_trazabilidad` varchar(100) DEFAULT '',
  `codigoTrazabilidad` varchar(100) DEFAULT '',
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productos_ibfk_1` (`fk_categoria`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`fk_categoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.productos: ~41 rows (aproximadamente)
DELETE FROM `productos`;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` (`id`, `nombre`, `fk_categoria`, `ingredientes`, `alergenos`, `trazas`, `img`, `codigo_trazabilidad`, `codigoTrazabilidad`, `precio`) VALUES
	(2, 'Lomo', 1, 'Lomo', '', NULL, 'https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202006/17/00118481300798____15__600x600.jpg', NULL, NULL, NULL),
	(8, 'Ternera', 1, NULL, NULL, NULL, 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', NULL, NULL, NULL),
	(9, 'Pechuga', 1, NULL, NULL, NULL, 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', NULL, NULL, NULL),
	(12, 'Manzana', 3, NULL, NULL, NULL, 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', NULL, NULL, NULL),
	(21, 'Aceite de oliva', 2, NULL, NULL, NULL, 'https://images.openfoodfacts.org/images/products/848/001/200/1165/front_es.17.400.jpg', NULL, NULL, NULL),
	(26, 'Batido de chocolate sin lactosa', 2, 'chocolate', '', '', 'https://images.openfoodfacts.org/images/products/841/012/877/7425/front_es.3.400.jpg', NULL, NULL, NULL),
	(32, 'Zumo de melocoton y uva', 2, '', '', '', 'https://i.imgur.com/eRy0iLv.png', '24002042', '', NULL),
	(33, 'Monster Energy ULTRA FIESTA', 2, 'Cosas malas', '', NULL, 'https://i.imgur.com/GMJ7xqv.png', '5060751213512', '', NULL),
	(37, 'Ritter Sport Joghurt', 1, '', '', '', 'https://i.imgur.com/xeWw0mi.png', '42208563', '', NULL),
	(38, 'Aquarius naranja', 2, 'Agua, azúcar, acidulantes (ácido cítrico, ácido málico), potenciadores del sabor (cloruro sódico, fosfato potásico, fosfato cálcico), corrector de acidez (citrato sódico), antioxidante (ácido ascórbico), estabilizantes (E414, E445), gluconato de zinc, edulcorantes (sucralosa, acesulfamo K), aromas naturales de cítricos, colorante (carotenos).', 'Agua, azúcar, acidulantes (ácido cítrico, ácido málico), potenciadores del sabor (cloruro sódico, fosfato potásico, fosfato cálcico), corrector de acidez (citrato sódico), antioxidante (ácido ascórbico), estabilizantes (E414, E445), gluconato de zinc, edulcorantes (sucralosa, acesulfamo K), aromas naturales de cítricos, colorante (carotenos).', '', 'https://images.openfoodfacts.org/images/products/544/900/003/3819/front_es.39.400.jpg', '5449000033819', '', NULL),
	(39, 'Agua', 2, 'Agua', '', '', 'https://i.imgur.com/kAXDFiq.png', '8437002460310', '', NULL),
	(43, 'Zumo de piña manzana-uva', 1, '', '', NULL, 'https://i.imgur.com/XKRT2FL.png', '8410261602615', '', NULL),
	(45, 'Nata En Esprai Montada Azucarada Asturiana', 2, 'Nata (leche), azúcar (8%), estabilizante (E-407), emulgente (E-472b), gas propelente (E-942), Unos aditivos artificiales que son necesarios para que la nata emulsione y salga ya montada,', 'Nata (leche), azúcar (8%), estabilizante (E-407), emulgente (E-472b), gas propelente (E-942), Unos aditivos artificiales que son necesarios para que la nata emulsione y salga ya montada,', '', 'https://images.openfoodfacts.org/images/products/841/029/712/1104/front_es.14.400.jpg', '8410297121104', '', NULL),
	(46, 'Torremoron', 2, '', '', NULL, 'https://images.openfoodfacts.org/images/products/842/204/011/1102/front_es.3.400.jpg', '8422040111102', '', NULL),
	(47, 'Oreo Original', 6, 'Azücar, harina de TRIGO, grasa de palma, cacao desgrasado en polvo 4,3 %, almidén de TRIGO, jarabe de glucosa y fructosa, gasificantes (carbonato acido de potasio, carbonato écido de amonio, carbonato acido de sodio), sal, emulgente (lecitina de SOJA), (vainillina). PUEDE CONTENER LECHE.', 'Azücar, harina de TRIGO, grasa de palma, cacao desgrasado en polvo 4,3 %, almidén de TRIGO, jarabe de glucosa y fructosa, gasificantes (carbonato acido de potasio, carbonato écido de amonio, carbonato acido de sodio), sal, emulgente (lecitina de SOJA), (vainillina). PUEDE CONTENER LECHE.', '', 'https://images.openfoodfacts.org/images/products/762/221/064/6712/front_es.37.400.jpg', '7622210646712', '', NULL),
	(48, 'Litro amstel', 2, 'Agua, malta de _cebada_, lúpulo, extracto de lúpulo.', 'Agua, malta de cebada, lúpulo, extracto de lúpulo.', NULL, 'https://images.openfoodfacts.org/images/products/841/056/900/5521/front_es.14.400.jpg', '8410569005521', '', NULL),
	(49, 'Servilletas colhogar 90un', 6, '', '', NULL, 'https://i.imgur.com/ChAzPjY.png', '', '', NULL),
	(50, 'Tomate pera', 4, 'Tomate pera', '', '', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', '', '', NULL),
	(51, 'Preparado para rebozados sin gluten', 7, 'Almidón de guisante, fibra y proteína de guisante, sal y aroma.\r\nPuede contener, mostaza sésamo y apio.', '', NULL, 'https://images.openfoodfacts.org/images/products/841/072/144/8005/front_es.4.400.jpg', '8410721448005', '', NULL),
	(52, 'Yolanda ', 7, 'Harina de trigo, harina de arroz, acidulante E-334, gasificante E 500B colorante E 180b', 'Trigo', NULL, 'https://i.imgur.com/K4Ltro6.png', '', '', NULL),
	(53, 'Castillo de Albai 2020', 2, '', '', '', 'https://i.imgur.com/Acjx8Uj.png', '', '', NULL),
	(54, 'Cacahuete ', 7, 'Cacahuete ', '', '', 'https://i.imgur.com/tdEuIVv.png', '', '', NULL),
	(55, 'Papas', 7, 'Patatas , aceite de girasol y sal.', '', '', 'https://images.openfoodfacts.org/images/products/841/412/601/2359/front_es.3.400.jpg', '8414126012359', '', NULL),
	(56, 'Mayonesa', 7, 'Aceite de girasol (65%), agua, yema de _huevo_, vinagre de vino, azúcar, sal, aromas, zumo de limón concentrado, estabilizante (goma xantana), conservador (sorbato potásico), colorante (carotenos),', 'Aceite de girasol (65%), agua, yema de huevo, vinagre de vino, azúcar, sal, aromas, zumo de limón concentrado, estabilizante (goma xantana), conservador (sorbato potásico), colorante (carotenos),', '', 'https://images.openfoodfacts.org/images/products/841/008/631/1501/front_es.24.400.jpg', '8410086311501', '', NULL),
	(57, 'Agua mineral natural', 2, '', '', '', 'https://images.openfoodfacts.org/images/products/843/700/246/0327/front_es.3.400.jpg', '8437002460327', '', NULL),
	(58, 'Mañanas ligeras leche sin lactosa entera', 2, 'Leche parcialmente desnatada (2% MG), lactasa y vitaminas A, E, B9 (ácido fólico) y D,', 'Leche parcialmente desnatada (2% MG), lactasa y vitaminas A, E, B9 (ácido fólico) y D,', '', 'https://images.openfoodfacts.org/images/products/841/170/000/1587/front_es.22.400.jpg', '8411700001587', '', NULL),
	(59, 'Sal fina mesa', 7, '', '', '', 'https://images.openfoodfacts.org/images/products/841/026/510/1169/front_es.13.400.jpg', '8410265101169', '', NULL),
	(60, 'Timonet', 7, '', '', '', 'https://images.openfoodfacts.org/images/products/842/661/401/0013/front_es.3.400.jpg', '8426614010013', '', NULL),
	(61, 'Bacon', 1, '', '', '', 'https://images.openfoodfacts.org/images/products/841/084/305/1053/front_es.3.400.jpg', '8410843051053', '', NULL),
	(62, 'Aceitunas rellenas de anchoa', 7, '', '', '', 'https://images.openfoodfacts.org/images/products/848/002/435/0251/front_es.3.400.jpg', '8480024350251', '', NULL),
	(63, 'Galleta Tostada', 7, '', '', '', 'https://images.openfoodfacts.org/images/products/848/002/475/9115/front_es.3.400.jpg', '8480024759115', '', NULL),
	(64, 'Coca-Cola', 2, 'Agua carbonatada, azúcar, colorante E-150d, acidulante ácido fosfórico y aromas naturales (incluyendo cafeína)', 'Agua carbonatada, azúcar, colorante E-150d, acidulante ácido fosfórico y aromas naturales (incluyendo cafeína)', '', 'https://images.openfoodfacts.org/images/products/544/900/000/0996/front_en.596.400.jpg', '5449000000996', '', NULL),
	(66, 'Coca-Cola Zero', 2, 'agua carbonatada, colorante E-150d , edulcorantes: ciclamato Sódico, acesulfamo K y aspartamo, acidulante acido fosforico, aromas naturales (incluyendo caeha)y corrector de acidez citrato sódico, Contiene una fuente de fenilalanina.', 'agua carbonatada, colorante E-150d , edulcorantes: ciclamato Sódico, acesulfamo K y aspartamo, acidulante acido fosforico, aromas naturales (incluyendo caeha)y corrector de acidez citrato sódico, Contiene una fuente de fenilalanina.', '', 'https://images.openfoodfacts.org/images/products/544/900/013/1805/front_en.474.400.jpg', '5449000131805', '', NULL),
	(67, 'Fanta Citron frappé', 2, 'Agua carbonatada, zumo de limón a partir de concentrado 6%, azúcar, acidulantes (ácido málico, ácido cítrico), edulcorantes (acesulfamo k, aspartamo, neohesperidina DC), antioxidante (ácido ascórbico), corrector de acidez (citrato sódico), conservador (sorbato potásico), estabilizantes (goma arábiga, ésteres glicéridos de colofonia de madera), aromas naturales de limón, aromas naturales, colorante (betacaroteno),', 'Agua carbonatada, zumo de limón a partir de concentrado 6%, azúcar, acidulantes (ácido málico, ácido cítrico), edulcorantes (acesulfamo k, aspartamo, neohesperidina DC), antioxidante (ácido ascórbico), corrector de acidez (citrato sódico), conservador (sorbato potásico), estabilizantes (goma arábiga, ésteres glicéridos de colofonia de madera), aromas naturales de limón, aromas naturales, colorante (betacaroteno),', '', 'https://images.openfoodfacts.org/images/products/544/900/000/6004/front_en.79.400.jpg', '5449000006004', '', NULL),
	(68, 'Orange', 2, 'Agua carbonatada, zumo de naranja a partir de concentrado 8%, azúcar o jarabe de glucosa-fructosa, acidulantes (ácido cítrico, ácido málico), estabilizantes (E414, E444, E445), conservador (E202), edulcorantes (E950, aspartamo), antioxidante (ácido ascórbico), aromas naturales de naranja y otros aromas naturales, colorante (E160a).', 'Agua carbonatada, zumo de naranja a partir de concentrado 8%, azúcar o jarabe de glucosa-fructosa, acidulantes (ácido cítrico, ácido málico), estabilizantes (E414, E444, E445), conservador (E202), edulcorantes (E950, aspartamo), antioxidante (ácido ascórbico), aromas naturales de naranja y otros aromas naturales, colorante (E160a).', '', 'https://images.openfoodfacts.org/images/products/544/900/001/1527/front.33.400.jpg', '5449000011527', '', NULL),
	(69, 'Rebanada normal', 7, '', '', '', 'https://images.openfoodfacts.org/images/products/841/260/045/0161/front_es.5.400.jpg', '8412600450161', '', NULL),
	(70, 'Carrillada de cerdo', 1, '', '', '', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', '', '', NULL),
	(72, 'aaaaaaaaaaaa', 1, '', '', '', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', '', '', NULL),
	(74, 'Manteles blancos ', 6, '', '', '', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', '8424088510619', '', NULL),
	(75, 'Manteles blancos ', 6, '', '', '', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', '8424088510619', '', NULL),
	(76, 'Manteles blancos ', 6, '', '', '', 'https://www.compuserviciosbcs.com/images/items/no-img.jpg', '8424088510619', '', NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.proveedores
CREATE TABLE IF NOT EXISTS `proveedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `numeroTelefono` varchar(50) DEFAULT NULL,
  `numero_telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.proveedores: ~7 rows (aproximadamente)
DELETE FROM `proveedores`;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` (`id`, `nombre`, `numeroTelefono`, `numero_telefono`) VALUES
	(1, 'Mercadona', '62163621621', '62163621621'),
	(2, 'Dia', '6213762136621', '6213762136621'),
	(3, 'MasyMas', '123123', '123123'),
	(4, 'Dialsur', NULL, '672183721'),
	(6, 'Spar', NULL, '123124324'),
	(7, 'aldi', NULL, '32263172351267'),
	(8, 'Lidl', NULL, '6666666666');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;

-- Volcando estructura para tabla sql11475961.reservas
CREATE TABLE IF NOT EXISTS `reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCliente` varchar(50) NOT NULL,
  `numeroTelefono` varchar(50) DEFAULT NULL,
  `cantidadComensales` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `comentarios` varchar(400) DEFAULT NULL,
  `fk_mesa` int(11) NOT NULL,
  `cantidad_comensales` int(11) DEFAULT NULL,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `numero_telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reservas_ibfk_1` (`fk_mesa`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`fk_mesa`) REFERENCES `mesas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla sql11475961.reservas: ~0 rows (aproximadamente)
DELETE FROM `reservas`;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
