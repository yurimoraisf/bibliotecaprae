CREATE TABLE usuarios (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE,
  senha VARCHAR(255) NOT NULL,
  creditos INT NOT NULL,
  pontuacao INT NOT NULL,
  is_admin BOOLEAN,
  favoritos JSON
);
