--create database sec;
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(20) NOT NULL,
  `familyname` VARCHAR(20) NOT NULL,
  `alias` VARCHAR(20) NULL,
  `gender` CHAR NOT NULL DEFAULT 0,
  `phone` INT NULL,
  `email` VARCHAR(45) NULL,
  `birthyear` INT NOT NULL,
  `birthmonth` INT NOT NULL,
  `birthdate` INT NOT NULL,
  `birthsecret` CHAR NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);

CREATE TABLE `email` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);

CREATE TABLE `password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  `hh` char(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
);

CREATE TABLE `sectype` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);
