CREATE TABLE IF NOT EXISTS Adn(
	id bigserial primary key,
	adn varchar,
	isMutant boolean
);

CREATE TABLE IF NOT EXISTS Stat(
	id bigserial primary key,
	count_mutant_dna int,
	count_humna_dna int,
	ratio double precision
);