package com.prueba.spring.DepartamentoDAO;

import com.prueba.spring.Entity.Departamento;
import java.util.List;

public interface IDepartamentoDAO {
    List<Departamento> consultarDepartamento();
}
