package biblioteca.prae.api.domain.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Page<Livro> findAllByDisponivelTrue(Pageable paginacao);

    Optional<Livro> findById(Long id);
}

