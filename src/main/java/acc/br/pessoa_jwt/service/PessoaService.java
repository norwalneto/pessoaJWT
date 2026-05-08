package acc.br.pessoa_jwt.service;

import acc.br.pessoa_jwt.domain.model.Pessoa;
import acc.br.pessoa_jwt.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public Pessoa create(Pessoa p) {
        p.setId(null);
        return repository.save(p);
    }

    public Pessoa update(Long id, Pessoa p) {
        Pessoa existing = findById(id);
        existing.setName(p.getName());
        existing.setAge(p.getAge());
        existing.setEmail(p.getEmail());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

