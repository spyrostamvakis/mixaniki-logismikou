-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 31, 2017 at 08:29 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `concertdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `concert`
--

CREATE TABLE `concert` (
  `CONCERT_ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Place` text NOT NULL,
  `Date` date NOT NULL,
  `Cost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `concert`
--

INSERT INTO `concert` (`CONCERT_ID`, `Name`, `Place`, `Date`, `Cost`) VALUES
(1, 'KOTSIRAS', 'OAKA', '2017-02-15', 10),
(2, 'ZOUGANELI', 'VRAXON', '2017-02-10', 15),
(3, 'XATZIGIANNIS', 'PETRAS', '2017-02-22', 20),
(4, 'REMOS', 'LYKAVITOS', '2017-06-06', 25),
(6, 'MAXAIRITSAS', 'OAKA', '2017-05-12', 20);

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `CONCERT_ID` int(11) NOT NULL,
  `SEAT_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`CONCERT_ID`, `SEAT_ID`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 17),
(1, 18),
(1, 34),
(1, 40),
(1, 53),
(1, 58),
(1, 59),
(1, 60),
(2, 3),
(2, 4),
(2, 12),
(2, 18),
(2, 19),
(2, 20),
(2, 26),
(2, 27),
(2, 28),
(2, 43),
(2, 47),
(2, 51),
(2, 55),
(3, 1),
(3, 2),
(3, 3),
(3, 5),
(3, 6),
(3, 7),
(3, 9),
(3, 11),
(3, 13),
(3, 17),
(3, 19),
(3, 21),
(3, 23),
(3, 25),
(3, 26),
(3, 27),
(3, 29),
(3, 30),
(3, 31),
(3, 34),
(3, 39),
(3, 41),
(3, 46),
(3, 49),
(3, 50),
(3, 51),
(3, 53),
(3, 54),
(3, 55),
(3, 56),
(3, 57),
(3, 58),
(4, 5),
(4, 9),
(4, 17),
(4, 18),
(4, 20),
(4, 21),
(4, 29),
(4, 31),
(4, 33),
(4, 41),
(4, 45),
(4, 53),
(4, 57),
(6, 1),
(6, 2),
(6, 3),
(6, 13),
(6, 14);

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `SEAT_ID` int(11) NOT NULL,
  `SEAT_NAME` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seats`
--

INSERT INTO `seats` (`SEAT_ID`, `SEAT_NAME`) VALUES
(1, 'A1'),
(2, 'A2'),
(3, 'A3'),
(4, 'A4'),
(5, 'A5'),
(6, 'A6'),
(7, 'A7'),
(8, 'A8'),
(9, 'A9'),
(10, 'A10'),
(11, 'A11'),
(12, 'A12'),
(13, 'B1'),
(14, 'B2'),
(15, 'B3'),
(16, 'B4'),
(17, 'B5'),
(18, 'B6'),
(19, 'B7'),
(20, 'B8'),
(21, 'B9'),
(22, 'B10'),
(23, 'B11'),
(24, 'B12'),
(25, 'C1'),
(26, 'C2'),
(27, 'C3'),
(28, 'C4'),
(29, 'C5'),
(30, 'C6'),
(31, 'C7'),
(32, 'C8'),
(33, 'C9'),
(34, 'C10'),
(35, 'C11'),
(36, 'C12'),
(37, 'D1'),
(38, 'D2'),
(39, 'D3'),
(40, 'D4'),
(41, 'D5'),
(42, 'D6'),
(43, 'D7'),
(44, 'D8'),
(45, 'D9'),
(46, 'D10'),
(47, 'D11'),
(48, 'D12'),
(49, 'E1'),
(50, 'E2'),
(51, 'E3'),
(52, 'E4'),
(53, 'E5'),
(54, 'E6'),
(55, 'E7'),
(56, 'E8'),
(57, 'E9'),
(58, 'E10'),
(59, 'E11'),
(60, 'E12');

-- --------------------------------------------------------

--
-- Table structure for table `stadium`
--

CREATE TABLE `stadium` (
  `STADIUM_ID` int(11) NOT NULL,
  `NAME` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stadium`
--

INSERT INTO `stadium` (`STADIUM_ID`, `NAME`) VALUES
(1, 'OAKA'),
(2, 'VRAXON'),
(3, 'PETRAS'),
(4, 'LYKAVITOS');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `concert`
--
ALTER TABLE `concert`
  ADD PRIMARY KEY (`CONCERT_ID`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`CONCERT_ID`,`SEAT_ID`),
  ADD KEY `CONCERT_ID` (`CONCERT_ID`);

--
-- Indexes for table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`SEAT_ID`);

--
-- Indexes for table `stadium`
--
ALTER TABLE `stadium`
  ADD PRIMARY KEY (`STADIUM_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `concert`
--
ALTER TABLE `concert`
  MODIFY `CONCERT_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `seats`
--
ALTER TABLE `seats`
  MODIFY `SEAT_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT for table `stadium`
--
ALTER TABLE `stadium`
  MODIFY `STADIUM_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
