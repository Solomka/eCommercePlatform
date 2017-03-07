-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Мар 07 2017 г., 01:56
-- Версия сервера: 5.1.41
-- Версия PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `ecommerceF`
--

-- --------------------------------------------------------

--
-- Структура таблицы `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `stripeAccId` varchar(128) NOT NULL,
  `seller_id` char(36) NOT NULL,
  PRIMARY KEY (`stripeAccId`),
  KEY `seller_id` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `account`
--


-- --------------------------------------------------------

--
-- Структура таблицы `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` char(36) NOT NULL,
  `country` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `indexx` varchar(8) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `first_name` varchar(35) NOT NULL,
  `last_name` varchar(35) NOT NULL,
  `customer_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `address`
--

INSERT INTO `address` (`id`, `country`, `state`, `region`, `city`, `street`, `indexx`, `phone`, `first_name`, `last_name`, `customer_id`) VALUES
('08a6c002-b5c4-4c12-b0d3-6ef17b355833', 'Ukraine', NULL, NULL, 'Kiev', 'Skovorody 12 str.', '12345', '+380968971663', 'Sasha', 'Chaban', '1c9a0551-a537-44af-be6e-3b775f44b162'),
('0937f0d9-40e3-434c-a959-dce30d5121f5', 'Ukraine', NULL, NULL, 'Kiev', 'Skovorody 12 str.', '12345', '+380968971663', 'Sasha', 'Chaban', '1c9a0551-a537-44af-be6e-3b775f44b162'),
('0b8722d9-b897-4967-9c39-9f1036ac675d', 'Ukraine', NULL, NULL, 'Kiev', 'Skovorody 12 str.', '12345', '+380968971663', 'Sasha', 'Chaban', '1c9a0551-a537-44af-be6e-3b775f44b162'),
('1ef0b12a-d5c7-4fd0-a569-c11562b474f8', 'Ukraine', NULL, NULL, 'Kiev', 'Skovorody 12 str.', '12345', '+380968971663', 'Sasha', 'Chaban', '1c9a0551-a537-44af-be6e-3b775f44b162'),
('6c1f6299-116d-42eb-8bb0-6799c9d61688', 'Ukraine', NULL, NULL, 'Kiev', 'Skovorody 12 str.', '12345', '+380968971663', 'Sasha', 'Chaban', '1c9a0551-a537-44af-be6e-3b775f44b162'),
('fde5bada-41df-4355-8cdf-556274c0d9b7', 'Ukraine', NULL, NULL, 'Kiev', 'Skovorody 12 str.', '12345', '+380968971663', 'Sasha', 'Chaban', '1c9a0551-a537-44af-be6e-3b775f44b162');

-- --------------------------------------------------------

--
-- Структура таблицы `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` char(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
('97b87e56-b4f8-11e6-af21-db5929c35768', 'notebooks & accessories');

-- --------------------------------------------------------

--
-- Структура таблицы `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `id` char(36) NOT NULL,
  `first_name` varchar(35) NOT NULL,
  `last_name` varchar(35) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `login` varchar(15) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `customer`
--

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `email`, `phone`, `login`, `password`) VALUES
('1c9a0551-a537-44af-be6e-3b775f44b162', 'Larisa', 'Yaremko', 'bla@gmail.com', '+380986746583', 'solomka123', 'password123'),
('485e5f20-5d5a-4489-98ae-de4f3f9703a2', 'Larisa', 'Yaremko', 'bla@gmail.com', '+380986746583', 'solomka123', 'password123'),
('540e12d4-8ed8-4953-bca9-cb8f4c3b4c94', 'Larisa', 'Yaremko', 'bla@gmail.com', '+380986746583', 'solomka123', 'password123'),
('9f4343b9-cba2-49b0-9017-d18f2883486e', 'Larisa', 'Yaremko', 'bla@gmail.com', '+380986746583', 'solomka123', 'password123'),
('b0dcb481-16bb-4e4b-9700-78d182e827ac', 'Sona', 'Yaremko', 'bla@gmail.com', '+380986746583', 'solomka123', 'password123'),
('dc86581b-6280-4a91-9f65-7549c6ebf502', 'Larisa', 'Yaremko', 'bla@gmail.com', '+380986746583', 'solomka123', 'password123');

-- --------------------------------------------------------

--
-- Структура таблицы `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `id` char(36) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_quantity` int(11) NOT NULL,
  `total_sum` decimal(13,2) NOT NULL,
  `currency` enum('UAH','EU','USD') NOT NULL DEFAULT 'USD',
  `status` enum('PENDING','PAID','NOT_PAID') NOT NULL DEFAULT 'PENDING',
  `order_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `invoice`
--


-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `id` char(36) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fulfilment_date` timestamp NULL DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `customer_id` char(36) NOT NULL,
  `shipment_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `shipment_id` (`shipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`id`, `creation_date`, `fulfilment_date`, `status`, `customer_id`, `shipment_id`) VALUES
('071782bb-7a17-4105-b3a4-114354f77bd5', '2017-01-29 20:47:00', NULL, 'CREATED', '1c9a0551-a537-44af-be6e-3b775f44b162', '87f180df-720e-4908-a20e-1bfa49c747eb'),
('1efc4ceb-3dba-4f04-a468-32dc1cc118ba', '2016-12-01 20:10:39', NULL, 'CREATED', '1c9a0551-a537-44af-be6e-3b775f44b162', '8020dc3a-86be-4176-b46d-d0244786fb81'),
('cd54d3e4-f47f-42cc-ac78-05d260f63f0c', '2016-12-01 01:19:02', NULL, 'CREATED', '1c9a0551-a537-44af-be6e-3b775f44b162', 'c8c3e9ea-dca3-4a6a-9cca-89b3604847c1'),
('e767b555-64a4-446b-ba32-16a2110d967b', '2016-12-02 01:05:36', NULL, 'CREATED', '1c9a0551-a537-44af-be6e-3b775f44b162', '2922a530-3158-4d27-a651-bdea1cb1efc3'),
('f96236a0-6593-44ae-913c-05dd7fa93f03', '2017-01-30 02:34:01', NULL, 'CREATED', '1c9a0551-a537-44af-be6e-3b775f44b162', '86738bb1-cd86-4113-bbfc-73c395f3a0a2');

-- --------------------------------------------------------

--
-- Структура таблицы `order_item`
--

CREATE TABLE IF NOT EXISTS `order_item` (
  `order_id` char(36) NOT NULL,
  `product_id` char(36) NOT NULL,
  `total_quantity` int(11) NOT NULL,
  `total_sum` decimal(13,2) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `order_item`
--

INSERT INTO `order_item` (`order_id`, `product_id`, `total_quantity`, `total_sum`) VALUES
('071782bb-7a17-4105-b3a4-114354f77bd5', '2cb84cf7-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('071782bb-7a17-4105-b3a4-114354f77bd5', '2cb85046-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('071782bb-7a17-4105-b3a4-114354f77bd5', 'e995c3bc-b4f8-11e6-af21-db5929c35768', 1, '10.00'),
('1efc4ceb-3dba-4f04-a468-32dc1cc118ba', '2cb84cf7-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('1efc4ceb-3dba-4f04-a468-32dc1cc118ba', '2cb85046-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('1efc4ceb-3dba-4f04-a468-32dc1cc118ba', 'e995c3bc-b4f8-11e6-af21-db5929c35768', 1, '10.00'),
('cd54d3e4-f47f-42cc-ac78-05d260f63f0c', '2cb84cf7-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('cd54d3e4-f47f-42cc-ac78-05d260f63f0c', '2cb85046-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('cd54d3e4-f47f-42cc-ac78-05d260f63f0c', 'e995c3bc-b4f8-11e6-af21-db5929c35768', 1, '10.00'),
('e767b555-64a4-446b-ba32-16a2110d967b', '2cb84cf7-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('e767b555-64a4-446b-ba32-16a2110d967b', '2cb85046-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('e767b555-64a4-446b-ba32-16a2110d967b', 'e995c3bc-b4f8-11e6-af21-db5929c35768', 1, '10.00'),
('f96236a0-6593-44ae-913c-05dd7fa93f03', '2cb84cf7-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('f96236a0-6593-44ae-913c-05dd7fa93f03', '2cb85046-b4f9-11e6-af21-db5929c35768', 1, '10.00'),
('f96236a0-6593-44ae-913c-05dd7fa93f03', 'e995c3bc-b4f8-11e6-af21-db5929c35768', 1, '10.00');

-- --------------------------------------------------------

--
-- Структура таблицы `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `id` char(36) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(50) NOT NULL,
  `invoice_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `invoice_id` (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `payment`
--


-- --------------------------------------------------------

--
-- Структура таблицы `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` char(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` decimal(13,2) NOT NULL,
  `currency` enum('UAH','EU','USD') NOT NULL DEFAULT 'USD',
  `quantity` int(11) NOT NULL DEFAULT '0',
  `description` varchar(512) NOT NULL,
  `seller_id` char(36) NOT NULL,
  `type_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `seller_id` (`seller_id`),
  KEY `type_id` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `currency`, `quantity`, `description`, `seller_id`, `type_id`) VALUES
('2cb84cf7-b4f9-11e6-af21-db5929c35768', 'thinkpad', '200.00', 'USD', 2, 'ThinkPad Edge', '393fdccd-b4f8-11e6-af21-db5929c35768', 'aeb0b0f3-b4f8-11e6-af21-db5929c35768'),
('2cb85046-b4f9-11e6-af21-db5929c35768', 'netbook', '120.00', 'USD', 2, 'netbook lenovo', '393fdccd-b4f8-11e6-af21-db5929c35768', 'aeb0b0f3-b4f8-11e6-af21-db5929c35768'),
('e995c3bc-b4f8-11e6-af21-db5929c35768', 'notbook', '100.00', 'USD', 4, 'bla', '393fdccd-b4f8-11e6-af21-db5929c35768', 'aeb0b0f3-b4f8-11e6-af21-db5929c35768');

-- --------------------------------------------------------

--
-- Структура таблицы `seller`
--

CREATE TABLE IF NOT EXISTS `seller` (
  `id` char(36) NOT NULL,
  `business_name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `country` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `indexx` varchar(8) NOT NULL,
  `login` varchar(15) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `seller`
--

INSERT INTO `seller` (`id`, `business_name`, `email`, `phone`, `country`, `state`, `region`, `city`, `street`, `indexx`, `login`, `password`) VALUES
('393fdccd-b4f8-11e6-af21-db5929c35768', 'Lenovo', 'bla@gmail.com', '+34895761552', 'America', NULL, NULL, 'Chicago', 'Franka str., 2', '12345', 'blablabla', 'blablabla123');

-- --------------------------------------------------------

--
-- Структура таблицы `shipment`
--

CREATE TABLE IF NOT EXISTS `shipment` (
  `id` char(36) NOT NULL,
  `delivery_service` varchar(50) DEFAULT NULL,
  `total_sum` decimal(13,2) NOT NULL DEFAULT '0.00',
  `currency` varchar(50) NOT NULL,
  `delivery_date` timestamp NULL DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `address_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_id` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `shipment`
--

INSERT INTO `shipment` (`id`, `delivery_service`, `total_sum`, `currency`, `delivery_date`, `status`, `address_id`) VALUES
('2922a530-3158-4d27-a651-bdea1cb1efc3', NULL, '0.00', 'USD', NULL, '0', 'fde5bada-41df-4355-8cdf-556274c0d9b7'),
('46b2659b-1ff9-43bb-995b-d947e12304aa', NULL, '0.00', 'USD', NULL, '0', '0937f0d9-40e3-434c-a959-dce30d5121f5'),
('8020dc3a-86be-4176-b46d-d0244786fb81', NULL, '0.00', 'USD', NULL, '0', '6c1f6299-116d-42eb-8bb0-6799c9d61688'),
('86738bb1-cd86-4113-bbfc-73c395f3a0a2', NULL, '0.00', 'USD', NULL, '0', '0b8722d9-b897-4967-9c39-9f1036ac675d'),
('87f180df-720e-4908-a20e-1bfa49c747eb', NULL, '0.00', 'USD', NULL, '0', '1ef0b12a-d5c7-4fd0-a569-c11562b474f8'),
('c8c3e9ea-dca3-4a6a-9cca-89b3604847c1', NULL, '0.00', 'USD', NULL, '0', '08a6c002-b5c4-4c12-b0d3-6ef17b355833');

-- --------------------------------------------------------

--
-- Структура таблицы `type`
--

CREATE TABLE IF NOT EXISTS `type` (
  `id` char(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `category_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `type`
--

INSERT INTO `type` (`id`, `name`, `category_id`) VALUES
('aeb0b0f3-b4f8-11e6-af21-db5929c35768', 'notebooks', '97b87e56-b4f8-11e6-af21-db5929c35768');

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`shipment_id`) REFERENCES `shipment` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `order_item_ibfk_3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `product_ibfk_4` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `shipment`
--
ALTER TABLE `shipment`
  ADD CONSTRAINT `shipment_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `type`
--
ALTER TABLE `type`
  ADD CONSTRAINT `type_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
