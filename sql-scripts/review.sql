DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
	`id` int NOT NULL AUTO_INCREMENT,
    `comment` varchar(50) NOT NULL,
    `rating` int,
    `account_id` int,
    `place_id` int,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`account_id`) REFERENCES `account`(id),
  FOREIGN KEY (`place_id`) REFERENCES `place`(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;