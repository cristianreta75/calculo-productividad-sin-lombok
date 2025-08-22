package com.productividad.calculo.service;

import com.productividad.calculo.model.*;
import com.productividad.calculo.repository.TurnoAlephooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoCalculoService {

    @Autowired
    private TurnoAlephooRepository turnoRepo;

    @Transactional(readOnly = true)
    public List<CalculoResult> calcularProductividad(RhEmpleado emp, int mes, int anio) {
        List<CalculoResult> out = new ArrayList<>();

        // En esta versión base, tomamos todos los turnos del mes/año del empleado
        LocalDate desde = LocalDate.of(anio, mes, 1);
        LocalDate hasta = desde.withDayOfMonth(desde.lengthOfMonth());

        List<TurnoAlephoo> turnos = turnoRepo.findByEmpleadoIdAndFechaBetween(emp.getId(), desde, hasta);

        int cantidad = 0;
        BigDecimal totalFacturado = BigDecimal.ZERO;
        for (TurnoAlephoo t : turnos) {
            int cant = t.getPrestacionCantidad() == null ? 0 : t.getPrestacionCantidad();
            if (cant != 0) {
                cantidad += cant;
                totalFacturado = totalFacturado.add(BigDecimal.valueOf(t.getImporteTotal() == null ? 0.0 : t.getImporteTotal()));
            }
        }

        // Cálculo simple por monto fijo para que compile (ajusta según tus variables)
        BigDecimal importe = totalFacturado;

        out.add(new CalculoResult(
                turnos.stream().map(TurnoAlephoo::getId).collect(Collectors.toList()),
                importe,
                cantidad,
                null,
                "GENERAL",
                null
        ));

        return out;
    }

    public static class CalculoResult {
        private List<Integer> turnoIds;
        private BigDecimal importe;
        private int cantidadPracticas;
        private MetodoCalculo metodoCalculo;
        private String horario;
        private MetodoCalculoVariable variable;

        public CalculoResult() {}

        public CalculoResult(List<Integer> turnoIds, BigDecimal importe, int cantidadPracticas,
                             MetodoCalculo metodoCalculo, String horario, MetodoCalculoVariable variable) {
            this.turnoIds = turnoIds;
            this.importe = importe;
            this.cantidadPracticas = cantidadPracticas;
            this.metodoCalculo = metodoCalculo;
            this.horario = horario;
            this.variable = variable;
        }

        public List<Integer> getTurnoIds() { return turnoIds; }
        public void setTurnoIds(List<Integer> turnoIds) { this.turnoIds = turnoIds; }

        public BigDecimal getImporte() { return importe; }
        public void setImporte(BigDecimal importe) { this.importe = importe; }

        public int getCantidadPracticas() { return cantidadPracticas; }
        public void setCantidadPracticas(int cantidadPracticas) { this.cantidadPracticas = cantidadPracticas; }

        public MetodoCalculo getMetodoCalculo() { return metodoCalculo; }
        public void setMetodoCalculo(MetodoCalculo metodoCalculo) { this.metodoCalculo = metodoCalculo; }

        public String getHorario() { return horario; }
        public void setHorario(String horario) { this.horario = horario; }

        public MetodoCalculoVariable getVariable() { return variable; }
        public void setVariable(MetodoCalculoVariable variable) { this.variable = variable; }
    }
}
