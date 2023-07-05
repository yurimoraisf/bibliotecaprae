package biblioteca.prae.api.domain.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLivro(
        @NotBlank
        String titulo,
        @NotBlank
        String autor,
        @NotNull
        Genero genero,
        String foto,
        @NotNull
        int quantidade
) {
}
