package com.prueba.spring.Service;

import com.prueba.spring.EmpleadoDAO.EmpleadoDAO;
import com.prueba.spring.EmpleadoDAO.IEmpleado;
import com.prueba.spring.Entity.Empleado;
import com.prueba.spring.Entity.ServiceResult;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmpleadoService implements IEmpleado {

    ServiceResult serviceResult = new ServiceResult();

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Override
    public List<Empleado> consultarEmpleados() {
        return empleadoDAO.consultarEmpleados();
    }

    @Override
    public List<Empleado> buscarEmpleado(String nombre) {
        return empleadoDAO.buscarEmpleado(nombre);
    }

    @Override
    @Transactional
    public Integer insertarEmpleado(String nombre, Integer puestoId, Integer departamentoId) {
        return empleadoDAO.insertarEmpleado(nombre, puestoId, departamentoId);
    }

    @Override
    public Integer eliminarEmpleado(Integer empleadoId) {
        return empleadoDAO.eliminarEmpleado(empleadoId);
    }

    @Override
    public void cargarEmpleadosDesdeArchivo(MultipartFile archivo) {
 try {
        List<String> lineas = new BufferedReader(
            new InputStreamReader(archivo.getInputStream()))
            .lines()
            .collect(Collectors.toList());

        int i = 0;
        while (i < lineas.size()) {
            if (lineas.get(i).trim().isEmpty()) {
                i++;
                continue;
            }

            String empleadoIdStr     = lineas.get(i).trim();  
            String nombre            = lineas.get(i + 1).trim();
            Integer departamentoId   = Integer.parseInt(lineas.get(i + 2).trim());
            Integer puestoId         = Integer.parseInt(lineas.get(i + 3).trim());

            empleadoDAO.insertarEmpleado(nombre, puestoId, departamentoId);

            i += 4;
        }

    } catch (Exception ex) {
serviceResult.correct = false;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
            serviceResult.ex = ex;    }
}

    

}
