import jakarta.persistence.TypedQuery;
import model.Especialidade;
import model.Funcionario;
import model.Medico;
import model.Uf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.JpaUtil;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        CadastrarDados();
        buscarMedicosPorEspecialidade("Especialidade 1");
        contarFuncionariosPorUF();
        buscarEspecialidadesSemMedico();
    }

    public static void CadastrarDados(){
        Uf uf1 = new Uf();
        uf1.setNome("BA");

        Uf uf2 = new Uf();
        uf2.setNome("SP");

        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("Funcionário@gmail.com");
        funcionario.setMatricula(123);
        funcionario.setNumeroRg(123);
        funcionario.setOrgaoExpedidorRg("SSP");
        funcionario.setDataAdmissao(java.time.LocalDate.now());
        funcionario.setUfRg(uf1);

        Medico medico1 = new Medico();
        medico1.setEmail("medico@gmail.com");
        medico1.setMatricula(1221343);
        medico1.setNumeroRg(1231234);
        medico1.setOrgaoExpedidorRg("SSP");
        medico1.setDataAdmissao(java.time.LocalDate.now());
        medico1.setUfRg(uf1);
        medico1.setNumeroCrm("12312312");
        medico1.setUfCrm(uf1);

        Medico medico2 = new Medico();
        medico2.setEmail("medico2@gmail.com");
        medico2.setMatricula(1231);
        medico2.setNumeroRg(3456);
        medico2.setOrgaoExpedidorRg("SSP");
        medico2.setDataAdmissao(java.time.LocalDate.now());
        medico2.setUfRg(uf1);
        medico2.setNumeroCrm("1231231112");
        medico2.setUfCrm(uf1);

        Especialidade especialidade1 = new Especialidade();
        especialidade1.setNome("Especialidade 1");

        Especialidade especialidade2 = new Especialidade();
        especialidade2.setNome("SEm nome msm");

        Especialidade especialidade3 = new Especialidade();
        especialidade1.setNome("essa é a 3");

        Especialidade especialidade4 = new Especialidade();
        especialidade2.setNome("4");
        Especialidade especialidade5 = new Especialidade();
        especialidade2.setNome("cabo");

        medico1.getEspecialidades().add(especialidade1);
        medico1.getEspecialidades().add(especialidade2);

        medico2.getEspecialidades().add(especialidade1);
        medico2.getEspecialidades().add(especialidade2);

        var factory = JpaUtil.getEntityManager();
        factory.getTransaction().begin();

        factory.persist(funcionario);
        factory.persist(medico1);
        factory.persist(medico2);
        factory.persist(especialidade1);
        factory.persist(especialidade2);
        factory.persist(especialidade3);
        factory.persist(especialidade4);
        factory.persist(especialidade5);

        factory.persist(uf1);
        factory.persist(uf2);

        factory.getTransaction().commit();

        factory.close();
        factory.close();
    }

    public static void buscarMedicosPorEspecialidade(String nomeEspecialidade) {
        var em = JpaUtil.getEntityManager();

        String jpql = "select m.email,m.id" +
                "    from Medico m" +
                "    inner join especialidade_medico em on m.id = em.medico_id" +
                "    inner join Especialidade e on e.id = em.especialidade_id" +
                "    where e.nome = :nomeEspecialidade";
        TypedQuery<Medico> query = em.createQuery(jpql, Medico.class);
        query.setParameter("nomeEspecialidade", nomeEspecialidade);

        List<Medico> medicos = query.getResultList();

        for (Medico medico : medicos) {
            System.out.println(medico);
        }

        em.close();
    }

    public static void contarFuncionariosPorUF() {
        var em = JpaUtil.getEntityManager();
        String jpql = "SELECT NEW Funcionario (f.ufRg.sigla, COUNT(f)) FROM Funcionario f GROUP BY f.ufRg.sigla";
        TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);

        List<Funcionario> resultados = query.getResultList();

        for (var dto : resultados) {
            System.out.println("UF: " + dto.getUfRg().getSigla() + ", Quantidade de Funcionários: " + resultados.stream().count());
        }

        em.close();
    }

    public static void buscarEspecialidadesSemMedico() {
        var em = JpaUtil.getEntityManager();

        String jpql = "SELECT e FROM Especialidade e WHERE e.medicos IS EMPTY";
        TypedQuery<Especialidade> query = em.createQuery(jpql, Especialidade.class);

        List<Especialidade> especialidades = query.getResultList();

        for (Especialidade especialidade : especialidades) {
            System.out.println(especialidade);
        }

        em.close();
    }

}
