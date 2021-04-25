-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 05:43 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `order_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer_table`
--

CREATE TABLE `customer_table` (
  `userid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_table`
--

INSERT INTO `customer_table` (`userid`, `username`, `password`, `email`, `phone`) VALUES
(3, 'rajaka12', '456', 'abc@gmail.com', 77852),
(4, 'rajaka20', '456', 'abc@gmail.com', 77852),
(5, 'rajaka30', '456', 'abc@gmail.com', 77852),
(8, 'rajaka premasinghe', '456', 'abc@gmail.com', 77852);

-- --------------------------------------------------------

--
-- Table structure for table `order_table`
--

CREATE TABLE `order_table` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `items` int(11) NOT NULL,
  `item_price` decimal(10,2) NOT NULL,
  `discount` decimal(10,2) NOT NULL,
  `customerid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_table`
--

INSERT INTO `order_table` (`order_id`, `product_id`, `items`, `item_price`, `discount`, `customerid`) VALUES
(3, 68, 10, '1000.00', '400.00', 2),
(4, 68, 10, '1000.00', '400.00', 2),
(7, 68, 10, '1000.00', '400.00', 2),
(8, 68, 50, '1000.00', '400.00', 2),
(9, 68, 10, '1000.00', '400.00', 2),
(10, 68, 10, '5000.00', '400.00', 2),
(11, 68, 20, '5000.00', '400.00', 2),
(12, 68, 30, '5000.00', '400.00', 2),
(13, 68, 130, '5000.00', '400.00', 2),
(14, 68, 130, '5000.00', '400.00', 2),
(15, 68, 130, '5000.00', '400.00', 2),
(16, 68, 130, '5000.00', '400.00', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer_table`
--
ALTER TABLE `customer_table`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `order_table`
--
ALTER TABLE `order_table`
  ADD PRIMARY KEY (`order_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_table`
--
ALTER TABLE `customer_table`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `order_table`
--
ALTER TABLE `order_table`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
