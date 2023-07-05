package biblioteca.prae.api.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAdicionarFavorito(
        @NotNull Long usuarioId,
        @NotNull
        Long livroId) {
}
