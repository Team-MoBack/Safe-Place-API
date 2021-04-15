USE `safe_place`;

DROP TABLE IF EXISTS `place`;
CREATE TABLE `place` (
	`id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `geometry` geometry,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;