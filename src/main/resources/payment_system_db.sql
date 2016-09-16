create database payment_system;
use payment_system;


CREATE TABLE IF NOT EXISTS `admin` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) NOT NULL UNIQUE,
    `password` varchar(64) NOT NULL,
    `salt` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `user` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) NOT NULL UNIQUE,
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


INSERT INTO `admin` (`id`, `login`, `password`, `salt`) VALUES
(1, 'admin1', 'password1', 'salt1'),
(2, 'admin2', 'password2', 'salt2');


INSERT INTO `user` (`id`, `login`, `password`, `salt`, `first_name`, `last_name`) VALUES
(1, 'user1', 'password1', 'salt1', 'FN1', 'LN1'),
(2, 'user2', 'password2', 'salt2', 'FN2', 'LN2');


INSERT INTO `account` (`id`, `number`, `status`, `amount`, `user_id`) VALUES
(1, 1111222233334444, 'ACTIVE', 1234.56, 1),
(2, 2222333344445555, 'CLOSED', 2345.67, 2),
(3, 3333444455556666, 'ACTIVE', 3456.78, 1);


INSERT INTO `card` (`id`, `number`, `pin_code`, `salt`, `status`, `account_id`) VALUES
(1, 9999888877776666, 'PIN1', 'salt1', 'ACTIVE', 1),
(2, 8888777766665555, 'PIN2', 'salt2', 'BLOCKED', 1),
(3, 7777666655554444, 'PIN3', 'salt3', 'BLOCKED', 2);


INSERT INTO `operation` (`id`, `date`, `type`, `amount`, `description`, `user_id`) VALUES
(1, '2016-04-07 12:34:17', 'PAYMENT', 1234.56, 'payment 1', 1),
(2, '2016-04-07 12:34:17', 'CARD_BLOCKING', 2345.67, 'payment 2', 2),
(3, '2016-04-07 12:34:17', 'ACCOUNT_CLOSING', 3456.78, 'payment 3', 1); 