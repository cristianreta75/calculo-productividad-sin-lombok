package com.productividad.calculo.controller;

import com.productividad.calculo.service.TurnoAlephooSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alephoo")
public class TurnoAlephooSyncController {

    @Autowired
    private TurnoAlephooSyncService syncService;

    @PostMapping("/sync")
    public String sync(@RequestParam Integer anio,
                       @RequestParam Integer mes,
                       @RequestParam(required = false) Integer empleadoId) {
        int nuevos = syncService.sincronizar(anio, mes, empleadoId);
        return "Sincronización completa. Se insertaron " + nuevos + " turnos nuevos.";
    }
}