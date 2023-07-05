package biblioteca.prae.api.domain.livro;

public record DadosDetalhamentoLivro(Long id, String titulo, String autor, Genero genero, int quantidade) {
    public DadosDetalhamentoLivro(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getGenero(), livro.getQuantidade());
    }
}