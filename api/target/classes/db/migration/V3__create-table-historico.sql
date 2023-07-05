CREATE TABLE troca (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    livro_entrada_id BIGINT NOT NULL,
    livro_saida_id BIGINT,
    data DATETIME NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (livro_entrada_id) REFERENCES livros(id),
    FOREIGN KEY (livro_saida_id) REFERENCES livros(id)
);
