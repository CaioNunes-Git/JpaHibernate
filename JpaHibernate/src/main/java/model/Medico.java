package model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medico", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero_crm", "uf_crm"})
})
@DiscriminatorValue("medico")
public class Medico extends Funcionario {
    @Column(name = "numero_crm", nullable = false, length = 11)
    private String numeroCrm;
    @Column(name = "uf_crm", nullable = false)
    @OneToOne
    private Uf ufCrm;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medico_especialidade",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
    private List<Especialidade> especialidades = new ArrayList<>();

    public Medico(){}

    public Medico(Integer matricula, Integer numeroRg, String orgaoExpedidorRg, Uf uf, LocalDate dataAdmissao, String email, List<Telefone> telefones, String numeroCrm, Uf ufCrm, List<Especialidade> especialidades) {
        super(matricula, numeroRg, orgaoExpedidorRg, uf, dataAdmissao, email, telefones);
        this.numeroCrm = numeroCrm;
        this.ufCrm = ufCrm;
        this.especialidades.addAll(especialidades);
    }

    public String getNumeroCrm() {
        return numeroCrm;
    }

    public void setNumeroCrm(String numeroCrm) {
        this.numeroCrm = numeroCrm;
    }

    public Uf getUfCrm() {
        return ufCrm;
    }

    public void setUfCrm(Uf ufCrm) {
        this.ufCrm = ufCrm;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}
