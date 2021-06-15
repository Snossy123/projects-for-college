-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 22, 2021 at 12:49 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinic`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID` int(20) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `MOBILE` varchar(20) NOT NULL,
  `ADDRESS` varchar(20) NOT NULL,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ID`, `NAME`, `MOBILE`, `ADDRESS`, `USERNAME`, `PASSWORD`) VALUES
(111, 'Solieman', '01125833982', 'cairo', '1', '1'),
(112, 'Rawan', '01552467461', 'Giza', 'Rawan123', '456');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `BILL_ID` int(20) NOT NULL,
  `PATIENT_ID` int(20) NOT NULL,
  `DOCTOR_ID` int(20) NOT NULL,
  `MEDICINE` varchar(20) NOT NULL,
  `DATE` date NOT NULL,
  `Resources_Charges` float NOT NULL,
  `medicine_price` float NOT NULL,
  `Bed_Cost` float NOT NULL,
  `Total_Charge` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`BILL_ID`, `PATIENT_ID`, `DOCTOR_ID`, `MEDICINE`, `DATE`, `Resources_Charges`, `medicine_price`, `Bed_Cost`, `Total_Charge`) VALUES
(1, 1, 1, '1', '2021-01-20', 5, 5, 5, 5),
(2, 2, 2, 'sad', '2002-02-02', 1, 1, 1, 3),
(3, 3, 1, 'sss,bbb', '2002-02-06', 5, 5, 5, 15),
(4, 4, 3, 'sad', '2002-02-02', 0, 0, 3, 3),
(5, 5, 1, 'sss,bbb', '2002-02-06', 5, 5, 8, 18),
(6, 6, 3, 'sad', '2002-02-02', 2, 2, 2, 6),
(7, 5, 4, 'dd,ff', '2020-12-12', 5, 3, 2, 10),
(8, 15, 0, 'asprine,catefast', '2020-01-17', 30, 50, 30, 110),
(9, 15, 0, 'asprine,catefast', '2020-01-17', 50, 100, 50, 200),
(10, 11, 5, 'asprine ', '2020-01-01', 20, 20, 10, 50),
(11, 11, 1, 'cccc', '2021-01-12', 2, 5, 2, 50),
(12, 11, 5, 'asprine ', '2020-01-01', 1, 1, 1, 3),
(13, 12, 5, 'Aspirine', '2021-02-04', 45, 50, 40, 135),
(14, 12, 5, 'Aspirine', '2021-02-04', 596, 50, 40, 686),
(15, 12, 5, 'Aspirine', '2021-02-04', 90, 0, 0, 90),
(16, 13, 2, 'aspirine', '2021-03-03', 45, 11, 22, 78);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `name`, `mobile`, `address`, `username`, `password`) VALUES
(1, 'Sief', '01234567897', 'cairo', '3', '3'),
(2, 'SOLI', '01125833982', 'Alex', 'SOLI@Clinic', '456'),
(3, 'Ali', '014525545', 'cairo', 'Ali@Clinic', '123'),
(4, 'Asma', '01125833982', 'aswan', 'Asma@Clinic', '123'),
(5, 'Ahmed', '01126258559', '42 Abu Fares ST.', 'Ahmed@Clinic', '123'),
(6, 'essra', '012', 'alex', 'essra@Clinic', '123'),
(7, 'Solieman', '011', '0152', 'Solieman@Clinic', '123'),
(8, 'saad', '01533', 'suize', 'saad@Clinic', '123');

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE `medicine` (
  `barcode` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `cost` float NOT NULL,
  `number_pieces` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`barcode`, `name`, `cost`, `number_pieces`) VALUES
(123, 'Oral rinse', 75, 50),
(145, 'Lotion', 90, 111),
(456, 'dsds', 564, 87),
(546, 'asd', 5645, 800),
(564, 'asdfg', 254, 1150),
(651, 'Capsule', 95.8, 119),
(656, 'cxkldhx', 456, 45677),
(789, 'Aspirin', 45, 496),
(1234, 'banadoul', 75, 500),
(56464, 'hjdh', 675, 5645);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `id` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `token_id` varchar(20) DEFAULT NULL,
  `date` date NOT NULL,
  `doctor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `name`, `mobile`, `address`, `token_id`, `date`, `doctor_id`) VALUES
(4, 'doha', '01523648987', 'Giza', NULL, '2021-01-09', 4),
(5, 'dia', '01125499', 'alex', NULL, '2021-01-09', 4),
(6, 'dsa', '56', 'sda', NULL, '2020-01-01', 1),
(7, 'salah', '01143604922', 'cairo', NULL, '2021-01-09', 4),
(8, 'essra', '01236548785', 'cairo', '12', '2021-01-08', 2),
(9, 'fg', '34', 'gf', NULL, '2020-01-01', 1),
(10, 'swd', '21', 'sad', '14', '2020-01-01', 1),
(11, 'Ahmed ', '01125658', 'cairo', '15', '2020-01-17', 5),
(12, 'ali', '011', 'cairo', '16', '2021-02-02', 5),
(13, 'asma', '0112346', 'ain shams', '17', '2021-03-03', 2);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `patient_id` int(20) NOT NULL,
  `describe_medicine` varchar(60) NOT NULL,
  `Bed_Needed` int(20) NOT NULL,
  `date` date NOT NULL,
  `disease` varchar(20) NOT NULL,
  `Number_Residency_Days` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`patient_id`, `describe_medicine`, `Bed_Needed`, `date`, `disease`, `Number_Residency_Days`) VALUES
(4, 'f,g', 2, '2021-01-01', 'coha', 3),
(5, 'dd,ff', 4, '2020-12-12', 'de', 5),
(10, 'Asd', 2, '2020-02-02', 'asd', 2),
(11, 'asprine ', 1, '2020-01-01', 'coha', 2),
(12, 'Aspirine', 2, '2021-02-04', 'coaha', 5),
(13, 'aspirine', 2, '2021-03-03', 'cofied19', 5),
(15, 'asprine,catefast', 2, '2020-01-17', 'coha', 5);

-- --------------------------------------------------------

--
-- Table structure for table `receptionist`
--

CREATE TABLE `receptionist` (
  `id` int(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receptionist`
--

INSERT INTO `receptionist` (`id`, `name`, `mobile`, `address`, `username`, `password`) VALUES
(26, 'waleed', '01245789874', 'delta', '2', '2'),
(27, 'aya', '015', 'cairo', 'aya@Clinic', '123'),
(28, 'shawkat', '0102234', 'loxur', 'shawkat@Clinic', '123');

-- --------------------------------------------------------

--
-- Table structure for table `waiting_list`
--

CREATE TABLE `waiting_list` (
  `number_patient` int(11) NOT NULL,
  `doctor_id` int(20) NOT NULL,
  `patient_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `waiting_list`
--

INSERT INTO `waiting_list` (`number_patient`, `doctor_id`, `patient_id`) VALUES
(6, 2, 3),
(7, 4, 1),
(8, 4, 2),
(11, 4, 7),
(12, 2, 8),
(13, 1, 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`BILL_ID`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`barcode`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`patient_id`,`describe_medicine`);

--
-- Indexes for table `receptionist`
--
ALTER TABLE `receptionist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `waiting_list`
--
ALTER TABLE `waiting_list`
  ADD PRIMARY KEY (`number_patient`,`doctor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `BILL_ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `medicine`
--
ALTER TABLE `medicine`
  MODIFY `barcode` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56465;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `receptionist`
--
ALTER TABLE `receptionist`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `waiting_list`
--
ALTER TABLE `waiting_list`
  MODIFY `number_patient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
