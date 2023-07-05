package biblioteca.prae.api.domain.historico;

public record DadosDetalhamentoTroca(Long id, Long usuarioID, String email, Long livroEntradaID, Long livroSaidaID) {
    public DadosDetalhamentoTroca(Troca troca) {
        this(
                troca.getId(),
                troca.getUsuario().getId(),
                troca.getUsuario().getEmail(),
                troca.getLivroEntrada().getId(),
                troca.getLivroSaida() != null ? troca.getLivroSaida().getId() : null
        );
    }
}
