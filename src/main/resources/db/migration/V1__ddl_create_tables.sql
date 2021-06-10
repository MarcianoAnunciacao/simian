create table dna(
    id int not null,
    sequence varchar(6),
    primary key (id)
);

create table dna_statistic(
    id int not null,
    mutant_dna int not null,
    human_dna int not null,
    ratio number(18,2),
    primary key (id)
);
