package es.unican.is2.common;

public interface IGestionSeguros {

	Seguro nuevoSeguro(Seguro s, String dni);

	Seguro bajaSeguro(String matricula, String dni);

	Seguro anadeConductorAdicional(String matricula, String conductor);

}
