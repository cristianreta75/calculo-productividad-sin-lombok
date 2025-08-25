package com.productividad.calculo.service;

import com.productividad.calculo.model.TurnoAlephoo;
import com.productividad.calculo.repository.RhEmpleadoRepository;
import com.productividad.calculo.repository.TurnoAlephooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoAlephooSyncService {

    @Autowired
    private JdbcTemplate alephooJdbc;

    @Autowired
    private JdbcTemplate odooJdbc;

    @Autowired
    private TurnoAlephooRepository turnoRepo;

    @Autowired
    private RhEmpleadoRepository rhEmpleadoRepo;

    private String loadSqlFile() throws IOException {
        var resource = new ClassPathResource("sql/alephoo_turnos.sql");
        return Files.readString(resource.getFile().toPath());
    }

    @Transactional
    public int sincronizar(Integer anio, Integer mes, Integer empleadoId) {
        try {
            Integer idAlephoo = rhEmpleadoRepo.findIdAlephooById(empleadoId);
            if (idAlephoo == null) {
                throw new RuntimeException("El empleado " + empleadoId + " no tiene idAlephoo configurado.");
            }
            
            YearMonth periodoFacturacion = YearMonth.of(anio, mes);
            YearMonth periodoTurnos = periodoFacturacion.minusMonths(1);

            LocalDate fechaInicio = periodoTurnos.atDay(1);
            LocalDate fechaFin = periodoTurnos.atEndOfMonth();

            String sql = loadSqlFile();

            List<TurnoAlephoo> turnos = alephooJdbc.query(sql,new Object[]{anio, mes, fechaInicio, fechaFin, idAlephoo, idAlephoo},(rs, rowNum) -> mapRow(rs, rowNum, empleadoId));

            int count = 0;
            for (TurnoAlephoo t : turnos) {
                t.setEmpleadoId(empleadoId);

                Optional<TurnoAlephoo> existente =
                    turnoRepo.findByItemTurnoIdAndPrestacionCodigo(t.getItemTurnoId(), t.getPrestacionCodigo());

                if (existente.isEmpty()) {
                    turnoRepo.save(t);
                    count++;
                }
            }

            return count;

        } catch (IOException e) {
            throw new RuntimeException("Error al leer alephoo_turnos.sql", e);
        }
    }

    private TurnoAlephoo mapRow(ResultSet rs, int rowNum, Integer empleadoId) throws SQLException {
        TurnoAlephoo t = new TurnoAlephoo();
        t.setTurnoId(rs.getInt("turno_id"));
        t.setEmpleadoId(empleadoId);
        t.setFecha(rs.getDate("fecha").toLocalDate());
        t.setPrestacionCodigo(rs.getString("prestacion_codigo"));
        t.setPrestacionNombre(rs.getString("prestacion_nombre"));
        t.setPrestacionCantidad(rs.getInt("prestacion_cantidad"));
        t.setImporte(rs.getDouble("importe"));
        t.setImporteCoseguro(rs.getDouble("importe_coseguro"));
        t.setImporteTotal(rs.getDouble("importe_total"));
        t.setEspecialidad(rs.getString("especialidad"));
        t.setPacienteNombre(rs.getString("paciente_nombre"));
        t.setPacienteDni(rs.getString("paciente_dni"));
        LocalDate fechaNac = rs.getDate("paciente_fecha_nacimiento").toLocalDate();
        t.setPacienteFechaNacimiento(fechaNac);
        if (fechaNac != null) {
            int edad = Period.between(fechaNac, LocalDate.now()).getYears();
            t.setPacienteEdad(String.valueOf(edad));
        } else {
            t.setPacienteEdad(null);
        }
        t.setPacienteFechaNacimiento(rs.getDate("paciente_fecha_nacimiento").toLocalDate());
        t.setPacienteNumeroHc(rs.getInt("paciente_numero_hc"));
        t.setMedicoNombre(rs.getString("medico_nombre"));
        t.setMedicoId(rs.getInt("medico_id"));
        t.setFacturaNro(rs.getInt("factura_nro"));
        t.setEstado(rs.getString("estado"));
        t.setMes(rs.getString("mes"));
        t.setItemTurnoId(rs.getInt("item_turno_id"));
        return t;
    }

}
