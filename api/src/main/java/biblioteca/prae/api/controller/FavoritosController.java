package biblioteca.prae.api.controller;

import biblioteca.prae.api.domain.usuario.DadosAdicionarFavorito;
import biblioteca.prae.api.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/favoritos")
@SecurityRequirement(name = "bearer-key")

public class FavoritosController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Long>> getFavoritos(@PathVariable Long usuarioId) {
        var favoritos = usuarioRepository.findFavoritosById(usuarioId);
        return ResponseEntity.ok(favoritos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> addFavorito(@RequestBody DadosAdicionarFavorito dto) {
        var usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        usuario.getFavoritos().add(dto.livroId());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{livroId}")
    public ResponseEntity<Void> excluir(@PathVariable Long usuarioId, @PathVariable Long livroId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        usuario.getFavoritos().remove(livroId);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
