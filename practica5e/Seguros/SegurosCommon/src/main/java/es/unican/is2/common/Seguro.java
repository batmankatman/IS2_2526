package es.unican.is2.common;

import java.time.LocalDate;

public class Seguro {

    private long id;
    private String matricula;
    private int potencia;
    private Cobertura cobertura;
    private LocalDate fechaInicio;
    private String conductorAdicional;

    public Seguro() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getConductorAdicional() {
        return conductorAdicional;
    }

    public void setConductorAdicional(String conductorAdicional) {
        this.conductorAdicional = conductorAdicional;
    }

    public double precio() throws OperacionNoValida {
        if (cobertura == null || potencia <= 0) {
            throw new OperacionNoValida("Datos no válidos");
        }
        
        double precioBase = 0;
        
        switch (cobertura) {
            case TODO_RIESGO:
                precioBase = 1000;
                break;
            case TERCEROS_LUNAS:
                precioBase = 600;
                break;
            case TERCEROS:
                precioBase = 400;
                break;
            default:
                throw new OperacionNoValida("Cobertura no válida");
        }

        if (potencia > 110) {
            precioBase *= 1.20;
        } else if (potencia >= 90) {
            precioBase *= 1.05;
        }

        if (fechaInicio != null) {
            int antiguedad = LocalDate.now().getYear() - fechaInicio.getYear();
            if (LocalDate.now().getDayOfYear() < fechaInicio.getDayOfYear()) {
                antiguedad--;
            }
            if (antiguedad >= 3) {
                precioBase *= 0.80; // 20% descuento
            } else if (antiguedad >= 1) {
                precioBase *= 0.90; // 10% descuento
            }
        }
        return precioBase;
    }
}
