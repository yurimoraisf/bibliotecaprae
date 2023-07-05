package biblioteca.prae.api.domain.livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "livros")
@Entity(name = "Livro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")//pk

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String foto;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private boolean disponivel;
    private int quantidade;

    public Livro(DadosCadastroLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor();
        this.genero = dados.genero();
        this.foto = dados.foto();
        this.disponivel = true;
        this.quantidade = dados.quantidade();
    }

    public void atualizarInformacoes(DadosAtualizacaoLivro dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.genero() != null) {
            this.genero = dados.genero();
        }
    }

    public void excluir() {
        this.disponivel = false;
    }
}