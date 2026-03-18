package com.prueba.spring.RestController;

import com.prueba.spring.Entity.Departamento;
import com.prueba.spring.Entity.Empleado;
import com.prueba.spring.Entity.Puesto;
import com.prueba.spring.Entity.ServiceResult;
import com.prueba.spring.Service.DepartamentoService;
import com.prueba.spring.Service.EmpleadoService;
import com.prueba.spring.Service.PuestoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/catalogos")
public class CatalogoRestController {
    
   @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private PuestoService puestoService;
    
    
    @GetMapping("/empleados")
    public ResponseEntity<ServiceResult<Empleado>> listarEmpleados() {
        ServiceResult<Empleado> serviceResult = new ServiceResult<>();
        try {
            List<Empleado> empleados = empleadoService.consultarEmpleados();
            if (!empleados.isEmpty()) {
                serviceResult.objects = empleados;
                serviceResult.status = 200;
                serviceResult.correct = true;
            } else {
                serviceResult.status = 404;
                serviceResult.correct = false;
                serviceResult.ErrorMessage = "No se encontraron empleados.";
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.correct = false;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
            serviceResult.ex = ex;
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }
    
 @GetMapping("/buscar")
    public ResponseEntity<ServiceResult<Empleado>> buscarEmpleado(@RequestParam String nombre) {
        ServiceResult<Empleado> serviceResult = new ServiceResult<>();
        try {
            List<Empleado> empleados = empleadoService.buscarEmpleado(nombre);
            if (!empleados.isEmpty()) {
                serviceResult.objects = empleados;
                serviceResult.status = 200;
                serviceResult.correct = true;
            } else {
                serviceResult.status = 404;
                serviceResult.correct = false;
                serviceResult.ErrorMessage = "No se encontraron empleados con ese nombre.";
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.correct = false;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
            serviceResult.ex = ex;
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    
    @GetMapping("/departamentos")
    public ResponseEntity<ServiceResult<Departamento>> listarDepartamentos() {
        ServiceResult<Departamento> ServiceResult = new ServiceResult<>();
        try {
            List<Departamento> departamentos = departamentoService.consultarDepartamento();
            if (!departamentos.isEmpty()) {
                ServiceResult.objects = departamentos;
                ServiceResult.status = 200;
                ServiceResult.correct = true;
            } else {
                ServiceResult.status = 404;
                ServiceResult.correct = false;
                ServiceResult.ErrorMessage = "No se encontraron departamentos.";
            }
        } catch (Exception ex) {
            ServiceResult.status = 500;
            ServiceResult.correct = false;
            ServiceResult.ErrorMessage = ex.getLocalizedMessage();
            ServiceResult.ex = ex;
        }
        return ResponseEntity.status(ServiceResult.status).body(ServiceResult);
    }

    @GetMapping("/puestos")
    public ResponseEntity<ServiceResult<Puesto>> listarPuestos() {
        ServiceResult<Puesto> ServiceResult = new ServiceResult<>();
        try {
            List<Puesto> puestos = puestoService.consultarPuesto();
            if (!puestos.isEmpty()) {
                ServiceResult.objects = puestos;
                ServiceResult.status = 200;
                ServiceResult.correct = true;
            } else {
                ServiceResult.status = 404;
                ServiceResult.correct = false;
                ServiceResult.ErrorMessage = "No se encontraron puestos.";
            }
        } catch (Exception ex) {
            ServiceResult.status = 500;
            ServiceResult.correct = false;
            ServiceResult.ErrorMessage = ex.getLocalizedMessage();
            ServiceResult.ex = ex;
        }
        return ResponseEntity.status(ServiceResult.status).body(ServiceResult);
    }
    
}
