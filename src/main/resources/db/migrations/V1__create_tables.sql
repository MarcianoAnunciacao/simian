CREATE TABLE dna(
    id INT NOT NULL,
    sequence VARCHAR(6) NOT NULL,
    CONSTRAINT id_key PRIMARY KEY (id)
)

CREATE TABLE statistic(
    id INT NOT NULL,
    mutant_dna INT NOT_NULL,
    humanDna INT NOT_NULL
    CONSTRAINT id_key PRIMARY KEY (id)
)