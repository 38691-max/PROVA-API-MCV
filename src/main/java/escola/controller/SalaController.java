package escola.controller;

import escola.model.Sala;
import escola.model.SalaRequest;
import escola.service.SalaService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService service;

    public SalaController(SalaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Sala> cadastrar(@Valid @RequestBody SalaRequest dados) {
        Sala sala = service.cadastrar(dados);
        URI local = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(sala.getId()).toUri();
        return ResponseEntity.created(local).body(sala);
    }

    @GetMapping
    public ResponseEntity<List<Sala>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PutMapping("/{id}/reservar")
    public ResponseEntity<Sala> reservar(@PathVariable Long id) {
        return ResponseEntity.ok(service.reservar(id));
    }

    @PutMapping("/{id}/liberar")
    public ResponseEntity<Sala> liberar(@PathVariable Long id) {
        return ResponseEntity.ok(service.liberar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}