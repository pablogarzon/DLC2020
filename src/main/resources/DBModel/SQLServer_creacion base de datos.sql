--Script for create db TPDLC
--CREATE DATABASE TPDLC; --crear base de dato
USE TPDLC;--usar goses
--SHOW TABLES; #mostrar tablas de goses

--Script for create table Vocabulario
IF OBJECT_ID('vocabulario') IS NOT NULL
	drop table vocabulario;

CREATE TABLE vocabulario(
palabra varchar(80) NOT NULL,
nr		int DEFAULT '1',
maxtf	int DEFAULT '1',
PRIMARY KEY (palabra)
);

--Script for create table documentos
IF OBJECT_ID('documentos') IS NOT NULL
	drop table documentos;
CREATE TABLE documentos (
idDoc	int NOT NULL IDENTITY,	--'id documento - pk'
nombre	varchar(80),			--'nombre del documento'
url		varchar(100),			--'ubicacion/url del documento'
PRIMARY KEY (idDoc)
);

--Script for create table posteo

IF OBJECT_ID('posteo') IS NOT NULL
	drop table posteo;

CREATE TABLE posteo(
palabra varchar(80) NOT NULL,	--palabra del vocabulario - fk/pk
idDoc	int NOT NULL,			--'id documento - fk/pk'
tf		int DEFAULT '1',		--'cant tr aparece en di'
PRIMARY KEY (palabra,idDoc),
FOREIGN KEY (palabra) REFERENCES vocabulario(palabra),
FOREIGN KEY (idDoc) REFERENCES documentos(idDoc)
);



