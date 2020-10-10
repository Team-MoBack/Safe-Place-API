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

INSERT INTO `store` 
VALUES
(1,'Two Some Place','박준현','카페',36.333,64.3434),
(2,'Start Bucks Dongsungro','홍길동','카페',36.4344,154.12323),
(3,'Angelinus','김자이구','카페',44.2232,12.32323);