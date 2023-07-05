package biblioteca.prae.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String username);

    @Query("SELECT u.favoritos FROM Usuario u WHERE u.id = :id")
    List<Long> findFavoritosById(@Param("id") Long id);

    Optional<Usuario> findById(Long id);
}
