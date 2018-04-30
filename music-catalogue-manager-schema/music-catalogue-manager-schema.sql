-- phpMyAdmin SQL Dump
-- version 4.6.5.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2018 at 06:33 PM
-- Server version: 5.6.34-log
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `music_db`
--
CREATE DATABASE IF NOT EXISTS `music_catalogue_manager_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `music_catalogue_manager_db`;

-- --------------------------------------------------------

--
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `artist_id` int(10) UNSIGNED NOT NULL,
  `catalogue_path` varchar(100) DEFAULT NULL,
  `release_date` DATE DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `api_identifier`
--
CREATE TABLE `api_identifier` (
  `id` int(10) UNSIGNED NOT NULL,
  `api_name` VARCHAR(10) DEFAULT NULL,
  `value` VARCHAR(20) DEFAULT NULL,
  `artist_id` int(10) UNSIGNED DEFAULT NULL,
  `album_id` int(10) UNSIGNED DEFAULT NULL,
  `song_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- --------------------------------------------------------

--
-- Table structure for table `artist`
--

CREATE TABLE `artist` (
  `id` int(10) UNSIGNED NOT NULL,  
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `music_entry`
--

CREATE TABLE `music_entry` (
  `id` int(10) UNSIGNED NOT NULL,
  `catalogue_path` VARCHAR(100) DEFAULT NULL,
  `artist_id` int(10) UNSIGNED DEFAULT NULL,
  `album_id` int(10) UNSIGNED DEFAULT NULL,
  `song_id` int(10) UNSIGNED DEFAULT NULL,
  `path_origin` VARCHAR(100), 
  `parent_path_origin` VARCHAR(100) DEFAULT NULL,
  `name_origin` VARCHAR(50) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `id` int(10) UNSIGNED NOT NULL,
  `album_id` int(11) UNSIGNED NOT NULL,
  `length` int(10) UNSIGNED NOT NULL,
  `path_origin` VARCHAR(100), 
  `catalogue_path` VARCHAR(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_album_artist_id` (`artist_id`);

--
-- Indexes for table `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `music_entry`
--
ALTER TABLE `music_entry`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_song_album_id` (`album_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `artist`
--
ALTER TABLE `artist`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `music_entry`
--
ALTER TABLE `music_entry`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `fk_album_artist_id` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`);

--
-- Constraints for table `song`
--
ALTER TABLE `song`
  ADD CONSTRAINT `fk_song_album_id` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
