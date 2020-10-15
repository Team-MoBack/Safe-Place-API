USE `moBack`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `email` varchar(50) UNIQUE NOT NULL,
    `password` varchar(60) NOT NULL,
    `user_position_id` int,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
-- INSERT INTO `user` 
-- VALUES 
-- (1,'jaegu88@naver.com','1234','kim jaegu',0,0),
-- (2,'mary123@kakao.com','1234','mary kim',36.4344,154.12323),
-- (3,'susan111@gmail.com','1234','susan lee',44.2232,12.32323);