package com.prueba.spring.PuestoDAO;

import com.prueba.spring.Entity.Puesto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PuestoDAO {

     @Autowired
    private EntityManager entityManager;

    public List<Puesto> consultarPuesto() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CONSULTAR_PUESTOS");
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();

        List<Object[]> rows = query.getResultList();
        List<Puesto> lista = new ArrayList<>();

        for (Object[] row : rows) {
            Puesto puesto = new Puesto();
            puesto.setPuestoid(((Number) row[0]).intValue());
            puesto.setDescripcion((String) row[1]);
            lista.add(puesto);
        }
        return lista;
    }

}
