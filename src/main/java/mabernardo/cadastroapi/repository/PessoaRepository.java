package mabernardo.cadastroapi.repository;

import mabernardo.cadastroapi.domain.Pessoa;
import mabernardo.cadastroapi.domain.enums.StatusCadastro;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository para a entidade Pessoa.
 */
@SuppressWarnings("unused")
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findOneByCpf(String cpf);

    List<Pessoa> findAllByStatus(StatusCadastro status);

    List<Pessoa> findAll(Specification<Pessoa> spec);
}
