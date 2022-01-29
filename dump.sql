-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306:8889
-- Generation Time: Jan 01, 2022 at 11:14 PM
-- Server version: 10.6.4-MariaDB
-- PHP Version: 7.3.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `spring`
--

-- --------------------------------------------------------

--
-- Table structure for table `urunler`
--

CREATE TABLE `urunler` (
                           `id` int(11) NOT NULL,
                           `isim` varchar(100) DEFAULT NULL,
                           `fiyat` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `urunler`
--

INSERT INTO `urunler` (`id`, `isim`, `fiyat`) VALUES
    (1, 'Macbook M1', 20000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `urunler`
--
ALTER TABLE `urunler`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `urunler`
--
ALTER TABLE `urunler`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
