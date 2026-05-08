package acc.br.pessoa_jwt.controller;

import acc.br.pessoa_jwt.domain.model.Pessoa;
import acc.br.pessoa_jwt.domain.dto.CreatePessoaDTO;
import acc.br.pessoa_jwt.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Pessoa> create(@RequestBody CreatePessoaDTO dto) {
        Pessoa p = Pessoa.builder()
                .age(dto.age())
                .name(dto.name())
                .email(dto.email())
                .build();

        Pessoa saved = service.create(p);
        return ResponseEntity.created(URI.create("/pessoas/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa p) {
        return ResponseEntity.ok(service.update(id, p));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

