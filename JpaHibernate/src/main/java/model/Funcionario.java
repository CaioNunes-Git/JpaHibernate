package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricula","numero_rg","orgao_expedidor_rg"
        ,"uf_rg"})
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "cargo", discriminatorType = DiscriminatorType.STRING)
public class Funcionario {
    @Id
    private Integer matricula;
    @Column(nullable = false)
    private Integer numeroRg;
    @Column(length = 40, nullable = false)
    private String orgaoExpedidorRg;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "uf_rg", referencedColumnName = "sigla")
    private Uf ufRg;
    @Column(nullable = false)
    private LocalDate dataAdmissao;
    @Column(length = 255)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "telefone_id")
    private List<Telefone> telefones = new ArrayList<>();

    public Funcionario(){}

    public Funcionario(Integer matricula, Integer numeroRg, String orgaoExpedidorRg, Uf uf, LocalDate dataAdmissao, String email, List<Telefone> telefones) {
        this.matricula = matricula;
        this.numeroRg = numeroRg;
        this.orgaoExpedidorRg = orgaoExpedidorRg;
        this.ufRg = uf;
        this.dataAdmissao = dataAdmissao;
        this.email = email;
        this.telefones.addAll(telefones);
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getNumeroRg() {
        return numeroRg;
    }

    public void setNumeroRg(Integer numeroRg) {
        this.numeroRg = numeroRg;
    }

    public String getOrgaoExpedidorRg() {
        return orgaoExpedidorRg;
    }

    public void setOrgaoExpedidorRg(String orgaoExpedidorRg) {
        this.orgaoExpedidorRg = orgaoExpedidorRg;
    }

    public Uf getUfRg() {
        return ufRg;
    }

    public void setUfRg(Uf ufRg) {
        this.ufRg = ufRg;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefone(Telefone telefone) {
        telefones.add(telefone);
    }
}
