package es.unican.is2.franquiciasuccommon;


import java.time.LocalDate;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) {
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
	}
	
public double sueldoBruto() {
		double sueldoBase = 0;
		switch(categoria) {
			case ENCARGADO:
				sueldoBase = 2000;
				break;
			case VENDEDOR:
				sueldoBase = 1500;
				break;
			case AUXILIAR:
				sueldoBase = 1000;
				break;
		}

		int years = LocalDate.now().getYear() - fechaContratacion.getYear();
		if (fechaContratacion.plusYears(years).isAfter(LocalDate.now())) {
			years--;
		}

		double complemento = 0;
		if (years > 20) {
			complemento = 200;
		} else if (years > 10) {
			complemento = 100;
		} else if (years > 5) {
			complemento = 50;
		}

		double total = sueldoBase + complemento;
		if (baja) {
			total = total * 0.75;
		}

		return total;
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado estï¿½ de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                Empleado other = (Empleado) obj;
                if (DNI == null) {
                        if (other.DNI != null)
                                return false;
                } else if (!DNI.equals(other.DNI))
                        return false;
                return true;
        }

}
