CREATE DATABASE sistema_academico;
USE sistema_academico;

CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    campus VARCHAR(100),
    periodo VARCHAR(20)
);

CREATE TABLE aluno (
    rgm VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(100),
    cpf VARCHAR(14) UNIQUE,
    celular VARCHAR(15),
    data_nascimento DATE,
    email VARCHAR(100),
    endereco VARCHAR(150),
    municipio VARCHAR(100),
    uf VARCHAR(2),
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE nota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rgm VARCHAR(20),
    disciplina VARCHAR(100),
    semestre VARCHAR(10),
    nota DOUBLE,
    faltas INT,
    FOREIGN KEY (rgm) REFERENCES aluno(rgm) ON DELETE CASCADE
);

ALTER TABLE aluno MODIFY data_nascimento VARCHAR(10);

SELECT * FROM aluno;

DELETE FROM aluno WHERE rgm IS NULL;

SELECT rgm, nome FROM aluno;

INSERT INTO curso (nome, campus, periodo) VALUES
('Análise e Desenvolvimento de Sistemas', 'Tatuapé', 'Noturno'),
('Ciência da Computação', 'Centro', 'Matutino'),
('Engenharia de Software', 'Santo André', 'Vespertino'),
('Sistemas de Informação', 'Campinas', 'Noturno');