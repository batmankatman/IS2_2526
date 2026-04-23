package es.unican.is2.common;

import java.util.LinkedList;
import java.util.List;

public class Cliente {

    private String dni;

    private String nombre;  
    
    private boolean minusvalia;

    private List<Seguro> seguros = new LinkedList<Seguro>();
    
    public List<Seguro> getSeguros() {
        return seguros;
    }
    
    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public boolean getMinusvalia() {
    	return minusvalia;
    }

    public void setMinusvalia(boolean minusvalia) {
        this.minusvalia = minusvalia;
    }
    
    public double totalSeguros() {
        double total = 0;
        for (Seguro s : seguros) {
            total += s.precio();
        }
        if (minusvalia) {
            total *= 0.8;
        }
        return total;
    }

}
