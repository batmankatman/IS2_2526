package es.unican.is2.common;

import java.time.LocalDate;

public class Seguro {
	
	private long id;

    private String matricula;

	private int potencia;

    private Cobertura cobertura;
    
    private LocalDate fechaInicio;

	private String conductorAdicional;

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
    
	public double precio() {
		double base;
		switch (cobertura) {
		case TERCEROS:
			base = potencia * 1.5;
			break;
		case TERCEROS_LUNAS:
			base = potencia * 1.8;
			break;
		case TODO_RIESGO:
			base = potencia * 2.5;
			break;
		default:
			base = 0;
			break;
		}
		if (conductorAdicional != null && !conductorAdicional.isEmpty()) {
			base += 50;
		}
		return base;
	}
	
}
