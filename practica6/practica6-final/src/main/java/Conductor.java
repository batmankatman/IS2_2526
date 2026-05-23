import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {

	private final ArrayList<Transporte> transportes = new ArrayList<>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	public String dni() {
		return dni;
	}

	public double sueldo() {
		double sueldoTransportes = 0;
		for (Transporte t : transportes) {
			// WMC/CCOg: el bucle aporta la estructura principal y el detalle por categoria se delega.
			sueldoTransportes += t.horas() * 5 + CalculoSueldo.extraTransporte(t);
		}
		return 700 + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte t) {
		transportes.add(t);
	}

	@Override
	public String toString() {
		String resultado = nombre + " " + apellido1;
		if (apellido2 != null) {
			resultado += " " + apellido2;
		}
		return resultado;
	}

}
