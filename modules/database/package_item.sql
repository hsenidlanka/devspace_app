-- phpMyAdmin SQL Dump
-- version 4.4.13.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 09, 2016 at 11:52 AM
-- Server version: 5.6.31-0ubuntu0.15.10.1
-- PHP Version: 5.6.11-1ubuntu3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pizza_shefu`
--

-- --------------------------------------------------------

--
-- Table structure for table `package_item`
--

CREATE TABLE IF NOT EXISTS `package_item` (
  `package_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `size` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `package_item`
--
ALTER TABLE `package_item`
  ADD PRIMARY KEY (`package_id`,`item_id`),
  ADD KEY `item_id` (`item_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `package_item`
--
ALTER TABLE `package_item`
  ADD CONSTRAINT `package_item_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `package` (`id`),
  ADD CONSTRAINT `package_item_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
