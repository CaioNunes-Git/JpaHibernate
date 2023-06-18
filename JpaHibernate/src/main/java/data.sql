-- Consultas

--A)
    select m.*
    from medicos m
    inner join especialidade_medico em on m.id = em.medico_id
    inner join especialidade e on e.id = em.especialidade_id
    where e.nome = ?;

--B)
    select uf.sigla, count(m.id) as qtd_funcionario
    from medicos m
    inner join uf on m.uf = uf.sigla
    group by uf.sigla;

--C)
    select e.*
    from especialidade e
    left join especialidade_medico em on m.id is null;