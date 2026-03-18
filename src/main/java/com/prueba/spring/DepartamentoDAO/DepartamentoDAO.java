package com.prueba.spring.DepartamentoDAO;

import com.prueba.spring.Entity.Departamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartamentoDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Departamento> consultarDepartamentos() {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CONSULTAR_DEPARTAMENTOS");

        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();

        List<Object[]> rows = query.getResultList();
        List<Departamento> lista = new ArrayList<>();

        for (Object[] row : rows) {
            Departamento dep = new Departamento();
            dep.setDepartamentoid(((Number) row[0]).intValue());
            dep.setDescripcion((String) row[1]);
            lista.add(dep);
        }
        return lista;

    }

}
