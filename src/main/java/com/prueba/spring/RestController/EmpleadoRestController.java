package com.prueba.spring.RestController;

import com.prueba.spring.Entity.Empleado;
import com.prueba.spring.Entity.ServiceResult;
import com.prueba.spring.Service.EmpleadoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/empleados")
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/insertar")
public ResponseEntity<ServiceResult<Empleado>> insertarEmpleado(@RequestBody Map<String, Object> request) {
    ServiceResult<Empleado> serviceResult = new ServiceResult<>();
    try {
        String nombre          = (String) request.get("nombre");
        Integer puestoId       = ((Number) request.get("puestoId")).intValue();
        Integer departamentoId = ((Number) request.get("departamentoId")).intValue();

        Integer empleadoId = empleadoService.insertarEmpleado(nombre, puestoId, departamentoId);

        if (empleadoId != null && empleadoId > 0) {
            List<Empleado> empleados = empleadoService.buscarEmpleado(nombre);
            Empleado empInsertado = empleados.stream()
                .filter(e -> e.getEmpleadoid() == empleadoId)
                .findFirst()
                .orElse(null);

            serviceResult.status = 200;
            serviceResult.correct = true;
            serviceResult.object = empInsertado;
        } else {
            serviceResult.status = 400;
            serviceResult.correct = false;
            serviceResult.ErrorMessage = "No se pudo insertar el empleado.";
        }
    } catch (Exception ex) {
        serviceResult.status = 500;
        serviceResult.correct = false;
        serviceResult.ErrorMessage = ex.getLocalizedMessage();
        serviceResult.ex = ex;
    }
    return ResponseEntity.status(serviceResult.status).body(serviceResult);
}

    @DeleteMapping("/eliminar/{empleadoId}")
    public ResponseEntity<ServiceResult<Empleado>> eliminarEmpleado(@PathVariable Integer empleadoId) {
        ServiceResult<Empleado> serviceResult = new ServiceResult<>();
        try {
            Integer filasAfectadas = empleadoService.eliminarEmpleado(empleadoId);
            if (filasAfectadas != null && filasAfectadas > 0) {
                serviceResult.status = 200;
                serviceResult.correct = true;
            } else {
                serviceResult.status = 404;
                serviceResult.correct = false;
                serviceResult.ErrorMessage = "Empleado no encontrado.";
            }
        } catch (Exception ex) {
            serviceResult.status = 500;
            serviceResult.correct = false;
            serviceResult.ErrorMessage = ex.getLocalizedMessage();
            serviceResult.ex = ex;
        }
        return ResponseEntity.status(serviceResult.status).body(serviceResult);
    }

    @PostMapping(value = "/cargar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<ServiceResult<Empleado>> cargarDesdeArchivo(
        @RequestParam("archivo") MultipartFile archivo) {
    ServiceResult<Empleado> serviceResult = new ServiceResult<>();
    try {
        empleadoService.cargarEmpleadosDesdeArchivo(archivo);
        serviceResult.status = 200;
        serviceResult.correct = true;
    } catch (Exception ex) {
        serviceResult.status = 500;
        serviceResult.correct = false;
        serviceResult.ErrorMessage = ex.getLocalizedMessage();
        serviceResult.ex = ex;
    }
    return ResponseEntity.status(serviceResult.status).body(serviceResult);
}

}
