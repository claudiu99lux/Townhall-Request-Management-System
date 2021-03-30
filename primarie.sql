-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1
-- Timp de generare: mart. 25, 2021 la 02:03 PM
-- Versiune server: 10.4.14-MariaDB
-- Versiune PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `primarie`
--
CREATE DATABASE IF NOT EXISTS `primarie` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `primarie`;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `address`
--

CREATE TABLE `address` (
  `buildingType` int(11) NOT NULL,
  `id` varchar(255) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `apartmentNumber` int(11) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `owner_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `address`
--

INSERT INTO `address` (`buildingType`, `id`, `number`, `street`, `apartmentNumber`, `floor`, `owner_id`) VALUES
(2, '6dbf9a37-e09d-418e-8353-a7686e8e1fdc', '25', 'Calea Bucuresti', 16, 4, '2323416a-baaa-4d42-a272-ddc3e9789007'),
(2, '88ee9d0d-5a3b-4945-b107-a755795ca9e5', '102', 'Calea Nationala', 24, 5, 'a6817031-d862-4196-9666-c69071ff2dc1'),
(1, '93753e14-95a7-4603-94d2-65b029173968', '12', 'Mihai Viteazul', NULL, NULL, 'a6817031-d862-4196-9666-c69071ff2dc1'),
(1, 'd1fc5d0a-1e05-478c-b065-ca0e72999629', '136', 'Observatorului', NULL, NULL, 'a6817031-d862-4196-9666-c69071ff2dc1');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `request`
--

CREATE TABLE `request` (
  `id` varchar(255) NOT NULL,
  `approved` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `address_id` varchar(255) DEFAULT NULL,
  `request_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `request`
--

INSERT INTO `request` (`id`, `approved`, `content`, `date`, `address_id`, `request_type`) VALUES
('10713709-9d51-42b5-aee9-6650ec3b1e34', 0, 'Model instiintare\nNice', '2021-03-25', '6dbf9a37-e09d-418e-8353-a7686e8e1fdc', 'ba055137-d450-4519-9161-7dce8c7dc25d'),
('6ce63b2f-24ba-42dd-af06-525cc3ef40ae', 1, 'Test edit denied', '2021-03-24', '88ee9d0d-5a3b-4945-b107-a755795ca9e5', '74b05671-7e03-46f3-b8cc-45d730e932bf'),
('98c4cb96-9c24-4a4e-9e75-8734787c58bf', 0, 'Text mai lung pentru a testa preview-ul', '2021-03-24', '93753e14-95a7-4603-94d2-65b029173968', '383cd5b0-9912-4809-9d73-a64ba350d231'),
('a6050079-a60d-4781-b55c-f73c4f59ee8e', 0, 'Continut', '2021-03-25', 'd1fc5d0a-1e05-478c-b065-ca0e72999629', '383cd5b0-9912-4809-9d73-a64ba350d231'),
('b039c1e1-9322-4766-b32d-56538d05b5bd', 0, 'Cerere parcare', '2021-03-25', '6dbf9a37-e09d-418e-8353-a7686e8e1fdc', '74b05671-7e03-46f3-b8cc-45d730e932bf'),
('df6cc1e9-ba70-482b-a520-4e02bcb628fe', 0, 'ultima', '2021-03-24', '93753e14-95a7-4603-94d2-65b029173968', '74b05671-7e03-46f3-b8cc-45d730e932bf'),
('ed209670-fc34-47d5-ad7d-9a62e6b9b1ac', 0, 'Super\nMerge', '2021-03-24', '88ee9d0d-5a3b-4945-b107-a755795ca9e5', '74b05671-7e03-46f3-b8cc-45d730e932bf');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `request_type`
--

CREATE TABLE `request_type` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `request_type`
--

INSERT INTO `request_type` (`id`, `name`) VALUES
('383cd5b0-9912-4809-9d73-a64ba350d231', 'Declaratie'),
('74b05671-7e03-46f3-b8cc-45d730e932bf', 'Cerere'),
('ba055137-d450-4519-9161-7dce8c7dc25d', 'Instiintare');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `user`
--

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `CNP` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Eliminarea datelor din tabel `user`
--

INSERT INTO `user` (`id`, `CNP`, `email`, `firstName`, `lastName`, `password`, `role`) VALUES
('2323416a-baaa-4d42-a272-ddc3e9789007', '84531687352', 'robert@email.com', 'Robert', 'Teodorescu', 'robert', 1),
('a6817031-d862-4196-9666-c69071ff2dc1', '1990505050005', 'claudiu@email.com', 'Claudiu', 'Mitelu', 'claudiu', 1),
('d14b28b3-1d69-4e11-b6e2-4fa8ca695233', '1991204117825', 'admin@admin.com', 'Admin', 'Admin', 'admin123', 2);

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrbqk9im53tdyjlao9xdobr4sh` (`owner_id`);

--
-- Indexuri pentru tabele `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5lgq31tbbs7hag7npu31ha3l4` (`address_id`),
  ADD KEY `FKigc1umn3lbmlsv3sss67h1wdp` (`request_type`);

--
-- Indexuri pentru tabele `request_type`
--
ALTER TABLE `request_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Constrângeri pentru tabele eliminate
--

--
-- Constrângeri pentru tabele `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FKrbqk9im53tdyjlao9xdobr4sh` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`);

--
-- Constrângeri pentru tabele `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `FK5lgq31tbbs7hag7npu31ha3l4` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FKigc1umn3lbmlsv3sss67h1wdp` FOREIGN KEY (`request_type`) REFERENCES `request_type` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
