final class CalculoSueldo {

	private CalculoSueldo() {
	}

	static double extraTransporte(Transporte transporte) {
		// WMC/CCOg: el switch concentra la variacion por categoria y mantiene estable el resto.
		switch (transporte.categoria()) {
			case Mercancias:
				return transporte.ton() * 2;
			case Mercancias_Peligrosas:
				return transporte.ton() * 2 + 50;
			case Personas:
				return extraPersonas(transporte);
			default:
				throw new IllegalStateException("Categoria de transporte no soportada");
		}
	}

	private static double extraPersonas(Transporte transporte) {
		if (transporte.getPersonas() < 10) {
			return transporte.horas() * 0.5;
		}
		return transporte.horas();
	}
}