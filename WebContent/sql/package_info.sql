-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- 主機: localhost
-- 產生時間： 2018 年 12 月 11 日 16:30
-- 伺服器版本: 5.7.24-0ubuntu0.16.04.1
-- PHP 版本： 7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `mfg_world`
--

-- --------------------------------------------------------

--
-- 資料表結構 `package_info`
--

CREATE TABLE `package_info` (
  `id` int(11) NOT NULL,
  `date1` date NOT NULL,
  `time1` time NOT NULL,
  `box` text COLLATE utf8_bin NOT NULL,
  `product` text COLLATE utf8_bin NOT NULL,
  `lot1` text COLLATE utf8_bin NOT NULL,
  `weight1` float NOT NULL,
  `lot2` text COLLATE utf8_bin NOT NULL,
  `weight2` float NOT NULL,
  `lot3` text COLLATE utf8_bin NOT NULL,
  `weight3` float NOT NULL,
  `total_weight` float NOT NULL,
  `recorder` text COLLATE utf8_bin NOT NULL COMMENT '紀錄者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 資料表的匯出資料 `package_info`
--

INSERT INTO `package_info` (`id`, `date1`, `time1`, `box`, `product`, `lot1`, `weight1`, `lot2`, `weight2`, `lot3`, `weight3`, `total_weight`, `recorder`) VALUES
(2, '2018-12-11', '15:12:11', 'B80000018', 'CP006', 'B80000018', 10, 'B80000018', 10, 'B80000018', 10, 30, '林彥亨'),
(3, '2018-12-11', '15:17:54', 'B80000015', 'CP006', 'B80000018', 10, 'B80000017', 10, 'B80000016', 10, 30, '林彥亨'),
(5, '2018-12-11', '15:25:39', 'CP006', 'CP006', 'B80000018', 10, 'B80000017', 10, 'B80000016', 10, 30, '林彥亨'),
(9, '2018-12-11', '15:40:38', 'CP006', 'CP006', 'B80000018', 10, 'B80000017', 9.8, '', 0, 19.8, '林彥亨'),
(11, '2018-12-11', '15:43:58', 'CP006', 'CP006', 'B80000018', 10, ' B80000017', 8.7, '', 0, 18.7, '林彥亨');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `package_info`
--
ALTER TABLE `package_info`
  ADD PRIMARY KEY (`id`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `package_info`
--
ALTER TABLE `package_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
