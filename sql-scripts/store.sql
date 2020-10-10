USE `moBack`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `longitude` double(13,10),
    `latitude` double(13,10),
    
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` 
VALUES 
(1,'Two Some Place',36.333,64.3434),
(2,'Start Bucks Dongsungro',36.4344,154.12323),
(3,'Angelinus',44.2232,12.32323);