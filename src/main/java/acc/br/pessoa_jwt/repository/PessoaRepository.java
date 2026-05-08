package acc.br.pessoa_jwt.repository;

import acc.br.pessoa_jwt.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}

