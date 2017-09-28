package mabernardo.cadastroapi.repository;

import mabernardo.cadastroapi.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository para a entidade Authority.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
