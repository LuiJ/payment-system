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
    `first_name` varchar(512) DEFAULT NULL,
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
    `expiration_date` date  NOT NULL,
    `account_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS `operation` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `date` datetime  DEFAULT NULL,
    `type` ENUM('PAYMENT', 'TRANSFER', 'RECEIVE_MONEY', 'CARD_BLOCKING', 'CARD_ACTIVATING', 'ACCOUNT_CLOSING'),
    `amount` decimal(15,2) DEFAULT NULL, 
    `user_id` int(10) DEFAULT NULL,
    `account_id` int(10) DEFAULT NULL,
    `card_id` int(10) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    KEY `account_id` (`account_id`),
    KEY `card_id` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;


ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `operation`
  ADD CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `operation_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `operation_ibfk_3` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


ALTER TABLE `card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;


INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');


INSERT INTO `admin` (`id`, `login`, `password`, `role_id`, `first_name`) VALUES
(1, 'admin1', 'pwd1', 1, 'Алексей'),
(2, 'admin2', 'pwd2', 1, 'Филипп');


INSERT INTO `user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`) VALUES
(1, 'user1', 'pwd1', 2, 'Иван', 'Сидоров'),
(2, 'user2', 'pwd2', 2, 'Семен', 'Смирнов'),
(3, 'user3', 'pwd3', 2, 'Виктор', 'Иванов');


INSERT INTO `account` (`id`, `number`, `status`, `amount`, `user_id`) VALUES
(1, 9999888877776666, 'ACTIVE', 5000, 1),
(2, 8888777766665555, 'ACTIVE', 5000, 1),
(3, 7777666655554444, 'ACTIVE', 5000, 2),
(4, 6666555544443333, 'ACTIVE', 5000, 2),
(5, 5555444433332222, 'ACTIVE', 5000, 3);


INSERT INTO `card` (`id`, `number`, `pin_code`, `salt`, `status`, `expiration_date`, `account_id`) VALUES
(1, 1111222233334444, '2e85f2853403ff47a63f3525034b848c', 'f9e04a82f7e1b7ef5eca', 'ACTIVE', '2018-04-07', 1),
(2, 2222333344445555, 'dc1513be2d528fafbb5e4ffe6f255795', '7e4a80b9f6d0baad8991', 'ACTIVE', '2018-04-07', 2),
(3, 3333444455556666, '4c93ae6f3b5785e75766718909c9c13d', '7750f3c4ab38895f3649', 'ACTIVE', '2018-04-07', 3),
(4, 4444555566667777, '3957a57092de2a6ba493ad3e6a59c35f', '26ecb15588d4a445b12c', 'ACTIVE', '2018-04-07', 3),
(5, 5555666677778888, 'a73a5617bd8afcfe22683a8c9988c6e4', 'bac3d0798fa05c3af10f', 'ACTIVE', '2018-04-07', 4),
(6, 6666777788889999, '4c3a9503975ca8b04827524959553837', 'd720aa09c95ff7dd7ccf', 'ACTIVE', '2018-04-07', 5);


INSERT INTO `operation` (`id`, `date`, `type`, `amount`, `user_id`, `account_id`, `card_id`) VALUES
(1, '2016-04-07 06:45:00', 'CARD_BLOCKING', NULL, 1, NULL, NULL),
(2, '2016-04-07 06:45:00', 'ACCOUNT_CLOSING', NULL, 1, NULL, NULL); 