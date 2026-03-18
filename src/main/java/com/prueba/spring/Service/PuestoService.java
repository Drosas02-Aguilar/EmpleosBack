package com.prueba.spring.Service;

import com.prueba.spring.Entity.Puesto;
import com.prueba.spring.PuestoDAO.IPuestoDAO;
import com.prueba.spring.PuestoDAO.PuestoDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuestoService implements IPuestoDAO {

    @Autowired
    PuestoDAO puestoDAO;

    @Override
    public List<Puesto> consultarPuesto() {
        return puestoDAO.consultarPuesto();
    }

}
