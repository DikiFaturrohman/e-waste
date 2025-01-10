-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 10, 2025 at 10:45 AM
-- Server version: 8.0.30
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `waste_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
CREATE TABLE `otp` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `otp_code` varchar(6) NOT NULL,
  `expiration_time` datetime NOT NULL,
  `status` enum('unused','used') DEFAULT 'unused'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `otp_requests`
--

DROP TABLE IF EXISTS `otp_requests`;
CREATE TABLE `otp_requests` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `otp_code` varchar(6) NOT NULL,
  `expires_at` datetime NOT NULL,
  `is_used` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `otp_requests`
--

INSERT INTO `otp_requests` (`id`, `email`, `otp_code`, `expires_at`, `is_used`) VALUES
(1, 'dikifaturrohman17@gmail.com', '582709', '2025-01-09 20:10:03', 0),
(2, 'dikifaturrohman17@gmail.com', '087769', '2025-01-10 04:11:20', 0),
(3, 'Dikifaturrohman17@gmail.com', '768406', '2025-01-10 04:16:08', 0),
(4, 'dikifaturrohman17@gmail.com', '957766', '2025-01-10 04:21:48', 0),
(5, 'dikifaturrohman17@gmail.com', '880836', '2025-01-10 04:25:40', 0),
(6, 'dikifaturrohman17@gmail.com', '828408', '2025-01-10 04:30:08', 0),
(7, 'dikifaturrohman17@gmail.com', '468654', '2025-01-10 04:34:07', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `role` enum('admin','user') DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`id`, `username`, `password`, `email`, `phone`, `address`, `profile_picture`, `role`) VALUES
(1, 'DIKI FATURROHMAN', '12345', 'dikifaturrohman17@gmail.com', '081223765077', 'SUBANG', NULL, 'user'),
(4, 'admin', 'admin123', 'admin@example.com', '08123456789', 'Headquarters', NULL, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `wastes`
--

DROP TABLE IF EXISTS `wastes`;
CREATE TABLE `wastes` (
  `id` int NOT NULL,
  `category` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `wastes`
--

INSERT INTO `wastes` (`id`, `category`, `type`) VALUES
(6, 'Peralatan Besar', 'Mesin Cuci'),
(7, 'Peralatan Kecil', 'Kamera'),
(8, 'Peralatan Kecil', 'Pemanggang Roti'),
(9, 'Peralatan Besar', 'Pengering Pakaian'),
(10, 'Telekomunikasi', 'Telepon'),
(11, 'Telekomunikasi', 'GPS'),
(12, 'Telekomunikasi', 'Smartphone'),
(13, 'Telekomunikasi', 'Router'),
(14, 'Peralatan Besar', 'Printer'),
(15, 'Pengatur Suhu', 'AC'),
(16, 'Pengatur Suhu', 'Freezer'),
(17, 'Layar', 'Monitor'),
(18, 'Layar', 'Tablet'),
(19, 'Lampu', 'LED');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `otp`
--
ALTER TABLE `otp`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `otp_requests`
--
ALTER TABLE `otp_requests`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `wastes`
--
ALTER TABLE `wastes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `otp`
--
ALTER TABLE `otp`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `otp_requests`
--
ALTER TABLE `otp_requests`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `wastes`
--
ALTER TABLE `wastes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `otp`
--
ALTER TABLE `otp`
  ADD CONSTRAINT `otp_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
