package com.productividad.calculo.service;

import com.productividad.calculo.model.*;
import com.productividad.calculo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductividadService {

    @Autowired private ProductividadRepository productividadRepo;
    @Autowired private RhEmpleadoRepository empleadoRepo;
    @Autowired private ProductividadEmpleadoRepository prodEmpRepo;
    @Autowired private ProductividadEmpleadoDetalleRepository detalleRepo;
    @Autowired private ProdEmpDetTurnoRepository detTurnoRepo;
    @Autowired private TurnoAlephooRepository turnoRepo;
    @Autowired private EmpleadoCalculoService empCalcService;

    @Transactional
    public void generarProductividadMensual(Integer mes, Integer anio, Integer limiteEmpleados, List<Integer> empleadosIds) {
    	if (mes == null || anio == null) {
            LocalDate now = LocalDate.now();
            mes  = (mes  == null) ? now.getMonthValue() : mes;
            anio = (anio == null) ? now.getYear()       : anio;
        }

        final Integer mesF  = mes;
        final Integer anioF = anio;

        Productividad prod = productividadRepo.findByAnioAndMes(anioF, mesF)
            .orElseGet(() -> {
                Productividad p = new Productividad();
                p.setAnio(anioF);
                p.setMes(mesF);
                p.setName(anioF + " - " + String.format("%02d", mesF));
                p.setEstado(EstadoProductividad.EN_CALCULO);
                return productividadRepo.save(p);
            });

        List<RhEmpleado> empleados;
        if (empleadosIds != null && !empleadosIds.isEmpty()) {
            empleados = empleadoRepo.findAllById(empleadosIds);
        } else {
            empleados = empleadoRepo.findAll();
        }
        if (limiteEmpleados != null && limiteEmpleados > 0 && empleados.size() > limiteEmpleados) {
            empleados = new ArrayList<>(empleados.subList(0, limiteEmpleados));
        }

        BigDecimal totalProd = BigDecimal.valueOf(prod.getImporteTotal() == null ? 0 : prod.getImporteTotal());

        for (RhEmpleado emp : empleados) {
            List<EmpleadoCalculoService.CalculoResult> resultados = empCalcService.calcularProductividad(emp, mes, anio);

            ProductividadEmpleado pe = prodEmpRepo.findByProductividadIdAndEmpleadoId(prod.getId(), emp.getId())
                    .orElseGet(() -> {
                        ProductividadEmpleado x = new ProductividadEmpleado();
                        x.setProductividad(prod); x.setEmpleado(emp);
                        return prodEmpRepo.save(x);
                    });

            BigDecimal totalEmp = BigDecimal.ZERO;

            for (EmpleadoCalculoService.CalculoResult r : resultados) {
                ProductividadEmpleadoDetalle det = new ProductividadEmpleadoDetalle();
                det.setProductividadEmpleado(pe);
                det.setImporte(r.getImporte().doubleValue());
                det.setCantidadPracticasRealizadas(r.getCantidadPracticas());
                det.setMetodoCalculo(r.getMetodoCalculo());
                det.setMetodoCalculoVariable(r.getVariable());
                det.setHorario(r.getHorario());
                if (r.getVariable() != null) {
                    det.setFormaCalculo(r.getVariable().getFormaCalculo());
                    det.setBaseValor(r.getVariable().getBaseValor());
                    det.setTipoPunto(r.getVariable().getTipoPunto());
                    det.setValorPunto(r.getVariable().getValorPunto());
                    det.setPorcentaje(r.getVariable().getPorcentaje());
                    det.setValorMontoFijo(r.getVariable().getValorMontoFijo());
                }
                detalleRepo.save(det);

                if (r.getTurnoIds() != null) {
                    for (Integer turnoId : r.getTurnoIds()) {
                        ProdEmpDetTurno link = new ProdEmpDetTurno();
                        link.setDetalle(det);
                        TurnoAlephoo t = turnoRepo.findById(turnoId).orElse(null);
                        link.setTurno(t);
                        link.setIncluido(Boolean.TRUE);
                        detTurnoRepo.save(link);
                    }
                }
                totalEmp = totalEmp.add(r.getImporte());
            }

            pe.setImporte(totalEmp.doubleValue());
            prodEmpRepo.save(pe);

            if (!emp.isNoIncluirEnImporteTotalProductividad()) {
                totalProd = totalProd.add(totalEmp);
            }
        }

        int totalEntero = totalProd.setScale(0, java.math.RoundingMode.HALF_UP).intValue();
        prod.setImporteTotal(totalEntero);

        if (prod.getEstado() == EstadoProductividad.EN_CALCULO && empleados.isEmpty()) {
            prod.setEstado(EstadoProductividad.CALCULO_COMPLETO);
        }

        productividadRepo.save(prod);
    }

    @Transactional
    public void recalcularDetalle(Integer detalleId) {
        ProductividadEmpleadoDetalle det = detalleRepo.findById(detalleId).orElseThrow();
        int cantidad = 0;
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal impManual = BigDecimal.ZERO;

        if (det.getTurnos() != null) {
            for (ProdEmpDetTurno x : det.getTurnos()) {
                if (x.isIncluido()) {
                    TurnoAlephoo t = x.getTurno();
                    if (t != null) {
                        int cant = t.getPrestacionCantidad() == null ? 0 : t.getPrestacionCantidad();
                        if (cant != 0) {
                            cantidad += cant;
                            total = total.add(BigDecimal.valueOf(t.getImporteTotal() == null ? 0.0 : t.getImporteTotal()));
                        }
                        if ("MODIFICACION MANUAL DE IMPORTE".equals(t.getPrestacionCodigo())) {
                            impManual = impManual.add(BigDecimal.valueOf(t.getImporteTotal() == null ? 0.0 : t.getImporteTotal()));
                        }
                    }
                }
            }
        }

        MetodoCalculoVariable v = det.getMetodoCalculoVariable();
        BigDecimal importe;
        if (v == null || v.getFormaCalculo() == null) {
            importe = total.add(impManual);
        } else {
            importe = calcularImporte(v, cantidad, total).add(impManual);
        }
        if (importe.signum() < 0) importe = BigDecimal.ZERO;

        det.setImporte(importe.doubleValue());
        det.setCantidadPracticasRealizadas(cantidad);
        detalleRepo.save(det);

        ProductividadEmpleado pe = det.getProductividadEmpleado();
        double suma = 0.0;
        if (pe.getDetalles() != null) {
            for (ProductividadEmpleadoDetalle d : pe.getDetalles()) {
                suma += d.getImporte() == null ? 0.0 : d.getImporte();
            }
        }
        pe.setImporte(suma);
        prodEmpRepo.save(pe);

        Productividad prod = pe.getProductividad();
        double totalDouble = 0.0;
        if (prod.getEmpleados() != null) {
            for (ProductividadEmpleado e : prod.getEmpleados()) {
                if (!e.getEmpleado().isNoIncluirEnImporteTotalProductividad()) {
                    totalDouble += e.getImporte() == null ? 0.0 : e.getImporte();
                }
            }
        }
        int totalEntero = BigDecimal.valueOf(totalDouble).setScale(0, java.math.RoundingMode.HALF_UP).intValue();
        prod.setImporteTotal(totalEntero);
        productividadRepo.save(prod);
    }

    private BigDecimal calcularImporte(MetodoCalculoVariable v, int cantidad, BigDecimal total) {
        BigDecimal tipoPunto = v.getTipoPunto() != null && v.getTipoPunto().getValor() != null
                ? BigDecimal.valueOf(v.getTipoPunto().getValor()) : BigDecimal.ZERO;
        BigDecimal valorPunto = BigDecimal.valueOf(v.getValorPunto() == null ? 0d : v.getValorPunto());
        int base = v.getBaseValor() == null ? 0 : v.getBaseValor();

        switch (v.getFormaCalculo()) {
            case PUNTAJE:
                return BigDecimal.valueOf(cantidad - base).multiply(valorPunto).multiply(tipoPunto);
            case PORCENTAJE_FACTURADO:
                return total.multiply(BigDecimal.valueOf((v.getPorcentaje() == null ? 0 : v.getPorcentaje()) / 100.0));
            case MONTO_FIJO_CANTIDAD:
                return BigDecimal.valueOf(cantidad).multiply(BigDecimal.valueOf(v.getValorMontoFijo() == null ? 0d : v.getValorMontoFijo()));
            case MONTO_FIJO:
                return BigDecimal.valueOf(v.getValorMontoFijo() == null ? 0d : v.getValorMontoFijo());
            case FORMULA_VIEJA:
                return BigDecimal.valueOf(cantidad).multiply(valorPunto).subtract(BigDecimal.valueOf(base)).multiply(tipoPunto);
            default:
                return BigDecimal.ZERO;
        }
    }
}
