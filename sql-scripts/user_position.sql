USE `moback`;
DROP TABLE IF EXISTS `USER_POSITION`;
CREATE TABLE `USER_POSITION` (
	`id` int NOT NULL,
    `longitude` double DEFAULT NULL,
    `latitude` double DEFAULT NULL,
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4


