package com.productividad.calculo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.productividad.calculo.api.dto.ProductividadViewDTO;
import com.productividad.calculo.service.ProductividadQueryService;

@RestController
@RequestMapping("/api/productividad")
public class ProductividadReadController {

    @Autowired private ProductividadQueryService queryService;

    @GetMapping("/ver")
    public ResponseEntity<ProductividadViewDTO> ver(
            @RequestParam(name = "anio") Integer anio,
            @RequestParam(name = "mes") Integer mes,
            @RequestParam(name = "empleadoId", required = false) Integer empleadoId) {

        Optional<ProductividadViewDTO> dto = queryService.verProductividad(anio, mes, empleadoId);
        return dto.map(ResponseEntity::ok)
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay productividad para ese período"));
    }
}
