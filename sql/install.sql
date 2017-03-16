--
-- Create tables
--
CREATE TABLE IF NOT EXISTS `dish` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) NOT NULL,
  `description` text,
  `price` float NOT NULL,
  `image` blob,
  `dish_group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `dish_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) NOT NULL PRIMARY KEY,
  `password` varchar(255) NOT NULL,
  `last_login` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `dish`
  ADD UNIQUE KEY `name` (`name`), 
  ADD CONSTRAINT `fk_dish_group` FOREIGN KEY (`dish_group_id`) REFERENCES `dish_group` (`id`);

ALTER TABLE `dish_group`
  ADD UNIQUE KEY `name` (`name`);
