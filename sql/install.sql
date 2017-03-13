CREATE TABLE IF NOT EXISTS `dish` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` float NOT NULL,
  `image` blob,
  `dish_group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

ALTER TABLE `dish`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `name` (`name`);


CREATE TABLE IF NOT EXISTS `dish_group` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_group` int(11) DEFAULT NULL
)

ALTER TABLE `dish_group`
 ADD PRIMARY KEY (`id`);
ENT pour la table `dish`
--
ALTER TABLE `dish`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `dish_group`
--
ALTER TABLE `dish_group`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
