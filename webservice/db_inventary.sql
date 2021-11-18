-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 12-10-2020 a las 20:34:08
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_inventary`
--
CREATE DATABASE IF NOT EXISTS `db_inventary` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `db_inventary`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_categorias`
--

CREATE TABLE `tb_categorias` (
  `id_categoria` int(11) NOT NULL,
  `nom_categoria` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `estado_categoria` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_productos`
--

CREATE TABLE `tb_productos` (
  `id_producto` int(11) NOT NULL,
  `nom_producto` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `des_producto` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `stock` decimal(3,2) NOT NULL,
  `precio` decimal(3,2) NOT NULL,
  `unidad_medida` tinyint(1) NOT NULL,
  `estado_producto` int(11) NOT NULL,
  `categoria` int(11) NOT NULL,
  `fecha_entrada` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuarios`
--

CREATE TABLE `tb_usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(35) COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(150) COLLATE utf8_spanish_ci NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `pregunta` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `respuesta` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_categorias`
--
ALTER TABLE `tb_categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `categoria` (`categoria`);

--
-- Indices de la tabla `tb_usuarios`
--
ALTER TABLE `tb_usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD CONSTRAINT `tb_productos_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `tb_categorias` (`id_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
