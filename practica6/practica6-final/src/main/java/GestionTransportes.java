import java.util.ArrayList;
import java.util.List;

public class GestionTransportes {

	private final ArrayList<Conductor> cs = new ArrayList<>();
	
	public Conductor buscaConductor(String DNI) {		
		for (Conductor c : cs) 
			if (c.dni().equals(DNI))
				return c;
		
		return null;
	}
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
		if (buscaConductor(dni) != null)
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2, direccion));
		return true;
	}

	public List<Conductor> conductores() {
		return cs;
	}

	public List<Conductor> mejoresConductores() {
		// WMC/CCOg: una pasada lineal con comparaciones para mantener solo los maximos.
		List<Conductor> resultado = new ArrayList<>();
		double maxSueldo = Double.NEGATIVE_INFINITY;
		for (Conductor conductor : cs) {
			double sueldoConductor = conductor.sueldo();
			if (sueldoConductor > maxSueldo) {
				maxSueldo = sueldoConductor;
				resultado.clear();
				resultado.add(conductor);
			} else if (sueldoConductor == maxSueldo) {
				resultado.add(conductor);
			}
		}
		return resultado;
	}
	
}
