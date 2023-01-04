-- SQLINES DEMO *** rated by MySQL Workbench
-- 11/01/21 23:54:12
-- SQLINES DEMO ***    Version: 1.0
-- SQLINES DEMO *** orward Engineering

/* SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0; */
/* SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0; */
/* SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'; */

-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy
-- SQLINES DEMO *** ------------------------------------

-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy
-- SQLINES DEMO *** ------------------------------------
CREATE SCHEMA IF NOT EXISTS "applibrography"; 
--	TEMPLATE = template0 
--	WITH OWNER "applibrography" 
--	ENCODING 'UTF8' LC_COLLATE = 
--	'en_US.UTF-8' 
--	LC_CTYPE = 'en_US.UTF-8'; 
	
SET SCHEMA 'applibrography' ;

-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_leitores`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_leitores_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_leitores (
  id INT ZEROFILL NOT NULL DEFAULT NEXTVAL ('applibrography.tb_leitores_seq'),
  nome VARCHAR(100) NULL,
  rg VARCHAR(30) NULL DEFAULT NULL,
  cpf VARCHAR(20) NULL DEFAULT NULL,
  email VARCHAR(200) NULL DEFAULT NULL,
  telefone VARCHAR(30) NULL DEFAULT NULL,
  celular VARCHAR(30) NULL DEFAULT NULL,
  cep VARCHAR(100) NULL DEFAULT NULL,
  endereco VARCHAR(255) NULL DEFAULT NULL,
  numero VARCHAR(50) NULL DEFAULT NULL,
  complemento VARCHAR(200) NULL DEFAULT NULL,
  bairro VARCHAR(100) NULL DEFAULT NULL,
  cidade VARCHAR(100) NULL DEFAULT NULL,
  estado VARCHAR(2) NULL DEFAULT NULL,
  tipo VARCHAR(15) NULL,
  is_locked SMALLINT NULL,
  curso VARCHAR(45) NULL,
  curso_ano VARCHAR(45) NULL,
  qtd_emprestimos INT NULL,
  emprestmax INT NULL,
  observacoes VARCHAR(225) NULL,
  PRIMARY KEY (id))
;

ALTER SEQUENCE applibrography.tb_leitores_seq RESTART WITH 00000100;


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_fornecedores`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_fornecedores_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_fornecedores (
  id INT NOT NULL DEFAULT NEXTVAL ('applibrography.tb_fornecedores_seq'),
  nome VARCHAR(100) NULL DEFAULT NULL,
  cnpj VARCHAR(100) NULL DEFAULT NULL,
  email VARCHAR(200) NULL DEFAULT NULL,
  telefone VARCHAR(30) NULL DEFAULT NULL,
  celular VARCHAR(30) NULL DEFAULT NULL,
  cep VARCHAR(100) NULL DEFAULT NULL,
  endereco VARCHAR(255) NULL DEFAULT NULL,
  numero VARCHAR(50) NULL DEFAULT NULL,
  complemento VARCHAR(200) NULL DEFAULT NULL,
  bairro VARCHAR(100) NULL DEFAULT NULL,
  cidade VARCHAR(100) NULL DEFAULT NULL,
  estado VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (id))
;


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_funcionarios`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_funcionarios_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_funcionarios (
  id INT NOT NULL DEFAULT NEXTVAL ('applibrography.tb_funcionarios_seq'),
  nome VARCHAR(100) NOT NULL,
  rg VARCHAR(30) NULL,
  cpf VARCHAR(20) NULL,
  email VARCHAR(200) NULL,
  senha VARCHAR(10) NOT NULL,
  cargo VARCHAR(100) NOT NULL,
  nivel_acesso VARCHAR(50) NOT NULL,
  telefone VARCHAR(30) NULL,
  celular VARCHAR(30) NULL,
  cep VARCHAR(100) NOT NULL,
  endereco VARCHAR(255) NULL,
  numero VARCHAR(50) NULL,
  complemento VARCHAR(200) NULL,
  bairro VARCHAR(100) NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(2) NOT NULL,
  PRIMARY KEY (id))
;


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_livros`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_livros_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_livros (
  id INT NOT NULL DEFAULT NEXTVAL ('applibrography.tb_livros_seq'),
  titulo VARCHAR(100) NOT NULL,
  autor VARCHAR(45) NOT NULL,
  editora VARCHAR(45) NOT NULL,
  isbn VARCHAR(13) NOT NULL,
  ano VARCHAR(4) NOT NULL,
  serie VARCHAR(45) NULL,
  edicao VARCHAR(45) NULL,
  idioma VARCHAR(25) NOT NULL,
  tb_fornecedores_id INT NULL,
  piso VARCHAR(45) NULL,
  corredor VARCHAR(45) NULL,
  posicao VARCHAR(45) NULL,
  secao VARCHAR(45) NULL,
  disponibilidade VARCHAR(15) NOT NULL,
  observacoes VARCHAR(225) NULL,
  is_emprestado SMALLINT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_tb_livros_tb_fornecedores1
    FOREIGN KEY (tb_fornecedores_id)
    REFERENCES applibrography.tb_fornecedores (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_tb_livros_tb_fornecedores1_idx ON applibrography.tb_livros (tb_fornecedores_id ASC);


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_emprestimos`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_emprestimos_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_emprestimos (
  id INT ZEROFILL NOT NULL DEFAULT NEXTVAL ('applibrography.tb_emprestimos_seq'),
  data_emprestimo TIMESTAMP(0) NULL,
  data_devolucao TIMESTAMP(0) NULL DEFAULT NULL,
  observacoes TEXT NULL DEFAULT NULL,
  tb_funcionarios_id INT NULL,
  tb_livros_id INT NULL,
  tb_livros_tb_fornecedores_id INT NULL,
  tb_leitores_id INT ZEROFILL NULL,
  data_entrega_agendada TIMESTAMP(0) NULL,
  tb_funcionarios_iddevol INT NULL,
  static_id_emprestimo INT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_tb_emprestimos_tb_funcionarios1
    FOREIGN KEY (tb_funcionarios_id)
    REFERENCES applibrography.tb_funcionarios (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tb_emprestimos_tb_livros1
    FOREIGN KEY (tb_livros_id)
    REFERENCES applibrography.tb_livros (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tb_emprestimos_tb_leitores1
    FOREIGN KEY (tb_leitores_id)
    REFERENCES applibrography.tb_leitores (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tb_emprestimos_tb_funcionarios2
    FOREIGN KEY (tb_funcionarios_iddevol)
    REFERENCES applibrography.tb_funcionarios (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

ALTER SEQUENCE applibrography.tb_emprestimos_seq RESTART WITH 10000000;

CREATE INDEX fk_tb_emprestimos_tb_funcionarios1_idx ON applibrography.tb_emprestimos (tb_funcionarios_id ASC);
CREATE INDEX fk_tb_emprestimos_tb_livros1_idx ON applibrography.tb_emprestimos (tb_livros_id ASC, tb_livros_tb_fornecedores_id ASC);
CREATE INDEX fk_tb_emprestimos_tb_leitores1_idx ON applibrography.tb_emprestimos (tb_leitores_id ASC);
CREATE INDEX fk_tb_emprestimos_tb_funcionarios2_idx ON applibrography.tb_emprestimos (tb_funcionarios_iddevol ASC);


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_multa`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_multa_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_multa (
  idmulta INT NOT NULL DEFAULT NEXTVAL ('applibrography.tb_multa_seq'),
  dias_atraso INT NOT NULL,
  valor_multa DECIMAL(10,2) NULL,
  esta_pago SMALLINT NULL,
  tb_leitores_id INT ZEROFILL NOT NULL,
  tb_emprestimos_id INT ZEROFILL NOT NULL,
  PRIMARY KEY (idmulta, tb_emprestimos_id)
 ,
  CONSTRAINT fk_multa_tb_leitores1
    FOREIGN KEY (tb_leitores_id)
    REFERENCES applibrography.tb_leitores (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tb_multa_tb_emprestimos1
    FOREIGN KEY (tb_emprestimos_id)
    REFERENCES applibrography.tb_emprestimos (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_multa_tb_leitores1_idx ON applibrography.tb_multa (tb_leitores_id ASC);
CREATE INDEX fk_tb_multa_tb_emprestimos1_idx ON applibrography.tb_multa (tb_emprestimos_id ASC);


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_opcoes`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_opcoes_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_opcoes (
  id INT NOT NULL DEFAULT NEXTVAL ('applibrography.tb_opcoes_seq'),
  data VARCHAR(45) NOT NULL,
  parentid INT NULL,
  PRIMARY KEY (id)
 ,
  CONSTRAINT fk_Opcoes_Opcoes1
    FOREIGN KEY (parentid)
    REFERENCES applibrography.tb_opcoes (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Opcoes_Opcoes1_idx ON applibrography.tb_opcoes (parentid ASC);


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** aphy`.`tb_recibos`
-- SQLINES DEMO *** ------------------------------------
-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE SEQUENCE applibrography.tb_recibos_seq;

CREATE TABLE IF NOT EXISTS applibrography.tb_recibos (
  id INT NOT NULL DEFAULT NEXTVAL ('applibrography.tb_recibos_seq'),
  emprestimo_id INT NULL,
  data_emprestimo VARCHAR(45) NULL,
  data_devolucao_agendada VARCHAR(45) NULL,
  data_entrega VARCHAR(45) NULL,
  livro VARCHAR(45) NULL,
  usuario VARCHAR(45) NULL,
  tipo VARCHAR(45) NULL,
  multa VARCHAR(45) NULL,
  status VARCHAR(45) NULL,
  funcionario VARCHAR(45) NULL,
  PRIMARY KEY (id))
;


/* SET SQL_MODE=@OLD_SQL_MODE; */
/* SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; */
/* SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; */

-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** pplibrography`.`tb_leitores`
-- SQLINES DEMO *** ------------------------------------
START TRANSACTION;
SET SCHEMA 'applibrography';
INSERT INTO applibrography.tb_leitores (id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado, tipo, is_locked, curso, curso_ano, qtd_emprestimos, emprestmax, observacoes) VALUES (00000101, 'Leitor Teste', '5.432.234-1', '099.999.345-23', 'leitor@mail.com', '(42)98877-2299', '(42)98877-2299', '854670-000', 'Rua 1 de abril', '13', 'casa', 'Centro', 'Mallet', 'PR', 'Estudante', 0, 'Letras', '1', 0, 123, 'Ok');

COMMIT;


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** pplibrography`.`tb_fornecedores`
-- SQLINES DEMO *** ------------------------------------
START TRANSACTION;
SET SCHEMA 'applibrography';
INSERT INTO applibrography.tb_fornecedores (id, nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) VALUES (1, 'Fornecedor Teste', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** pplibrography`.`tb_funcionarios`
-- SQLINES DEMO *** ------------------------------------
START TRANSACTION;
SET SCHEMA 'applibrography';
INSERT INTO applibrography.tb_funcionarios (id, nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) VALUES (1, 'Administrador', NULL, NULL, 'admin@admin', 'admin', 'Administrador', 'Administrador', NULL, NULL, '84570-000', NULL, NULL, NULL, NULL, 'Mallet', 'PR');

COMMIT;


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** pplibrography`.`tb_livros`
-- SQLINES DEMO *** ------------------------------------
START TRANSACTION;
SET SCHEMA 'applibrography';
INSERT INTO applibrography.tb_livros (id, titulo, autor, editora, isbn, ano, serie, edicao, idioma, tb_fornecedores_id, piso, corredor, posicao, secao, disponibilidade, observacoes, is_emprestado) VALUES (1, 'Alice no Pis das maravilhas', 'Lewis Carrol', 'Nope', '1231231231231', '1894', '1', '1', 'Portugues', 1, NULL, NULL, NULL, 'Literatura Universal', '5', 'ok', 0);

COMMIT;


-- SQLINES DEMO *** ------------------------------------
-- SQLINES DEMO *** pplibrography`.`tb_opcoes`
-- SQLINES DEMO *** ------------------------------------
START TRANSACTION;
SET SCHEMA 'applibrography';
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (1, 'piso', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (2, 'corredor', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (3, 'secao', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (4, 'posicao', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (5, 'disponibilidade', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (6, 'printerurl', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (7, 'instituicaonome', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (8, 'instiuicaoendereco', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (17, 'piso 1', 1);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (20, 'corredor 1', 2);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (21, '1A', 3);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (22, 'Literatura Universal', 4);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (13, '0', 5);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (14, '3', 5);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (15, 'Biblioteca de Hogwarts', 7);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (16, 'Rua Epsilon, 888, Cidade Imaginaria, LA ', 8);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (9, 'tiposdeusuarios', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (18, 'Estudante', 9);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (19, 'Professor', 9);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (10, 'server url', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (11, 'libraryname', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (12, 'libraryAddress', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (23, '127.0.0.1', 12);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (24, 'Nome da Biblioteca', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (25, 'valor_multa', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (26, '300', 25);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (27, 'MsgReceipt', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (28, 'Obrigado!!', 27);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (29, 'theme', NULL);
INSERT INTO applibrography.tb_opcoes (id, data, parentid) VALUES (30, 'dark', 29);

COMMIT;

