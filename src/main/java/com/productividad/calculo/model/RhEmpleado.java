package com.productividad.calculo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hr_employee")
public class RhEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_alephoo")
    private Integer idAlephoo;
    
    @Column(name = "name")
    private String name;

    @Column(name = "no_incluir_en_importe_total_productividad")
    private Boolean noIncluirEnImporteTotalProductividad;

    public RhEmpleado() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getIdAlephoo() { return idAlephoo; }
    public void setIdAlephoo(Integer idAlephoo) { this.idAlephoo = idAlephoo; }

    public Boolean getNoIncluirEnImporteTotalProductividad() { return noIncluirEnImporteTotalProductividad; }
    public void setNoIncluirEnImporteTotalProductividad(Boolean v) { this.noIncluirEnImporteTotalProductividad = v; }

    public boolean isNoIncluirEnImporteTotalProductividad(){
        return Boolean.TRUE.equals(noIncluirEnImporteTotalProductividad);
    }
}
