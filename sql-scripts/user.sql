USE `moBack`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `email` varchar(50) NOT NULL,
    `password` char(68) NOT NULL,
    `name` varchar(50) NOT NULL,
    `longitude` double(13,10),
    `latitude` double(13,10),
    
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `user` 
VALUES 
(1,'jaegu88@naver.com','1234','kim jaegu',0,0),
(2,'mary123@kakao.com','1234','mary kim',36.4344,154.12323),
(3,'susan111@gmail.com','1234','susan lee',44.2232,12.32323);