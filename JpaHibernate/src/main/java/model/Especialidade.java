package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false, length = 50)
    private String nome;
    @ManyToMany(mappedBy = "especialidades")
    private List<Medico> medicos = new ArrayList<>();

    public Especialidade(){}

    public Especialidade(Long codigo, String nome, List<Medico> medicos) {
        this.codigo = codigo;
        this.nome = nome;
        this.medicos.addAll(medicos);
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}
