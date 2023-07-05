package biblioteca.prae.api.domain.historico;

import biblioteca.prae.api.domain.livro.Livro;
import biblioteca.prae.api.domain.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "troca")
@Entity(name = "Troca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Troca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livroEntrada;

    @ManyToOne(optional = true)
    private Livro livroSaida;

    private LocalDateTime data;

    public Troca(Usuario usuario, Livro livroEntrada, Livro livroSaida, LocalDateTime data) {
        this.usuario = usuario;
        this.livroEntrada = livroEntrada;
        this.livroSaida = livroSaida;
        this.data = data;
    }

    public boolean usuarioRetirouLivro() {
        return livroSaida != null;
    }
}
