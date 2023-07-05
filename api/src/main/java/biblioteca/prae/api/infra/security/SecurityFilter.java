package biblioteca.prae.api.infra.security;

import biblioteca.prae.api.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//revisar essa parte dos filtros
@Component //para representar um componente generico
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var email = repository.findByEmail(subject);

            //aqui eu vou autenticar o token pra nao precisar ficar fazendo o login toda hora
            var authenctication = new UsernamePasswordAuthenticationToken(email, null, email.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenctication);
        }

        //para chamar o prox filtro
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        //se nao vem cabecalho eu so retorno null
        return null;
    }
}
