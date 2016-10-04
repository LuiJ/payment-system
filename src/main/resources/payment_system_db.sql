create database payment_system;
use payment_system;


CREATE TABLE IF NOT EXISTS `role` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(512) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `admin` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) NOT NULL UNIQUE,
    `password` varchar(64) NOT NULL,
    `role_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `user` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `login` varchar(512) NOT NULL UNIQUE,
    `password` varchar(64) NOT NULL,
    `role_id` int(10) DEFAULT NULL,
    `first_name` varchar(512) DEFAULT NULL,
    `last_name` varchar(512) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `role_id` (`role_id`)
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


ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `operation`
  ADD CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');


INSERT INTO `admin` (`id`, `login`, `password`, `role_id`) VALUES
(1, 'admin1', 'pwd1', 1),
(2, 'admin2', 'pwd2', 1);


INSERT INTO `user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`) VALUES
(1, 'user1', 'pwd1', 2, 'Ivan', 'Sidorov'),
(2, 'user2', 'pwd2', 2, 'Semen', 'Smirnov'),
(3, 'user3', 'pwd3', 2, 'Viktor', 'Ivanov');


INSERT INTO `account` (`id`, `number`, `status`, `amount`, `user_id`) VALUES
(1, 9999888877776666, 'ACTIVE', 1234.56, 1),
(2, 8888777766665555, 'ACTIVE', 2345.67, 1),
(3, 7777666655554444, 'ACTIVE', 3456.78, 2),
(4, 6666555544443333, 'ACTIVE', 4567.89, 2),
(5, 5555444433332222, 'ACTIVE', 5678.90, 3);


INSERT INTO `card` (`id`, `number`, `pin_code`, `salt`, `status`, `account_id`) VALUES
(1, 1111222233334444, '2e85f2853403ff47a63f3525034b848c', 'f9e04a82f7e1b7ef5eca', 'ACTIVE', 1),
(2, 2222333344445555, 'dc1513be2d528fafbb5e4ffe6f255795', '7e4a80b9f6d0baad8991', 'ACTIVE', 2),
(3, 3333444455556666, '4c93ae6f3b5785e75766718909c9c13d', '7750f3c4ab38895f3649', 'ACTIVE', 3),
(4, 4444555566667777, '3957a57092de2a6ba493ad3e6a59c35f', '26ecb15588d4a445b12c', 'ACTIVE', 3),
(5, 5555666677778888, 'a73a5617bd8afcfe22683a8c9988c6e4', 'bac3d0798fa05c3af10f', 'ACTIVE', 4),
(6, 6666777788889999, '4c3a9503975ca8b04827524959553837', 'd720aa09c95ff7dd7ccf', 'ACTIVE', 5);


INSERT INTO `operation` (`id`, `date`, `type`, `amount`, `description`, `user_id`) VALUES
(1, '2016-04-07 12:34:17', 'PAYMENT', 1234.56, 'payment 1', 1),
(2, '2016-04-07 12:34:17', 'CARD_BLOCKING', 2345.67, 'payment 2', 2),
(3, '2016-04-07 12:34:17', 'ACCOUNT_CLOSING', 3456.78, 'payment 3', 1); 