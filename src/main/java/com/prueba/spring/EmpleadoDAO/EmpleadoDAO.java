package com.prueba.spring.EmpleadoDAO;

import com.prueba.spring.Entity.Departamento;
import com.prueba.spring.Entity.Empleado;
import com.prueba.spring.Entity.Puesto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Empleado> consultarEmpleados() {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CONSULTAR_EMPLEADOS");

        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
        query.execute();

        List<Object[]> rows = query.getResultList();
        List<Empleado> empleados = new ArrayList<>();

        for (Object[] row : rows) {
            Empleado empleado = new Empleado();
            empleado.setEmpleadoid(((Number) row[0]).intValue());
            empleado.setNombre((String) row[1]);

            Puesto puesto = new Puesto();

            puesto.setPuestoid(((Number) row[2]).intValue());
            puesto.setDescripcion((String) row[3]);
            empleado.setPuesto(puesto);

            Departamento departamento = new Departamento();
            departamento.setDepartamentoid(((Number) row[4]).intValue());
            departamento.setDescripcion((String) row[5]);
            empleado.setDepartamento(departamento);

            empleados.add(empleado);

        }
        return empleados;
    }

    public List<Empleado> buscarEmpleado(String nombre) {

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_BUSCAR_EMPLEADO");
        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_nombre", nombre);
        query.execute();

        List<Object[]> rows = query.getResultList();
        List<Empleado> empleados = new ArrayList<>();

        for (Object[] row : rows) {
            Empleado empleado = new Empleado();
            empleado.setEmpleadoid(((Number) row[0]).intValue());
            empleado.setNombre((String) row[1]);

            Puesto puesto = new Puesto();
            puesto.setPuestoid(((Number) row[2]).intValue());
            puesto.setDescripcion((String) row[3]);
            empleado.setPuesto(puesto);

            Departamento departamento = new Departamento();
            departamento.setDepartamentoid(((Number) row[4]).intValue());
            departamento.setDescripcion((String) row[5]);
            empleado.setDepartamento(departamento);
            empleados.add(empleado);
        }
        return empleados;
    }

    public Integer insertarEmpleado(String nombre, Integer puestoid, Integer departamentoid) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_INSERTAR_EMPLEADO");

        query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_puestoid", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_departamentoid", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_empleadoid", Integer.class, ParameterMode.OUT);

        query.setParameter("p_nombre", nombre);
        query.setParameter("p_puestoid", puestoid);
        query.setParameter("p_departamentoid", departamentoid);

        query.execute();

        return ((Number) query.getOutputParameterValue("p_empleadoid")).intValue();
    }

    public Integer eliminarEmpleado(Integer empleadoId) {
    StoredProcedureQuery query = entityManager
        .createStoredProcedureQuery("SP_ELIMINAR_EMPLEADO");

    query.registerStoredProcedureParameter("p_empleadoID", Integer.class, ParameterMode.IN);      // ← ID en mayúsculas
    query.registerStoredProcedureParameter("p_filas_afectadas", Integer.class, ParameterMode.OUT);

    query.setParameter("p_empleadoID", empleadoId);

    query.execute();

    return ((Number) query.getOutputParameterValue("p_filas_afectadas")).intValue();
}

}
