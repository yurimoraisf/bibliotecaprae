package biblioteca.prae.api.controller;

import biblioteca.prae.api.domain.livro.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("livros")
@SecurityRequirement(name = "bearer-key")

public class LivroController {
    @Autowired
    private LivroRepository repository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLivro dados, UriComponentsBuilder uriBuilder) {
        var livro = new Livro(dados);
        repository.save(livro); //o save eh como se fosse um INSERT no bd
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoLivro(livro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemLivro>> listar(Pageable paginacao) {
        var page = repository.findAllByDisponivelTrue(paginacao).map(DadosListagemLivro::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLivro dados) {
        var livro = repository.getReferenceById(dados.id());
        livro.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")//o ID eh recebido como parametro dinamico, vem pela URL
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {//@PathVariable serve para dizer que é o parametro que vem da URL
        var livro = repository.getReferenceById(id);
        livro.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var livro = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }
}


