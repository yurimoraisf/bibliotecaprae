package biblioteca.prae.api.domain.livro;

public record DadosListagemLivro(Long id, String titulo, String autor, Genero genero) {
    public DadosListagemLivro(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getGenero());
    }
}