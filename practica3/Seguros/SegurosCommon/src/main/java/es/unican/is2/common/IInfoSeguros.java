package es.unican.is2.common;

public interface IInfoSeguros {

	Cliente cliente(String dni);

	Seguro seguro(String matricula);

}
