USE `moback`;
DROP TABLE IF EXISTS `user_position`;
CREATE TABLE `user_position` (
	`id` int NOT NULL,
    `longitude` double DEFAULT NULL,
    `latitude` double DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4


