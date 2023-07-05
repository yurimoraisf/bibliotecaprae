package biblioteca.prae.api.domain.usuario;

public record DadosRankingUsario(String email, String nome, long pontuacao, int posicao) {
    public DadosRankingUsario(Usuario usuario, int posicao) {
        this(usuario.getEmail(), usuario.getNome(), usuario.getPontuacao(), posicao);
    }
}