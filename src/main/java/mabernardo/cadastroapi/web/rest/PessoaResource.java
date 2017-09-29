package mabernardo.cadastroapi.web.rest;

import mabernardo.cadastroapi.domain.Pessoa;
import mabernardo.cadastroapi.domain.enums.StatusCadastro;
import mabernardo.cadastroapi.repository.PessoaRepository;
import mabernardo.cadastroapi.web.rest.util.HeaderUtil;
import mabernardo.cadastroapi.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller para gerenciar Pessoas.
 */
@RestController
@RequestMapping("/api")
public class PessoaResource {

    private final Logger log = LoggerFactory.getLogger(PessoaResource.class);

    private static final String ENTITY_NAME = "pessoa";

    private final PessoaRepository pessoaRepository;

    public PessoaResource(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * POST  /pessoas : Cria uma nova pessoa.
     *
     * @param pessoa a pessoa a ser criada.
     * @return ResponseEntity com status 201 (Created) e os dados da nova pessoa,
     * ou status 400 (Bad Request) se a pessoa já tiver um ID
     * @throws URISyntaxException se a sintaxe da URI estiver incorreta
     */
    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody Pessoa pessoa) throws URISyntaxException {
        log.debug("REST request to save Pessoa : {}", pessoa);
        if (pessoa.getId() != null) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(
                            ENTITY_NAME,
                            "idexists",
                            "Uma nova pessoa n\u00e3o pode possuir Id."))
                    .body(null);
        }
        Pessoa result = pessoaRepository.save(pessoa);
        return ResponseEntity.created(new URI("/api/pessoas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT /pessoas : Atualiza uma pessoa existente.
     *
     * @param pessoa a pessoa a atualizar
     * @return ResponseEntity com status 200 (OK) e com os dados da pessoa atualizada,
     * ou com status 400 (Bad Request) se a pessoa não for válida,
     * ou com status 500 (Internal Server Error) se não for possível atualizar a pessoa
     * @throws URISyntaxException se a sintaxe da URI estiver incorreta
     */
    @PutMapping("/pessoas")
    public ResponseEntity<Pessoa> updatePessoa(@Valid @RequestBody Pessoa pessoa) throws URISyntaxException {
        log.debug("REST request to update Pessoa : {}", pessoa);
        if (pessoa.getId() == null) {
            return createPessoa(pessoa);
        }
        Pessoa result = pessoaRepository.save(pessoa);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pessoa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pessoas : busca pessoas de acordo com o status, se nenhum for passado, busca todas com status Ativo.
     *
     * @param status status cadastral da pessoa
     * @return ResponseEntity com status 200 (OK) e a lista de pessoas no corpo
     */
    @GetMapping("/pessoas")
    public List<Pessoa> getPessoas(@RequestParam(required = false) String status) {
        log.debug("REST request to get Pessoas");
        StatusCadastro sc = status == null ? StatusCadastro.ATIVO : StatusCadastro.valueOf(status);
        return pessoaRepository.findAllByStatus(sc);
    }

    /**
     * GET  /pessoas : busca pessoa com o CPF informado.
     *
     * @param cpf CPF da pessoa
     * @return ResponseEntity com status 200 (OK) e com os dados da pessoa,
     * ou status 404 (Not Found) se não for encontrada
     */
    @GetMapping("/pessoas/search")
    public ResponseEntity<Pessoa> getPessoaPorCPF(@RequestParam String cpf) {
        log.debug("REST request to get Pessoas by CPF");
        Pessoa pessoa = pessoaRepository.findOneByCpf(cpf);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pessoa));
    }

    /**
     * GET  /pessoas/:id : obtém a pessoa com o id informado.
     *
     * @param id o id da pessoa a ser recuperada
     * @return ResponseEntity com status 200 (OK) e com os dados da pessoa,
     * ou status 404 (Not Found) se não for encontrada
     */
    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
        log.debug("REST request to get Pessoa : {}", id);
        Pessoa pessoa = pessoaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pessoa));
    }

    /**
     * DELETE  /pessoas/:id : delete a pessoa com o id informado.
     *
     * @param id o id da pessoa a ser deletada
     * @return ResponseEntity com status 200 (OK)
     */
    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        log.debug("REST request to delete Pessoa : {}", id);
        pessoaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
