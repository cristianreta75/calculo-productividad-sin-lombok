SELECT 
  tp.id AS turno_id,
  CASE MONTH(tp.fecha) 
    WHEN 1 THEN 'Enero' WHEN 2 THEN 'Febrero' WHEN 3 THEN 'Marzo' WHEN 4 THEN 'Abril' 
    WHEN 5 THEN 'Mayo' WHEN 6 THEN 'Junio' WHEN 7 THEN 'Julio' WHEN 8 THEN 'Agosto' 
    WHEN 9 THEN 'Septiembre' WHEN 10 THEN 'Octubre' WHEN 11 THEN 'Noviembre' WHEN 12 THEN 'Diciembre' 
  END AS mes, 
  CONCAT(p.apellidos, ' ', p.nombres) AS paciente_nombre, 
  p.documento AS paciente_dni, 
  p.fecha_nacimiento AS paciente_fecha_nacimiento, 
  FORMAT(DATEDIFF(NOW(), p.fecha_nacimiento) / 365,0) AS paciente_edad, 
  es.nombre AS especialidad, 
  tp.fecha AS fecha, 
  tp.hora AS hora, 
  p.nro_hc AS paciente_numero_hc, 
  dep.nombre AS departamento, 
  IF (ISNULL(con.evento_id), 
      CONCAT(p2.apellidos, ' ', p2.nombres), 
      CONCAT(medc.apellidos, ' ', medc.nombres)
  ) AS medico_nombre,
  pr.id AS medico_id,
  CASE tp.estado_turno_id 
    WHEN 1 THEN 'PENDIENTE' WHEN 2 THEN 'EN CURSO' WHEN 3 THEN 'CANCELADO' WHEN 4 THEN 'NO ASISTIO' 
    WHEN 5 THEN 'REALIZADO' WHEN 6 THEN 'ARRIBO' WHEN 7 THEN 'BLOQUEADO' WHEN 8 THEN 'NO ATENDIDO' WHEN 9 THEN 'VISTO' 
  END AS estado, 
  fac.numero AS factura_nro,
  bi.monto / 100 AS importe, 
  IF(ISNULL(f.monto),0,f.monto / 100) AS importe_coseguro, 
  (bi.monto / 100 + IF(ISNULL(f.monto),0,f.monto / 100)) * bi.cantidad AS importe_total, 
  bi.id AS item_turno_id, 
  bi.cantidad AS prestacion_cantidad, 
  pre.codigo AS prestacion_codigo, 
  pre.nombre AS prestacion_nombre

FROM turno_programado tp
INNER JOIN persona p ON tp.persona_id = p.id
INNER JOIN bono bo ON bo.turnoprogramado_id = tp.id
INNER JOIN tipobono tb ON bo.tipobono_id = tb.id
INNER JOIN item_bono bi ON bi.bono_id = bo.id
LEFT JOIN item_bono bi2 ON bi2.id = bi.item_bono_related
LEFT JOIN bono bo2 ON bo2.id = bi2.bono_id
LEFT JOIN prefacturaitem p3 ON p3.bono_id = bo2.id
LEFT JOIN factura_prefactura fp ON fp.prefactura_id = p3.prefactura_id
LEFT JOIN factura f ON f.id = fp.factura_id
LEFT JOIN itemAutorizacion ia ON ia.itemBonoId = bi.id
INNER JOIN prestacion pre ON bi.prestacion_id = pre.id
INNER JOIN plan pl ON bo.plan_id = pl.id
INNER JOIN agenda ag ON tp.agenda_id = ag.id
INNER JOIN asignacion asig ON ag.asignacion_id = asig.id
INNER JOIN especialidad es ON asig.especialidad_id = es.id
INNER JOIN departamento dep ON es.departamento_id = dep.id
INNER JOIN personal pr ON asig.personal_id = pr.id
INNER JOIN persona p2 ON pr.persona_id = p2.id
LEFT JOIN prefacturaitem prefi ON prefi.bono_id = bo.id
LEFT JOIN prefactura pref ON pref.id = prefi.prefactura_id
LEFT JOIN factura_prefactura fac_pre ON fac_pre.prefactura_id = pref.id
LEFT JOIN factura fac ON fac.id = fac_pre.factura_id
INNER JOIN documentofacturacion df ON df.id = fac.id
INNER JOIN centrodecosto cc ON cc.id = fac.centrodecosto_id
LEFT JOIN recibo_factura reci_fac ON reci_fac.factura_id = fac.id
LEFT JOIN recibo rec ON rec.id = reci_fac.recibo_id
LEFT JOIN recibopago recp ON recp.recibo_id = rec.id
LEFT JOIN consulta con ON con.id = tp.consulta_id
LEFT JOIN personal perc ON perc.id = con.personal_id
LEFT JOIN persona medc ON medc.id = perc.persona_id
LEFT JOIN usuario usuc ON usuc.id = bo.creadopor_id
LEFT JOIN usuario usum ON usum.id = bo.modificadopor_id
LEFT JOIN persona_plan pp ON pp.plan_id = bo.plan_id AND pp.persona_id = bo.persona_id
LEFT JOIN (
    SELECT bo4.turnoprogramado_id, COUNT(*) AS cnt
    FROM bono bo4
    INNER JOIN item_bono bi4 ON bi4.bono_id = bo4.id
    INNER JOIN prestacion pre4 ON bi4.prestacion_id = pre4.id
    INNER JOIN cotizacion coti4 ON coti4.prestacion_id = pre4.id
    WHERE bo4.tipobono_id IN (1, 2) AND bo4.estado <> 6
    GROUP BY bo4.turnoprogramado_id
) AS filtro1 ON filtro1.turnoprogramado_id = bo.turnoprogramado_id
LEFT JOIN (
    SELECT bo5.turnoprogramado_id, COUNT(*) AS cnt
    FROM bono bo5
    INNER JOIN item_bono bi5 ON bi5.bono_id = bo5.id
    INNER JOIN prestacion pre5 ON bi5.prestacion_id = pre5.id
    INNER JOIN cotizacion coti5 ON coti5.prestacion_id = pre5.id
    WHERE bo5.tipobono_id = 10 AND bo5.estado = 8
    GROUP BY bo5.turnoprogramado_id
) AS filtro2 ON filtro2.turnoprogramado_id = bo.turnoprogramado_id
LEFT JOIN (
    SELECT bo6.turnoprogramado_id, COUNT(*) AS cnt
    FROM bono bo6
    INNER JOIN item_bono bi6 ON bi6.bono_id = bo6.id
    INNER JOIN prestacion pre6 ON bi6.prestacion_id = pre6.id
    INNER JOIN cotizacion coti6 ON coti6.prestacion_id = pre6.id
    WHERE bo6.tipobono_id = 10
    GROUP BY bo6.turnoprogramado_id
) AS filtro3 ON filtro3.turnoprogramado_id = bo.turnoprogramado_id
WHERE
    (tb.id <> 10 OR filtro1.cnt IS NULL)
    AND ((tb.id NOT IN (1, 2) OR bo.estado = 7) OR filtro2.cnt IS NULL)
    AND ((tb.id NOT IN (1, 2) OR bo.estado NOT IN (5, 6)) OR filtro3.cnt IS NULL)
    AND (
        (cc.id IN (10, 8) AND YEAR(df.fecha) = ? AND MONTH(df.fecha) = ?)
        OR
        (cc.id = 11 AND tp.fecha BETWEEN ? AND ?)
    )
    AND IF(ISNULL(con.evento_id), pr.id = ?, con.personal_id = ?)
    AND bo.estado = 7
GROUP BY bo.id, bi.id
ORDER BY p.apellidos ASC;