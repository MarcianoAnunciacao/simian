create table dna(
    id int not null AUTO_INCREMENT,
    sequence varchar(6),
    primary key (id)
);

create table dna_statistic(
    id int not null AUTO_INCREMENT,
    mutant_dna int not null,
    human_dna int not null,
    ratio decimal(18,2),
    primary key (id)
);
