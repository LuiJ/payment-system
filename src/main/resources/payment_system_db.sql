create database payment_system;
use payment_system;


CREATE TABLE IF NOT EXISTS `admin` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) DEFAULT NULL,
    `password` varchar(64) NOT NULL,
    `salt` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `user` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) DEFAULT NULL,
    `password` varchar(64) NOT NULL,
    `salt` varchar(20) NOT NULL,
    `first_name` varchar(512) DEFAULT NULL,
    `last_name` varchar(512) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `account` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `number` bigint(16) NOT NULL UNIQUE,
    `status` ENUM('ACTIVE', 'CLOSED'),
    `amount` decimal(15,2) DEFAULT NULL,    
    `user_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `card` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `number` bigint(16) NOT NULL UNIQUE,
    `pin_code` varchar(64) NOT NULL,
    `salt` varchar(20) NOT NULL,
    `status` ENUM('ACTIVE', 'BLOCKED'),
    `account_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `operation` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `date` datetime  DEFAULT NULL,
    `type` ENUM('PAYMENT', 'CARD_BLOCKING', 'ACCOUNT_CLOSING'),
    `amount` decimal(15,2) DEFAULT NULL,
    `description` varchar(512) DEFAULT NULL,   
    `user_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `operation`
  ADD CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;