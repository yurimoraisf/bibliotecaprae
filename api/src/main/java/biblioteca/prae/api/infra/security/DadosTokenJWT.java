package biblioteca.prae.api.infra.security;

//para devolver o token como um JSON
public record DadosTokenJWT(String token, String email, String nome, Long id, Long pontuacao, int creditos, int classificacao, Boolean is_admin) {
}
