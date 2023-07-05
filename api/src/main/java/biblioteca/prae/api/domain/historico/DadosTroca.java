package biblioteca.prae.api.domain.historico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

//dados que vou receber do front
public record DadosTroca(
        @NotNull
        String emailUsuario,
        @NotNull
        Long livroEntradaId,
        Long livroSaidaId,
        LocalDateTime data
) {
}