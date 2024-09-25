-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 25, 2024 at 06:54 PM
-- Server version: 8.0.39-0ubuntu0.22.04.1
-- PHP Version: 8.1.2-1ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sgpa`
--

-- --------------------------------------------------------

--
-- Table structure for table `aplicacao`
--

CREATE TABLE `aplicacao` (
  `aplicacao` varchar(255) NOT NULL,
  `container_blob` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `theme` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `aplicacao`
--

INSERT INTO `aplicacao` (`aplicacao`, `container_blob`, `nome`, `theme`) VALUES
('aplicacao', 'teaaa', 'SGPA', 'Journal');

-- --------------------------------------------------------

--
-- Table structure for table `arquivos`
--

CREATE TABLE `arquivos` (
  `id` bigint NOT NULL,
  `nome_arquivo` varchar(255) DEFAULT NULL,
  `processo_id` bigint NOT NULL,
  `nome_blob` varchar(255) DEFAULT NULL,
  `data_arquivo` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --------------------------------------------------------

--
-- Table structure for table `tb_departamento`
--

CREATE TABLE `tb_departamento` (
  `id` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_processo`
--

CREATE TABLE `tb_processo` (
  `id` bigint NOT NULL,
  `ano_vigencia` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `protocolo` bigint DEFAULT NULL,
  `departamento_id` bigint NOT NULL,
  `requerente_id` bigint DEFAULT NULL,
  `servico_id` bigint NOT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `data_abertura` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tb_servico`
--

CREATE TABLE `tb_servico` (
  `id` bigint NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --------------------------------------------------------

--
-- Table structure for table `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `id` bigint NOT NULL,
  `enabled` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` tinyint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL
) ;

--
-- Dumping data for table `tb_usuario`
--

INSERT INTO `tb_usuario` (`id`, `enabled`, `nome`, `password`, `role`, `username`, `bairro`, `cep`, `cidade`, `complemento`, `cpf`, `email`, `logradouro`, `numero`, `telefone`, `uf`) VALUES
(1, b'1', 'ADMIN', '$2a$10$Z33mbQruIbLSfkS0aBL5WO0B.eLJJhFinORDFVcTySH97EHOIct5e', 0, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
-- --------------------------------------------------------

--
-- Table structure for table `tb_vigencia`
--

CREATE TABLE `tb_vigencia` (
  `id` bigint NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `protocolo_vigencia` bigint DEFAULT NULL,
  `servico_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `usuario_departamento`
--

CREATE TABLE `usuario_departamento` (
  `usuario_id` bigint NOT NULL,
  `departamento_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aplicacao`
--
ALTER TABLE `aplicacao`
  ADD PRIMARY KEY (`aplicacao`);

--
-- Indexes for table `arquivos`
--
ALTER TABLE `arquivos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3400my2giq82bgpwtjwsb7y7a` (`processo_id`);

--
-- Indexes for table `tb_departamento`
--
ALTER TABLE `tb_departamento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_processo`
--
ALTER TABLE `tb_processo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5mr0ugb77xuo3u7s7r518yypu` (`departamento_id`),
  ADD KEY `FK8uyna9oh7ycu09ymsruqycm6x` (`requerente_id`),
  ADD KEY `FKga0wt0vyqsx7ghd3k927mpias` (`servico_id`),
  ADD KEY `FKjt4pg8p0yacu1p6dib7h9xue1` (`usuario_id`);

--
-- Indexes for table `tb_servico`
--
ALTER TABLE `tb_servico`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_vigencia`
--
ALTER TABLE `tb_vigencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3s5gu3bilg0reibs1pki5u9jj` (`servico_id`);

--
-- Indexes for table `usuario_departamento`
--
ALTER TABLE `usuario_departamento`
  ADD PRIMARY KEY (`usuario_id`,`departamento_id`),
  ADD KEY `FKjtie0idjohiw19g0a9p3j54n4` (`departamento_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `arquivos`
--
ALTER TABLE `arquivos`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `tb_departamento`
--
ALTER TABLE `tb_departamento`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `tb_processo`
--
ALTER TABLE `tb_processo`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `tb_servico`
--
ALTER TABLE `tb_servico`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tb_usuario`
--
ALTER TABLE `tb_usuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_vigencia`
--
ALTER TABLE `tb_vigencia`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `arquivos`
--
ALTER TABLE `arquivos`
  ADD CONSTRAINT `FK3400my2giq82bgpwtjwsb7y7a` FOREIGN KEY (`processo_id`) REFERENCES `tb_processo` (`id`);

--
-- Constraints for table `tb_processo`
--
ALTER TABLE `tb_processo`
  ADD CONSTRAINT `FK5mr0ugb77xuo3u7s7r518yypu` FOREIGN KEY (`departamento_id`) REFERENCES `tb_departamento` (`id`),
  ADD CONSTRAINT `FK8uyna9oh7ycu09ymsruqycm6x` FOREIGN KEY (`requerente_id`) REFERENCES `tb_usuario` (`id`),
  ADD CONSTRAINT `FKga0wt0vyqsx7ghd3k927mpias` FOREIGN KEY (`servico_id`) REFERENCES `tb_servico` (`id`),
  ADD CONSTRAINT `FKjt4pg8p0yacu1p6dib7h9xue1` FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuario` (`id`);

--
-- Constraints for table `tb_vigencia`
--
ALTER TABLE `tb_vigencia`
  ADD CONSTRAINT `FK3s5gu3bilg0reibs1pki5u9jj` FOREIGN KEY (`servico_id`) REFERENCES `tb_servico` (`id`);

--
-- Constraints for table `usuario_departamento`
--
ALTER TABLE `usuario_departamento`
  ADD CONSTRAINT `FKg1dtrafrjbvfp3l2mhey0x7av` FOREIGN KEY (`usuario_id`) REFERENCES `tb_usuario` (`id`),
  ADD CONSTRAINT `FKjtie0idjohiw19g0a9p3j54n4` FOREIGN KEY (`departamento_id`) REFERENCES `tb_departamento` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
