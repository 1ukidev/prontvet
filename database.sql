create database `prontvet`;

use `prontvet`;

create table if not exists `animais` (
	`id` int not null auto_increment primary key,
	`nome` varchar(255) not null,
	`raca` varchar(255) not null,
	`sexo` char not null,
	`idade` int not null,
	`peso` double not null
)

create table if not exists `tutores` (
	`id` int not null auto_increment primary key,
	`nome` varchar(255) not null,
	`telefone` varchar(255) not null,
	`endereco` varchar(255) not null
)
