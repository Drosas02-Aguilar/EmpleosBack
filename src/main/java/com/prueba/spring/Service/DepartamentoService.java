package com.prueba.spring.Service;

import com.prueba.spring.DepartamentoDAO.DepartamentoDAO;
import com.prueba.spring.DepartamentoDAO.IDepartamentoDAO;
import com.prueba.spring.Entity.Departamento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService implements IDepartamentoDAO{

   @Autowired
   DepartamentoDAO departamentoDAO;
    
    @Override
    public List<Departamento> consultarDepartamento() {
        return departamentoDAO.consultarDepartamentos();
    }
    
}
