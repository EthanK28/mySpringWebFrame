CREATE DATABASE IF NOT EXISTS `springbook`;
USE `springbook`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(10) primary key,
  `name` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `level` tinyint NOT NULL,
  `login` int NOT NULL,
  `recommend` int NOT NULL,
  PRIMARY KEY (id),  
) DEFAULT CHARSET=utf8;

INSERT INTO `users` VALUES ('admin','홍길동','admin','admin@ksug.org',1,0,0),('bumjin','박범진','p1','user1@ksug.org',1,49,0),('green','오민규','p5','user5@ksug.org',3,100,2147483647);

CREATE TABLE user_roles (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uni_username_role (`role`, `email`),
  KEY fk_username_idx (`email`),
  CONSTRAINT fk_username FOREIGN KEY (`email`) REFERENCES users (`email`));
  
  
