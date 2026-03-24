package es.unican.is2.common;

public interface ISegurosDAO {

	Seguro[] seguros();

	Seguro seguro(long id);

	Seguro creaSeguro(Seguro s);

	Seguro actualizaSeguro(Seguro nuevo);

	Seguro eliminaSeguro(long id);

	Seguro seguroPorMatricula(String matricula);

}
