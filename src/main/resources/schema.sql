CREATE DATABASE ADN;

CREATE TABLE Adn(
	id bigserial primary key,
	adn varchar,
	isMutant boolean
);

CREATE TABLE Stat(
	id bigserial primary key,
	count_mutant_dna int,
	count_humna_dna int,
	ratio double precision
);