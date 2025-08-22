package com.productividad.calculo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productividad.calculo.api.dto.*;
import com.productividad.calculo.model.*;
import com.productividad.calculo.repository.*;

@Service
public class ProductividadQueryService {

    @Autowired private ProductividadRepository productividadRepo;
    @Autowired private ProductividadEmpleadoRepository prodEmpRepo;
    @Autowired private ProductividadEmpleadoDetalleRepository detalleRepo;

    @Transactional(readOnly = true)
    public Optional<ProductividadViewDTO> verProductividad(Integer anio, Integer mes, Integer empleadoId) {

        Optional<Productividad> opt = productividadRepo.findByAnioAndMes(anio, mes);
        if (opt.isEmpty()) return Optional.empty();

        Productividad p = opt.get();

        ProductividadViewDTO dto = new ProductividadViewDTO();
        dto.setAnio(p.getAnio());
        dto.setMes(p.getMes());
        dto.setEstado(p.getEstado() == null ? null : p.getEstado().name());
        dto.setImporteTotal(p.getImporteTotal());

        List<ProductividadEmpleado> pes;
        if (empleadoId != null) {
            Optional<ProductividadEmpleado> uno = prodEmpRepo.findByProductividadIdAndEmpleadoId(p.getId(), empleadoId);
            pes = uno.map(Collections::singletonList).orElseGet(ArrayList::new);
        } else {
            pes = prodEmpRepo.findByProductividadId(p.getId());
        }

        List<EmpleadoViewDTO> empleados = new ArrayList<>();
        for (ProductividadEmpleado pe : pes) {
            EmpleadoViewDTO ev = new EmpleadoViewDTO();
            ev.setEmpleadoId(pe.getEmpleado() != null ? pe.getEmpleado().getId() : null);
            ev.setEmpleadoNombre(nombreEmpleado(pe.getEmpleado()));
            ev.setImporte(pe.getImporte());

            List<ProductividadEmpleadoDetalle> dets = detalleRepo.findByProductividadEmpleadoId(pe.getId());
            List<DetalleViewDTO> detDTOs = dets.stream().map(d -> {
                DetalleViewDTO dv = new DetalleViewDTO();
                dv.setDetalleId(d.getId());
                dv.setMetodo(d.getMetodoCalculo() != null ? safeNombreMetodo(d.getMetodoCalculo()) : null);
                dv.setFormaCalculo(d.getFormaCalculo() != null ? d.getFormaCalculo().name() : null);
                dv.setBaseValor(d.getBaseValor());
                dv.setValorPunto(d.getValorPunto());
                dv.setPorcentaje(d.getPorcentaje());
                dv.setValorMontoFijo(d.getValorMontoFijo());
                dv.setHorario(d.getHorario());
                dv.setCantidadPracticas(d.getCantidadPracticasRealizadas());
                dv.setImporte(d.getImporte());
                return dv;
            }).collect(Collectors.toList());

            ev.setDetalles(detDTOs);
            empleados.add(ev);
        }

        dto.setEmpleados(empleados);
        return Optional.of(dto);
    }

    // -------- Helpers

    private String nombreEmpleado(RhEmpleado e) {
        if (e == null) return null;
        try {
            if (has(e, "getNombreCompleto")) return String.valueOf(e.getClass().getMethod("getNombreCompleto").invoke(e));
            if (has(e, "getNombre")) return String.valueOf(e.getClass().getMethod("getNombre").invoke(e));
            if (has(e, "getName")) return String.valueOf(e.getClass().getMethod("getName").invoke(e));
        } catch (Exception ignored) {}
        return "Empleado " + e.getId();
    }

    private String safeNombreMetodo(MetodoCalculo m) {
        try {
            if (has(m, "getNombre")) return String.valueOf(m.getClass().getMethod("getNombre").invoke(m));
            if (has(m, "getName")) return String.valueOf(m.getClass().getMethod("getName").invoke(m));
        } catch (Exception ignored) {}
        return "Método #" + m.getId();
    }

    private boolean has(Object o, String method) {
        for (var m : o.getClass().getMethods()) {
            if (m.getName().equals(method) && m.getParameterCount() == 0) return true;
        }
        return false;
    }
}
