CREATE TABLE IF NOT EXISTS dentistas(
idDentista INT auto_increment PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
sobrenome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS pacientes(

idPaciente INT auto_increment PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
sobrenome VARCHAR(255),
rg VARCHAR(15) NOT NULL,
endereco VARCHAR(350),
dataCadastro DATE NOT NULL
);