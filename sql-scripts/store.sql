USE `moBack`;

DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `owner` varchar(50) NOT NULL,
    `category` varchar(50) NOT NULL,
    `longitude` double(13,10),
    `latitude` double(13,10),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;