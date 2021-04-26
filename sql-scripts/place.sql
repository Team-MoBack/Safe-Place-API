USE `safe_place`;

DROP TABLE IF EXISTS `place`;
CREATE TABLE `place` (
	`id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `location` point NOT NULL,
    `number_of_people` int,
  PRIMARY KEY (`id`),
  spatial index (location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;