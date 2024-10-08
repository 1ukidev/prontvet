-- Script para criação do banco de dados

CREATE DATABASE IF NOT EXISTS `prontvet`;

USE `prontvet`;

CREATE TABLE IF NOT EXISTS `usuario` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `senha` VARCHAR(255) NOT NULL
)
