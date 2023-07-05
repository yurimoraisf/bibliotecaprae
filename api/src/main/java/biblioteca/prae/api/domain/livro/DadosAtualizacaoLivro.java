package biblioteca.prae.api.domain.livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLivro(
        @NotNull
        Long id,
        String titulo,
        String autor,
        Genero genero
) {
}
