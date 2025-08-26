package com.productividad.calculo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productividad.calculo.model.AgrupadorPrestaciones;
import com.productividad.calculo.model.Prestacion;
import com.productividad.calculo.model.TipoPunto;
import com.productividad.calculo.repository.AgrupadorPrestacionesRepository;
import com.productividad.calculo.repository.PrestacionRepository;
import com.productividad.calculo.repository.TipoPuntoRepository;

@RestController
@RequestMapping("/api/v1/productividad/config")
public class ConfiguracionController {

    @Autowired
    private PrestacionRepository prestacionRepo;

    @Autowired
    private AgrupadorPrestacionesRepository agrupadorRepo;

    @Autowired
    private TipoPuntoRepository tipoPuntoRepo;

    @GetMapping("/prestaciones")
    public List<Prestacion> getPrestaciones() {
        return prestacionRepo.findAll();
    }

    @GetMapping("/agrupadores")
    public List<AgrupadorPrestaciones> getAgrupadores() {
        return agrupadorRepo.findAll();
    }

    @GetMapping("/tipos-punto")
    public List<TipoPunto> getTiposPunto() {
        return tipoPuntoRepo.findAll();
    }
}
