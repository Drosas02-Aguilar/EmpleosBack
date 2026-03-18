package com.prueba.spring.EmpleadoDAO;

import com.prueba.spring.Entity.Empleado;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IEmpleado {

    List<Empleado> consultarEmpleados();

    List<Empleado> buscarEmpleado(String nombre);

    Integer insertarEmpleado(String nombre, Integer puestoId, Integer departamentoId);

    Integer eliminarEmpleado(Integer empleadoId);

    void cargarEmpleadosDesdeArchivo(MultipartFile archivo);
}
