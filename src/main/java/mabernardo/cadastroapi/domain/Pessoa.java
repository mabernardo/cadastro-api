package mabernardo.cadastroapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import mabernardo.cadastroapi.domain.enums.StatusCadastro;
/**
 * Entidade de Pessoas.
 * @author Marcio Bernardo
 */
@ApiModel(description = "Entidade de Pessoas.")
@Entity
@Table(name = "pessoa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotNull
    @Size(min = 2, max = 60)
    @Column(name = "sobrenome", length = 60, nullable = false)
    private String sobrenome;

    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCadastro status;

    @OneToOne
    @JoinColumn(unique = true)
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Telefone> telefones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Pessoa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Pessoa sobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
        return this;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Pessoa nascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public StatusCadastro getStatus() {
        return status;
    }

    public Pessoa status(StatusCadastro status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusCadastro status) {
        this.status = status;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Pessoa endereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Pessoa telefones(Set<Telefone> telefones) {
        this.telefones = telefones;
        return this;
    }

    public Pessoa addTelefones(Telefone telefone) {
        this.telefones.add(telefone);
        telefone.setPessoa(this);
        return this;
    }

    public Pessoa removeTelefones(Telefone telefone) {
        this.telefones.remove(telefone);
        telefone.setPessoa(null);
        return this;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        if (pessoa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pessoa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pessoa{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", sobrenome='" + getSobrenome() + "'" +
            ", nascimento='" + getNascimento() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
