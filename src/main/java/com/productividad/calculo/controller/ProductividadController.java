package com.productividad.calculo.controller;

import com.productividad.calculo.service.ProductividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/productividad")
public class ProductividadController {

    @Autowired
    private ProductividadService productividadService;

    @PostMapping("/calcular")
    public ResponseEntity<?> calcular(
            @RequestParam(name="mes", required=false) Integer mes,
            @RequestParam(name="anio", required=false) Integer anio,
            @RequestParam(name="empleadoId", required=false) Integer empleadoId,
            @RequestParam(name="limite", required=false) Integer limiteEmpleados
    ){
        List<Integer> empleados = empleadoId == null ? Collections.emptyList() : List.of(empleadoId);
        productividadService.generarProductividadMensual(mes, anio, limiteEmpleados, empleados);
        return ResponseEntity.ok().build();
        /*List<Integer> empleados = empleadoId == null ? Collections.emptyList() : List.of(empleadoId);
        var resultado = productividadService.generarProductividadMensual(mes, anio, limiteEmpleados, empleados);
        return ResponseEntity.ok(resultado);*/
    }

    @PostMapping("/recalcular-detalle/{id}")
    public ResponseEntity<?> recalcularDetalle(@PathVariable("id") Integer id){
        productividadService.recalcularDetalle(id);
        return ResponseEntity.ok().build();
    }
    
}
