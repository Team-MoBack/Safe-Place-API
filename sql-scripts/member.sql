USE `moBack_security`;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `email` char(68) NOT NULL,
  `password` char(68) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
