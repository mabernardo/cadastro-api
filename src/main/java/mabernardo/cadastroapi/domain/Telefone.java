package mabernardo.cadastroapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import mabernardo.cadastroapi.domain.enums.TipoTelefone;

/**
 * Entidade de telefones de pessoas.
 * @author Marcio Bernardo
 */
@ApiModel(description = "Entidade de telefones de pessoas. @author Marcio Bernardo")
@Entity
@Table(name = "telefone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 3)
    @Column(name = "ddd", length = 3, nullable = false)
    private String ddd;

    @NotNull
    @Size(max = 10)
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoTelefone tipo;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    @JsonIgnore
    private Pessoa pessoa;
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public Telefone ddd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public Telefone numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public Telefone tipo(TipoTelefone tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }
/*
    public Pessoa getPessoa() {
        return pessoa;
    }

    public Telefone pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Telefone telefone = (Telefone) o;
        if (telefone.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), telefone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Telefone{" +
            "id=" + getId() +
            ", ddd='" + getDdd() + "'" +
            ", numero='" + getNumero() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
